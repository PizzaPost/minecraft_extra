package de.pizzapost.minecraft_extra.item.custom;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public class SuperFertilizerItem extends Item {

    public SuperFertilizerItem(Settings settings) {
        super(settings);
    }

    private void giveAdvancement(PlayerEntity player) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            Identifier advancementId = Identifier.of(MinecraftExtra.MOD_ID, "super_fertilizer");
            AdvancementEntry advancement = serverPlayer.getEntityWorld().getServer().getAdvancementLoader().get(advancementId);
            if (advancement != null) {
                serverPlayer.getAdvancementTracker().grantCriterion(advancement, "imp");
            }
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        Block block = blockState.getBlock();
        if (blockState.getBlock() instanceof Fertilizable fertilizable) {
            if (fertilizable.isFertilizable(world, blockPos, blockState)) {
                if (!world.isClient()) {
                    if (block instanceof CropBlock) {
                        if (!(block == Blocks.BEETROOTS)) {
                            world.setBlockState(blockPos, block.getDefaultState().with(CropBlock.AGE, 7));
                            giveAdvancement(context.getPlayer());
                        }
                    } else if (block instanceof StemBlock) {
                        world.setBlockState(blockPos, block.getDefaultState().with(StemBlock.AGE, 7));
                        giveAdvancement(context.getPlayer());
                    } else if (block instanceof SweetBerryBushBlock) {
                        world.setBlockState(blockPos, block.getDefaultState().with(SweetBerryBushBlock.AGE, 3));
                        giveAdvancement(context.getPlayer());
                    } else if (block == Blocks.CAVE_VINES || block == Blocks.CAVE_VINES_PLANT) {
                        world.setBlockState(blockPos, block.getDefaultState().with(CaveVines.BERRIES, true));
                        giveAdvancement(context.getPlayer());
                    } else if (block == Blocks.BEETROOTS) {
                        world.setBlockState(blockPos, block.getDefaultState().with(BeetrootsBlock.AGE, 3));
                        giveAdvancement(context.getPlayer());
                    }
                } else {
                    return ActionResult.FAIL;
                }
            }
            BlockState block2 = world.getBlockState(blockPos);
            if (!(blockState == block2)) {
                context.getStack().decrementUnlessCreative(1, context.getPlayer());
                world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, blockPos, 0);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
}