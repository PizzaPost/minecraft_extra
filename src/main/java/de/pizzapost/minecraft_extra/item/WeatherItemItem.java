package de.pizzapost.minecraft_extra.item;

import de.pizzapost.minecraft_extra.sound.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import java.util.Random;




public class WeatherItemItem extends Item {
    public WeatherItemItem(Settings settings) {
        super(settings);
    }

    public void executeCommand(MinecraftServer server, String command) {
        ServerCommandSource source = server.getCommandSource();
        server.getCommandManager().executeWithPrefix(source, command);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        Random rand=new Random();
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        int random=rand.nextInt(3);
        if (!world.isClient() && player instanceof ServerPlayerEntity serverPlayer) {
            MinecraftServer server = serverPlayer.getServer();
            if (random == 0) {
                executeCommand(server, "weather clear");
            } else if (random == 1) {
                executeCommand(server, "weather rain");
            } else {
                executeCommand(server, "weather thunder");
            }
            world.playSound(null, player.getBlockPos(), ModSounds.CHANGE_WEATHER, SoundCategory.AMBIENT, 1f, 1f);
        }

        player.getItemCooldownManager().set(this, 200);
        player.getStackInHand(hand).decrementUnlessCreative(1, player);
        return TypedActionResult.success(player.getStackInHand(hand));
    }
}
