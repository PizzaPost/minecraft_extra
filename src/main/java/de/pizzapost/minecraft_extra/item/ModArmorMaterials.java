package de.pizzapost.minecraft_extra.item;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.util.ModTags;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;

public class ModArmorMaterials {
    static RegistryKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = RegistryKey.ofRegistry(Identifier.ofVanilla("equipment_asset"));
    public static final RegistryKey<EquipmentAsset> HARDENED_NETHERITE_KEY = RegistryKey.of(REGISTRY_KEY, Identifier.of(MinecraftExtra.MOD_ID, "hardened_netherite"));

    public static final ArmorMaterial HARDENED_NETHERITE_ARMOR_MATERIAL = new ArmorMaterial(500, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 4);
        map.put(EquipmentType.CHESTPLATE, 9);
        map.put(EquipmentType.LEGGINGS, 7);
        map.put(EquipmentType.BOOTS, 4);
        map.put(EquipmentType.BODY, 5);
    }), 20, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0, 0, ModTags.Items.HARDENED_NETHERITE_REPAIR, HARDENED_NETHERITE_KEY);
}
