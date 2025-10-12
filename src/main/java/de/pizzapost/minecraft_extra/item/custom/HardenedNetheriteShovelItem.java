package de.pizzapost.minecraft_extra.item.custom;

import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class HardenedNetheriteShovelItem extends ShovelItem {


    public HardenedNetheriteShovelItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack item = player.getMainHandStack();
        if (player.experienceLevel >= 25) {
            if (!item.hasEnchantments()) {
                player.addExperienceLevels(-25);
                var changes = ComponentChanges.builder().add(DataComponentTypes.ITEM_NAME, item.getName().copy().formatted(Formatting.DARK_RED).formatted(Formatting.ITALIC)).add(DataComponentTypes.CUSTOM_NAME, item.getName().copy().formatted(Formatting.DARK_RED)).build();
                item.applyChanges(changes);
                RegistryWrapper<Enchantment> enchantmentRegistry = player.getEntityWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
                item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.EFFICIENCY), 5);
                item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.UNBREAKING), 3);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }
}