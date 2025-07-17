package de.pizzapost.minecraft_extra.mixin;

import net.minecraft.client.gui.screen.GameMenuScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.net.URI;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin {

    @ModifyArg(method = "addFeedbackAndBugsButtons", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/GameMenuScreen;" + "createUrlButton(Lnet/minecraft/client/gui/screen/Screen;" + "Lnet/minecraft/text/Text;Ljava/net/URI;)Lnet/minecraft/client/gui/widget/ButtonWidget;", ordinal = 0), index = 2

    )
    private static URI replaceFeedbackUrl(URI originalUri) {
        return URI.create("https://discord.gg/CzUuUwfV7j");
    }

    @ModifyArg(method = "addFeedbackAndBugsButtons", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/GameMenuScreen;" + "createUrlButton(Lnet/minecraft/client/gui/screen/Screen;" + "Lnet/minecraft/text/Text;Ljava/net/URI;)Lnet/minecraft/client/gui/widget/ButtonWidget;", ordinal = 1), index = 2

    )
    private static URI replaceBugReportUrl(URI originalUri) {
        return URI.create("https://discord.gg/CzUuUwfV7j");
    }
}