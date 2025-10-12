package de.pizzapost.minecraft_extra.effect.custom;

import de.pizzapost.minecraft_extra.entity.ModEntities;
import de.pizzapost.minecraft_extra.entity.custom.SoapBubbleEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class SoapedEffect extends StatusEffect {
    private static Double e = 2.71828;

    public SoapedEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld serverWorld, LivingEntity entity, int amplifier) {
        World world = entity.getEntityWorld();
        entity.getAttributeInstance(EntityAttributes.SCALE).setBaseValue(1.0);
        entity.getAttributeInstance(EntityAttributes.BLOCK_BREAK_SPEED).setBaseValue(1.0);
        entity.getAttributeInstance(EntityAttributes.BLOCK_INTERACTION_RANGE).setBaseValue(5.0);
        entity.getAttributeInstance(EntityAttributes.ARMOR).setBaseValue(0.0);
        entity.getAttributeInstance(EntityAttributes.ARMOR_TOUGHNESS).setBaseValue(0.0);
        entity.getAttributeInstance(EntityAttributes.ATTACK_KNOCKBACK).setBaseValue(0.0);
        entity.getAttributeInstance(EntityAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0);
        entity.getAttributeInstance(EntityAttributes.OXYGEN_BONUS).setBaseValue(0.0);
        entity.getAttributeInstance(EntityAttributes.WATER_MOVEMENT_EFFICIENCY).setBaseValue(0.0);
        entity.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(1.0);
        entity.getAttributeInstance(EntityAttributes.ATTACK_SPEED).setBaseValue(4.000158321693853);
        entity.getAttributeInstance(EntityAttributes.FALL_DAMAGE_MULTIPLIER).setBaseValue(1.0008781457744116);
        entity.getAttributeInstance(EntityAttributes.JUMP_STRENGTH).setBaseValue(0.418649677011299);
        entity.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.100036978618533);
        entity.getAttributeInstance(EntityAttributes.SAFE_FALL_DISTANCE).setBaseValue(3.0);
        entity.getAttributeInstance(EntityAttributes.STEP_HEIGHT).setBaseValue(0.5999541124066);
        entity.getAttributeInstance(EntityAttributes.ENTITY_INTERACTION_RANGE).setBaseValue(3.0);
        if (!world.isClient()) {
            Box area = new Box(entity.getX() - 3, entity.getY() - 3, entity.getZ() - 3, entity.getX() + 3, entity.getY() + 3, entity.getZ() + 3);
            List<SoapBubbleEntity> bubbles = world.getEntitiesByType(ModEntities.SOAP_BUBBLE, area, e -> true);
            if (bubbles.isEmpty()) {
                SoapBubbleEntity bubble = new SoapBubbleEntity(ModEntities.SOAP_BUBBLE, world);
                bubble.setPosition(entity.getX(), entity.getY(), entity.getZ());
                entity.startRiding(bubble);
                world.spawnEntity(bubble);
            }
        }
        entity.velocityModified = true;
        entity.velocityDirty = true;
        return super.applyUpdateEffect(serverWorld, entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
