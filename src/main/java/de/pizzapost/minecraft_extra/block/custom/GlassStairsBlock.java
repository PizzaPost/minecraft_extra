package de.pizzapost.minecraft_extra.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Stainable;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.DyeColor;

public class GlassStairsBlock extends StairsBlock implements Stainable {
    private final DyeColor color;

    public GlassStairsBlock(DyeColor color, BlockState baseBlockState, Settings settings) {
        super(baseBlockState, settings);
        this.color = color;
    }


    @Override
    public DyeColor getColor() {
        return this.color;
    }
}
