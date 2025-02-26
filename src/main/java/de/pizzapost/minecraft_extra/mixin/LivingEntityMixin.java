package de.pizzapost.minecraft_extra.mixin;

import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @ModifyVariable(
            method = "tryUseTotem",
            at = @At(
                    value = "STORE",
                    ordinal = 0
            ),
            ordinal = 0
    )
    private ItemStack checkInventoryForTotem(ItemStack itemStack, DamageSource source) {
        LivingEntity entity = (LivingEntity) (Object) this;
        boolean hasTotemInHands = false;
        for (Hand hand : Hand.values()) {
            ItemStack handStack = entity.getStackInHand(hand);
            if (handStack.isOf(Items.TOTEM_OF_UNDYING)) {
                hasTotemInHands = true;
                break;
            }
        }
        if (!hasTotemInHands && itemStack == null && entity instanceof PlayerEntity player) {
            PlayerInventory inventory = player.getInventory();
            for (int i = 0; i < inventory.size(); i++) {
                ItemStack stack = inventory.getStack(i);
                if (stack.isOf(Items.TOTEM_OF_UNDYING)) {
                    ItemStack consumed = stack.copy();
                    stack.decrement(1);
                    return consumed;
                }
            }
        }
        return itemStack;
    }
}