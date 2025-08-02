package de.pizzapost.minecraft_extra.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tryMerge", at = @At("HEAD"), cancellable = true)
    private void increaseMergeRadius(CallbackInfo ci) {
        ItemEntity self = (ItemEntity) (Object) this;

        if (!this.canMerge()) return;

        double radius = 10;

        for (ItemEntity other : self.getWorld().getEntitiesByClass(ItemEntity.class, self.getBoundingBox().expand(radius, 0.0, radius), other -> other != self && ((ItemEntityMixin) (Object) other).canMerge())) {
            if (self.isRemoved()) break;
            ((ItemEntityMixin) (Object) self).tryMerge(other);
        }

        ci.cancel();
    }

    @Shadow
    protected abstract boolean canMerge();

    @Shadow
    protected abstract void tryMerge(ItemEntity other);
}
