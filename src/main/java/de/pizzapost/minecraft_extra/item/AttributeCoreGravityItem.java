package de.pizzapost.minecraft_extra.item;

import de.pizzapost.minecraft_extra.sound.ModSounds;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.text.Text;
import java.util.Objects;


public class AttributeCoreGravityItem extends Item {
    public AttributeCoreGravityItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        var current_vel=player.getFinalGravity();
        if (current_vel == 0.08) {
            Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_GRAVITY))
                    .setBaseValue(player.getFinalGravity() - 0.16);
        }
        if (current_vel == -0.08) {
            Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_GRAVITY))
                    .setBaseValue(player.getFinalGravity() + 0.08);

        }
        if (current_vel == 0.0) {
            Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_GRAVITY))
                    .setBaseValue(player.getFinalGravity() + 0.08);
        }
        player.getItemCooldownManager().set(this, 10);
        player.setVelocity(Vec3d.ZERO);
        if (player instanceof ServerPlayerEntity serverPlayer) {
            double gravity = player.getFinalGravity();
            Text actionbarMessage = Text.translatable("actionbar.minecraft_extra.gravity_message", gravity);
            serverPlayer.networkHandler.sendPacket(new OverlayMessageS2CPacket(actionbarMessage));
            world.playSound(null, player.getBlockPos(), ModSounds.ATTRIBUTE_CORE_USE, SoundCategory.AMBIENT, 1f, 1f);
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }
}
