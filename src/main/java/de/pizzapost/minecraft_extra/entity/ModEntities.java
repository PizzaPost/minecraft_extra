package de.pizzapost.minecraft_extra.entity;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.entity.custom.EndCrystalMobEntity;
import de.pizzapost.minecraft_extra.entity.custom.IceblazeEntity;
import de.pizzapost.minecraft_extra.entity.custom.IcebombEntity;
import de.pizzapost.minecraft_extra.entity.custom.SoapBubbleEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final RegistryKey<EntityType<?>> ICEBLAZE_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(MinecraftExtra.MOD_ID, "iceblaze"));
    public static final RegistryKey<EntityType<?>> ICEBOMB_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(MinecraftExtra.MOD_ID, "icebomb"));
    public static final RegistryKey<EntityType<?>> SOAP_BUBBLE_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(MinecraftExtra.MOD_ID, "soap_bubble"));
    public static final RegistryKey<EntityType<?>> END_CRYSTAL_MOB_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(MinecraftExtra.MOD_ID, "end_crystal_mob"));


    public static final EntityType<IceblazeEntity> ICEBLAZE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MinecraftExtra.MOD_ID, "iceblaze"),
            EntityType.Builder.create(IceblazeEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.6f, 1.8f).build(ICEBLAZE_KEY));

    public static final EntityType<IcebombEntity> ICEBOMB = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MinecraftExtra.MOD_ID, "icebomb"),
            EntityType.Builder.<IcebombEntity>create((entityType, world) -> new IcebombEntity(entityType, world), SpawnGroup.MISC)
                    .dimensions(0.25F, 0.25F).dropsNothing()
                    .build(ICEBOMB_KEY));

    public static final EntityType<SoapBubbleEntity> SOAP_BUBBLE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MinecraftExtra.MOD_ID, "soap_bubble"),
            EntityType.Builder.create(SoapBubbleEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1.6f, 1.6f)
                    .build(SOAP_BUBBLE_KEY));

    public static final EntityType<EndCrystalMobEntity> END_CRYSTAL_MOB = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MinecraftExtra.MOD_ID, "end_crystal_mob"),
            EntityType.Builder.create(EndCrystalMobEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1f, 1f)
                    .build(END_CRYSTAL_MOB_KEY));

    public static void registerModEntities() {
        MinecraftExtra.LOGGER.info("Registering Mod Entities for " + MinecraftExtra.MOD_ID);
    }
}