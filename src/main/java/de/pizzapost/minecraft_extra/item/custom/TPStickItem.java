package de.pizzapost.minecraft_extra.item.custom;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

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
            Vec3d teleportPosition = player.getPos().add(lookVec.multiply(20*player.getAttributeInstance(EntityAttributes.GENERIC_SCALE).getValue()));
            BlockPos pos1 = new BlockPos((int) Math.floor(teleportPosition.x), (int) Math.floor(teleportPosition.y), (int) Math.floor(teleportPosition.z));
            world.breakBlock(pos1, true);
            BlockPos pos2 = pos1.up();
            world.breakBlock(pos2, true);
            player.requestTeleport(teleportPosition.x, teleportPosition.y, teleportPosition.z);
            player.requestTeleport(player.getBlockPos().getX()+0.5, player.getBlockPos().getY(), player.getBlockPos().getZ()+0.5);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PLAYER_TELEPORT, SoundCategory.PLAYERS, 1f, 1f);
            if (player.getAttributeInstance(EntityAttributes.GENERIC_SCALE).getValue()>1) {
                if (player instanceof ServerPlayerEntity serverPlayer) {
                    Identifier advancementId = Identifier.of(MinecraftExtra.MOD_ID, "tp_stick");
                    AdvancementEntry advancement = serverPlayer.getServer().getAdvancementLoader().get(advancementId);
                    if (advancement != null) {
                        serverPlayer.getAdvancementTracker().grantCriterion(advancement, "imp");
                    }
                }
            }
        }
        return TypedActionResult.success(itemStack);
    }
}
