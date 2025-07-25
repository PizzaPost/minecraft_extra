package de.pizzapost.minecraft_extra.item.custom;

import de.pizzapost.minecraft_extra.sound.ModSounds;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Random;


public class AttributeCoreGravityItem extends Item {
    public AttributeCoreGravityItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        Random random = new Random();
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        var current_vel = player.getFinalGravity();
        if (player.isSneaking()) {
            if (current_vel == 0.08) {
                return ActionResult.FAIL;
            }
            Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GRAVITY)).setBaseValue(0.08);
        } else {
            if (current_vel == 0.08) {
                Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GRAVITY)).setBaseValue(-0.08);
            } else if (current_vel == 0.0) {
                Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GRAVITY)).setBaseValue(-0.08);
            } else if (current_vel == -0.08) {
                Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GRAVITY)).setBaseValue(0.0);
            }
        }
        player.fallDistance = -10;
        player.setVelocity(Vec3d.ZERO);
        if (player instanceof ServerPlayerEntity serverPlayer) {
            double gravity = player.getFinalGravity();
            Text actionbarMessage = Text.translatable("actionbar.minecraft_extra.gravity_message", gravity);
            serverPlayer.networkHandler.sendPacket(new OverlayMessageS2CPacket(actionbarMessage));
            world.playSound(null, player.getBlockPos(), ModSounds.ATTRIBUTE_CORE_USE, SoundCategory.AMBIENT, 1f, 1f);
        }
        if (world instanceof ServerWorld serverWorld) {
            for (int i = 0; i < 20; i++)
                serverWorld.spawnParticles(ParticleTypes.CLOUD, player.getX() + (random.nextDouble() * 2 - 1), player.getY() + 1 + (random.nextDouble() * 2 - 1), player.getZ() + (random.nextDouble() * 2 - 1), 1, 0.0, 0.0, 0.0, 0.1);
        }
        return ActionResult.SUCCESS;
    }
}
