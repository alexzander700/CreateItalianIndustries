package com.ingotcraft.create_italian_industries;

import com.ingotcraft.create_italian_industries.registry.ModBlocks;
import com.ingotcraft.create_italian_industries.registry.ModFluids;
import com.ingotcraft.create_italian_industries.registry.ModItems;

public final class Create_italian_industries {
    public static final String MOD_ID = "create_italian_industries";

    public static void init() {
        ModFluids.register();
        ModBlocks.register();
        ModItems.register();
    }
}
