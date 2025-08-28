package de.pizzapost.minecraft_extra.block.custom;

import net.minecraft.block.SlabBlock;
import net.minecraft.block.Stainable;
import net.minecraft.util.DyeColor;

public class GlassSlabBlock extends SlabBlock implements Stainable {
    private final DyeColor color;
    public GlassSlabBlock(DyeColor color, Settings settings) {
        super(settings);
        this.color = color;
    }

    @Override
    public DyeColor getColor() {
        return this.color;
    }
}
