package de.pizzapost.minecraft_extra.entity.client;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.entity.custom.IceblazeEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class IceblazeRenderer extends MobEntityRenderer<IceblazeEntity, IceblazeModel<IceblazeEntity>> {
    public IceblazeRenderer(EntityRendererFactory.Context context) {
        super(context, new IceblazeModel<>(context.getPart(IceblazeModel.ICEBLAZE)), 0.5f);
    }

    @Override
    public Identifier getTexture(IceblazeEntity entity) {
        return Identifier.of(MinecraftExtra.MOD_ID, "textures/entity/iceblaze/iceblaze.png");
    }

    @Override
    public void render(IceblazeEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f, 1f, 1f);
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
