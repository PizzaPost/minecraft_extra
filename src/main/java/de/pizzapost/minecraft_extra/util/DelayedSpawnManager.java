package de.pizzapost.minecraft_extra.util;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.sound.SoundEvents;
import net.minecraft.sound.SoundCategory;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class DelayedSpawnManager implements ServerTickEvents.EndTick {
    private static final DelayedSpawnManager INSTANCE = new DelayedSpawnManager();
    private final CopyOnWriteArrayList<DelayedSpawn> pendingSpawns = new CopyOnWriteArrayList<>();
    public static void registerDelayedSpawnManager() {
        ServerTickEvents.END_SERVER_TICK.register(INSTANCE);
    }
    public static void scheduleSpawn(ServerWorld world, UUID playerUuid, BlockPos pos, int delay) {
        INSTANCE.pendingSpawns.add(new DelayedSpawn(world, playerUuid, pos, delay));
    }
    @Override
    public void onEndTick(MinecraftServer server) {
        for (DelayedSpawn spawn : INSTANCE.pendingSpawns) {
            if (--spawn.ticksRemaining <= 0) {
                spawn.execute();
                INSTANCE.pendingSpawns.remove(spawn);
            }
        }
    }
    private static class DelayedSpawn {
        private final ServerWorld world;
        private final UUID playerUuid;
        private final BlockPos pos;
        private int ticksRemaining;
        DelayedSpawn(ServerWorld world, UUID playerUuid, BlockPos pos, int delay) {
            this.world = world;
            this.playerUuid = playerUuid;
            this.pos = pos;
            this.ticksRemaining = delay;
        }
        void execute() {
            PlayerEntity player = world.getPlayerByUuid(playerUuid);
            if (player == null || !player.isAlive()) return;
            EndermanEntity enderman = new EndermanEntity(EntityType.ENDERMAN, world);
            enderman.refreshPositionAndAngles(
                    pos.getX() + 0.5,
                    pos.getY(),
                    pos.getZ() + 0.5,
                    world.random.nextFloat() * 360.0F,
                    0.0F
            );
            enderman.setTarget(player);
            enderman.setProvoked();
            world.playSound(null, pos, SoundEvents.ENTITY_ENDERMAN_SCREAM,
                    SoundCategory.HOSTILE, 1.0F, 1.0F);
            Objects.requireNonNull(enderman.getAttributeInstance(EntityAttributes.GENERIC_SCALE))
                    .setBaseValue(1.75);
            Objects.requireNonNull(enderman.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE))
                    .setBaseValue(60);
            Objects.requireNonNull(enderman.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH))
                    .setBaseValue(150);
            enderman.setHealth(150);
            enderman.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 999999, 1));
            world.spawnEntity(enderman);
        }
    }
}