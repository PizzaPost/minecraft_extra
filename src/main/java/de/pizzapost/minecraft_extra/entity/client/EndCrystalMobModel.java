package de.pizzapost.minecraft_extra.entity.client;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.entity.custom.EndCrystalMobEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class EndCrystalMobModel<T extends EndCrystalMobEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer END_CRYSTAL_MOB = new EntityModelLayer(Identifier.of(MinecraftExtra.MOD_ID, "end_crystal_mob"), "main");
    private final ModelPart cube;
    public EndCrystalMobModel(ModelPart root) {
        this.cube = root.getChild("cube");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData cube = modelPartData.addChild("cube", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -6.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 14.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(EndCrystalMobEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);
        this.updateAnimation(entity.idleAnimationState, EndCrystalMobAnimations.IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.walkingAnimationState, EndCrystalMobAnimations.WALKING, ageInTicks, MathHelper.clamp(limbSwingAmount * 2f, 0f, 1f));
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.cube.yaw = headYaw * 0.017453292F;
        this.cube.pitch = headPitch * 0.017453292F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        cube.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return this.cube;
    }
}
