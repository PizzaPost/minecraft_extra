package de.pizzapost.minecraft_extra;

import com.mojang.blaze3d.systems.RenderSystem;
import de.pizzapost.minecraft_extra.block.ModBlocks;
import de.pizzapost.minecraft_extra.effect.ModEffects;
import de.pizzapost.minecraft_extra.entity.client.IceblazeModel;
import de.pizzapost.minecraft_extra.entity.client.IceblazeRenderer;
import de.pizzapost.minecraft_extra.entity.client.SoapBubbleModel;
import de.pizzapost.minecraft_extra.entity.client.SoapBubbleRenderer;
import de.pizzapost.minecraft_extra.keybinds.ModKeys;
import de.pizzapost.minecraft_extra.particle.ModParticles;
import de.pizzapost.minecraft_extra.particle.custom.EffectGemPushParticle;
import de.pizzapost.minecraft_extra.particle.custom.HardenedNetheriteAmbientParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import de.pizzapost.minecraft_extra.entity.ModEntities;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class MinecraftExtraClient implements ClientModInitializer {
    public static Integer LIGHT_BOOST = 0;
    private static boolean wasFreezeActive = false;
    private static long freezeEffectStartTime = 0;
    @Override
    public void onInitializeClient() {
        ModKeys.registerKeyBindings();
        EntityRendererRegistry.register(ModEntities.ICEBOMB, FlyingItemEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(IceblazeModel.ICEBLAZE, IceblazeModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.ICEBLAZE, IceblazeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(SoapBubbleModel.SoapBubble, SoapBubbleModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.SOAP_BUBBLE, SoapBubbleRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HONEY_BERRY_BUSH, RenderLayer.getCutout());
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                if (!(client.player.getWorld().getRegistryKey() == World.END)) {
                    if (ModKeys.nightVisionKey.wasPressed()) {
                        if (LIGHT_BOOST == 0) LIGHT_BOOST = 5;
                        else LIGHT_BOOST = 0;
                    }
                } else {
                    LIGHT_BOOST = 0;
                }
            }
        });
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            if (player == null) {wasFreezeActive = false; return;}
            boolean isFreezeActive = player.hasStatusEffect(ModEffects.FREEZE);
            if (!isFreezeActive) {wasFreezeActive = false; return;}
            StatusEffectInstance freezeEffect = player.getStatusEffect(ModEffects.FREEZE);
            int remainingDuration = freezeEffect.getDuration();
            long currentTime = player.getWorld().getTime();
            if (!wasFreezeActive) {freezeEffectStartTime = currentTime; wasFreezeActive = true;}
            long elapsedTicks = currentTime - freezeEffectStartTime;
            float fadeInProgress = Math.min((float) elapsedTicks / 100f, 1f);
            float fadeOutProgress = Math.min((float) remainingDuration / 100f, 1f);
            float alpha = Math.min(fadeInProgress, fadeOutProgress) * 0.75f;
            Identifier texture = Identifier.of(MinecraftExtra.MOD_ID, "textures/gui/freeze_overlay.png");
            int width = drawContext.getScaledWindowWidth();
            int height = drawContext.getScaledWindowHeight();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShaderColor(1f, 1f, 1f, alpha);
            drawContext.drawTexture(texture, 0, 0, 0, 0, width, height, width, height);
            RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
            RenderSystem.disableBlend();
        });
        ParticleFactoryRegistry.getInstance().register(ModParticles.EFFECT_GEM_PUSH_PARTICLE, EffectGemPushParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.HARDENED_NETHERITE_AMBIENT, HardenedNetheriteAmbientParticle.Factory::new);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), ModBlocks.WHITE_PETALS);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), ModBlocks.LIGHT_GRAY_PETALS);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), ModBlocks.GRAY_PETALS);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), ModBlocks.BLACK_PETALS);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), ModBlocks.BROWN_PETALS);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), ModBlocks.RED_PETALS);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), ModBlocks.ORANGE_PETALS);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), ModBlocks.YELLOW_PETALS);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), ModBlocks.LIME_PETALS);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), ModBlocks.GREEN_PETALS);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), ModBlocks.CYAN_PETALS);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), ModBlocks.LIGHT_BLUE_PETALS);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), ModBlocks.BLUE_PETALS);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), ModBlocks.PURPLE_PETALS);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), ModBlocks.MAGENTA_PETALS);
    }
}
