package io.github.lightman314.lightmanscurrency.common.blocks;

import com.google.common.collect.ImmutableList;
import io.github.lightman314.lightmanscurrency.LCConfig;
import io.github.lightman314.lightmanscurrency.LCText;
import io.github.lightman314.lightmanscurrency.LightmansCurrency;
import io.github.lightman314.lightmanscurrency.api.misc.EasyText;
import io.github.lightman314.lightmanscurrency.api.misc.blocks.IEasyEntityBlock;
import io.github.lightman314.lightmanscurrency.api.misc.blocks.IOwnableBlock;
import io.github.lightman314.lightmanscurrency.api.misc.blocks.RotatableBlock;
import io.github.lightman314.lightmanscurrency.common.blockentity.TaxBlockEntity;
import io.github.lightman314.lightmanscurrency.common.core.ModBlockEntities;
import io.github.lightman314.lightmanscurrency.common.items.TooltipItem;
import io.github.lightman314.lightmanscurrency.common.menus.validation.types.BlockEntityValidator;
import io.github.lightman314.lightmanscurrency.common.taxes.TaxEntry;
import io.github.lightman314.lightmanscurrency.util.InventoryUtil;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class TaxCollectorBlock extends RotatableBlock implements IOwnableBlock, IEasyEntityBlock {

    public TaxCollectorBlock(BlockBehaviour.Properties properties) {
        super(properties.pushReaction(PushReaction.BLOCK));
    }

    @Override
    public void setPlacedBy(@Nonnull Level level, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nullable LivingEntity entity, @Nonnull ItemStack stack) {
        if (!level.isClientSide && level.getBlockEntity(pos) instanceof TaxBlockEntity taxBlock && entity instanceof Player player) {
            taxBlock.initialize(player);
        }
    }

    @Override
    public void playerWillDestroy(@Nonnull Level level, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull Player player) {
        if (level.getBlockEntity(pos) instanceof TaxBlockEntity be) {
            TaxEntry entry = be.getTaxEntry();
            if (entry != null) {
                if (!entry.getOwner().isAdmin(player)) {
                    return;
                }
                be.flagAsValidBreak();
                InventoryUtil.dumpContents(level, pos, be.getContents(!player.isCreative()));
            }
        }
        super.m_5707_(level, pos, state, player);
    }

    @Override
    public void onRemove(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, @Nonnull BlockState newState, boolean flag) {
        if (level.getBlockEntity(pos) instanceof TaxBlockEntity taxBlock) {
            taxBlock.onRemove();
        }
        super.m_6810_(state, level, pos, newState, flag);
    }

    @Nonnull
    @Override
    public InteractionResult use(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        if (!level.isClientSide && level.getBlockEntity(pos) instanceof TaxBlockEntity taxBlock) {
            TaxEntry entry = taxBlock.getTaxEntry();
            if (entry == null) {
                LightmansCurrency.LogWarning("Tax Entry for block at " + pos.m_123341_() + "," + pos.m_123342_() + "," + pos.m_123343_() + " had to be re-initialized on interaction.");
                EasyText.sendMessage(player, LCText.MESSAGE_TAX_COLLECTOR_WARNING_MISSING_DATA.getWithStyle(ChatFormatting.RED));
                taxBlock.initialize(player);
                entry = taxBlock.getTaxEntry();
            }
            if (entry != null) {
                if (entry.canAccess(player)) {
                    entry.openMenu(player, BlockEntityValidator.of(taxBlock));
                } else {
                    EasyText.sendMessage(player, LCText.MESSAGE_TAX_COLLECTOR_WARNING_NO_ACCESS.get());
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean canBreak(@Nonnull Player player, @Nonnull LevelAccessor level, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        if (level.m_7702_(pos) instanceof TaxBlockEntity taxBlock) {
            TaxEntry entry = taxBlock.getTaxEntry();
            if (entry != null) {
                return entry.getOwner().isAdmin(player);
            }
        }
        return true;
    }

    @Nonnull
    @Override
    public Collection<BlockEntityType<?>> getAllowedTypes() {
        return ImmutableList.of(ModBlockEntities.TAX_BLOCK.get());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new TaxBlockEntity(pos, state);
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable BlockGetter level, @Nonnull List<Component> tooltips, @Nonnull TooltipFlag flag) {
        TooltipItem.addTooltip(tooltips, LCText.TOOLTIP_TAX_COLLECTOR.asTooltip());
        if (LCConfig.SERVER.taxCollectorAdminOnly.get()) {
            tooltips.add(LCText.TOOLTIP_TAX_COLLECTOR_ADMIN.get().withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
        }
    }
}