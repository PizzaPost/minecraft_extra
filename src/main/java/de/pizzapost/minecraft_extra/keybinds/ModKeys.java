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
            GLFW.GLFW_KEY_LEFT_CONTROL,
            "key.categories.gameplay"
    );

    public static void registerKeyBindings() {
        net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper.registerKeyBinding(jumpBoostKey);
    }
}
