package com.finallion.graveyard.entities.renders;

import com.finallion.graveyard.entities.projectiles.SkullEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class SkullEntityRenderer extends EntityRenderer<SkullEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/skeleton/skeleton.png");
    private final SkullModel model;

    public SkullEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new SkullModel(context.bakeLayer(ModelLayers.SKELETON_SKULL));
    }

    protected int getBlockLightLevel(SkullEntity p_114087_, BlockPos p_114088_) {
        return 15;
    }

    public void render(SkullEntity skullEntity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i) {
        matrixStack.pushPose();
        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        float h = Mth.rotlerp(skullEntity.yRotO, skullEntity.getYRot(), g);
        float j = Mth.lerp(g, skullEntity.xRotO, skullEntity.getXRot());
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(this.getTextureLocation(skullEntity)));
        this.model.setupAnim(0.0F, h, j);
        this.model.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.popPose();
        super.render(skullEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public ResourceLocation getTextureLocation(SkullEntity entity) {
        return TEXTURE;
    }

}

