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
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BEEKEEPER_KEY, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.HONEYCOMB), new ItemStack(Items.HONEY_BOTTLE), 7, 2, 0.04F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.SHEARS), new ItemStack(Items.HONEYCOMB, 5), 7, 2, 0.02F));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BEEKEEPER_KEY, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.HONEY_BOTTLE), new ItemStack(Items.EMERALD, 3), 7, 2, 0.04F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.CORNFLOWER), new ItemStack(Items.EMERALD), 7, 2, 0.02F));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BEEKEEPER_KEY, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.OXEYE_DAISY), new ItemStack(Items.HONEY_BLOCK), 6, 3, 0.1F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.ORANGE_TULIP), new ItemStack(Items.SUNFLOWER, 2), 6, 3, 0.1F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.RED_TULIP, 2), new ItemStack(Items.CORNFLOWER), 6, 3, 0.1F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.WHITE_TULIP, 12), new ItemStack(Items.BEEHIVE), 6, 3, 0.1F));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BEEKEEPER_KEY, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD), new ItemStack(Items.SUNFLOWER, 9), 4, 6, -0.1F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD), new ItemStack(Items.ROSE_BUSH, 8), 4, 6, -0.1F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD), new ItemStack(Items.PINK_TULIP, 4), 4, 6, 0.1F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD), new ItemStack(Items.LILY_OF_THE_VALLEY, 3), 4, 7, 0.1F));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BEEKEEPER_KEY, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.BEEHIVE), new ItemStack(Items.HONEY_BOTTLE), 6, 9, 0.03F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.HONEY_BOTTLE), new ItemStack(Items.HONEYCOMB), 6, 4, 0.1F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.GLASS_BOTTLE), Optional.of(new TradedItem(Items.HONEYCOMB)), new ItemStack(Items.HONEY_BOTTLE), 6, 4, 0.04F));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BEEKEEPER_KEY, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.BLUE_ORCHID), new ItemStack(Items.DANDELION), 12, 3, 0.1F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.POPPY), new ItemStack(Items.AZURE_BLUET), 12, 3, 0.1F));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BEEKEEPER_KEY, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.CORNFLOWER), new ItemStack(Items.PEONY), 12, 3, 0.1F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.LILAC), new ItemStack(Items.ALLIUM), 12, 3, 0.1F));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BEEKEEPER_KEY, 5, factories -> {
            factories.add((entity, random) -> new TradeOffer(new TradedItem(ModItems.HONEY_BERRIES, 32), Optional.of(new TradedItem(Blocks.HONEY_BLOCK, 7)), new ItemStack(ModItems.HONEY_KEY), 1, 0, 2F));
        });


        TradeOfferHelper.registerVillagerOffers(ModVillagers.COPPERER_KEY, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD), new ItemStack(Items.COPPER_DOOR, 3), 8, 7, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.COPPER_GRATE, 6), new ItemStack(Items.EMERALD), 7, 2, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.CUT_COPPER_SLAB, 6), new ItemStack(Items.EMERALD), 7, 2, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.COPPER_DOOR, 3), new ItemStack(Items.EMERALD), 7, 2, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD), new ItemStack(Items.CUT_COPPER_STAIRS), 8, 2, 0.02F));
        });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.COPPERER_KEY, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.CHISELED_COPPER, 3), new ItemStack(Items.EMERALD), 4, 6, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD), new ItemStack(Items.COPPER_INGOT, 22), 5, 4, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD), new ItemStack(Items.COPPER_BLOCK, 3), 4, 4, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD), new ItemStack(Items.COPPER_TRAPDOOR, 8), 7, 4, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD, 3), new ItemStack(Items.CUT_COPPER, 9), 12, 4, 0.02F));
        });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.COPPERER_KEY, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD, 3), new ItemStack(Items.COPPER_GRATE), 12, 6, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.COPPER_INGOT, 8), new ItemStack(Items.EMERALD), 3, 6, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.RAW_COPPER, 15), new ItemStack(Items.EMERALD), 5, 4, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD, 2), new ItemStack(Items.CHISELED_COPPER, 6), 7, 6, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD, 2), new ItemStack(Items.CUT_COPPER_SLAB, 8), 9, 6, 0.02F));
        });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.COPPERER_KEY, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.COPPER_BULB), new ItemStack(Items.EMERALD), 7, 7, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.SPYGLASS), new ItemStack(Items.EMERALD), 7, 7, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.LIGHTNING_ROD, 9), new ItemStack(Items.EMERALD), 7, 7, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.COPPER_BLOCK, 9), new ItemStack(Items.EMERALD, 2), 7, 7, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.RAW_COPPER, 32), new ItemStack(Items.EMERALD), 7, 2, 0.02F));
        });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.COPPERER_KEY, 5, factories -> {
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD), new ItemStack(Items.LIGHTNING_ROD, 3), 8, 0, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD), new ItemStack(Items.COPPER_BLOCK, 12), 7, 0, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD), new ItemStack(Items.SPYGLASS), 4, 0, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.CHISELED_COPPER, 6), new ItemStack(Items.EMERALD, 2), 4, 0, 0.02F));
            factories.add((entity, random) -> new TradeOffer(new TradedItem(Items.COPPER_BLOCK, 3), new ItemStack(Items.EMERALD), 5, 0, 0.02F));
        });
    }
}