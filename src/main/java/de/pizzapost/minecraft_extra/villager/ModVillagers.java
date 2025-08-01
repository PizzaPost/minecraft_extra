package de.pizzapost.minecraft_extra.villager;

import com.google.common.collect.ImmutableSet;
import de.pizzapost.minecraft_extra.MinecraftExtra;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class ModVillagers {
    public static final RegistryKey<PointOfInterestType> PIZZA_POI_KEY = registerPoiKey("pizza_poi");
    public static final PointOfInterestType PIZZA_POI = registerPOI("pizza_poi", Blocks.HONEYCOMB_BLOCK);
    public static final RegistryKey<VillagerProfession> PIZZA_KEY = RegistryKey.of(RegistryKeys.VILLAGER_PROFESSION, Identifier.of(MinecraftExtra.MOD_ID, "beekeeper"));
    public static final VillagerProfession BEEKEEPER = registerProfession("beekeeper", PIZZA_POI_KEY);


    private static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, Identifier.of(MinecraftExtra.MOD_ID, name), new VillagerProfession(Text.literal(name), entry -> entry.matchesKey(type), entry -> entry.matchesKey(type), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_LIBRARIAN));
    }

    private static PointOfInterestType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(Identifier.of(MinecraftExtra.MOD_ID, name), 1, 1, block);
    }

    private static RegistryKey<PointOfInterestType> registerPoiKey(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, Identifier.of(MinecraftExtra.MOD_ID, name));
    }

    public static void registerVillagers() {
        MinecraftExtra.LOGGER.info("Registering Villagers for " + MinecraftExtra.MOD_ID);
    }
}