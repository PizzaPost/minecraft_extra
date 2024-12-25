package de.pizzapost.minecraft_extra.item;

import de.pizzapost.minecraft_extra.sound.ModSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class TPStickItem extends Item {

    public TPStickItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        int xp = player.experienceLevel;
        if (xp >= 1) {
            player.getItemCooldownManager().set(this, 10 * 20);
            player.addExperience(-10);
            Vec3d lookVec = player.getRotationVec(1.0f);
            Vec3d teleportPosition = player.getPos().add(lookVec.multiply(20));
            player.requestTeleport(teleportPosition.x, teleportPosition.y, teleportPosition.z);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PLAYER_TELEPORT, SoundCategory.PLAYERS, 1f, 1f);
        }
        return TypedActionResult.success(itemStack);
    }
}
