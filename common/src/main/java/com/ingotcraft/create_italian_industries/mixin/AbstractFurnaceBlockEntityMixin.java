package com.ingotcraft.create_italian_industries.mixin;

import com.ingotcraft.create_italian_industries.registry.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Uncooked Pizza is cooked with a vanilla-style smoking recipe, because that is the recipe type Create's
 * Bulk Smoking reads. The catch is that a vanilla Smoker reads the very same recipe, so this stops the
 * Uncooked Pizza from being placed in any furnace-type block's input slot (by hand, shift-click or hopper).
 * That leaves Bulk Smoking as the only way to cook it. (Bulk Blasting just destroys it: it has no
 * smelting or blasting recipe.)
 */
@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {
    @Inject(method = "canPlaceItem", at = @At("HEAD"), cancellable = true)
    private void create_italian_industries$noUncookedPizza(int slot, ItemStack stack,
                                                           CallbackInfoReturnable<Boolean> cir) {
        // Slot 0 is the furnace's input slot (1 is fuel, 2 is output).
        if (slot == 0 && stack.is(ModItems.UNCOOKED_PIZZA.get())) {
            cir.setReturnValue(false);
        }
    }
}
