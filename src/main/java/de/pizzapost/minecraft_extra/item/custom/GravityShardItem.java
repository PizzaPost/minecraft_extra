package de.pizzapost.minecraft_extra.item.custom;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.item.ModItems;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GravityShardItem extends Item {

    public GravityShardItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, world, entity, slot);
        if (entity instanceof PlayerEntity player) {
            if (player.getMainHandStack().getItem() == ModItems.GRAVITY_SHARD || player.getOffHandStack().getItem() == ModItems.GRAVITY_SHARD) {
                if (!world.isClient()) {
                    List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class, player.getBoundingBox().expand(10 * player.getAttributeInstance(EntityAttributes.SCALE).getValue()), itemEntity -> true);
                    if (!items.isEmpty()) {
                        for (ItemEntity itemEntity : items) {
                            itemEntity.refreshPositionAndAngles(player.getX(), player.getY(), player.getZ(), itemEntity.getYaw(), itemEntity.getPitch());
                        }
                        if (player instanceof ServerPlayerEntity serverPlayer) {
                            Identifier advancementId = Identifier.of(MinecraftExtra.MOD_ID, "gravity_shard");
                            AdvancementEntry advancement = world.getServer().getAdvancementLoader().get(advancementId);
                            if (advancement != null) {
                                serverPlayer.getAdvancementTracker().grantCriterion(advancement, "imp");
                            }
                        }
                    }
                }
            }
        }
    }
}