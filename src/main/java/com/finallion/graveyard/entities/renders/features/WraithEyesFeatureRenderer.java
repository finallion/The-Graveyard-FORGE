package com.finallion.graveyard.entities.renders.features;


import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.WraithEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;


public class WraithEyesFeatureRenderer extends GeoLayerRenderer<WraithEntity> {
    private final RenderType TEXTURE = RenderType.eyes(new ResourceLocation("graveyard:textures/entity/wraith_eyes.png"));
    private final IGeoRenderer<WraithEntity> renderer;

    public WraithEyesFeatureRenderer(IGeoRenderer<WraithEntity> entityRendererIn) {
        super(entityRendererIn);
        this.renderer = entityRendererIn;
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, WraithEntity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer vertexConsumer = bufferIn.getBuffer(TEXTURE);
        renderer.render(
                getEntityModel().getModel(new ResourceLocation(TheGraveyard.MOD_ID, "geo/wraith.geo.json")),
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

