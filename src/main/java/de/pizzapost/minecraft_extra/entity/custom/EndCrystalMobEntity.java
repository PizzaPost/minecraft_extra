package de.pizzapost.minecraft_extra.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
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
                .add(EntityAttributes.MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.MAX_HEALTH, 75)
                .add(EntityAttributes.ATTACK_DAMAGE, 50)
                .add(EntityAttributes.STEP_HEIGHT, 200);
    }

    private void setupAnimationStates() {
        if (this.getWorld().isClient()) {
            boolean isMoving = this.getVelocity().horizontalLengthSquared() > 0 || this.navigation.isFollowingPath();
            System.out.println(isMoving);
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
    public boolean tryAttack(ServerWorld serverWorld, Entity target) {
        this.attackTick = 10;
        this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_ATTACK_SOUND);
        return super.tryAttack(serverWorld, target);
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
    public boolean isFireImmune() {
        return true;
    }

    @Override
    public boolean isInvulnerableTo(ServerWorld serverWorld, DamageSource damageSource) {
        return damageSource.isIn(DamageTypeTags.IS_EXPLOSION)
                || damageSource.isIn(DamageTypeTags.IS_FALL)
                || super.isInvulnerableTo(serverWorld, damageSource);
    }
}