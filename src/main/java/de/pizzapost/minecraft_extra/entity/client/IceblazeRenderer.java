package de.pizzapost.minecraft_extra.entity.client;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.entity.custom.IceblazeEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class IceblazeRenderer extends MobEntityRenderer<IceblazeEntity, IceblazeRenderState, IceblazeModel> {

    public IceblazeRenderer(EntityRendererFactory.Context context) {
        super(context, new IceblazeModel(context.getPart(IceblazeModel.ICEBLAZE)), 0.5f);
    }

    @Override
    public Identifier getTexture(IceblazeRenderState state) {
        return Identifier.of(MinecraftExtra.MOD_ID, "textures/entity/iceblaze/iceblaze.png");
    }

    @Override
    public IceblazeRenderState createRenderState() {
        return new IceblazeRenderState();
    }

    @Override
    public void updateRenderState(IceblazeEntity livingEntity, IceblazeRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
    }
}
