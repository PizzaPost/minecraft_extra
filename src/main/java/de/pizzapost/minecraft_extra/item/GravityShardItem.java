package de.pizzapost.minecraft_extra.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class GravityShardItem extends Item {

    public GravityShardItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (entity instanceof PlayerEntity player) {
            if (player.getMainHandStack().getItem() == ModItems.GRAVITY_SHARD || player.getOffHandStack().getItem() == ModItems.GRAVITY_SHARD) {
                if (!world.isClient) {
                    List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class, player.getBoundingBox().expand(10), itemEntity -> true);
                    for (ItemEntity itemEntity : items) {
                        itemEntity.refreshPositionAndAngles(player.getX(), player.getY(), player.getZ(), itemEntity.getYaw(), itemEntity.getPitch());
                    }
                }
            }
        }
    }
}
