package com.github.alexmodguy.alexscaves.mixin;

import com.github.alexmodguy.alexscaves.server.potion.ACEffectRegistry;
import java.util.Collection;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ PotionUtils.class })
public class PotionUtilsMixin {

    @Inject(method = { "Lnet/minecraft/world/item/alchemy/PotionUtils;getColor(Ljava/util/Collection;)I" }, remap = true, cancellable = true, at = { @At("HEAD") })
    private static void ac_getColor(Collection<MobEffectInstance> collection, CallbackInfoReturnable<Integer> cir) {
        if (collection.stream().anyMatch(mobEffectInstance -> mobEffectInstance.getEffect() == ACEffectRegistry.IRRADIATED.get() && mobEffectInstance.getAmplifier() >= 4)) {
            cir.setReturnValue(65535);
        }
    }
}