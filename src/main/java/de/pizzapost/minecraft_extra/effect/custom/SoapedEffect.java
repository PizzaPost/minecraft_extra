package de.pizzapost.minecraft_extra.effect.custom;

import de.pizzapost.minecraft_extra.entity.ModEntities;
import de.pizzapost.minecraft_extra.entity.custom.SoapBubbleEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class SoapedEffect extends StatusEffect {
    public SoapedEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld serverWorld, LivingEntity entity, int amplifier) {
        entity.setVelocity(0, 0.1, 0);
        World world = entity.getWorld();
        if (!world.isClient) {
            Box area = new Box(
                    entity.getX() - 3, entity.getY() - 3, entity.getZ() - 3,
                    entity.getX() + 3, entity.getY() + 3, entity.getZ() + 3
            );
            List<SoapBubbleEntity> bubbles = world.getEntitiesByType(
                    ModEntities.SOAP_BUBBLE,
                    area,
                    e -> true
            );
            if (bubbles.isEmpty()) {
                SoapBubbleEntity bubble = new SoapBubbleEntity(ModEntities.SOAP_BUBBLE, world);
                bubble.setPosition(entity.getPos());
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
