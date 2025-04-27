package de.pizzapost.minecraft_extra.entity.client;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.entity.custom.EndCrystalMobEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class EndCrystalMobRenderer extends MobEntityRenderer<EndCrystalMobEntity, EndCrystalMobModel<EndCrystalMobEntity>> {
    public EndCrystalMobRenderer(EntityRendererFactory.Context context) {
        super(context, new EndCrystalMobModel<>(context.getPart(EndCrystalMobModel.END_CRYSTAL_MOB)), 0.5f);
    }

    @Override
    public Identifier getTexture(EndCrystalMobEntity entity) {
        return Identifier.of(MinecraftExtra.MOD_ID, "textures/entity/end_crystal_mob/end_crystal_mob.png");
    }

    @Override
    public void render(EndCrystalMobEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f, 1f, 1f);
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
