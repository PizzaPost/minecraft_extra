package de.pizzapost.minecraft_extra.datagen;

import de.pizzapost.minecraft_extra.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ATTRIBUTE_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ATTRIBUTE_CORE_EXTRA_HEARTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.ATTRIBUTE_CORE_GRAVITY, Models.GENERATED);
        itemModelGenerator.register(ModItems.ATTRIBUTE_CORE_SCALED, Models.GENERATED);
        itemModelGenerator.register(ModItems.ATTRIBUTE_CORE_SPICY, Models.GENERATED);
        itemModelGenerator.register(ModItems.ATTRIBUTE_CORE_SPIKES, Models.GENERATED);
        itemModelGenerator.register(ModItems.HEART, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFINITE_POTATO, Models.GENERATED);
        itemModelGenerator.register(ModItems.WEATHER_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROTTEN_CHUNK, Models.GENERATED);
        itemModelGenerator.register(ModItems.LOOTBOX, Models.GENERATED);
        itemModelGenerator.register(ModItems.HARDENED_NETHERITE_INGOT, Models.GENERATED);
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.HARDENED_NETHERITE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.HARDENED_NETHERITE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.HARDENED_NETHERITE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.HARDENED_NETHERITE_BOOTS));
        itemModelGenerator.register(ModItems.FLUTE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GRAVITY_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.TP_STICK, Models.GENERATED);
        itemModelGenerator.register(ModItems.LIGHTNING_STICK, Models.GENERATED);
        itemModelGenerator.register(ModItems.ICEBOMB, Models.GENERATED);
        itemModelGenerator.register(ModItems.THROWABLE_FIRECHARGE, Models.GENERATED);
    }
}
