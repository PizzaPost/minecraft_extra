package de.pizzapost.minecraft_extra.item;

import de.pizzapost.minecraft_extra.sound.ModSounds;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Objects;

public class AttributeCoreExtraHeartsItem extends Item {
    public AttributeCoreExtraHeartsItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (player.getMaxHealth() == 20.0F) {
            Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH))
                    .setBaseValue(player.getMaxHealth() + 20.0F);
            player.setHealth(Math.min(player.getHealth() + 20.0F, player.getMaxHealth()));
            player.getStackInHand(hand).decrementUnlessCreative(1, player);
            world.playSound(null, player.getBlockPos(), ModSounds.ATTRIBUTE_CORE_USE, SoundCategory.AMBIENT, 1f, 1f);
            player.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }
}
