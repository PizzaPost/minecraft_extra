package de.pizzapost.minecraft_extra.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class SpiderEffect extends StatusEffect {
    public SpiderEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld serverWorld, LivingEntity entity, int amplifier) {
        BlockPos blockPos1 = entity.getBlockPos().down();
        BlockPos blockPos2 = entity.getBlockPos().down(2);
        if (entity.horizontalCollision && entity.getPitch() >= 50 && !entity.getEntityWorld().getBlockState(blockPos1).isSolidBlock(entity.getEntityWorld(), blockPos1) && !entity.getEntityWorld().getBlockState(blockPos2).isSolidBlock(entity.getEntityWorld(), blockPos2)) {
            Vec3d initialVec = entity.getVelocity();
            Vec3d climbVec = new Vec3d(initialVec.x, -0.1D, initialVec.z);
            entity.setVelocity(climbVec);
            entity.velocityModified = true;
            entity.velocityDirty = true;
            return true;
        } else if (entity.horizontalCollision && entity.getPitch() < 50 && !entity.getEntityWorld().getBlockState(blockPos1).isSolidBlock(entity.getEntityWorld(), blockPos1)) {
            Vec3d initialVec = entity.getVelocity();
            Vec3d climbVec = new Vec3d(initialVec.x, 0.15D, initialVec.z);
            entity.setVelocity(climbVec);
            entity.velocityModified = true;
            entity.velocityDirty = true;
            return true;
        }
        return super.applyUpdateEffect(serverWorld, entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
