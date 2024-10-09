package de.pizzapost.minecraft_extra.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent INFINITE_POTATO = new FoodComponent.Builder().nutrition(15).
            saturationModifier(20f).
            statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 300,4),0.25f).
            build();

    public static final FoodComponent LOOTBOX = new FoodComponent.Builder().nutrition(0).
            saturationModifier(0f).build();
}
