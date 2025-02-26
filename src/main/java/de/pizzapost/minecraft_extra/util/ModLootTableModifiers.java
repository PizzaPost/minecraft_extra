package de.pizzapost.minecraft_extra.util;

import de.pizzapost.minecraft_extra.entity.ModEntities;
import de.pizzapost.minecraft_extra.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;


public class ModLootTableModifiers {
    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {

            if(EntityType.ENDERMAN.getLootTableId() == key) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(ModItems.HEART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0f, 1f)).build())
                        .conditionally(RandomChanceLootCondition.builder(.01f));
                tableBuilder.pool(poolBuilder.build());
            }

            if(Blocks.POTATOES.getLootTableKey() == key) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(ModItems.INFINITE_POTATO))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0f, 1f)).build())
                        .conditionally(RandomChanceLootCondition.builder(.002f));
                tableBuilder.pool(poolBuilder.build());
            }

            if (Blocks.GRAVEL.getLootTableKey() == key) {
                LootPool.Builder ironNuggetPool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(Items.IRON_NUGGET))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0f, 1f)).build())
                        .conditionally(RandomChanceLootCondition.builder(0.1f));
                tableBuilder.pool(ironNuggetPool.build());
                LootPool.Builder diamondPool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(Items.DIAMOND))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0f, 1f)).build())
                        .conditionally(RandomChanceLootCondition.builder(0.0001f));
                tableBuilder.pool(diamondPool.build());
            }

            if(EntityType.ILLUSIONER.getLootTableId() == key) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(ModItems.ILLUSIONER_ESSENCE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0f, 1f)).build())
                        .conditionally(RandomChanceLootCondition.builder(.4f));
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
