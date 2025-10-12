package de.pizzapost.minecraft_extra;

import de.pizzapost.minecraft_extra.block.ModBlockEntities;
import de.pizzapost.minecraft_extra.block.ModBlocks;
import de.pizzapost.minecraft_extra.block.renderer.XPStorageEntityRenderer;
import de.pizzapost.minecraft_extra.effect.ModEffects;
import de.pizzapost.minecraft_extra.entity.ModEntities;
import de.pizzapost.minecraft_extra.entity.client.*;
import de.pizzapost.minecraft_extra.keybinds.ModKeys;
import de.pizzapost.minecraft_extra.particle.ModParticles;
import de.pizzapost.minecraft_extra.particle.custom.EffectGemPushParticle;
import de.pizzapost.minecraft_extra.particle.custom.HardenedNetheriteAmbientParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class MinecraftExtraClient implements ClientModInitializer {
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

        ParticleFactoryRegistry.getInstance().register(
                ModParticles.EFFECT_GEM_PUSH_PARTICLE,
                (FabricSpriteProvider provider) -> new EffectGemPushParticle.Factory(provider)
        );


        ParticleFactoryRegistry.getInstance().register(
                ModParticles.HARDENED_NETHERITE_AMBIENT,
                (FabricSpriteProvider provider) -> new HardenedNetheriteAmbientParticle.Factory(provider)
        );

        BlockEntityRendererFactories.register(ModBlockEntities.XP_STORAGE_BE, XPStorageEntityRenderer::new);
        BlockRenderLayerMap.putBlock(ModBlocks.XP_STORAGE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlocks(BlockRenderLayer.TRANSLUCENT, ModBlocks.GLASS_SLAB, ModBlocks.GLASS_STAIR, ModBlocks.GLASS_WALL, ModBlocks.WHITE_STAINED_GLASS_SLAB, ModBlocks.WHITE_STAINED_GLASS_STAIR, ModBlocks.WHITE_STAINED_GLASS_WALL, ModBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB, ModBlocks.LIGHT_GRAY_STAINED_GLASS_STAIR, ModBlocks.LIGHT_GRAY_STAINED_GLASS_WALL, ModBlocks.GRAY_STAINED_GLASS_SLAB, ModBlocks.GRAY_STAINED_GLASS_STAIR, ModBlocks.GRAY_STAINED_GLASS_WALL, ModBlocks.BLACK_STAINED_GLASS_SLAB, ModBlocks.BLACK_STAINED_GLASS_STAIR, ModBlocks.BLACK_STAINED_GLASS_WALL, ModBlocks.BROWN_STAINED_GLASS_SLAB, ModBlocks.BROWN_STAINED_GLASS_STAIR, ModBlocks.BROWN_STAINED_GLASS_WALL, ModBlocks.RED_STAINED_GLASS_SLAB, ModBlocks.RED_STAINED_GLASS_STAIR, ModBlocks.RED_STAINED_GLASS_WALL, ModBlocks.ORANGE_STAINED_GLASS_SLAB, ModBlocks.ORANGE_STAINED_GLASS_STAIR, ModBlocks.ORANGE_STAINED_GLASS_WALL, ModBlocks.YELLOW_STAINED_GLASS_SLAB, ModBlocks.YELLOW_STAINED_GLASS_STAIR, ModBlocks.YELLOW_STAINED_GLASS_WALL, ModBlocks.LIME_STAINED_GLASS_SLAB, ModBlocks.LIME_STAINED_GLASS_STAIR, ModBlocks.LIME_STAINED_GLASS_WALL, ModBlocks.GREEN_STAINED_GLASS_SLAB, ModBlocks.GREEN_STAINED_GLASS_STAIR, ModBlocks.GREEN_STAINED_GLASS_WALL, ModBlocks.CYAN_STAINED_GLASS_SLAB, ModBlocks.CYAN_STAINED_GLASS_STAIR, ModBlocks.CYAN_STAINED_GLASS_WALL, ModBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB, ModBlocks.LIGHT_BLUE_STAINED_GLASS_STAIR, ModBlocks.LIGHT_BLUE_STAINED_GLASS_WALL, ModBlocks.BLUE_STAINED_GLASS_SLAB, ModBlocks.BLUE_STAINED_GLASS_STAIR, ModBlocks.BLUE_STAINED_GLASS_WALL, ModBlocks.PURPLE_STAINED_GLASS_SLAB, ModBlocks.PURPLE_STAINED_GLASS_STAIR, ModBlocks.PURPLE_STAINED_GLASS_WALL, ModBlocks.MAGENTA_STAINED_GLASS_SLAB, ModBlocks.MAGENTA_STAINED_GLASS_STAIR, ModBlocks.MAGENTA_STAINED_GLASS_WALL, ModBlocks.PINK_STAINED_GLASS_SLAB, ModBlocks.PINK_STAINED_GLASS_STAIR, ModBlocks.PINK_STAINED_GLASS_WALL);
    }
}