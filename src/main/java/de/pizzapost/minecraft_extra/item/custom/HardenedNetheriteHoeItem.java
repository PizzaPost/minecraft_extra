package de.pizzapost.minecraft_extra.item.custom;

import de.pizzapost.minecraft_extra.block.ModBlocks;
import de.pizzapost.minecraft_extra.block.custom.EffectFarmlandBlock;
import de.pizzapost.minecraft_extra.block.custom.EffectType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HardenedNetheriteHoeItem extends HoeItem {
    private static final List<Block> TILLABLE_BLOCKS = Arrays.asList(Blocks.GRASS_BLOCK, Blocks.DIRT_PATH, Blocks.DIRT);

    public HardenedNetheriteHoeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
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
                RegistryWrapper<Enchantment> enchantmentRegistry = player.getWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
                item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.EFFICIENCY), 5);
                item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.UNBREAKING), 3);
                item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.FORTUNE), 3);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        if (context.getStack().hasEnchantments()) {
            if (state.getBlock() == Blocks.ROOTED_DIRT) {
                if (!world.isClient) {
                    BlockState newState = Blocks.DIRT.getDefaultState();
                    world.setBlockState(pos, newState, 11);
                    Block.dropStack(world, pos.up(), new ItemStack(Items.HANGING_ROOTS));
                }
                return ActionResult.SUCCESS;
            }
            if (state.getBlock() == Blocks.COARSE_DIRT || state.getBlock() == Blocks.ROOTED_DIRT) {
                if (!world.isClient) {
                    BlockState newState = Blocks.DIRT.getDefaultState();
                    world.setBlockState(pos, newState, 11);
                }
                return ActionResult.SUCCESS;
            }
            if (TILLABLE_BLOCKS.contains(state.getBlock())) {
                if (!world.isClient) {
                    BlockState newState = ModBlocks.EFFECT_FARMLAND.getDefaultState();
                    world.setBlockState(pos, newState, 11);
                    Random random = new Random();
                    EffectType[] effects = EffectType.values();
                    EffectType randomEffect = effects[random.nextInt(effects.length)];
                    world.setBlockState(pos, newState.with(EffectFarmlandBlock.EFFECT, randomEffect), 11);
                }
                return ActionResult.SUCCESS;
            }
            return super.useOnBlock(context);
        } else {
            if (TILLABLE_BLOCKS.contains(state.getBlock())) {
                if (!world.isClient) {
                    BlockState newState = Blocks.FARMLAND.getDefaultState();
                    world.setBlockState(pos, newState);
                }
                return ActionResult.SUCCESS;
            }
            if (state.getBlock() == Blocks.ROOTED_DIRT) {
                if (!world.isClient) {
                    BlockState newState = Blocks.DIRT.getDefaultState();
                    world.setBlockState(pos, newState, 11);
                    Block.dropStack(world, pos.up(), new ItemStack(Items.HANGING_ROOTS));
                }
                return ActionResult.SUCCESS;
            }
            if (state.getBlock() == Blocks.COARSE_DIRT || state.getBlock() == Blocks.ROOTED_DIRT) {
                if (!world.isClient) {
                    BlockState newState = Blocks.DIRT.getDefaultState();
                    world.setBlockState(pos, newState);
                }
                return ActionResult.SUCCESS;
            }
            return super.useOnBlock(context);
        }
    }
}
