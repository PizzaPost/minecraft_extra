package de.pizzapost.minecraft_extra.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import de.pizzapost.minecraft_extra.item.ModItems;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.HARDENED_NETHERITE_HELMET)
                .add(ModItems.HARDENED_NETHERITE_CHESTPLATE)
                .add(ModItems.HARDENED_NETHERITE_LEGGINGS)
                .add(ModItems.HARDENED_NETHERITE_BOOTS);
    }
}