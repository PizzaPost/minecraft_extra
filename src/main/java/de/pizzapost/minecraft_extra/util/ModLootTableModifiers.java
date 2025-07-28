package de.pizzapost.minecraft_extra.util;

import de.pizzapost.minecraft_extra.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;


public class ModLootTableModifiers {
    private static final Identifier POTATO_BLOCK_ID = Identifier.of("minecraft", "blocks/potato");
    private static final Identifier GRAVEL_ID = Identifier.of("minecraft", "blocks/gravel");
    private static final Identifier ENDERMAN_ID = Identifier.of("minecraft", "entities/enderman");
    private static final Identifier ILLUSIONER_ID = Identifier.of("minecraft", "entities/illusioner");


    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {

            if (ENDERMAN_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).with(ItemEntry.builder(ModItems.HEART)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0f, 1f)).build()).conditionally(RandomChanceLootCondition.builder(.01f));
                tableBuilder.pool(poolBuilder.build());
            }

            if (POTATO_BLOCK_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).with(ItemEntry.builder(ModItems.INFINITE_POTATO)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0f, 1f)).build()).conditionally(RandomChanceLootCondition.builder(.002f));
                tableBuilder.pool(poolBuilder.build());
            }

            if (GRAVEL_ID.equals(key.getValue())) {
                LootPool.Builder ironNuggetPool = LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).with(ItemEntry.builder(Items.IRON_NUGGET)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0f, 1f)).build()).conditionally(RandomChanceLootCondition.builder(0.1f));
                tableBuilder.pool(ironNuggetPool.build());
                LootPool.Builder diamondPool = LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).with(ItemEntry.builder(Items.DIAMOND)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0f, 1f)).build()).conditionally(RandomChanceLootCondition.builder(0.0001f));
                tableBuilder.pool(diamondPool.build());
            }

            if (ILLUSIONER_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).with(ItemEntry.builder(ModItems.ILLUSIONER_ESSENCE)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0f, 1f)).build()).conditionally(RandomChanceLootCondition.builder(.4f));
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}