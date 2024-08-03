package me.jellysquid.mods.sodium.mixin.features.render.world.sky;

import java.util.function.Function;
import me.jellysquid.mods.sodium.client.util.color.FastCubicSampler;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.CubicSampler;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({ ClientLevel.class })
public class ClientWorldMixin {

    @Redirect(method = { "getSkyColor" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/CubicSampler;gaussianSampleVec3(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/util/CubicSampler$Vec3Fetcher;)Lnet/minecraft/world/phys/Vec3;"))
    private Vec3 redirectSampleColor(Vec3 pos, CubicSampler.Vec3Fetcher rgbFetcher) {
        Level world = (Level) this;
        return FastCubicSampler.sampleColor(pos, (x, y, z) -> ((Biome) world.m_203495_(x, y, z).value()).getSkyColor(), Function.identity());
    }
}