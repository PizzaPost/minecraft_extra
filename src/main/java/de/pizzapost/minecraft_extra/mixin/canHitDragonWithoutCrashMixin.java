package de.pizzapost.minecraft_extra.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class canHitDragonWithoutCrashMixin {
    @Inject(
            method = "attack",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onAttackEntity(Entity target, CallbackInfo ci) {
        // Skip Architectury's client damage handling for Ender Dragon parts
        if (target instanceof EnderDragonPart) {
            ci.cancel(); // Prevent the default attack handling
            // Implement custom attack logic that doesn't trigger Architectury's bug
            handleDragonPartAttack((PlayerEntity) (Object) this, target);
        }
    }

    private void handleDragonPartAttack(PlayerEntity player, Entity target) {
        // Directly call the server-side damage handling without client processing
        if (!player.getWorld().isClient) {
            target.serverDamage(player.getDamageSources().playerAttack(player), 1.0f);
        }

        // Reset attack cooldown to prevent lockout
        player.resetLastAttackedTicks();
    }
}