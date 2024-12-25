package de.pizzapost.minecraft_extra.entity.custom;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.entity.ModEntities;
import de.pizzapost.minecraft_extra.item.ModItems;
import de.pizzapost.minecraft_extra.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class IcebombEntity extends ThrownItemEntity {
    private static final Identifier TEXTURE = Identifier.of(MinecraftExtra.MOD_ID, "textures/entity/icebomb.png");
    private final Map<BlockPos, Integer> growingBlocks = new HashMap<>();
    private int animationTick = 0;
    public IcebombEntity(EntityType<? extends IcebombEntity> entityType, World world) {
        super(entityType, world);
    }

    public Identifier getTexture(IcebombEntity entity) {
        return TEXTURE;
    }

    public IcebombEntity(World world, LivingEntity owner) {
        super(EntityType.SNOWBALL, owner, world);
    }

    public IcebombEntity(World world, double x, double y, double z) {
        super(EntityType.SNOWBALL, x, y, z, world);
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
                this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int damage = entity instanceof BlazeEntity ? 5 : 2;
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), (float) damage);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient) {
            BlockPos currentPos = this.getBlockPos();

            if (this.getWorld().getBlockState(currentPos).isOf(Blocks.WATER)) {
                int maxRadius = 12;
                int waveDelay = 5;
;                if (animationTick == 0) {
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
                        return true;
                    }
                    return false;
                });
                if (growingBlocks.isEmpty()) {
                    this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.ENTITY_ICEBOMB, SoundCategory.BLOCKS, 1f, random.nextFloat() * 0.4F + 0.8F);
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
