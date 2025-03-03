package de.pizzapost.minecraft_extra.block;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.block.custom.*;
import de.pizzapost.minecraft_extra.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block XP_STORAGE = registerBlock("xp_storage",
            new Block(AbstractBlock.Settings.create().strength(30F, 30F)
                    .sounds(BlockSoundGroup.METAL).mapColor(MapColor.LIME)
                    .instrument(NoteBlockInstrument.BASEDRUM)));
    public static final Block FLINT_BLOCK = registerBlock("flint_block",
            new FlintBlockBlockEntity(AbstractBlock.Settings.create().strength(1.5F, 6.0F)
                    .sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.SNARE)));
    public static final Block FLINT_BRICKS = registerBlock("flint_bricks",
            new Block(AbstractBlock.Settings.create().strength(1.5F, 6.0F)
                    .sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.SNARE)));
    public static final Block CHISELED_FLINT_BRICKS = registerBlock("chiseled_flint_bricks",
            new Block(AbstractBlock.Settings.create().strength(1.5F, 6.0F)
                    .sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.SNARE)));
    public static final Block FLINT_SLAB = registerBlock("flint_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(1.5F, 6.0F)
                    .sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.SNARE)));
    public static final Block FLINT_STAIR = registerBlock("flint_stair",
            new StairsBlock(FLINT_BLOCK.getDefaultState(), AbstractBlock.Settings.copyShallow(FLINT_BLOCK)));
    public static final Block FLINT_WALL = registerBlock("flint_wall",
            new WallBlock(AbstractBlock.Settings.copyShallow(FLINT_BLOCK)));
    public static final Block FLINT_BRICK_SLAB = registerBlock("flint_brick_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(1.5F, 6.0F)
                    .sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.SNARE)));
    public static final Block FLINT_BRICK_STAIR = registerBlock("flint_brick_stair",
            new StairsBlock(FLINT_BRICKS.getDefaultState(), AbstractBlock.Settings.copyShallow(FLINT_BRICKS)));
    public static final Block FLINT_BRICK_WALL = registerBlock("flint_brick_wall",
            new WallBlock(AbstractBlock.Settings.copyShallow(FLINT_BRICKS)));
    public static final Block EFFECT_FARMLAND = registerBlock("effect_farmland",
            new EffectFarmlandBlock(FabricBlockSettings.copyOf(Blocks.FARMLAND)));
    public static final Block ASH_LAYER = registerBlock("ash_layer",
            new AshBlock(AbstractBlock.Settings.create().mapColor(MapColor.WHITE).replaceable().notSolid()
                    .ticksRandomly().strength(0.1F).sounds(BlockSoundGroup.SAND).pistonBehavior(PistonBehavior.DESTROY)
                    .instrument(NoteBlockInstrument.GUITAR)
                    .blockVision((state, world, pos) -> (Integer)state.get(SnowBlock.LAYERS) >= 8)));
    public static final Block FEATHER_BLOCK = registerBlock("feather_block",
            new FeatherBlock(AbstractBlock.Settings.create().mapColor(MapColor.WHITE_GRAY).sounds(BlockSoundGroup.WOOL)
                    .strength(0.8F).instrument(NoteBlockInstrument.GUITAR)));
    public static final Block HONEY_BERRY_BUSH = registerBlock("honey_berry_bush",
            new HoneyBerryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final Block WHITE_PETALS = registerBlock("white_petals",
            new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS)));
    public static final Block LIGHT_GRAY_PETALS = registerBlock("light_gray_petals",
            new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS)));
    public static final Block GRAY_PETALS = registerBlock("gray_petals",
            new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS)));
    public static final Block BLACK_PETALS = registerBlock("black_petals",
            new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS)));
    public static final Block BROWN_PETALS = registerBlock("brown_petals",
            new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS)));
    public static final Block RED_PETALS = registerBlock("red_petals",
            new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS)));
    public static final Block ORANGE_PETALS = registerBlock("orange_petals",
            new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS)));
    public static final Block YELLOW_PETALS = registerBlock("yellow_petals",
            new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS)));
    public static final Block LIME_PETALS = registerBlock("lime_petals",
            new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS)));
    public static final Block GREEN_PETALS = registerBlock("green_petals",
            new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS)));
    public static final Block CYAN_PETALS = registerBlock("cyan_petals",
            new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS)));
    public static final Block LIGHT_BLUE_PETALS = registerBlock("light_blue_petals",
            new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS)));
    public static final Block BLUE_PETALS = registerBlock("blue_petals",
            new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS)));
    public static final Block PURPLE_PETALS = registerBlock("purple_petals",
            new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS)));
    public static final Block MAGENTA_PETALS = registerBlock("magenta_petals",
            new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS)));
    public static final Block PIZZAPOST_BLOCK = registerBlock("pizzapost_block",
            new Block(AbstractBlock.Settings.create().strength(-1F, -1F)
                    .sounds(BlockSoundGroup.METAL).mapColor(MapColor.LIME)
                    .instrument(NoteBlockInstrument.PLING)));

    public static void registerCompostables() {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.ASH, 0.1f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.HONEY_BERRIES, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.WHITE_PETALS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.LIGHT_GRAY_PETALS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.GRAY_PETALS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.BLACK_PETALS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.BROWN_PETALS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.RED_PETALS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.ORANGE_PETALS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.YELLOW_PETALS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.LIME_PETALS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.GREEN_PETALS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.CYAN_PETALS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.LIGHT_BLUE_PETALS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.BLUE_PETALS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.PURPLE_PETALS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.MAGENTA_PETALS, 0.3f);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MinecraftExtra.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(MinecraftExtra.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        MinecraftExtra.LOGGER.info("Registering Mod Blocks for " + MinecraftExtra.MOD_ID);
    }
}
