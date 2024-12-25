package de.pizzapost.minecraft_extra;

import de.pizzapost.minecraft_extra.effect.ModEffects;
import de.pizzapost.minecraft_extra.entity.ModEntities;
import de.pizzapost.minecraft_extra.event.LightningStrikeHandler;
import de.pizzapost.minecraft_extra.event.SleepRegeneration;
import de.pizzapost.minecraft_extra.item.ModItemGroups;
import de.pizzapost.minecraft_extra.item.ModItems;
import de.pizzapost.minecraft_extra.keybinds.ModKeys;
import de.pizzapost.minecraft_extra.sound.ModSounds;
import de.pizzapost.minecraft_extra.util.ModLootTableModifiers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.block.*;
import net.minecraft.client.gui.screen.advancement.AdvancementsScreen;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.lang.reflect.Field;
import java.util.List;

public class MinecraftExtra implements ModInitializer {
	public static final String MOD_ID = "minecraft_extra";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModLootTableModifiers.modifyLootTables();
		ModSounds.registerSounds();
		ModEntities.registeredModEntities();
		LightningStrikeHandler.registerLightningStrikeEvent();
		ModEffects.registerEffects();
		new SleepRegeneration().onInitialize();
		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
			if (!world.isClient()) {
				Block block = state.getBlock();

				if (block instanceof CropBlock) {
					try {
						int age = state.get(CropBlock.AGE);
						handleCropBlock(world, player, pos, block, age);
					} catch (Exception e) {
						;
					}
				} else if (block instanceof StemBlock || block instanceof AttachedStemBlock) {
					handleStemBlock(world, player, pos, state, block);
				} else if (block == Blocks.SWEET_BERRY_BUSH) {
					int age = state.get(SweetBerryBushBlock.AGE);
					handleSweetBerryBush(world, player, pos, block, age);
				} else if (block == Blocks.CAVE_VINES || block == Blocks.CAVE_VINES_PLANT) {
					handleCaveVines(world, player, pos, block);
				} else if (block == Blocks.BEETROOTS) {
					int age = state.get(BeetrootsBlock.AGE);
					handleBeetroot(world, player, pos, block, age);
				}
			}
		});
	}

	private void removeNearbyDrops(World world, BlockPos pos) {
		if (world instanceof ServerWorld serverWorld) {
			serverWorld.getServer().execute(() -> {
				Vec3d min = new Vec3d(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1);
				Vec3d max = new Vec3d(pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
				List<ItemEntity> drops = world.getEntitiesByClass(
						ItemEntity.class,
						new Box(min, max),
						itemEntity -> true
				);
				for (ItemEntity drop : drops) {
					drop.discard();
				}
			});
		}
	}

	private void break_crop_message (PlayerEntity player){
		if (player instanceof ServerPlayerEntity serverPlayer) {
			Text actionbarMessage = Text.translatable("actionbar.minecraft_extra.break_crop_message");
			serverPlayer.networkHandler.sendPacket(new OverlayMessageS2CPacket(actionbarMessage));
		}
	}

	private void handleCropBlock(World world, net.minecraft.entity.player.PlayerEntity player, BlockPos pos, Block block, int age) {
		if (age == ((CropBlock) block).getMaxAge()) {
			if (player.isSneaking()) {
				world.breakBlock(pos, true);
			} else {
				world.setBlockState(pos, block.getDefaultState().with(CropBlock.AGE, 0));
			}
		} else {
			if (player.isSneaking()) {
				world.breakBlock(pos, true);
			} else {
				world.setBlockState(pos, block.getDefaultState().with(CropBlock.AGE, age));
				removeNearbyDrops(world, pos);
				break_crop_message(player);
			}
		}
	}

	private void handleStemBlock(World world, net.minecraft.entity.player.PlayerEntity player, BlockPos pos, BlockState state, Block block) {
		if (block instanceof StemBlock) {
			int age = state.get(StemBlock.AGE);
			if (player.isSneaking()) {
				world.breakBlock(pos, true);
			}
			else {
				world.setBlockState(pos, block.getDefaultState().with(StemBlock.AGE, age));
				removeNearbyDrops(world, pos);
				break_crop_message(player);
			}
		} else if (block instanceof AttachedStemBlock) {
			if (player.isSneaking()) {
				world.breakBlock(pos, true);
			} else {
				Direction facing = state.get(AttachedStemBlock.FACING);
				world.setBlockState(pos, block.getDefaultState().with(AttachedStemBlock.FACING, facing));
				removeNearbyDrops(world, pos);
				break_crop_message(player);
			}
		}
	}

	private void handleBeetroot(World world, net.minecraft.entity.player.PlayerEntity player, BlockPos pos, Block block, int age) {
		if (age == ((BeetrootsBlock) block).getMaxAge()) {
			if (player.isSneaking()) {
				world.breakBlock(pos, true);
			}
			else {
				world.setBlockState(pos, block.getDefaultState().with(BeetrootsBlock.AGE, 0));
			}
		} else {
			if (player.isSneaking()) {
				world.breakBlock(pos, true);
			}
			else {
				world.setBlockState(pos, block.getDefaultState().with(BeetrootsBlock.AGE, age));
				removeNearbyDrops(world, pos);
				break_crop_message(player);
			}
		}
	}

	private void handleSweetBerryBush(World world, net.minecraft.entity.player.PlayerEntity player, BlockPos pos, Block block, int age) {
		if (player.isSneaking()) {
			world.breakBlock(pos, true);
		} else {
			world.setBlockState(pos, block.getDefaultState().with(SweetBerryBushBlock.AGE, age));
			removeNearbyDrops(world, pos);
			break_crop_message(player);
		}
	}

	private void handleCaveVines(World world, net.minecraft.entity.player.PlayerEntity player, BlockPos pos, Block block) {
		if (player.isSneaking()) {
			world.breakBlock(pos, true);
		} else {
			world.setBlockState(pos, block.getDefaultState());
			break_crop_message(player);
		}
	}

	public static class ClientInit implements ClientModInitializer {
		@Override
		public void onInitializeClient() {
			ModKeys.registerKeyBindings();
		}
	}
}