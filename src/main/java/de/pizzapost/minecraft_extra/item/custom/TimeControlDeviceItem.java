package de.pizzapost.minecraft_extra.item.custom;

import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.ServerTickManager;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TimeControlDeviceItem extends Item {
    public static boolean frozen = false;
    public TimeControlDeviceItem(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!world.isClient) {
            frozen = !frozen;
            ServerTickManager serverTickManager = player.getServer().getTickManager();
            serverTickManager.setFrozen(frozen);
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }
}
