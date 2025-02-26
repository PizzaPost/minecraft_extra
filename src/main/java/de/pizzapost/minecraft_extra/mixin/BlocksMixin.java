package de.pizzapost.minecraft_extra.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public abstract class BlocksMixin {
    @Redirect(
            method = "<clinit>",
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=bedrock")),
            at = @At(value = "NEW", target = "(Lnet/minecraft/block/AbstractBlock$Settings;)Lnet/minecraft/block/Block;", ordinal = 0)
    )
    private static Block nemuelch$redirectBedrockBlockRegistration(AbstractBlock.Settings settings) {
        return new Block(AbstractBlock.Settings.create()
                .mapColor(MapColor.STONE_GRAY)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .strength(1000F, 3600000.0F)
                .allowsSpawning(Blocks::never));
    }
}
