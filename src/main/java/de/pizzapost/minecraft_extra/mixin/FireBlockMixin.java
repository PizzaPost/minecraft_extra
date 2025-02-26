package de.pizzapost.minecraft_extra.mixin;

import de.pizzapost.minecraft_extra.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireBlock.class)
public abstract class FireBlockMixin {
    @Shadow
    protected abstract int getSpreadChance(BlockState state);
    @Inject(method = "trySpreadingFire", at = @At("HEAD"), cancellable = true)
    private void modifyFireSpread(World world, BlockPos pos, int spreadFactor, Random random, int currentAge, CallbackInfo ci) {
        int i = this.getSpreadChance(world.getBlockState(pos));
        if (random.nextInt(spreadFactor) < i) {
            BlockState blockState = world.getBlockState(pos);
            if (random.nextInt(currentAge + 10) < 5 && !world.hasRain(pos)) {
                int j = Math.min(currentAge + random.nextInt(5) / 4, 15);
                BlockState fireState = Blocks.FIRE.getDefaultState().with(Properties.AGE_15, j);
                world.setBlockState(pos, fireState, Block.NOTIFY_ALL);
            } else {
                if (random.nextFloat() < 0.6) {
                    world.setBlockState(pos, ModBlocks.ASH_LAYER.getDefaultState().with(Properties.LAYERS, random.nextInt(3)+1), Block.NOTIFY_ALL);
                } else {
                    world.removeBlock(pos, false);
                }
            }
            Block block = blockState.getBlock();
            if (block instanceof TntBlock) {
                TntBlock.primeTnt(world, pos);
            }
        }
        ci.cancel();
    }
}
