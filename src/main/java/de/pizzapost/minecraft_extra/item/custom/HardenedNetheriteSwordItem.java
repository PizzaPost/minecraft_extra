package de.pizzapost.minecraft_extra.item.custom;

import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HardenedNetheriteSwordItem extends SwordItem {

    public HardenedNetheriteSwordItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack item = player.getMainHandStack();
        if (player.experienceLevel>=25) {
            if (!item.hasEnchantments()) {
                player.addExperienceLevels(-25);
                var changes = ComponentChanges.builder()
                        .add(DataComponentTypes.ITEM_NAME, item.getName().copy().formatted(Formatting.DARK_RED).formatted(Formatting.ITALIC))
                        .add(DataComponentTypes.CUSTOM_NAME, item.getName().copy().formatted(Formatting.DARK_RED))
                        .build();
                item.applyChanges(changes);
                var enchantmentRegistry = player.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT);
                item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.BANE_OF_ARTHROPODS), 5);
                item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.SMITE), 5);
                item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.KNOCKBACK), 2);
                item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.FIRE_ASPECT), 2);
                item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.SWEEPING_EDGE), 3);
                item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.UNBREAKING), 3);
                item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.LOOTING), 3);
                item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.SHARPNESS), 5);
                return TypedActionResult.success(item, true);
            }
        }
        return TypedActionResult.fail(item);
    }
}
