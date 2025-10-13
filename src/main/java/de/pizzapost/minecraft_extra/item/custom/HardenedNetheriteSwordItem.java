package de.pizzapost.minecraft_extra.item.custom;

import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class HardenedNetheriteSwordItem extends Item {


    public HardenedNetheriteSwordItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(settings.tool(material, BlockTags.SHOVEL_MINEABLE, attackDamage, attackSpeed, 0));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack item = player.getMainHandStack();
        if (player.experienceLevel >= 25) {
            if (!item.hasEnchantments()) {
                player.addExperienceLevels(-25);
                var changes = ComponentChanges.builder().add(DataComponentTypes.ITEM_NAME, item.getName().copy().formatted(Formatting.DARK_RED).formatted(Formatting.ITALIC)).add(DataComponentTypes.CUSTOM_NAME, item.getName().copy().formatted(Formatting.DARK_RED)).build();
                item.applyChanges(changes);
                RegistryWrapper<Enchantment> enchantmentRegistry = player.getEntityWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
                item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.BANE_OF_ARTHROPODS), 5);
                item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.SMITE), 5);
                item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.KNOCKBACK), 2);
                item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.SWEEPING_EDGE), 3);
                item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.UNBREAKING), 3);
                item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.LOOTING), 3);
                item.addEnchantment(enchantmentRegistry.getOrThrow(Enchantments.SHARPNESS), 5);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        String enchantments = stack.getEnchantments().toString();
        if (enchantments.contains("Bane of Arthropods}=>5") && enchantments.contains("Sharpness}=>5") && enchantments.contains("Unbreaking}=>3") && enchantments.contains("Smite}=>5") && enchantments.contains("Sweeping Edge}=>3") && enchantments.contains("Looting}=>3") && enchantments.contains("Knockback}=>2")) {
            if (!ModArmorItem.hasFullUnenchantedSuitOfArmorOn(target)) {
                target.setFireTicks(160);
            }
        }
    }
}