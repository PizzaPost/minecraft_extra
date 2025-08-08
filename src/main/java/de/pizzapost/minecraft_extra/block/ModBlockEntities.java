package de.pizzapost.minecraft_extra.block;

import de.pizzapost.minecraft_extra.MinecraftExtra;
import de.pizzapost.minecraft_extra.block.entity.XPStorageBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<XPStorageBlockEntity> XP_STORAGE_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MinecraftExtra.MOD_ID, "xp_storage_be"), FabricBlockEntityTypeBuilder.create(XPStorageBlockEntity::new, ModBlocks.XP_STORAGE).build(null));

    public static void registerBlockEntities() {
        MinecraftExtra.LOGGER.info("Registering Mod Block Entities for " + MinecraftExtra.MOD_ID);
    }
}
