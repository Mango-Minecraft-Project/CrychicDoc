package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelBananaSlug;
import com.github.alexthe666.alexsmobs.entity.EntityBananaSlug;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Pose;

public class RenderBananaSlug extends MobRenderer<EntityBananaSlug, ModelBananaSlug> {

    private static final ResourceLocation TEXTURE_0 = new ResourceLocation("alexsmobs:textures/entity/banana_slug/banana_slug_0.png");

    private static final ResourceLocation TEXTURE_1 = new ResourceLocation("alexsmobs:textures/entity/banana_slug/banana_slug_1.png");

    private static final ResourceLocation TEXTURE_2 = new ResourceLocation("alexsmobs:textures/entity/banana_slug/banana_slug_2.png");

    private static final ResourceLocation TEXTURE_3 = new ResourceLocation("alexsmobs:textures/entity/banana_slug/banana_slug_3.png");

    private static final ResourceLocation TEXTURE_SLIME = new ResourceLocation("alexsmobs:textures/entity/banana_slug/banana_slug_slime.png");

    public RenderBananaSlug(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelBananaSlug(), 0.2F);
        this.m_115326_(new RenderBananaSlug.LayerSlime());
    }

    protected void scale(EntityBananaSlug entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(0.9F, 0.9F, 0.9F);
    }

    private Direction rotate(Direction attachmentFacing) {
        return attachmentFacing.getAxis() == Direction.Axis.Y ? Direction.UP : attachmentFacing;
    }

    private void rotateForAngle(PoseStack matrixStackIn, Direction rotate, float f) {
        if (rotate.getAxis() != Direction.Axis.Y) {
            matrixStackIn.mulPose(Axis.XP.rotationDegrees(90.0F * f));
        }
        switch(rotate) {
            case DOWN:
                matrixStackIn.mulPose(Axis.ZP.rotationDegrees(180.0F * f));
            case UP:
            case SOUTH:
            default:
                break;
            case NORTH:
                matrixStackIn.mulPose(Axis.ZP.rotationDegrees(180.0F * f));
                break;
            case WEST:
                matrixStackIn.mulPose(Axis.ZP.rotationDegrees(90.0F * f));
                break;
            case EAST:
                matrixStackIn.mulPose(Axis.ZP.rotationDegrees(-90.0F * f));
        }
    }

    protected void setupRotations(EntityBananaSlug entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        if (entityLiving.m_20159_()) {
            super.m_7523_(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        } else {
            if (this.m_5936_(entityLiving)) {
                rotationYaw += (float) (Math.cos((double) entityLiving.f_19797_ * 3.25) * Math.PI * 0.4F);
            }
            float trans = entityLiving.m_6162_() ? 0.2F : 0.4F;
            Pose pose = entityLiving.m_20089_();
            if (pose != Pose.SLEEPING) {
                float progress = (entityLiving.prevAttachChangeProgress + (entityLiving.attachChangeProgress - entityLiving.prevAttachChangeProgress) * partialTicks) * 0.2F;
                float yawMul = 0.0F;
                if (entityLiving.prevAttachDir == entityLiving.getAttachmentFacing() && entityLiving.getAttachmentFacing().getAxis() == Direction.Axis.Y) {
                    yawMul = 1.0F;
                }
                matrixStackIn.mulPose(Axis.YP.rotationDegrees(180.0F - yawMul * rotationYaw));
                matrixStackIn.translate(0.0, (double) trans, 0.0);
                float prevProg = 1.0F - progress;
                this.rotateForAngle(matrixStackIn, this.rotate(entityLiving.prevAttachDir), prevProg);
                this.rotateForAngle(matrixStackIn, this.rotate(entityLiving.getAttachmentFacing()), progress);
                if (entityLiving.getAttachmentFacing() != Direction.DOWN) {
                    matrixStackIn.translate(0.0, (double) trans, 0.0);
                    if (entityLiving.m_20184_().y <= -0.001F) {
                        matrixStackIn.mulPose(Axis.YN.rotationDegrees(180.0F * progress));
                    }
                    matrixStackIn.translate(0.0, (double) (-trans), 0.0);
                }
                matrixStackIn.translate(0.0, (double) (-trans), 0.0);
            }
            if (entityLiving.f_20919_ > 0) {
                float f = ((float) entityLiving.f_20919_ + partialTicks - 1.0F) / 20.0F * 1.6F;
                f = Mth.sqrt(f);
                if (f > 1.0F) {
                    f = 1.0F;
                }
                matrixStackIn.mulPose(Axis.ZP.rotationDegrees(f * this.m_6441_(entityLiving)));
            } else if (entityLiving.m_21209_()) {
                matrixStackIn.mulPose(Axis.XP.rotationDegrees(-90.0F - entityLiving.m_146909_()));
                matrixStackIn.mulPose(Axis.YP.rotationDegrees(((float) entityLiving.f_19797_ + partialTicks) * -75.0F));
            } else if (pose != Pose.SLEEPING && entityLiving.m_8077_()) {
                String s = ChatFormatting.stripFormatting(entityLiving.m_7755_().getString());
                if ("Dinnerbone".equals(s) || "Grumm".equals(s)) {
                    matrixStackIn.translate(0.0, (double) (entityLiving.m_20206_() + 0.1F), 0.0);
                    matrixStackIn.mulPose(Axis.ZP.rotationDegrees(180.0F));
                }
            }
        }
    }

    public ResourceLocation getTextureLocation(EntityBananaSlug entity) {
        return switch(entity.getVariant()) {
            case 1 ->
                TEXTURE_1;
            case 2 ->
                TEXTURE_2;
            case 3 ->
                TEXTURE_3;
            default ->
                TEXTURE_0;
        };
    }

    class LayerSlime extends RenderLayer<EntityBananaSlug, ModelBananaSlug> {

        public LayerSlime() {
            super(RenderBananaSlug.this);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityBananaSlug entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            float alpha = entitylivingbaseIn.prevTrailVisability + (entitylivingbaseIn.trailVisability - entitylivingbaseIn.prevTrailVisability) * partialTicks;
            if (alpha > 0.0F) {
                VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.entityTranslucent(RenderBananaSlug.TEXTURE_SLIME));
                ((ModelBananaSlug) this.m_117386_()).renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.getOverlayCoords(entitylivingbaseIn, 0.0F), 1.0F, 1.0F, 1.0F, alpha);
            }
        }
    }
}