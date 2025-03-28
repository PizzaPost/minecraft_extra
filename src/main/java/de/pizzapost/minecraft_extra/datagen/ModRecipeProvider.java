package de.pizzapost.minecraft_extra.datagen;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.block.ModBlocks;
import de.pizzapost.minecraft_extra.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RepairItemRecipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        TagKey<Item>planksTag=TagKey.of(RegistryKeys.ITEM, Identifier.of("minecraft", "planks"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE)
            .group("minecraft_extra:attribute_core")
            .pattern(" g ")
            .pattern("idi")
            .pattern(" g ")
            .input('g', Items.GOLD_INGOT)
            .input('i', Items.IRON_INGOT)
            .input('d', Items.DIAMOND)
            .criterion(hasItem(Items.GOLD_INGOT),conditionsFromItem(Items.GOLD_INGOT))
            .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
            .criterion(hasItem(Items.DIAMOND),conditionsFromItem(Items.DIAMOND))
            .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE_EXTRA_HEARTS)
                .group("minecraft_extra:attribute_core")
                .input(ModItems.ATTRIBUTE_CORE)
                .input(ModItems.HEART)
                .criterion(hasItem(ModItems.ATTRIBUTE_CORE), conditionsFromItem(ModItems.ATTRIBUTE_CORE))
                .criterion(hasItem(ModItems.HEART), conditionsFromItem(ModItems.HEART))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE_GRAVITY)
                .group("minecraft_extra:attribute_core")
                .input(ModItems.ATTRIBUTE_CORE)
                .input(Items.FEATHER)
                .criterion(hasItem(ModItems.ATTRIBUTE_CORE), conditionsFromItem(ModItems.ATTRIBUTE_CORE))
                .criterion(hasItem(Items.FEATHER), conditionsFromItem(Items.FEATHER))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE_SCALED)
                .group("minecraft_extra:attribute_core")
                .pattern("gwg")
                .pattern("gag")
                .pattern("gwg")
                .input('a', ModItems.ATTRIBUTE_CORE)
                .input('g', Items.GHAST_TEAR)
                .input('w', Items.GHAST_TEAR)
                .criterion(hasItem(ModItems.ATTRIBUTE_CORE), conditionsFromItem(ModItems.ATTRIBUTE_CORE))
                .criterion(hasItem(Items.GHAST_TEAR), conditionsFromItem(Items.GHAST_TEAR))
                .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE_SPICY)
                .group("minecraft_extra:attribute_core")
                .input(ModItems.ATTRIBUTE_CORE)
                .input(Items.PUFFERFISH)
                .criterion(hasItem(ModItems.ATTRIBUTE_CORE), conditionsFromItem(ModItems.ATTRIBUTE_CORE))
                .criterion(hasItem(Items.PUFFERFISH), conditionsFromItem(Items.PUFFERFISH))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE_SPIKES)
                .group("minecraft_extra:attribute_core")
                .input(ModItems.ATTRIBUTE_CORE)
                .input(Items.CACTUS)
                .criterion(hasItem(ModItems.ATTRIBUTE_CORE), conditionsFromItem(ModItems.ATTRIBUTE_CORE))
                .criterion(hasItem(Items.CACTUS), conditionsFromItem(Items.CACTUS))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LOOTBOX)
                .input(Items.DIAMOND)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GILDED_ILLUSIONER_ESSENCE)
                .input(Items.GOLD_INGOT)
                .input(ModItems.ILLUSIONER_ESSENCE)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(ModItems.ILLUSIONER_ESSENCE), conditionsFromItem(ModItems.ILLUSIONER_ESSENCE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HARDENED_NETHERITE_INGOT)
                .input(Items.NETHERITE_INGOT)
                .input(Items.NETHERITE_INGOT)
                .input(ModItems.GILDED_ILLUSIONER_ESSENCE)
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .criterion(hasItem(ModItems.GILDED_ILLUSIONER_ESSENCE), conditionsFromItem(ModItems.GILDED_ILLUSIONER_ESSENCE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.HARDENED_NETHERITE_HELMET)
                .pattern("hhh")
                .pattern("h h")
                .input('h', ModItems.HARDENED_NETHERITE_INGOT)
                .criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT),conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.HARDENED_NETHERITE_CHESTPLATE)
                .pattern("h h")
                .pattern("hhh")
                .pattern("hhh")
                .input('h', ModItems.HARDENED_NETHERITE_INGOT)
                .criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT),conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.HARDENED_NETHERITE_LEGGINGS)
                .pattern("hhh")
                .pattern("h h")
                .pattern("h h")
                .input('h', ModItems.HARDENED_NETHERITE_INGOT)
                .criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT),conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.HARDENED_NETHERITE_BOOTS)
                .pattern("h h")
                .pattern("h h")
                .input('h', ModItems.HARDENED_NETHERITE_INGOT)
                .criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT),conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.HARDENED_NETHERITE_PICKAXE)
                .pattern("hhh")
                .pattern(" s ")
                .pattern(" s ")
                .input('h', ModItems.HARDENED_NETHERITE_INGOT)
                .input('s', Items.STICK)
                .criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT),conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT))
                .criterion(hasItem(Items.STICK),conditionsFromItem(Items.STICK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.HARDENED_NETHERITE_SWORD)
                .pattern("h")
                .pattern("h")
                .pattern("s")
                .input('h', ModItems.HARDENED_NETHERITE_INGOT)
                .input('s', Items.STICK)
                .criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT),conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT))
                .criterion(hasItem(Items.STICK),conditionsFromItem(Items.STICK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.HARDENED_NETHERITE_AXE)
                .group("minecraft_extra:hardened_netherite_axe")
                .pattern("hh")
                .pattern("hs")
                .pattern(" s")
                .input('h', ModItems.HARDENED_NETHERITE_INGOT)
                .input('s', Items.STICK)
                .criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT),conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT))
                .criterion(hasItem(Items.STICK),conditionsFromItem(Items.STICK))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "hardened_netherite_axe_left"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.HARDENED_NETHERITE_AXE)
                .group("minecraft_extra:hardened_netherite_axe")
                .pattern("hh")
                .pattern("sh")
                .pattern("s ")
                .input('h', ModItems.HARDENED_NETHERITE_INGOT)
                .input('s', Items.STICK)
                .criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT),conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT))
                .criterion(hasItem(Items.STICK),conditionsFromItem(Items.STICK))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "hardened_netherite_axe_right"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.HARDENED_NETHERITE_SHOVEL)
                .pattern("h")
                .pattern("s")
                .pattern("s")
                .input('h', ModItems.HARDENED_NETHERITE_INGOT)
                .input('s', Items.STICK)
                .criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT),conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT))
                .criterion(hasItem(Items.STICK),conditionsFromItem(Items.STICK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.HARDENED_NETHERITE_HOE)
                .pattern("hh")
                .pattern(" s")
                .pattern(" s")
                .input('h', ModItems.HARDENED_NETHERITE_INGOT)
                .input('s', Items.STICK)
                .criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT),conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT))
                .criterion(hasItem(Items.STICK),conditionsFromItem(Items.STICK))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "hardened_netherite_hoe_left"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.HARDENED_NETHERITE_HOE)
                .pattern("hh")
                .pattern("s ")
                .pattern("s ")
                .input('h', ModItems.HARDENED_NETHERITE_INGOT)
                .input('s', Items.STICK)
                .criterion(hasItem(ModItems.HARDENED_NETHERITE_INGOT),conditionsFromItem(ModItems.HARDENED_NETHERITE_INGOT))
                .criterion(hasItem(Items.STICK),conditionsFromItem(Items.STICK))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "hardened_netherite_hoe_right"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FLUTE)
                .group("minecraft_extra:flute")
                .pattern("pnp")
                .input('p', Ingredient.fromTag(planksTag))
                .input('n', Items.NOTE_BLOCK)
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create().tag(planksTag).build()))
                .criterion(hasItem(Items.NOTE_BLOCK),conditionsFromItem(Items.NOTE_BLOCK))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GRAVITY_SHARD)
                .input(Items.IRON_INGOT)
                .input(Items.REDSTONE)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TP_STICK)
                .group("minecraft_extra:tp_stick")
                .pattern("  e")
                .pattern(" f ")
                .pattern("s  ")
                .input('e', Items.ENDER_PEARL)
                .input('f', Items.STRING) //String is called "Faden" in german
                .input('s', Items.STICK)
                .criterion(hasItem(Items.ENDER_PEARL),conditionsFromItem(Items.ENDER_PEARL))
                .criterion(hasItem(Items.STRING),conditionsFromItem(Items.STRING))
                .criterion(hasItem(Items.STICK),conditionsFromItem(Items.STICK))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ICEBOMB, 10)
                .input(Items.SNOWBALL)
                .input(Items.ICE)
                .criterion(hasItem(Items.SNOWBALL), conditionsFromItem(Items.SNOWBALL))
                .criterion(hasItem(Items.ICE), conditionsFromItem(Items.ICE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.THROWABLE_FIRECHARGE)
                .input(Items.FIRE_CHARGE)
                .criterion(hasItem(Items.FIRE_CHARGE), conditionsFromItem(Items.FIRE_CHARGE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STRONG_THROWABLE_FIRECHARGE)
                .input(ModItems.THROWABLE_FIRECHARGE)
                .input(Items.GUNPOWDER)
                .criterion(hasItem(ModItems.THROWABLE_FIRECHARGE), conditionsFromItem(ModItems.THROWABLE_FIRECHARGE))
                .criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SUPER_STRONG_THROWABLE_FIRECHARGE)
                .input(ModItems.STRONG_THROWABLE_FIRECHARGE)
                .input(Items.GUNPOWDER)
                .criterion(hasItem(ModItems.STRONG_THROWABLE_FIRECHARGE), conditionsFromItem(ModItems.STRONG_THROWABLE_FIRECHARGE))
                .criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "super_strong_throwable_firecharge_from_strong_throwable_firecharge"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SUPER_STRONG_THROWABLE_FIRECHARGE)
                .input(ModItems.THROWABLE_FIRECHARGE)
                .input(Items.GUNPOWDER)
                .input(Items.GUNPOWDER)
                .criterion(hasItem(ModItems.THROWABLE_FIRECHARGE), conditionsFromItem(ModItems.THROWABLE_FIRECHARGE))
                .criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "super_strong_throwable_firecharge_from_throwable_firecharge"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.FIRE_CHARGE)
                .input(ModItems.THROWABLE_FIRECHARGE)
                .criterion(hasItem(ModItems.THROWABLE_FIRECHARGE), conditionsFromItem(ModItems.THROWABLE_FIRECHARGE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.SUPER_FERTILIZER)
                .pattern("rrr")
                .pattern("rwr")
                .pattern("rrr")
                .input('r', Items.ROTTEN_FLESH)
                .input('w', Items.WATER_BUCKET)
                .criterion(hasItem(Items.ROTTEN_FLESH),conditionsFromItem(Items.ROTTEN_FLESH))
                .criterion(hasItem(Items.WATER_BUCKET),conditionsFromItem(Items.WATER_BUCKET))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.EFFECT_GEM)
                .group("minecraft_extra:effect_gems")
                .pattern(" g ")
                .pattern("g g")
                .pattern(" g ")
                .input('g', Items.GLASS)
                .criterion(hasItem(Items.GLASS),conditionsFromItem(Items.GLASS))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.EFFECT_GEM_SLOW_FALLING)
                .group("minecraft_extra:effect_gems")
                .input(ModItems.EFFECT_GEM)
                .input(Items.PHANTOM_MEMBRANE)
                .criterion(hasItem(ModItems.EFFECT_GEM), conditionsFromItem(ModItems.EFFECT_GEM))
                .criterion(hasItem(Items.PHANTOM_MEMBRANE), conditionsFromItem(Items.PHANTOM_MEMBRANE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.EFFECT_GEM_FIRE_RESISTANCE)
                .group("minecraft_extra:effect_gems")
                .input(ModItems.EFFECT_GEM)
                .input(Items.LAVA_BUCKET)
                .input(Items.NETHER_WART)
                .criterion(hasItem(ModItems.EFFECT_GEM), conditionsFromItem(ModItems.EFFECT_GEM))
                .criterion(hasItem(Items.LAVA_BUCKET), conditionsFromItem(Items.LAVA_BUCKET))
                .criterion(hasItem(Items.NETHER_WART), conditionsFromItem(Items.NETHER_WART))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.EFFECT_GEM_PUSH)
                .group("minecraft_extra:effect_gems")
                .pattern("www")
                .pattern("wew")
                .pattern("www")
                .input('e', ModItems.EFFECT_GEM)
                .input('w', Items.WIND_CHARGE)
                .criterion(hasItem(ModItems.EFFECT_GEM), conditionsFromItem(ModItems.EFFECT_GEM))
                .criterion(hasItem(Items.WIND_CHARGE), conditionsFromItem(Items.WIND_CHARGE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.HAMMER)
                .pattern("i")
                .pattern("s")
                .input('i', Items.IRON_INGOT)
                .input('s', Items.STICK)
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.STICK),conditionsFromItem(Items.STICK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ROTTEN_CHUNK)
                .pattern("rrr")
                .pattern("rrr")
                .pattern("rrr")
                .input('r', Items.ROTTEN_FLESH)
                .criterion(hasItem(Items.ROTTEN_FLESH),conditionsFromItem(Items.ROTTEN_FLESH))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.ROTTEN_FLESH, 9)
                .input(ModItems.ROTTEN_CHUNK)
                .criterion(hasItem(ModItems.ROTTEN_CHUNK),conditionsFromItem(ModItems.ROTTEN_CHUNK))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FLINT_BLOCK)
                .input(Items.FLINT, 9)
                .criterion(hasItem(Items.FLINT),conditionsFromItem(Items.FLINT))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.FLINT, 9)
                .input(ModBlocks.FLINT_BLOCK)
                .criterion(hasItem(ModBlocks.FLINT_BLOCK),conditionsFromItem(ModBlocks.FLINT_BLOCK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FLINT_BRICKS, 4)
                .pattern("ff")
                .pattern("ff")
                .input('f', ModBlocks.FLINT_BLOCK)
                .criterion(hasItem(ModBlocks.FLINT_BLOCK),conditionsFromItem(ModBlocks.FLINT_BLOCK))
                .offerTo(exporter);

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(ModBlocks.FLINT_BLOCK), RecipeCategory.MISC,ModBlocks.FLINT_BRICKS)
                .criterion(hasItem(ModBlocks.FLINT_BLOCK),conditionsFromItem(ModBlocks.FLINT_BLOCK))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "stonecutting/flint_brick"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FLINT_SLAB, 6)
                .pattern("fff")
                .input('f', ModBlocks.FLINT_BLOCK)
                .criterion(hasItem(ModBlocks.FLINT_BLOCK),conditionsFromItem(ModBlocks.FLINT_BLOCK))
                .offerTo(exporter);

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(ModBlocks.FLINT_BLOCK), RecipeCategory.MISC,ModBlocks.FLINT_SLAB)
                .criterion(hasItem(ModBlocks.FLINT_BLOCK),conditionsFromItem(ModBlocks.FLINT_BLOCK))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "stonecutting/flint_slab"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FLINT_STAIR, 4)
                .pattern("f  ")
                .pattern("ff ")
                .pattern("fff")
                .input('f', ModBlocks.FLINT_BLOCK)
                .criterion(hasItem(ModBlocks.FLINT_BLOCK),conditionsFromItem(ModBlocks.FLINT_BLOCK))
                .offerTo(exporter);

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(ModBlocks.FLINT_BLOCK), RecipeCategory.MISC,ModBlocks.FLINT_STAIR)
                .criterion(hasItem(ModBlocks.FLINT_BLOCK),conditionsFromItem(ModBlocks.FLINT_BLOCK))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "stonecutting/flint_stair"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FLINT_WALL, 6)
                .pattern("fff")
                .pattern("fff")
                .input('f', ModBlocks.FLINT_BLOCK)
                .criterion(hasItem(ModBlocks.FLINT_BLOCK),conditionsFromItem(ModBlocks.FLINT_BLOCK))
                .offerTo(exporter);

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(ModBlocks.FLINT_BLOCK), RecipeCategory.MISC,ModBlocks.FLINT_WALL)
                .criterion(hasItem(ModBlocks.FLINT_BLOCK),conditionsFromItem(ModBlocks.FLINT_BLOCK))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "stonecutting/flint_wall"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CHISELED_FLINT_BRICKS)
                .pattern("f")
                .pattern("f")
                .input('f', ModBlocks.FLINT_BRICK_SLAB)
                .criterion(hasItem(ModBlocks.FLINT_BRICK_SLAB),conditionsFromItem(ModBlocks.FLINT_BRICK_SLAB))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FLINT_BRICK_SLAB, 6)
                .pattern("fff")
                .input('f', ModBlocks.FLINT_BRICKS)
                .criterion(hasItem(ModBlocks.FLINT_BRICKS),conditionsFromItem(ModBlocks.FLINT_BRICKS))
                .offerTo(exporter);

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(ModBlocks.FLINT_BRICKS), RecipeCategory.MISC,ModBlocks.FLINT_BRICK_SLAB, 2)
                .criterion(hasItem(ModBlocks.FLINT_BRICKS),conditionsFromItem(ModBlocks.FLINT_BRICKS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "stonecutting/flint_brick_slab"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FLINT_BRICK_STAIR, 4)
                .pattern("f  ")
                .pattern("ff ")
                .pattern("fff")
                .input('f', ModBlocks.FLINT_BRICKS)
                .criterion(hasItem(ModBlocks.FLINT_BRICKS),conditionsFromItem(ModBlocks.FLINT_BRICKS))
                .offerTo(exporter);

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(ModBlocks.FLINT_BRICKS), RecipeCategory.MISC,ModBlocks.FLINT_BRICK_STAIR)
                .criterion(hasItem(ModBlocks.FLINT_BRICKS),conditionsFromItem(ModBlocks.FLINT_BRICKS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "stonecutting/flint_brick_stair"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FLINT_BRICK_WALL, 6)
                .pattern("fff")
                .pattern("fff")
                .input('f', ModBlocks.FLINT_BRICKS)
                .criterion(hasItem(ModBlocks.FLINT_BRICKS),conditionsFromItem(ModBlocks.FLINT_BRICKS))
                .offerTo(exporter);

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(ModBlocks.FLINT_BRICKS), RecipeCategory.MISC,ModBlocks.FLINT_BRICK_WALL)
                .criterion(hasItem(ModBlocks.FLINT_BRICKS),conditionsFromItem(ModBlocks.FLINT_BRICKS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "stonecutting/flint_brick_wall"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.BIG_FLINT_AND_STEEL)
                .input(ModBlocks.FLINT_BLOCK)
                .input(Blocks.IRON_BLOCK)
                .criterion(hasItem(ModBlocks.FLINT_BLOCK),conditionsFromItem(ModBlocks.FLINT_BLOCK))
                .criterion(hasItem(Blocks.IRON_BLOCK),conditionsFromItem(Blocks.IRON_BLOCK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FEATHER_BLOCK)
                .pattern("fff")
                .pattern("fff")
                .pattern("fff")
                .input('f', Items.FEATHER)
                .criterion(hasItem(Items.FEATHER),conditionsFromItem(Items.FEATHER))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.BIOME_COMPASS)
                .input(Items.COMPASS)
                .input(Items.GLOWSTONE_DUST)
                .criterion(hasItem(Items.COMPASS),conditionsFromItem(Items.COMPASS))
                .criterion(hasItem(Items.GLOWSTONE_DUST),conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.WHITE_DYE)
                .input(ModItems.WHITE_PETALS)
                .criterion(hasItem(ModItems.WHITE_PETALS), conditionsFromItem(ModItems.WHITE_PETALS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "white_dye_from_white_petals"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.ORANGE_DYE)
                .input(ModItems.ORANGE_PETALS)
                .criterion(hasItem(ModItems.ORANGE_PETALS), conditionsFromItem(ModItems.ORANGE_PETALS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "orange_dye_from_orange_petals"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.MAGENTA_DYE)
                .input(ModItems.MAGENTA_PETALS)
                .criterion(hasItem(ModItems.MAGENTA_PETALS), conditionsFromItem(ModItems.MAGENTA_PETALS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "magenta_dye_from_magenta_petals"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.LIGHT_BLUE_DYE)
                .input(ModItems.LIGHT_BLUE_PETALS)
                .criterion(hasItem(ModItems.LIGHT_BLUE_PETALS), conditionsFromItem(ModItems.LIGHT_BLUE_PETALS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "light_blue_dye_from_light_blue_petals"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.YELLOW_DYE)
                .input(ModItems.YELLOW_PETALS)
                .criterion(hasItem(ModItems.YELLOW_PETALS), conditionsFromItem(ModItems.YELLOW_PETALS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "yellow_dye_from_yellow_petals"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.LIME_DYE)
                .input(ModItems.LIME_PETALS)
                .criterion(hasItem(ModItems.LIME_PETALS), conditionsFromItem(ModItems.LIME_PETALS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "lime_dye_from_lime_petals"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.GRAY_DYE)
                .input(ModItems.GRAY_PETALS)
                .criterion(hasItem(ModItems.GRAY_PETALS), conditionsFromItem(ModItems.GRAY_PETALS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "gray_dye_from_gray_petals"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.LIGHT_GRAY_DYE)
                .input(ModItems.LIGHT_GRAY_PETALS)
                .criterion(hasItem(ModItems.LIGHT_GRAY_PETALS), conditionsFromItem(ModItems.LIGHT_GRAY_PETALS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "light_gray_dye_from_light_gray_petals"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.CYAN_DYE)
                .input(ModItems.CYAN_PETALS)
                .criterion(hasItem(ModItems.CYAN_PETALS), conditionsFromItem(ModItems.CYAN_PETALS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "cyan_dye_from_cyan_petals"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.PURPLE_DYE)
                .input(ModItems.PURPLE_PETALS)
                .criterion(hasItem(ModItems.PURPLE_PETALS), conditionsFromItem(ModItems.PURPLE_PETALS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "purple_dye_from_purple_petals"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.BLUE_DYE)
                .input(ModItems.BLUE_PETALS)
                .criterion(hasItem(ModItems.BLUE_PETALS), conditionsFromItem(ModItems.BLUE_PETALS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "blue_dye_from_blue_petals"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.BROWN_DYE)
                .input(ModItems.BROWN_PETALS)
                .criterion(hasItem(ModItems.BROWN_PETALS), conditionsFromItem(ModItems.BROWN_PETALS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "brown_dye_from_brown_petals"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.GREEN_DYE)
                .input(ModItems.GREEN_PETALS)
                .criterion(hasItem(ModItems.GREEN_PETALS), conditionsFromItem(ModItems.GREEN_PETALS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "green_dye_from_green_petals"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.RED_DYE)
                .input(ModItems.RED_PETALS)
                .criterion(hasItem(ModItems.RED_PETALS), conditionsFromItem(ModItems.RED_PETALS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "red_dye_from_red_petals"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.BLACK_DYE)
                .input(ModItems.BLACK_PETALS)
                .criterion(hasItem(ModItems.BLACK_PETALS), conditionsFromItem(ModItems.BLACK_PETALS))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "black_dye_from_black_petals"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.IRON_HORSE_ARMOR)
                .pattern("i i")
                .pattern("iii")
                .pattern("i i")
                .input('i', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.DIAMOND_HORSE_ARMOR)
                .pattern("d d")
                .pattern("ddd")
                .pattern("d d")
                .input('d', Items.DIAMOND)
                .criterion(hasItem(Items.DIAMOND),conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.GOLDEN_HORSE_ARMOR)
                .pattern("g g")
                .pattern("ggg")
                .pattern("g g")
                .input('g', Items.GOLD_INGOT)
                .criterion(hasItem(Items.GOLD_INGOT),conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.FAT)
                .pattern("ss")
                .pattern("ss")
                .input('s', Items.SUNFLOWER)
                .criterion(hasItem(Items.SUNFLOWER),conditionsFromItem(Items.SUNFLOWER))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SOAP)
                .input(ModItems.FAT)
                .input(ModItems.ASH)
                .input(Items.WATER_BUCKET)
                .criterion(hasItem(ModItems.FAT), conditionsFromItem(ModItems.FAT))
                .criterion(hasItem(ModItems.ASH), conditionsFromItem(ModItems.ASH))
                .criterion(hasItem(Items.WATER_BUCKET), conditionsFromItem(Items.WATER_BUCKET))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TIME_CONTROL_DEVICE_PIECE)
                .pattern(" gg")
                .pattern("cig")
                .pattern("cc ")
                .input('g', Items.GOLD_INGOT)
                .input('c', Items.COPPER_INGOT)
                .input('i', ModItems.ICE_BLAZE_ROD)
                .criterion(hasItem(Items.GOLD_INGOT),conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(Items.COPPER_INGOT),conditionsFromItem(Items.COPPER_INGOT))
                .criterion(hasItem(ModItems.ICE_BLAZE_ROD),conditionsFromItem(ModItems.ICE_BLAZE_ROD))
                .offerTo(exporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TIME_CONTROL_DEVICE)
                .pattern(" d ")
                .pattern("iti")
                .pattern("iii")
                .input('d', Items.DAYLIGHT_DETECTOR)
                .input('i', Items.IRON_INGOT)
                .input('t', ModItems.TIME_CONTROL_DEVICE_PIECE)
                .criterion(hasItem(Items.DAYLIGHT_DETECTOR),conditionsFromItem(Items.DAYLIGHT_DETECTOR))
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(ModItems.TIME_CONTROL_DEVICE_PIECE),conditionsFromItem(ModItems.TIME_CONTROL_DEVICE_PIECE))
                .offerTo(exporter);
    }
}
