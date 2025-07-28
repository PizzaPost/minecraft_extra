package de.pizzapost.minecraft_extra.entity.client;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class EndCrystalMobModel extends EntityModel<EndCrystalMobRenderState> {
    public static final EntityModelLayer END_CRYSTAL_MOB = new EntityModelLayer(Identifier.of(MinecraftExtra.MOD_ID, "end_crystal_mob"), "main");
    private final ModelPart cube;

    public EndCrystalMobModel(ModelPart root) {
        super(root);
        this.cube = root.getChild("cube");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData cube = modelPartData.addChild("cube", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -6.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 14.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(EndCrystalMobRenderState state) {
        super.setAngles(state);
        this.setHeadAngles(state.relativeHeadYaw, state.pitch);
        this.animate(state.idleAnimationState, EndCrystalMobAnimations.IDLE, state.age, 1f);
        this.animateWalking(EndCrystalMobAnimations.WALKING, state.limbSwingAnimationProgress, state.limbSwingAmplitude, 0f, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.cube.yaw = headYaw * 0.017453292F;
        this.cube.pitch = headPitch * 0.017453292F;
    }
}
