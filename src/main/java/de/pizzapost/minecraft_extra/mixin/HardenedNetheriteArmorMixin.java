package de.pizzapost.minecraft_extra.mixin;

import de.pizzapost.minecraft_extra.item.ModItems;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;

@Mixin(ArmorItem.class)
public abstract class HardenedNetheriteArmorMixin {

    @Unique
    public void applyHardenedNetheriteChanges(PlayerEntity user) {
        ItemStack item = user.getMainHandStack();
        user.addExperienceLevels(-50);
        var changes = ComponentChanges.builder()
                .add(DataComponentTypes.ITEM_NAME, item.getName().copy().formatted(Formatting.DARK_RED))
                .add(DataComponentTypes.CUSTOM_NAME, item.getName().copy().formatted(Formatting.DARK_RED))
                .add(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
                .build();
        item.applyChanges(changes);
        //tooltip löschen
    }
    @Inject(method = "use", at = @At("HEAD"))
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (user.experienceLevel>=50) {
            if (user.getMainHandStack().getItem()==ModItems.HARDENED_NETHERITE_HELMET) {
                applyHardenedNetheriteChanges(user);
            } else if (user.getMainHandStack().getItem()==ModItems.HARDENED_NETHERITE_CHESTPLATE) {
                applyHardenedNetheriteChanges(user);
            } else if (user.getMainHandStack().getItem()==ModItems.HARDENED_NETHERITE_LEGGINGS) {
                applyHardenedNetheriteChanges(user);
            } else if (user.getMainHandStack().getItem()==ModItems.HARDENED_NETHERITE_BOOTS) {
                applyHardenedNetheriteChanges(user);
            }
        } else {
            java.lang.System.out.println("False: Different item detected");
        }
    }
}