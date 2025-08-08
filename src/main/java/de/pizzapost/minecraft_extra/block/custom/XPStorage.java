package de.pizzapost.minecraft_extra.block.custom;

import com.mojang.serialization.MapCodec;
import de.pizzapost.minecraft_extra.block.entity.XPStorageBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class XPStorage extends BlockWithEntity {

    public static final IntProperty XP = IntProperty.of("xp", 0, 10253);

    public XPStorage(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(XP, 0));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(XPStorage::new);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new XPStorageBlockEntity(pos, state);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (player.isSneaking()) {
            if (player.totalExperience > 0 && state.get(XP) < 10253) {
                player.addExperience(-1);
                world.setBlockState(pos, state.with(XP, state.get(XP) + 1));
            } else if (player.totalExperience == 0) {
                Text message = Text.translatable("block.minecraft_extra.xp_storage.player_empty");
                player.sendMessage(message, true);
                return ActionResult.FAIL;
            } else if (state.get(XP) >= 10253) {
                Text message = Text.translatable("block.minecraft_extra.xp_storage.full");
                player.sendMessage(message, true);
                return ActionResult.FAIL;
            }
            return ActionResult.SUCCESS;
        } else {
            if (state.get(XP) > 0) {
                player.addExperience(1);
                world.setBlockState(pos, state.with(XP, state.get(XP) - 1));
            } else if (state.get(XP) == 0) {
                Text message = Text.translatable("block.minecraft_extra.xp_storage.storage_empty");
                player.sendMessage(message, true);
                return ActionResult.FAIL;
            }
            return ActionResult.SUCCESS;
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(XP);
    }

    @Override
    protected boolean isTransparent(BlockState state) {
        return true;
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);
        if (!world.isClient) {
            int xpToDrop = state.get(XP);
            for (int i = 0; i < xpToDrop / 2; i++) {
                ExperienceOrbEntity orb = new ExperienceOrbEntity(world, (double) pos.getX() + 0.5, (double) pos.getY() + 0.5, (double) pos.getZ() + 0.5, 1);
                world.spawnEntity(orb);
            }
        }
    }
}
