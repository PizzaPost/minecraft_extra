package de.pizzapost.minecraft_extra.item;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.block.ModBlocks;
import de.pizzapost.minecraft_extra.entity.ModEntities;
import de.pizzapost.minecraft_extra.item.custom.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.List;

public class ModItems {
    public static final Item ATTRIBUTE_CORE = registerItem("attribute_core", new Item(new Item.Settings()));
    public static final Item ATTRIBUTE_CORE_EXTRA_HEARTS = registerItem("attribute_core_extra_hearts",
            new AttributeCoreExtraHeartsItem(new Item.Settings().maxCount(1)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item ATTRIBUTE_CORE_GRAVITY = registerItem("attribute_core_gravity",
            new AttributeCoreGravityItem(new Item.Settings().maxCount(1)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item ATTRIBUTE_CORE_SCALED = registerItem("attribute_core_scaled",
            new AttributeCoreScaledItem(new Item.Settings().maxCount(1)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.explain_attribute_core_scaled"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item ATTRIBUTE_CORE_SPICY = registerItem("attribute_core_spicy",
            new AttributeCoreSpicyItem(new Item.Settings().maxCount(1).maxDamage(100)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item ATTRIBUTE_CORE_SPIKES = registerItem("attribute_core_spikes",
            new Item(new Item.Settings().maxCount(1)));
    public static final Item HEART = registerItem("heart", new Item(new Item.Settings().maxCount(16)));
    public static final Item INFINITE_POTATO = registerItem("infinite_potato",
            new InfinitePotatoItem(new Item.Settings().maxCount(1).food(ModFoodComponents.INFINITE_POTATO).
                    component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final Item WEATHER_ITEM = registerItem("weather_item",
            new WeatherItemItem(new Item.Settings().maxCount(64)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item ROTTEN_CHUNK = registerItem("rotten_chunk",
            new Item(new Item.Settings().maxCount(64)));
    public static final Item LOOTBOX = registerItem("lootbox",
            new LootboxItem(new Item.Settings().maxCount(1)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item HARDENED_NETHERITE_INGOT = registerItem("hardened_netherite_ingot",
            new Item(new Item.Settings().maxCount(64).fireproof()));
    public static final Item FLUTE = registerItem("flute",
            new FluteItem(new Item.Settings().maxCount(1).maxDamage(64)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item GRAVITY_SHARD = registerItem("gravity_shard",
            new GravityShardItem(new Item.Settings().maxCount(1)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hold"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item TP_STICK = registerItem("tp_stick",
            new TPStickItem(new Item.Settings().maxCount(1)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click_tp_stick"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item LIGHTNING_STICK = registerItem("lightning_stick",
            new LightningStickItem(new Item.Settings().maxCount(64).maxDamage(64)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.obtain_lightning_stick"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item ICEBOMB = registerItem("icebomb",
            new IcebombItem(new Item.Settings().maxCount(16)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item HARDENED_NETHERITE_HELMET = registerItem("hardened_netherite_helmet",
            new ModArmorItem(ModArmorMaterials.HARDENED_NETHERITE_ARMOR_MATERIAL,
                    ArmorItem.Type.HELMET, new Item.Settings().fireproof().maxCount(1).rarity(Rarity.EPIC)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_armor"));
                    tooltip.add(Text.literal(""));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor1"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor2"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor3"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor4"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor5"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor6"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor7"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item HARDENED_NETHERITE_CHESTPLATE = registerItem("hardened_netherite_chestplate",
            new ArmorItem(ModArmorMaterials.HARDENED_NETHERITE_ARMOR_MATERIAL,
                    ArmorItem.Type.CHESTPLATE, new Item.Settings().fireproof().maxCount(1).rarity(Rarity.EPIC)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_armor"));
                    tooltip.add(Text.literal(""));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor1"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor2"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor3"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor4"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor5"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor6"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor7"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item HARDENED_NETHERITE_LEGGINGS = registerItem("hardened_netherite_leggings",
            new ArmorItem(ModArmorMaterials.HARDENED_NETHERITE_ARMOR_MATERIAL,
                    ArmorItem.Type.LEGGINGS, new Item.Settings().fireproof().maxCount(1).rarity(Rarity.EPIC)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_armor"));
                    tooltip.add(Text.literal(""));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor1"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor2"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor3"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor4"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor5"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor6"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor7"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item HARDENED_NETHERITE_BOOTS = registerItem("hardened_netherite_boots",
            new ArmorItem(ModArmorMaterials.HARDENED_NETHERITE_ARMOR_MATERIAL,
                    ArmorItem.Type.BOOTS, new Item.Settings().fireproof().maxCount(1).rarity(Rarity.EPIC)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_armor"));
                    tooltip.add(Text.literal(""));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor1"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor2"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor3"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor4"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor5"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor6"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_armor7"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item THROWABLE_FIRECHARGE = registerItem("throwable_fire_charge",
            new ThrowableFireChargeItem(new Item.Settings().maxCount(64)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item SUPER_FERTILIZER = registerItem("super_fertilizer",
            new SuperFertilizerItem(new Item.Settings().maxCount(64)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item ILLUSIONER_ESSENCE = registerItem("illusioner_essence",
            new Item(new Item.Settings().maxCount(64)));
    public static final Item GILDED_ILLUSIONER_ESSENCE = registerItem("gilded_illusioner_essence",
            new Item(new Item.Settings().maxCount(64)));
    public static final Item EFFECT_GEM = registerItem("effect_gem",
            new Item(new Item.Settings().maxCount(1)));
    public static final Item EFFECT_GEM_HASTE = registerItem("effect_gem_haste",
            new Item(new Item.Settings().maxCount(1)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hold"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item EFFECT_GEM_WATER_BREATHING = registerItem("effect_gem_water_breathing",
            new Item(new Item.Settings().maxCount(1)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hold"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item EFFECT_GEM_SLOW_FALLING = registerItem("effect_gem_slow_falling",
            new Item(new Item.Settings().maxCount(1)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hold"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item EFFECT_GEM_STRENGTH = registerItem("effect_gem_strength",
            new Item(new Item.Settings().maxCount(1)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hold"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item EFFECT_GEM_FIRE_RESISTANCE = registerItem("effect_gem_fire_resistance",
            new Item(new Item.Settings().maxCount(1)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hold"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item EFFECT_GEM_JUMP_BOOST = registerItem("effect_gem_jump_boost",
            new Item(new Item.Settings().maxCount(1)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hold"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item EFFECT_GEM_SPEED = registerItem("effect_gem_speed",
            new Item(new Item.Settings().maxCount(1)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hold"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item EFFECT_GEM_PUSH = registerItem("effect_gem_push",
            new Item(new Item.Settings().maxCount(1)) {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hold"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item STRONG_THROWABLE_FIRECHARGE = registerItem("strong_throwable_fire_charge",
            new StrongThrowableFireChargeItem(new Item.Settings().maxCount(16)) {
                @Override
                public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item SUPER_STRONG_THROWABLE_FIRECHARGE = registerItem("super_strong_throwable_fire_charge",
            new SuperStrongThrowableFireChargeItem(new Item.Settings().maxCount(16)) {
                @Override
                public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item HAMMER = registerItem("hammer",
            new PickaxeItem(ToolMaterials.WOOD,
                    new Item.Settings().maxCount(1).maxDamage(160)
                            .attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.WOOD,
                                    1.0F, -2.8F))) {
                @Override
                public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click_hammer1"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click_hammer2"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item ICEBLAZE_SPAWN_EGG = registerItem("iceblaze_spawn_egg",
            new SpawnEggItem(ModEntities.ICEBLAZE, 0x59867d, 0x6dc8b6,new Item.Settings()) {
            });
    public static final Item HARDENED_NETHERITE_PICKAXE = registerItem("hardened_netherite_pickaxe",
            new HardenedNetheritePickaxeItem(ModToolMaterials.HARDENED_NETHERITE,
                    new Item.Settings().maxCount(1).maxDamage(9999).rarity(Rarity.EPIC)
                            .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.HARDENED_NETHERITE,
                                    2.0F, -2.8F))) {
                @Override
                public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_tool"));
                    tooltip.add(Text.literal(""));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_pickaxe"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item HARDENED_NETHERITE_SWORD = registerItem("hardened_netherite_sword",
            new HardenedNetheriteSwordItem(ModToolMaterials.HARDENED_NETHERITE,
                    new Item.Settings().maxCount(1).maxDamage(9999).rarity(Rarity.EPIC)
                            .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.HARDENED_NETHERITE,
                                    4, -2.4F))) {
                @Override
                public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_tool"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item HARDENED_NETHERITE_AXE = registerItem("hardened_netherite_axe",
            new HardenedNetheriteAxeItem(ModToolMaterials.HARDENED_NETHERITE,
                    new Item.Settings().maxCount(1).maxDamage(9999).rarity(Rarity.EPIC)
                            .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.HARDENED_NETHERITE,
                                    6.0F, -3.0F))) {
                @Override
                public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_tool"));
                    tooltip.add(Text.literal(""));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_tool_full_power"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_axe"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item HARDENED_NETHERITE_SHOVEL = registerItem("hardened_netherite_shovel",
            new HardenedNetheriteShovelItem(ModToolMaterials.HARDENED_NETHERITE,
                    new Item.Settings().maxCount(1).maxDamage(9999).rarity(Rarity.EPIC)
                            .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.HARDENED_NETHERITE,
                                    2.5F, -3.0F))) {
                @Override
                public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_tool"));
                    tooltip.add(Text.literal(""));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_tool_full_power"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_shovel"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item HARDENED_NETHERITE_HOE = registerItem("hardened_netherite_hoe",
            new HardenedNetheriteHoeItem(ModToolMaterials.HARDENED_NETHERITE,
                    new Item.Settings().maxCount(1).maxDamage(9999).rarity(Rarity.EPIC)
                            .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.HARDENED_NETHERITE,
                                    -3.0F, 0.0F))) {
                @Override
                public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.right_click_hardened_netherite_tool"));
                    tooltip.add(Text.literal(""));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_tool_full_power"));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.hardened_netherite_hoe"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item BIG_FLINT_AND_STEEL = registerItem("big_flint_and_steel",
            new BigFlintAndSteelItem(new Item.Settings().maxCount(1).maxDamage(128)));
    public static final Item ASH = registerItem("ash",
            new Item(new Item.Settings().maxCount(64)));
    public static final Item HONEY_KEY = registerItem("honey_key",
            new HoneyKeyItem(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)) {
                @Override
                public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.obtain_honey_key"));
                    tooltip.add(Text.literal(""));
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.honey_key"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item HONEY_KEY_CLEAN = registerItem("honey_key_clean",
            new HoneyKeyCleanItem(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)) {
                @Override
                public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.minecraft_extra.honey_key_clean"));
                    tooltip.add(Text.literal(""));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item HONEY_BERRIES = registerItem("honey_berries",
            new AliasedBlockItem(ModBlocks.HONEY_BERRY_BUSH, new Item.Settings().maxCount(64).food(ModFoodComponents.HONEY_BERRY)));
    public static final Item BIOME_COMPASS = registerItem("biome_compass",
            new BiomeCompassItem(new Item.Settings().maxCount(1)));
    public static final Item WHITE_PETALS = registerBlock(ModBlocks.WHITE_PETALS);
    public static final Item LIGHT_GRAY_PETALS = registerBlock(ModBlocks.LIGHT_GRAY_PETALS);
    public static final Item GRAY_PETALS = registerBlock(ModBlocks.GRAY_PETALS);
    public static final Item BLACK_PETALS = registerBlock(ModBlocks.BLACK_PETALS);
    public static final Item BROWN_PETALS = registerBlock(ModBlocks.BROWN_PETALS);
    public static final Item RED_PETALS = registerBlock(ModBlocks.RED_PETALS);
    public static final Item ORANGE_PETALS = registerBlock(ModBlocks.ORANGE_PETALS);
    public static final Item YELLOW_PETALS = registerBlock(ModBlocks.YELLOW_PETALS);
    public static final Item LIME_PETALS = registerBlock(ModBlocks.LIME_PETALS);
    public static final Item GREEN_PETALS = registerBlock(ModBlocks.GREEN_PETALS);
    public static final Item CYAN_PETALS = registerBlock(ModBlocks.CYAN_PETALS);
    public static final Item LIGHT_BLUE_PETALS = registerBlock(ModBlocks.LIGHT_BLUE_PETALS);
    public static final Item BLUE_PETALS = registerBlock(ModBlocks.BLUE_PETALS);
    public static final Item PURPLE_PETALS = registerBlock(ModBlocks.PURPLE_PETALS);
    public static final Item MAGENTA_PETALS = registerBlock(ModBlocks.MAGENTA_PETALS);
    public static final Item PIZZAPOST_HEAD_ITEM = registerItem("pizzapost_item",
            new Item(new Item.Settings()));
    public static final Item FAT = registerItem("fat",
            new Item(new Item.Settings()));
    public static final Item SOAP = registerItem("soap",
            new Item(new Item.Settings().food(ModFoodComponents.SOAP)));
    public static final Item TIME_CONTROL_DEVICE = registerItem("time_control_device",
            new TimeControlDeviceItem(new Item.Settings().maxCount(1)));
    public static final Item TIME_CONTROL_DEVICE_PIECE = registerItem("time_control_device_piece",
            new Item(new Item.Settings()));
    public static final Item ICE_BLAZE_ROD = registerItem("ice_blaze_rod",
            new Item(new Item.Settings()));

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
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registries.ITEM, key, item);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MinecraftExtra.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MinecraftExtra.LOGGER.info("Registering Mod Items for " + MinecraftExtra.MOD_ID);
    }
}
