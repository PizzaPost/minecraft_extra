package de.pizzapost.minecraft_extra.effect;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.effect.custom.FreezeEffect;
import de.pizzapost.minecraft_extra.effect.custom.SoapedEffect;
import de.pizzapost.minecraft_extra.effect.custom.SpiderEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> SPIDER = registerStatusEffect("spider", new SpiderEffect(StatusEffectCategory.NEUTRAL, 0x000000));
    public static final RegistryEntry<StatusEffect> FREEZE = registerStatusEffect("freeze", new FreezeEffect(StatusEffectCategory.HARMFUL, 0x89EDFF));
    public static final RegistryEntry<StatusEffect> SOAPED = registerStatusEffect("soaped", new SoapedEffect(StatusEffectCategory.HARMFUL, 0xffa3fd));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(MinecraftExtra.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        MinecraftExtra.LOGGER.info("Registering Mod Effects for " + MinecraftExtra.MOD_ID);
    }
}
