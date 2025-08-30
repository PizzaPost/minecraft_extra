package de.pizzapost.minecraft_extra.datagen;

import de.pizzapost.minecraft_extra.block.ModBlocks;
import de.pizzapost.minecraft_extra.block.custom.HoneyBerryBushBlock;
import de.pizzapost.minecraft_extra.item.ModArmorMaterials;
import de.pizzapost.minecraft_extra.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.XP_STORAGE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_FLINT_BRICKS);
        BlockStateModelGenerator.BlockTexturePool flintBlockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.FLINT_BLOCK);
        BlockStateModelGenerator.BlockTexturePool flintBrickBlockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.FLINT_BRICKS);
        flintBlockPool.slab(ModBlocks.FLINT_SLAB);
        flintBlockPool.stairs(ModBlocks.FLINT_STAIR);
        flintBlockPool.wall(ModBlocks.FLINT_WALL);
        flintBrickBlockPool.slab(ModBlocks.FLINT_BRICK_SLAB);
        flintBrickBlockPool.stairs(ModBlocks.FLINT_BRICK_STAIR);
        flintBrickBlockPool.wall(ModBlocks.FLINT_BRICK_WALL);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FEATHER_BLOCK);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.HONEY_BERRY_BUSH, BlockStateModelGenerator.CrossType.NOT_TINTED, HoneyBerryBushBlock.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PIZZAPOST_BLOCK);
        for (String name : ModLootTableProvider.baseNames) {
            try {
                Block base = (Block) Blocks.class.getField(name).get(null);
                BlockStateModelGenerator.BlockTexturePool pool = blockStateModelGenerator.registerCubeAllModelTexturePool(base);
                Block slab = (Block) ModBlocks.class.getField(name + "_SLAB").get(null);
                Block stairs = (Block) ModBlocks.class.getField(name + "_STAIR").get(null);
                Block wall = (Block) ModBlocks.class.getField(name + "_WALL").get(null);
                pool.slab(slab);
                pool.stairs(stairs);
                pool.wall(wall);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Failed to register block pool for " + name, e);
            }
        }
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HARDENED_NETHERITE_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ATTRIBUTE_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ATTRIBUTE_CORE_EXTRA_HEARTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.ATTRIBUTE_CORE_GRAVITY, Models.GENERATED);
        itemModelGenerator.register(ModItems.ATTRIBUTE_CORE_SCALED, Models.GENERATED);
        itemModelGenerator.register(ModItems.ATTRIBUTE_CORE_SPICY, Models.GENERATED);
        itemModelGenerator.register(ModItems.ATTRIBUTE_CORE_SPIKES, Models.GENERATED);
        itemModelGenerator.register(ModItems.HEART, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFINITE_POTATO, Models.GENERATED);
        itemModelGenerator.register(ModItems.WEATHER_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROTTEN_CHUNK, Models.GENERATED);
        itemModelGenerator.register(ModItems.LOOTBOX, Models.GENERATED);
        itemModelGenerator.register(ModItems.HARDENED_NETHERITE_INGOT, Models.GENERATED);
        itemModelGenerator.registerArmor((ModItems.HARDENED_NETHERITE_HELMET), ModArmorMaterials.HARDENED_NETHERITE_KEY, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor((ModItems.HARDENED_NETHERITE_CHESTPLATE), ModArmorMaterials.HARDENED_NETHERITE_KEY, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor((ModItems.HARDENED_NETHERITE_LEGGINGS), ModArmorMaterials.HARDENED_NETHERITE_KEY, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor((ModItems.HARDENED_NETHERITE_BOOTS), ModArmorMaterials.HARDENED_NETHERITE_KEY, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);
        itemModelGenerator.register(ModItems.FLUTE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GRAVITY_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.TP_STICK, Models.GENERATED);
        itemModelGenerator.register(ModItems.LIGHTNING_STICK, Models.GENERATED);
        itemModelGenerator.register(ModItems.ICEBOMB, Models.GENERATED);
        itemModelGenerator.register(ModItems.THROWABLE_FIRECHARGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.STRONG_THROWABLE_FIRECHARGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SUPER_STRONG_THROWABLE_FIRECHARGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SUPER_FERTILIZER, Models.GENERATED);
        itemModelGenerator.register(ModItems.ILLUSIONER_ESSENCE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GILDED_ILLUSIONER_ESSENCE, Models.GENERATED);
        itemModelGenerator.register(ModItems.EFFECT_GEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.EFFECT_GEM_HASTE, Models.GENERATED);
        itemModelGenerator.register(ModItems.EFFECT_GEM_WATER_BREATHING, Models.GENERATED);
        itemModelGenerator.register(ModItems.EFFECT_GEM_SLOW_FALLING, Models.GENERATED);
        itemModelGenerator.register(ModItems.EFFECT_GEM_STRENGTH, Models.GENERATED);
        itemModelGenerator.register(ModItems.EFFECT_GEM_FIRE_RESISTANCE, Models.GENERATED);
        itemModelGenerator.register(ModItems.EFFECT_GEM_JUMP_BOOST, Models.GENERATED);
        itemModelGenerator.register(ModItems.EFFECT_GEM_SPEED, Models.GENERATED);
        itemModelGenerator.register(ModItems.EFFECT_GEM_PUSH, Models.GENERATED);
        itemModelGenerator.register(ModItems.HAMMER, Models.GENERATED);
        itemModelGenerator.register(ModItems.ICEBLAZE_SPAWN_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.HARDENED_NETHERITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HARDENED_NETHERITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HARDENED_NETHERITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HARDENED_NETHERITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HARDENED_NETHERITE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ASH, Models.GENERATED);
        itemModelGenerator.register(ModItems.HONEY_KEY, Models.GENERATED);
        itemModelGenerator.register(ModItems.HONEY_KEY_CLEAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.BIOME_COMPASS, Models.GENERATED);
        itemModelGenerator.register(ModItems.PIZZAPOST_HEAD_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.FAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SOAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.ICE_BLAZE_ROD, Models.GENERATED);
        itemModelGenerator.register(ModItems.TIME_CONTROL_DEVICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.TIME_CONTROL_DEVICE_PIECE, Models.GENERATED);
        itemModelGenerator.register(ModItems.END_CRYSTAL_MOB_SPAWN_EGG, Models.GENERATED);
    }
}
