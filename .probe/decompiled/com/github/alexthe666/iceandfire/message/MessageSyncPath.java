package com.github.alexthe666.iceandfire.message;

import com.github.alexthe666.iceandfire.client.render.pathfinding.PathfindingDebugRenderer;
import com.github.alexthe666.iceandfire.pathfinding.raycoms.MNode;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public class MessageSyncPath {

    public Set<MNode> lastDebugNodesVisited = new HashSet();

    public Set<MNode> lastDebugNodesNotVisited = new HashSet();

    public Set<MNode> lastDebugNodesPath = new HashSet();

    public MessageSyncPath(Set<MNode> lastDebugNodesVisited, Set<MNode> lastDebugNodesNotVisited, Set<MNode> lastDebugNodesPath) {
        this.lastDebugNodesVisited = lastDebugNodesVisited;
        this.lastDebugNodesNotVisited = lastDebugNodesNotVisited;
        this.lastDebugNodesPath = lastDebugNodesPath;
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(this.lastDebugNodesVisited.size());
        for (MNode MNode : this.lastDebugNodesVisited) {
            MNode.serializeToBuf(buf);
        }
        buf.writeInt(this.lastDebugNodesNotVisited.size());
        for (MNode MNode : this.lastDebugNodesNotVisited) {
            MNode.serializeToBuf(buf);
        }
        buf.writeInt(this.lastDebugNodesPath.size());
        for (MNode MNode : this.lastDebugNodesPath) {
            MNode.serializeToBuf(buf);
        }
    }

    public static MessageSyncPath read(FriendlyByteBuf buf) {
        int size = buf.readInt();
        Set<MNode> lastDebugNodesVisited = new HashSet();
        for (int i = 0; i < size; i++) {
            lastDebugNodesVisited.add(new MNode(buf));
        }
        size = buf.readInt();
        Set<MNode> lastDebugNodesNotVisited = new HashSet();
        for (int i = 0; i < size; i++) {
            lastDebugNodesNotVisited.add(new MNode(buf));
        }
        size = buf.readInt();
        Set<MNode> lastDebugNodesPath = new HashSet();
        for (int i = 0; i < size; i++) {
            lastDebugNodesPath.add(new MNode(buf));
        }
        return new MessageSyncPath(lastDebugNodesVisited, lastDebugNodesNotVisited, lastDebugNodesPath);
    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        ((NetworkEvent.Context) contextSupplier.get()).enqueueWork(() -> {
            ((NetworkEvent.Context) contextSupplier.get()).setPacketHandled(true);
            if (((NetworkEvent.Context) contextSupplier.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                PathfindingDebugRenderer.lastDebugNodesVisited = this.lastDebugNodesVisited;
                PathfindingDebugRenderer.lastDebugNodesNotVisited = this.lastDebugNodesNotVisited;
                PathfindingDebugRenderer.lastDebugNodesPath = this.lastDebugNodesPath;
            }
        });
        return true;
    }

    public LogicalSide getExecutionSide() {
        return LogicalSide.CLIENT;
    }
}