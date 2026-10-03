package com.ingotcraft.create_italian_industries.registry;

import com.ingotcraft.create_italian_industries.Create_italian_industries;
import dev.architectury.core.item.ArchitecturyBucketItem;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public final class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Create_italian_industries.MOD_ID, Registries.ITEM);

    // ---- Cheese line: Salt -> Curds -> Cheese -> Shredded Cheese. Generic items with no behaviour of their own. ----

    /** Made by heating water in a Basin. */
    public static final RegistrySupplier<Item> SALT = ITEMS.register("salt",
            () -> new Item(new Item.Properties()));

    /** Salt + Milk, Mixed in a heated Basin (the Whey comes out as a fluid at the same time). */
    public static final RegistrySupplier<Item> CURDS = ITEMS.register("curds",
            () -> new Item(new Item.Properties()));

    /** Curds, Pressed in a Basin. */
    public static final RegistrySupplier<Item> CHEESE = ITEMS.register("cheese",
            () -> new Item(new Item.Properties()));

    /** Cheese, ground in a Millstone. */
    public static final RegistrySupplier<Item> SHREDDED_CHEESE = ITEMS.register("shredded_cheese",
            () -> new Item(new Item.Properties()));

    // ---- Pizza line ----

    /** Pressed Dough. The base of the pizza assembly line. */
    public static final RegistrySupplier<Item> CRUST = ITEMS.register("crust",
            () -> new Item(new Item.Properties()));

    /** Crust + Tomato Sauce + Shredded Cheese. Not edible: it can only be cooked by Bulk Smoking. */
    public static final RegistrySupplier<Item> UNCOOKED_PIZZA = ITEMS.register("uncooked_pizza",
            () -> new Item(new Item.Properties()));

    /**
     * The finished pizza (stacks to 16), the result of Bulk Smoking an Uncooked Pizza. 8 hunger points; vanilla
     * works out saturation as nutrition * modifier * 2, so 0.6 gives 8 * 0.6 * 2 = 9.6 saturation.
     */
    public static final RegistrySupplier<Item> PIZZA = ITEMS.register("pizza",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationModifier(0.6F)
                            .build())));

    // ---- Fluid buckets ----

    public static final RegistrySupplier<Item> TOMATO_SAUCE_BUCKET = ITEMS.register("tomato_sauce_bucket",
            () -> new ArchitecturyBucketItem(ModFluids.SOURCE,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistrySupplier<Item> WHEY_BUCKET = ITEMS.register("whey_bucket",
            () -> new ArchitecturyBucketItem(ModFluids.WHEY,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistrySupplier<Item> LIQUID_FERTILIZER_BUCKET = ITEMS.register("liquid_fertilizer_bucket",
            () -> new ArchitecturyBucketItem(ModFluids.FERTILIZER,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static void register() {
        ITEMS.register();
        CreativeTabRegistry.append(CreativeModeTabs.FOOD_AND_DRINKS,
                SALT, CURDS, CHEESE, SHREDDED_CHEESE, CRUST, UNCOOKED_PIZZA, PIZZA);
        CreativeTabRegistry.append(CreativeModeTabs.TOOLS_AND_UTILITIES,
                TOMATO_SAUCE_BUCKET, WHEY_BUCKET, LIQUID_FERTILIZER_BUCKET);
    }

    private ModItems() {}
}
