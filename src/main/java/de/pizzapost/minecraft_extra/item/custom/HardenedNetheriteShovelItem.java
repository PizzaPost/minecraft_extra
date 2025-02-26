package de.pizzapost.minecraft_extra.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Stack;

public class HardenedNetheriteShovelItem extends ShovelItem {

    public HardenedNetheriteShovelItem(ToolMaterial material, Settings settings) {
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
                item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.EFFICIENCY), 5);
                item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.UNBREAKING), 3);
                return TypedActionResult.success(item, true);
            }
        }
        return TypedActionResult.fail(item);
    }
}
