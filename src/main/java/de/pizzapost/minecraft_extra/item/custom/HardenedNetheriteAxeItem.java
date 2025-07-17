package de.pizzapost.minecraft_extra.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Stack;

public class HardenedNetheriteAxeItem extends AxeItem {


    public HardenedNetheriteAxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
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
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }

    public static boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, PlayerEntity miner, Hand hand) {
        if (!world.isClient && isLog(state)) {
            breakTree((ServerWorld) world, pos, miner, stack, hand);
        }
        return true;
    }

    private static boolean isLog(BlockState state) {
        Block block = state.getBlock();
        return block == Blocks.OAK_LOG || block == Blocks.BIRCH_LOG || block == Blocks.SPRUCE_LOG || block == Blocks.JUNGLE_LOG || block == Blocks.ACACIA_LOG || block == Blocks.DARK_OAK_LOG || block == Blocks.MANGROVE_LOG || block == Blocks.CHERRY_LOG || block == Blocks.CRIMSON_STEM || block == Blocks.WARPED_STEM;
    }

    private static void breakTree(ServerWorld world, BlockPos start, PlayerEntity miner, ItemStack tool, Hand hand) {
        Stack<BlockPos> stack = new Stack<>();
        HashSet<BlockPos> visited = new HashSet<>();
        stack.push(start);
        BlockPos[] neighbors = {new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 1, 0), new BlockPos(0, -1, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1), // Direkt angrenzend
                new BlockPos(1, 1, 0), new BlockPos(1, -1, 0), new BlockPos(-1, 1, 0), new BlockPos(-1, -1, 0), new BlockPos(1, 0, 1), new BlockPos(1, 0, -1), new BlockPos(-1, 0, 1), new BlockPos(-1, 0, -1), new BlockPos(0, 1, 1), new BlockPos(0, 1, -1), new BlockPos(0, -1, 1), new BlockPos(0, -1, -1), new BlockPos(1, 1, 1), new BlockPos(1, 1, -1), new BlockPos(1, -1, 1), new BlockPos(1, -1, -1), new BlockPos(-1, 1, 1), new BlockPos(-1, 1, -1), new BlockPos(-1, -1, 1), new BlockPos(-1, -1, -1)};
        while (!stack.isEmpty()) {
            BlockPos current = stack.pop();
            if (visited.contains(current)) continue;
            visited.add(current);
            BlockState state = world.getBlockState(current);
            if (isLog(state)) {
                world.breakBlock(current, true, miner);
                tool.setDamage(tool.getDamage() + 1);
                if (tool.getDamage() >= tool.getMaxDamage()) {
                    miner.setStackInHand(hand, ItemStack.EMPTY);
                }
                for (BlockPos offset : neighbors) {
                    BlockPos neighborPos = current.add(offset);
                    if (!visited.contains(neighborPos)) {
                        stack.push(neighborPos);
                    }
                }
            }
        }
    }
}