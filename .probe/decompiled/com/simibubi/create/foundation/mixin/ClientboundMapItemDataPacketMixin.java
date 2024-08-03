package com.simibubi.create.foundation.mixin;

import com.simibubi.create.content.trains.station.StationMarker;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundMapItemDataPacket;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = { ClientboundMapItemDataPacket.class }, priority = 426)
public class ClientboundMapItemDataPacketMixin {

    @Shadow
    @Final
    private List<MapDecoration> decorations;

    @Unique
    private int[] create$stationIndices;

    @Inject(method = { "<init>(IBZLjava/util/Collection;Lnet/minecraft/world/level/saveddata/maps/MapItemSavedData$MapPatch;)V" }, at = { @At("RETURN") })
    private void create$onInit(int mapId, byte scale, boolean locked, @Nullable Collection<MapDecoration> decorations, @Nullable MapItemSavedData.MapPatch colorPatch, CallbackInfo ci) {
        this.create$stationIndices = create$getStationIndices(this.decorations);
    }

    @Unique
    private static int[] create$getStationIndices(List<MapDecoration> decorations) {
        if (decorations == null) {
            return new int[0];
        } else {
            IntList indices = new IntArrayList();
            for (int i = 0; i < decorations.size(); i++) {
                MapDecoration decoration = (MapDecoration) decorations.get(i);
                if (decoration instanceof StationMarker.Decoration) {
                    indices.add(i);
                }
            }
            return indices.toIntArray();
        }
    }

    @Inject(method = { "<init>(Lnet/minecraft/network/FriendlyByteBuf;)V" }, at = { @At("RETURN") })
    private void create$onInit(FriendlyByteBuf buf, CallbackInfo ci) {
        this.create$stationIndices = buf.readVarIntArray();
        if (this.decorations != null) {
            for (int i : this.create$stationIndices) {
                if (i >= 0 && i < this.decorations.size()) {
                    MapDecoration decoration = (MapDecoration) this.decorations.get(i);
                    this.decorations.set(i, StationMarker.Decoration.from(decoration));
                }
            }
        }
    }

    @Inject(method = { "write(Lnet/minecraft/network/FriendlyByteBuf;)V" }, at = { @At("RETURN") })
    private void create$onWrite(FriendlyByteBuf buf, CallbackInfo ci) {
        buf.writeVarIntArray(this.create$stationIndices);
    }
}