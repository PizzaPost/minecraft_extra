package de.pizzapost.minecraft_extra.entity.client;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.entity.custom.SoapBubbleEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SoapBubbleRenderer extends MobEntityRenderer<SoapBubbleEntity, SoapBubbleRenderState, SoapBubbleModel> {
    public SoapBubbleRenderer(EntityRendererFactory.Context context) {
        super(context, new SoapBubbleModel(context.getPart(SoapBubbleModel.SoapBubble)), 0.5f);
    }

    @Override
    public SoapBubbleRenderState createRenderState() {
        return new SoapBubbleRenderState();
    }

    @Override
    public Identifier getTexture(SoapBubbleRenderState state) {
        return Identifier.of(MinecraftExtra.MOD_ID, "textures/entity/soap_bubble/soap_bubble.png");
    }
}