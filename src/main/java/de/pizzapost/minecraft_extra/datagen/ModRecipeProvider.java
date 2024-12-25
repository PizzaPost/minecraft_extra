package de.pizzapost.minecraft_extra.datagen;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE)
            .group("minecraft_extra:attribute_core")
            .pattern(" g ")
            .pattern("idi")
            .pattern(" g ")
            .input('g', Items.GOLD_INGOT)
            .input('i', Items.IRON_INGOT)
            .input('d', Items.DIAMOND)
            .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
            .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE_EXTRA_HEARTS)
                .group("minecraft_extra:attribute_core")
                .input(ModItems.ATTRIBUTE_CORE)
                .input(ModItems.HEART)
                .criterion(hasItem(ModItems.ATTRIBUTE_CORE), conditionsFromItem(ModItems.ATTRIBUTE_CORE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE_GRAVITY)
                .group("minecraft_extra:attribute_core")
                .input(ModItems.ATTRIBUTE_CORE)
                .input(Items.FEATHER)
                .criterion(hasItem(ModItems.ATTRIBUTE_CORE), conditionsFromItem(ModItems.ATTRIBUTE_CORE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE_SCALED)
                .group("minecraft_extra:attribute_core")
                .input(ModItems.ATTRIBUTE_CORE)
                .input(Items.GHAST_TEAR)
                .criterion(hasItem(ModItems.ATTRIBUTE_CORE), conditionsFromItem(ModItems.ATTRIBUTE_CORE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE_SPICY)
                .group("minecraft_extra:attribute_core")
                .input(ModItems.ATTRIBUTE_CORE)
                .input(Items.PUFFERFISH)
                .criterion(hasItem(ModItems.ATTRIBUTE_CORE), conditionsFromItem(ModItems.ATTRIBUTE_CORE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ATTRIBUTE_CORE_SPIKES)
                .group("minecraft_extra:attribute_core")
                .input(ModItems.ATTRIBUTE_CORE)
                .input(Items.CACTUS)
                .criterion(hasItem(ModItems.ATTRIBUTE_CORE), conditionsFromItem(ModItems.ATTRIBUTE_CORE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LOOTBOX)
                .input(Items.DIAMOND)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.HARDENED_NETHERITE_INGOT)
                .input(Items.NETHERITE_INGOT)
                .input(Items.NETHERITE_INGOT)
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
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

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FLUTE)
                .group("minecraft_extra:flute")
                .pattern("pnp")
                .input('p', Items.OAK_PLANKS)
                .input('n', Items.NOTE_BLOCK)
                .criterion(hasItem(Items.NOTE_BLOCK),conditionsFromItem(Items.NOTE_BLOCK))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "oak_flute"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FLUTE)
                .group("minecraft_extra:flute")
                .pattern("pnp")
                .input('p', Items.SPRUCE_PLANKS)
                .input('n', Items.NOTE_BLOCK)
                .criterion(hasItem(Items.NOTE_BLOCK),conditionsFromItem(Items.NOTE_BLOCK))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "spruce_flute"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FLUTE)
                .group("minecraft_extra:flute")
                .pattern("pnp")
                .input('p', Items.BIRCH_PLANKS)
                .input('n', Items.NOTE_BLOCK)
                .criterion(hasItem(Items.NOTE_BLOCK),conditionsFromItem(Items.NOTE_BLOCK))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "birch_flute"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FLUTE)
                .group("minecraft_extra:flute")
                .pattern("pnp")
                .input('p', Items.JUNGLE_PLANKS)
                .input('n', Items.NOTE_BLOCK)
                .criterion(hasItem(Items.NOTE_BLOCK),conditionsFromItem(Items.NOTE_BLOCK))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "jungle_flute"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FLUTE)
                .group("minecraft_extra:flute")
                .pattern("pnp")
                .input('p', Items.ACACIA_PLANKS)
                .input('n', Items.NOTE_BLOCK)
                .criterion(hasItem(Items.NOTE_BLOCK),conditionsFromItem(Items.NOTE_BLOCK))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "acacia_flute"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FLUTE)
                .group("minecraft_extra:flute")
                .pattern("pnp")
                .input('p', Items.DARK_OAK_PLANKS)
                .input('n', Items.NOTE_BLOCK)
                .criterion(hasItem(Items.NOTE_BLOCK),conditionsFromItem(Items.NOTE_BLOCK))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "dark_oak_flute"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FLUTE)
                .group("minecraft_extra:flute")
                .pattern("pnp")
                .input('p', Items.MANGROVE_PLANKS)
                .input('n', Items.NOTE_BLOCK)
                .criterion(hasItem(Items.NOTE_BLOCK),conditionsFromItem(Items.NOTE_BLOCK))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "mangrove_flute"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FLUTE)
                .group("minecraft_extra:flute")
                .pattern("pnp")
                .input('p', Items.CHERRY_PLANKS)
                .input('n', Items.NOTE_BLOCK)
                .criterion(hasItem(Items.NOTE_BLOCK),conditionsFromItem(Items.NOTE_BLOCK))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "cherry_flute"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FLUTE)
                .group("minecraft_extra:flute")
                .pattern("pnp")
                .input('p', Items.BAMBOO_PLANKS)
                .input('n', Items.NOTE_BLOCK)
                .criterion(hasItem(Items.NOTE_BLOCK),conditionsFromItem(Items.NOTE_BLOCK))
                .offerTo(exporter, Identifier.of(MinecraftExtra.MOD_ID, "bamboo_flute"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GRAVITY_SHARD)
                .input(Items.IRON_INGOT)
                .input(Items.REDSTONE)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
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
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ICEBOMB, 10)
                .input(Items.SNOWBALL)
                .input(Items.ICE)
                .criterion(hasItem(Items.SNOWBALL), conditionsFromItem(Items.SNOWBALL))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.THROWABLE_FIRECHARGE)
                .input(Items.FIRE_CHARGE)
                .criterion(hasItem(Items.FIRE_CHARGE), conditionsFromItem(Items.FIRE_CHARGE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.FIRE_CHARGE)
                .input(ModItems.THROWABLE_FIRECHARGE)
                .criterion(hasItem(ModItems.THROWABLE_FIRECHARGE), conditionsFromItem(ModItems.THROWABLE_FIRECHARGE))
                .offerTo(exporter);
    }
}
