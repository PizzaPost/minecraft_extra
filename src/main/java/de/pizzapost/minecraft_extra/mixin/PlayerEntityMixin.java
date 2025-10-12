package de.pizzapost.minecraft_extra.mixin;

import de.pizzapost.minecraft_extra.entity.custom.SoapBubbleEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        World world = player.getEntityWorld();
        BlockPos pos = player.getBlockPos();
        BlockPos belowPos = pos.down();
        if (world.getBlockState(belowPos).isOf(Blocks.GRAVEL) && world.getBlockState(pos).isAir()) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 2, 2));
        }
    }

    @Redirect(method = "tickRiding", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;shouldDismount()Z"))
    private boolean soapbubble_preventDismountOnSneak(PlayerEntity player) {
        if (player.getVehicle() instanceof SoapBubbleEntity) {
            return false;
        }
        return player.isSneaking();
    }
}
