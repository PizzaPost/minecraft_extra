package de.pizzapost.minecraft_extra.item.custom;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.particle.ModParticles;
import de.pizzapost.minecraft_extra.sound.ModSounds;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Random;

public class AttributeCoreExtraHeartsItem extends Item {
    public AttributeCoreExtraHeartsItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        try {
            Random random = new Random();
            player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addTemporaryModifier(new EntityAttributeModifier(Identifier.of(MinecraftExtra.MOD_ID, "attribute_core_extra_hearts"), 20, EntityAttributeModifier.Operation.ADD_VALUE));
            player.setHealth(Math.min(player.getHealth() + 20.0F, player.getMaxHealth()));
            player.getStackInHand(hand).decrementUnlessCreative(1, player);
            world.playSound(null, player.getBlockPos(), ModSounds.ATTRIBUTE_CORE_USE, SoundCategory.AMBIENT, 1f, 1f);
            player.incrementStat(Stats.USED.getOrCreateStat(this));
            if (world instanceof ServerWorld serverWorld) {
                for (int i = 0; i < 20; i++)
                    serverWorld.spawnParticles(ParticleTypes.HEART, player.getX() + (random.nextDouble() * 2 - 1), player.getY() + 1 + (random.nextDouble() * 2 - 1), player.getZ() + (random.nextDouble() * 2 - 1), 1, 0.0, 0.0, 0.0, 1.0);
            }
            return TypedActionResult.success(player.getStackInHand(hand));
        } catch (Exception e) {
            return TypedActionResult.pass(player.getStackInHand(hand));
        }
    }
}
