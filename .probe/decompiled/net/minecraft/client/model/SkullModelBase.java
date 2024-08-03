package net.minecraft.client.model;

import net.minecraft.client.renderer.RenderType;

public abstract class SkullModelBase extends Model {

    public SkullModelBase() {
        super(RenderType::m_110473_);
    }

    public abstract void setupAnim(float var1, float var2, float var3);
}