package com.simibubi.create.content.contraptions.behaviour.dispenser;

import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.ItemHandlerHelper;

public class MovedDefaultDispenseItemBehaviour implements IMovedDispenseItemBehaviour {

    private static final MovedDefaultDispenseItemBehaviour DEFAULT_INSTANCE = new MovedDefaultDispenseItemBehaviour();

    public static void doDispense(Level p_82486_0_, ItemStack p_82486_1_, int p_82486_2_, Vec3 facing, BlockPos p_82486_4_, MovementContext context) {
        double d0 = (double) p_82486_4_.m_123341_() + facing.x + 0.5;
        double d1 = (double) p_82486_4_.m_123342_() + facing.y + 0.5;
        double d2 = (double) p_82486_4_.m_123343_() + facing.z + 0.5;
        if (Direction.getNearest(facing.x, facing.y, facing.z).getAxis() == Direction.Axis.Y) {
            d1 -= 0.125;
        } else {
            d1 -= 0.15625;
        }
        ItemEntity itementity = new ItemEntity(p_82486_0_, d0, d1, d2, p_82486_1_);
        double d3 = p_82486_0_.random.nextDouble() * 0.1 + 0.2;
        itementity.m_20334_(p_82486_0_.random.nextGaussian() * 0.0075F * (double) p_82486_2_ + facing.x() * d3 + context.motion.x, p_82486_0_.random.nextGaussian() * 0.0075F * (double) p_82486_2_ + facing.y() * d3 + context.motion.y, p_82486_0_.random.nextGaussian() * 0.0075F * (double) p_82486_2_ + facing.z() * d3 + context.motion.z);
        p_82486_0_.m_7967_(itementity);
    }

    @Override
    public ItemStack dispense(ItemStack itemStack, MovementContext context, BlockPos pos) {
        Vec3 facingVec = Vec3.atLowerCornerOf(((Direction) context.state.m_61143_(DispenserBlock.FACING)).getNormal());
        facingVec = (Vec3) context.rotation.apply(facingVec);
        facingVec.normalize();
        Direction closestToFacing = this.getClosestFacingDirection(facingVec);
        Container inventory = HopperBlockEntity.getContainerAt(context.world, pos.relative(closestToFacing));
        if (inventory == null) {
            this.playDispenseSound(context.world, pos);
            this.spawnDispenseParticles(context.world, pos, closestToFacing);
            return this.dispenseStack(itemStack, context, pos, facingVec);
        } else {
            if (HopperBlockEntity.addItem(null, inventory, itemStack.copy().split(1), closestToFacing.getOpposite()).isEmpty()) {
                itemStack.shrink(1);
            }
            return itemStack;
        }
    }

    protected ItemStack dispenseStack(ItemStack itemStack, MovementContext context, BlockPos pos, Vec3 facing) {
        ItemStack itemstack = itemStack.split(1);
        doDispense(context.world, itemstack, 6, facing, pos, context);
        return itemStack;
    }

    protected void playDispenseSound(LevelAccessor world, BlockPos pos) {
        world.levelEvent(1000, pos, 0);
    }

    protected void spawnDispenseParticles(LevelAccessor world, BlockPos pos, Vec3 facing) {
        this.spawnDispenseParticles(world, pos, this.getClosestFacingDirection(facing));
    }

    protected void spawnDispenseParticles(LevelAccessor world, BlockPos pos, Direction direction) {
        world.levelEvent(2000, pos, direction.get3DDataValue());
    }

    protected Direction getClosestFacingDirection(Vec3 exactFacing) {
        return Direction.getNearest(exactFacing.x, exactFacing.y, exactFacing.z);
    }

    protected ItemStack placeItemInInventory(ItemStack consumedFrom, ItemStack output, MovementContext context, BlockPos pos, Vec3 facing) {
        consumedFrom.shrink(1);
        ItemStack remainder = ItemHandlerHelper.insertItem(context.contraption.getSharedInventory(), output.copy(), false);
        if (!remainder.isEmpty()) {
            DEFAULT_INSTANCE.dispenseStack(output, context, pos, facing);
        }
        return consumedFrom;
    }
}