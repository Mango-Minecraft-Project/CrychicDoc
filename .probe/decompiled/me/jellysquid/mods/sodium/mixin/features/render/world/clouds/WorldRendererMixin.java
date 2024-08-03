package me.jellysquid.mods.sodium.mixin.features.render.world.clouds;

import com.mojang.blaze3d.vertex.PoseStack;
import me.jellysquid.mods.sodium.client.render.immediate.CloudRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = { LevelRenderer.class }, priority = 990)
public class WorldRendererMixin {

    @Shadow
    @Nullable
    private ClientLevel level;

    @Shadow
    private int ticks;

    @Shadow
    @Final
    private Minecraft minecraft;

    @Unique
    private CloudRenderer cloudRenderer;

    @Inject(method = { "renderClouds" }, at = { @At(value = "INVOKE", target = "Ljava/lang/Float;isNaN(F)Z", ordinal = 0) }, cancellable = true)
    public void renderCloudsFast(PoseStack matrices, Matrix4f projectionMatrix, float tickDelta, double x, double y, double z, CallbackInfo ci) {
        if (this.cloudRenderer == null) {
            this.cloudRenderer = new CloudRenderer(this.minecraft.getResourceManager());
        }
        boolean renderFasterClouds = true;
        if (renderFasterClouds) {
            this.cloudRenderer.render(this.level, this.minecraft.player, matrices, projectionMatrix, (float) this.ticks, tickDelta, x, y, z);
            ci.cancel();
        }
    }

    @Inject(method = { "onResourceManagerReload(Lnet/minecraft/server/packs/resources/ResourceManager;)V" }, at = { @At("RETURN") })
    private void onReload(ResourceManager manager, CallbackInfo ci) {
        if (this.cloudRenderer != null) {
            this.cloudRenderer.reloadTextures(manager);
        }
    }

    @Inject(method = { "allChanged()V" }, at = { @At("RETURN") })
    private void onReload(CallbackInfo ci) {
        if (this.cloudRenderer != null) {
            this.cloudRenderer.destroy();
            this.cloudRenderer = null;
        }
    }

    @Inject(method = { "close" }, at = { @At("RETURN") }, remap = false)
    private void onClose(CallbackInfo ci) {
        if (this.cloudRenderer != null) {
            this.cloudRenderer.destroy();
            this.cloudRenderer = null;
        }
    }
}