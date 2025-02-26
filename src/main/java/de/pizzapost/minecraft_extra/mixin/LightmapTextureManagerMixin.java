package de.pizzapost.minecraft_extra.mixin;

import de.pizzapost.minecraft_extra.MinecraftExtraClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import net.minecraft.client.render.LightmapTextureManager;

@Mixin(LightmapTextureManager.class)
public class LightmapTextureManagerMixin {
    @ModifyVariable(
            method = "update(F)V",
            at = @At(value = "STORE"),
            index = 8
    )
    private float lightLevel(float x) {
        return x + (1 - x) * MinecraftExtraClient.LIGHT_BOOST;
    }
}