package de.pizzapost.minecraft_extra.item.custom;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.sound.ModSounds;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.concurrent.ThreadLocalRandom;


public class WeatherItemItem extends Item {
    public WeatherItemItem(Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient()) {
            MinecraftServer server = world.getServer();
            if (server != null) {
                ServerWorld overworld = server.getWorld(World.OVERWORLD);
                if (overworld != null) {
                    int random = ThreadLocalRandom.current().nextInt(3);
                    if (random == 0) {
                        overworld.setWeather(ServerWorld.CLEAR_WEATHER_DURATION_PROVIDER.get(overworld.getRandom()), 0, false, false);
                        if (player instanceof ServerPlayerEntity serverPlayer) {
                            Identifier advancementId = Identifier.of(MinecraftExtra.MOD_ID, "weather");
                            AdvancementEntry advancement = world.getServer().getAdvancementLoader().get(advancementId);
                            if (advancement != null) {
                                serverPlayer.getAdvancementTracker().grantCriterion(advancement, "clear");
                            }
                        }
                    } else if (random == 1) {
                        overworld.setWeather(0, ServerWorld.RAIN_WEATHER_DURATION_PROVIDER.get(overworld.getRandom()), true, false);
                        if (player instanceof ServerPlayerEntity serverPlayer) {
                            Identifier advancementId = Identifier.of(MinecraftExtra.MOD_ID, "weather");
                            AdvancementEntry advancement = world.getServer().getAdvancementLoader().get(advancementId);
                            if (advancement != null) {
                                serverPlayer.getAdvancementTracker().grantCriterion(advancement, "rain");
                            }
                        }
                    } else if (random == 2) {
                        overworld.setWeather(0, ServerWorld.THUNDER_WEATHER_DURATION_PROVIDER.get(overworld.getRandom()), true, true);
                        if (player instanceof ServerPlayerEntity serverPlayer) {
                            Identifier advancementId = Identifier.of(MinecraftExtra.MOD_ID, "weather");
                            AdvancementEntry advancement = world.getServer().getAdvancementLoader().get(advancementId);
                            if (advancement != null) {
                                serverPlayer.getAdvancementTracker().grantCriterion(advancement, "thunder");
                            }
                        }
                    }
                    world.playSound(null, player.getBlockPos(), ModSounds.CHANGE_WEATHER, SoundCategory.AMBIENT, 1f, 1f);
                }
            }
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));
        player.getStackInHand(hand).decrementUnlessCreative(1, player);
        return ActionResult.SUCCESS;
    }
}