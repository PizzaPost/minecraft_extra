package de.pizzapost.minecraft_extra.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class ConcretePowderSlabBlock extends ConcretePowderBlock implements Waterloggable {
    public static final EnumProperty<SlabType> TYPE = Properties.SLAB_TYPE;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private static final VoxelShape BOTTOM_SHAPE = Block.createColumnShape(16.0, 0.0, 8.0);
    private static final VoxelShape TOP_SHAPE = Block.createColumnShape(16.0, 8.0, 16.0);
    private final Block baseBlock;

    public ConcretePowderSlabBlock(Block concreteBlock, Settings settings) {
        super(concreteBlock, settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, false));
        this.baseBlock = concreteBlock.getDefaultState().getBlock();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        BlockState state = ctx.getWorld().getBlockState(pos);

        if (state.isOf(this)) {
            return state.with(TYPE, SlabType.DOUBLE).with(WATERLOGGED, false);
        }

        FluidState fluidState = ctx.getWorld().getFluidState(pos);
        BlockState base = this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);

        Direction side = ctx.getSide();
        double hitY = ctx.getHitPos().y - (double) pos.getY();

        if (side == Direction.DOWN || (side != Direction.UP && hitY > 0.5D)) {
            return base.with(TYPE, SlabType.TOP);
        } else {
            return base.with(TYPE, SlabType.BOTTOM);
        }
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext ctx) {
        ItemStack itemStack = ctx.getStack();
        SlabType type = state.get(TYPE);

        if (type != SlabType.DOUBLE && itemStack.isOf(this.asItem())) {
            if (ctx.canReplaceExisting()) {
                Direction direction = ctx.getSide();
                if (type == SlabType.BOTTOM) {
                    return direction == Direction.UP || (ctx.getHitPos().y - (double) ctx.getBlockPos().getY()) > 0.5D;
                } else {
                    return direction == Direction.DOWN || (ctx.getHitPos().y - (double) ctx.getBlockPos().getY()) <= 0.5D;
                }
            }
        }
        return false;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(TYPE)) {
            case TOP -> TOP_SHAPE;
            case BOTTOM -> BOTTOM_SHAPE;
            case DOUBLE -> VoxelShapes.fullCube();
        };
    }

    @Override
    public void scheduledTick(BlockState state, net.minecraft.server.world.ServerWorld world, BlockPos pos, Random random) {
        if (state.get(WATERLOGGED)) {
            BlockState hardened = baseBlock.getDefaultState().with(TYPE, state.get(TYPE)).with(WATERLOGGED, true);
            world.setBlockState(pos, hardened, Block.NOTIFY_ALL);
            return;
        }
        super.scheduledTick(state, world, pos, random);
    }
}