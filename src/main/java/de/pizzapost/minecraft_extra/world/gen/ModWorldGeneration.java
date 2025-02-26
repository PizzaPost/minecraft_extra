package de.pizzapost.minecraft_extra.world.gen;

public class ModWorldGeneration {
    public static void generateModWorldGen() {
        EntitySpawns.addSpawns();
        ModEntitySpawns.addSpawns();
        ModBushGeneration.generateBushes();
    }
}
