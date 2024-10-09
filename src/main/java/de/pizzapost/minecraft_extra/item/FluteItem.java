package de.pizzapost.minecraft_extra.item;

import de.pizzapost.minecraft_extra.sound.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class FluteItem extends Item {

    public FluteItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (!world.isClient) {
            if (!player.getItemCooldownManager().isCoolingDown(this)) {
                player.getItemCooldownManager().set(this, 120 * 20);
                world.playSound(null, player.getBlockPos(), ModSounds.FLUTE, SoundCategory.PLAYERS, 1f, 1f);
                List<LivingEntity> livingEntitys = world.getEntitiesByClass(LivingEntity.class, player.getBoundingBox().expand(75), entity -> entity != player);
                for (LivingEntity livingEntity : livingEntitys) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 600, 0, false, false));
                }
                if (!livingEntitys.isEmpty()) {
                    itemStack.setDamage(itemStack.getDamage() + 1);
                    if (itemStack.getDamage() == 64) {
                        player.getStackInHand(hand).decrementUnlessCreative(1, player);
                        world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.AMBIENT, 1f, 1f);
                    }
                }
            }
        }
        return TypedActionResult.success(itemStack);
    }
}
