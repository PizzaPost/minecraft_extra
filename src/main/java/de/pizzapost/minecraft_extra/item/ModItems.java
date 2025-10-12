package de.pizzapost.minecraft_extra.item;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.block.ModBlocks;
import de.pizzapost.minecraft_extra.entity.ModEntities;
import de.pizzapost.minecraft_extra.item.custom.*;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.function.Consumer;
import java.util.function.Function;

public class ModItems {
    public static final Item ATTRIBUTE_CORE = registerItem("attribute_core", Item::new);
    public static final Item ATTRIBUTE_CORE_EXTRA_HEARTS = registerItem("attribute_core_extra_hearts", settings -> new AttributeCoreExtraHeartsItem(settings.maxCount(1)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item ATTRIBUTE_CORE_GRAVITY = registerItem("attribute_core_gravity", settings -> new AttributeCoreGravityItem(settings.maxCount(1)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item ATTRIBUTE_CORE_SCALED = registerItem("attribute_core_scaled", settings -> new AttributeCoreScaledItem(settings.maxCount(1)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.explain_attribute_core_scaled"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item ATTRIBUTE_CORE_SPICY = registerItem("attribute_core_spicy", settings -> new AttributeCoreSpicyItem(settings.maxCount(1).maxDamage(100)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item ATTRIBUTE_CORE_SPIKES = registerItem("attribute_core_spikes", settings -> new Item(settings.maxCount(1)));
    public static final Item HEART = registerItem("heart", settings -> new Item(settings.maxCount(16)));
    public static final Item INFINITE_POTATO = registerItem("infinite_potato", settings -> new InfinitePotatoItem(settings.maxCount(1).food(ModFoodComponents.INFINITE_POTATO, ModFoodComponents.INFINITE_POTATO_EFFECT).component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final Item WEATHER_ITEM = registerItem("weather_item", settings -> new WeatherItemItem(settings.maxCount(64)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item ROTTEN_CHUNK = registerItem("rotten_chunk", settings -> new Item(settings.maxCount(64)));
    public static final Item LOOTBOX = registerItem("lootbox", settings -> new LootboxItem(settings.maxCount(1)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item HARDENED_NETHERITE_INGOT = registerItem("hardened_netherite_ingot", settings -> new Item(settings.maxCount(64).fireproof()));
    public static final Item FLUTE = registerItem("flute", settings -> new FluteItem(settings.maxCount(1).maxDamage(64)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item GRAVITY_SHARD = registerItem("gravity_shard", settings -> new GravityShardItem(settings.maxCount(1)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hold"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item TP_STICK = registerItem("tp_stick", settings -> new TPStickItem(settings.maxCount(1)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click_tp_stick"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item LIGHTNING_STICK = registerItem("lightning_stick", settings -> new LightningStickItem(settings.maxCount(64)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.obtain_lightning_stick"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item ICEBOMB = registerItem("icebomb", settings -> new IcebombItem(settings.maxCount(16)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item HARDENED_NETHERITE_HELMET = registerItem("hardened_netherite_helmet", settings -> new ModArmorItem(settings.armor(ModArmorMaterials.HARDENED_NETHERITE_ARMOR_MATERIAL, EquipmentType.HELMET).fireproof().maxCount(1).rarity(Rarity.EPIC)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_armor"));
            textConsumer.accept(Text.literal(""));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor1"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor2"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor3"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor4"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor5"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor6"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor7"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item HARDENED_NETHERITE_CHESTPLATE = registerItem("hardened_netherite_chestplate", settings -> new Item(settings.armor(ModArmorMaterials.HARDENED_NETHERITE_ARMOR_MATERIAL, EquipmentType.CHESTPLATE).fireproof().maxCount(1).rarity(Rarity.EPIC)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_armor"));
            textConsumer.accept(Text.literal(""));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor1"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor2"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor3"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor4"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor5"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor6"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor7"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item HARDENED_NETHERITE_LEGGINGS = registerItem("hardened_netherite_leggings", settings -> new Item(settings.armor(ModArmorMaterials.HARDENED_NETHERITE_ARMOR_MATERIAL, EquipmentType.LEGGINGS).fireproof().maxCount(1).rarity(Rarity.EPIC)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_armor"));
            textConsumer.accept(Text.literal(""));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor1"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor2"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor3"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor4"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor5"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor6"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor7"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item HARDENED_NETHERITE_BOOTS = registerItem("hardened_netherite_boots", settings -> new Item(settings.armor(ModArmorMaterials.HARDENED_NETHERITE_ARMOR_MATERIAL, EquipmentType.BOOTS).fireproof().maxCount(1).rarity(Rarity.EPIC)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_armor"));
            textConsumer.accept(Text.literal(""));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor1"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor2"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor3"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor4"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor5"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor6"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor7"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item THROWABLE_FIRECHARGE = registerItem("throwable_fire_charge", settings -> new ThrowableFireChargeItem(settings.maxCount(64)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item SUPER_FERTILIZER = registerItem("super_fertilizer", settings -> new SuperFertilizerItem(settings.maxCount(64)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item ILLUSIONER_ESSENCE = registerItem("illusioner_essence", settings -> new Item(settings.maxCount(64)));
    public static final Item GILDED_ILLUSIONER_ESSENCE = registerItem("gilded_illusioner_essence", settings -> new Item(settings.maxCount(64)));
    public static final Item EFFECT_GEM = registerItem("effect_gem", settings -> new Item(settings.maxCount(1)));
    public static final Item EFFECT_GEM_HASTE = registerItem("effect_gem_haste", settings -> new Item(settings.maxCount(1)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hold"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item EFFECT_GEM_WATER_BREATHING = registerItem("effect_gem_water_breathing", settings -> new Item(settings.maxCount(1)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hold"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item EFFECT_GEM_SLOW_FALLING = registerItem("effect_gem_slow_falling", settings -> new Item(settings.maxCount(1)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hold"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item EFFECT_GEM_STRENGTH = registerItem("effect_gem_strength", settings -> new Item(settings.maxCount(1)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hold"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item EFFECT_GEM_FIRE_RESISTANCE = registerItem("effect_gem_fire_resistance", settings -> new Item(settings.maxCount(1)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hold"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item EFFECT_GEM_JUMP_BOOST = registerItem("effect_gem_jump_boost", settings -> new Item(settings.maxCount(1)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hold"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item EFFECT_GEM_SPEED = registerItem("effect_gem_speed", settings -> new Item(settings.maxCount(1)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hold"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item EFFECT_GEM_PUSH = registerItem("effect_gem_push", settings -> new Item(settings.maxCount(1)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hold"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item STRONG_THROWABLE_FIRECHARGE = registerItem("strong_throwable_fire_charge", settings -> new StrongThrowableFireChargeItem(settings.maxCount(16)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item SUPER_STRONG_THROWABLE_FIRECHARGE = registerItem("super_strong_throwable_fire_charge", settings -> new SuperStrongThrowableFireChargeItem(settings.maxCount(16)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item HAMMER = registerItem("hammer", settings -> new Item(settings.pickaxe(ToolMaterial.WOOD, 1.0F, -2.8F).maxCount(1).maxDamage(160)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click_hammer1"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click_hammer2"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item ICEBLAZE_SPAWN_EGG = registerItem("iceblaze_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(ModEntities.ICEBLAZE)) {
    });
    public static final Item HARDENED_NETHERITE_PICKAXE = registerItem("hardened_netherite_pickaxe", settings -> new HardenedNetheritePickaxeItem(ModToolMaterials.HARDENED_NETHERITE, 2.0F, -2.8F, settings.maxCount(1).maxDamage(9999).rarity(Rarity.EPIC)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_tool"));
            textConsumer.accept(Text.literal(""));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_pickaxe"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item HARDENED_NETHERITE_SWORD = registerItem("hardened_netherite_sword", settings -> new HardenedNetheriteSwordItem(ModToolMaterials.HARDENED_NETHERITE, 4, -2.4F, settings.maxCount(1).maxDamage(9999).rarity(Rarity.EPIC)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_tool"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item HARDENED_NETHERITE_AXE = registerItem("hardened_netherite_axe", settings -> new HardenedNetheriteAxeItem(ModToolMaterials.HARDENED_NETHERITE, 6.0F, -3.0F, settings.maxCount(1).maxDamage(9999).rarity(Rarity.EPIC)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_tool"));
            textConsumer.accept(Text.literal(""));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_tool_full_power"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_axe"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item HARDENED_NETHERITE_SHOVEL = registerItem("hardened_netherite_shovel", settings -> new HardenedNetheriteShovelItem(ModToolMaterials.HARDENED_NETHERITE, 2.5F, -3.0F, settings.maxCount(1).maxDamage(9999).rarity(Rarity.EPIC)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_tool"));
            textConsumer.accept(Text.literal(""));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_tool_full_power"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_shovel"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item HARDENED_NETHERITE_HOE = registerItem("hardened_netherite_hoe", settings -> new HardenedNetheriteHoeItem(ModToolMaterials.HARDENED_NETHERITE, -3.0F, 0.0F, settings.maxCount(1).maxDamage(9999).rarity(Rarity.EPIC)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_tool"));
            textConsumer.accept(Text.literal(""));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_tool_full_power"));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.hardened_netherite_hoe"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item BIG_FLINT_AND_STEEL = registerItem("big_flint_and_steel", settings -> new BigFlintAndSteelItem(settings.maxCount(1).maxDamage(128)));
    public static final Item ASH = registerItem("ash", settings -> new Item(settings.maxCount(64)));
    public static final Item HONEY_KEY = registerItem("honey_key", settings -> new HoneyKeyItem(settings.maxCount(1).rarity(Rarity.UNCOMMON)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.obtain_honey_key"));
            textConsumer.accept(Text.literal(""));
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.honey_key"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item HONEY_KEY_CLEAN = registerItem("honey_key_clean", settings -> new HoneyKeyCleanItem(settings.maxCount(1).rarity(Rarity.UNCOMMON)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.minecraft_extra.honey_key_clean"));
            textConsumer.accept(Text.literal(""));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });
    public static final Item HONEY_BERRIES = registerItem("honey_berries", settings -> new BlockItem(ModBlocks.HONEY_BERRY_BUSH, settings.maxCount(64).food(ModFoodComponents.HONEY_BERRY)));
    public static final Item BIOME_COMPASS = registerItem("biome_compass", settings -> new BiomeCompassItem(settings.maxCount(1)));
    public static final Item PIZZAPOST_HEAD_ITEM = registerItem("pizzapost_item", Item::new);
    public static final Item FAT = registerItem("fat", Item::new);
    public static final Item SOAP = registerItem("soap", settings -> new Item(settings.food(ModFoodComponents.SOAP, ModFoodComponents.SOAP_EFFECT)));
    public static final Item TIME_CONTROL_DEVICE = registerItem("time_control_device", settings -> new TimeControlDeviceItem(settings.maxCount(1)));
    public static final Item TIME_CONTROL_DEVICE_PIECE = registerItem("time_control_device_piece", Item::new);
    public static final Item ICE_BLAZE_ROD = registerItem("ice_blaze_rod", Item::new);
    public static final Item END_CRYSTAL_MOB_SPAWN_EGG = registerItem("end_crystal_mob_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(ModEntities.END_CRYSTAL_MOB)));

    public static Item registerBlock(Block block) {
        return registerBlock(new BlockItem(block, new Item.Settings()));
    }

    public static Item registerBlock(BlockItem item) {
        return registerBlock(item.getBlock(), item);
    }

    public static Item registerBlock(Block block, Item item) {
        return registerBlock(Registries.BLOCK.getId(block), item);
    }

    public static Item registerBlock(Identifier id, Item item) {
        return registerBlock(RegistryKey.of(Registries.ITEM.getKey(), id), item);
    }

    public static Item registerBlock(RegistryKey<Item> key, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem) item).appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registries.ITEM, key, item);
    }

    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(MinecraftExtra.MOD_ID, name), function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MinecraftExtra.MOD_ID, name)))));
    }

    public static void registerModItems() {
        MinecraftExtra.LOGGER.info("Registering Mod Items for " + MinecraftExtra.MOD_ID);
    }
}
