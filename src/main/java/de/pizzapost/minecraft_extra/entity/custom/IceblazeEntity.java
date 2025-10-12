package de.pizzapost.minecraft_extra.entity.custom;

import de.pizzapost.minecraft_extra.goals.ChaseAndAttackPlayerGoal;
import de.pizzapost.minecraft_extra.item.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class IceblazeEntity extends HostileEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationCooldown = 0;
    private int hoverTimer = 0;
    private float verticalVelocity = 0.0f;
    private boolean grounded = false;
    private final Random random = new Random();

    private final ServerBossBar bossbar = new ServerBossBar(Text.translatable("entity.minecraft_extra.iceblaze"), BossBar.Color.BLUE, BossBar.Style.PROGRESS);

    public IceblazeEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 10;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 32.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.goalSelector.add(4, new GoToWalkTargetGoal(this, 1.0));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0, 0.0F));
        this.goalSelector.add(2, new ChaseAndAttackPlayerGoal(this, 1.2, 10.0));
        this.goalSelector.add(0, new SwimGoal(this));
        this.targetSelector.add(3, new ActiveTargetGoal(this, PlayerEntity.class, true));
        this.targetSelector.add(1, new IceblazeEntity.ShootIcebombGoal(this));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.MAX_HEALTH, 80.0).add(EntityAttributes.MOVEMENT_SPEED, 0.5).add(EntityAttributes.ATTACK_DAMAGE, 6.5).add(EntityAttributes.ATTACK_SPEED, 1.0);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationCooldown <= 0) {
            this.idleAnimationCooldown = 4000;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationCooldown;
        }
    }

    @Override
    public void tick() {
        super.tick();
        List<Entity> entities = this.getEntityWorld().getEntitiesByClass(Entity.class, this.getBoundingBox().expand(5.0), Entity::isAlive);
        for (Entity entity : entities) {
            if (entity instanceof ArrowEntity) {
                entity.setAngles(entity.getYaw(), -90);
                entity.setVelocity(0, -0.5, 0);
            }
        }
        if (!this.grounded) {
            if (this.hoverTimer > 0) {
                this.hoverTimer--;
                this.verticalVelocity = 0.0f;
            } else if (this.random.nextInt(200) == 0) {
                this.grounded = true;
            } else {
                this.verticalVelocity = MathHelper.sin(this.age * 0.05f) * 0.1f;
            }
        } else {
            if (this.random.nextInt(200) == 0) {
                this.grounded = false;
                this.hoverTimer = 50 + this.random.nextInt(150);
            } else {
                this.verticalVelocity = -0.02f;
            }
        }
        this.setVelocity(this.getVelocity().x, this.verticalVelocity, this.getVelocity().z);
        if (this.getEntityWorld().isClient()) {
            this.setupAnimationStates();
        }
    }

    @Override
    public boolean tryAttack(ServerWorld serverWorld, Entity target) {
        float f = (float) this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);
        DamageSource damageSource = this.getDamageSources().mobAttack(this);
        f = EnchantmentHelper.getDamage(serverWorld, this.getWeaponStack(), target, damageSource, f);

        boolean bl = target.damage(serverWorld, damageSource, f);
        if (bl) {
            float g = this.getAttackKnockbackAgainst(target, damageSource);
            if (g > 0.0F && target instanceof LivingEntity livingEntity) {
                livingEntity.takeKnockback((double) (g * 0.5F), (double) MathHelper.sin(this.getYaw() * (float) (Math.PI / 180.0)), (double) (-MathHelper.cos(this.getYaw() * (float) (Math.PI / 180.0))));
                this.setVelocity(this.getVelocity().multiply(0.6, 1.0, 0.6));
            }

            EnchantmentHelper.onTargetDamaged(serverWorld, target, damageSource);

            this.onAttacking(target);
            this.playAttackSound();
        }

        return bl;
    }

    static class ShootIcebombGoal extends Goal {
        private final IceblazeEntity iceblaze;
        private int icebombsFired;
        private int icebombCooldown;
        private int targetNotVisibleTicks;
        private final Random random = new Random();

        public ShootIcebombGoal(IceblazeEntity blaze) {
            this.iceblaze = blaze;
            this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
        }

        @Override
        public boolean canStart() {
            LivingEntity livingEntity = this.iceblaze.getTarget();
            return livingEntity != null && livingEntity.isAlive() && this.iceblaze.canTarget(livingEntity);
        }

        @Override
        public void start() {
            this.icebombsFired = 0;
        }

        @Override
        public void stop() {
            this.targetNotVisibleTicks = 0;
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            this.icebombCooldown--;
            LivingEntity livingEntity = this.iceblaze.getTarget();
            if (livingEntity != null) {
                boolean bl = this.iceblaze.getVisibilityCache().canSee(livingEntity);
                if (bl) {
                    this.targetNotVisibleTicks = 0;
                } else {
                    this.targetNotVisibleTicks++;
                }
                double d = this.iceblaze.squaredDistanceTo(livingEntity);
                if (d < 4.0) {
                    if (!bl) {
                        return;
                    }
                    if (this.icebombCooldown <= 0) {
                        this.icebombCooldown = 20;
                        this.iceblaze.tryAttack(getServerWorld(this.iceblaze), livingEntity);
                    }
                    this.iceblaze.getMoveControl().moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0);
                } else if (d < this.getFollowRange() * this.getFollowRange() && bl) {
                    double e = livingEntity.getX() - this.iceblaze.getX();
                    double f = livingEntity.getBodyY(0.5) - this.iceblaze.getBodyY(0.5);
                    double g = livingEntity.getZ() - this.iceblaze.getZ();
                    if (this.icebombCooldown <= 0) {
                        this.icebombsFired++;
                        if (this.icebombsFired == 1) {
                            this.icebombCooldown = 60;
                        } else if (this.icebombsFired <= 4) {
                            this.icebombCooldown = 6;
                        } else {
                            this.icebombCooldown = 100;
                            this.icebombsFired = 0;
                        }
                        if (this.icebombsFired > 1) {
                            double h = Math.sqrt(Math.sqrt(d)) * 0.5;
                            if (!this.iceblaze.isSilent()) {
                                this.iceblaze.getEntityWorld().syncWorldEvent(null, WorldEvents.BLAZE_SHOOTS, this.iceblaze.getBlockPos(), 0);
                            }
                            for (int i = 0; i < 16; i++) {
                                Vec3d vec3d = new Vec3d(this.iceblaze.getRandom().nextTriangular(e, 2.297 * h), f, this.iceblaze.getRandom().nextTriangular(g, 2.297 * h));
                                IcebombEntity IcebombEntity = new IcebombEntity(this.iceblaze.getEntityWorld(), this.iceblaze, new ItemStack(ModItems.ICEBOMB));
                                IcebombEntity.setVelocity(vec3d.x + random.nextDouble(-.2, .2), vec3d.y + random.nextDouble(-.2, .2), vec3d.z + random.nextDouble(-.2, .2));
                                IcebombEntity.setPosition(IcebombEntity.getX(), this.iceblaze.getBodyY(0.5) + 0.5, IcebombEntity.getZ());
                                this.iceblaze.getEntityWorld().spawnEntity(IcebombEntity);
                            }
                        }
                    }
                    this.iceblaze.getLookControl().lookAt(livingEntity, 10.0F, 10.0F);
                } else if (this.targetNotVisibleTicks < 5) {
                    this.iceblaze.getMoveControl().moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0);
                }
                super.tick();
            }
        }

        private double getFollowRange() {
            return this.iceblaze.getAttributeValue(EntityAttributes.FOLLOW_RANGE);
        }
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossbar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossbar.removePlayer(player);
    }

    @Override
    protected void mobTick(ServerWorld serverWorld) {
        super.mobTick(serverWorld);
        this.bossbar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public boolean isInvulnerableTo(ServerWorld serverWorld, DamageSource damageSource) {
        return damageSource.isIn(DamageTypeTags.IS_FALL)
                || super.isInvulnerableTo(serverWorld, damageSource);
    }
}
