package de.pizzapost.minecraft_extra.item.custom;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.sound.ModSounds;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AttributeCoreSpicyItem extends Item {
    public AttributeCoreSpicyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!world.isClient() && world instanceof ServerWorld serverWorld) {
            Vec3d position = player.getPos();
            Box area = new Box(position.subtract(8, 8, 8), position.add(8, 8, 8));
            if (player.isSneaking()) {
                for (MobEntity mob : serverWorld.getEntitiesByClass(MobEntity.class, area, e -> true)) {
                    mob.damage(serverWorld, world.getDamageSources().playerAttack(player), 8.0F);
                    itemStack.damage(2, player, LivingEntity.getSlotForHand(hand));
                    if (itemStack.getDamage() >= 100) {
                        player.getStackInHand(hand).decrementUnlessCreative(1, player);
                        world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.AMBIENT, 1f, 1f);
                        if (player instanceof ServerPlayerEntity serverPlayer) {
                            Identifier advancementId = Identifier.of(MinecraftExtra.MOD_ID, "spicy");
                            AdvancementEntry advancement = serverPlayer.getServer().getAdvancementLoader().get(advancementId);
                            if (advancement != null) {
                                serverPlayer.getAdvancementTracker().grantCriterion(advancement, "imp");
                            }
                        }
                    }
                }
                for (PlayerEntity targetPlayer : serverWorld.getPlayers()) {
                    if (targetPlayer != player && area.contains(targetPlayer.getPos())) {
                        targetPlayer.damage(serverWorld, world.getDamageSources().playerAttack(player), 8.0F);
                    }
                }
            } else {
                for (MobEntity mob : serverWorld.getEntitiesByClass(MobEntity.class, area, e -> true)) {
                    mob.damage(serverWorld, world.getDamageSources().playerAttack(player), 4.0F);
                    itemStack.damage(1, player, LivingEntity.getSlotForHand(hand));
                    if (itemStack.getDamage() >= 100) {
                        player.getStackInHand(hand).decrementUnlessCreative(1, player);
                        world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.AMBIENT, 1f, 1f);
                        if (player instanceof ServerPlayerEntity serverPlayer) {
                            Identifier advancementId = Identifier.of(MinecraftExtra.MOD_ID, "spicy");
                            AdvancementEntry advancement = serverPlayer.getServer().getAdvancementLoader().get(advancementId);
                            if (advancement != null) {
                                serverPlayer.getAdvancementTracker().grantCriterion(advancement, "imp");
                            }
                        }
                    }
                }
                for (PlayerEntity targetPlayer : serverWorld.getPlayers()) {
                    if (targetPlayer != player && area.contains(targetPlayer.getPos())) {
                        targetPlayer.damage(serverWorld, world.getDamageSources().playerAttack(player), 4.0F);
                    }
                }
            }
            world.playSound(null, player.getBlockPos(), ModSounds.ATTRIBUTE_CORE_USE, SoundCategory.AMBIENT, 1f, 1f);
        }
        return ActionResult.SUCCESS;
    }
}