package com.ingotcraft.create_italian_industries.neoforge.client;

import com.ingotcraft.create_italian_industries.Create_italian_industries;
import com.ingotcraft.create_italian_industries.registry.ModFluids;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

import java.util.function.Supplier;

/** Client-only wiring. Only ever called when running on the client dist. */
public final class ItalianClient {
    public static void init(IEventBus modBus) {
        modBus.addListener(ItalianClient::onRegisterClientExtensions);
    }

    /**
     * Gives each fluid type its textures directly. Other mods (Create's pipe/pump splash particles) ask the
     * fluid type for its still texture and crash if they get null, so we don't rely on Architectury's
     * generated extension having been picked up. Source and flowing share one fluid type, so registering
     * the source covers both.
     */
    private static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        register(event, ModFluids.SOURCE, "tomato_sauce");
        register(event, ModFluids.WHEY, "whey");
        register(event, ModFluids.FERTILIZER, "liquid_fertilizer");
    }

    private static void register(RegisterClientExtensionsEvent event, Supplier<? extends Fluid> source, String name) {
        ResourceLocation still = ResourceLocation.fromNamespaceAndPath(Create_italian_industries.MOD_ID, "block/" + name + "_still");
        ResourceLocation flowing = ResourceLocation.fromNamespaceAndPath(Create_italian_industries.MOD_ID, "block/" + name + "_flow");
        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return still;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return flowing;
            }

            @Override
            public int getTintColor() {
                return 0xFFFFFFFF;   // textures are already coloured; no tint
            }
        }, source.get().getFluidType());
    }

    private ItalianClient() {}
}
