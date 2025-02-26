
package de.pizzapost.minecraft_extra.block.custom;

import de.pizzapost.minecraft_extra.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class AshBlock extends SnowBlock {

    public AshBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        super.neighborUpdate(state, world, pos, block, fromPos, notify);
        if (fromPos.equals(pos.down()) && world.isAir(fromPos)) {
            int layers = state.get(Properties.LAYERS);
            world.spawnEntity(new ItemEntity(world, pos.getX()+0.5, pos.getY(), pos.getZ()+0.5, new ItemStack(ModItems.ASH, layers)));
        }
    }
}