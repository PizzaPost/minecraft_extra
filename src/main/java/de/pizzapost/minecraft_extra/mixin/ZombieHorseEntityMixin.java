package de.pizzapost.minecraft_extra.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieHorseEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
                    if (this.random.nextInt(3) == 0) {
                        this.setTame(true);
                        itemStack.decrement(1);
                    } else {
                        itemStack.decrement(1);
                    }
                }
            }
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}
