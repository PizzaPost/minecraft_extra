package de.pizzapost.minecraft_extra.item.custom;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class LightningStickItem extends Item {

    public LightningStickItem(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!world.isClient()) {
            if (!player.getItemCooldownManager().isCoolingDown(itemStack)) {
                player.getItemCooldownManager().set(itemStack, 10 * 20);
                List<LivingEntity> livingEntities = world.getEntitiesByClass(LivingEntity.class, player.getBoundingBox().expand(50), entity -> entity != player);
                for (LivingEntity livingEntity : livingEntities) {
                    BlockPos pos = livingEntity.getBlockPos();
                    if (world instanceof ServerWorld serverWorld) {
                        EntityType.LIGHTNING_BOLT.spawn(serverWorld, pos, SpawnReason.TRIGGERED);
                    }
                }
                if (!livingEntities.isEmpty()) {
                    if (player instanceof ServerPlayerEntity serverPlayer) {
                        Identifier advancementId = Identifier.of(MinecraftExtra.MOD_ID, "lightning_stick");
                        AdvancementEntry advancement = serverPlayer.getServer().getAdvancementLoader().get(advancementId);
                        if (advancement != null) {
                            serverPlayer.getAdvancementTracker().grantCriterion(advancement, "imp");
                        }
                    }
                }
            }
            itemStack.decrement(1);
        }
        return ActionResult.SUCCESS;
    }
}