package com.github.alexmodguy.alexscaves.mixin.client;

import com.github.alexmodguy.alexscaves.client.ClientProxy;
import com.github.alexmodguy.alexscaves.client.sound.ACMusics;
import com.github.alexmodguy.alexscaves.server.entity.util.PossessesCamera;
import com.github.alexmodguy.alexscaves.server.misc.ACTagRegistry;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = { Minecraft.class }, priority = -100)
public abstract class MinecraftMixin {

    @Shadow
    @Nullable
    public LocalPlayer player;

    @Shadow
    @Final
    public Gui gui;

    @Shadow
    @Nullable
    public abstract Entity getCameraEntity();

    @Inject(method = { "Lnet/minecraft/client/Minecraft;startAttack()Z" }, at = { @At("HEAD") }, cancellable = true)
    private void ac_startAttack(CallbackInfoReturnable<Boolean> cir) {
        if (this.getCameraEntity() instanceof PossessesCamera) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = { "Lnet/minecraft/client/Minecraft;startUseItem()V" }, at = { @At("HEAD") }, cancellable = true)
    private void ac_startUseItem(CallbackInfo ci) {
        if (this.getCameraEntity() instanceof PossessesCamera) {
            ci.cancel();
        }
    }

    @Inject(method = { "Lnet/minecraft/client/Minecraft;getSituationalMusic()Lnet/minecraft/sounds/Music;" }, at = { @At("HEAD") }, cancellable = true)
    private void ac_getSituationalMusic(CallbackInfoReturnable<Music> cir) {
        if (this.player != null) {
            if (this.gui.getBossOverlay() != null && this.gui.getBossOverlay().shouldPlayMusic() && ClientProxy.primordialBossActive) {
                cir.setReturnValue(ACMusics.LUXTRUCTOSAURUS_BOSS_MUSIC);
            } else {
                Holder<Biome> holder = this.player.m_9236_().m_204166_(this.player.m_20183_());
                if (holder.is(ACTagRegistry.OVERRIDE_ALL_VANILLA_MUSIC_IN)) {
                    cir.setReturnValue((Music) holder.value().getBackgroundMusic().orElse(Musics.GAME));
                }
            }
        }
    }
}