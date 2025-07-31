package de.pizzapost.minecraft_extra;

import de.pizzapost.minecraft_extra.block.ModBlocks;
import de.pizzapost.minecraft_extra.effect.ModEffects;
import de.pizzapost.minecraft_extra.entity.ModEntities;
import de.pizzapost.minecraft_extra.entity.client.*;
import de.pizzapost.minecraft_extra.keybinds.ModKeys;
import de.pizzapost.minecraft_extra.particle.ModParticles;
import de.pizzapost.minecraft_extra.particle.custom.EffectGemPushParticle;
import de.pizzapost.minecraft_extra.particle.custom.HardenedNetheriteAmbientParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class MinecraftExtraClient implements ClientModInitializer {
    public static Integer LIGHT_BOOST = 0;
    private static float freezeOverlayAlpha = 0f;
    private static final float FADE_SPEED = 0.01f;
    private static final float MAX_ALPHA = 0.4f;
    private static boolean NIGHT_VISION = false;

    @Override
    public void onInitializeClient() {
        ModKeys.registerKeyBindings();
        EntityRendererRegistry.register(ModEntities.ICEBOMB, FlyingItemEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(IceblazeModel.ICEBLAZE, IceblazeModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.ICEBLAZE, IceblazeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(SoapBubbleModel.SoapBubble, SoapBubbleModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.SOAP_BUBBLE, SoapBubbleRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(EndCrystalMobModel.END_CRYSTAL_MOB, EndCrystalMobModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.END_CRYSTAL_MOB, EndCrystalMobRenderer::new);

        BlockRenderLayerMap.putBlock(ModBlocks.HONEY_BERRY_BUSH, BlockRenderLayer.CUTOUT);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                if (ModKeys.nightVisionKey.wasPressed()) {
                    NIGHT_VISION = !NIGHT_VISION;
                }
                if (NIGHT_VISION) {
                    client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 300, 0, false, false));
                } else {
                    client.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
                }
                boolean isFreezeActive = client.player.hasStatusEffect(ModEffects.FREEZE);
                if (isFreezeActive) {
                    if (freezeOverlayAlpha < MAX_ALPHA) {
                        freezeOverlayAlpha += FADE_SPEED;
                        if (freezeOverlayAlpha > MAX_ALPHA) freezeOverlayAlpha = MAX_ALPHA;
                    }
                } else {
                    if (freezeOverlayAlpha > 0) {
                        freezeOverlayAlpha -= FADE_SPEED;
                        if (freezeOverlayAlpha < 0) freezeOverlayAlpha = 0;
                    }
                }
            } else {
                freezeOverlayAlpha = 0;
            }
        });
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (freezeOverlayAlpha <= 0) return;
            int alpha = (int) (freezeOverlayAlpha * 255);
            int color = (alpha << 24) | 0x00FFFF;
            int width = drawContext.getScaledWindowWidth();
            int height = drawContext.getScaledWindowHeight();
            drawContext.fill(0, 0, width, height, color);
        });

        ParticleFactoryRegistry.getInstance().register(ModParticles.EFFECT_GEM_PUSH_PARTICLE, EffectGemPushParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.HARDENED_NETHERITE_AMBIENT, HardenedNetheriteAmbientParticle.Factory::new);
    }
}