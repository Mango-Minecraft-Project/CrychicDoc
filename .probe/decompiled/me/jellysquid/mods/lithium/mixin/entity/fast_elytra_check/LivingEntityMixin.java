package me.jellysquid.mods.lithium.mixin.entity.fast_elytra_check;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin({ LivingEntity.class })
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(method = { "tickFallFlying()V" }, at = { @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/entity/LivingEntity;getFlag(I)Z", shift = Shift.AFTER) }, locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void skipStopFlying(CallbackInfo ci, boolean isFlying) {
        if (!isFlying) {
            ci.cancel();
        }
    }
}