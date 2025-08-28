package de.pizzapost.minecraft_extra.block.custom;

import net.minecraft.block.Stainable;
import net.minecraft.block.WallBlock;
import net.minecraft.util.DyeColor;

public class GlassWallBlock extends WallBlock implements Stainable {
    private final DyeColor color;
    public GlassWallBlock(DyeColor color, Settings settings) {
        super(settings);
        this.color = color;
    }

    @Override
    public DyeColor getColor() {
        return this.color;
    }
}
