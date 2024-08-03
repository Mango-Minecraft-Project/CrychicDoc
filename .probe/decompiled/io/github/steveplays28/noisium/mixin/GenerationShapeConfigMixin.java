package io.github.steveplays28.noisium.mixin;

import net.minecraft.core.QuartPos;
import net.minecraft.world.level.levelgen.NoiseSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ NoiseSettings.class })
public abstract class GenerationShapeConfigMixin {

    @Unique
    private int noisium$horizontalCellBlockCount;

    @Unique
    private int noisium$verticalCellBlockCount;

    @Inject(method = { "<init>" }, at = { @At("TAIL") })
    private void noisium$createCacheHorizontalAndVerticalCellBlockCountInject(int minimumY, int height, int horizontalSize, int verticalSize, CallbackInfo ci) {
        this.noisium$horizontalCellBlockCount = QuartPos.toBlock(horizontalSize);
        this.noisium$verticalCellBlockCount = QuartPos.toBlock(verticalSize);
    }

    @Inject(method = { "horizontalCellBlockCount" }, at = { @At("HEAD") }, cancellable = true)
    private void noisium$horizontalCellBlockCountGetFromCacheInject(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(this.noisium$horizontalCellBlockCount);
    }

    @Inject(method = { "verticalCellBlockCount" }, at = { @At("HEAD") }, cancellable = true)
    private void noisium$verticalCellBlockCountGetFromCacheInject(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(this.noisium$verticalCellBlockCount);
    }
}