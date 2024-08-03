package com.github.alexmodguy.alexscaves.mixin;

import com.github.alexmodguy.alexscaves.server.level.biome.ACBiomeRegistry;
import com.github.alexmodguy.alexscaves.server.level.feature.FeaturePositionValidator;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ LakeFeature.class })
public class LakeFeatureMixin {

    @Inject(method = { "Lnet/minecraft/world/level/levelgen/feature/LakeFeature;place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z" }, remap = true, cancellable = true, at = { @At("HEAD") })
    private void ac_place(FeaturePlaceContext context, CallbackInfoReturnable<Boolean> cir) {
        if (FeaturePositionValidator.isBiome(context, ACBiomeRegistry.ABYSSAL_CHASM)) {
            cir.cancel();
        }
    }
}