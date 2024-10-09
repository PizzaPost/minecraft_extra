package de.pizzapost.minecraft_extra.event;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;

public class SleepRegeneration implements ModInitializer {

    @Override
    public void onInitialize() {
        EntitySleepEvents.START_SLEEPING.register(this::onPlayerSleep);
    }

    private void onPlayerSleep(LivingEntity player, BlockPos pos) {
        player.setHealth(player.getMaxHealth());

    }
}
