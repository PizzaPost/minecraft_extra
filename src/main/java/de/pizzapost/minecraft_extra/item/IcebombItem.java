package de.pizzapost.minecraft_extra.item;

import de.pizzapost.minecraft_extra.entity.custom.IcebombEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

import java.util.List;

public class IcebombItem extends Item {

    public IcebombItem(Item.Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ENTITY_SNOWBALL_THROW,
                SoundCategory.NEUTRAL,
                0.5F,
                0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        if (!world.isClient) {
            IcebombEntity iceBomb = new IcebombEntity(world, user);
            iceBomb.setItem(itemStack);
            iceBomb.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(iceBomb);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.success(itemStack, world.isClient());
    }

    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        IcebombEntity IcebombEntity = new IcebombEntity(world, pos.getX(), pos.getY(), pos.getZ());
        IcebombEntity.setItem(stack);
        return IcebombEntity;
    }
}
