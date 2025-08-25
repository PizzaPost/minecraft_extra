package de.pizzapost.minecraft_extra.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.AxisRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.DirectionTransformation;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

import java.util.Map;

public class ConcretePowderStairsBlock extends ConcretePowderBlock implements Waterloggable {
    public static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;
    public static final EnumProperty<BlockHalf> HALF = Properties.BLOCK_HALF;
    public static final EnumProperty<StairShape> SHAPE = Properties.STAIR_SHAPE;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private static final VoxelShape OUTER_SHAPE = VoxelShapes.union(Block.createColumnShape(16.0, 0.0, 8.0), Block.createCuboidShape(0.0, 8.0, 0.0, 8.0, 16.0, 8.0));
    private static final VoxelShape STRAIGHT_SHAPE = VoxelShapes.union(OUTER_SHAPE, VoxelShapes.transform(OUTER_SHAPE, DirectionTransformation.fromRotations(AxisRotation.R0, AxisRotation.R90)));
    private static final VoxelShape INNER_SHAPE = VoxelShapes.union(STRAIGHT_SHAPE, VoxelShapes.transform(STRAIGHT_SHAPE, DirectionTransformation.fromRotations(AxisRotation.R0, AxisRotation.R90)));
    private static final Map<Direction, VoxelShape> OUTER_BOTTOM_SHAPES = VoxelShapes.createHorizontalFacingShapeMap(OUTER_SHAPE);
    private static final Map<Direction, VoxelShape> STRAIGHT_BOTTOM_SHAPES = VoxelShapes.createHorizontalFacingShapeMap(STRAIGHT_SHAPE);
    private static final Map<Direction, VoxelShape> INNER_BOTTOM_SHAPES = VoxelShapes.createHorizontalFacingShapeMap(INNER_SHAPE);
    private static final Map<Direction, VoxelShape> OUTER_TOP_SHAPES = VoxelShapes.createHorizontalFacingShapeMap(VoxelShapes.transform(OUTER_SHAPE, DirectionTransformation.INVERT_Y));
    private static final Map<Direction, VoxelShape> STRAIGHT_TOP_SHAPES = VoxelShapes.createHorizontalFacingShapeMap(VoxelShapes.transform(STRAIGHT_SHAPE, DirectionTransformation.INVERT_Y));
    private static final Map<Direction, VoxelShape> INNER_TOP_SHAPES = VoxelShapes.createHorizontalFacingShapeMap(VoxelShapes.transform(INNER_SHAPE, DirectionTransformation.INVERT_Y));

    private final Block baseBlock;
    protected final BlockState baseBlockState;

    public ConcretePowderStairsBlock(BlockState baseBlockState, Block concreteBlock, Settings settings) {
        super(concreteBlock, settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(HALF, BlockHalf.BOTTOM).with(SHAPE, StairShape.STRAIGHT).with(WATERLOGGED, false));
        this.baseBlock = baseBlockState.getBlock();
        this.baseBlockState = baseBlockState;
    }

    @Override
    protected boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        boolean bottom = state.get(HALF) == BlockHalf.BOTTOM;
        Direction direction = state.get(FACING);

        Map<Direction, VoxelShape> shapeMap = switch (state.get(SHAPE)) {
            case STRAIGHT -> bottom ? STRAIGHT_BOTTOM_SHAPES : STRAIGHT_TOP_SHAPES;
            case OUTER_LEFT, OUTER_RIGHT -> bottom ? OUTER_BOTTOM_SHAPES : OUTER_TOP_SHAPES;
            case INNER_LEFT, INNER_RIGHT -> bottom ? INNER_BOTTOM_SHAPES : INNER_TOP_SHAPES;
        };

        Direction shapeDirection = switch (state.get(SHAPE)) {
            case STRAIGHT, OUTER_LEFT, INNER_RIGHT -> direction;
            case INNER_LEFT -> direction.rotateYCounterclockwise();
            case OUTER_RIGHT -> direction.rotateYClockwise();
        };

        return shapeMap.get(shapeDirection);
    }

    @Override
    public float getBlastResistance() {
        return this.baseBlock.getBlastResistance();
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction side = ctx.getSide();
        BlockPos pos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(pos);

        BlockState state = this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing()).with(HALF, side != Direction.DOWN && (side == Direction.UP || !(ctx.getHitPos().y - pos.getY() > 0.5)) ? BlockHalf.BOTTOM : BlockHalf.TOP).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);

        return state.with(SHAPE, getStairShape(state, ctx.getWorld(), pos));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (state.get(WATERLOGGED)) {
            tickView.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return direction.getAxis().isHorizontal() ? state.with(SHAPE, getStairShape(state, world, pos)) : super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    private static StairShape getStairShape(BlockState state, BlockView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockState neighbor = world.getBlockState(pos.offset(direction));
        if (isStairs(neighbor) && state.get(HALF) == neighbor.get(HALF)) {
            Direction neighborFacing = neighbor.get(FACING);
            if (neighborFacing.getAxis() != state.get(FACING).getAxis() && isDifferentOrientation(state, world, pos, neighborFacing.getOpposite())) {
                return neighborFacing == direction.rotateYCounterclockwise() ? StairShape.OUTER_LEFT : StairShape.OUTER_RIGHT;
            }
        }

        BlockState neighbor2 = world.getBlockState(pos.offset(direction.getOpposite()));
        if (isStairs(neighbor2) && state.get(HALF) == neighbor2.get(HALF)) {
            Direction neighbor2Facing = neighbor2.get(FACING);
            if (neighbor2Facing.getAxis() != state.get(FACING).getAxis() && isDifferentOrientation(state, world, pos, neighbor2Facing)) {
                return neighbor2Facing == direction.rotateYCounterclockwise() ? StairShape.INNER_LEFT : StairShape.INNER_RIGHT;
            }
        }

        return StairShape.STRAIGHT;
    }

    private static boolean isDifferentOrientation(BlockState state, BlockView world, BlockPos pos, Direction dir) {
        BlockState neighbor = world.getBlockState(pos.offset(dir));
        return !isStairs(neighbor) || neighbor.get(FACING) != state.get(FACING) || neighbor.get(HALF) != state.get(HALF);
    }

    public static boolean isStairs(BlockState state) {
        return state.getBlock() instanceof ConcretePowderStairsBlock;
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        Direction direction = state.get(FACING);
        StairShape shape = state.get(SHAPE);

        switch (mirror) {
            case LEFT_RIGHT -> {
                if (direction.getAxis() == Direction.Axis.Z) {
                    return switch (shape) {
                        case OUTER_LEFT ->
                                state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.OUTER_RIGHT);
                        case OUTER_RIGHT ->
                                state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.OUTER_LEFT);
                        case INNER_LEFT ->
                                state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.INNER_RIGHT);
                        case INNER_RIGHT ->
                                state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.INNER_LEFT);
                        default -> state.rotate(BlockRotation.CLOCKWISE_180);
                    };
                }
            }
            case FRONT_BACK -> {
                if (direction.getAxis() == Direction.Axis.X) {
                    return switch (shape) {
                        case OUTER_LEFT ->
                                state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.OUTER_RIGHT);
                        case OUTER_RIGHT ->
                                state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.OUTER_LEFT);
                        case INNER_LEFT -> state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.INNER_LEFT);
                        case INNER_RIGHT ->
                                state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.INNER_RIGHT);
                        default -> state.rotate(BlockRotation.CLOCKWISE_180);
                    };
                }
            }
        }

        return super.mirror(state, mirror);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF, SHAPE, WATERLOGGED);
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public void scheduledTick(BlockState state, net.minecraft.server.world.ServerWorld world, BlockPos pos, Random random) {
        if (state.get(WATERLOGGED)) {
            BlockState hardened = baseBlock.getDefaultState().with(FACING, state.get(FACING)).with(HALF, state.get(HALF)).with(SHAPE, state.get(SHAPE)).with(WATERLOGGED, true);
            world.setBlockState(pos, hardened, Block.NOTIFY_ALL);
            return;
        }
        super.scheduledTick(state, world, pos, random);
    }
}
