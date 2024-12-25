package de.pizzapost.minecraft_extra.item;

import de.pizzapost.minecraft_extra.sound.ModSounds;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.text.Text;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import java.util.HashMap;
import java.util.Map;

public class AttributeCoreScaledItem extends Item {
    private static final double[] PACK1_STAGES = {0.05, 0.25, 0.5, 0.75, 1, 1.5, 2, 3, 5, 7.5, 10, 13, 16};
    private static final double[] PACK2_STAGES = {0.225, 1.125, 2.25, 3.375, 4.5, 6.75, 9, 13.5, 22.5, 33.75, 45, 58.5, 72};
    private static final double[] PACK3_STAGES = {-0.95, -0.75, -0.5, -0.25, 0, 0.5, 1, 2, 4, 6.5, 9, 12, 15};
    private static final double[] PACK4_STAGES = {-0.95, -0.75, -0.5, -0.25, 1, 1.5, 2, 3, 5, 7.5, 10, 13, 16};
    private static final double[] PACK5_STAGES = {7.8, 7, 6, 5, 4, 3, 2, 1.3, 0.8, 0.5, 0.4, 0.3, 0.25};
    private static final double[] PACK6_STAGES = {1.95, 1.75, 1.5, 1.25, 1, 0.75, 0.5, 0.3, 0.2, 0.13, 0.1, 0.07, 0.06};
    private static final double[] PACK7_STAGES = {0.02095, 0.10725, 0.2095, 0.31425, 0.419, 0.5285, 0.638, 0.757, 1.095, 1.2425, 1.49, 1.747, 2.047};
    private static final double[] PACK8_STAGES = {0.0035, 0.007, 0.035, 0.07, 0.1, 0.2095, 0.31425, 0.419, 0.5285, 0.638, 0.757, 1.095, 1.2425};
    private static final double[] PACK9_STAGES = {0.25, 1, 2, 2.5, 3, 6, 9, 12, 15, 18, 21, 24, 27};
    private static final double[] PACK10_STAGES = {0.2, 0.3, 0.4, 0.5, 0.6, 0.8, 1, 1.2, 1.4, 1.7, 2, 2.4, 3};
    private static final double[] PACK11_STAGES = {0.25, 0.95, 1.5, 2.5, 3, 8, 13, 18, 23, 28, 33, 38, 43};
    private final Map<PlayerEntity, Integer> playerSizes = new HashMap<>();
    private final Map<PlayerEntity, Integer> playerBlockInteractionRange = new HashMap<>();
    private final Map<PlayerEntity, Integer> playerArmor = new HashMap<>();
    private final Map<PlayerEntity, Integer> playerAttackDamage = new HashMap<>();
    private final Map<PlayerEntity, Integer> playerAttackSpeed = new HashMap<>();
    private final Map<PlayerEntity, Integer> playerFallDamageMultiplier = new HashMap<>();
    private final Map<PlayerEntity, Integer> playerJumpStrength = new HashMap<>();
    private final Map<PlayerEntity, Integer> playerMovementSpeed = new HashMap<>();
    private final Map<PlayerEntity, Integer> playerSafeFallDistance = new HashMap<>();
    private final Map<PlayerEntity, Integer> playerStepHeight = new HashMap<>();
    private final Map<PlayerEntity, Integer> playerEntityInteractionRange = new HashMap<>();

    public AttributeCoreScaledItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if (player instanceof ServerPlayerEntity serverPlayer) {
            int currentStageSize = playerSizes.getOrDefault(player, 0);
            int nextStageSize = (currentStageSize + 1) % PACK1_STAGES.length;
            double newSize = PACK1_STAGES[nextStageSize];
            serverPlayer.getAttributeInstance(EntityAttributes.GENERIC_SCALE).setBaseValue(newSize);
            serverPlayer.getAttributeInstance(EntityAttributes.PLAYER_BLOCK_BREAK_SPEED).setBaseValue(newSize);
            playerSizes.put(player, nextStageSize);

            int currentStageBlockInteractionRange = playerBlockInteractionRange.getOrDefault(player, 0);
            int nextStageBlockInteractionRange = (currentStageBlockInteractionRange + 1) % PACK2_STAGES.length;
            double newBlockInteractionRange = PACK2_STAGES[nextStageBlockInteractionRange];
            serverPlayer.getAttributeInstance(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE).setBaseValue(newBlockInteractionRange);
            playerBlockInteractionRange.put(player, nextStageBlockInteractionRange);

            int currentStageArmor = playerArmor.getOrDefault(player, 0);
            int nextStageArmor = (currentStageArmor + 1) % PACK3_STAGES.length;
            double newArmor = PACK3_STAGES[nextStageArmor];
            serverPlayer.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(newArmor);
            serverPlayer.getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS).setBaseValue(newArmor);
            serverPlayer.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_KNOCKBACK).setBaseValue(newArmor);
            serverPlayer.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(newArmor);
            serverPlayer.getAttributeInstance(EntityAttributes.GENERIC_OXYGEN_BONUS).setBaseValue(newArmor);
            serverPlayer.getAttributeInstance(EntityAttributes.GENERIC_WATER_MOVEMENT_EFFICIENCY).setBaseValue(newArmor);
            playerArmor.put(player, nextStageArmor);

            int currentStageAttackDamage = playerAttackDamage.getOrDefault(player, 0);
            int nextStageAttackDamage = (currentStageAttackDamage + 1) % PACK4_STAGES.length;
            double newAttackDamage = PACK4_STAGES[nextStageAttackDamage];
            serverPlayer.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(newAttackDamage);
            playerAttackDamage.put(player, nextStageAttackDamage);

            int currentStageAttackSpeed = playerAttackSpeed.getOrDefault(player, 0);
            int nextStageAttackSpeed = (currentStageAttackSpeed + 1) % PACK5_STAGES.length;
            double newAttackSpeed = PACK5_STAGES[nextStageAttackSpeed];
            serverPlayer.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED).setBaseValue(newAttackSpeed);
            playerAttackSpeed.put(player, nextStageAttackSpeed);

            int currentStageFallDamageMultiplier = playerFallDamageMultiplier.getOrDefault(player, 0);
            int nextStageFallDamageMultiplier = (currentStageFallDamageMultiplier + 1) % PACK6_STAGES.length;
            double newFallDamageMultiplier = PACK6_STAGES[nextStageFallDamageMultiplier];
            serverPlayer.getAttributeInstance(EntityAttributes.GENERIC_FALL_DAMAGE_MULTIPLIER).setBaseValue(newFallDamageMultiplier);
            playerFallDamageMultiplier.put(player, nextStageFallDamageMultiplier);

            int currentStageJumpStrength = playerJumpStrength.getOrDefault(player, 0);
            int nextStageJumpStrength = (currentStageJumpStrength + 1) % PACK7_STAGES.length;
            double newJumpStrength = PACK7_STAGES[nextStageJumpStrength];
            serverPlayer.getAttributeInstance(EntityAttributes.GENERIC_JUMP_STRENGTH).setBaseValue(newJumpStrength);
            playerJumpStrength.put(player, nextStageJumpStrength);

            int currentStageMovementSpeed = playerMovementSpeed.getOrDefault(player, 0);
            int nextStageMovementSpeed = (currentStageMovementSpeed + 1) % PACK8_STAGES.length;
            double newMovementSpeed = PACK8_STAGES[nextStageMovementSpeed];
            serverPlayer.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(newMovementSpeed);
            playerMovementSpeed.put(player, nextStageMovementSpeed);

            int currentStageSafeFallDistance = playerSafeFallDistance.getOrDefault(player, 0);
            int nextStageSafeFallDistance = (currentStageSafeFallDistance + 1) % PACK9_STAGES.length;
            double newSafeFallDistance = PACK9_STAGES[nextStageSafeFallDistance];
            serverPlayer.getAttributeInstance(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE).setBaseValue(newSafeFallDistance);
            playerSafeFallDistance.put(player, nextStageSafeFallDistance);

            int currentStageStepHeight = playerStepHeight.getOrDefault(player, 0);
            int nextStageStepHeight = (currentStageStepHeight + 1) % PACK10_STAGES.length;
            double newStepHeight = PACK10_STAGES[nextStageStepHeight];
            serverPlayer.getAttributeInstance(EntityAttributes.GENERIC_STEP_HEIGHT).setBaseValue(newStepHeight);
            playerStepHeight.put(player, nextStageStepHeight);

            int currentStageEntityInteractionRange = playerEntityInteractionRange.getOrDefault(player, 0);
            int nextStageEntityInteractionRange = (currentStageEntityInteractionRange + 1) % PACK11_STAGES.length;
            double newEntityInteractionRange = PACK11_STAGES[nextStageEntityInteractionRange];
            serverPlayer.getAttributeInstance(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE).setBaseValue(newEntityInteractionRange);
            playerEntityInteractionRange.put(player, nextStageEntityInteractionRange);
            world.playSound(null, player.getBlockPos(), ModSounds.ATTRIBUTE_CORE_USE, SoundCategory.AMBIENT, 1f, 1f);
        }
        player.getItemCooldownManager().set(this, 10);
        if (player instanceof ServerPlayerEntity serverPlayer) {
            double scale = player.getScale();
            Text actionbarMessage = Text.translatable("actionbar.minecraft_extra.scale_message", scale);
            serverPlayer.networkHandler.sendPacket(new OverlayMessageS2CPacket(actionbarMessage));
        }
        return TypedActionResult.success(itemStack);
    }
}
