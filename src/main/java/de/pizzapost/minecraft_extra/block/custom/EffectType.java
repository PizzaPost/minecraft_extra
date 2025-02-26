package de.pizzapost.minecraft_extra.block.custom;

import net.minecraft.util.StringIdentifiable;

public enum EffectType implements StringIdentifiable {
    NONE,
    ABSORPTION,
    BAD_OMEN,
    BLINDNESS,
    CONDUIT_POWER,
    DARKNESS,
    DOLPHINS_GRACE,
    FIRE_RESISTANCE,
    GLOWING,
    HASTE,
    HEALTH_BOOST,
    HERO_OF_THE_VILLAGE,
    HUNGER,
    INFESTED,
    INSTANT_DAMAGE,
    INSTANT_HEALTH,
    INVISIBILITY,
    JUMP_BOOST,
    LEVITATION,
    LUCK,
    MINING_FATIGUE,
    NAUSEA,
    NIGHT_VISION,
    OOZING,
    POISON,
    RAID_OMEN,
    REGENERATION,
    RESISTANCE,
    SATURATION,
    SLOW_FALLING,
    SLOWNESS,
    SPEED,
    STRENGTH,
    TRIAL_OMEN,
    UNLUCK,
    WATER_BREATHING,
    WEAKNESS,
    WEAVING,
    WIND_CHARGED,
    WITHER;

    @Override
    public String asString() {
        return this.name().toLowerCase();
    }
}
