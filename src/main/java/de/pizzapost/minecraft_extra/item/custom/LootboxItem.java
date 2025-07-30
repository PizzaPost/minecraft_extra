package de.pizzapost.minecraft_extra.item.custom;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.item.ModItems;
import de.pizzapost.minecraft_extra.util.InventoryShuffler;
import net.minecraft.block.Block;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.context.LootWorldContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public class LootboxItem extends Item {
    public LootboxItem(Settings settings) {
        super(settings);
    }
    List paths = List.of(
            List.of("lootbox/1", -1, -1, -1),
            List.of("lootbox/2", 3, -1, 1),
            List.of("lootbox/3", 0, -14, 0),
            List.of("lootbox/4", 0, -1, 2),
            List.of("lootbox/5", 0, -1, 0),
            List.of("lootbox/6", 0, 0, 0),
            List.of("lootbox/7", 0, 0, 0),
            List.of("lootbox/8", 0, 0, 0),
            List.of("lootbox/9", 0, 0, 8),
            List.of("lootbox/10", 0, 0, 0),
            List.of("lootbox/11", 0, 0, 0),
            List.of("lootbox/12", 0, 0, 0));

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        Random random = world.getRandom();
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        player.getStackInHand(hand).decrement(1);
        if (player instanceof ServerPlayerEntity serverPlayer) {
            if (random.nextInt(1) == 0) {
                int x = random.nextInt(4+paths.size());
                if (x == 0) {
                    Inventory inventory = player.getInventory();
                    if (!inventory.isEmpty()) {
                        InventoryShuffler.startShuffle(serverPlayer);
                    } else {
                        player.setVelocity(player.getVelocity().x, 2, player.getVelocity().z);
                        player.velocityModified = true;
                        player.velocityDirty = true;
                        return ActionResult.SUCCESS;
                    }
                    return ActionResult.SUCCESS;
                } else if (x == 1) {
                    TntEntity tntEntity = new TntEntity(world, player.getX(), player.getY(), player.getZ(), player);
                    tntEntity.setFuse(20);
                    world.spawnEntity(tntEntity);
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                    world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                    return ActionResult.SUCCESS;
                } else if (x == 2) {
                    player.addVelocity(0, 2, 0);
                    player.velocityModified = true;
                    player.velocityDirty = true;
                    return ActionResult.SUCCESS;
                } else if (x > 3) {
                    placeStructure((ServerWorld) world, player.getBlockPos(), player);
                    return ActionResult.SUCCESS;
                }
            }
            RegistryKey lootTableId = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(MinecraftExtra.MOD_ID, "items/lootbox"));
            LootTable lootTable = serverPlayer.getServer().getReloadableRegistries().getLootTable(lootTableId);
            LootWorldContext parameters = new LootWorldContext.Builder((ServerWorld) world).add(LootContextParameters.ORIGIN, serverPlayer.getPos()).add(LootContextParameters.THIS_ENTITY, serverPlayer).build(LootContextTypes.CHEST);
            List<ItemStack> items = lootTable.generateLoot(parameters);
            for (ItemStack item : items) {
                player.setStackInHand(hand, item);
            }
        }
        return ActionResult.FAIL;
    }

    private boolean placeStructure(ServerWorld world, BlockPos pos, PlayerEntity player) {
        Object path = paths.get(world.getRandom().nextInt(paths.size()));
        StructureTemplateManager manager = world.getStructureTemplateManager();
        Identifier id = Identifier.of(MinecraftExtra.MOD_ID, (String) ((List<?>) path).get(0));
        Optional<StructureTemplate> template = manager.getTemplate(id);
        if (template.isEmpty()) {
            if (player != null) {
                player.sendMessage(Text.literal("Structure not found! Please report this. structure: " + path), false);
            }
            player.giveItemStack(ModItems.LOOTBOX.getDefaultStack());
        }
        StructurePlacementData settings = new StructurePlacementData().setUpdateNeighbors(true);
        StructureTemplate structure = template.get();
        Vec3i size = structure.getSize();
        BlockPos centeredPos = pos.add(-size.getX() / 2 + (Integer) ((List<?>) path).get(1), (Integer) ((List<?>) path).get(2), -size.getZ() / 2 + (Integer) ((List<?>) path).get(3));
        structure.place(world, centeredPos, centeredPos, settings, world.getRandom(), Block.NOTIFY_ALL);
        return true;
    }
}