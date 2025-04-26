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
    public static final Block PIZZAPOST_BLOCK = registerBlock("pizzapost_block",
            new Block(AbstractBlock.Settings.create().strength(-1F, -1F)
                    .sounds(BlockSoundGroup.METAL).mapColor(MapColor.LIME)
                    .instrument(NoteBlockInstrument.PLING)));

    public static void registerCompostables() {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.ASH, 0.1f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.HONEY_BERRIES, 0.3f);
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
