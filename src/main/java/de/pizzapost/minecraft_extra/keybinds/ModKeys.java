package de.pizzapost.minecraft_extra.keybinds;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ModKeys {
    public static final KeyBinding jumpBoostKey = new KeyBinding(
            "key.minecraft_extra.jump_boost",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_ALT,
            "key.categories.gameplay"
    );

    public static final KeyBinding silkTouchKey = new KeyBinding(
            "key.minecraft_extra.silk_touch",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_ALT,
            "key.categories.gameplay"
    );

    public static final KeyBinding nightVisionKey = new KeyBinding(
            "key.minecraft_extra.night_vision",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            "key.categories.gameplay"
    );

    public static void registerKeyBindings() {
        net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper.registerKeyBinding(jumpBoostKey);
        net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper.registerKeyBinding(silkTouchKey);
        net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper.registerKeyBinding(nightVisionKey);
    }
}
