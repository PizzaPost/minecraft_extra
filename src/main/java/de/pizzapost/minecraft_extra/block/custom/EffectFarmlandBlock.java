package de.pizzapost.minecraft_extra.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

import java.util.concurrent.ThreadLocalRandom;

public class EffectFarmlandBlock extends FarmlandBlock {
    public static final EnumProperty<EffectType> EFFECT = EnumProperty.of("effect", EffectType.class);

    public EffectFarmlandBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(MOISTURE, 0).with(EFFECT, EffectType.NONE));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        BlockState cropState = world.getBlockState(pos.up());
        Block cropBlock = cropState.getBlock();
        if (cropBlock instanceof CropBlock crop) {
            if (cropState.get(CropBlock.AGE) == crop.getMaxAge()) {
                EffectType effectType = state.get(EFFECT);
                applyEffectToEntities(world, pos, effectType);
            }
        }
    }

    private void applyEffectToEntities(ServerWorld world, BlockPos pos, EffectType effectType) {
        world.getEntitiesByClass(net.minecraft.entity.player.PlayerEntity.class,
                new net.minecraft.util.math.Box(pos).expand(3),
                player -> true
        ).forEach(player -> {
            RegistryEntry effect=null;
            switch (effectType) {
                case ABSORPTION -> effect=StatusEffects.ABSORPTION;
                case BAD_OMEN -> effect=StatusEffects.BAD_OMEN;
                case BLINDNESS -> effect=StatusEffects.BLINDNESS;
                case CONDUIT_POWER -> effect=StatusEffects.CONDUIT_POWER;
                case DARKNESS -> effect=StatusEffects.DARKNESS;
                case DOLPHINS_GRACE -> effect=StatusEffects.DOLPHINS_GRACE;
                case FIRE_RESISTANCE -> effect=StatusEffects.FIRE_RESISTANCE;
                case GLOWING -> effect=StatusEffects.GLOWING;
                case HASTE -> effect=StatusEffects.HASTE;
                case HEALTH_BOOST -> effect=StatusEffects.HEALTH_BOOST;
                case HERO_OF_THE_VILLAGE -> effect=StatusEffects.HERO_OF_THE_VILLAGE;
                case HUNGER -> effect=StatusEffects.HUNGER;
                case INFESTED -> effect=StatusEffects.INFESTED;
                case INSTANT_DAMAGE -> effect=StatusEffects.INSTANT_DAMAGE;
                case INSTANT_HEALTH -> effect=StatusEffects.INSTANT_HEALTH;
                case INVISIBILITY -> effect=StatusEffects.INVISIBILITY;
                case JUMP_BOOST -> effect=StatusEffects.JUMP_BOOST;
                case LEVITATION -> effect=StatusEffects.LEVITATION;
                case LUCK -> effect=StatusEffects.LUCK;
                case MINING_FATIGUE -> effect=StatusEffects.MINING_FATIGUE;
                case NAUSEA -> effect=StatusEffects.NAUSEA;
                case NIGHT_VISION -> effect=StatusEffects.NIGHT_VISION;
                case OOZING -> effect=StatusEffects.OOZING;
                case POISON -> effect=StatusEffects.POISON;
                case RAID_OMEN -> effect=StatusEffects.RAID_OMEN;
                case REGENERATION -> effect=StatusEffects.REGENERATION;
                case RESISTANCE -> effect=StatusEffects.RESISTANCE;
                case SATURATION -> effect=StatusEffects.SATURATION;
                case SLOW_FALLING -> effect=StatusEffects.SLOW_FALLING;
                case SLOWNESS -> effect=StatusEffects.SLOWNESS;
                case SPEED -> effect=StatusEffects.SPEED;
                case STRENGTH -> effect=StatusEffects.STRENGTH;
                case TRIAL_OMEN -> effect=StatusEffects.TRIAL_OMEN;
                case UNLUCK -> effect=StatusEffects.UNLUCK;
                case WATER_BREATHING -> effect=StatusEffects.WATER_BREATHING;
                case WEAKNESS -> effect=StatusEffects.WEAKNESS;
                case WEAVING -> effect=StatusEffects.WEAVING;
                case WIND_CHARGED -> effect=StatusEffects.WIND_CHARGED;
                case WITHER -> effect=StatusEffects.WITHER;
            }
            if (effect==null) return;
            if (player.hasStatusEffect(effect)) return;
            int randomDuration = ThreadLocalRandom.current().nextInt(100, 1000);
            int randomAmplifier = ThreadLocalRandom.current().nextInt(3);
            applyEffect(player, effect, randomDuration, randomAmplifier);
        });
    }

    private void applyEffect(PlayerEntity player, RegistryEntry effect, int duration, int amplifier) {
        player.addStatusEffect(new StatusEffectInstance(effect, duration, amplifier));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(EFFECT);
    }
}