package de.pizzapost.minecraft_extra.item.custom;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.item.ModItems;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HoneyKeyItem extends Item {
    public HoneyKeyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        PlayerEntity player = context.getPlayer();
        if (!world.isClient && player != null) {
            if (state.isOf(Blocks.WATER_CAULDRON)) {
                int level = state.get(LeveledCauldronBlock.LEVEL);
                if (level > 1) {
                    world.setBlockState(pos, state.with(LeveledCauldronBlock.LEVEL, level - 1));
                } else if (level == 1) {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
                if (level > 0) {
                    if (player instanceof ServerPlayerEntity serverPlayer) {
                        Identifier advancementId = Identifier.of(MinecraftExtra.MOD_ID, "honey_key_clean");
                        AdvancementEntry advancement = serverPlayer.getServer().getAdvancementLoader().get(advancementId);
                        if (advancement != null) {
                            serverPlayer.getAdvancementTracker().grantCriterion(advancement, "imp");
                        }
                    }
                }
                if (player.getStackInHand(Hand.MAIN_HAND).isOf(ModItems.HONEY_KEY)) {
                    player.setStackInHand(Hand.MAIN_HAND, new ItemStack(ModItems.HONEY_KEY_CLEAN));
                } else if (player.getStackInHand(Hand.OFF_HAND).isOf(ModItems.HONEY_KEY)) {
                    player.setStackInHand(Hand.OFF_HAND, new ItemStack(ModItems.HONEY_KEY_CLEAN));
                }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
}
