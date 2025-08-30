package de.pizzapost.minecraft_extra.block.custom;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FeatherBlock extends Block {
    public FeatherBlock(Settings settings) {
        super(settings);
    }

    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        entity.handleFallDamage(fallDistance, 0.5F, world.getDamageSources().fall());
        if (entity instanceof ServerPlayerEntity serverPlayer && fallDistance > 13) {
            Identifier advancementId = Identifier.of(MinecraftExtra.MOD_ID, "feather_block");
            AdvancementEntry advancement = serverPlayer.getServer().getAdvancementLoader().get(advancementId);
            if (advancement != null) {
                serverPlayer.getAdvancementTracker().grantCriterion(advancement, "imp");
            }
        }
    }
}
