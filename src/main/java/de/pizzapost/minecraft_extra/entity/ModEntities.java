package de.pizzapost.minecraft_extra.entity;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.entity.custom.IceblazeEntity;
import de.pizzapost.minecraft_extra.entity.custom.IcebombEntity;
import de.pizzapost.minecraft_extra.entity.custom.SoapBubbleEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<IceblazeEntity> ICEBLAZE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MinecraftExtra.MOD_ID, "iceblaze"),
            EntityType.Builder.create(IceblazeEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.6f, 1.8f).build("iceblaze"));

    public static final EntityType<IcebombEntity> ICEBOMB = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MinecraftExtra.MOD_ID, "icebomb"),
            FabricEntityTypeBuilder.<IcebombEntity>create(SpawnGroup.MISC, IcebombEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(99).trackedUpdateRate(1)
                    .build());

    public static final EntityType<SoapBubbleEntity> SOAP_BUBBLE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MinecraftExtra.MOD_ID, "soap_bubble"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SoapBubbleEntity::new)
                    .dimensions(EntityDimensions.fixed(1.6f, 1.6f))
                    .build());

    public static void registerModEntities() {
        MinecraftExtra.LOGGER.info("Registering Mod Entities for " + MinecraftExtra.MOD_ID);
    }
}