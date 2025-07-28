package de.pizzapost.minecraft_extra.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieHorseEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieHorseEntity.class)
public abstract class ZombieHorseEntityMixin extends AbstractHorseEntity {

    protected ZombieHorseEntityMixin(EntityType<? extends AbstractHorseEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    public void tameWithRottenFlesh(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (itemStack.isOf(Items.ROTTEN_FLESH)) {
            if (!this.getWorld().isClient) {
                if (!(this.isTame())) {
                    if (this.random.nextInt(7) == 0) {
                        this.setTame(true);
                        itemStack.decrement(1);
                        for (int i = 0; i < 7; i++)
                            if (player.getWorld() instanceof ServerWorld serverWorld)
                                serverWorld.spawnParticles(ParticleTypes.HEART, this.getX() + (random.nextDouble() * 2 - 1), this.getY() + 1 + (random.nextDouble() * 2 - 1), this.getZ() + (random.nextDouble() * 2 - 1), 1, 0.0, 0.0, 0.0, 1.0);
                        cir.setReturnValue(ActionResult.FAIL);
                    } else {
                        itemStack.decrement(1);
                        cir.setReturnValue(ActionResult.SUCCESS);
                    }
                }
            }
        }
    }
}