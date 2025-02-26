package de.pizzapost.minecraft_extra.entity.client;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.entity.custom.SoapBubbleEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SoapBubbleRenderer extends MobEntityRenderer<SoapBubbleEntity, SoapBubbleModel<SoapBubbleEntity>> {
    public SoapBubbleRenderer(EntityRendererFactory.Context context) {
        super(context, new SoapBubbleModel<>(context.getPart(SoapBubbleModel.SoapBubble)), 0.5F);
    }

    @Override
    protected RenderLayer getRenderLayer(SoapBubbleEntity entity, boolean showBody, boolean translucent, boolean showOutline) {
        return RenderLayer.getEntityTranslucent(this.getTexture(entity));
    }

    @Override
    public Identifier getTexture(SoapBubbleEntity entity) {
        return Identifier.of(MinecraftExtra.MOD_ID, "textures/entity/soap_bubble/soap_bubble.png");
    }
}