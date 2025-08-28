package de.pizzapost.minecraft_extra.block;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.block.custom.*;
import de.pizzapost.minecraft_extra.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

import java.util.function.Function;

public class ModBlocks {
    public static final Block XP_STORAGE = registerBlock("xp_storage", properties -> new XPStorage(properties.strength(30F, 30F).sounds(BlockSoundGroup.METAL).mapColor(MapColor.LIME).instrument(NoteBlockInstrument.BASEDRUM).nonOpaque()));

    public static final Block FLINT_BLOCK = registerBlock("flint_block", properties -> new FlintBlockBlockEntity(properties.strength(1.5F, 6.0F).sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.SNARE)));

    public static final Block FLINT_BRICKS = registerBlock("flint_bricks", properties -> new Block(properties.strength(1.5F, 6.0F).sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.SNARE)));

    public static final Block CHISELED_FLINT_BRICKS = registerBlock("chiseled_flint_bricks", properties -> new Block(properties.strength(1.5F, 6.0F).sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.SNARE)));

    public static final Block FLINT_SLAB = registerBlock("flint_slab", properties -> new SlabBlock(properties.strength(1.5F, 6.0F).sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.SNARE)));

    public static final Block FLINT_STAIR = registerBlock("flint_stair", properties -> new StairsBlock(FLINT_BLOCK.getDefaultState(), properties.strength(1.5F, 6.0F).sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.SNARE)));

    public static final Block FLINT_WALL = registerBlock("flint_wall", properties -> new WallBlock(properties.strength(1.5F, 6.0F).sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.SNARE)));

    public static final Block FLINT_BRICK_SLAB = registerBlock("flint_brick_slab", properties -> new SlabBlock(properties.strength(1.5F, 6.0F).sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.SNARE)));

    public static final Block FLINT_BRICK_STAIR = registerBlock("flint_brick_stair", properties -> new StairsBlock(FLINT_BRICKS.getDefaultState(), properties.strength(1.5F, 6.0F).sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.SNARE)));

    public static final Block FLINT_BRICK_WALL = registerBlock("flint_brick_wall", properties -> new WallBlock(properties.strength(1.5F, 6.0F).sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.SNARE)));

    public static final Block EFFECT_FARMLAND = registerBlock("effect_farmland", properties -> new EffectFarmlandBlock(properties.mapColor(MapColor.DIRT_BROWN).ticksRandomly().strength(0.6F).sounds(BlockSoundGroup.GRAVEL).blockVision(Blocks::always).suffocates(Blocks::always)));

    public static final Block ASH_LAYER = registerBlock("ash_layer", properties -> new AshBlock(properties.mapColor(MapColor.WHITE).replaceable().notSolid().ticksRandomly().strength(0.1F).sounds(BlockSoundGroup.SAND).pistonBehavior(PistonBehavior.DESTROY).instrument(NoteBlockInstrument.GUITAR).blockVision((state, world, pos) -> (Integer) state.get(SnowBlock.LAYERS) >= 8)));

    public static final Block FEATHER_BLOCK = registerBlock("feather_block", properties -> new FeatherBlock(properties.mapColor(MapColor.WHITE_GRAY).sounds(BlockSoundGroup.WOOL).strength(0.8F).instrument(NoteBlockInstrument.GUITAR)));

    public static final Block HONEY_BERRY_BUSH = registerBlock("honey_berry_bush", properties -> new HoneyBerryBushBlock(properties.mapColor(MapColor.DARK_GREEN).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block PIZZAPOST_BLOCK = registerBlock("pizzapost_block", properties -> new Block(properties.strength(-1F, -1F).sounds(BlockSoundGroup.METAL).mapColor(MapColor.LIME).instrument(NoteBlockInstrument.PLING)));

    public static final Block WHITE_WOOL_SLAB = registerBlock("white_wool_slab", properties -> new SlabBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.WHITE).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block WHITE_WOOL_STAIR = registerBlock("white_wool_stair", properties -> new StairsBlock(Blocks.WHITE_WOOL.getDefaultState(), properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.WHITE).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block WHITE_WOOL_WALL = registerBlock("white_wool_wall", properties -> new WallBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.WHITE).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block LIGHT_GRAY_WOOL_SLAB = registerBlock("light_gray_wool_slab", properties -> new SlabBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.LIGHT_GRAY).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block LIGHT_GRAY_WOOL_STAIR = registerBlock("light_gray_wool_stair", properties -> new StairsBlock(Blocks.LIGHT_GRAY_WOOL.getDefaultState(), properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.LIGHT_GRAY).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block LIGHT_GRAY_WOOL_WALL = registerBlock("light_gray_wool_wall", properties -> new WallBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.LIGHT_GRAY).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block GRAY_WOOL_SLAB = registerBlock("gray_wool_slab", properties -> new SlabBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.GRAY).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block GRAY_WOOL_STAIR = registerBlock("gray_wool_stair", properties -> new StairsBlock(Blocks.GRAY_WOOL.getDefaultState(), properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.GRAY).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block GRAY_WOOL_WALL = registerBlock("gray_wool_wall", properties -> new WallBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.GRAY).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block BLACK_WOOL_SLAB = registerBlock("black_wool_slab", properties -> new SlabBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block BLACK_WOOL_STAIR = registerBlock("black_wool_stair", properties -> new StairsBlock(Blocks.BLACK_WOOL.getDefaultState(), properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block BLACK_WOOL_WALL = registerBlock("black_wool_wall", properties -> new WallBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block BROWN_WOOL_SLAB = registerBlock("brown_wool_slab", properties -> new SlabBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.BROWN).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block BROWN_WOOL_STAIR = registerBlock("brown_wool_stair", properties -> new StairsBlock(Blocks.BROWN_WOOL.getDefaultState(), properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.BROWN).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block BROWN_WOOL_WALL = registerBlock("brown_wool_wall", properties -> new WallBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.BROWN).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block RED_WOOL_SLAB = registerBlock("red_wool_slab", properties -> new SlabBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.RED).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block RED_WOOL_STAIR = registerBlock("red_wool_stair", properties -> new StairsBlock(Blocks.RED_WOOL.getDefaultState(), properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.RED).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block RED_WOOL_WALL = registerBlock("red_wool_wall", properties -> new WallBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.RED).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block ORANGE_WOOL_SLAB = registerBlock("orange_wool_slab", properties -> new SlabBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block ORANGE_WOOL_STAIR = registerBlock("orange_wool_stair", properties -> new StairsBlock(Blocks.ORANGE_WOOL.getDefaultState(), properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block ORANGE_WOOL_WALL = registerBlock("orange_wool_wall", properties -> new WallBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block YELLOW_WOOL_SLAB = registerBlock("yellow_wool_slab", properties -> new SlabBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.YELLOW).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block YELLOW_WOOL_STAIR = registerBlock("yellow_wool_stair", properties -> new StairsBlock(Blocks.YELLOW_WOOL.getDefaultState(), properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.YELLOW).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block YELLOW_WOOL_WALL = registerBlock("yellow_wool_wall", properties -> new WallBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.YELLOW).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block LIME_WOOL_SLAB = registerBlock("lime_wool_slab", properties -> new SlabBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.LIME).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block LIME_WOOL_STAIR = registerBlock("lime_wool_stair", properties -> new StairsBlock(Blocks.LIME_WOOL.getDefaultState(), properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.LIME).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block LIME_WOOL_WALL = registerBlock("lime_wool_wall", properties -> new WallBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.LIME).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block GREEN_WOOL_SLAB = registerBlock("green_wool_slab", properties -> new SlabBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.GREEN).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block GREEN_WOOL_STAIR = registerBlock("green_wool_stair", properties -> new StairsBlock(Blocks.GREEN_WOOL.getDefaultState(), properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.GREEN).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block GREEN_WOOL_WALL = registerBlock("green_wool_wall", properties -> new WallBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.GREEN).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block CYAN_WOOL_SLAB = registerBlock("cyan_wool_slab", properties -> new SlabBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block CYAN_WOOL_STAIR = registerBlock("cyan_wool_stair", properties -> new StairsBlock(Blocks.CYAN_WOOL.getDefaultState(), properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block CYAN_WOOL_WALL = registerBlock("cyan_wool_wall", properties -> new WallBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block LIGHT_BLUE_WOOL_SLAB = registerBlock("light_blue_wool_slab", properties -> new SlabBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.LIGHT_BLUE).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block LIGHT_BLUE_WOOL_STAIR = registerBlock("light_blue_wool_stair", properties -> new StairsBlock(Blocks.LIGHT_BLUE_WOOL.getDefaultState(), properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.LIGHT_BLUE).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block LIGHT_BLUE_WOOL_WALL = registerBlock("light_blue_wool_wall", properties -> new WallBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.LIGHT_BLUE).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block BLUE_WOOL_SLAB = registerBlock("blue_wool_slab", properties -> new SlabBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.BLUE).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block BLUE_WOOL_STAIR = registerBlock("blue_wool_stair", properties -> new StairsBlock(Blocks.BLUE_WOOL.getDefaultState(), properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.BLUE).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block BLUE_WOOL_WALL = registerBlock("blue_wool_wall", properties -> new WallBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.BLUE).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block PURPLE_WOOL_SLAB = registerBlock("purple_wool_slab", properties -> new SlabBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block PURPLE_WOOL_STAIR = registerBlock("purple_wool_stair", properties -> new StairsBlock(Blocks.PURPLE_WOOL.getDefaultState(), properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block PURPLE_WOOL_WALL = registerBlock("purple_wool_wall", properties -> new WallBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block MAGENTA_WOOL_SLAB = registerBlock("magenta_wool_slab", properties -> new SlabBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.MAGENTA).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block MAGENTA_WOOL_STAIR = registerBlock("magenta_wool_stair", properties -> new StairsBlock(Blocks.MAGENTA_WOOL.getDefaultState(), properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.MAGENTA).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block MAGENTA_WOOL_WALL = registerBlock("magenta_wool_wall", properties -> new WallBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.MAGENTA).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block PINK_WOOL_SLAB = registerBlock("pink_wool_slab", properties -> new SlabBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.PINK).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block PINK_WOOL_STAIR = registerBlock("pink_wool_stair", properties -> new StairsBlock(Blocks.PINK_WOOL.getDefaultState(), properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.PINK).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block PINK_WOOL_WALL = registerBlock("pink_wool_wall", properties -> new WallBlock(properties.strength(0.8F).sounds(BlockSoundGroup.WOOL).mapColor(MapColor.PINK).instrument(NoteBlockInstrument.GUITAR).burnable()));
    public static final Block TERRACOTTA_SLAB = registerBlock("terracotta_slab", properties -> new SlabBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block TERRACOTTA_STAIR = registerBlock("terracotta_stair", properties -> new StairsBlock(Blocks.TERRACOTTA.getDefaultState(), properties.strength(1.25F, 4.2F).mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block TERRACOTTA_WALL = registerBlock("terracotta_wall", properties -> new WallBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block WHITE_TERRACOTTA_SLAB = registerBlock("white_terracotta_slab", properties -> new SlabBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block WHITE_TERRACOTTA_STAIR = registerBlock("white_terracotta_stair", properties -> new StairsBlock(Blocks.WHITE_TERRACOTTA.getDefaultState(), properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block WHITE_TERRACOTTA_WALL = registerBlock("white_terracotta_wall", properties -> new WallBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_GRAY_TERRACOTTA_SLAB = registerBlock("light_gray_terracotta_slab", properties -> new SlabBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_GRAY_TERRACOTTA_STAIR = registerBlock("light_gray_terracotta_stair", properties -> new StairsBlock(Blocks.LIGHT_GRAY_TERRACOTTA.getDefaultState(), properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_GRAY_TERRACOTTA_WALL = registerBlock("light_gray_terracotta_wall", properties -> new WallBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GRAY_TERRACOTTA_SLAB = registerBlock("gray_terracotta_slab", properties -> new SlabBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GRAY_TERRACOTTA_STAIR = registerBlock("gray_terracotta_stair", properties -> new StairsBlock(Blocks.GRAY_TERRACOTTA.getDefaultState(), properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GRAY_TERRACOTTA_WALL = registerBlock("gray_terracotta_wall", properties -> new WallBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLACK_TERRACOTTA_SLAB = registerBlock("black_terracotta_slab", properties -> new SlabBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLACK_TERRACOTTA_STAIR = registerBlock("black_terracotta_stair", properties -> new StairsBlock(Blocks.BLACK_TERRACOTTA.getDefaultState(), properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLACK_TERRACOTTA_WALL = registerBlock("black_terracotta_wall", properties -> new WallBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BROWN_TERRACOTTA_SLAB = registerBlock("brown_terracotta_slab", properties -> new SlabBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BROWN_TERRACOTTA_STAIR = registerBlock("brown_terracotta_stair", properties -> new StairsBlock(Blocks.BROWN_TERRACOTTA.getDefaultState(), properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BROWN_TERRACOTTA_WALL = registerBlock("brown_terracotta_wall", properties -> new WallBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block RED_TERRACOTTA_SLAB = registerBlock("red_terracotta_slab", properties -> new SlabBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block RED_TERRACOTTA_STAIR = registerBlock("red_terracotta_stair", properties -> new StairsBlock(Blocks.RED_TERRACOTTA.getDefaultState(), properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block RED_TERRACOTTA_WALL = registerBlock("red_terracotta_wall", properties -> new WallBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block ORANGE_TERRACOTTA_SLAB = registerBlock("orange_terracotta_slab", properties -> new SlabBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block ORANGE_TERRACOTTA_STAIR = registerBlock("orange_terracotta_stair", properties -> new StairsBlock(Blocks.ORANGE_TERRACOTTA.getDefaultState(), properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block ORANGE_TERRACOTTA_WALL = registerBlock("orange_terracotta_wall", properties -> new WallBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block YELLOW_TERRACOTTA_SLAB = registerBlock("yellow_terracotta_slab", properties -> new SlabBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block YELLOW_TERRACOTTA_STAIR = registerBlock("yellow_terracotta_stair", properties -> new StairsBlock(Blocks.YELLOW_TERRACOTTA.getDefaultState(), properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block YELLOW_TERRACOTTA_WALL = registerBlock("yellow_terracotta_wall", properties -> new WallBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIME_TERRACOTTA_SLAB = registerBlock("lime_terracotta_slab", properties -> new SlabBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_LIME).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIME_TERRACOTTA_STAIR = registerBlock("lime_terracotta_stair", properties -> new StairsBlock(Blocks.LIME_TERRACOTTA.getDefaultState(), properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_LIME).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIME_TERRACOTTA_WALL = registerBlock("lime_terracotta_wall", properties -> new WallBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_LIME).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GREEN_TERRACOTTA_SLAB = registerBlock("green_terracotta_slab", properties -> new SlabBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GREEN_TERRACOTTA_STAIR = registerBlock("green_terracotta_stair", properties -> new StairsBlock(Blocks.GREEN_TERRACOTTA.getDefaultState(), properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GREEN_TERRACOTTA_WALL = registerBlock("green_terracotta_wall", properties -> new WallBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block CYAN_TERRACOTTA_SLAB = registerBlock("cyan_terracotta_slab", properties -> new SlabBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block CYAN_TERRACOTTA_STAIR = registerBlock("cyan_terracotta_stair", properties -> new StairsBlock(Blocks.CYAN_TERRACOTTA.getDefaultState(), properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block CYAN_TERRACOTTA_WALL = registerBlock("cyan_terracotta_wall", properties -> new WallBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_BLUE_TERRACOTTA_SLAB = registerBlock("light_blue_terracotta_slab", properties -> new SlabBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_BLUE_TERRACOTTA_STAIR = registerBlock("light_blue_terracotta_stair", properties -> new StairsBlock(Blocks.LIGHT_BLUE_TERRACOTTA.getDefaultState(), properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_BLUE_TERRACOTTA_WALL = registerBlock("light_blue_terracotta_wall", properties -> new WallBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLUE_TERRACOTTA_SLAB = registerBlock("blue_terracotta_slab", properties -> new SlabBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLUE_TERRACOTTA_STAIR = registerBlock("blue_terracotta_stair", properties -> new StairsBlock(Blocks.BLUE_TERRACOTTA.getDefaultState(), properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLUE_TERRACOTTA_WALL = registerBlock("blue_terracotta_wall", properties -> new WallBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PURPLE_TERRACOTTA_SLAB = registerBlock("purple_terracotta_slab", properties -> new SlabBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PURPLE_TERRACOTTA_STAIR = registerBlock("purple_terracotta_stair", properties -> new StairsBlock(Blocks.PURPLE_TERRACOTTA.getDefaultState(), properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PURPLE_TERRACOTTA_WALL = registerBlock("purple_terracotta_wall", properties -> new WallBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block MAGENTA_TERRACOTTA_SLAB = registerBlock("magenta_terracotta_slab", properties -> new SlabBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block MAGENTA_TERRACOTTA_STAIR = registerBlock("magenta_terracotta_stair", properties -> new StairsBlock(Blocks.MAGENTA_TERRACOTTA.getDefaultState(), properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block MAGENTA_TERRACOTTA_WALL = registerBlock("magenta_terracotta_wall", properties -> new WallBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PINK_TERRACOTTA_SLAB = registerBlock("pink_terracotta_slab", properties -> new SlabBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PINK_TERRACOTTA_STAIR = registerBlock("pink_terracotta_stair", properties -> new StairsBlock(Blocks.PINK_TERRACOTTA.getDefaultState(), properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PINK_TERRACOTTA_WALL = registerBlock("pink_terracotta_wall", properties -> new WallBlock(properties.strength(1.25F, 4.2F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block WHITE_GLAZED_TERRACOTTA_SLAB = registerBlock("white_glazed_terracotta_slab", properties -> new SlabBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block WHITE_GLAZED_TERRACOTTA_STAIR = registerBlock("white_glazed_terracotta_stair", properties -> new StairsBlock(Blocks.WHITE_GLAZED_TERRACOTTA.getDefaultState(), properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block WHITE_GLAZED_TERRACOTTA_WALL = registerBlock("white_glazed_terracotta_wall", properties -> new WallBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_GRAY_GLAZED_TERRACOTTA_SLAB = registerBlock("light_gray_glazed_terracotta_slab", properties -> new SlabBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_GRAY_GLAZED_TERRACOTTA_STAIR = registerBlock("light_gray_glazed_terracotta_stair", properties -> new StairsBlock(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA.getDefaultState(), properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_GRAY_GLAZED_TERRACOTTA_WALL = registerBlock("light_gray_glazed_terracotta_wall", properties -> new WallBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GRAY_GLAZED_TERRACOTTA_SLAB = registerBlock("gray_glazed_terracotta_slab", properties -> new SlabBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GRAY_GLAZED_TERRACOTTA_STAIR = registerBlock("gray_glazed_terracotta_stair", properties -> new StairsBlock(Blocks.GRAY_GLAZED_TERRACOTTA.getDefaultState(), properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GRAY_GLAZED_TERRACOTTA_WALL = registerBlock("gray_glazed_terracotta_wall", properties -> new WallBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLACK_GLAZED_TERRACOTTA_SLAB = registerBlock("black_glazed_terracotta_slab", properties -> new SlabBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLACK_GLAZED_TERRACOTTA_STAIR = registerBlock("black_glazed_terracotta_stair", properties -> new StairsBlock(Blocks.BLACK_GLAZED_TERRACOTTA.getDefaultState(), properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLACK_GLAZED_TERRACOTTA_WALL = registerBlock("black_glazed_terracotta_wall", properties -> new WallBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BROWN_GLAZED_TERRACOTTA_SLAB = registerBlock("brown_glazed_terracotta_slab", properties -> new SlabBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BROWN_GLAZED_TERRACOTTA_STAIR = registerBlock("brown_glazed_terracotta_stair", properties -> new StairsBlock(Blocks.BROWN_GLAZED_TERRACOTTA.getDefaultState(), properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BROWN_GLAZED_TERRACOTTA_WALL = registerBlock("brown_glazed_terracotta_wall", properties -> new WallBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block RED_GLAZED_TERRACOTTA_SLAB = registerBlock("red_glazed_terracotta_slab", properties -> new SlabBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block RED_GLAZED_TERRACOTTA_STAIR = registerBlock("red_glazed_terracotta_stair", properties -> new StairsBlock(Blocks.RED_GLAZED_TERRACOTTA.getDefaultState(), properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block RED_GLAZED_TERRACOTTA_WALL = registerBlock("red_glazed_terracotta_wall", properties -> new WallBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block ORANGE_GLAZED_TERRACOTTA_SLAB = registerBlock("orange_glazed_terracotta_slab", properties -> new SlabBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block ORANGE_GLAZED_TERRACOTTA_STAIR = registerBlock("orange_glazed_terracotta_stair", properties -> new StairsBlock(Blocks.ORANGE_GLAZED_TERRACOTTA.getDefaultState(), properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block ORANGE_GLAZED_TERRACOTTA_WALL = registerBlock("orange_glazed_terracotta_wall", properties -> new WallBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block YELLOW_GLAZED_TERRACOTTA_SLAB = registerBlock("yellow_glazed_terracotta_slab", properties -> new SlabBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block YELLOW_GLAZED_TERRACOTTA_STAIR = registerBlock("yellow_glazed_terracotta_stair", properties -> new StairsBlock(Blocks.YELLOW_GLAZED_TERRACOTTA.getDefaultState(), properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block YELLOW_GLAZED_TERRACOTTA_WALL = registerBlock("yellow_glazed_terracotta_wall", properties -> new WallBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIME_GLAZED_TERRACOTTA_SLAB = registerBlock("lime_glazed_terracotta_slab", properties -> new SlabBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIME_GLAZED_TERRACOTTA_STAIR = registerBlock("lime_glazed_terracotta_stair", properties -> new StairsBlock(Blocks.LIME_GLAZED_TERRACOTTA.getDefaultState(), properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIME_GLAZED_TERRACOTTA_WALL = registerBlock("lime_glazed_terracotta_wall", properties -> new WallBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GREEN_GLAZED_TERRACOTTA_SLAB = registerBlock("green_glazed_terracotta_slab", properties -> new SlabBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GREEN_GLAZED_TERRACOTTA_STAIR = registerBlock("green_glazed_terracotta_stair", properties -> new StairsBlock(Blocks.GREEN_GLAZED_TERRACOTTA.getDefaultState(), properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GREEN_GLAZED_TERRACOTTA_WALL = registerBlock("green_glazed_terracotta_wall", properties -> new WallBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block CYAN_GLAZED_TERRACOTTA_SLAB = registerBlock("cyan_glazed_terracotta_slab", properties -> new SlabBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block CYAN_GLAZED_TERRACOTTA_STAIR = registerBlock("cyan_glazed_terracotta_stair", properties -> new StairsBlock(Blocks.CYAN_GLAZED_TERRACOTTA.getDefaultState(), properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block CYAN_GLAZED_TERRACOTTA_WALL = registerBlock("cyan_glazed_terracotta_wall", properties -> new WallBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_BLUE_GLAZED_TERRACOTTA_SLAB = registerBlock("light_blue_glazed_terracotta_slab", properties -> new SlabBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_BLUE_GLAZED_TERRACOTTA_STAIR = registerBlock("light_blue_glazed_terracotta_stair", properties -> new StairsBlock(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA.getDefaultState(), properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_BLUE_GLAZED_TERRACOTTA_WALL = registerBlock("light_blue_glazed_terracotta_wall", properties -> new WallBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLUE_GLAZED_TERRACOTTA_SLAB = registerBlock("blue_glazed_terracotta_slab", properties -> new SlabBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLUE_GLAZED_TERRACOTTA_STAIR = registerBlock("blue_glazed_terracotta_stair", properties -> new StairsBlock(Blocks.BLUE_GLAZED_TERRACOTTA.getDefaultState(), properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLUE_GLAZED_TERRACOTTA_WALL = registerBlock("blue_glazed_terracotta_wall", properties -> new WallBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PURPLE_GLAZED_TERRACOTTA_SLAB = registerBlock("purple_glazed_terracotta_slab", properties -> new SlabBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PURPLE_GLAZED_TERRACOTTA_STAIR = registerBlock("purple_glazed_terracotta_stair", properties -> new StairsBlock(Blocks.PURPLE_GLAZED_TERRACOTTA.getDefaultState(), properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PURPLE_GLAZED_TERRACOTTA_WALL = registerBlock("purple_glazed_terracotta_wall", properties -> new WallBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block MAGENTA_GLAZED_TERRACOTTA_SLAB = registerBlock("magenta_glazed_terracotta_slab", properties -> new SlabBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block MAGENTA_GLAZED_TERRACOTTA_STAIR = registerBlock("magenta_glazed_terracotta_stair", properties -> new StairsBlock(Blocks.MAGENTA_GLAZED_TERRACOTTA.getDefaultState(), properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block MAGENTA_GLAZED_TERRACOTTA_WALL = registerBlock("magenta_glazed_terracotta_wall", properties -> new WallBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PINK_GLAZED_TERRACOTTA_SLAB = registerBlock("pink_glazed_terracotta_slab", properties -> new SlabBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PINK_GLAZED_TERRACOTTA_STAIR = registerBlock("pink_glazed_terracotta_stair", properties -> new StairsBlock(Blocks.PINK_GLAZED_TERRACOTTA.getDefaultState(), properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PINK_GLAZED_TERRACOTTA_WALL = registerBlock("pink_glazed_terracotta_wall", properties -> new WallBlock(properties.strength(1.4F).mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block WHITE_CONCRETE_SLAB = registerBlock("white_concrete_slab", properties -> new SlabBlock(properties.strength(1.8F).mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block WHITE_CONCRETE_STAIR = registerBlock("white_concrete_stair", properties -> new StairsBlock(Blocks.WHITE_CONCRETE.getDefaultState(), properties.strength(1.8F).mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block WHITE_CONCRETE_WALL = registerBlock("white_concrete_wall", properties -> new WallBlock(properties.strength(1.8F).mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_GRAY_CONCRETE_SLAB = registerBlock("light_gray_concrete_slab", properties -> new SlabBlock(properties.strength(1.8F).mapColor(DyeColor.LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_GRAY_CONCRETE_STAIR = registerBlock("light_gray_concrete_stair", properties -> new StairsBlock(Blocks.LIGHT_GRAY_CONCRETE.getDefaultState(), properties.strength(1.8F).mapColor(DyeColor.LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_GRAY_CONCRETE_WALL = registerBlock("light_gray_concrete_wall", properties -> new WallBlock(properties.strength(1.8F).mapColor(DyeColor.LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GRAY_CONCRETE_SLAB = registerBlock("gray_concrete_slab", properties -> new SlabBlock(properties.strength(1.8F).mapColor(DyeColor.GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GRAY_CONCRETE_STAIR = registerBlock("gray_concrete_stair", properties -> new StairsBlock(Blocks.GRAY_CONCRETE.getDefaultState(), properties.strength(1.8F).mapColor(DyeColor.GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GRAY_CONCRETE_WALL = registerBlock("gray_concrete_wall", properties -> new WallBlock(properties.strength(1.8F).mapColor(DyeColor.GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLACK_CONCRETE_SLAB = registerBlock("black_concrete_slab", properties -> new SlabBlock(properties.strength(1.8F).mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLACK_CONCRETE_STAIR = registerBlock("black_concrete_stair", properties -> new StairsBlock(Blocks.BLACK_CONCRETE.getDefaultState(), properties.strength(1.8F).mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLACK_CONCRETE_WALL = registerBlock("black_concrete_wall", properties -> new WallBlock(properties.strength(1.8F).mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BROWN_CONCRETE_SLAB = registerBlock("brown_concrete_slab", properties -> new SlabBlock(properties.strength(1.8F).mapColor(DyeColor.BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BROWN_CONCRETE_STAIR = registerBlock("brown_concrete_stair", properties -> new StairsBlock(Blocks.BROWN_CONCRETE.getDefaultState(), properties.strength(1.8F).mapColor(DyeColor.BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BROWN_CONCRETE_WALL = registerBlock("brown_concrete_wall", properties -> new WallBlock(properties.strength(1.8F).mapColor(DyeColor.BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block RED_CONCRETE_SLAB = registerBlock("red_concrete_slab", properties -> new SlabBlock(properties.strength(1.8F).mapColor(DyeColor.RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block RED_CONCRETE_STAIR = registerBlock("red_concrete_stair", properties -> new StairsBlock(Blocks.RED_CONCRETE.getDefaultState(), properties.strength(1.8F).mapColor(DyeColor.RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block RED_CONCRETE_WALL = registerBlock("red_concrete_wall", properties -> new WallBlock(properties.strength(1.8F).mapColor(DyeColor.RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block ORANGE_CONCRETE_SLAB = registerBlock("orange_concrete_slab", properties -> new SlabBlock(properties.strength(1.8F).mapColor(DyeColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block ORANGE_CONCRETE_STAIR = registerBlock("orange_concrete_stair", properties -> new StairsBlock(Blocks.ORANGE_CONCRETE.getDefaultState(), properties.strength(1.8F).mapColor(DyeColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block ORANGE_CONCRETE_WALL = registerBlock("orange_concrete_wall", properties -> new WallBlock(properties.strength(1.8F).mapColor(DyeColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block YELLOW_CONCRETE_SLAB = registerBlock("yellow_concrete_slab", properties -> new SlabBlock(properties.strength(1.8F).mapColor(DyeColor.YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block YELLOW_CONCRETE_STAIR = registerBlock("yellow_concrete_stair", properties -> new StairsBlock(Blocks.YELLOW_CONCRETE.getDefaultState(), properties.strength(1.8F).mapColor(DyeColor.YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block YELLOW_CONCRETE_WALL = registerBlock("yellow_concrete_wall", properties -> new WallBlock(properties.strength(1.8F).mapColor(DyeColor.YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIME_CONCRETE_SLAB = registerBlock("lime_concrete_slab", properties -> new SlabBlock(properties.strength(1.8F).mapColor(DyeColor.LIME).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIME_CONCRETE_STAIR = registerBlock("lime_concrete_stair", properties -> new StairsBlock(Blocks.LIME_CONCRETE.getDefaultState(), properties.strength(1.8F).mapColor(DyeColor.LIME).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIME_CONCRETE_WALL = registerBlock("lime_concrete_wall", properties -> new WallBlock(properties.strength(1.8F).mapColor(DyeColor.LIME).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GREEN_CONCRETE_SLAB = registerBlock("green_concrete_slab", properties -> new SlabBlock(properties.strength(1.8F).mapColor(DyeColor.GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GREEN_CONCRETE_STAIR = registerBlock("green_concrete_stair", properties -> new StairsBlock(Blocks.GREEN_CONCRETE.getDefaultState(), properties.strength(1.8F).mapColor(DyeColor.GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block GREEN_CONCRETE_WALL = registerBlock("green_concrete_wall", properties -> new WallBlock(properties.strength(1.8F).mapColor(DyeColor.GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block CYAN_CONCRETE_SLAB = registerBlock("cyan_concrete_slab", properties -> new SlabBlock(properties.strength(1.8F).mapColor(DyeColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block CYAN_CONCRETE_STAIR = registerBlock("cyan_concrete_stair", properties -> new StairsBlock(Blocks.CYAN_CONCRETE.getDefaultState(), properties.strength(1.8F).mapColor(DyeColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block CYAN_CONCRETE_WALL = registerBlock("cyan_concrete_wall", properties -> new WallBlock(properties.strength(1.8F).mapColor(DyeColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_BLUE_CONCRETE_SLAB = registerBlock("light_blue_concrete_slab", properties -> new SlabBlock(properties.strength(1.8F).mapColor(DyeColor.LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_BLUE_CONCRETE_STAIR = registerBlock("light_blue_concrete_stair", properties -> new StairsBlock(Blocks.LIGHT_BLUE_CONCRETE.getDefaultState(), properties.strength(1.8F).mapColor(DyeColor.LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block LIGHT_BLUE_CONCRETE_WALL = registerBlock("light_blue_concrete_wall", properties -> new WallBlock(properties.strength(1.8F).mapColor(DyeColor.LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLUE_CONCRETE_SLAB = registerBlock("blue_concrete_slab", properties -> new SlabBlock(properties.strength(1.8F).mapColor(DyeColor.BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLUE_CONCRETE_STAIR = registerBlock("blue_concrete_stair", properties -> new StairsBlock(Blocks.BLUE_CONCRETE.getDefaultState(), properties.strength(1.8F).mapColor(DyeColor.BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block BLUE_CONCRETE_WALL = registerBlock("blue_concrete_wall", properties -> new WallBlock(properties.strength(1.8F).mapColor(DyeColor.BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PURPLE_CONCRETE_SLAB = registerBlock("purple_concrete_slab", properties -> new SlabBlock(properties.strength(1.8F).mapColor(DyeColor.PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PURPLE_CONCRETE_STAIR = registerBlock("purple_concrete_stair", properties -> new StairsBlock(Blocks.PURPLE_CONCRETE.getDefaultState(), properties.strength(1.8F).mapColor(DyeColor.PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PURPLE_CONCRETE_WALL = registerBlock("purple_concrete_wall", properties -> new WallBlock(properties.strength(1.8F).mapColor(DyeColor.PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block MAGENTA_CONCRETE_SLAB = registerBlock("magenta_concrete_slab", properties -> new SlabBlock(properties.strength(1.8F).mapColor(DyeColor.MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block MAGENTA_CONCRETE_STAIR = registerBlock("magenta_concrete_stair", properties -> new StairsBlock(Blocks.MAGENTA_CONCRETE.getDefaultState(), properties.strength(1.8F).mapColor(DyeColor.MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block MAGENTA_CONCRETE_WALL = registerBlock("magenta_concrete_wall", properties -> new WallBlock(properties.strength(1.8F).mapColor(DyeColor.MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PINK_CONCRETE_SLAB = registerBlock("pink_concrete_slab", properties -> new SlabBlock(properties.strength(1.8F).mapColor(DyeColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PINK_CONCRETE_STAIR = registerBlock("pink_concrete_stair", properties -> new StairsBlock(Blocks.PINK_CONCRETE.getDefaultState(), properties.strength(1.8F).mapColor(DyeColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block PINK_CONCRETE_WALL = registerBlock("pink_concrete_wall", properties -> new WallBlock(properties.strength(1.8F).mapColor(DyeColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()));
    public static final Block WHITE_CONCRETE_POWDER_SLAB = registerBlock("white_concrete_powder_slab", properties -> new ConcretePowderSlabBlock(ModBlocks.WHITE_CONCRETE_SLAB, properties.mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block WHITE_CONCRETE_POWDER_STAIR = registerBlock("white_concrete_powder_stair", properties -> new ConcretePowderStairsBlock(ModBlocks.WHITE_CONCRETE_STAIR.getDefaultState(), ModBlocks.WHITE_CONCRETE_STAIR, properties.mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block WHITE_CONCRETE_POWDER_WALL = registerBlock("white_concrete_powder_wall", props -> new ConcretePowderWallBlock(ModBlocks.WHITE_CONCRETE_WALL, props.mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block LIGHT_GRAY_CONCRETE_POWDER_SLAB = registerBlock("light_gray_concrete_powder_slab", properties -> new ConcretePowderSlabBlock(ModBlocks.LIGHT_GRAY_CONCRETE_SLAB, properties.mapColor(DyeColor.LIGHT_GRAY).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block LIGHT_GRAY_CONCRETE_POWDER_STAIR = registerBlock("light_gray_concrete_powder_stair", properties -> new ConcretePowderStairsBlock(ModBlocks.LIGHT_GRAY_CONCRETE_STAIR.getDefaultState(), ModBlocks.LIGHT_GRAY_CONCRETE_STAIR, properties.mapColor(DyeColor.LIGHT_GRAY).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block LIGHT_GRAY_CONCRETE_POWDER_WALL = registerBlock("light_gray_concrete_powder_wall", props -> new ConcretePowderWallBlock(ModBlocks.LIGHT_GRAY_CONCRETE_WALL, props.mapColor(DyeColor.LIGHT_GRAY).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block GRAY_CONCRETE_POWDER_SLAB = registerBlock("gray_concrete_powder_slab", properties -> new ConcretePowderSlabBlock(ModBlocks.GRAY_CONCRETE_SLAB, properties.mapColor(DyeColor.GRAY).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block GRAY_CONCRETE_POWDER_STAIR = registerBlock("gray_concrete_powder_stair", properties -> new ConcretePowderStairsBlock(ModBlocks.GRAY_CONCRETE_STAIR.getDefaultState(), ModBlocks.GRAY_CONCRETE_STAIR, properties.mapColor(DyeColor.GRAY).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block GRAY_CONCRETE_POWDER_WALL = registerBlock("gray_concrete_powder_wall", props -> new ConcretePowderWallBlock(ModBlocks.GRAY_CONCRETE_WALL, props.mapColor(DyeColor.GRAY).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block BLACK_CONCRETE_POWDER_SLAB = registerBlock("black_concrete_powder_slab", properties -> new ConcretePowderSlabBlock(ModBlocks.BLACK_CONCRETE_SLAB, properties.mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block BLACK_CONCRETE_POWDER_STAIR = registerBlock("black_concrete_powder_stair", properties -> new ConcretePowderStairsBlock(ModBlocks.BLACK_CONCRETE_STAIR.getDefaultState(), ModBlocks.BLACK_CONCRETE_STAIR, properties.mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block BLACK_CONCRETE_POWDER_WALL = registerBlock("black_concrete_powder_wall", props -> new ConcretePowderWallBlock(ModBlocks.BLACK_CONCRETE_WALL, props.mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block BROWN_CONCRETE_POWDER_SLAB = registerBlock("brown_concrete_powder_slab", properties -> new ConcretePowderSlabBlock(ModBlocks.BROWN_CONCRETE_SLAB, properties.mapColor(DyeColor.BROWN).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block BROWN_CONCRETE_POWDER_STAIR = registerBlock("brown_concrete_powder_stair", properties -> new ConcretePowderStairsBlock(ModBlocks.BROWN_CONCRETE_STAIR.getDefaultState(), ModBlocks.BROWN_CONCRETE_STAIR, properties.mapColor(DyeColor.BROWN).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block BROWN_CONCRETE_POWDER_WALL = registerBlock("brown_concrete_powder_wall", props -> new ConcretePowderWallBlock(ModBlocks.BROWN_CONCRETE_WALL, props.mapColor(DyeColor.BROWN).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block RED_CONCRETE_POWDER_SLAB = registerBlock("red_concrete_powder_slab", properties -> new ConcretePowderSlabBlock(ModBlocks.RED_CONCRETE_SLAB, properties.mapColor(DyeColor.RED).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block RED_CONCRETE_POWDER_STAIR = registerBlock("red_concrete_powder_stair", properties -> new ConcretePowderStairsBlock(ModBlocks.RED_CONCRETE_STAIR.getDefaultState(), ModBlocks.RED_CONCRETE_STAIR, properties.mapColor(DyeColor.RED).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block RED_CONCRETE_POWDER_WALL = registerBlock("red_concrete_powder_wall", props -> new ConcretePowderWallBlock(ModBlocks.RED_CONCRETE_WALL, props.mapColor(DyeColor.RED).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block ORANGE_CONCRETE_POWDER_SLAB = registerBlock("orange_concrete_powder_slab", properties -> new ConcretePowderSlabBlock(ModBlocks.ORANGE_CONCRETE_SLAB, properties.mapColor(DyeColor.ORANGE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block ORANGE_CONCRETE_POWDER_STAIR = registerBlock("orange_concrete_powder_stair", properties -> new ConcretePowderStairsBlock(ModBlocks.ORANGE_CONCRETE_STAIR.getDefaultState(), ModBlocks.ORANGE_CONCRETE_STAIR, properties.mapColor(DyeColor.ORANGE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block ORANGE_CONCRETE_POWDER_WALL = registerBlock("orange_concrete_powder_wall", props -> new ConcretePowderWallBlock(ModBlocks.ORANGE_CONCRETE_WALL, props.mapColor(DyeColor.ORANGE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block YELLOW_CONCRETE_POWDER_SLAB = registerBlock("yellow_concrete_powder_slab", properties -> new ConcretePowderSlabBlock(ModBlocks.YELLOW_CONCRETE_SLAB, properties.mapColor(DyeColor.YELLOW).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block YELLOW_CONCRETE_POWDER_STAIR = registerBlock("yellow_concrete_powder_stair", properties -> new ConcretePowderStairsBlock(ModBlocks.YELLOW_CONCRETE_STAIR.getDefaultState(), ModBlocks.YELLOW_CONCRETE_STAIR, properties.mapColor(DyeColor.YELLOW).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block YELLOW_CONCRETE_POWDER_WALL = registerBlock("yellow_concrete_powder_wall", props -> new ConcretePowderWallBlock(ModBlocks.YELLOW_CONCRETE_WALL, props.mapColor(DyeColor.YELLOW).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block LIME_CONCRETE_POWDER_SLAB = registerBlock("lime_concrete_powder_slab", properties -> new ConcretePowderSlabBlock(ModBlocks.LIME_CONCRETE_SLAB, properties.mapColor(DyeColor.LIME).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block LIME_CONCRETE_POWDER_STAIR = registerBlock("lime_concrete_powder_stair", properties -> new ConcretePowderStairsBlock(ModBlocks.LIME_CONCRETE_STAIR.getDefaultState(), ModBlocks.LIME_CONCRETE_STAIR, properties.mapColor(DyeColor.LIME).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block LIME_CONCRETE_POWDER_WALL = registerBlock("lime_concrete_powder_wall", props -> new ConcretePowderWallBlock(ModBlocks.LIME_CONCRETE_WALL, props.mapColor(DyeColor.LIME).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block GREEN_CONCRETE_POWDER_SLAB = registerBlock("green_concrete_powder_slab", properties -> new ConcretePowderSlabBlock(ModBlocks.GREEN_CONCRETE_SLAB, properties.mapColor(DyeColor.GREEN).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block GREEN_CONCRETE_POWDER_STAIR = registerBlock("green_concrete_powder_stair", properties -> new ConcretePowderStairsBlock(ModBlocks.GREEN_CONCRETE_STAIR.getDefaultState(), ModBlocks.GREEN_CONCRETE_STAIR, properties.mapColor(DyeColor.GREEN).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block GREEN_CONCRETE_POWDER_WALL = registerBlock("green_concrete_powder_wall", props -> new ConcretePowderWallBlock(ModBlocks.GREEN_CONCRETE_WALL, props.mapColor(DyeColor.GREEN).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block CYAN_CONCRETE_POWDER_SLAB = registerBlock("cyan_concrete_powder_slab", properties -> new ConcretePowderSlabBlock(ModBlocks.CYAN_CONCRETE_SLAB, properties.mapColor(DyeColor.CYAN).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block CYAN_CONCRETE_POWDER_STAIR = registerBlock("cyan_concrete_powder_stair", properties -> new ConcretePowderStairsBlock(ModBlocks.CYAN_CONCRETE_STAIR.getDefaultState(), ModBlocks.CYAN_CONCRETE_STAIR, properties.mapColor(DyeColor.CYAN).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block CYAN_CONCRETE_POWDER_WALL = registerBlock("cyan_concrete_powder_wall", props -> new ConcretePowderWallBlock(ModBlocks.CYAN_CONCRETE_WALL, props.mapColor(DyeColor.CYAN).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block LIGHT_BLUE_CONCRETE_POWDER_SLAB = registerBlock("light_blue_concrete_powder_slab", properties -> new ConcretePowderSlabBlock(ModBlocks.LIGHT_BLUE_CONCRETE_SLAB, properties.mapColor(DyeColor.LIGHT_BLUE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block LIGHT_BLUE_CONCRETE_POWDER_STAIR = registerBlock("light_blue_concrete_powder_stair", properties -> new ConcretePowderStairsBlock(ModBlocks.LIGHT_BLUE_CONCRETE_STAIR.getDefaultState(), ModBlocks.LIGHT_BLUE_CONCRETE_STAIR, properties.mapColor(DyeColor.LIGHT_BLUE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block LIGHT_BLUE_CONCRETE_POWDER_WALL = registerBlock("light_blue_concrete_powder_wall", props -> new ConcretePowderWallBlock(ModBlocks.LIGHT_BLUE_CONCRETE_WALL, props.mapColor(DyeColor.LIGHT_BLUE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block BLUE_CONCRETE_POWDER_SLAB = registerBlock("blue_concrete_powder_slab", properties -> new ConcretePowderSlabBlock(ModBlocks.BLUE_CONCRETE_SLAB, properties.mapColor(DyeColor.BLUE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block BLUE_CONCRETE_POWDER_STAIR = registerBlock("blue_concrete_powder_stair", properties -> new ConcretePowderStairsBlock(ModBlocks.BLUE_CONCRETE_STAIR.getDefaultState(), ModBlocks.BLUE_CONCRETE_STAIR, properties.mapColor(DyeColor.BLUE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block BLUE_CONCRETE_POWDER_WALL = registerBlock("blue_concrete_powder_wall", props -> new ConcretePowderWallBlock(ModBlocks.BLUE_CONCRETE_WALL, props.mapColor(DyeColor.BLUE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block PURPLE_CONCRETE_POWDER_SLAB = registerBlock("purple_concrete_powder_slab", properties -> new ConcretePowderSlabBlock(ModBlocks.PURPLE_CONCRETE_SLAB, properties.mapColor(DyeColor.PURPLE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block PURPLE_CONCRETE_POWDER_STAIR = registerBlock("purple_concrete_powder_stair", properties -> new ConcretePowderStairsBlock(ModBlocks.PURPLE_CONCRETE_STAIR.getDefaultState(), ModBlocks.PURPLE_CONCRETE_STAIR, properties.mapColor(DyeColor.PURPLE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block PURPLE_CONCRETE_POWDER_WALL = registerBlock("purple_concrete_powder_wall", props -> new ConcretePowderWallBlock(ModBlocks.PURPLE_CONCRETE_WALL, props.mapColor(DyeColor.PURPLE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block MAGENTA_CONCRETE_POWDER_SLAB = registerBlock("magenta_concrete_powder_slab", properties -> new ConcretePowderSlabBlock(ModBlocks.MAGENTA_CONCRETE_SLAB, properties.mapColor(DyeColor.MAGENTA).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block MAGENTA_CONCRETE_POWDER_STAIR = registerBlock("magenta_concrete_powder_stair", properties -> new ConcretePowderStairsBlock(ModBlocks.MAGENTA_CONCRETE_STAIR.getDefaultState(), ModBlocks.MAGENTA_CONCRETE_STAIR, properties.mapColor(DyeColor.MAGENTA).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block MAGENTA_CONCRETE_POWDER_WALL = registerBlock("magenta_concrete_powder_wall", props -> new ConcretePowderWallBlock(ModBlocks.MAGENTA_CONCRETE_WALL, props.mapColor(DyeColor.MAGENTA).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block PINK_CONCRETE_POWDER_SLAB = registerBlock("pink_concrete_powder_slab", properties -> new ConcretePowderSlabBlock(ModBlocks.PINK_CONCRETE_SLAB, properties.mapColor(DyeColor.PINK).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block PINK_CONCRETE_POWDER_STAIR = registerBlock("pink_concrete_powder_stair", properties -> new ConcretePowderStairsBlock(ModBlocks.PINK_CONCRETE_STAIR.getDefaultState(), ModBlocks.PINK_CONCRETE_STAIR, properties.mapColor(DyeColor.PINK).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block PINK_CONCRETE_POWDER_WALL = registerBlock("pink_concrete_powder_wall", props -> new ConcretePowderWallBlock(ModBlocks.PINK_CONCRETE_WALL, props.mapColor(DyeColor.PINK).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND)));

    public static boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> entityType) {
        return false;
    }

    private static boolean never(BlockState blockState, BlockView blockView, BlockPos blockPos) {
        return false;
    }


    public static final Block GLASS_SLAB = registerBlock("glass_slab", properties -> new GlassSlabBlock(DyeColor.WHITE, properties.strength(0.3F).nonOpaque().solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.WHITE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block GLASS_STAIR = registerBlock("glass_stair", properties -> new GlassStairsBlock(DyeColor.RED, Blocks.GLASS.getDefaultState(), properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.WHITE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block GLASS_WALL = registerBlock("glass_wall", properties -> new GlassWallBlock(DyeColor.WHITE, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.WHITE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block WHITE_STAINED_GLASS_SLAB = registerBlock("white_stained_glass_slab", properties -> new GlassSlabBlock(DyeColor.WHITE, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.WHITE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block WHITE_STAINED_GLASS_STAIR = registerBlock("white_stained_glass_stair", properties -> new GlassStairsBlock(DyeColor.WHITE, Blocks.WHITE_STAINED_GLASS.getDefaultState(), properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.WHITE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block WHITE_STAINED_GLASS_WALL = registerBlock("white_stained_glass_wall", properties -> new GlassWallBlock(DyeColor.WHITE, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.WHITE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block LIGHT_GRAY_STAINED_GLASS_SLAB = registerBlock("light_gray_stained_glass_slab", properties -> new GlassSlabBlock(DyeColor.LIGHT_GRAY, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.LIGHT_GRAY).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block LIGHT_GRAY_STAINED_GLASS_STAIR = registerBlock("light_gray_stained_glass_stair", properties -> new GlassStairsBlock(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_STAINED_GLASS.getDefaultState(), properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.LIGHT_GRAY).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block LIGHT_GRAY_STAINED_GLASS_WALL = registerBlock("light_gray_stained_glass_wall", properties -> new GlassWallBlock(DyeColor.LIGHT_GRAY, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.LIGHT_GRAY).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block GRAY_STAINED_GLASS_SLAB = registerBlock("gray_stained_glass_slab", properties -> new GlassSlabBlock(DyeColor.GRAY, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.GRAY).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block GRAY_STAINED_GLASS_STAIR = registerBlock("gray_stained_glass_stair", properties -> new GlassStairsBlock(DyeColor.GRAY, Blocks.GRAY_STAINED_GLASS.getDefaultState(), properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.GRAY).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block GRAY_STAINED_GLASS_WALL = registerBlock("gray_stained_glass_wall", properties -> new GlassWallBlock(DyeColor.GRAY, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.GRAY).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block BLACK_STAINED_GLASS_SLAB = registerBlock("black_stained_glass_slab", properties -> new GlassSlabBlock(DyeColor.BLACK, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block BLACK_STAINED_GLASS_STAIR = registerBlock("black_stained_glass_stair", properties -> new GlassStairsBlock(DyeColor.BLACK, Blocks.BLACK_STAINED_GLASS.getDefaultState(), properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block BLACK_STAINED_GLASS_WALL = registerBlock("black_stained_glass_wall", properties -> new GlassWallBlock(DyeColor.BLACK, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block BROWN_STAINED_GLASS_SLAB = registerBlock("brown_stained_glass_slab", properties -> new GlassSlabBlock(DyeColor.BROWN, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.BROWN).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block BROWN_STAINED_GLASS_STAIR = registerBlock("brown_stained_glass_stair", properties -> new GlassStairsBlock(DyeColor.BROWN, Blocks.BROWN_STAINED_GLASS.getDefaultState(), properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.BROWN).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block BROWN_STAINED_GLASS_WALL = registerBlock("brown_stained_glass_wall", properties -> new GlassWallBlock(DyeColor.BROWN, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.BROWN).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block RED_STAINED_GLASS_SLAB = registerBlock("red_stained_glass_slab", properties -> new GlassSlabBlock(DyeColor.RED, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.RED).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block RED_STAINED_GLASS_STAIR = registerBlock("red_stained_glass_stair", properties -> new GlassStairsBlock(DyeColor.RED, Blocks.RED_STAINED_GLASS.getDefaultState(), properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.RED).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block RED_STAINED_GLASS_WALL = registerBlock("red_stained_glass_wall", properties -> new GlassWallBlock(DyeColor.RED, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.RED).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block ORANGE_STAINED_GLASS_SLAB = registerBlock("orange_stained_glass_slab", properties -> new GlassSlabBlock(DyeColor.ORANGE, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block ORANGE_STAINED_GLASS_STAIR = registerBlock("orange_stained_glass_stair", properties -> new GlassStairsBlock(DyeColor.ORANGE, Blocks.ORANGE_STAINED_GLASS.getDefaultState(), properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block ORANGE_STAINED_GLASS_WALL = registerBlock("orange_stained_glass_wall", properties -> new GlassWallBlock(DyeColor.ORANGE, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block YELLOW_STAINED_GLASS_SLAB = registerBlock("yellow_stained_glass_slab", properties -> new GlassSlabBlock(DyeColor.YELLOW, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.YELLOW).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block YELLOW_STAINED_GLASS_STAIR = registerBlock("yellow_stained_glass_stair", properties -> new GlassStairsBlock(DyeColor.YELLOW, Blocks.YELLOW_STAINED_GLASS.getDefaultState(), properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.YELLOW).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block YELLOW_STAINED_GLASS_WALL = registerBlock("yellow_stained_glass_wall", properties -> new GlassWallBlock(DyeColor.YELLOW, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.YELLOW).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block LIME_STAINED_GLASS_SLAB = registerBlock("lime_stained_glass_slab", properties -> new GlassSlabBlock(DyeColor.LIME, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.LIME).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block LIME_STAINED_GLASS_STAIR = registerBlock("lime_stained_glass_stair", properties -> new GlassStairsBlock(DyeColor.LIME, Blocks.LIME_STAINED_GLASS.getDefaultState(), properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.LIME).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block LIME_STAINED_GLASS_WALL = registerBlock("lime_stained_glass_wall", properties -> new GlassWallBlock(DyeColor.LIME, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.LIME).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block GREEN_STAINED_GLASS_SLAB = registerBlock("green_stained_glass_slab", properties -> new GlassSlabBlock(DyeColor.GREEN, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.GREEN).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block GREEN_STAINED_GLASS_STAIR = registerBlock("green_stained_glass_stair", properties -> new GlassStairsBlock(DyeColor.GREEN, Blocks.GREEN_STAINED_GLASS.getDefaultState(), properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.GREEN).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block GREEN_STAINED_GLASS_WALL = registerBlock("green_stained_glass_wall", properties -> new GlassWallBlock(DyeColor.GREEN, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.GREEN).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block CYAN_STAINED_GLASS_SLAB = registerBlock("cyan_stained_glass_slab", properties -> new GlassSlabBlock(DyeColor.CYAN, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block CYAN_STAINED_GLASS_STAIR = registerBlock("cyan_stained_glass_stair", properties -> new GlassStairsBlock(DyeColor.CYAN, Blocks.CYAN_STAINED_GLASS.getDefaultState(), properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block CYAN_STAINED_GLASS_WALL = registerBlock("cyan_stained_glass_wall", properties -> new GlassWallBlock(DyeColor.CYAN, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block LIGHT_BLUE_STAINED_GLASS_SLAB = registerBlock("light_blue_stained_glass_slab", properties -> new GlassSlabBlock(DyeColor.LIGHT_BLUE, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.LIGHT_BLUE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block LIGHT_BLUE_STAINED_GLASS_STAIR = registerBlock("light_blue_stained_glass_stair", properties -> new GlassStairsBlock(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_STAINED_GLASS.getDefaultState(), properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.LIGHT_BLUE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block LIGHT_BLUE_STAINED_GLASS_WALL = registerBlock("light_blue_stained_glass_wall", properties -> new GlassWallBlock(DyeColor.LIGHT_BLUE, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.LIGHT_BLUE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block BLUE_STAINED_GLASS_SLAB = registerBlock("blue_stained_glass_slab", properties -> new GlassSlabBlock(DyeColor.BLUE, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.BLUE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block BLUE_STAINED_GLASS_STAIR = registerBlock("blue_stained_glass_stair", properties -> new GlassStairsBlock(DyeColor.BLUE, Blocks.BLUE_STAINED_GLASS.getDefaultState(), properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.BLUE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block BLUE_STAINED_GLASS_WALL = registerBlock("blue_stained_glass_wall", properties -> new GlassWallBlock(DyeColor.BLUE, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.BLUE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block PURPLE_STAINED_GLASS_SLAB = registerBlock("purple_stained_glass_slab", properties -> new GlassSlabBlock(DyeColor.PURPLE, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block PURPLE_STAINED_GLASS_STAIR = registerBlock("purple_stained_glass_stair", properties -> new GlassStairsBlock(DyeColor.PURPLE, Blocks.PURPLE_STAINED_GLASS.getDefaultState(), properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block PURPLE_STAINED_GLASS_WALL = registerBlock("purple_stained_glass_wall", properties -> new GlassWallBlock(DyeColor.PURPLE, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.PURPLE).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block MAGENTA_STAINED_GLASS_SLAB = registerBlock("magenta_stained_glass_slab", properties -> new GlassSlabBlock(DyeColor.MAGENTA, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.MAGENTA).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block MAGENTA_STAINED_GLASS_STAIR = registerBlock("magenta_stained_glass_stair", properties -> new GlassStairsBlock(DyeColor.MAGENTA, Blocks.MAGENTA_STAINED_GLASS.getDefaultState(), properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.MAGENTA).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block MAGENTA_STAINED_GLASS_WALL = registerBlock("magenta_stained_glass_wall", properties -> new GlassWallBlock(DyeColor.MAGENTA, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.MAGENTA).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block PINK_STAINED_GLASS_SLAB = registerBlock("pink_stained_glass_slab", properties -> new GlassSlabBlock(DyeColor.PINK, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.PINK).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block PINK_STAINED_GLASS_STAIR = registerBlock("pink_stained_glass_stair", properties -> new GlassStairsBlock(DyeColor.PINK, Blocks.PINK_STAINED_GLASS.getDefaultState(), properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.PINK).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });
    public static final Block PINK_STAINED_GLASS_WALL = registerBlock("pink_stained_glass_wall", properties -> new GlassWallBlock(DyeColor.PINK, properties.strength(0.3F).nonOpaque().allowsSpawning(ModBlocks::never).solidBlock(ModBlocks::never).suffocates(ModBlocks::never).blockVision(ModBlocks::never).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.PINK).instrument(NoteBlockInstrument.HAT)) {
        @Override
        public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
            if (stateFrom.isOf(this)) return true;
            if (stateFrom.isOf(Blocks.GLASS)) return true;
            return super.isSideInvisible(state, stateFrom, direction);
        }
    });

    public static void registerCompostables() {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.ASH, 0.1f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.HONEY_BERRIES, 0.3f);
    }

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MinecraftExtra.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(MinecraftExtra.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(MinecraftExtra.MOD_ID, name), new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MinecraftExtra.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        MinecraftExtra.LOGGER.info("Registering Mod Blocks for " + MinecraftExtra.MOD_ID);
    }
}