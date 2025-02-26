package de.pizzapost.minecraft_extra.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.Vec3d;

public class FreezeEffect extends StatusEffect {
    public FreezeEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        Vec3d velocity = entity.getVelocity();
        entity.setVelocity(velocity.multiply(0.8, 1.0, 0.8));
        entity.setInPowderSnow(true);
        return super.applyUpdateEffect(entity, amplifier);
    }
}
