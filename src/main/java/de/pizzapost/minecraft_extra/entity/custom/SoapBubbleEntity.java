package de.pizzapost.minecraft_extra.entity.custom;

import de.pizzapost.minecraft_extra.effect.ModEffects;
import de.pizzapost.minecraft_extra.sound.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SoapBubbleEntity extends HostileEntity {
    private static final double DESPAWN_RANGE = 3.0;

    public SoapBubbleEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.setNoGravity(true);
        this.setInvulnerable(true);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient) {
            PlayerEntity targetPlayer = findNearestSoapedPlayer();
            if (targetPlayer == null) {
                this.getWorld().playSound(null, this.getBlockPos(), ModSounds.SOAP_BUBBLE_POP, SoundCategory.AMBIENT, 1f, 1f);
                this.discard();
                return;
            }
            Vec3d playerPos = targetPlayer.getPos();
            double distanceSq = this.squaredDistanceTo(playerPos.x, playerPos.y, playerPos.z);
            if (distanceSq > 0) {
                this.refreshPositionAndAngles(playerPos.x, playerPos.y, playerPos.z, this.getYaw(), this.getPitch());
                this.setVelocity(Vec3d.ZERO);
                this.velocityDirty = true;
            }
        }
        this.setVelocity(this.getVelocity().multiply(1, 0, 1));
        this.velocityDirty = true;
        this.fallDistance = 0f;
    }

    private PlayerEntity findNearestSoapedPlayer() {
        return this.getWorld().getPlayers().stream()
                .filter(player ->
                        player.isAlive() &&
                                player.hasStatusEffect(ModEffects.SOAPED) &&
                                this.squaredDistanceTo(player) <= DESPAWN_RANGE * DESPAWN_RANGE
                )
                .min((p1, p2) ->
                        Double.compare(this.squaredDistanceTo(p1), this.squaredDistanceTo(p2))
                )
                .orElse(null);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return true;
    }

    @Override
    public boolean canHit() {
        return false;
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public void pushAwayFrom(Entity entity) {
    }

    @Override
    public void pushAway(Entity entity) {
    }

    @Override
    protected void mobTick() {
        this.setVelocity(Vec3d.ZERO);
        super.mobTick();
    }

    @Override
    public boolean isClimbing() {
        return false;
    }

    @Override
    public boolean canAvoidTraps() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return false;
    }
}