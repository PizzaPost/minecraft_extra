package de.pizzapost.minecraft_extra.block.custom;

import com.mojang.serialization.MapCodec;
import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.block.ModBlocks;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class FlintBlockBlockEntity extends BlockWithEntity {
    private static final List<Block> FLAMMABLE_BLOCKS = Arrays.asList(
            Blocks.DEAD_BUSH,
            Blocks.BLUE_ORCHID,
            Blocks.LILY_OF_THE_VALLEY,
            Blocks.PITCHER_CROP,
            Blocks.TORCHFLOWER_CROP,
            Blocks.DANDELION,
            Blocks.OXEYE_DAISY,
            Blocks.PINK_TULIP,
            Blocks.ALLIUM,
            Blocks.AZURE_BLUET,
            Blocks.WITHER_ROSE,
            Blocks.PINK_PETALS,
            Blocks.RED_TULIP,
            Blocks.ORANGE_TULIP,
            Blocks.WHITE_TULIP,
            Blocks.ROSE_BUSH,
            Blocks.LILAC,
            Blocks.PEONY,
            Blocks.PITCHER_PLANT,
            Blocks.SUNFLOWER,
            Blocks.POPPY,
            Blocks.CORNFLOWER,
            Blocks.TORCHFLOWER,
            Blocks.LARGE_FERN,
            Blocks.TALL_GRASS,
            Blocks.FERN,
            Blocks.SHORT_GRASS,
            Blocks.MOSS_CARPET,
            Blocks.AZALEA,
            Blocks.MOSS_BLOCK,
            Blocks.DARK_OAK_SAPLING,
            Blocks.ACACIA_SAPLING,
            Blocks.BIRCH_SAPLING,
            Blocks.FLOWERING_AZALEA,
            Blocks.CHERRY_SAPLING,
            Blocks.MANGROVE_PROPAGULE,
            Blocks.JUNGLE_SAPLING,
            Blocks.SPRUCE_SAPLING,
            Blocks.OAK_SAPLING,
            Blocks.OAK_LEAVES,
            Blocks.CHERRY_LEAVES,
            Blocks.AZALEA_LEAVES,
            Blocks.SPRUCE_LEAVES,
            Blocks.DARK_OAK_LEAVES,
            Blocks.BIRCH_LEAVES,
            Blocks.ACACIA_LEAVES,
            Blocks.JUNGLE_LEAVES,
            Blocks.MANGROVE_LEAVES,
            Blocks.FLOWERING_AZALEA_LEAVES,
            Blocks.NETHERRACK,
            Blocks.CRIMSON_NYLIUM,
            Blocks.WARPED_NYLIUM,
            Blocks.SOUL_SAND,
            Blocks.SOUL_SOIL,
            Blocks.MANGROVE_ROOTS,
            Blocks.SWEET_BERRY_BUSH,
            Blocks.CAVE_VINES,
            Blocks.CAVE_VINES_PLANT,
            Blocks.HAY_BLOCK
    );

    private static final List<Block> FLAMMABLE_BLOCKS2 = Arrays.asList(
            Blocks.POTATOES,
            Blocks.CARROTS,
            Blocks.BEETROOTS,
            Blocks.PUMPKIN_STEM,
            Blocks.ATTACHED_PUMPKIN_STEM,
            Blocks.MELON_STEM,
            Blocks.ATTACHED_MELON_STEM,
            Blocks.WHEAT
    );

    public FlintBlockBlockEntity(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState());
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (placer.isSneaking()) return;
        boolean proceed = false;
        for (int x=-1; x<2; x++) {
            for (int y = -1; y < 2; y++) {
                for (int z = -1; z < 2; z++) {
                    if ((world.getBlockState(pos.add(x, y, z)).getBlock() == ModBlocks.FLINT_BLOCK)) {
                        if (!(pos.add(x, y, z).equals(pos))) {
                            world.setBlockState(pos.add(x, y, z), Blocks.FIRE.getDefaultState());
                            proceed = true;
                            if (placer instanceof ServerPlayerEntity serverPlayer) {
                                Identifier advancementId = Identifier.of(MinecraftExtra.MOD_ID, "flint_block");
                                AdvancementEntry advancement = serverPlayer.getServer().getAdvancementLoader().get(advancementId);
                                if (advancement != null) {
                                    serverPlayer.getAdvancementTracker().grantCriterion(advancement, "imp");
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!proceed) return;
        for (int x = -2; x < 3; x++) {
            for (int y = -2; y < 3; y++) {
                for (int z = -2; z < 3; z++) {
                    if (FLAMMABLE_BLOCKS.contains(world.getBlockState(pos.add(x, y, z)).getBlock())) {
                        world.setBlockState(pos.add(x, y, z), Blocks.FIRE.getDefaultState());
                    } else if (FLAMMABLE_BLOCKS2.contains(world.getBlockState(pos.add(x, y, z)).getBlock())) {
                        world.setBlockState(pos.add(x, y-1, z), Blocks.DIRT.getDefaultState());
                        world.setBlockState(pos.add(x, y, z), Blocks.FIRE.getDefaultState());
                    }
                }
            }
        }
        world.setBlockState(pos, Blocks.FIRE.getDefaultState());
        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 1.0f, 1.0f);
        Vec3d pos2 = pos.toCenterPos();
        Box area = new Box(pos2.subtract(8, 8, 8), pos2.add(8, 8, 8));
        PlayerEntity player = (PlayerEntity) placer;
        for (PlayerEntity targetPlayer : world.getPlayers()) {
            if (area.contains(targetPlayer.getPos())) {
                targetPlayer.damage(world.getDamageSources().playerAttack(player), 1.5F);
                Vec3d direction = targetPlayer.getPos().subtract(Vec3d.ofCenter(pos));
                double distance = direction.length();
                double boost = Math.max(0, 1 / (distance + 1)*1.5);
                targetPlayer.addVelocity(direction.x * boost, 0.02 * boost, direction.z * boost);
                Vec3d currentVelocity = targetPlayer.getVelocity();
                targetPlayer.setVelocity(currentVelocity);
            }
        }
        for (LivingEntity targetEntity : world.getEntitiesByClass(MobEntity.class, area, e -> true)) {
            if (area.contains(targetEntity.getPos())) {
                targetEntity.damage(world.getDamageSources().playerAttack(player), 1.5F);
                Vec3d direction = targetEntity.getPos().subtract(Vec3d.ofCenter(pos));
                double distance = direction.length();
                double boost = Math.max(0, 1 / (distance + 1)*1.5);
                targetEntity.addVelocity(direction.x * boost, 0.02 * boost, direction.z * boost);
                Vec3d currentVelocity = targetEntity.getVelocity();
                targetEntity.setVelocity(currentVelocity);
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }
}
