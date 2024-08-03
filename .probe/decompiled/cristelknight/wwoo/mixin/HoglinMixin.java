package cristelknight.wwoo.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ Hoglin.class })
public abstract class HoglinMixin {

    @Shadow
    public abstract void setImmuneToZombification(boolean var1);

    @Inject(method = { "finalizeSpawn" }, at = { @At("HEAD") })
    public void modifyHoglin(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, SpawnGroupData spawnGroupData, CompoundTag compoundTag, CallbackInfoReturnable<SpawnGroupData> cir) {
        if (serverLevelAccessor.m_6042_().natural() && (mobSpawnType.equals(MobSpawnType.NATURAL) || mobSpawnType.equals(MobSpawnType.CHUNK_GENERATION))) {
            this.setImmuneToZombification(true);
        }
    }
}