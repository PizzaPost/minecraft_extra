package de.pizzapost.minecraft_extra.block.custom;

import com.mojang.serialization.MapCodec;
import de.pizzapost.minecraft_extra.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

import java.util.Map;

public class CopperAnvil extends FallingBlock {
    public static final MapCodec<CopperAnvil> CODEC = createCodec(CopperAnvil::new);
    public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;
    private static final Map<Direction.Axis, VoxelShape> SHAPES_BY_AXIS = VoxelShapes.createHorizontalAxisShapeMap(VoxelShapes.union(Block.createColumnShape(12.0, 0.0, 4.0), Block.createColumnShape(8.0, 10.0, 4.0, 5.0), Block.createColumnShape(4.0, 8.0, 5.0, 10.0), Block.createColumnShape(10.0, 16.0, 10.0, 16.0)));

    @Override
    public MapCodec<CopperAnvil> getCodec() {
        return CODEC;
    }

    public CopperAnvil(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().rotateYClockwise());
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (VoxelShape) SHAPES_BY_AXIS.get(((Direction) state.get(FACING)).getAxis());
    }

    @Override
    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
        entity.setHurtEntities(2.0F, 40);
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        if (!fallingBlockEntity.isSilent()) {
            world.syncWorldEvent(WorldEvents.ANVIL_LANDS, pos, 0);
            if (fallingBlockState.isOf(ModBlocks.COPPER_ANVIL)) {
                world.setBlockState(pos, ModBlocks.CHIPPED_COPPER_ANVIL.getDefaultState().with(FACING, fallingBlockState.get(FACING)));
            } else if (fallingBlockState.isOf(ModBlocks.CHIPPED_COPPER_ANVIL)) {
                world.setBlockState(pos, ModBlocks.DAMAGED_COPPER_ANVIL.getDefaultState().with(FACING, fallingBlockState.get(FACING)));
            } else {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
                world.syncWorldEvent(WorldEvents.ANVIL_DESTROYED, pos, 0);
            }
        }
    }

    @Override
    public DamageSource getDamageSource(Entity attacker) {
        return attacker.getDamageSources().fallingAnvil(attacker);
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    public int getColor(BlockState state, BlockView world, BlockPos pos) {
        return state.getMapColor(world, pos).color;
    }
}
