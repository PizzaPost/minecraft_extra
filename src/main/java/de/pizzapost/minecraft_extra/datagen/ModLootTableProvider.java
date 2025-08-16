package de.pizzapost.minecraft_extra.datagen;

import de.pizzapost.minecraft_extra.block.ModBlocks;
import de.pizzapost.minecraft_extra.block.custom.HoneyBerryBushBlock;
import de.pizzapost.minecraft_extra.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public static String[] suffixes = {"_SLAB", "_STAIR", "_WALL"};
    public static String[] baseNames = {"WHITE_WOOL", "LIGHT_GRAY_WOOL", "GRAY_WOOL", "BLACK_WOOL", "BROWN_WOOL", "RED_WOOL", "ORANGE_WOOL", "YELLOW_WOOL", "LIME_WOOL", "GREEN_WOOL", "CYAN_WOOL", "LIGHT_BLUE_WOOL", "BLUE_WOOL", "PURPLE_WOOL", "MAGENTA_WOOL", "PINK_WOOL", "TERRACOTTA", "WHITE_TERRACOTTA", "LIGHT_GRAY_TERRACOTTA", "GRAY_TERRACOTTA", "BLACK_TERRACOTTA", "BROWN_TERRACOTTA", "RED_TERRACOTTA", "ORANGE_TERRACOTTA", "YELLOW_TERRACOTTA", "LIME_TERRACOTTA", "GREEN_TERRACOTTA", "CYAN_TERRACOTTA", "LIGHT_BLUE_TERRACOTTA", "BLUE_TERRACOTTA", "PURPLE_TERRACOTTA", "MAGENTA_TERRACOTTA", "PINK_TERRACOTTA", "WHITE_GLAZED_TERRACOTTA", "LIGHT_GRAY_GLAZED_TERRACOTTA", "GRAY_GLAZED_TERRACOTTA", "BLACK_GLAZED_TERRACOTTA", "BROWN_GLAZED_TERRACOTTA", "RED_GLAZED_TERRACOTTA", "ORANGE_GLAZED_TERRACOTTA", "YELLOW_GLAZED_TERRACOTTA", "LIME_GLAZED_TERRACOTTA", "GREEN_GLAZED_TERRACOTTA", "CYAN_GLAZED_TERRACOTTA", "LIGHT_BLUE_GLAZED_TERRACOTTA", "BLUE_GLAZED_TERRACOTTA", "PURPLE_GLAZED_TERRACOTTA", "MAGENTA_GLAZED_TERRACOTTA", "PINK_GLAZED_TERRACOTTA", "WHITE_CONCRETE", "LIGHT_GRAY_CONCRETE", "GRAY_CONCRETE", "BLACK_CONCRETE", "BROWN_CONCRETE", "RED_CONCRETE", "ORANGE_CONCRETE", "YELLOW_CONCRETE", "LIME_CONCRETE", "GREEN_CONCRETE", "CYAN_CONCRETE", "LIGHT_BLUE_CONCRETE", "BLUE_CONCRETE", "PURPLE_CONCRETE", "MAGENTA_CONCRETE", "PINK_CONCRETE"};
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        addDrop(ModBlocks.FLINT_BLOCK);
        addDrop(ModBlocks.FLINT_BRICKS);
        addDrop(ModBlocks.CHISELED_FLINT_BRICKS);
        addDrop(ModBlocks.FLINT_SLAB);
        addDrop(ModBlocks.FLINT_STAIR);
        addDrop(ModBlocks.FLINT_WALL);
        addDrop(ModBlocks.FLINT_BRICK_SLAB);
        addDrop(ModBlocks.FLINT_BRICK_STAIR);
        addDrop(ModBlocks.FLINT_BRICK_WALL);
        addDrop(ModBlocks.FEATHER_BLOCK);
        this.addDrop(ModBlocks.HONEY_BERRY_BUSH, block -> this.applyExplosionDecay(block, LootTable.builder().pool(LootPool.builder().conditionally(BlockStatePropertyLootCondition.builder(ModBlocks.HONEY_BERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(HoneyBerryBushBlock.AGE, 3))).with(ItemEntry.builder(ModItems.HONEY_BERRIES)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F))).apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))).pool(LootPool.builder().conditionally(BlockStatePropertyLootCondition.builder(ModBlocks.HONEY_BERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(HoneyBerryBushBlock.AGE, 2))).with(ItemEntry.builder(ModItems.HONEY_BERRIES)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))).apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE))))));
        for (String base : baseNames) {
            for (String suffix : suffixes) {
                try {
                    Block block = (Block) ModBlocks.class.getField(base + suffix).get(null);
                    addDrop(block);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException("Failed to add drop for " + base + suffix, e);
                }
            }
        }

    }
}
