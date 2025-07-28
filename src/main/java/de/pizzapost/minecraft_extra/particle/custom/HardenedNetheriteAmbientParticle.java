package de.pizzapost.minecraft_extra.particle.custom;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class HardenedNetheriteAmbientParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    public HardenedNetheriteAmbientParticle(ClientWorld clientWorld, double x, double y, double z, SpriteProvider spriteProvider, double xSpeed, double ySpeed, double zSpeed) {
        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed);
        this.spriteProvider = spriteProvider;
        this.velocityMultiplier = 0.8f;
        this.maxAge = 50;
        this.setBoundingBoxSpacing(0.2f, 0.2f);
        this.setSpriteForAge(spriteProvider);
        updateColor();
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteForAge(spriteProvider);
        updateColor();
    }

    private void updateColor() {
        float progress = (float) (age % 60) / 60.0f;
        float startR = 71 / 255.0f;
        float startG = 19 / 255.0f;
        float startB = 112 / 255.0f;
        float endR = 5 / 255.0f;
        float endG = 1 / 255.0f;
        float endB = 15 / 255.0f;
        this.red = startR + (endR - startR) * progress;
        this.green = startG + (endG - startG) * progress;
        this.blue = startB + (endB - startB) * progress;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new HardenedNetheriteAmbientParticle(world, x, y, z, this.spriteProvider, velocityX, velocityY, velocityZ);
        }
    }
}