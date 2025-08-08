package de.pizzapost.minecraft_extra.block.renderer;

import de.pizzapost.minecraft_extra.block.custom.XPStorage;
import de.pizzapost.minecraft_extra.block.entity.XPStorageBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;

public class XPStorageEntityRenderer implements BlockEntityRenderer<XPStorageBlockEntity> {

    public XPStorageEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }

    @Override
    public void render(XPStorageBlockEntity entity, float tickProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d cameraPos) {
        int xp = entity.getCachedState().get(XPStorage.XP);
        if (xp <= 0) return;
        int amount;
        if (xp >= 10253) {
            amount = 15;
        } else {
            amount = Math.max(1, (int) Math.ceil(xp / (10253.0 / 15)));
        }

        for (int i = 0; i < amount; i++) {
            matrices.push();
            float angle = (360.0f / amount) * i;
            matrices.translate(0.5, 0.25, 0.5);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(angle + (float) (entity.getWorld().getTime() + tickProgress) * 4));
            double radius = 0.3;
            matrices.translate(radius, Math.sin((entity.getWorld().getTime() + tickProgress) / 8.0) / 6.0, 0);
            int lightAbove = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().up());
            ItemStack xpBottle = new ItemStack(Items.EXPERIENCE_BOTTLE);
            ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
            itemRenderer.renderItem(xpBottle, net.minecraft.item.ItemDisplayContext.GROUND, lightAbove, overlay, matrices, vertexConsumers, entity.getWorld(), 0);
            matrices.pop();
        }
    }
}
