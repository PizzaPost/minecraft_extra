package de.pizzapost.minecraft_extra.item.custom;

import com.google.common.collect.ImmutableMap;
import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.effect.ModEffects;
import de.pizzapost.minecraft_extra.item.ModArmorMaterials;
import de.pizzapost.minecraft_extra.keybinds.ModKeys;
import de.pizzapost.minecraft_extra.particle.ModParticles;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class ModArmorItem extends Item {
    private static final Map<ArmorMaterial, List<StatusEffectInstance>> MATERIAL_TO_EFFECT_MAP = (new ImmutableMap.Builder<ArmorMaterial, List<StatusEffectInstance>>()).put(ModArmorMaterials.HARDENED_NETHERITE_ARMOR_MATERIAL, List.of(new StatusEffectInstance(StatusEffects.HASTE, 10, 2, false, false), new StatusEffectInstance(StatusEffects.REGENERATION, 50, 0, false, false), new StatusEffectInstance(StatusEffects.STRENGTH, 10, 1, false, false), new StatusEffectInstance(StatusEffects.SPEED, 10, 1, false, false), new StatusEffectInstance(ModEffects.SPIDER, 10, 0, false, false))).build();

    public ModArmorItem(Settings settings) {
        super(settings);
    }

    private void removeHealthModifier(PlayerEntity player) {
        try {
            player.getAttributeInstance(EntityAttributes.MAX_HEALTH).removeModifier(Identifier.of(MinecraftExtra.MOD_ID, "hardened_netherite_armor_health"));
        } catch (Exception ignored) {
        }
        if (player.getHealth() > player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
    }


    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity player) {
                if (hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player);
                } else {
                    removeHealthModifier(player);
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot);
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<ArmorMaterial, List<StatusEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            List<StatusEffectInstance> mapStatusEffects = entry.getValue();
            if (player.getWorld() instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(ModParticles.HARDENED_NETHERITE_AMBIENT, player.getX(), player.getY() + 0.1, player.getZ(), 1, 0.0, 0.0, 0.0, 1);
            }
            if (hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffects);
                if (ModKeys.jumpBoostKey.isPressed()) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 10, 4, false, false));
                }
            }
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, ArmorMaterial mapArmorMaterial, List<StatusEffectInstance> mapStatusEffect) {
        boolean hasPlayerEffect = mapStatusEffect.stream().allMatch(statusEffectInstance -> player.hasStatusEffect(statusEffectInstance.getEffectType()));
        if (!hasPlayerEffect) {
            for (StatusEffectInstance instance : mapStatusEffect) {
                player.addStatusEffect(new StatusEffectInstance(instance.getEffectType(), instance.getDuration(), instance.getAmplifier(), instance.isAmbient(), instance.shouldShowParticles()));
            }
            if (player.getAttributeInstance(EntityAttributes.MAX_HEALTH).getValue() < 60) {
                try {
                    player.getAttributeInstance(EntityAttributes.MAX_HEALTH).addTemporaryModifier(new EntityAttributeModifier(Identifier.of(MinecraftExtra.MOD_ID, "hardened_netherite_armor_health"), 20, EntityAttributeModifier.Operation.ADD_VALUE));
                    player.setHealth(Math.min(player.getHealth() + 20.0F, player.getMaxHealth()));
                } catch (Exception ignored) {
                }
            }
        }
    }

    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET);
        ItemStack leggings = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack breastplate = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack helmet = player.getEquippedStack(EquipmentSlot.HEAD);
        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty() && isEnchanted(helmet) && isEnchanted(breastplate) && isEnchanted(leggings) && isEnchanted(boots);
    }

    private boolean isEnchanted(ItemStack armor) {
        return !armor.isEmpty() && armor.hasEnchantments();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player) {
        ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET);
        ItemStack leggings = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack breastplate = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack helmet = player.getEquippedStack(EquipmentSlot.HEAD);

        EquippableComponent equippableComponentBoots = boots.getComponents().get(DataComponentTypes.EQUIPPABLE);
        EquippableComponent equippableComponentLeggings = leggings.getComponents().get(DataComponentTypes.EQUIPPABLE);
        EquippableComponent equippableComponentBreastplate = breastplate.getComponents().get(DataComponentTypes.EQUIPPABLE);
        EquippableComponent equippableComponentHelmet = helmet.getComponents().get(DataComponentTypes.EQUIPPABLE);
        return equippableComponentBoots.assetId().get().equals(material.assetId()) == equippableComponentLeggings.assetId().get().equals(material.assetId()) && equippableComponentBreastplate.assetId().get().equals(material.assetId()) && equippableComponentHelmet.assetId().get().equals(material.assetId());
    }
}