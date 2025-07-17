package de.pizzapost.minecraft_extra.mixin;

import net.minecraft.block.Blocks;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public abstract class BlocksMixin {

    @ModifyArg(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=bedrock"), to = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;BEDROCK:Lnet/minecraft/block/Block;", opcode = Opcodes.PUTSTATIC)), at = @At(value = "INVOKE", target = "Lnet/minecraft/block/AbstractBlock$Settings;strength(FF)Lnet/minecraft/block/AbstractBlock$Settings;", ordinal = 0), index = 0)
    private static float modifyBedrockHardness(float original) {
        return 1000.0F;
    }
}