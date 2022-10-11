package com.finallion.graveyard.entities.renders.features;


import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.LichEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;


public class LichEyesFeatureRenderer extends GeoLayerRenderer<LichEntity> {
    private RenderType TEXTURE;
    private final IGeoRenderer<LichEntity> renderer;

    public LichEyesFeatureRenderer(IGeoRenderer<LichEntity> entityRendererIn) {
        super(entityRendererIn);
        this.renderer = entityRendererIn;
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, LichEntity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        TEXTURE = RenderType.eyes(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/lich_eye_texture.png"));
        VertexConsumer vertexConsumer = bufferIn.getBuffer(TEXTURE);

        renderer.render(
                getEntityModel().getModel(getEntityModel().getModelResource(entityLivingBaseIn)),
                entityLivingBaseIn,
                partialTicks,
                TEXTURE,
                matrixStackIn,
                bufferIn,
                vertexConsumer,
                15728640,
                OverlayTexture.NO_OVERLAY,
                1.0F, 1.0F, 1.0F, 1.0F
        );


    }


}

