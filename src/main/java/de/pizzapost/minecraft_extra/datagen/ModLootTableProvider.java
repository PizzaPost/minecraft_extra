package de.pizzapost.minecraft_extra.datagen;

import de.pizzapost.minecraft_extra.block.ModBlocks;
import de.pizzapost.minecraft_extra.block.custom.HoneyBerryBushBlock;
import de.pizzapost.minecraft_extra.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
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
    }
}
