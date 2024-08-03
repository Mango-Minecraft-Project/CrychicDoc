package dev.xkmc.l2artifacts.content.mobeffects;

import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import dev.xkmc.l2library.base.effects.api.DelayedEntityRender;
import dev.xkmc.l2library.base.effects.api.InherentEffect;
import dev.xkmc.l2library.util.math.MathHelper;
import java.util.List;
import java.util.UUID;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class FleshOvergrowth extends InherentEffect implements IOverlayRenderEffect {

    public FleshOvergrowth(MobEffectCategory category, int color) {
        super(category, color);
        String str = "l2artifacts:flesh_overgrowth";
        UUID id = MathHelper.getUUIDFromString(str);
        this.m_19472_(Attributes.MAX_HEALTH, id.toString(), 0.2, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.m_19472_((Attribute) L2DamageTracker.REDUCTION.get(), id.toString(), 0.2, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return List.of();
    }

    @Override
    public DelayedEntityRender getIcon(LivingEntity entity, int lv) {
        return DelayedEntityRender.icon(entity, new ResourceLocation("l2artifacts", "textures/effect_overlay/flesh_" + (lv + 1) + ".png"));
    }
}