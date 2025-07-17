package de.pizzapost.minecraft_extra.world.gen;

import de.pizzapost.minecraft_extra.entity.ModEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawns {
    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.FROZEN_OCEAN, BiomeKeys.SNOWY_PLAINS), SpawnGroup.MONSTER, ModEntities.ICEBLAZE, 10, 1, 1);
        SpawnRestriction.register(ModEntities.ICEBLAZE, SpawnRestriction.getLocation(ModEntities.ICEBLAZE), Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (type, world, spawnReason, pos, random) -> pos.getY() > 63);
    }
}
