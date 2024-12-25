package de.pizzapost.minecraft_extra.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class InfinitePotatoItem extends Item {

    public InfinitePotatoItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!world.isClient) {
            if (!player.getItemCooldownManager().isCoolingDown(this)) {
                player.eatFood(world, itemStack, ModFoodComponents.INFINITE_POTATO);
                player.getItemCooldownManager().set(this, 120 * 20);
                world.getServer().execute(() -> giveNewPotato(player));
                world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.AMBIENT, 1f, 1f);
            }
        }
        return TypedActionResult.success(itemStack);
    }
    private void giveNewPotato(PlayerEntity player) {
        ItemStack newInfinitePotato = new ItemStack(ModItems.INFINITE_POTATO);
        if (player.getMainHandStack().isEmpty()) {
            player.setStackInHand(Hand.MAIN_HAND, newInfinitePotato);
        } else {
            if (!player.getInventory().insertStack(newInfinitePotato)) {
                player.dropItem(newInfinitePotato, false);
            }
        }
    }
}
