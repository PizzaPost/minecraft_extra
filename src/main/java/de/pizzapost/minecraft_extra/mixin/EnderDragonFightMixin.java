package de.pizzapost.minecraft_extra.mixin;

import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.boss.dragon.phase.HoldingPatternPhase;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.gen.feature.EndSpikeFeature;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.UUID;

@Mixin(EnderDragonFight.class)
public abstract class EnderDragonFightMixin {
    @Shadow
    private ServerWorld world;
    @Shadow
    private int endCrystalsAlive;
    @Shadow
    private ServerBossBar bossBar;
    @Shadow
    private @Nullable UUID dragonUuid;
    private ServerBossBar crystalsBossBar;

    @Inject(
            method = "<init>(Lnet/minecraft/server/world/ServerWorld;JLnet/minecraft/entity/boss/dragon/EnderDragonFight$Data;Lnet/minecraft/util/math/BlockPos;)V",
            at = @At("RETURN")
    )
    private void initCrystalsBossBar(ServerWorld world, long gatewaysSeed, EnderDragonFight.Data data, BlockPos origin, CallbackInfo ci) {
        this.crystalsBossBar = (ServerBossBar) new ServerBossBar(
                Text.translatable("bossbar.minecraft_extra.end_crystals_searching"),
                BossBar.Color.GREEN,
                BossBar.Style.PROGRESS
        )
                .setDarkenSky(false)
                .setThickenFog(false);
    }

    @Inject(method = "countAliveCrystals", at = @At("TAIL"))
    private void updateCrystalsBossBar(CallbackInfo ci) {
        List<EndSpikeFeature.Spike> spikes = EndSpikeFeature.getSpikes(this.world);
        float progress = (float) this.endCrystalsAlive / spikes.size();
        this.crystalsBossBar.setPercent(progress);
        this.crystalsBossBar.setName(Text.translatable("bossbar.minecraft_extra.end_crystals_left", this.endCrystalsAlive, spikes.size()));
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void updateCrystalsBossBarPlayers(CallbackInfo ci) {
        this.crystalsBossBar.setVisible(this.bossBar.isVisible());
        this.bossBar.getPlayers().forEach(player -> {
            if (!this.crystalsBossBar.getPlayers().contains(player)) {
                this.crystalsBossBar.addPlayer(player);
            }
        });
    }

    @Inject(method = "dragonKilled", at = @At("TAIL"))
    private void hideCrystalsBossBar(EnderDragonEntity dragon, CallbackInfo ci) {
        this.crystalsBossBar.setVisible(false);
    }

    @Redirect(method = "dragonKilled(Lnet/minecraft/entity/boss/dragon/EnderDragonEntity;)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/entity/boss/dragon/EnderDragonFight;previouslyKilled:Z",
                    opcode = Opcodes.GETFIELD))
    private boolean redirectPreviouslyKilled(EnderDragonFight instance) {
        return false;
    }

    @Inject(method = "updateFight", at = @At("HEAD"))
    private void updateBossBarColor(EnderDragonEntity dragon, CallbackInfo ci) {
        if (dragon.getPhaseManager().getCurrent().getType() == PhaseType.LANDING || dragon.getPhaseManager().getCurrent().getType() == PhaseType.LANDING_APPROACH) {
            bossBar.setColor(BossBar.Color.RED);
        } else {
            bossBar.setColor(BossBar.Color.PINK);
        }
    }
}