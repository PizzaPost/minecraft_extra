package de.pizzapost.minecraft_extra.item.custom;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.ServerTickManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class TimeControlDeviceItem extends Item {
    public static boolean frozen = false;

    public TimeControlDeviceItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!world.isClient) {
            frozen = !frozen;
            ServerTickManager serverTickManager = player.getServer().getTickManager();
            serverTickManager.setFrozen(frozen);
        }
        Identifier advancementId = Identifier.of(MinecraftExtra.MOD_ID, "time_control_device");
        if (player instanceof ServerPlayerEntity serverPlayer) {
            AdvancementEntry advancement = serverPlayer.getServer().getAdvancementLoader().get(advancementId);
            if (advancement != null) {
                serverPlayer.getAdvancementTracker().grantCriterion(advancement, "imp");
            }
        }
        return ActionResult.SUCCESS;
    }
}