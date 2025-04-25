package de.pizzapost.minecraft_extra.mixin;

import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DragonFireballEntity.class)
public abstract class DragonFireballEntityMixin {
    @Inject(method = "onCollision", at = @At("HEAD"), cancellable = true)
    private void preventDragonCollision(HitResult hitResult, CallbackInfo ci) {
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            EntityHitResult entityHit = (EntityHitResult) hitResult;
            if (entityHit.getEntity() instanceof EnderDragonEntity ||
                    (entityHit.getEntity() instanceof EnderDragonPart &&
                            ((EnderDragonPart) entityHit.getEntity()).owner instanceof EnderDragonEntity)) {
                ci.cancel();
            }
        }
    }
}