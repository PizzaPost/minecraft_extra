package de.pizzapost.minecraft_extra.item;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.block.ModBlocks;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;

public class ModFuel {
    public static void registerFuelItems() {
        MinecraftExtra.LOGGER.info("Registering fuel for " + MinecraftExtra.MOD_ID);

        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.ROTTEN_CHUNK, 150);
            builder.add(ModBlocks.WHITE_WOOL_SLAB, 250);
            builder.add(ModBlocks.WHITE_WOOL_STAIR, 250);
            builder.add(ModBlocks.WHITE_WOOL_WALL, 250);
            builder.add(ModBlocks.LIGHT_GRAY_WOOL_SLAB, 250);
            builder.add(ModBlocks.LIGHT_GRAY_WOOL_STAIR, 250);
            builder.add(ModBlocks.LIGHT_GRAY_WOOL_WALL, 250);
            builder.add(ModBlocks.GRAY_WOOL_SLAB, 250);
            builder.add(ModBlocks.GRAY_WOOL_STAIR, 250);
            builder.add(ModBlocks.GRAY_WOOL_WALL, 250);
            builder.add(ModBlocks.BLACK_WOOL_SLAB, 250);
            builder.add(ModBlocks.BLACK_WOOL_STAIR, 250);
            builder.add(ModBlocks.BLACK_WOOL_WALL, 250);
            builder.add(ModBlocks.BROWN_WOOL_SLAB, 250);
            builder.add(ModBlocks.BROWN_WOOL_STAIR, 250);
            builder.add(ModBlocks.BROWN_WOOL_WALL, 250);
            builder.add(ModBlocks.RED_WOOL_SLAB, 250);
            builder.add(ModBlocks.RED_WOOL_STAIR, 250);
            builder.add(ModBlocks.RED_WOOL_WALL, 250);
            builder.add(ModBlocks.ORANGE_WOOL_SLAB, 250);
            builder.add(ModBlocks.ORANGE_WOOL_STAIR, 250);
            builder.add(ModBlocks.ORANGE_WOOL_WALL, 250);
            builder.add(ModBlocks.YELLOW_WOOL_SLAB, 250);
            builder.add(ModBlocks.YELLOW_WOOL_STAIR, 250);
            builder.add(ModBlocks.YELLOW_WOOL_WALL, 250);
            builder.add(ModBlocks.LIME_WOOL_SLAB, 250);
            builder.add(ModBlocks.LIME_WOOL_STAIR, 250);
            builder.add(ModBlocks.LIME_WOOL_WALL, 250);
            builder.add(ModBlocks.GREEN_WOOL_SLAB, 250);
            builder.add(ModBlocks.GREEN_WOOL_STAIR, 250);
            builder.add(ModBlocks.GREEN_WOOL_WALL, 250);
            builder.add(ModBlocks.CYAN_WOOL_SLAB, 250);
            builder.add(ModBlocks.CYAN_WOOL_STAIR, 250);
            builder.add(ModBlocks.CYAN_WOOL_WALL, 250);
            builder.add(ModBlocks.LIGHT_BLUE_WOOL_SLAB, 250);
            builder.add(ModBlocks.LIGHT_BLUE_WOOL_STAIR, 250);
            builder.add(ModBlocks.LIGHT_BLUE_WOOL_WALL, 250);
            builder.add(ModBlocks.BLUE_WOOL_SLAB, 250);
            builder.add(ModBlocks.BLUE_WOOL_STAIR, 250);
            builder.add(ModBlocks.BLUE_WOOL_WALL, 250);
            builder.add(ModBlocks.PURPLE_WOOL_SLAB, 250);
            builder.add(ModBlocks.PURPLE_WOOL_STAIR, 250);
            builder.add(ModBlocks.PURPLE_WOOL_WALL, 250);
            builder.add(ModBlocks.MAGENTA_WOOL_SLAB, 250);
            builder.add(ModBlocks.MAGENTA_WOOL_STAIR, 250);
            builder.add(ModBlocks.MAGENTA_WOOL_WALL, 250);
            builder.add(ModBlocks.PINK_WOOL_SLAB, 250);
            builder.add(ModBlocks.PINK_WOOL_STAIR, 250);
            builder.add(ModBlocks.PINK_WOOL_WALL, 250);
        });
    }
}
