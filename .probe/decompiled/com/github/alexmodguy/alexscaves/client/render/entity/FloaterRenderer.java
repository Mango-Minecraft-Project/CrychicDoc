package com.github.alexmodguy.alexscaves.client.render.entity;

import com.github.alexmodguy.alexscaves.client.model.FloaterModel;
import com.github.alexmodguy.alexscaves.client.render.ACRenderTypes;
import com.github.alexmodguy.alexscaves.server.entity.item.FloaterEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class FloaterRenderer extends EntityRenderer<FloaterEntity> {

    private static final FloaterModel FLOATER_MODEL = new FloaterModel();

    private static final ResourceLocation TEXTURE = new ResourceLocation("alexscaves", "textures/entity/floater.png");

    public FloaterRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }

    public void render(FloaterEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
        float ageInTicks = (float) entity.f_19797_ + partialTicks;
        poseStack.pushPose();
        poseStack.translate(0.0, 1.5, 0.0);
        poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
        FLOATER_MODEL.setupAnim(entity, 0.0F, 0.0F, ageInTicks, 0.0F, 0.0F);
        VertexConsumer textureVertexConsumer = bufferIn.getBuffer(ACRenderTypes.getGhostly(TEXTURE));
        FLOATER_MODEL.m_7695_(poseStack, textureVertexConsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
    }

    public ResourceLocation getTextureLocation(FloaterEntity entity) {
        return TEXTURE;
    }
}