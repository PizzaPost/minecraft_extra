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

public class EffectGemPushParticle extends BillboardParticle {
    public EffectGemPushParticle(ClientWorld clientWorld, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, Sprite sprite) {
        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed, sprite);
        this.velocityMultiplier = 0.2f;
        this.maxAge = 2;
        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;
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
            return new EffectGemPushParticle(world, x, y, z, 0, 0, 0, sprite);
        }
    }
}