package com.ingotcraft.create_italian_industries.client;

import com.ingotcraft.create_italian_industries.registry.ModFluids;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.material.FluidState;

/**
 * Client-only helpers for the thick red fog inside tomato sauce. Kept out of the mixin class because mixin
 * classes must never be referenced from outside themselves.
 */
public final class SauceFog {
    public static final float RED = 0.62F;
    public static final float GREEN = 0.07F;
    public static final float BLUE = 0.04F;

    /** True when the camera itself (the player's eyes) is below the surface of tomato sauce. */
    public static boolean cameraInSauce() {
        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;
        if (level == null) {
            return false;
        }
        Camera camera = mc.gameRenderer.getMainCamera();
        if (!camera.isInitialized()) {
            return false;
        }
        BlockPos pos = camera.getBlockPosition();
        FluidState state = level.getFluidState(pos);
        // isSame treats the source and flowing forms as one fluid. Same surface test vanilla uses for lava.
        return ModFluids.SOURCE.get().isSame(state.getType())
                && pos.getY() + state.getHeight(level, pos) > camera.getPosition().y;
    }

    /** Lava's fog distances: you can barely see past arm's length. */
    public static void applyFog() {
        RenderSystem.setShaderFogStart(0.25F);
        RenderSystem.setShaderFogEnd(1.0F);
        RenderSystem.setShaderFogShape(FogShape.CYLINDER);
    }

    private SauceFog() {}
}
