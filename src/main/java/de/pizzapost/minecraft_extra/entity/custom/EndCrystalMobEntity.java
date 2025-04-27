package de.pizzapost.minecraft_extra.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.world.World;

public class EndCrystalMobEntity extends HostileEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState walkingAnimationState = new AnimationState();
    private int attackTick = 0;

    public EndCrystalMobEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 60;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 100.0F));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 100)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 70)
                .add(EntityAttributes.GENERIC_STEP_HEIGHT, 200);
    }

    private void setupAnimationStates() {
        if (this.getWorld().isClient()) {
            boolean isMoving = this.getVelocity().horizontalLengthSquared() > 0 || this.navigation.isFollowingPath();
            this.idleAnimationState.startIfNotRunning(this.age);
            this.walkingAnimationState.startIfNotRunning(this.age);
            if (isMoving) {
                this.idleAnimationState.setRunning(false, this.age);
                this.walkingAnimationState.setRunning(true, this.age);
            } else {
                this.walkingAnimationState.setRunning(false, this.age);
                this.idleAnimationState.setRunning(true, this.age);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }

        if (this.attackTick > 0) {
            this.attackTick--;
        }
    }

    @Override
    public boolean tryAttack(Entity target) {
        this.attackTick = 10;
        this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_ATTACK_SOUND);
        return super.tryAttack(target);
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_ATTACK_SOUND) {
            this.attackTick = 10;
            return;
        }
        super.handleStatus(status);
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    public boolean isFireImmune() {
        return true;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return damageSource.isIn(DamageTypeTags.IS_EXPLOSION)
                || damageSource.isIn(DamageTypeTags.IS_FALL)
                || super.isInvulnerableTo(damageSource);
    }
}