package de.pizzapost.minecraft_extra.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import de.pizzapost.minecraft_extra.MinecraftExtra;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandSource;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

import static net.minecraft.server.command.CommandManager.*;

public class ModCommands {
    public static void initializeCommands() {
        SuggestionProvider<ServerCommandSource> USERNAME_SUGGESTIONS = (context, builder) ->
                CommandSource.suggestMatching(List.of("PizzaPost", "Ghxo", "MoCoXIII", "lay123"), builder);

        SuggestionProvider<ServerCommandSource> COMMAND_SUGGESTIONS = (context, builder) ->
                CommandSource.suggestMatching(List.of("bob", "bug", "credits", "discord", "feedback", "socials"), builder);

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("bob")
                .executes(context -> {
                    context.getSource().sendFeedback(() -> Text.translatable("command.minecraft_extra.bob"), false);
                    return 1;
                })));

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("feedback")
                .executes(context -> {
                    Text link = Text.translatable("command.minecraft_extra.link")
                            .styled(style -> style.withUnderline(true)
                                    .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/CzUuUwfV7j"))
                                    .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.translatable("command.minecraft_extra.hover.click"))));
                    Text message = Text.translatable("command.minecraft_extra.feedback").append(link);
                    context.getSource().sendFeedback(() -> message, false);
                    return 1;
                })));

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("bug")
                .executes(context -> {
                    Text link = Text.translatable("command.minecraft_extra.link")
                            .styled(style -> style.withUnderline(true)
                                    .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/CzUuUwfV7j"))
                                    .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.translatable("command.minecraft_extra.hover.click"))));
                    Text message = Text.translatable("command.minecraft_extra.bug").append(link);
                    context.getSource().sendFeedback(() -> message, false);
                    return 1;
                })));

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("discord")
                .executes(context -> {
                    Text link = Text.translatable("command.minecraft_extra.link")
                            .styled(style -> style.withUnderline(true)
                                    .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/CzUuUwfV7j"))
                                    .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.translatable("command.minecraft_extra.hover.click"))));
                    Text message = Text.translatable("command.minecraft_extra.discord").append(link);
                    context.getSource().sendFeedback(() -> message, false);
                    return 1;
                })));

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("credits")
                .executes(context -> {
                    Text linkPizzaPost = Text.translatable("command.minecraft_extra.credits6")
                            .styled(style -> style
                                    .withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/socials PizzaPost"))
                                    .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.translatable("command.minecraft_extra.hover.click"))));
                    Text linkGhxo = Text.translatable("command.minecraft_extra.credits7")
                            .styled(style -> style
                                    .withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/socials Ghxo"))
                                    .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.translatable("command.minecraft_extra.hover.click"))));
                    Text linkMoCoXIII = Text.translatable("command.minecraft_extra.credits8")
                            .styled(style -> style
                                    .withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/socials MoCoXIII"))
                                    .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.translatable("command.minecraft_extra.hover.click"))));
                    Text linkLay123 = Text.translatable("command.minecraft_extra.credits9")
                            .styled(style -> style
                                    .withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/socials lay123"))
                                    .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.translatable("command.minecraft_extra.hover.click"))));
                    Text message2 = Text.translatable("command.minecraft_extra.credits2").append(linkPizzaPost);
                    Text message3 = Text.translatable("command.minecraft_extra.credits3").append(linkGhxo).append("\n").append(linkPizzaPost);
                    Text message4 = Text.translatable("command.minecraft_extra.credits4").append(linkPizzaPost).append(linkGhxo).append("\n").append(linkMoCoXIII).append(linkLay123);
                    Text message5 = Text.translatable("command.minecraft_extra.credits5").append(linkLay123).append(linkMoCoXIII).append(linkGhxo);
                    Text message = Text.translatable("command.minecraft_extra.credits1").append(message2).append(message3).append(message4).append(message5);
                    context.getSource().sendFeedback(() -> message, false);
                    return 1;
                })));

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("socials")
                    .then(CommandManager.argument("username", StringArgumentType.word())
                            .suggests(USERNAME_SUGGESTIONS)
                            .executes(context -> {
                                String username = StringArgumentType.getString(context, "username");
                                ServerCommandSource source = context.getSource();

                                Text socialsMessage;
                                switch(username) {
                                    case "PizzaPost":
                                        socialsMessage = createSocialsMessage(
                                                "PizzaPost",
                                                Formatting.GREEN,
                                                createLink("YouTube", "https://www.youtube.com/@pizzapost1234", Formatting.DARK_RED),
                                                createLink("Instagram", "https://www.instagram.com/8002_phil", Formatting.LIGHT_PURPLE),
                                                createLink("Instagram", "https://www.instagram.com/pizzapoststudio", Formatting.LIGHT_PURPLE),
                                                createLink("Discord", "https://www.discord.com/users/916636380967354419", Formatting.DARK_BLUE)
                                        );
                                        break;
                                    case "Ghxo":
                                        socialsMessage = createSocialsMessage(
                                                "Ghxo",
                                                Formatting.DARK_GREEN,
                                                createLink("Instagram", "https://www.instagram.com/theoderehrenmann", Formatting.LIGHT_PURPLE),
                                                createLink("Discord", "https://www.discord.com/users/997734571791613963", Formatting.DARK_BLUE),
                                                createLink("TikTok", "https://www.tiktok.com/@theoderehrenmann", Formatting.WHITE)
                                        );
                                        break;
                                    case "MoCoXIII":
                                        socialsMessage = createSocialsMessage(
                                                "MoCoXIII",
                                                Formatting.DARK_BLUE,
                                                createLink("YouTube", "https://www.youtube.com/@Yahtl", Formatting.DARK_RED),
                                                createLink("Instagram", "https://www.instagram.com/yahtlabts", Formatting.LIGHT_PURPLE),
                                                createLink("Discord", "https://www.discord.com/users/819205588818985031", Formatting.DARK_BLUE),
                                                createLink("GitHub", "https://github.com/MoCoXIII", Formatting.WHITE)
                                        );
                                        break;
                                    case "lay123":
                                        socialsMessage = createSocialsMessage(
                                                "lay123",
                                                Formatting.LIGHT_PURPLE,
                                                createLink("Instagram", "https://www.instagram.com/jakob_nbr07", Formatting.LIGHT_PURPLE),
                                                createLink("Discord", "https://www.discord.com/users/775388584735014913", Formatting.DARK_BLUE)
                                        );
                                        break;
                                    default:
                                        source.sendError(Text.translatable("command.minecraft_extra.socials.error")
                                                .formatted(Formatting.RED));
                                        return 0;
                                }

                                source.sendMessage(socialsMessage);
                                return 1;
                            })
                    )
            );
        });
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("commands")
                .then(CommandManager.argument("command", StringArgumentType.word())
                        .suggests(COMMAND_SUGGESTIONS)
                        .executes(context -> {
                            String command = StringArgumentType.getString(context, "command");
                            ServerCommandSource source = context.getSource();
                            switch(command) {
                                case "bob":
                                    source.sendMessage(Text.translatable("command.minecraft_extra.commands.bob"));
                                    break;
                                case "bug":
                                    source.sendMessage(Text.translatable("command.minecraft_extra.commands.bug"));
                                    break;
                                case "credits":
                                    source.sendMessage(Text.translatable("command.minecraft_extra.commands.credits"));
                                    break;
                                case "discord":
                                    source.sendMessage(Text.translatable("command.minecraft_extra.commands.discord"));
                                    break;
                                case "feedback":
                                    source.sendMessage(Text.translatable("command.minecraft_extra.commands.feedback"));
                                    break;
                                case "socials":
                                    source.sendMessage(Text.translatable("command.minecraft_extra.commands.socials"));
                                    break;
                                default:
                                    source.sendError(Text.translatable("command.minecraft_extra.commands.error")
                                            .formatted(Formatting.RED));
                                    return 0;
                            }
                            return 1;
                        })
                )));
    }

    private static Text createSocialsMessage(String username, Formatting nameColor, Text... links) {
        MutableText base = Text.translatable(username)
                .formatted(nameColor)
                .append(Text.literal(":\n"));

        for(int i = 0; i < links.length; i++) {
            base.append(links[i]);
            if(i < links.length - 1) base.append(Text.literal(" "));
        }

        return base;
    }

    private static Text createLink(String platform, String url, Formatting color) {
        return Text.literal(platform)
                .styled(style -> style
                        .withColor(color)
                        .withUnderline(true)
                        .withClickEvent(new ClickEvent(
                                ClickEvent.Action.OPEN_URL,
                                url
                        ))
                        .withHoverEvent(new HoverEvent(
                                HoverEvent.Action.SHOW_TEXT,
                                Text.translatable("command.minecraft_extra.hover.click", url)
                        )));
    }



    public static void registerModCommands() {
        MinecraftExtra.LOGGER.info("Registering Mod Commands for " + MinecraftExtra.MOD_ID);
        initializeCommands();
    }
}