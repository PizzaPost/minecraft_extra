package de.pizzapost.minecraft_extra.datagen;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.block.ModBlocks;
import de.pizzapost.minecraft_extra.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.StonecuttingRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                String[] baseNames = {"WHITE_TERRACOTTA", "LIGHT_GRAY_TERRACOTTA", "GRAY_TERRACOTTA", "BLACK_TERRACOTTA", "BROWN_TERRACOTTA", "RED_TERRACOTTA", "ORANGE_TERRACOTTA", "YELLOW_TERRACOTTA", "LIME_TERRACOTTA", "GREEN_TERRACOTTA", "CYAN_TERRACOTTA", "LIGHT_BLUE_TERRACOTTA", "BLUE_TERRACOTTA", "PURPLE_TERRACOTTA", "MAGENTA_TERRACOTTA", "PINK_TERRACOTTA"};
                for (String name : baseNames) {
                    try {
                        Block slab = (Block) ModBlocks.class.getField(name + "_SLAB").get(null);
                        Block stairs = (Block) ModBlocks.class.getField(name + "_STAIR").get(null);
                        Block wall = (Block) ModBlocks.class.getField(name + "_WALL").get(null);
                        String glazedBase = name.replace("_TERRACOTTA", "_GLAZED_TERRACOTTA");
                        Block glazedSlab = (Block) ModBlocks.class.getField(glazedBase + "_SLAB").get(null);
                        Block glazedStair = (Block) ModBlocks.class.getField(glazedBase + "_STAIR").get(null);
                        Block glazedWall = (Block) ModBlocks.class.getField(glazedBase + "_WALL").get(null);
                        offerSmelting(List.of(slab), RecipeCategory.BUILDING_BLOCKS, glazedSlab, 0.1f, 200, glazedSlab.toString());
                        offerSmelting(List.of(stairs), RecipeCategory.BUILDING_BLOCKS, glazedStair, 0.1f, 200, glazedStair.toString());
                        offerSmelting(List.of(wall), RecipeCategory.BUILDING_BLOCKS, glazedWall, 0.1f, 200, glazedWall.toString());
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new RuntimeException("Failed to add block for recipe generation: " + name, e);
                    }
                }

                createShaped(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE).group("minecraft_extra:attribute_core").pattern(" g ").pattern("idi").pattern(" g ").input('g', Items.GOLD_INGOT).input('i', Items.IRON_INGOT).input('d', Items.DIAMOND).criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT)).criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT)).criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND)).offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE_EXTRA_HEARTS).group("minecraft_extra:attribute_core").input(ModItems.ATTRIBUTE_CORE).input(ModItems.HEART).criterion(hasItem(ModItems.ATTRIBUTE_CORE), conditionsFromItem(ModItems.ATTRIBUTE_CORE)).criterion(hasItem(ModItems.HEART), conditionsFromItem(ModItems.HEART)).offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE_GRAVITY).group("minecraft_extra:attribute_core").input(ModItems.ATTRIBUTE_CORE).input(Items.FEATHER).criterion(hasItem(ModItems.ATTRIBUTE_CORE), conditionsFromItem(ModItems.ATTRIBUTE_CORE)).criterion(hasItem(Items.FEATHER), conditionsFromItem(Items.FEATHER)).offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE_SCALED).group("minecraft_extra:attribute_core").pattern("gwg").pattern("gag").pattern("gwg").input('a', ModItems.ATTRIBUTE_CORE).input('g', Items.GHAST_TEAR).input('w', Items.GHAST_TEAR).criterion(hasItem(ModItems.ATTRIBUTE_CORE), conditionsFromItem(ModItems.ATTRIBUTE_CORE)).criterion(hasItem(Items.GHAST_TEAR), conditionsFromItem(Items.GHAST_TEAR)).criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR)).offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE_SPICY).group("minecraft_extra:attribute_core").input(ModItems.ATTRIBUTE_CORE).input(Items.PUFFERFISH).criterion(hasItem(ModItems.ATTRIBUTE_CORE), conditionsFromItem(ModItems.ATTRIBUTE_CORE)).criterion(hasItem(Items.PUFFERFISH), conditionsFromItem(Items.PUFFERFISH)).offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE_SPIKES).group("minecraft_extra:attribute_core").input(ModItems.ATTRIBUTE_CORE).input(Items.CACTUS).criterion(hasItem(ModItems.ATTRIBUTE_CORE), conditionsFromItem(ModItems.ATTRIBUTE_CORE)).criterion(hasItem(Items.CACTUS), conditionsFromItem(Items.CACTUS)).offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModItems.LOOTBOX).input(Items.DIAMOND).criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND)).offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModItems.GILDED_ILLUSIONER_ESSENCE).input(Items.GOLD_INGOT).input(ModItems.ILLUSIONER_ESSENCE).criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT)).criterion(hasItem(ModItems.ILLUSIONER_ESSENCE), conditionsFromItem(ModItems.ILLUSIONER_ESSENCE)).offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModItems.HARDENED_NETHERITE_INGOT).input(Items.NETHERITE_INGOT).input(Items.NETHERITE_INGOT).input(ModItems.GILDED_ILLUSIONER_ESSENCE).criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT)).criterion(hasItem(ModItems.GILDED_ILLUSIONER_ESSENCE), conditionsFromItem(ModItems.GILDED_ILLUSIONER_ESSENCE)).offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.HARDENED_NETHERITE_HELMET).pattern("hhh").pattern("h h").input('h', ModItems.HARDENED_NETHERITE_INGOT).criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT), conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT)).offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.HARDENED_NETHERITE_CHESTPLATE).pattern("h h").pattern("hhh").pattern("hhh").input('h', ModItems.HARDENED_NETHERITE_INGOT).criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT), conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT)).offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.HARDENED_NETHERITE_LEGGINGS).pattern("hhh").pattern("h h").pattern("h h").input('h', ModItems.HARDENED_NETHERITE_INGOT).criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT), conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT)).offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.HARDENED_NETHERITE_BOOTS).pattern("h h").pattern("h h").input('h', ModItems.HARDENED_NETHERITE_INGOT).criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT), conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT)).offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ModItems.HARDENED_NETHERITE_PICKAXE).pattern("hhh").pattern(" s ").pattern(" s ").input('h', ModItems.HARDENED_NETHERITE_INGOT).input('s', Items.STICK).criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT), conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT)).criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK)).offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ModItems.HARDENED_NETHERITE_SWORD).pattern("h").pattern("h").pattern("s").input('h', ModItems.HARDENED_NETHERITE_INGOT).input('s', Items.STICK).criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT), conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT)).criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK)).offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ModItems.HARDENED_NETHERITE_AXE).group("minecraft_extra:hardened_netherite_axe").pattern("hh").pattern("hs").pattern(" s").input('h', ModItems.HARDENED_NETHERITE_INGOT).input('s', Items.STICK).criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT), conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT)).criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK)).offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(MinecraftExtra.MOD_ID, "hardened_netherite_axe_left")));

                createShaped(RecipeCategory.TOOLS, ModItems.HARDENED_NETHERITE_AXE).group("minecraft_extra:hardened_netherite_axe").pattern("hh").pattern("sh").pattern("s ").input('h', ModItems.HARDENED_NETHERITE_INGOT).input('s', Items.STICK).criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT), conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT)).criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK)).offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(MinecraftExtra.MOD_ID, "hardened_netherite_axe_right")));

                createShaped(RecipeCategory.TOOLS, ModItems.HARDENED_NETHERITE_SHOVEL).pattern("h").pattern("s").pattern("s").input('h', ModItems.HARDENED_NETHERITE_INGOT).input('s', Items.STICK).criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT), conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT)).criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK)).offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ModItems.HARDENED_NETHERITE_HOE).group("minecraft_extra:hardened_netherite_hoe").pattern("hh").pattern(" s").pattern(" s").input('h', ModItems.HARDENED_NETHERITE_INGOT).input('s', Items.STICK).criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT), conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT)).criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK)).offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(MinecraftExtra.MOD_ID, "hardened_netherite_hoe_left")));

                createShaped(RecipeCategory.TOOLS, ModItems.HARDENED_NETHERITE_HOE).group("minecraft_extra:hardened_netherite_hoe").pattern("hh").pattern("s ").pattern("s ").input('h', ModItems.HARDENED_NETHERITE_INGOT).input('s', Items.STICK).criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT), conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT)).criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK)).offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(MinecraftExtra.MOD_ID, "hardened_netherite_hoe_right")));

                createShaped(RecipeCategory.MISC, ModItems.FLUTE).group("minecraft_extra:flute").pattern("pnp").input('p', ItemTags.PLANKS).input('n', Items.NOTE_BLOCK).criterion("has_planks", conditionsFromTag(ItemTags.PLANKS)).criterion(hasItem(Items.NOTE_BLOCK), conditionsFromItem(Items.NOTE_BLOCK)).offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModItems.GRAVITY_SHARD).input(Items.IRON_INGOT).input(Items.REDSTONE).criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT)).criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE)).offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.TP_STICK).group("minecraft_extra:tp_stick").pattern("  e").pattern(" f ").pattern("s  ").input('e', Items.ENDER_PEARL).input('f', Items.STRING) //String is called "Faden" in german
                        .input('s', Items.STICK).criterion(hasItem(Items.ENDER_PEARL), conditionsFromItem(Items.ENDER_PEARL)).criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING)).criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK)).offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModItems.ICEBOMB, 10).input(Items.SNOWBALL).input(Items.ICE).criterion(hasItem(Items.SNOWBALL), conditionsFromItem(Items.SNOWBALL)).criterion(hasItem(Items.ICE), conditionsFromItem(Items.ICE)).offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModItems.THROWABLE_FIRECHARGE).input(Items.FIRE_CHARGE).criterion(hasItem(Items.FIRE_CHARGE), conditionsFromItem(Items.FIRE_CHARGE)).offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModItems.STRONG_THROWABLE_FIRECHARGE).input(ModItems.THROWABLE_FIRECHARGE).input(Items.GUNPOWDER).criterion(hasItem(ModItems.THROWABLE_FIRECHARGE), conditionsFromItem(ModItems.THROWABLE_FIRECHARGE)).criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER)).offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModItems.SUPER_STRONG_THROWABLE_FIRECHARGE).input(ModItems.STRONG_THROWABLE_FIRECHARGE).input(Items.GUNPOWDER).criterion(hasItem(ModItems.STRONG_THROWABLE_FIRECHARGE), conditionsFromItem(ModItems.STRONG_THROWABLE_FIRECHARGE)).criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER)).offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(MinecraftExtra.MOD_ID, "super_strong_throwable_firecharge_from_strong_throwable_firecharge")));

                createShapeless(RecipeCategory.MISC, ModItems.SUPER_STRONG_THROWABLE_FIRECHARGE).input(ModItems.THROWABLE_FIRECHARGE).input(Items.GUNPOWDER).input(Items.GUNPOWDER).criterion(hasItem(ModItems.THROWABLE_FIRECHARGE), conditionsFromItem(ModItems.THROWABLE_FIRECHARGE)).criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER)).offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(MinecraftExtra.MOD_ID, "super_strong_throwable_firecharge_from_throwable_firecharge")));

                createShapeless(RecipeCategory.MISC, Items.FIRE_CHARGE).input(ModItems.THROWABLE_FIRECHARGE).criterion(hasItem(ModItems.THROWABLE_FIRECHARGE), conditionsFromItem(ModItems.THROWABLE_FIRECHARGE)).offerTo(exporter);

                createShaped(RecipeCategory.FOOD, ModItems.SUPER_FERTILIZER).pattern("rrr").pattern("rwr").pattern("rrr").input('r', Items.ROTTEN_FLESH).input('w', Items.WATER_BUCKET).criterion(hasItem(Items.ROTTEN_FLESH), conditionsFromItem(Items.ROTTEN_FLESH)).criterion(hasItem(Items.WATER_BUCKET), conditionsFromItem(Items.WATER_BUCKET)).offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.EFFECT_GEM).group("minecraft_extra:effect_gems").pattern(" g ").pattern("g g").pattern(" g ").input('g', Items.GLASS).criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS)).offerTo(exporter);

                createShapeless(RecipeCategory.COMBAT, ModItems.EFFECT_GEM_SLOW_FALLING).group("minecraft_extra:effect_gems").input(ModItems.EFFECT_GEM).input(Items.PHANTOM_MEMBRANE).criterion(hasItem(ModItems.EFFECT_GEM), conditionsFromItem(ModItems.EFFECT_GEM)).criterion(hasItem(Items.PHANTOM_MEMBRANE), conditionsFromItem(Items.PHANTOM_MEMBRANE)).offerTo(exporter);

                createShapeless(RecipeCategory.COMBAT, ModItems.EFFECT_GEM_FIRE_RESISTANCE).group("minecraft_extra:effect_gems").input(ModItems.EFFECT_GEM).input(Items.LAVA_BUCKET).input(Items.NETHER_WART).criterion(hasItem(ModItems.EFFECT_GEM), conditionsFromItem(ModItems.EFFECT_GEM)).criterion(hasItem(Items.LAVA_BUCKET), conditionsFromItem(Items.LAVA_BUCKET)).criterion(hasItem(Items.NETHER_WART), conditionsFromItem(Items.NETHER_WART)).offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.EFFECT_GEM_PUSH).group("minecraft_extra:effect_gems").pattern("www").pattern("wew").pattern("www").input('e', ModItems.EFFECT_GEM).input('w', Items.WIND_CHARGE).criterion(hasItem(ModItems.EFFECT_GEM), conditionsFromItem(ModItems.EFFECT_GEM)).criterion(hasItem(Items.WIND_CHARGE), conditionsFromItem(Items.WIND_CHARGE)).offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ModItems.HAMMER).pattern("i").pattern("s").input('i', Items.IRON_INGOT).input('s', Items.STICK).criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT)).criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK)).offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.ROTTEN_CHUNK).pattern("rrr").pattern("rrr").pattern("rrr").input('r', Items.ROTTEN_FLESH).criterion(hasItem(Items.ROTTEN_FLESH), conditionsFromItem(Items.ROTTEN_FLESH)).offerTo(exporter);

                createShapeless(RecipeCategory.MISC, Items.ROTTEN_FLESH, 9).input(ModItems.ROTTEN_CHUNK).criterion(hasItem(ModItems.ROTTEN_CHUNK), conditionsFromItem(ModItems.ROTTEN_CHUNK)).offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModBlocks.FLINT_BLOCK).input(Items.FLINT, 9).criterion(hasItem(Items.FLINT), conditionsFromItem(Items.FLINT)).offerTo(exporter);

                createShapeless(RecipeCategory.MISC, Items.FLINT, 9).input(ModBlocks.FLINT_BLOCK).criterion(hasItem(ModBlocks.FLINT_BLOCK), conditionsFromItem(ModBlocks.FLINT_BLOCK)).offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.FLINT_BRICKS, 4).pattern("ff").pattern("ff").input('f', ModBlocks.FLINT_BLOCK).criterion(hasItem(ModBlocks.FLINT_BLOCK), conditionsFromItem(ModBlocks.FLINT_BLOCK)).offerTo(exporter);

                StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(ModBlocks.FLINT_BLOCK), RecipeCategory.MISC, ModBlocks.FLINT_BRICKS).criterion(hasItem(ModBlocks.FLINT_BLOCK), conditionsFromItem(ModBlocks.FLINT_BLOCK)).offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(MinecraftExtra.MOD_ID, "stonecutting/flint_brick")));

                createShaped(RecipeCategory.MISC, ModBlocks.FLINT_SLAB, 6).pattern("fff").input('f', ModBlocks.FLINT_BLOCK).criterion(hasItem(ModBlocks.FLINT_BLOCK), conditionsFromItem(ModBlocks.FLINT_BLOCK)).offerTo(exporter);

                StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(ModBlocks.FLINT_BLOCK), RecipeCategory.MISC, ModBlocks.FLINT_SLAB, 2).criterion(hasItem(ModBlocks.FLINT_BLOCK), conditionsFromItem(ModBlocks.FLINT_BLOCK)).offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(MinecraftExtra.MOD_ID, "stonecutting/flint_slab")));

                createShaped(RecipeCategory.MISC, ModBlocks.FLINT_STAIR, 4).pattern("f  ").pattern("ff ").pattern("fff").input('f', ModBlocks.FLINT_BLOCK).criterion(hasItem(ModBlocks.FLINT_BLOCK), conditionsFromItem(ModBlocks.FLINT_BLOCK)).offerTo(exporter);

                StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(ModBlocks.FLINT_BLOCK), RecipeCategory.MISC, ModBlocks.FLINT_STAIR).criterion(hasItem(ModBlocks.FLINT_BLOCK), conditionsFromItem(ModBlocks.FLINT_BLOCK)).offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(MinecraftExtra.MOD_ID, "stonecutting/flint_stair")));

                createShaped(RecipeCategory.MISC, ModBlocks.FLINT_WALL, 6).pattern("fff").pattern("fff").input('f', ModBlocks.FLINT_BLOCK).criterion(hasItem(ModBlocks.FLINT_BLOCK), conditionsFromItem(ModBlocks.FLINT_BLOCK)).offerTo(exporter);

                StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(ModBlocks.FLINT_BLOCK), RecipeCategory.MISC, ModBlocks.FLINT_WALL).criterion(hasItem(ModBlocks.FLINT_BLOCK), conditionsFromItem(ModBlocks.FLINT_BLOCK)).offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(MinecraftExtra.MOD_ID, "stonecutting/flint_wall")));

                createShaped(RecipeCategory.MISC, ModBlocks.CHISELED_FLINT_BRICKS).pattern("f").pattern("f").input('f', ModBlocks.FLINT_BRICK_SLAB).criterion(hasItem(ModBlocks.FLINT_BRICK_SLAB), conditionsFromItem(ModBlocks.FLINT_BRICK_SLAB)).offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.FLINT_BRICK_SLAB, 6).pattern("fff").input('f', ModBlocks.FLINT_BRICKS).criterion(hasItem(ModBlocks.FLINT_BRICKS), conditionsFromItem(ModBlocks.FLINT_BRICKS)).offerTo(exporter);

                StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(ModBlocks.FLINT_BRICKS), RecipeCategory.MISC, ModBlocks.FLINT_BRICK_SLAB, 2).criterion(hasItem(ModBlocks.FLINT_BRICKS), conditionsFromItem(ModBlocks.FLINT_BRICKS)).offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(MinecraftExtra.MOD_ID, "stonecutting/flint_brick_slab")));

                createShaped(RecipeCategory.MISC, ModBlocks.FLINT_BRICK_STAIR, 4).pattern("f  ").pattern("ff ").pattern("fff").input('f', ModBlocks.FLINT_BRICKS).criterion(hasItem(ModBlocks.FLINT_BRICKS), conditionsFromItem(ModBlocks.FLINT_BRICKS)).offerTo(exporter);

                StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(ModBlocks.FLINT_BRICKS), RecipeCategory.MISC, ModBlocks.FLINT_BRICK_STAIR).criterion(hasItem(ModBlocks.FLINT_BRICKS), conditionsFromItem(ModBlocks.FLINT_BRICKS)).offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(MinecraftExtra.MOD_ID, "stonecutting/flint_brick_stair")));

                createShaped(RecipeCategory.MISC, ModBlocks.FLINT_BRICK_WALL, 6).pattern("fff").pattern("fff").input('f', ModBlocks.FLINT_BRICKS).criterion(hasItem(ModBlocks.FLINT_BRICKS), conditionsFromItem(ModBlocks.FLINT_BRICKS)).offerTo(exporter);

                StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(ModBlocks.FLINT_BRICKS), RecipeCategory.MISC, ModBlocks.FLINT_BRICK_WALL).criterion(hasItem(ModBlocks.FLINT_BRICKS), conditionsFromItem(ModBlocks.FLINT_BRICKS)).offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(MinecraftExtra.MOD_ID, "stonecutting/flint_brick_wall")));

                createShapeless(RecipeCategory.TOOLS, ModItems.BIG_FLINT_AND_STEEL).input(ModBlocks.FLINT_BLOCK).input(Blocks.IRON_BLOCK).criterion(hasItem(ModBlocks.FLINT_BLOCK), conditionsFromItem(ModBlocks.FLINT_BLOCK)).criterion(hasItem(Blocks.IRON_BLOCK), conditionsFromItem(Blocks.IRON_BLOCK)).offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.FEATHER_BLOCK).pattern("fff").pattern("fff").pattern("fff").input('f', Items.FEATHER).criterion(hasItem(Items.FEATHER), conditionsFromItem(Items.FEATHER)).offerTo(exporter);

                createShapeless(RecipeCategory.TOOLS, ModItems.BIOME_COMPASS).input(Items.COMPASS).input(Items.GLOWSTONE_DUST).criterion(hasItem(Items.COMPASS), conditionsFromItem(Items.COMPASS)).criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST)).offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, Items.IRON_HORSE_ARMOR).pattern("i i").pattern("iii").pattern("i i").input('i', Items.IRON_INGOT).criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT)).offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, Items.DIAMOND_HORSE_ARMOR).pattern("d d").pattern("ddd").pattern("d d").input('d', Items.DIAMOND).criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND)).offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, Items.GOLDEN_HORSE_ARMOR).pattern("g g").pattern("ggg").pattern("g g").input('g', Items.GOLD_INGOT).criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT)).offerTo(exporter);

                createShaped(RecipeCategory.FOOD, ModItems.FAT).pattern("ss").pattern("ss").input('s', Items.SUNFLOWER).criterion(hasItem(Items.SUNFLOWER), conditionsFromItem(Items.SUNFLOWER)).offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModItems.SOAP).input(ModItems.FAT).input(ModItems.ASH).input(Items.WATER_BUCKET).criterion(hasItem(ModItems.FAT), conditionsFromItem(ModItems.FAT)).criterion(hasItem(ModItems.ASH), conditionsFromItem(ModItems.ASH)).criterion(hasItem(Items.WATER_BUCKET), conditionsFromItem(Items.WATER_BUCKET)).offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.TIME_CONTROL_DEVICE_PIECE).pattern(" gg").pattern("cig").pattern("cc ").input('g', Items.GOLD_INGOT).input('c', Items.COPPER_INGOT).input('i', ModItems.ICE_BLAZE_ROD).criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT)).criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT)).criterion(hasItem(ModItems.ICE_BLAZE_ROD), conditionsFromItem(ModItems.ICE_BLAZE_ROD)).offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.TIME_CONTROL_DEVICE).pattern(" d ").pattern("iti").pattern("iii").input('d', Items.DAYLIGHT_DETECTOR).input('i', Items.IRON_INGOT).input('t', ModItems.TIME_CONTROL_DEVICE_PIECE).criterion(hasItem(Items.DAYLIGHT_DETECTOR), conditionsFromItem(Items.DAYLIGHT_DETECTOR)).criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT)).criterion(hasItem(ModItems.TIME_CONTROL_DEVICE_PIECE), conditionsFromItem(ModItems.TIME_CONTROL_DEVICE_PIECE)).offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "MinecraftExtra Recipes";
    }
}