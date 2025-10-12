package de.pizzapost.minecraft_extra.effect.custom;

import de.pizzapost.minecraft_extra.effect.ModEffects;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public class FreezeEffect extends StatusEffect {
    public FreezeEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld serverWorld, LivingEntity entity, int amplifier) {
        Random random = new Random();
        Vec3d velocity = entity.getVelocity();
        entity.setVelocity(velocity.multiply(0.8, 1.0, 0.8));
        int effectDuration = (entity.hasStatusEffect(ModEffects.FREEZE) ? entity.getStatusEffect(ModEffects.FREEZE).getDuration() : 0);
        Block blockInPlayer = serverWorld.getBlockState(entity.getBlockPos()).getBlock();
        if (blockInPlayer == Blocks.POWDER_SNOW) {
            effectDuration = 300;
        }
        entity.setFrozenTicks(effectDuration);
        serverWorld.spawnParticles(ParticleTypes.SNOWFLAKE, entity.getX() + (random.nextDouble() - 0.5), entity.getY() + 1 + (random.nextDouble() - 0.5), entity.getZ() + (random.nextDouble() - 0.5), 1, 0.0, 0.0, 0.0, 0.1);
        return super.applyUpdateEffect(serverWorld, entity, amplifier);
    }
}
