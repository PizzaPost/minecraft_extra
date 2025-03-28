package de.pizzapost.minecraft_extra;

import de.pizzapost.minecraft_extra.block.ModBlocks;
import de.pizzapost.minecraft_extra.block.custom.HoneyBerryBushBlock;
import de.pizzapost.minecraft_extra.commands.ModCommands;
import de.pizzapost.minecraft_extra.effect.ModEffects;
import de.pizzapost.minecraft_extra.entity.ModEntities;
import de.pizzapost.minecraft_extra.entity.custom.IceblazeEntity;
import de.pizzapost.minecraft_extra.entity.custom.SoapBubbleEntity;
import de.pizzapost.minecraft_extra.event.LightningStrikeHandler;
import de.pizzapost.minecraft_extra.event.SleepRegeneration;
import de.pizzapost.minecraft_extra.item.custom.AttributeCoreScaledItem;
import de.pizzapost.minecraft_extra.item.ModItemGroups;
import de.pizzapost.minecraft_extra.item.ModItems;
import de.pizzapost.minecraft_extra.item.custom.HardenedNetheriteAxeItem;
import de.pizzapost.minecraft_extra.item.custom.TimeControlDeviceItem;
import de.pizzapost.minecraft_extra.keybinds.ModKeys;
import de.pizzapost.minecraft_extra.particle.ModParticles;
import de.pizzapost.minecraft_extra.sound.ModSounds;
import de.pizzapost.minecraft_extra.util.ModLootTableModifiers;
import de.pizzapost.minecraft_extra.villager.CustomVillagerTrades;
import de.pizzapost.minecraft_extra.villager.ModVillagers;
import de.pizzapost.minecraft_extra.world.gen.ModWorldGeneration;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.block.*;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;
import net.minecraft.world.Heightmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class MinecraftExtra implements ModInitializer {
	public static final String MOD_ID = "minecraft_extra";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final Set<Item> TARGET_ITEMS = Set.of(
			Items.COBBLESTONE,
			Items.COBBLED_DEEPSLATE,
			Items.NETHERRACK,
			Items.DIRT,
			Items.END_STONE,
			Items.SAND,
			Items.STONE,
			Items.DEEPSLATE
	);
	private static final Random RANDOM = new Random();

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModLootTableModifiers.modifyLootTables();
		ModSounds.registerSounds();
		ModEntities.registerModEntities();
		LightningStrikeHandler.registerLightningStrikeEvent();
		ModEffects.registerEffects();
		ModBlocks.registerModBlocks();
		ModBlocks.registerCompostables();
		ModVillagers.registerVillagers();
		CustomVillagerTrades.registerVillagerTrades();
		ModCommands.registerModCommands();
		FuelRegistry.INSTANCE.add(ModItems.ROTTEN_CHUNK, 150);
		ModWorldGeneration.generateModWorldGen();
		ModParticles.registerParticles();
		new SleepRegeneration().onInitialize();
		ServerTickEvents.END_WORLD_TICK.register(this::onWorldTick);
		ServerTickEvents.START_WORLD_TICK.register(world -> {
			for (ServerPlayerEntity player : world.getPlayers()) {
				ItemStack itemStack = player.getStackInHand(Hand.MAIN_HAND);
				checkPlayerHandForEffect(player, world);
				AttributeCoreScaledItem.checkAndResetAttributes(player);
				if (player.getPos().getY() >= 400) {
					if (player instanceof ServerPlayerEntity serverPlayer) {
						Identifier advancementId = Identifier.of(MinecraftExtra.MOD_ID, "gravity");
						AdvancementEntry advancement = serverPlayer.getServer().getAdvancementLoader().get(advancementId);
						if (advancement != null) {
							serverPlayer.getAdvancementTracker().grantCriterion(advancement, "imp");
						}
					}
				}
				if (ModKeys.silkTouchKey.isPressed() && itemStack.isOf(ModItems.HARDENED_NETHERITE_PICKAXE)) {
					var enchantmentRegistry = player.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT);
					itemStack.addEnchantment(enchantmentRegistry.entryOf(Enchantments.SILK_TOUCH), 1);
				} else if (!ModKeys.silkTouchKey.isPressed() && itemStack.isOf(ModItems.HARDENED_NETHERITE_PICKAXE)){
					if (itemStack.getEnchantments().toString().contains("minecraft:silk_touch")) {
						PlayerInventory inventory = player.getInventory();
						inventory.setStack(inventory.selectedSlot, ModItems.HARDENED_NETHERITE_PICKAXE.getDefaultStack());
						ItemStack itemStack2 = player.getStackInHand(Hand.MAIN_HAND);
						var changes = ComponentChanges.builder()
								.add(DataComponentTypes.ITEM_NAME, itemStack.getName().copy().formatted(Formatting.DARK_RED).formatted(Formatting.ITALIC))
								.add(DataComponentTypes.CUSTOM_NAME, itemStack.getName().copy().formatted(Formatting.DARK_RED))
								.build();
						itemStack2.applyChanges(changes);
						var enchantmentRegistry = player.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT);
						itemStack2.addEnchantment(enchantmentRegistry.entryOf(Enchantments.EFFICIENCY), 5);
						itemStack2.addEnchantment(enchantmentRegistry.entryOf(Enchantments.FORTUNE), 3);
						itemStack2.addEnchantment(enchantmentRegistry.entryOf(Enchantments.UNBREAKING), 3);
						itemStack2.setDamage(itemStack.getDamage());
					}
				}
			}
		});
		ServerTickEvents.END_WORLD_TICK.register(world -> {
			if (world.isRaining()) {
				if (world.random.nextFloat() < 0.000025f) {
					var players = world.getPlayers();
					if (players.isEmpty()) return;
					ServerPlayerEntity player = players.get(world.random.nextInt(players.size()));
					double offsetX = (world.random.nextDouble() - 0.5) * 40;
					double offsetZ = (world.random.nextDouble() - 0.5) * 40;
					double targetX = player.getX() + offsetX;
					double targetZ = player.getZ() + offsetZ;
					int topY = world.getTopY(Heightmap.Type.MOTION_BLOCKING, (int) Math.floor(targetX), (int) Math.floor(targetZ));
					double spawnY = topY + 50;
					ItemStack itemStack = new ItemStack(ModItems.WEATHER_ITEM);
					ItemEntity itemEntity = new ItemEntity(world, targetX, spawnY, targetZ, itemStack);
					world.spawnEntity(itemEntity);
				}
			}
			try {
				for (ServerPlayerEntity player : world.getPlayers()) {
					checkUnderwaterForItem(player);
					checkPlayerMovement(player);
					if (player.getWorld().getRegistryKey() == World.END && player.getY() <= -55) {
						if (player instanceof ServerPlayerEntity serverPlayer) {
							Vec3d pos = player.getPos();
							player.teleport(player.server.getWorld(World.OVERWORLD), pos.getX(), 499, pos.getZ(), player.getYaw(), player.getPitch());
							serverPlayer.refreshPositionAfterTeleport(player.getX(), player.getY(), player.getZ());
						}
					} else if (player.getWorld().getRegistryKey() == World.OVERWORLD && player.getY() >= 500) {
						if (player instanceof ServerPlayerEntity serverPlayer) {
							boolean hasEnteredTheEnd = serverPlayer.getAdvancementTracker()
									.getProgress(serverPlayer.server.getAdvancementLoader()
											.get(Identifier.of("minecraft:story/enter_the_end"))).isDone();
							if (hasEnteredTheEnd) {
								Vec3d pos = player.getPos();
								player.teleport(player.server.getWorld(World.END), pos.getX(), -40, pos.getZ(), player.getYaw(), player.getPitch());
								serverPlayer.refreshPositionAfterTeleport(player.getX(), player.getY(), player.getZ());
								Vec3d boost = new Vec3d(0, 1.5, 0);
								player.setVelocity(player.getVelocity().add(boost));
								player.velocityModified = true;
							} else {
								Text actionbarMessage = Text.translatable("actionbar.minecraft_extra.levitate_between_dimensions");
								serverPlayer.networkHandler.sendPacket(new OverlayMessageS2CPacket(actionbarMessage));
							}
						}
					} else if (player.getWorld().getRegistryKey() == World.OVERWORLD && player.getY() <= -95) {
						if (player instanceof ServerPlayerEntity serverPlayer) {
							boolean hasEnteredTheNether = serverPlayer.getAdvancementTracker()
									.getProgress(serverPlayer.server.getAdvancementLoader()
											.get(Identifier.of("minecraft:nether/root"))).isDone();
							if (hasEnteredTheNether) {
								Vec3d pos = player.getPos();
								player.teleport(player.server.getWorld(World.NETHER), pos.getX() * 8, 250, pos.getZ() * 8, player.getYaw(), player.getPitch());
								serverPlayer.refreshPositionAfterTeleport(player.getX(), player.getY(), player.getZ());
							} else {
								Text actionbarMessage = Text.translatable("actionbar.minecraft_extra.levitate_between_dimensions");
								serverPlayer.networkHandler.sendPacket(new OverlayMessageS2CPacket(actionbarMessage));
							}
						}
					} else if (player.getWorld().getRegistryKey() == World.NETHER && player.getY() >= 251) {
						if (player instanceof ServerPlayerEntity serverPlayer) {
							Vec3d pos = player.getPos();
							player.teleport(player.server.getWorld(World.OVERWORLD), pos.getX()/8, -80, pos.getZ()/8, player.getYaw(), player.getPitch());
							serverPlayer.refreshPositionAfterTeleport(player.getX(), player.getY(), player.getZ());
							Vec3d boost = new Vec3d(0, 1.5, 0);
							player.setVelocity(player.getVelocity().add(boost));
							player.velocityModified = true;
						}
					}
				}
			} catch (Exception ignored) {
			}
		});

		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (player instanceof ServerPlayerEntity serverPlayer) {
				giveItemWithChance(serverPlayer, ModItems.EFFECT_GEM_STRENGTH.getDefaultStack(), 0.001);
			}
			return ActionResult.PASS;
		});

		PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
			if (player.getStackInHand(Hand.MAIN_HAND).isOf(ModItems.HAMMER)) {
				if (state.getBlock() == Blocks.STONE || state.getBlock() == Blocks.DEEPSLATE || state.getBlock() == Blocks.COBBLESTONE || state.getBlock() == Blocks.COBBLED_DEEPSLATE) {
					world.breakBlock(pos, false);
					ItemStack itemStack = player.getStackInHand(Hand.MAIN_HAND);
					itemStack.setDamage(itemStack.getDamage() + 1);
					world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.GRAVEL, 2)));
					if (player instanceof ServerPlayerEntity serverPlayer) {
						Identifier advancementId = Identifier.of(MinecraftExtra.MOD_ID, "hammer");
						AdvancementEntry advancement = serverPlayer.getServer().getAdvancementLoader().get(advancementId);
						if (advancement != null) {
							serverPlayer.getAdvancementTracker().grantCriterion(advancement, "imp");
						}
					}
					return false;
				}
			}
			return true;
		});
		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
			if (state.getBlock()==Blocks.BEDROCK) {
				if (player.getStackInHand(Hand.MAIN_HAND).isOf(ModItems.HARDENED_NETHERITE_PICKAXE)) {
					world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(Items.BEDROCK, 1)));
				} else if (player.getStackInHand(Hand.OFF_HAND).isOf(ModItems.HARDENED_NETHERITE_PICKAXE)) {
					world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(Items.BEDROCK, 1)));
				}
			}
		});
		PlayerBlockBreakEvents.BEFORE.register((world,  player, pos, state,  blockEntity) -> {
			ItemStack itemStack = player.getMainHandStack();
			if (itemStack.isOf(ModItems.HARDENED_NETHERITE_AXE)) {
				if (itemStack.hasEnchantments()) HardenedNetheriteAxeItem.postMine(itemStack, world, state, pos, player, Hand.MAIN_HAND);
			} else if (itemStack.isOf(ModItems.HARDENED_NETHERITE_SHOVEL)) {
				if (itemStack.hasEnchantments() && state.getBlock()==Blocks.GRAVEL) {
					world.breakBlock(pos, false);
					player.increaseStat(Stats.MINED.getOrCreateStat(Blocks.GRAVEL), 1);
					world.spawnEntity(new ItemEntity(world, pos.getX()+0.5, pos.getY(), pos.getZ()+0.5, new ItemStack(Items.FLINT, 2)));
				}
			} else if (state.getBlock()==ModBlocks.ASH_LAYER) {
				if (!player.isCreative()) {
					int layers = state.get(Properties.LAYERS);
					world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(ModItems.ASH, layers)));
				}
			}
            return true;
        });
		PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
			if (player instanceof ServerPlayerEntity serverPlayer) {
				giveItemWithChance(serverPlayer, ModItems.EFFECT_GEM_HASTE.getDefaultStack(), 0.0001);
			}
			if (!world.isClient()) {
				Block block = state.getBlock();
				if (block instanceof CropBlock && !(block==Blocks.BEETROOTS)) {
					try {
						int age = state.get(CropBlock.AGE);
						handleCropBlock(world, player, pos, block, age);
					} catch (Exception e) {
						;
					}
					return false;
				} else if (block instanceof StemBlock || block instanceof AttachedStemBlock) {
					handleStemBlock(world, player, pos, state, block);
					return false;
				} else if (block == Blocks.SWEET_BERRY_BUSH) {
					int age = state.get(SweetBerryBushBlock.AGE);
					handleSweetBerryBush(world, player, pos, block, age);
					return false;
				} else if (block == Blocks.CAVE_VINES || block == Blocks.CAVE_VINES_PLANT) {
					handleCaveVines(world, player, pos, block);
					return false;
				} else if (block == Blocks.BEETROOTS) {
					int age = state.get(BeetrootsBlock.AGE);
					handleBeetroot(world, player, pos, block, age);
					return false;
				} else if (block == ModBlocks.HONEY_BERRY_BUSH) {
					int age = state.get(HoneyBerryBushBlock.AGE);
					handleHoneyBerryBush(world, player, pos, block, age);
				}
			}
			return true;
        });
		FabricDefaultAttributeRegistry.register(ModEntities.ICEBLAZE, IceblazeEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.SOAP_BUBBLE, SoapBubbleEntity.createAttributes());
	}

	private void onWorldTick(ServerWorld world) {
		for (Entity entity : world.getEntitiesByType(EntityType.ITEM, e -> true)) {
			if (entity instanceof ItemEntity itemEntity) {
				Item item = itemEntity.getStack().getItem();
				if (TARGET_ITEMS.contains(item)) {
					adjustMaxStackSize(itemEntity);
				}
			}
		}
	}

	private void adjustMaxStackSize(ItemEntity itemEntity) {
		itemEntity.getStack().set(DataComponentTypes.MAX_STACK_SIZE, 99);
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
				world.breakBlock(pos, true);
				world.setBlockState(pos, block.getDefaultState().with(CropBlock.AGE, 0));
			}
		} else {
			if (player.isSneaking()) {
				world.breakBlock(pos, true);
			} else {
				world.breakBlock(pos, false);
				world.setBlockState(pos, block.getDefaultState().with(CropBlock.AGE, age));
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
				world.breakBlock(pos, false);
				world.setBlockState(pos, block.getDefaultState().with(StemBlock.AGE, age));
				break_crop_message(player);
			}
		} else if (block instanceof AttachedStemBlock) {
			if (player.isSneaking()) {
				world.breakBlock(pos, true);
			} else {
				world.breakBlock(pos, false);
				Direction facing = state.get(AttachedStemBlock.FACING);
				world.setBlockState(pos, block.getDefaultState().with(AttachedStemBlock.FACING, facing));
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
				world.breakBlock(pos, true);
				world.setBlockState(pos, block.getDefaultState().with(BeetrootsBlock.AGE, 0));
			}
		} else {
			if (player.isSneaking()) {
				world.breakBlock(pos, true);
			}
			else {
				world.breakBlock(pos, false);
				world.setBlockState(pos, block.getDefaultState().with(BeetrootsBlock.AGE, age));
				break_crop_message(player);
			}
		}
	}

	private void handleHoneyBerryBush(World world, PlayerEntity player, BlockPos pos, Block block, int age) {
		if (player.isSneaking()) {
			world.breakBlock(pos, true);
		} else {
			world.breakBlock(pos, false);
			world.setBlockState(pos, block.getDefaultState().with(HoneyBerryBushBlock.AGE, age));
			break_crop_message(player);
			System.out.println("Warum funktioniert das nicht :(");
		}
	}

	private void handleSweetBerryBush(World world, PlayerEntity player, BlockPos pos, Block block, int age) {
		if (player.isSneaking()) {
			world.breakBlock(pos, true);
		} else {
			world.breakBlock(pos, false);
			world.setBlockState(pos, block.getDefaultState().with(SweetBerryBushBlock.AGE, age));
			break_crop_message(player);
		}
	}

	private void handleCaveVines(World world, PlayerEntity player, BlockPos pos, Block block) {
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

	private void checkPlayerHandForEffect(ServerPlayerEntity player, World world) {
		boolean hasHasteGem =
				player.getStackInHand(Hand.MAIN_HAND).isOf(ModItems.EFFECT_GEM_HASTE) ||
				player.getStackInHand(Hand.OFF_HAND).isOf(ModItems.EFFECT_GEM_HASTE);

		boolean hasWaterBreathingGem =
				player.getStackInHand(Hand.MAIN_HAND).isOf(ModItems.EFFECT_GEM_WATER_BREATHING) ||
				player.getStackInHand(Hand.OFF_HAND).isOf(ModItems.EFFECT_GEM_WATER_BREATHING);

		boolean hasSlowFallingGem =
				player.getStackInHand(Hand.MAIN_HAND).isOf(ModItems.EFFECT_GEM_SLOW_FALLING) ||
				player.getStackInHand(Hand.OFF_HAND).isOf(ModItems.EFFECT_GEM_SLOW_FALLING);

		boolean hasStrengthGem =
				player.getStackInHand(Hand.MAIN_HAND).isOf(ModItems.EFFECT_GEM_STRENGTH) ||
				player.getStackInHand(Hand.OFF_HAND).isOf(ModItems.EFFECT_GEM_STRENGTH);

		boolean hasFireResistanceGem =
				player.getStackInHand(Hand.MAIN_HAND).isOf(ModItems.EFFECT_GEM_FIRE_RESISTANCE) ||
				player.getStackInHand(Hand.OFF_HAND).isOf(ModItems.EFFECT_GEM_FIRE_RESISTANCE);

		boolean hasJumpBoostGem =
				player.getStackInHand(Hand.MAIN_HAND).isOf(ModItems.EFFECT_GEM_JUMP_BOOST) ||
				player.getStackInHand(Hand.OFF_HAND).isOf(ModItems.EFFECT_GEM_JUMP_BOOST);

		boolean hasSpeedGem =
				player.getStackInHand(Hand.MAIN_HAND).isOf(ModItems.EFFECT_GEM_SPEED) ||
				player.getStackInHand(Hand.OFF_HAND).isOf(ModItems.EFFECT_GEM_SPEED);

		boolean hasPushGem =
				player.getStackInHand(Hand.MAIN_HAND).isOf(ModItems.EFFECT_GEM_PUSH) ||
				player.getStackInHand(Hand.OFF_HAND).isOf(ModItems.EFFECT_GEM_PUSH);

		if (hasHasteGem) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 10, 0, false, false));
		}
		if (hasWaterBreathingGem) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 5, 0, false, false));
		}
		if (hasSlowFallingGem) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 5, 0, false, false));
		}
		if (hasStrengthGem) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 5, 0, false, false));
		}
		if (hasFireResistanceGem) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 5, 0, false, false));
		}
		if (hasJumpBoostGem) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 5, 8, false, false));
		}
		if (hasSpeedGem) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 5, 0, false, false));
		}
		if (hasPushGem) {
			double angle = 0;
			for (int i = 0; i < 360; i += 10) {
				angle = Math.toRadians(i);
				double x = player.getX() + Math.cos(angle) * 1.5;
				double z = player.getZ() + Math.sin(angle) * 1.5;
				ServerWorld serverWorld = player.getServerWorld();
				serverWorld.spawnParticles(ModParticles.EFFECT_GEM_PUSH_PARTICLE, x, player.getY()+0.2, z, 1, 0.0, 0.0, 0.0, 0.0);
			}
			world.getEntitiesByClass(MobEntity.class,
					player.getBoundingBox().expand(1.5),
					Entity::isAlive
			).forEach(mob -> {
				if (mob.getType() == EntityType.BLAZE ||
					mob.getType() == EntityType.BOGGED ||
					mob.getType() == EntityType.BREEZE ||
					mob.getType() == EntityType.CAVE_SPIDER ||
					mob.getType() == EntityType.CREEPER ||
					mob.getType() == EntityType.DROWNED ||
					mob.getType() == EntityType.ELDER_GUARDIAN ||
					mob.getType() == EntityType.ENDER_DRAGON ||
					mob.getType() == EntityType.ENDERMAN ||
					mob.getType() == EntityType.ENDERMITE ||
					mob.getType() == EntityType.EVOKER ||
					mob.getType() == EntityType.GHAST ||
					mob.getType() == EntityType.GIANT ||
					mob.getType() == EntityType.GUARDIAN ||
					mob.getType() == EntityType.HOGLIN ||
					mob.getType() == EntityType.HUSK ||
					mob.getType() == EntityType.ILLUSIONER ||
					mob.getType() == EntityType.MAGMA_CUBE ||
					mob.getType() == EntityType.PHANTOM ||
					mob.getType() == EntityType.PIGLIN ||
					mob.getType() == EntityType.PIGLIN_BRUTE ||
					mob.getType() == EntityType.PILLAGER ||
					mob.getType() == EntityType.PUFFERFISH ||
					mob.getType() == EntityType.RAVAGER ||
					mob.getType() == EntityType.SHULKER ||
					mob.getType() == EntityType.SILVERFISH ||
					mob.getType() == EntityType.SKELETON ||
					mob.getType() == EntityType.SLIME ||
					mob.getType() == EntityType.SPIDER ||
					mob.getType() == EntityType.STRAY ||
					mob.getType() == EntityType.VEX ||
					mob.getType() == EntityType.VINDICATOR ||
					mob.getType() == EntityType.WARDEN ||
					mob.getType() == EntityType.WITCH ||
					mob.getType() == EntityType.WITHER ||
					mob.getType() == EntityType.WITHER_SKELETON ||
					mob.getType() == EntityType.ZOGLIN ||
					mob.getType() == EntityType.ZOMBIE ||
					mob.getType() == EntityType.ZOMBIE_VILLAGER) {
					Vec3d direction = mob.getPos().subtract(player.getPos()).normalize();
					mob.addVelocity(direction.x * 0.5, direction.y * 0.5+0.5, direction.z * 0.5);
				}
			});
		}
	}

	private void checkUnderwaterForItem(ServerPlayerEntity player) {
		if (player.isSubmergedInWater()) {
			giveItemWithChance(player, ModItems.EFFECT_GEM_WATER_BREATHING.getDefaultStack(), 0.00005);
		}
	}
	private void checkPlayerMovement(ServerPlayerEntity player) {
		if (player.isSprinting()) {
			giveItemWithChance(player, ModItems.EFFECT_GEM_SPEED.getDefaultStack(), 0.00001);
		}
		if (!player.isOnGround() && player.getVelocity().y > 0) {
			giveItemWithChance(player, ModItems.EFFECT_GEM_JUMP_BOOST.getDefaultStack(), 0.00001);
		}
	}
	private void giveItemWithChance(ServerPlayerEntity player, ItemStack item, double chance) {
		if (RANDOM.nextDouble() < chance) {
			player.giveItemStack(item.copy());
		}
	}

}