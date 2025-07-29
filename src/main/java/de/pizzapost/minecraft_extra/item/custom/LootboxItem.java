package de.pizzapost.minecraft_extra.item.custom;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;

public class LootboxItem extends Item {
    public LootboxItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if (player instanceof ServerPlayerEntity serverPlayer) {
            RegistryKey lootTableId = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(MinecraftExtra.MOD_ID, "items/lootbox"));
            LootTable lootTable = serverPlayer.getServer().getReloadableRegistries().getLootTable(lootTableId);
            LootWorldContext parameters = new LootWorldContext.Builder((ServerWorld) world).add(LootContextParameters.ORIGIN, serverPlayer.getPos()).add(LootContextParameters.THIS_ENTITY, serverPlayer).build(LootContextTypes.CHEST);
            List<ItemStack> items = lootTable.generateLoot(parameters);
            for (ItemStack item : items) {
                player.setStackInHand(hand, item);
            }
        }
        return ActionResult.SUCCESS;
    }
}