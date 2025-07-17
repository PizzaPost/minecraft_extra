package de.pizzapost.minecraft_extra.damage;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> HONEY_BERRY_BUSH_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(MinecraftExtra.MOD_ID, "honey_berry_bush"));
}
