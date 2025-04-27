package de.pizzapost.minecraft_extra.entity.client;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.entity.custom.SoapBubbleEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SoapBubbleModel<T extends SoapBubbleEntity> extends EntityModel<T> {
    public static final EntityModelLayer SoapBubble = new EntityModelLayer(Identifier.of(MinecraftExtra.MOD_ID, "soap_bubble"), "main");
    private final ModelPart bb_main;

    public SoapBubbleModel(ModelPart root) {
        this.bb_main = root.getChild("bb_main");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("bb_main", ModelPartBuilder.create()
                .uv(0, 0).cuboid(-16.0F, -32.0F, -16.0F, 32.0F, 32.0F, 32.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        int alpha = 127;
        int transparentColor = (color & 0x00FFFFFF) | (alpha << 24);
        bb_main.render(matrices, vertices, light, overlay, transparentColor);
    }
}