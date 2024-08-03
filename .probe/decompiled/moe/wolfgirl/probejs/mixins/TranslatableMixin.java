package moe.wolfgirl.probejs.mixins;

import moe.wolfgirl.probejs.GlobalStates;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ TranslatableContents.class })
public abstract class TranslatableMixin {

    @Shadow
    @Final
    private String key;

    @Shadow
    @Final
    private String fallback;

    @Inject(method = { "<init>" }, at = { @At("RETURN") })
    private void init(CallbackInfo ci) {
        synchronized (GlobalStates.MIXIN_LANG_KEYS) {
            GlobalStates.MIXIN_LANG_KEYS.add(this.key);
            if (this.fallback != null) {
                GlobalStates.MIXIN_LANG_KEYS.add(this.fallback);
            }
        }
    }
}