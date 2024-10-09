package de.pizzapost.minecraft_extra.item;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item ATTRIBUTE_CORE = registerItem("attribute_core", new Item(new Item.Settings()));
    public static final Item ATTRIBUTE_CORE_EXTRA_HEARTS = registerItem("attribute_core_extra_hearts",
            new AttributeCoreExtraHeartsItem(new Item.Settings().maxCount(1)));
    public static final Item ATTRIBUTE_CORE_GRAVITY = registerItem("attribute_core_gravity",
            new AttributeCoreGravityItem(new Item.Settings().maxCount(1)));
    public static final Item ATTRIBUTE_CORE_SCALED = registerItem("attribute_core_scaled",
            new AttributeCoreScaledItem(new Item.Settings().maxCount(1)));
    public static final Item ATTRIBUTE_CORE_SPICY = registerItem("attribute_core_spicy",
            new AttributeCoreSpicyItem(new Item.Settings().maxCount(1).maxDamage(500)));
    public static final Item ATTRIBUTE_CORE_SPIKES = registerItem("attribute_core_spikes",
            new Item(new Item.Settings().maxCount(1)));
    public static final Item HEART = registerItem("heart", new Item(new Item.Settings().maxCount(16)));
    public static final Item INFINITE_POTATO = registerItem("infinite_potato",
            new InfinitePotatoItem(new Item.Settings().maxCount(1).food(ModFoodComponents.INFINITE_POTATO).
                    component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final Item WEATHER_ITEM = registerItem("weather_item",
            new WeatherItemItem(new Item.Settings().maxCount(64)));
    public static final Item ROTTEN_CHUNK = registerItem("rotten_chunk",
            new Item(new Item.Settings().maxCount(64)));
    public static final Item LOOTBOX = registerItem("lootbox",
            new Item(new Item.Settings().maxCount(1).food(ModFoodComponents.LOOTBOX)));
    public static final Item HARDENED_NETHERITE_INGOT = registerItem("hardened_netherite_ingot",
            new Item(new Item.Settings().maxCount(64).fireproof()));
    public static final Item FLUTE = registerItem("flute",
            new FluteItem(new Item.Settings().maxCount(1).maxDamage(64)));
    public static final Item GRAVITY_SHARD = registerItem("gravity_shard",
            new GravityShardItem(new Item.Settings().maxCount(1)));
    public static final Item TP_STICK = registerItem("tp_stick",
            new TPStickItem(new Item.Settings().maxCount(1)));
    public static final Item LIGHTNING_STICK = registerItem("lightning_stick",
            new LightningStickItem(new Item.Settings().maxCount(64).maxDamage(64)));




    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MinecraftExtra.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MinecraftExtra.LOGGER.info("Registering Mod Items for " + MinecraftExtra.MOD_ID);
    }
}
