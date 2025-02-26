package de.pizzapost.minecraft_extra.particle;
import de.pizzapost.minecraft_extra.MinecraftExtra;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
public class ModParticles {
    public static final SimpleParticleType EFFECT_GEM_PUSH_PARTICLE =
            registerParticle("effect_gem_push_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType HARDENED_NETHERITE_AMBIENT =
            registerParticle("hardened_netherite_ambient_particle", FabricParticleTypes.simple());

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MinecraftExtra.MOD_ID, name), particleType);
    }
    public static void registerParticles() {
        MinecraftExtra.LOGGER.info("Registering Particles for " + MinecraftExtra.MOD_ID);
    }
}