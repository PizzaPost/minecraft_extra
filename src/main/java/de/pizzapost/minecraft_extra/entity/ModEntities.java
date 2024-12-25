package de.pizzapost.minecraft_extra.entity;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.entity.custom.IcebombEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, id, type.build(id));
    }
    public static final EntityType<IcebombEntity> ICEBOMB = register(
            "icebomb",
            EntityType.Builder.<IcebombEntity>create(IcebombEntity::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10)
    );

    public static void registeredModEntities() {
        MinecraftExtra.LOGGER.info("Registering Mod Entities for " + MinecraftExtra.MOD_ID);
    }
}
