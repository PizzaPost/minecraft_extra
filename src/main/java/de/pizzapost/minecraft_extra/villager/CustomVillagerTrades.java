package de.pizzapost.minecraft_extra.villager;

import de.pizzapost.minecraft_extra.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;

import java.util.Optional;

public class CustomVillagerTrades {
    public static void registerVillagerTrades() {
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BEEKEEPER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.HONEYCOMB),
                    new ItemStack(Items.HONEY_BOTTLE), 7, 2, 0.04F));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.SHEARS),
                    new ItemStack(Items.HONEYCOMB, 5), 7, 2, 0.02F));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BEEKEEPER, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.OXEYE_DAISY),
                    new ItemStack(Items.RED_TULIP), 6, 4, 0.1F));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.ORANGE_TULIP),
                    new ItemStack(Items.WHITE_TULIP), 6, 4, 0.1F));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.SUNFLOWER),
                    new ItemStack(Items.PINK_TULIP), 6, 4, 0.1F));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.ROSE_BUSH),
                    new ItemStack(Items.LILY_OF_THE_VALLEY), 6, 4, 0.1F));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BEEKEEPER, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.BEEHIVE),
                    new ItemStack(Items.HONEY_BOTTLE), 6, 9, 0.03F));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.HONEY_BOTTLE),
                    new ItemStack(Items.HONEYCOMB), 6, 4, 0.1F));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.GLASS_BOTTLE),
                    Optional.of(new TradedItem(Items.HONEYCOMB)),
                    new ItemStack(Items.HONEY_BOTTLE), 6, 6, 0.04F));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BEEKEEPER, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.CORNFLOWER),
                    new ItemStack(Items.PEONY), 6, 4, 0.1F));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.LILAC),
                    new ItemStack(Items.ALLIUM), 6, 4, 0.1F));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.BLUE_ORCHID),
                    new ItemStack(Items.DANDELION), 6, 4, 0.1F));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.POPPY),
                    new ItemStack(Items.AZURE_BLUET), 6, 4, 0.1F));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BEEKEEPER, 5, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.HONEY_BERRIES, 64),
                    Optional.of(new TradedItem(Blocks.HONEY_BLOCK, 9)),
                    new ItemStack(ModItems.HONEY_KEY), 1, 0, 2F));
        });
    }
}
