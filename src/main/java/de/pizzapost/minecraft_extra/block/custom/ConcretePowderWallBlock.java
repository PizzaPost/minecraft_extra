package de.pizzapost.minecraft_extra.block.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.block.enums.WallShape;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

public class ConcretePowderWallBlock extends ConcretePowderBlock implements net.minecraft.block.Waterloggable {
    public static final BooleanProperty UP = Properties.UP;
    public static final EnumProperty<WallShape> EAST_WALL_SHAPE = Properties.EAST_WALL_SHAPE;
    public static final EnumProperty<WallShape> NORTH_WALL_SHAPE = Properties.NORTH_WALL_SHAPE;
    public static final EnumProperty<WallShape> SOUTH_WALL_SHAPE = Properties.SOUTH_WALL_SHAPE;
    public static final EnumProperty<WallShape> WEST_WALL_SHAPE = Properties.WEST_WALL_SHAPE;
    public static final Map<Direction, EnumProperty<WallShape>> WALL_SHAPE_PROPERTIES_BY_DIRECTION = ImmutableMap.copyOf(Maps.newEnumMap(Map.of(Direction.NORTH, NORTH_WALL_SHAPE, Direction.EAST, EAST_WALL_SHAPE, Direction.SOUTH, SOUTH_WALL_SHAPE, Direction.WEST, WEST_WALL_SHAPE)));
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private final Function<BlockState, VoxelShape> outlineShapeFunction;
    private final Function<BlockState, VoxelShape> collisionShapeFunction;
    private static final VoxelShape POST_SHAPE_FOR_TALL_TEST = Block.createColumnShape(2.0, 0.0, 16.0);
    private static final Map<Direction, VoxelShape> WALL_SHAPES_FOR_TALL_TEST_BY_DIRECTION = VoxelShapes.createHorizontalFacingShapeMap(Block.createCuboidZShape(2.0, 16.0, 0.0, 9.0));
    private final Block baseBlock;

    public ConcretePowderWallBlock(Block solidBlock, Settings settings) {
        super(solidBlock, settings);
        this.baseBlock = solidBlock;
        this.setDefaultState(this.stateManager.getDefaultState().with(UP, true).with(NORTH_WALL_SHAPE, WallShape.NONE).with(EAST_WALL_SHAPE, WallShape.NONE).with(SOUTH_WALL_SHAPE, WallShape.NONE).with(WEST_WALL_SHAPE, WallShape.NONE).with(WATERLOGGED, false));
        this.outlineShapeFunction = this.createShapeFunction(16.0F, 14.0F);
        this.collisionShapeFunction = this.createShapeFunction(24.0F, 24.0F);
    }

    private Function<BlockState, VoxelShape> createShapeFunction(float tallHeight, float lowHeight) {
        VoxelShape post = Block.createColumnShape(8.0F, 0.0F, tallHeight);
        Map<Direction, VoxelShape> low = VoxelShapes.createHorizontalFacingShapeMap(Block.createCuboidZShape(6.0, 0.0, lowHeight, 0.0, 11.0));
        Map<Direction, VoxelShape> tall = VoxelShapes.createHorizontalFacingShapeMap(Block.createCuboidZShape(6.0, 0.0, tallHeight, 0.0, 11.0));
        return this.createShapeFunction(state -> {
            VoxelShape shape = state.get(UP) ? post : VoxelShapes.empty();

            for (Entry<Direction, EnumProperty<WallShape>> entry : WALL_SHAPE_PROPERTIES_BY_DIRECTION.entrySet()) {
                shape = VoxelShapes.union(shape, switch (state.get(entry.getValue())) {
                    case NONE -> VoxelShapes.empty();
                    case LOW -> low.get(entry.getKey());
                    case TALL -> tall.get(entry.getKey());
                });
            }

            return shape;
        }, new Property[]{WATERLOGGED});
    }

    protected Function<BlockState, VoxelShape> createShapeFunction(Function<BlockState, VoxelShape> f, Property<?>[] ignored) {
        return f;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.outlineShapeFunction.apply(state);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.collisionShapeFunction.apply(state);
    }

    private boolean shouldConnectTo(BlockState state, boolean faceFullSquare, Direction side) {
        Block block = state.getBlock();
        boolean gateConnect = block instanceof net.minecraft.block.FenceGateBlock && net.minecraft.block.FenceGateBlock.canWallConnect(state, side);
        return state.isIn(BlockTags.WALLS) || !cannotConnect(state) && faceFullSquare || block instanceof net.minecraft.block.PaneBlock || gateConnect;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        WorldView worldView = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        FluidState fluidState = worldView.getFluidState(pos);
        BlockPos north = pos.north();
        BlockPos east = pos.east();
        BlockPos south = pos.south();
        BlockPos west = pos.west();
        BlockPos up = pos.up();
        BlockState northState = worldView.getBlockState(north);
        BlockState eastState = worldView.getBlockState(east);
        BlockState southState = worldView.getBlockState(south);
        BlockState westState = worldView.getBlockState(west);
        BlockState upState = worldView.getBlockState(up);
        boolean connectNorth = this.shouldConnectTo(northState, northState.isSideSolidFullSquare(worldView, north, Direction.SOUTH), Direction.SOUTH);
        boolean connectEast = this.shouldConnectTo(eastState, eastState.isSideSolidFullSquare(worldView, east, Direction.WEST), Direction.WEST);
        boolean connectSouth = this.shouldConnectTo(southState, southState.isSideSolidFullSquare(worldView, south, Direction.NORTH), Direction.NORTH);
        boolean connectWest = this.shouldConnectTo(westState, westState.isSideSolidFullSquare(worldView, west, Direction.EAST), Direction.EAST);
        BlockState base = this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        return this.getStateWith(worldView, base, up, upState, connectNorth, connectEast, connectSouth, connectWest);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (state.get(WATERLOGGED)) {
            tickView.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        if (direction == Direction.DOWN) {
            return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        } else {
            return direction == Direction.UP ? this.getStateAt(world, state, pos, neighborState) : this.getStateWithNeighbor(world, pos, state, neighborPos, neighborState, direction);
        }
    }

    private static boolean isConnected(BlockState state, Property<WallShape> property) {
        return state.get(property) != WallShape.NONE;
    }

    private static boolean shouldUseTallShape(VoxelShape aboveShape, VoxelShape tallShape) {
        return !VoxelShapes.matchesAnywhere(tallShape, aboveShape, BooleanBiFunction.ONLY_FIRST);
    }

    private BlockState getStateAt(WorldView world, BlockState state, BlockPos pos, BlockState aboveState) {
        boolean n = isConnected(state, NORTH_WALL_SHAPE);
        boolean e = isConnected(state, EAST_WALL_SHAPE);
        boolean s = isConnected(state, SOUTH_WALL_SHAPE);
        boolean w = isConnected(state, WEST_WALL_SHAPE);
        return this.getStateWith(world, state, pos, aboveState, n, e, s, w);
    }

    private BlockState getStateWithNeighbor(WorldView world, BlockPos pos, BlockState state, BlockPos neighborPos, BlockState neighborState, Direction direction) {
        Direction opposite = direction.getOpposite();
        boolean n = direction == Direction.NORTH ? this.shouldConnectTo(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, opposite), opposite) : isConnected(state, NORTH_WALL_SHAPE);
        boolean e = direction == Direction.EAST ? this.shouldConnectTo(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, opposite), opposite) : isConnected(state, EAST_WALL_SHAPE);
        boolean s = direction == Direction.SOUTH ? this.shouldConnectTo(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, opposite), opposite) : isConnected(state, SOUTH_WALL_SHAPE);
        boolean w = direction == Direction.WEST ? this.shouldConnectTo(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, opposite), opposite) : isConnected(state, WEST_WALL_SHAPE);
        BlockPos abovePos = pos.up();
        BlockState aboveState = world.getBlockState(abovePos);
        return this.getStateWith(world, state, abovePos, aboveState, n, e, s, w);
    }

    private BlockState getStateWith(WorldView world, BlockState state, BlockPos abovePos, BlockState aboveState, boolean north, boolean east, boolean south, boolean west) {
        VoxelShape aboveShape = aboveState.getCollisionShape(world, abovePos).getFace(Direction.DOWN);
        BlockState s = this.getStateWith(state, north, east, south, west, aboveShape);
        return s.with(UP, this.shouldHavePost(s, aboveState, aboveShape));
    }

    private boolean shouldHavePost(BlockState state, BlockState aboveState, VoxelShape aboveShape) {
        boolean aboveIsWallWithPost = aboveState.getBlock() instanceof WallBlock && (Boolean) aboveState.get(UP);
        if (aboveIsWallWithPost) {
            return true;
        } else {
            WallShape north = state.get(NORTH_WALL_SHAPE);
            WallShape south = state.get(SOUTH_WALL_SHAPE);
            WallShape east = state.get(EAST_WALL_SHAPE);
            WallShape west = state.get(WEST_WALL_SHAPE);
            boolean sN = north == WallShape.NONE;
            boolean sS = south == WallShape.NONE;
            boolean sE = east == WallShape.NONE;
            boolean sW = west == WallShape.NONE;
            boolean cond = sN && sS && sE && sW || sN != sS || sE != sW;
            if (cond) {
                return true;
            } else {
                boolean tallPair = (north == WallShape.TALL && south == WallShape.TALL) || (east == WallShape.TALL && west == WallShape.TALL);
                if (tallPair) return false;
                return aboveState.isIn(BlockTags.WALL_POST_OVERRIDE) || shouldUseTallShape(aboveShape, POST_SHAPE_FOR_TALL_TEST);
            }
        }
    }

    private BlockState getStateWith(BlockState state, boolean north, boolean east, boolean south, boolean west, VoxelShape aboveShape) {
        return state.with(NORTH_WALL_SHAPE, this.getWallShape(north, aboveShape, WALL_SHAPES_FOR_TALL_TEST_BY_DIRECTION.get(Direction.NORTH))).with(EAST_WALL_SHAPE, this.getWallShape(east, aboveShape, WALL_SHAPES_FOR_TALL_TEST_BY_DIRECTION.get(Direction.EAST))).with(SOUTH_WALL_SHAPE, this.getWallShape(south, aboveShape, WALL_SHAPES_FOR_TALL_TEST_BY_DIRECTION.get(Direction.SOUTH))).with(WEST_WALL_SHAPE, this.getWallShape(west, aboveShape, WALL_SHAPES_FOR_TALL_TEST_BY_DIRECTION.get(Direction.WEST)));
    }

    private WallShape getWallShape(boolean connected, VoxelShape aboveShape, VoxelShape tallShape) {
        if (connected) {
            return shouldUseTallShape(aboveShape, tallShape) ? WallShape.TALL : WallShape.LOW;
        } else {
            return WallShape.NONE;
        }
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected boolean isTransparent(BlockState state) {
        return !(Boolean) state.get(WATERLOGGED);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UP, NORTH_WALL_SHAPE, EAST_WALL_SHAPE, WEST_WALL_SHAPE, SOUTH_WALL_SHAPE, WATERLOGGED);
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        switch (rotation) {
            case CLOCKWISE_180:
                return state.with(NORTH_WALL_SHAPE, state.get(SOUTH_WALL_SHAPE)).with(EAST_WALL_SHAPE, state.get(WEST_WALL_SHAPE)).with(SOUTH_WALL_SHAPE, state.get(NORTH_WALL_SHAPE)).with(WEST_WALL_SHAPE, state.get(EAST_WALL_SHAPE));
            case COUNTERCLOCKWISE_90:
                return state.with(NORTH_WALL_SHAPE, state.get(EAST_WALL_SHAPE)).with(EAST_WALL_SHAPE, state.get(SOUTH_WALL_SHAPE)).with(SOUTH_WALL_SHAPE, state.get(WEST_WALL_SHAPE)).with(WEST_WALL_SHAPE, state.get(NORTH_WALL_SHAPE));
            case CLOCKWISE_90:
                return state.with(NORTH_WALL_SHAPE, state.get(WEST_WALL_SHAPE)).with(EAST_WALL_SHAPE, state.get(NORTH_WALL_SHAPE)).with(SOUTH_WALL_SHAPE, state.get(EAST_WALL_SHAPE)).with(WEST_WALL_SHAPE, state.get(SOUTH_WALL_SHAPE));
            default:
                return state;
        }
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        switch (mirror) {
            case LEFT_RIGHT:
                return state.with(NORTH_WALL_SHAPE, state.get(SOUTH_WALL_SHAPE)).with(SOUTH_WALL_SHAPE, state.get(NORTH_WALL_SHAPE));
            case FRONT_BACK:
                return state.with(EAST_WALL_SHAPE, state.get(WEST_WALL_SHAPE)).with(WEST_WALL_SHAPE, state.get(EAST_WALL_SHAPE));
            default:
                return super.mirror(state, mirror);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(WATERLOGGED)) {
            BlockState hardened = this.baseBlock.getDefaultState().with(UP, state.get(UP)).with(NORTH_WALL_SHAPE, state.get(NORTH_WALL_SHAPE)).with(EAST_WALL_SHAPE, state.get(EAST_WALL_SHAPE)).with(SOUTH_WALL_SHAPE, state.get(SOUTH_WALL_SHAPE)).with(WEST_WALL_SHAPE, state.get(WEST_WALL_SHAPE)).with(WATERLOGGED, true);
            world.setBlockState(pos, hardened, Block.NOTIFY_ALL);
            return;
        }
        super.scheduledTick(state, world, pos, random);
    }
}
