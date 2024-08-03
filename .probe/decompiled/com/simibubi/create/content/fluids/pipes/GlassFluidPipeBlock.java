package com.simibubi.create.content.fluids.pipes;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.schematics.requirement.ISpecialBlockItemRequirement;
import com.simibubi.create.content.schematics.requirement.ItemRequirement;
import com.simibubi.create.foundation.block.IBE;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GlassFluidPipeBlock extends AxisPipeBlock implements IBE<StraightPipeBlockEntity>, SimpleWaterloggedBlock, ISpecialBlockItemRequirement {

    public static final BooleanProperty ALT = BooleanProperty.create("alt");

    public GlassFluidPipeBlock(BlockBehaviour.Properties p_i48339_1_) {
        super(p_i48339_1_);
        this.m_49959_((BlockState) ((BlockState) this.m_49966_().m_61124_(ALT, false)).m_61124_(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_206840_1_) {
        super.m_7926_(p_206840_1_.add(ALT, BlockStateProperties.WATERLOGGED));
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        if (this.tryRemoveBracket(context)) {
            return InteractionResult.SUCCESS;
        } else {
            Level world = context.getLevel();
            BlockPos pos = context.getClickedPos();
            FluidTransportBehaviour.cacheFlows(world, pos);
            BlockState newState = (BlockState) this.toRegularPipe(world, pos, state).m_61124_(BlockStateProperties.WATERLOGGED, (Boolean) state.m_61143_(BlockStateProperties.WATERLOGGED));
            world.setBlock(pos, newState, 3);
            FluidTransportBehaviour.loadFlows(world, pos);
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState ifluidstate = context.m_43725_().getFluidState(context.getClickedPos());
        BlockState state = super.m_5573_(context);
        return state == null ? null : (BlockState) state.m_61124_(BlockStateProperties.WATERLOGGED, ifluidstate.getType() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.m_61143_(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
    }

    @Override
    public ItemRequirement getRequiredItems(BlockState state, BlockEntity be) {
        return ItemRequirement.of(AllBlocks.FLUID_PIPE.getDefaultState(), be);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter reader, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Override
    public Class<StraightPipeBlockEntity> getBlockEntityClass() {
        return StraightPipeBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends StraightPipeBlockEntity> getBlockEntityType() {
        return (BlockEntityType<? extends StraightPipeBlockEntity>) AllBlockEntityTypes.GLASS_FLUID_PIPE.get();
    }
}