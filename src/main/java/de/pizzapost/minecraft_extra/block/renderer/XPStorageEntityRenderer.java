package de.pizzapost.minecraft_extra.block.renderer;

import de.pizzapost.minecraft_extra.block.custom.XPStorage;
import de.pizzapost.minecraft_extra.block.entity.XPStorageBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class XPStorageEntityRenderer implements BlockEntityRenderer<XPStorageBlockEntity, XPStorageEntityRenderState> {

    public XPStorageEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }

    public XPStorageEntityRenderState createRenderState() {
        return new XPStorageEntityRenderState();
    }

    public void render(XPStorageEntityRenderState state, MatrixStack matrixStack, OrderedRenderCommandQueue queue, CameraRenderState cameraRenderState) {
        int xp = state.blockState.get(XPStorage.XP);
        if (xp <= 0) return;
        int amount;
        if (xp >= 10253) {
            amount = 15;
        } else {
            amount = Math.max(1, (int) Math.ceil(xp / (10253.0 / 15)));
        }
        //I would render the xp bottles here but 1.21.9 destroyed me
    }
}
