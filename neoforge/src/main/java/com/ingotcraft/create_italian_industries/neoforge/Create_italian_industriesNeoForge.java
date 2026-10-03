package com.ingotcraft.create_italian_industries.neoforge;

import com.ingotcraft.create_italian_industries.Create_italian_industries;
import com.ingotcraft.create_italian_industries.neoforge.client.ItalianClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(Create_italian_industries.MOD_ID)
public final class Create_italian_industriesNeoForge {
    public Create_italian_industriesNeoForge(IEventBus modBus) {
        // Run our common setup.
        Create_italian_industries.init();

        // NeoForge-only: items that use Create's classes.
        ItalianCreateItems.register();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            ItalianClient.init(modBus);
        }
    }
}
