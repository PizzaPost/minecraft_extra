package de.pizzapost.minecraft_extra.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class StrongThrowableFireChargeItem extends Item {

    public StrongThrowableFireChargeItem(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!world.isClient()) {
            itemStack.decrementUnlessCreative(1, player);
            Vec3d lookVec = player.getRotationVec(1.0F);
            Double x = player.getX();
            Double y = player.getEyeY();
            Double z = player.getZ();
            Vec3d Vec = player.getRotationVec(1.0F);
            FireballEntity fireball = new FireballEntity(world, player, Vec, 9);
            fireball.setPos(x + lookVec.x, y + lookVec.y, z + lookVec.z);
            world.spawnEntity(fireball);
            Random random = world.getRandom();
            world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
        }
        return ActionResult.SUCCESS;
    }
}