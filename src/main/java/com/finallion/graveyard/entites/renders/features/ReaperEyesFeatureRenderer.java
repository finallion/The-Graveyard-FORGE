package com.finallion.graveyard.entites.renders.features;


import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entites.ReaperEntity;
import com.finallion.graveyard.entites.models.ReaperModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;


public class ReaperEyesFeatureRenderer extends GeoLayerRenderer<ReaperEntity> {
    private final RenderType TEXTURE = RenderType.eyes(new ResourceLocation("graveyard:textures/entity/reaper_eyes.png"));
    private final IGeoRenderer<ReaperEntity> renderer;

    public ReaperEyesFeatureRenderer(IGeoRenderer<ReaperEntity> entityRendererIn) {
        super(entityRendererIn);
        this.renderer = entityRendererIn;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, ReaperEntity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        IVertexBuilder vertexConsumer = bufferIn.getBuffer(TEXTURE);
        renderer.render(
                getEntityModel().getModel(new ResourceLocation(TheGraveyard.MOD_ID, "geo/reaper.geo.json")),
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


