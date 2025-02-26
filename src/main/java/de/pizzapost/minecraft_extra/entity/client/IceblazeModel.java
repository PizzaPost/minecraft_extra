package de.pizzapost.minecraft_extra.entity.client;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.entity.custom.IceblazeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class IceblazeModel<T extends IceblazeEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer ICEBLAZE = new EntityModelLayer(Identifier.of(MinecraftExtra.MOD_ID, "iceblaze"), "main");

    private final ModelPart root;
    private final ModelPart Head;
    private final ModelPart body0;
    private final ModelPart body1;
    private final ModelPart body2;
    public IceblazeModel(ModelPart root) {
        this.root = root.getChild("root");
        this.Head = this.root.getChild("Head");
        this.body0 = this.root.getChild("body0");
        this.body1 = this.root.getChild("body1");
        this.body2 = this.root.getChild("body2");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData Head = root.addChild("Head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

        ModelPartData body0 = root.addChild("body0", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData upperBodyParts0 = body0.addChild("upperBodyParts0", ModelPartBuilder.create().uv(0, 16).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, -26.0F, -3.0F));

        ModelPartData upperBodyParts1 = body0.addChild("upperBodyParts1", ModelPartBuilder.create().uv(0, 16).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-10.0F, -26.0F, 1.0F));

        ModelPartData upperBodyParts2 = body0.addChild("upperBodyParts2", ModelPartBuilder.create().uv(0, 16).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -26.0F, 8.0F));

        ModelPartData upperBodyParts3 = body0.addChild("upperBodyParts3", ModelPartBuilder.create().uv(0, 16).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -26.0F, -10.0F));

        ModelPartData body1 = root.addChild("body1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData upperBodyParts4 = body1.addChild("upperBodyParts4", ModelPartBuilder.create().uv(0, 16).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, -18.0F, -1.0F));

        ModelPartData upperBodyParts5 = body1.addChild("upperBodyParts5", ModelPartBuilder.create().uv(0, 16).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-7.0F, -18.0F, -1.0F));

        ModelPartData upperBodyParts6 = body1.addChild("upperBodyParts6", ModelPartBuilder.create().uv(0, 16).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -18.0F, 5.0F));

        ModelPartData upperBodyParts7 = body1.addChild("upperBodyParts7", ModelPartBuilder.create().uv(0, 16).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -18.0F, -7.0F));

        ModelPartData body2 = root.addChild("body2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData upperBodyParts8 = body2.addChild("upperBodyParts8", ModelPartBuilder.create().uv(0, 16).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, -8.0F, 2.0F));

        ModelPartData upperBodyParts9 = body2.addChild("upperBodyParts9", ModelPartBuilder.create().uv(0, 16).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -8.0F, -4.0F));

        ModelPartData upperBodyParts10 = body2.addChild("upperBodyParts10", ModelPartBuilder.create().uv(0, 16).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, -8.0F, 3.0F));

        ModelPartData upperBodyParts11 = body2.addChild("upperBodyParts11", ModelPartBuilder.create().uv(0, 16).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -8.0F, -5.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }
    @Override
    public void setAngles(IceblazeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.updateAnimation(entity.idleAnimationState, IceblazeAnimations.idle, ageInTicks, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.Head.yaw = headYaw * 0.017453292F;
        this.Head.pitch = headPitch *0.017453292F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        root.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return root;
    }
}
