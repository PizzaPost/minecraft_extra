package de.pizzapost.minecraft_extra.util;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

import java.util.*;

public class InventoryShuffler {
    private static final Map<UUID, Integer> shufflingPlayers = new HashMap<>();

    public static void startShuffle(ServerPlayerEntity player) {
        shufflingPlayers.put(player.getUuid(), 20);
    }

    public static void tick(ServerPlayerEntity player) {
        Random random = new Random();
        UUID uuid = player.getUuid();
        if (!shufflingPlayers.containsKey(uuid)) return;
        int ticksLeft = shufflingPlayers.get(uuid);
        if (ticksLeft <= 0) {
            shufflingPlayers.remove(uuid);
            return;
        }
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < player.getInventory().size(); i++) {
            items.add(player.getInventory().getStack(i));
        }
        Collections.shuffle(items);
        player.getInventory().clear();
        for (ItemStack stack : items) {
            player.getInventory().insertStack(stack);
        }
        shufflingPlayers.put(uuid, ticksLeft - 1);
        if (player.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
        }
    }

    public static Set<UUID> getPlayers() {
        return shufflingPlayers.keySet();
    }
}
