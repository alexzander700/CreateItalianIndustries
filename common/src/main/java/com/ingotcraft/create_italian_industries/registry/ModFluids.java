package com.ingotcraft.create_italian_industries.registry;

import com.ingotcraft.create_italian_industries.Create_italian_industries;
import dev.architectury.core.fluid.ArchitecturyFlowingFluid;
import dev.architectury.core.fluid.ArchitecturyFluidAttributes;
import dev.architectury.core.fluid.SimpleArchitecturyFluidAttributes;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;

/**
 * All three fluids are valuable, crafted resources, so they are deliberately NOT water-like:
 *  - convertToSource(false): vanilla water turns a gap between two source blocks into a new source
 *    ("infinite water"). With this off, you can never duplicate the fluid by arranging buckets.
 *  - dropOff(2) / slopeFindDistance(2): they spread about as far as lava does in the Overworld
 *    (a few blocks) instead of water's 7, so a bucket makes a small puddle, not a lake.
 *  - tickDelay: they flow slowly, like thick liquids (water is 5, Overworld lava is 30). Tomato Sauce is the
 *    thickest and matches lava at 30; Whey and Liquid Fertilizer are a little runnier at 20.
 * Same density/viscosity as water, and none of them is in the minecraft:water tag, so they don't
 * hydrate farmland, extinguish fires or count as water for other mods. Textures are fully opaque, and fluids
 * render in the solid layer unless registered otherwise, so nothing else is needed to keep them opaque.
 *
 * Qualified names (ModFluids.X) inside the lambdas avoid illegal-forward-reference errors, since each set of
 * attributes needs its fluids and each fluid needs its attributes.
 */
public final class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(Create_italian_industries.MOD_ID, Registries.FLUID);

    // ---- Tomato Sauce (keeps the original SOURCE / FLOWING names) ----
    public static final ArchitecturyFluidAttributes ATTRIBUTES = SimpleArchitecturyFluidAttributes
            .of(() -> ModFluids.FLOWING.get(), () -> ModFluids.SOURCE.get())
            .blockSupplier(() -> ModBlocks.TOMATO_SAUCE)
            .bucketItemSupplier(() -> ModItems.TOMATO_SAUCE_BUCKET)
            .sourceTexture(id("block/tomato_sauce_still"))
            .flowingTexture(id("block/tomato_sauce_flow"))
            .color(0xFFFFFFFF)          // textures are already coloured; no tint
            .density(1000)
            .viscosity(1000)
            .luminosity(0)
            .slopeFindDistance(2)
            .dropOff(2)
            .tickDelay(30)
            .convertToSource(false);

    public static final RegistrySupplier<FlowingFluid> SOURCE = FLUIDS.register("tomato_sauce",
            () -> new ArchitecturyFlowingFluid.Source(ATTRIBUTES));
    public static final RegistrySupplier<FlowingFluid> FLOWING = FLUIDS.register("flowing_tomato_sauce",
            () -> new ArchitecturyFlowingFluid.Flowing(ATTRIBUTES));

    // ---- Whey: the byproduct of making curds ----
    public static final ArchitecturyFluidAttributes WHEY_ATTRIBUTES = SimpleArchitecturyFluidAttributes
            .of(() -> ModFluids.WHEY_FLOWING.get(), () -> ModFluids.WHEY.get())
            .blockSupplier(() -> ModBlocks.WHEY)
            .bucketItemSupplier(() -> ModItems.WHEY_BUCKET)
            .sourceTexture(id("block/whey_still"))
            .flowingTexture(id("block/whey_flow"))
            .color(0xFFFFFFFF)
            .density(1000)
            .viscosity(1000)
            .luminosity(0)
            .slopeFindDistance(2)
            .dropOff(2)
            .tickDelay(20)
            .convertToSource(false);

    public static final RegistrySupplier<FlowingFluid> WHEY = FLUIDS.register("whey",
            () -> new ArchitecturyFlowingFluid.Source(WHEY_ATTRIBUTES));
    public static final RegistrySupplier<FlowingFluid> WHEY_FLOWING = FLUIDS.register("flowing_whey",
            () -> new ArchitecturyFlowingFluid.Flowing(WHEY_ATTRIBUTES));

    // ---- Liquid Fertilizer: whey mixed with water. Does nothing by itself yet. ----
    public static final ArchitecturyFluidAttributes FERTILIZER_ATTRIBUTES = SimpleArchitecturyFluidAttributes
            .of(() -> ModFluids.FERTILIZER_FLOWING.get(), () -> ModFluids.FERTILIZER.get())
            .blockSupplier(() -> ModBlocks.LIQUID_FERTILIZER)
            .bucketItemSupplier(() -> ModItems.LIQUID_FERTILIZER_BUCKET)
            .sourceTexture(id("block/liquid_fertilizer_still"))
            .flowingTexture(id("block/liquid_fertilizer_flow"))
            .color(0xFFFFFFFF)
            .density(1000)
            .viscosity(1000)
            .luminosity(0)
            .slopeFindDistance(2)
            .dropOff(2)
            .tickDelay(20)
            .convertToSource(false);

    public static final RegistrySupplier<FlowingFluid> FERTILIZER = FLUIDS.register("liquid_fertilizer",
            () -> new ArchitecturyFlowingFluid.Source(FERTILIZER_ATTRIBUTES));
    public static final RegistrySupplier<FlowingFluid> FERTILIZER_FLOWING = FLUIDS.register("flowing_liquid_fertilizer",
            () -> new ArchitecturyFlowingFluid.Flowing(FERTILIZER_ATTRIBUTES));

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(Create_italian_industries.MOD_ID, path);
    }

    public static void register() {
        FLUIDS.register();
    }

    private ModFluids() {}
}
