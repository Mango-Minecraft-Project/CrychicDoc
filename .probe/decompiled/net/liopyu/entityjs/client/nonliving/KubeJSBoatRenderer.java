package net.liopyu.entityjs.client.nonliving;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import javax.annotation.Nullable;
import net.liopyu.entityjs.builders.nonliving.vanilla.BoatEntityBuilder;
import net.liopyu.entityjs.client.nonliving.model.BoatEntityModel;
import net.liopyu.entityjs.entities.nonliving.entityjs.IAnimatableJSNL;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import org.joml.Quaternionf;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class KubeJSBoatRenderer<T extends Boat & IAnimatableJSNL> extends GeoEntityRenderer<T> {

    private final BoatEntityBuilder<T> builder;

    public KubeJSBoatRenderer(EntityRendererProvider.Context pContext, BoatEntityBuilder<?> builder) {
        super(pContext, new BoatEntityModel<>(builder));
        this.builder = (BoatEntityBuilder<T>) builder;
        this.f_114477_ = this.getShadowRadius();
    }

    private float getShadowRadius() {
        return this.builder.setShadowRadius;
    }

    public ResourceLocation getTextureLocation(T entity) {
        return (ResourceLocation) this.builder.textureResource.apply(entity);
    }

    public RenderType getRenderType(T animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return switch(animatable.getBuilder().renderType) {
            case SOLID ->
                RenderType.entitySolid(texture);
            case CUTOUT ->
                RenderType.entityCutout(texture);
            case TRANSLUCENT ->
                RenderType.entityTranslucent(texture);
        };
    }

    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.0F, 0.375F, 0.0F);
        float entityYaw = Mth.lerp(pPartialTicks, pEntity.f_19859_, pEntity.m_146908_());
        pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));
        pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        float f = (float) pEntity.getHurtTime() - pPartialTicks;
        float f1 = pEntity.getDamage() - pPartialTicks;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }
        if (f > 0.0F) {
            pPoseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float) pEntity.getHurtDir()));
        }
        float f2 = pEntity.getBubbleAngle(pPartialTicks);
        if (!Mth.equal(f2, 0.0F)) {
            pPoseStack.mulPose(new Quaternionf().setAngleAxis(pEntity.getBubbleAngle(pPartialTicks) * (float) (Math.PI / 180.0), 1.0F, 0.0F, 1.0F));
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
        pPoseStack.popPose();
    }
}