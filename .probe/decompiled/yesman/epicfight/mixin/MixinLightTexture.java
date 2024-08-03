package yesman.epicfight.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.client.ClientEngine;
import yesman.epicfight.client.gui.screen.overlay.OverlayManager;

@Mixin({ LightTexture.class })
public abstract class MixinLightTexture {

    @Inject(at = { @At("HEAD") }, method = { "updateLightTexture(F)V" }, cancellable = true)
    private void epicfight_head_updateLightTexture(CallbackInfo info) {
        OverlayManager overlayManager = ClientEngine.getInstance().renderEngine.getOverlayManager();
        if (overlayManager.isGammaChanged()) {
            Minecraft minecraft = Minecraft.getInstance();
            minecraft.options.gamma().set(overlayManager.getModifiedGamma(minecraft.options.gamma().get()));
        }
    }

    @Inject(at = { @At("TAIL") }, method = { "updateLightTexture(F)V" }, cancellable = true)
    private void epicfight_tail_updateLightTexture(CallbackInfo info) {
        OverlayManager overlayManager = ClientEngine.getInstance().renderEngine.getOverlayManager();
        if (overlayManager.isGammaChanged()) {
            Minecraft minecraft = Minecraft.getInstance();
            minecraft.options.gamma().set(overlayManager.getOriginalGamma());
        }
    }
}