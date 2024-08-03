package net.minecraft.client.resources.model;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.world.level.block.state.BlockState;

public class WeightedBakedModel implements BakedModel {

    private final int totalWeight;

    private final List<WeightedEntry.Wrapper<BakedModel>> list;

    private final BakedModel wrapped;

    public WeightedBakedModel(List<WeightedEntry.Wrapper<BakedModel>> listWeightedEntryWrapperBakedModel0) {
        this.list = listWeightedEntryWrapperBakedModel0;
        this.totalWeight = WeightedRandom.getTotalWeight(listWeightedEntryWrapperBakedModel0);
        this.wrapped = (BakedModel) ((WeightedEntry.Wrapper) listWeightedEntryWrapperBakedModel0.get(0)).getData();
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState blockState0, @Nullable Direction direction1, RandomSource randomSource2) {
        return (List<BakedQuad>) WeightedRandom.getWeightedItem(this.list, Math.abs((int) randomSource2.nextLong()) % this.totalWeight).map(p_235065_ -> ((BakedModel) p_235065_.getData()).getQuads(blockState0, direction1, randomSource2)).orElse(Collections.emptyList());
    }

    @Override
    public boolean useAmbientOcclusion() {
        return this.wrapped.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return this.wrapped.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return this.wrapped.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return this.wrapped.isCustomRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return this.wrapped.getParticleIcon();
    }

    @Override
    public ItemTransforms getTransforms() {
        return this.wrapped.getTransforms();
    }

    @Override
    public ItemOverrides getOverrides() {
        return this.wrapped.getOverrides();
    }

    public static class Builder {

        private final List<WeightedEntry.Wrapper<BakedModel>> list = Lists.newArrayList();

        public WeightedBakedModel.Builder add(@Nullable BakedModel bakedModel0, int int1) {
            if (bakedModel0 != null) {
                this.list.add(WeightedEntry.wrap(bakedModel0, int1));
            }
            return this;
        }

        @Nullable
        public BakedModel build() {
            if (this.list.isEmpty()) {
                return null;
            } else {
                return (BakedModel) (this.list.size() == 1 ? (BakedModel) ((WeightedEntry.Wrapper) this.list.get(0)).getData() : new WeightedBakedModel(this.list));
            }
        }
    }
}