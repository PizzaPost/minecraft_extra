package de.pizzapost.minecraft_extra.event;

import de.pizzapost.minecraft_extra.item.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;

public class LightningStrikeHandler {
    public static void registerLightningStrikeEvent() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof LightningEntity lightning && !world.isClient()) {
                handleLightningStrike((ServerWorld) world, lightning);
            }
        });
    }

    private static void handleLightningStrike(ServerWorld world, LightningEntity lightning) {
        for (PlayerEntity player : world.getPlayers()) {
            if (player.distanceTo(lightning) <= 10 && player.getMainHandStack().getItem() == Items.STICK) {
                int count = player.getMainHandStack().getCount();
                ItemStack lightningStick = new ItemStack(ModItems.LIGHTNING_STICK);
                lightningStick.setCount(count);
                player.setStackInHand(Hand.MAIN_HAND, lightningStick);
            }
            if (player.distanceTo(lightning) <= 10 && player.getOffHandStack().getItem() == Items.STICK) {
                int count = player.getOffHandStack().getCount();
                ItemStack lightningStick = new ItemStack(ModItems.LIGHTNING_STICK);
                lightningStick.setCount(count);
                player.setStackInHand(Hand.OFF_HAND, lightningStick);
            }
        }
    }
}
