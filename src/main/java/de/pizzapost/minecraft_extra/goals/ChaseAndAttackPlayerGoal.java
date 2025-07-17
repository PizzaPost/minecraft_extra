package de.pizzapost.minecraft_extra.goals;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.List;

public class ChaseAndAttackPlayerGoal extends Goal {
    private final MobEntity mob;
    private final double speed;
    private final double detectionRange;
    private PlayerEntity target;

    public ChaseAndAttackPlayerGoal(MobEntity mob, double speed, double detectionRange) {
        this.mob = mob;
        this.speed = speed;
        this.detectionRange = detectionRange;
    }

    @Override
    public boolean canStart() {
        List<PlayerEntity> players = this.mob.getWorld().getEntitiesByClass(PlayerEntity.class, this.mob.getBoundingBox().expand(detectionRange), player -> true);
        if (!players.isEmpty()) {
            this.target = players.get(0);
            return true;
        }
        return false;
    }

    @Override
    public void start() {
        this.mob.getNavigation().startMovingTo(this.target, this.speed);
    }

    @Override
    public void stop() {
        this.target = null;
        this.mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (this.target != null && this.mob.squaredDistanceTo(this.target) <= 10.5) {
            if (this.mob.getWorld() instanceof ServerWorld serverWorld) {
                this.mob.tryAttack(serverWorld, this.target);
            }
        } else {
            this.mob.getNavigation().startMovingTo(this.target, this.speed);
        }
    }
}
