package vectorwing.farmersdelight.common.block;

import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShepherdsPieBlock extends FeastBlock {

    protected static final VoxelShape PLATE_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 2.0, 15.0);

    protected static final VoxelShape PIE_SHAPE = Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(2.0, 2.0, 2.0, 14.0, 8.0, 14.0), BooleanOp.OR);

    public ShepherdsPieBlock(BlockBehaviour.Properties properties, Supplier<Item> servingItem, boolean hasLeftovers) {
        super(properties, servingItem, hasLeftovers);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.m_61143_(SERVINGS) == 0 ? PLATE_SHAPE : PIE_SHAPE;
    }
}