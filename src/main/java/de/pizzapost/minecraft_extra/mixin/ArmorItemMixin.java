package de.pizzapost.minecraft_extra.mixin;

import de.pizzapost.minecraft_extra.item.ModItems;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.enchantment.Enchantments;

@Mixin(ArmorItem.class)
public abstract class ArmorItemMixin {

    @Unique
    public void applyHardenedNetheriteChanges(PlayerEntity player) {
        ItemStack item = player.getMainHandStack();
        if (item.hasEnchantments()) return;
        player.addExperienceLevels(-50);
        var changes = ComponentChanges.builder()
                .add(DataComponentTypes.ITEM_NAME, item.getName().copy().formatted(Formatting.DARK_RED).formatted(Formatting.ITALIC))
                .add(DataComponentTypes.CUSTOM_NAME, item.getName().copy().formatted(Formatting.DARK_RED))
                .build();
        item.applyChanges(changes);
        var enchantmentRegistry = player.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT);
        item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.PROTECTION), 4);
        item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.FIRE_PROTECTION), 4);
        item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.THORNS), 3);
        if (item.getItem()==ModItems.HARDENED_NETHERITE_HELMET) {
            item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.RESPIRATION), 3);
            item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.AQUA_AFFINITY), 1);
        } else if (item.getItem()==ModItems.HARDENED_NETHERITE_LEGGINGS) {
            item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.SWIFT_SNEAK), 3);
        } else if (item.getItem()==ModItems.HARDENED_NETHERITE_BOOTS) {
            item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.FEATHER_FALLING), 4);
            item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.DEPTH_STRIDER), 3);
            item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.FROST_WALKER), 2);
            item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.SOUL_SPEED), 3);
        }
    }
    @Inject(method = "use", at = @At("HEAD"))
    public void use(World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (player.experienceLevel>=50) {
            ItemStack item = player.getMainHandStack();
            if (item.getItem() == ModItems.HARDENED_NETHERITE_HELMET ||
                    item.getItem() == ModItems.HARDENED_NETHERITE_CHESTPLATE ||
                    item.getItem() == ModItems.HARDENED_NETHERITE_LEGGINGS ||
                    item.getItem() == ModItems.HARDENED_NETHERITE_BOOTS) {
                applyHardenedNetheriteChanges(player);
            }
        }
    }
}