package com.simibubi.create.content.decoration.steamWhistle;

import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.SpecialBlockStateGen;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.ModelFile;

public class WhistleGenerator extends SpecialBlockStateGen {

    @Override
    protected int getXRotation(BlockState state) {
        return 0;
    }

    @Override
    protected int getYRotation(BlockState state) {
        return this.horizontalAngle((Direction) state.m_61143_(WhistleBlock.FACING));
    }

    @Override
    public <T extends Block> ModelFile getModel(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov, BlockState state) {
        String wall = state.m_61143_(WhistleBlock.WALL) ? "wall" : "floor";
        String size = ((WhistleBlock.WhistleSize) state.m_61143_(WhistleBlock.SIZE)).getSerializedName();
        boolean powered = (Boolean) state.m_61143_(WhistleBlock.POWERED);
        ModelFile model = AssetLookup.partialBaseModel(ctx, prov, size, wall);
        if (!powered) {
            return model;
        } else {
            ResourceLocation parentLocation = model.getLocation();
            return prov.models().withExistingParent(parentLocation.getPath() + "_powered", parentLocation).texture("2", Create.asResource("block/copper_redstone_plate_powered"));
        }
    }
}