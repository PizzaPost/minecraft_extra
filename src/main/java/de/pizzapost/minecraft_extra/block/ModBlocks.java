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
    public static final Block XP_STORAGE = registerBlock("xp_storage",
            properties -> new Block(properties.strength(30F, 30F)
                    .sounds(BlockSoundGroup.METAL).mapColor(MapColor.LIME)
                    .instrument(NoteBlockInstrument.BASEDRUM)));

    public static final Block FLINT_BLOCK = registerBlock("flint_block",
            properties -> new FlintBlockBlockEntity(properties.strength(1.5F, 6.0F)
                    .sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.SNARE)));

    public static final Block FLINT_BRICKS = registerBlock("flint_bricks",
            properties -> new Block(properties.strength(1.5F, 6.0F)
                    .sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.SNARE)));

    public static final Block CHISELED_FLINT_BRICKS = registerBlock("chiseled_flint_bricks",
            properties -> new Block(properties.strength(1.5F, 6.0F)
                    .sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.SNARE)));

    public static final Block FLINT_SLAB = registerBlock("flint_slab",
            properties -> new SlabBlock(properties.strength(1.5F, 6.0F)
                    .sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.SNARE)));

    public static final Block FLINT_STAIR = registerBlock("flint_stair",
            properties -> new StairsBlock(FLINT_BLOCK.getDefaultState(), properties.strength(1.5F, 6.0F)
                    .sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.SNARE)));

    public static final Block FLINT_WALL = registerBlock("flint_wall",
            properties -> new WallBlock(properties.strength(1.5F, 6.0F)
                    .sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.SNARE)));

    public static final Block FLINT_BRICK_SLAB = registerBlock("flint_brick_slab",
            properties -> new SlabBlock(properties.strength(1.5F, 6.0F)
                    .sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.SNARE)));

    public static final Block FLINT_BRICK_STAIR = registerBlock("flint_brick_stair",
            properties -> new StairsBlock(FLINT_BRICKS.getDefaultState(), properties.strength(1.5F, 6.0F)
                    .sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.SNARE)));

    public static final Block FLINT_BRICK_WALL = registerBlock("flint_brick_wall",
            properties -> new WallBlock(properties.strength(1.5F, 6.0F)
                    .sounds(BlockSoundGroup.GRAVEL).mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.SNARE)));

    public static final Block EFFECT_FARMLAND = registerBlock("effect_farmland",
            properties -> new EffectFarmlandBlock(properties.mapColor(MapColor.DIRT_BROWN).ticksRandomly().strength(0.6F).sounds(BlockSoundGroup.GRAVEL).blockVision(Blocks::always).suffocates(Blocks::always)));

    public static final Block ASH_LAYER = registerBlock("ash_layer",
            properties -> new AshBlock(properties.mapColor(MapColor.WHITE).replaceable().notSolid()
                    .ticksRandomly().strength(0.1F).sounds(BlockSoundGroup.SAND).pistonBehavior(PistonBehavior.DESTROY)
                    .instrument(NoteBlockInstrument.GUITAR)
                    .blockVision((state, world, pos) -> (Integer) state.get(SnowBlock.LAYERS) >= 8)));

    public static final Block FEATHER_BLOCK = registerBlock("feather_block",
            properties -> new FeatherBlock(properties.mapColor(MapColor.WHITE_GRAY).sounds(BlockSoundGroup.WOOL)
                    .strength(0.8F).instrument(NoteBlockInstrument.GUITAR)));

    public static final Block HONEY_BERRY_BUSH = registerBlock("honey_berry_bush",
            properties -> new HoneyBerryBushBlock(properties.mapColor(MapColor.DARK_GREEN).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block PIZZAPOST_BLOCK = registerBlock("pizzapost_block",
            properties -> new Block(properties.strength(-1F, -1F)
                    .sounds(BlockSoundGroup.METAL).mapColor(MapColor.LIME)
                    .instrument(NoteBlockInstrument.PLING)));

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
        Registry.register(Registries.ITEM, Identifier.of(MinecraftExtra.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MinecraftExtra.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        MinecraftExtra.LOGGER.info("Registering Mod Blocks for " + MinecraftExtra.MOD_ID);
    }
}