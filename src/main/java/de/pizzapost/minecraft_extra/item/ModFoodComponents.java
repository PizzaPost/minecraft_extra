package de.pizzapost.minecraft_extra.item;

import de.pizzapost.minecraft_extra.effect.ModEffects;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

public class ModFoodComponents {
    public static final FoodComponent INFINITE_POTATO = new FoodComponent.Builder().nutrition(15).saturationModifier(20F).build();
    public static final ConsumableComponent INFINITE_POTATO_EFFECT = ConsumableComponents.food().consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 300, 4), 0.25F)).build();
    public static final FoodComponent HONEY_BERRY = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1F).build();
    public static final FoodComponent SOAP = new FoodComponent.Builder().nutrition(1).saturationModifier(0.1F).alwaysEdible().build();
    public static final ConsumableComponent SOAP_EFFECT = ConsumableComponents.food().consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(ModEffects.SOAPED, 1800, 0), 1F)).build();
}
