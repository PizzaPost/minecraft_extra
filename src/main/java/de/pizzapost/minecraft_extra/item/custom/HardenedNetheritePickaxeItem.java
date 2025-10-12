package de.pizzapost.minecraft_extra.item.custom;

import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class HardenedNetheritePickaxeItem extends Item {


    public HardenedNetheritePickaxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(settings.tool(material, BlockTags.PICKAXE_MINEABLE, attackDamage, attackSpeed, 0));
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
                item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.FORTUNE), 3);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }
}