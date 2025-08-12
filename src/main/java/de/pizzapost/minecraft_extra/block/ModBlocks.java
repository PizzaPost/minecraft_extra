package de.pizzapost.minecraft_extra.block;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.block.custom.*;
import de.pizzapost.minecraft_extra.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

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