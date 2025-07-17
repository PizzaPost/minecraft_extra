package de.pizzapost.minecraft_extra.mixin;

import de.pizzapost.minecraft_extra.item.ModItems;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ArmorItemMixin {

    @Unique
    public void applyHardenedNetheriteChanges(PlayerEntity player, ItemStack item) {
        if (item.hasEnchantments()) return;
        player.addExperienceLevels(-50);
        var changes = ComponentChanges.builder().add(DataComponentTypes.ITEM_NAME, item.getName().copy().formatted(Formatting.DARK_RED).formatted(Formatting.ITALIC)).add(DataComponentTypes.CUSTOM_NAME, item.getName().copy().formatted(Formatting.DARK_RED)).build();
        item.applyChanges(changes);
        RegistryWrapper<Enchantment> enchantmentRegistry = player.getWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
        item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.PROTECTION), 4);
        item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.FIRE_PROTECTION), 4);
        item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.THORNS), 3);
        if (item.getItem() == ModItems.HARDENED_NETHERITE_HELMET) {
            item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.RESPIRATION), 3);
            item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.AQUA_AFFINITY), 1);
        } else if (item.getItem() == ModItems.HARDENED_NETHERITE_LEGGINGS) {
            item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.SWIFT_SNEAK), 3);
        } else if (item.getItem() == ModItems.HARDENED_NETHERITE_BOOTS) {
            item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.FEATHER_FALLING), 4);
            item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.DEPTH_STRIDER), 3);
            item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.FROST_WALKER), 2);
            item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.SOUL_SPEED), 3);
        }
    }

    @Inject(method = "use", at = @At("HEAD"))
    private void onUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (user.experienceLevel < 50) return;

        ItemStack stack = user.getStackInHand(hand);
        if (stack.isOf(ModItems.HARDENED_NETHERITE_HELMET) || stack.isOf(ModItems.HARDENED_NETHERITE_CHESTPLATE) || stack.isOf(ModItems.HARDENED_NETHERITE_LEGGINGS) || stack.isOf(ModItems.HARDENED_NETHERITE_BOOTS)) {
            applyHardenedNetheriteChanges(user, stack);
        }
    }
}