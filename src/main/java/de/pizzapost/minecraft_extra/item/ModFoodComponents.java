package de.pizzapost.minecraft_extra.item;

import de.pizzapost.minecraft_extra.effect.ModEffects;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent INFINITE_POTATO = new FoodComponent.Builder().nutrition(15).
            saturationModifier(20F).
            statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 300,4),0.25F)
            .build();
    public static final FoodComponent HONEY_BERRY = new FoodComponent.Builder().nutrition(2).
            saturationModifier(0.1F).snack().build();
    public static final FoodComponent SOAP = new FoodComponent.Builder().nutrition(1).
            saturationModifier(0.1F).alwaysEdible().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 1050,0), 1)
            .statusEffect(new StatusEffectInstance(ModEffects.SOAPED, 1000,0), 1)
            .build();
}
