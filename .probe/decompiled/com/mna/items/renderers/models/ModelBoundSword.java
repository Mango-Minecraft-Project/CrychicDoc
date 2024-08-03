package com.mna.items.renderers.models;

import com.mna.api.tools.RLoc;
import com.mna.items.sorcery.bound.ItemBoundSword;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelBoundSword extends GeoModel<ItemBoundSword> {

    private static final ResourceLocation anim = RLoc.create("animations/bound_swordaxe.animation.json");

    private static final ResourceLocation model = RLoc.create("geo/bound_sword.geo.json");

    private static final ResourceLocation texture = RLoc.create("textures/item/sorcery/bound_item.png");

    public ResourceLocation getAnimationResource(ItemBoundSword animatable) {
        return anim;
    }

    public ResourceLocation getModelResource(ItemBoundSword object) {
        return model;
    }

    public ResourceLocation getTextureResource(ItemBoundSword object) {
        return texture;
    }
}