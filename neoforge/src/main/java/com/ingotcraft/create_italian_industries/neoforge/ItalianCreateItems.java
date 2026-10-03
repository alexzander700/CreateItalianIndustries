package com.ingotcraft.create_italian_industries.neoforge;

import com.ingotcraft.create_italian_industries.Create_italian_industries;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

/**
 * Items that need Create's classes, so they live in the NeoForge module (Create 6 is NeoForge-only here).
 * The "incomplete" item is what the dough turns into while it moves through the pizza assembly line; it
 * shows the progress bar and is replaced by the final Uncooked Pizza at the end.
 */
public final class ItalianCreateItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Create_italian_industries.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> INCOMPLETE_UNCOOKED_PIZZA = ITEMS.register(
            "incomplete_uncooked_pizza", () -> new SequencedAssemblyItem(new Item.Properties()));

    public static void register() {
        ITEMS.register();
    }

    private ItalianCreateItems() {}
}
