package de.pizzapost.minecraft_extra.sound;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent CHANGE_WEATHER = registerSoundEvent("change_weather");
    public static final SoundEvent SUPER_FERTILIZER = registerSoundEvent("super_fertilizer");
    public static final SoundEvent ATTRIBUTE_CORE_USE = registerSoundEvent("attribute_core.use");
    public static final SoundEvent WIRELESS_REDSTONE_SEND = registerSoundEvent("wire_redstone.send");
    public static final SoundEvent FLUTE = registerSoundEvent("flute");
    public static final SoundEvent GIANT_DEATH = registerSoundEvent("giant.death");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(MinecraftExtra.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        MinecraftExtra.LOGGER.info("Registering Sounds for " + MinecraftExtra.MOD_ID);
    }
}