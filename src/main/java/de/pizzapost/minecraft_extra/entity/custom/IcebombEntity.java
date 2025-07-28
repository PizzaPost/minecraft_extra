package de.pizzapost.minecraft_extra.entity.custom;

import de.pizzapost.minecraft_extra.effect.ModEffects;
import de.pizzapost.minecraft_extra.entity.ModEntities;
import de.pizzapost.minecraft_extra.item.ModItems;
import de.pizzapost.minecraft_extra.sound.ModSounds;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class IcebombEntity extends ThrownItemEntity {
    private final Map<BlockPos, Integer> growingBlocks = new HashMap<>();
    private int animationTick = 0;

    public IcebombEntity(EntityType<? extends IcebombEntity> entityType, World world) {
        super(entityType, world);
    }

    public IcebombEntity(World world, LivingEntity owner, ItemStack stack) {
        super(ModEntities.ICEBOMB, owner, world, stack);
    }


    protected Item getDefaultItem() {
        return ModItems.ICEBOMB;
    }

    private ParticleEffect getParticleParameters() {
        return ParticleTypes.ITEM_SNOWBALL;
    }

    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for (int i = 0; i < 8; i++) {
                this.getWorld().addParticleClient(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int damage = entity instanceof BlazeEntity ? 5 : 2;
        entity.serverDamage(this.getDamageSources().thrown(this, this.getOwner()), (float) damage + 1);
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(ModEffects.FREEZE, 600, 0));
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient) {
            BlockPos currentPos = this.getBlockPos();
            if (this.getWorld().getBlockState(currentPos).isOf(Blocks.WATER)) {
                int maxRadius = 12;
                int waveDelay = 5;
                if (animationTick == 0) {
                    BlockPos hitPos = this.getBlockPos();
                    if (this.getWorld().getBlockState(hitPos).isOf(Blocks.WATER)) {
                        growingBlocks.put(hitPos, waveDelay);
                    }
                    for (int x = -maxRadius; x <= maxRadius; x++) {
                        for (int y = -maxRadius; y <= maxRadius; y++) {
                            for (int z = -maxRadius; z <= maxRadius; z++) {
                                BlockPos targetPos = currentPos.add(x, y, z);
                                double distance = Math.sqrt(x * x + y * y + z * z);
                                if (distance <= maxRadius && this.getWorld().getBlockState(targetPos).isOf(Blocks.WATER)) {
                                    growingBlocks.put(targetPos, (int) Math.ceil(distance) * waveDelay);
                                }
                            }
                        }
                    }
                }
                animationTick++;
                growingBlocks.entrySet().removeIf(entry -> {
                    BlockPos pos = entry.getKey();
                    int delay = entry.getValue();
                    if (delay == animationTick) {
                        this.getWorld().setBlockState(pos, Blocks.FROSTED_ICE.getDefaultState());
                        ServerWorld serverWorld = (ServerWorld) this.getWorld();
                        serverWorld.spawnParticles(ParticleTypes.SNOWFLAKE, pos.getX(), pos.getY() + 1, pos.getZ(), 3, 0.0, 0.0, 0.0, 0.2);
                        for (ServerPlayerEntity player : serverWorld.getPlayers()) {
                            if (player.getX() > pos.getX() - 25 && player.getX() < pos.getX() + 25 && player.getY() > pos.getY() - 25 && player.getY() < pos.getY() + 25 && player.getZ() > pos.getZ() - 25 && player.getZ() < pos.getZ() + 25) {
                                serverWorld.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.ICEBOMB_BUILD, SoundCategory.BLOCKS, 0.04f, random.nextFloat() * 0.4F + 0.8F);
                            }
                        }
                        return true;
                    }
                    return false;
                });
                if (growingBlocks.isEmpty()) {
                    this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
                    this.discard();
                }
            }
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
        }
    }
}