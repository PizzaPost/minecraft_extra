package de.pizzapost.minecraft_extra.effect.custom;

import de.pizzapost.minecraft_extra.keybinds.ModKeys;
import net.minecraft.client.option.GameOptions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.control.JumpControl;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

// Climbing Effect by SameDifferent: https://github.com/samedifferent/TrickOrTreat/blob/master/LICENSE
// MIT License!
public class SpiderEffect extends StatusEffect {
    public SpiderEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        BlockPos blockPos1 = entity.getBlockPos().down();
        BlockPos blockPos2 = entity.getBlockPos().down(2);
        if (entity.horizontalCollision && entity.getPitch() >= 50 && !entity.getWorld().getBlockState(blockPos1).isSolidBlock(entity.getWorld(), blockPos1) && !entity.getWorld().getBlockState(blockPos2).isSolidBlock(entity.getWorld(), blockPos2)) {
            Vec3d initialVec = entity.getVelocity();
            Vec3d climbVec = new Vec3d(initialVec.x, -0.1D, initialVec.z);
            entity.setVelocity(climbVec.multiply(0.96D));
            return true;
        } else if (entity.horizontalCollision && entity.getPitch() < 50 && !entity.getWorld().getBlockState(blockPos1).isSolidBlock(entity.getWorld(), blockPos1)) {
            Vec3d initialVec = entity.getVelocity();
            Vec3d climbVec = new Vec3d(initialVec.x, 0.1D, initialVec.z);
            entity.setVelocity(climbVec.multiply(0.96D));
            return true;
        }
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
