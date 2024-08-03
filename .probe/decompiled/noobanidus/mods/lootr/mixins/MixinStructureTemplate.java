package noobanidus.mods.lootr.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import noobanidus.mods.lootr.api.LootrAPI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ StructureTemplate.class })
public class MixinStructureTemplate {

    @Inject(method = { "fillFromWorld" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/BlockEntity;saveWithId()Lnet/minecraft/nbt/CompoundTag;", shift = Shift.BEFORE) })
    private void LootrInjectStructureSavingStart(Level level0, BlockPos blockPos1, Vec3i vecI2, boolean boolean3, Block block4, CallbackInfo ci) {
        LootrAPI.shouldDiscardIdAndOpeners = true;
    }

    @Inject(method = { "fillFromWorld" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/BlockEntity;saveWithId()Lnet/minecraft/nbt/CompoundTag;", shift = Shift.AFTER) })
    private void LootrInjectStructureSavingStop(Level level0, BlockPos blockPos1, Vec3i vecI2, boolean boolean3, Block block4, CallbackInfo ci) {
        LootrAPI.shouldDiscardIdAndOpeners = false;
    }
}