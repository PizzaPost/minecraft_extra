package de.pizzapost.minecraft_extra.item.custom;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.item.ModItems;
import de.pizzapost.minecraft_extra.sound.ModSounds;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class AttributeCoreScaledItem extends Item {
    private static final double[] PACK1_STAGES = {0.05, 0.5, 1, 1.5, 2, 2.5, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    private final Map<PlayerEntity, Integer> playerSizes = new HashMap<>();
    private static Double e = 2.71828;

    public AttributeCoreScaledItem(Settings settings) {
        super(settings);
    }

    public static void checkAndResetAttributes(ServerPlayerEntity player) {
        boolean hasItem = player.getInventory().contains(ModItems.ATTRIBUTE_CORE_SCALED.getDefaultStack());
        if (!hasItem) {
            double currentSize = player.getAttributeInstance(EntityAttributes.SCALE).getValue();
            double newSize = currentSize + (0.01 * (1 - currentSize));
            player.getAttributeInstance(EntityAttributes.SCALE).setBaseValue(newSize);
            player.getAttributeInstance(EntityAttributes.BLOCK_BREAK_SPEED).setBaseValue(newSize);
            double newBlockInteractionRange = 4.5 * newSize;
            player.getAttributeInstance(EntityAttributes.BLOCK_INTERACTION_RANGE).setBaseValue(newBlockInteractionRange);
            double newArmor = newSize - 1;
            player.getAttributeInstance(EntityAttributes.ARMOR).setBaseValue(newArmor);
            player.getAttributeInstance(EntityAttributes.ARMOR_TOUGHNESS).setBaseValue(newArmor);
            player.getAttributeInstance(EntityAttributes.ATTACK_KNOCKBACK).setBaseValue(newArmor);
            player.getAttributeInstance(EntityAttributes.KNOCKBACK_RESISTANCE).setBaseValue(newArmor);
            player.getAttributeInstance(EntityAttributes.OXYGEN_BONUS).setBaseValue(newArmor);
            player.getAttributeInstance(EntityAttributes.WATER_MOVEMENT_EFFICIENCY).setBaseValue(newArmor);
            player.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(newSize);
            double newAttackSpeed = -((6.619) / (1 - 1.812921774133562 * Math.pow(e, (0.381386290729889 * newSize))));
            player.getAttributeInstance(EntityAttributes.ATTACK_SPEED).setBaseValue(newAttackSpeed);
            double newFallDamageMultiplier = -((2.098) / (1 - 2.032837516048408 * Math.pow(e, (0.420730071899163 * newSize))));
            player.getAttributeInstance(EntityAttributes.FALL_DAMAGE_MULTIPLIER).setBaseValue(newFallDamageMultiplier);
            double newJumpStrength = -0.000099210638019 * Math.pow(newSize, 4) + 0.003989817981528 * Math.pow(newSize, 3) - 0.055450551930779 * Math.pow(newSize, 2) + 0.398209621598569 * newSize + 0.072;
            player.getAttributeInstance(EntityAttributes.JUMP_STRENGTH).setBaseValue(newJumpStrength);
            double newMovementSpeed = -0.001595060599008 * Math.pow(newSize, 2) + 0.100132039217541 * newSize + 0.0015;
            player.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(newMovementSpeed);
            double newSafeFallDistance = 3 * Math.pow(newSize, 0.829);
            player.getAttributeInstance(EntityAttributes.SAFE_FALL_DISTANCE).setBaseValue(newSafeFallDistance);
            double newStepHeight = 0.001424787902551 * Math.pow(newSize, 3) - 0.03704011137885 * Math.pow(newSize, 2) + 0.403009435882899 * newSize + 0.23256;
            player.getAttributeInstance(EntityAttributes.STEP_HEIGHT).setBaseValue(newStepHeight);
            double newEntityInteractionRange = 3 * newSize;
            player.getAttributeInstance(EntityAttributes.ENTITY_INTERACTION_RANGE).setBaseValue(newEntityInteractionRange);
        }
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        double size = player.getAttributeInstance(EntityAttributes.SCALE).getValue();
        if (size > 15.5 && !player.isSneaking()) {
            return ActionResult.FAIL;
        } else if (size < 0.07 && player.isSneaking()) {
            return ActionResult.FAIL;
        }
        if (size < 0.6 && player.isSneaking()) {
            if (player instanceof ServerPlayerEntity serverPlayer) {
                Identifier advancementId = Identifier.of(MinecraftExtra.MOD_ID, "scale");
                AdvancementEntry advancement = serverPlayer.getServer().getAdvancementLoader().get(advancementId);
                if (advancement != null) {
                    serverPlayer.getAdvancementTracker().grantCriterion(advancement, "imp");
                }
            }
        }
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if (player instanceof ServerPlayerEntity serverPlayer) {
            int currentStageSize = playerSizes.getOrDefault(player, 1);
            int nextStageSize;
            if (player.isSneaking()) {
                nextStageSize = Math.max(currentStageSize - 1, 0);
            } else {
                nextStageSize = Math.min(currentStageSize + 1, PACK1_STAGES.length - 1);
            }
            double newSize = PACK1_STAGES[nextStageSize];
            serverPlayer.getAttributeInstance(EntityAttributes.SCALE).setBaseValue(newSize);
            serverPlayer.getAttributeInstance(EntityAttributes.BLOCK_BREAK_SPEED).setBaseValue(newSize);
            playerSizes.put(player, nextStageSize);
            double newBlockInteractionRange = 4.5 * newSize;
            serverPlayer.getAttributeInstance(EntityAttributes.BLOCK_INTERACTION_RANGE).setBaseValue(newBlockInteractionRange);
            double newArmor = newSize - 1;
            serverPlayer.getAttributeInstance(EntityAttributes.ARMOR).setBaseValue(newArmor);
            serverPlayer.getAttributeInstance(EntityAttributes.ARMOR_TOUGHNESS).setBaseValue(newArmor);
            serverPlayer.getAttributeInstance(EntityAttributes.ATTACK_KNOCKBACK).setBaseValue(newArmor);
            serverPlayer.getAttributeInstance(EntityAttributes.KNOCKBACK_RESISTANCE).setBaseValue(newArmor);
            serverPlayer.getAttributeInstance(EntityAttributes.OXYGEN_BONUS).setBaseValue(newArmor);
            serverPlayer.getAttributeInstance(EntityAttributes.WATER_MOVEMENT_EFFICIENCY).setBaseValue(newArmor);
            serverPlayer.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(newSize);
            double newAttackSpeed = -((6.619) / (1 - 1.812921774133562 * Math.pow(e, (0.381386290729889 * newSize))));
            serverPlayer.getAttributeInstance(EntityAttributes.ATTACK_SPEED).setBaseValue(newAttackSpeed);
            double newFallDamageMultiplier = -((2.098) / (1 - 2.032837516048408 * Math.pow(e, (0.420730071899163 * newSize))));
            serverPlayer.getAttributeInstance(EntityAttributes.FALL_DAMAGE_MULTIPLIER).setBaseValue(newFallDamageMultiplier);
            double newJumpStrength = -0.000099210638019 * Math.pow(newSize, 4) + 0.003989817981528 * Math.pow(newSize, 3) - 0.055450551930779 * Math.pow(newSize, 2) + 0.398209621598569 * newSize + 0.072;
            serverPlayer.getAttributeInstance(EntityAttributes.JUMP_STRENGTH).setBaseValue(newJumpStrength);
            double newMovementSpeed = -0.001595060599008 * Math.pow(newSize, 2) + 0.100132039217541 * newSize + 0.0015;
            serverPlayer.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(newMovementSpeed);
            double newSafeFallDistance = 3 * Math.pow(newSize, 0.829);
            serverPlayer.getAttributeInstance(EntityAttributes.SAFE_FALL_DISTANCE).setBaseValue(newSafeFallDistance);
            double newStepHeight = 0.001424787902551 * Math.pow(newSize, 3) - 0.03704011137885 * Math.pow(newSize, 2) + 0.403009435882899 * newSize + 0.23256;
            serverPlayer.getAttributeInstance(EntityAttributes.STEP_HEIGHT).setBaseValue(newStepHeight);
            double newEntityInteractionRange = 3 * newSize;
            serverPlayer.getAttributeInstance(EntityAttributes.ENTITY_INTERACTION_RANGE).setBaseValue(newEntityInteractionRange);
            world.playSound(null, player.getBlockPos(), ModSounds.ATTRIBUTE_CORE_USE, SoundCategory.AMBIENT, 1f, 1f);
            Text actionbarMessage = Text.translatable("actionbar.minecraft_extra.scale_message", newSize);
            player.sendMessage(actionbarMessage, true);
        }
        return ActionResult.SUCCESS;
    }
}
