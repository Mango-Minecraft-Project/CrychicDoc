package io.github.lightman314.lightmanscurrency.common.blockentity.trader;

import io.github.lightman314.lightmanscurrency.api.traders.TraderData;
import io.github.lightman314.lightmanscurrency.api.traders.blockentity.TraderBlockEntity;
import io.github.lightman314.lightmanscurrency.client.resourcepacks.data.item_trader.ItemPositionData;
import io.github.lightman314.lightmanscurrency.common.blocks.traderblocks.interfaces.IItemTraderBlock;
import io.github.lightman314.lightmanscurrency.common.core.ModBlockEntities;
import io.github.lightman314.lightmanscurrency.common.traders.item.ItemTraderData;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class ItemTraderBlockEntity extends TraderBlockEntity<ItemTraderData> {

    protected int tradeCount;

    protected boolean networkTrader;

    public ItemTraderBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.ITEM_TRADER.get(), pos, state, 1, false);
    }

    public ItemTraderBlockEntity(BlockPos pos, BlockState state, int tradeCount) {
        this(ModBlockEntities.ITEM_TRADER.get(), pos, state, tradeCount, false);
    }

    public ItemTraderBlockEntity(BlockPos pos, BlockState state, int tradeCount, boolean networkTrader) {
        this(ModBlockEntities.ITEM_TRADER.get(), pos, state, tradeCount, networkTrader);
    }

    protected ItemTraderBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        this(type, pos, state, 1, false);
    }

    protected ItemTraderBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int tradeCount) {
        this(type, pos, state, tradeCount, false);
    }

    protected ItemTraderBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int tradeCount, boolean networkTrader) {
        super(type, pos, state);
        this.tradeCount = tradeCount;
        this.networkTrader = networkTrader;
    }

    @Nullable
    protected ItemTraderData castOrNullify(@Nonnull TraderData trader) {
        return trader instanceof ItemTraderData ? (ItemTraderData) trader : null;
    }

    @Nonnull
    public ItemTraderData buildNewTrader() {
        ItemTraderData trader = new ItemTraderData(this.tradeCount, this.f_58857_, this.f_58858_);
        if (this.networkTrader) {
            trader.setAlwaysShowOnTerminal();
        }
        return trader;
    }

    @OnlyIn(Dist.CLIENT)
    @Nonnull
    public ItemPositionData GetRenderData() {
        return this.m_58900_().m_60734_() instanceof IItemTraderBlock traderBlock ? traderBlock.getItemPositionData() : ItemPositionData.EMPTY;
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putInt("TradeCount", this.tradeCount);
        compound.putBoolean("NetworkTrader", this.networkTrader);
    }

    @Override
    public void load(@NotNull CompoundTag compound) {
        super.load(compound);
        this.tradeCount = compound.getInt("TradeCount");
        this.networkTrader = compound.getBoolean("NetworkTrader");
    }
}