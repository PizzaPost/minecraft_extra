package de.pizzapost.minecraft_extra.util;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

import java.util.*;

public class InventoryShuffler {
    private static class ShuffleState {
        int ticksLeft;
        final List<ItemStack> originalItems;

        ShuffleState(int ticks, List<ItemStack> items) {
            this.ticksLeft = ticks;
            this.originalItems = new ArrayList<>(items); // deep copy
        }
    }

    private static final Map<UUID, ShuffleState> shufflingPlayers = new HashMap<>();

    public static void startShuffle(ServerPlayerEntity player) {
        List<ItemStack> currentItems = new ArrayList<>();
        for (int i = 0; i < player.getInventory().size(); i++) {
            currentItems.add(player.getInventory().getStack(i).copy());
        }
        shufflingPlayers.put(player.getUuid(), new ShuffleState(900, currentItems));
    }

    public static void tick(ServerPlayerEntity player) {
        UUID uuid = player.getUuid();
        ShuffleState state = shufflingPlayers.get(uuid);
        if (state == null) return;

        if (state.ticksLeft <= 0) {
            player.getInventory().clear();
            for (ItemStack stack : state.originalItems) {
                player.getInventory().insertStack(stack.copy());
            }
            shufflingPlayers.remove(uuid);
            return;
        }

        List<ItemStack> shuffled = new ArrayList<>(state.originalItems);
        Collections.shuffle(shuffled);
        player.getInventory().clear();
        for (ItemStack stack : shuffled) {
            player.getInventory().insertStack(stack.copy());
        }

        state.ticksLeft--;

        if (player.getEntityWorld() instanceof ServerWorld serverWorld) {
            Random random = new Random();
            serverWorld.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
        }
    }

    public static boolean isShuffling(UUID player) {
        return shufflingPlayers.containsKey(player);
    }

    public static Set<UUID> getPlayers() {
        return shufflingPlayers.keySet();
    }
}
