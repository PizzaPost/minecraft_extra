package de.pizzapost.minecraft_extra.item.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import de.pizzapost.minecraft_extra.block.ModBlocks;
import de.pizzapost.minecraft_extra.block.custom.EffectFarmlandBlock;
import de.pizzapost.minecraft_extra.block.custom.EffectType;
import de.pizzapost.minecraft_extra.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class HardenedNetheriteHoeItem extends HoeItem {
    private static final List<Block> TILLABLE_BLOCKS = Arrays.asList(
            Blocks.GRASS_BLOCK,
            Blocks.DIRT_PATH,
            Blocks.DIRT
    );

    public HardenedNetheriteHoeItem(ToolMaterial material, Settings settings) {
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
                item.addEnchantment(enchantmentRegistry.entryOf(Enchantments.FORTUNE), 3);
                return TypedActionResult.success(item, true);
            }
        }
        return TypedActionResult.fail(item);
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
