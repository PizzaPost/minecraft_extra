package de.pizzapost.minecraft_extra.item;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup MINECRAFT_EXTRA = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MinecraftExtra.MOD_ID, "minecraft_extra"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.ATTRIBUTE_CORE))
                    .displayName(Text.translatable("itemgroup.minecraft_extra.minecraft_extra_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.ATTRIBUTE_CORE);
                        entries.add(ModItems.ATTRIBUTE_CORE_EXTRA_HEARTS);
                        entries.add(ModItems.ATTRIBUTE_CORE_GRAVITY);
                        entries.add(ModItems.ATTRIBUTE_CORE_SCALED);
                        entries.add(ModItems.ATTRIBUTE_CORE_SPICY);
                        entries.add(ModItems.ATTRIBUTE_CORE_SPIKES);
                        entries.add(ModItems.HEART);
                        entries.add(ModItems.INFINITE_POTATO);
                        entries.add(ModItems.WEATHER_ITEM);
                        entries.add(ModItems.ROTTEN_CHUNK);
                        entries.add(ModItems.LOOTBOX);
                        entries.add(ModItems.HARDENED_NETHERITE_INGOT);
                        entries.add(ModItems.HARDENED_NETHERITE_HELMET);
                        entries.add(ModItems.HARDENED_NETHERITE_CHESTPLATE);
                        entries.add(ModItems.HARDENED_NETHERITE_LEGGINGS);
                        entries.add(ModItems.HARDENED_NETHERITE_BOOTS);
                        entries.add(ModItems.FLUTE);
                        entries.add(ModItems.GRAVITY_SHARD);
                        entries.add(ModItems.TP_STICK);
                        entries.add(ModItems.LIGHTNING_STICK);
                        entries.add(ModItems.ICEBOMB);
                        entries.add(ModItems.THROWABLE_FIRECHARGE);
                    })

                    .build());

    public static void registerItemGroups() {
        MinecraftExtra.LOGGER.info("Registering Item Groups for " + MinecraftExtra.MOD_ID);
    }
}
