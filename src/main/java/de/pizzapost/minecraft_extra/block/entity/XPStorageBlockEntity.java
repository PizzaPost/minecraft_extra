package de.pizzapost.minecraft_extra.block.entity;

import de.pizzapost.minecraft_extra.block.ModBlockEntities;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;

public class XPStorageBlockEntity extends BlockEntity {
    private final SimpleInventory inventory = new SimpleInventory(1);

    public XPStorageBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.XP_STORAGE_BE, pos, state);
    }

    public ItemStack getItem() {
        return inventory.getStack(0);
    }

    public void setItem(ItemStack stack) {
        inventory.setStack(0, stack);
        markDirty();
    }
}
