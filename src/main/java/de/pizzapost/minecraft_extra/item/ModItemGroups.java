package de.pizzapost.minecraft_extra.item;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup MINECRAFT_EXTRA_ITEMS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MinecraftExtra.MOD_ID, "minecraft_extra"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.PIZZAPOST_HEAD_ITEM))
                    .displayName(Text.translatable("itemgroup.minecraft_extra.minecraft_extra_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.PIZZAPOST_HEAD_ITEM);
                        entries.add(ModItems.ATTRIBUTE_CORE);
                        entries.add(ModItems.ATTRIBUTE_CORE_EXTRA_HEARTS);
                        entries.add(ModItems.ATTRIBUTE_CORE_GRAVITY);
                        entries.add(ModItems.ATTRIBUTE_CORE_SCALED);
                        entries.add(ModItems.ATTRIBUTE_CORE_SPICY);
                        entries.add(ModItems.ATTRIBUTE_CORE_SPIKES);
                        entries.add(ModItems.HEART);
                        entries.add(ModItems.INFINITE_POTATO);
                        entries.add(ModItems.WEATHER_ITEM);
                        entries.add(ModItems.ROTTEN_CHUNK);
                        entries.add(ModItems.LOOTBOX);
                        entries.add(ModItems.HARDENED_NETHERITE_INGOT);
                        entries.add(ModItems.HARDENED_NETHERITE_HELMET);
                        entries.add(ModItems.HARDENED_NETHERITE_CHESTPLATE);
                        entries.add(ModItems.HARDENED_NETHERITE_LEGGINGS);
                        entries.add(ModItems.HARDENED_NETHERITE_BOOTS);
                        entries.add(ModItems.HARDENED_NETHERITE_PICKAXE);
                        entries.add(ModItems.HARDENED_NETHERITE_SWORD);
                        entries.add(ModItems.HARDENED_NETHERITE_AXE);
                        entries.add(ModItems.HARDENED_NETHERITE_SHOVEL);
                        entries.add(ModItems.HARDENED_NETHERITE_HOE);
                        entries.add(ModItems.FLUTE);
                        entries.add(ModItems.GRAVITY_SHARD);
                        entries.add(ModItems.TP_STICK);
                        entries.add(ModItems.LIGHTNING_STICK);
                        entries.add(ModItems.ICEBOMB);
                        entries.add(ModItems.THROWABLE_FIRECHARGE);
                        entries.add(ModItems.STRONG_THROWABLE_FIRECHARGE);
                        entries.add(ModItems.SUPER_STRONG_THROWABLE_FIRECHARGE);
                        entries.add(ModItems.SUPER_FERTILIZER);
                        entries.add(ModItems.ILLUSIONER_ESSENCE);
                        entries.add(ModItems.GILDED_ILLUSIONER_ESSENCE);
                        entries.add(ModItems.EFFECT_GEM);
                        entries.add(ModItems.EFFECT_GEM_HASTE);
                        entries.add(ModItems.EFFECT_GEM_WATER_BREATHING);
                        entries.add(ModItems.EFFECT_GEM_SLOW_FALLING);
                        entries.add(ModItems.EFFECT_GEM_STRENGTH);
                        entries.add(ModItems.EFFECT_GEM_FIRE_RESISTANCE);
                        entries.add(ModItems.EFFECT_GEM_JUMP_BOOST);
                        entries.add(ModItems.EFFECT_GEM_SPEED);
                        entries.add(ModItems.EFFECT_GEM_PUSH);
                        entries.add(ModItems.HAMMER);
                        entries.add(ModItems.ICEBLAZE_SPAWN_EGG);
                        entries.add(ModItems.BIG_FLINT_AND_STEEL);
                        entries.add(ModItems.ASH);
                        entries.add(ModItems.HONEY_BERRIES);
                        entries.add(ModItems.HONEY_KEY);
                        entries.add(ModItems.HONEY_KEY_CLEAN);
                        entries.add(ModItems.BIOME_COMPASS);
                        entries.add(ModItems.FAT);
                        entries.add(ModItems.SOAP);
                    }).build());

    public static final ItemGroup MINECRAFT_EXTRA_BLOCKS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MinecraftExtra.MOD_ID, "minecraft_extra_block"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.PIZZAPOST_BLOCK))
                    .displayName(Text.translatable("itemgroup.minecraft_extra.minecraft_extra_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.PIZZAPOST_BLOCK);
                        entries.add(ModBlocks.XP_STORAGE);
                        entries.add(ModBlocks.FLINT_BLOCK);
                        entries.add(ModBlocks.FLINT_BRICKS);
                        entries.add(ModBlocks.CHISELED_FLINT_BRICKS);
                        entries.add(ModBlocks.FLINT_SLAB);
                        entries.add(ModBlocks.FLINT_STAIR);
                        entries.add(ModBlocks.FLINT_WALL);
                        entries.add(ModBlocks.FLINT_BRICK_SLAB);
                        entries.add(ModBlocks.FLINT_BRICK_STAIR);
                        entries.add(ModBlocks.FLINT_BRICK_WALL);
                        entries.add(ModBlocks.ASH_LAYER);
                        entries.add(ModBlocks.EFFECT_FARMLAND);
                        entries.add(ModBlocks.FEATHER_BLOCK);
                        entries.add(ModBlocks.HONEY_BERRY_BUSH);
                        entries.add(ModBlocks.WHITE_PETALS);
                        entries.add(ModBlocks.LIGHT_GRAY_PETALS);
                        entries.add(ModBlocks.GRAY_PETALS);
                        entries.add(ModBlocks.BLACK_PETALS);
                        entries.add(ModBlocks.BROWN_PETALS);
                        entries.add(ModBlocks.RED_PETALS);
                        entries.add(ModBlocks.ORANGE_PETALS);
                        entries.add(ModBlocks.YELLOW_PETALS);
                        entries.add(ModBlocks.LIME_PETALS);
                        entries.add(ModBlocks.GREEN_PETALS);
                        entries.add(ModBlocks.CYAN_PETALS);
                        entries.add(ModBlocks.LIGHT_BLUE_PETALS);
                        entries.add(ModBlocks.BLUE_PETALS);
                        entries.add(ModBlocks.PURPLE_PETALS);
                        entries.add(ModBlocks.MAGENTA_PETALS);
                    }).build());

    public static void registerItemGroups() {
        MinecraftExtra.LOGGER.info("Registering Item Groups for " + MinecraftExtra.MOD_ID);
    }
}
