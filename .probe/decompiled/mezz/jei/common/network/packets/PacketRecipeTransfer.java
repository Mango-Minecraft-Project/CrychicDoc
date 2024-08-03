package mezz.jei.common.network.packets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import mezz.jei.common.network.IPacketId;
import mezz.jei.common.network.PacketIdServer;
import mezz.jei.common.network.ServerPacketContext;
import mezz.jei.common.network.ServerPacketData;
import mezz.jei.common.transfer.BasicRecipeTransferHandlerServer;
import mezz.jei.common.transfer.TransferOperation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;

public class PacketRecipeTransfer extends PacketJei {

    public final Collection<TransferOperation> transferOperations;

    public final Collection<Slot> craftingSlots;

    public final Collection<Slot> inventorySlots;

    private final boolean maxTransfer;

    private final boolean requireCompleteSets;

    public PacketRecipeTransfer(Collection<TransferOperation> transferOperations, Collection<Slot> craftingSlots, Collection<Slot> inventorySlots, boolean maxTransfer, boolean requireCompleteSets) {
        this.transferOperations = transferOperations;
        this.craftingSlots = craftingSlots;
        this.inventorySlots = inventorySlots;
        this.maxTransfer = maxTransfer;
        this.requireCompleteSets = requireCompleteSets;
    }

    @Override
    public IPacketId getPacketId() {
        return PacketIdServer.RECIPE_TRANSFER;
    }

    @Override
    public void writePacketData(FriendlyByteBuf buf) {
        buf.writeVarInt(this.transferOperations.size());
        for (TransferOperation operation : this.transferOperations) {
            operation.writePacketData(buf);
        }
        buf.writeVarInt(this.craftingSlots.size());
        for (Slot craftingSlot : this.craftingSlots) {
            buf.writeVarInt(craftingSlot.index);
        }
        buf.writeVarInt(this.inventorySlots.size());
        for (Slot inventorySlot : this.inventorySlots) {
            buf.writeVarInt(inventorySlot.index);
        }
        buf.writeBoolean(this.maxTransfer);
        buf.writeBoolean(this.requireCompleteSets);
    }

    public static CompletableFuture<Void> readPacketData(ServerPacketData data) {
        ServerPacketContext context = data.context();
        ServerPlayer player = context.player();
        FriendlyByteBuf buf = data.buf();
        AbstractContainerMenu container = player.f_36096_;
        int transferOperationsSize = buf.readVarInt();
        List<TransferOperation> transferOperations = new ArrayList();
        for (int i = 0; i < transferOperationsSize; i++) {
            TransferOperation transferOperation = TransferOperation.readPacketData(buf, container);
            transferOperations.add(transferOperation);
        }
        int craftingSlotsSize = buf.readVarInt();
        List<Slot> craftingSlots = new ArrayList();
        for (int i = 0; i < craftingSlotsSize; i++) {
            int slotIndex = buf.readVarInt();
            Slot slot = container.getSlot(slotIndex);
            craftingSlots.add(slot);
        }
        int inventorySlotsSize = buf.readVarInt();
        List<Slot> inventorySlots = new ArrayList();
        for (int i = 0; i < inventorySlotsSize; i++) {
            int slotIndex = buf.readVarInt();
            Slot slot = container.getSlot(slotIndex);
            inventorySlots.add(slot);
        }
        boolean maxTransfer = buf.readBoolean();
        boolean requireCompleteSets = buf.readBoolean();
        MinecraftServer server = player.server;
        return server.m_18707_(() -> BasicRecipeTransferHandlerServer.setItems(player, transferOperations, craftingSlots, inventorySlots, maxTransfer, requireCompleteSets));
    }
}