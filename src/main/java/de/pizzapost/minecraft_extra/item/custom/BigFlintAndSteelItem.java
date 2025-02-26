package de.pizzapost.minecraft_extra.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.CandleBlock;
import net.minecraft.block.CandleCakeBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class BigFlintAndSteelItem extends Item {
    public BigFlintAndSteelItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity playerEntity = context.getPlayer();
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        if (!CampfireBlock.canBeLit(blockState) && !CandleBlock.canBeLit(blockState) && !CandleCakeBlock.canBeLit(blockState)) {
            boolean anyBlockLit = false;
            Direction clickedFace = context.getSide();
            BlockPos targetPos = blockPos.offset(clickedFace);
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    BlockPos areaPos = targetPos.add(x, 0, y);
                    if (AbstractFireBlock.canPlaceAt(world, areaPos, Direction.UP)) {
                        world.playSound(playerEntity, areaPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
                        BlockState fireState = AbstractFireBlock.getState(world, areaPos);
                        world.setBlockState(areaPos, fireState, Block.NOTIFY_ALL_AND_REDRAW);
                        world.emitGameEvent(playerEntity, GameEvent.BLOCK_PLACE, areaPos);
                        anyBlockLit = true;
                    }
                }
            }
            if (anyBlockLit) {
                ItemStack itemStack = context.getStack();
                if (playerEntity instanceof ServerPlayerEntity) {
                    Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
                    itemStack.damage(1, playerEntity, LivingEntity.getSlotForHand(context.getHand()));
                }
                return ActionResult.success(world.isClient());
            } else {
                return ActionResult.FAIL;
            }
        } else {
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
            world.setBlockState(blockPos, blockState.with(Properties.LIT, Boolean.TRUE), Block.NOTIFY_ALL_AND_REDRAW);
            world.emitGameEvent(playerEntity, GameEvent.BLOCK_CHANGE, blockPos);
            if (playerEntity != null) {
                context.getStack().damage(1, playerEntity, LivingEntity.getSlotForHand(context.getHand()));
            }
            return ActionResult.success(world.isClient());
        }
    }
}
