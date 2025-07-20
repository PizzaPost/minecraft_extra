package de.pizzapost.minecraft_extra.util;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> HARDENED_NETHERITE_REPAIR = createTag("hardened_netherite_repair");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(MinecraftExtra.MOD_ID, name));
        }
    }
}