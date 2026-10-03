package com.ingotcraft.create_italian_industries.mixin;

import com.ingotcraft.create_italian_industries.client.SauceFog;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.FogRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Gives tomato sauce lava-style fog. Vanilla only picks fog by fluid TAG (water, lava, powder snow), and
 * tomato sauce is in none of those on purpose, so it would otherwise get no fog at all. Doing it here, in
 * common code, means it works the same on NeoForge and Fabric.
 *
 * Both handlers declare only the callback (no copy of the target's parameters) so they keep working if
 * Mojang changes those parameter lists.
 */
@Mixin(FogRenderer.class)
public abstract class FogRendererMixin {
    @Shadow
    private static float fogRed;
    @Shadow
    private static float fogGreen;
    @Shadow
    private static float fogBlue;

    /** Runs after vanilla has picked the sky/biome fog colour for this frame; swaps in sauce red. */
    @Inject(method = "setupColor", at = @At("RETURN"))
    private static void create_italian_industries$sauceColor(CallbackInfo ci) {
        if (SauceFog.cameraInSauce()) {
            fogRed = SauceFog.RED;
            fogGreen = SauceFog.GREEN;
            fogBlue = SauceFog.BLUE;
            RenderSystem.clearColor(fogRed, fogGreen, fogBlue, 0.0F);
        }
    }

    /** Runs after vanilla has set the fog distances; replaces them with lava's. */
    @Inject(method = "setupFog", at = @At("RETURN"))
    private static void create_italian_industries$sauceFog(CallbackInfo ci) {
        if (SauceFog.cameraInSauce()) {
            SauceFog.applyFog();
        }
    }
}
