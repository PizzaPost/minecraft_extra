package de.pizzapost.minecraft_extra.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.List;

public class LightningStickItem extends Item {

    public LightningStickItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (!world.isClient()) {
            if (!player.getItemCooldownManager().isCoolingDown(this)) {
                player.getItemCooldownManager().set(this, 10 * 20);
                List<LivingEntity> livingEntities = world.getEntitiesByClass(LivingEntity.class, player.getBoundingBox().expand(75), entity -> entity != player);
                for (LivingEntity livingEntity : livingEntities) {
                    BlockPos pos = livingEntity.getBlockPos();
                    if (world instanceof ServerWorld serverWorld) {
                        EntityType.LIGHTNING_BOLT.spawn(serverWorld, pos, SpawnReason.TRIGGERED);
                    }
                }
                if (!livingEntities.isEmpty()) {
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
