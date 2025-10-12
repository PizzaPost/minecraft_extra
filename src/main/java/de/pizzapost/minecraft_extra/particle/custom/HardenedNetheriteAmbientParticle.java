package de.pizzapost.minecraft_extra.particle.custom;

import net.minecraft.client.particle.BillboardParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

public class HardenedNetheriteAmbientParticle extends BillboardParticle {
    public HardenedNetheriteAmbientParticle(ClientWorld clientWorld, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, Sprite sprite) {
        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed, sprite);
        this.velocityMultiplier = 0.8f;
        this.maxAge = 50;
        this.setBoundingBoxSpacing(0.2f, 0.2f);
        updateColor();
    }

    @Override
    public void tick() {
        super.tick();
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
    public RenderType getRenderType() {
        return RenderType.PARTICLE_ATLAS_TRANSLUCENT;
    }

    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Random random) {
            Sprite sprite = this.spriteProvider.getSprite(random);
            return new HardenedNetheriteAmbientParticle(world, x, y, z, velocityX, velocityY, velocityZ, sprite);
        }
    }
}