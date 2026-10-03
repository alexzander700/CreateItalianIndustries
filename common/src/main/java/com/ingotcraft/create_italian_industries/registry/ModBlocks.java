package com.ingotcraft.create_italian_industries.registry;

import com.ingotcraft.create_italian_industries.Create_italian_industries;
import dev.architectury.core.block.ArchitecturyLiquidBlock;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public final class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Create_italian_industries.MOD_ID, Registries.BLOCK);

    // The in-world blocks of the fluids. They copy water's properties, so they never damage anything.

    public static final RegistrySupplier<LiquidBlock> TOMATO_SAUCE = BLOCKS.register("tomato_sauce", () ->
            new ArchitecturyLiquidBlock(ModFluids.SOURCE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).mapColor(MapColor.COLOR_RED)));

    public static final RegistrySupplier<LiquidBlock> WHEY = BLOCKS.register("whey", () ->
            new ArchitecturyLiquidBlock(ModFluids.WHEY,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).mapColor(MapColor.SAND)));

    public static final RegistrySupplier<LiquidBlock> LIQUID_FERTILIZER = BLOCKS.register("liquid_fertilizer", () ->
            new ArchitecturyLiquidBlock(ModFluids.FERTILIZER,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).mapColor(MapColor.COLOR_LIGHT_GREEN)));

    public static void register() {
        BLOCKS.register();
    }

    private ModBlocks() {}
}
