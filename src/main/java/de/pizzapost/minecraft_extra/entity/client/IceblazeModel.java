package de.pizzapost.minecraft_extra.entity.client;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class IceblazeModel extends EntityModel<IceblazeRenderState> {
    public static final EntityModelLayer ICEBLAZE = new EntityModelLayer(Identifier.of(MinecraftExtra.MOD_ID, "iceblaze"), "main");

    private final ModelPart root;
    private final ModelPart Head;
    private final ModelPart body0;
    private final ModelPart body1;
    private final ModelPart body2;

    private final Animation idleAnimation;

    public IceblazeModel(ModelPart root) {
        super(root);
        this.root = root.getChild("root");
        this.Head = this.root.getChild("Head");
        this.body0 = this.root.getChild("body0");
        this.body1 = this.root.getChild("body1");
        this.body2 = this.root.getChild("body2");

        this.idleAnimation = IceblazeAnimations.IDLE.createAnimation(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 24.0F, 0.0F));

        ModelPartData Head = root.addChild("Head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -4.0F, 8, 8, 8, new Dilation(0.0F)), ModelTransform.origin(0.0F, -24.0F, 0.0F));

        ModelPartData body0 = root.addChild("body0", ModelPartBuilder.create(), ModelTransform.NONE);
        body0.addChild("upperBodyParts0", ModelPartBuilder.create().uv(0, 16).cuboid(0, 0, 0, 2, 8, 2, new Dilation(0.0F)), ModelTransform.origin(8.0F, -26.0F, -3.0F));
        body0.addChild("upperBodyParts1", ModelPartBuilder.create().uv(0, 16).cuboid(0, 0, 0, 2, 8, 2, new Dilation(0.0F)), ModelTransform.origin(-10.0F, -26.0F, 1.0F));
        body0.addChild("upperBodyParts2", ModelPartBuilder.create().uv(0, 16).cuboid(0, 0, 0, 2, 8, 2, new Dilation(0.0F)), ModelTransform.origin(1.0F, -26.0F, 8.0F));
        body0.addChild("upperBodyParts3", ModelPartBuilder.create().uv(0, 16).cuboid(0, 0, 0, 2, 8, 2, new Dilation(0.0F)), ModelTransform.origin(-3.0F, -26.0F, -10.0F));

        ModelPartData body1 = root.addChild("body1", ModelPartBuilder.create(), ModelTransform.NONE);
        body1.addChild("upperBodyParts4", ModelPartBuilder.create().uv(0, 16).cuboid(0, 0, 0, 2, 8, 2, new Dilation(0.0F)), ModelTransform.origin(5.0F, -18.0F, -1.0F));
        body1.addChild("upperBodyParts5", ModelPartBuilder.create().uv(0, 16).cuboid(0, 0, 0, 2, 8, 2, new Dilation(0.0F)), ModelTransform.origin(-7.0F, -18.0F, -1.0F));
        body1.addChild("upperBodyParts6", ModelPartBuilder.create().uv(0, 16).cuboid(0, 0, 0, 2, 8, 2, new Dilation(0.0F)), ModelTransform.origin(-1.0F, -18.0F, 5.0F));
        body1.addChild("upperBodyParts7", ModelPartBuilder.create().uv(0, 16).cuboid(0, 0, 0, 2, 8, 2, new Dilation(0.0F)), ModelTransform.origin(-1.0F, -18.0F, -7.0F));

        ModelPartData body2 = root.addChild("body2", ModelPartBuilder.create(), ModelTransform.NONE);
        body2.addChild("upperBodyParts8", ModelPartBuilder.create().uv(0, 16).cuboid(0, 0, 0, 2, 8, 2, new Dilation(0.0F)), ModelTransform.origin(3.0F, -8.0F, 2.0F));
        body2.addChild("upperBodyParts9", ModelPartBuilder.create().uv(0, 16).cuboid(0, 0, 0, 2, 8, 2, new Dilation(0.0F)), ModelTransform.origin(-5.0F, -8.0F, -4.0F));
        body2.addChild("upperBodyParts10", ModelPartBuilder.create().uv(0, 16).cuboid(0, 0, 0, 2, 8, 2, new Dilation(0.0F)), ModelTransform.origin(-4.0F, -8.0F, 3.0F));
        body2.addChild("upperBodyParts11", ModelPartBuilder.create().uv(0, 16).cuboid(0, 0, 0, 2, 8, 2, new Dilation(0.0F)), ModelTransform.origin(2.0F, -8.0F, -5.0F));

        return TexturedModelData.of(modelData, 64, 32);
    }

    @Override
    public void setAngles(IceblazeRenderState state) {
        super.setAngles(state);
        float yaw = MathHelper.clamp(state.relativeHeadYaw, -30.0F, 30.0F) * (MathHelper.PI / 180F);
        float pitch = MathHelper.clamp(state.pitch, -25.0F, 45.0F) * (MathHelper.PI / 180F);
        this.Head.yaw = yaw;
        this.Head.pitch = pitch;
        this.idleAnimation.apply(state.idleAnimationState, state.age, 1.0F);
    }
}