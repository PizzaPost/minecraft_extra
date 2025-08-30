package de.pizzapost.minecraft_extra.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class HoneyExtractor extends Block {
    private static final VoxelShape HONEY_EXTRACTOR_BASE = Block.createCuboidShape(0, 2, 0, 16, 3, 16);
    private static final VoxelShape FOOT1 = Block.createCuboidShape(0, 0, 0, 2, 2, 2);
    private static final VoxelShape FOOT2 = Block.createCuboidShape(14, 0, 0, 16, 2, 2);
    private static final VoxelShape FOOT3 = Block.createCuboidShape(0, 0, 14, 2, 2, 16);
    private static final VoxelShape FOOT4 = Block.createCuboidShape(14, 0, 14, 16, 2, 16);
    private static final VoxelShape SIDE_N = Block.createCuboidShape(0, 3, 0, 16, 16, 1);
    private static final VoxelShape SIDE_S = Block.createCuboidShape(0, 3, 15, 16, 16, 16);
    private static final VoxelShape SIDE_E = Block.createCuboidShape(15, 3, 1, 16, 16, 15);
    private static final VoxelShape SIDE_W = Block.createCuboidShape(0, 3, 1, 1, 16, 15);
    private static final VoxelShape TOP_1 = Block.createCuboidShape(1, 12, 1, 6, 16, 15);
    private static final VoxelShape TOP_2 = Block.createCuboidShape(10, 12, 1, 15, 16, 15);
    private static final VoxelShape TOP_3 = Block.createCuboidShape(6, 12, 1, 10, 16, 6);
    private static final VoxelShape TOP_4 = Block.createCuboidShape(6, 12, 10, 10, 16, 15);

    private static final VoxelShape SHAPE = VoxelShapes.union(HONEY_EXTRACTOR_BASE, FOOT1, FOOT2, FOOT3, FOOT4, SIDE_N, SIDE_S, SIDE_E, SIDE_W, TOP_1, TOP_2, TOP_3, TOP_4);

    public HoneyExtractor(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}