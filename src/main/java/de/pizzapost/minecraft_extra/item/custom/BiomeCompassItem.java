package de.pizzapost.minecraft_extra.item.custom;

import com.google.common.base.Predicate;
import com.mojang.datafixers.util.Pair;
import de.pizzapost.minecraft_extra.MinecraftExtra;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LodestoneTrackerComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BiomeCompassItem extends Item {
    public BiomeCompassItem(Settings settings) {
        super(settings);
    }

    private Pair<BlockPos, RegistryEntry<Biome>> findNearestBiome(PlayerEntity player, Identifier targetBiomeId) {
        World world = player.getEntityWorld();
        BlockPos searchCenter = player.getBlockPos();
        Predicate<RegistryEntry<Biome>> biomePredicate = entry -> {
            return entry.getKey().map(key -> key.getValue().equals(targetBiomeId)).orElse(false);
        };
        RegistryKey<World> dimensionKey = world.getRegistryKey();
        ServerWorld targetWorld = world.getServer().getWorld(dimensionKey);
        player.sendMessage(Text.translatable("actionbar.minecraft_extra.biome_compass_searching"), true);
        return targetWorld.locateBiome(biomePredicate, searchCenter, dimensionKey == World.NETHER ? 6000 : dimensionKey == World.END ? 10000 : 20000, 32, dimensionKey == World.NETHER ? 128 : 64);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Identifier advancementId = Identifier.of(MinecraftExtra.MOD_ID, "biome_compass");
        if (player instanceof ServerPlayerEntity serverPlayer) {
            AdvancementEntry advancement = world.getServer().getAdvancementLoader().get(advancementId);
            if (advancement != null) {
                serverPlayer.getAdvancementTracker().grantCriterion(advancement, "imp");
            }
        }
        if (!world.isClient()) {
            Registry<Biome> biomeRegistry = world.getRegistryManager().getOrThrow(RegistryKeys.BIOME);
            List<Biome> biomes = biomeRegistry.stream().filter(biome -> {
                Identifier id = biomeRegistry.getId(biome);
                return id != null && !id.getPath().equals("empty");
            }).collect(Collectors.toList());
            if (biomes.isEmpty()) {
                player.sendMessage(Text.translatable("actionbar.minecraft_extra.biome_compass_fail"), true);
                return ActionResult.FAIL;
            }
            Biome selectedBiome = biomes.get(world.getRandom().nextInt(biomes.size()));
            Identifier biomeId = biomeRegistry.getId(selectedBiome);
            String formattedBiomeId = biomeId.toString().replace("minecraft:", "").replace("_", " ").replace("minecraft_extra:", "");
            Pair<BlockPos, RegistryEntry<Biome>> result = findNearestBiome(player, biomeId);
            if (result != null) {
                BlockPos biomePos = result.getFirst();
                world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_LODESTONE_COMPASS_LOCK, SoundCategory.PLAYERS, 1.0F, 1.0F);
                LodestoneTrackerComponent lodestoneTrackerComponent = new LodestoneTrackerComponent(Optional.of(GlobalPos.create(world.getRegistryKey(), biomePos)), false);
                ItemStack compassStack = new ItemStack(Items.COMPASS);
                compassStack.set(DataComponentTypes.LODESTONE_TRACKER, lodestoneTrackerComponent);
                var changes = ComponentChanges.builder().add(DataComponentTypes.ITEM_NAME, Text.literal(formattedBiomeId)).add(DataComponentTypes.CUSTOM_NAME, Text.literal(formattedBiomeId)).build();
                compassStack.applyChanges(changes);
                player.giveOrDropStack(compassStack);
                if (!player.isInCreativeMode()) {
                    itemStack.decrement(1);
                }
                player.getInventory().markDirty();
                int distance = (int) Math.round(Math.sqrt(player.getBlockPos().getSquaredDistance(biomePos)));
                String formattedPos = String.format("%d, Y, %d", biomePos.getX(), biomePos.getZ());
                player.sendMessage(Text.literal(formattedBiomeId + ": " + formattedPos + " (" + distance + ")"), false);
            } else {
                player.sendMessage(Text.translatable("actionbar.minecraft_extra.biome_compass_fail", biomeId.toString().replace("minecraft:", "").replace("_", " ").replace("minecraft_extra:", "")), true);
                return ActionResult.FAIL;
            }
        }
        return ActionResult.SUCCESS;
    }
}