package de.pizzapost.minecraft_extra.item;

import de.pizzapost.minecraft_extra.sound.ModSounds;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AttributeCoreSpicyItem extends Item {
    public AttributeCoreSpicyItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!world.isClient() && world instanceof ServerWorld serverWorld) {
            Vec3d position = player.getPos();
            Box area = new Box(position.subtract(8, 8, 8), position.add(8, 8, 8));

            if (player.isSneaking()) {
                for (MobEntity mob : serverWorld.getEntitiesByClass(MobEntity.class, area, e -> true)) {
                    mob.damage(world.getDamageSources().playerAttack(player), 8.0F);
                    itemStack.setDamage(itemStack.getDamage() + 1);
                    if (itemStack.getDamage() == 500) {
                        player.getStackInHand(hand).decrementUnlessCreative(1, player);
                    }
                }
                player.getItemCooldownManager().set(this, 200);
            }
            else {
                for (MobEntity mob : serverWorld.getEntitiesByClass(MobEntity.class, area, e -> true)) {
                    mob.damage(world.getDamageSources().playerAttack(player), 4.0F);
                    itemStack.setDamage(itemStack.getDamage() + 1);
                    if (itemStack.getDamage() == 500) {
                        player.getStackInHand(hand).decrementUnlessCreative(1, player);
                        world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.AMBIENT, 1f, 1f);
                    }
                }
                player.getItemCooldownManager().set(this, 10);
            }
            world.playSound(null, player.getBlockPos(), ModSounds.ATTRIBUTE_CORE_USE, SoundCategory.AMBIENT, 1f, 1f);
        }
        return TypedActionResult.success(itemStack);
    }
}
