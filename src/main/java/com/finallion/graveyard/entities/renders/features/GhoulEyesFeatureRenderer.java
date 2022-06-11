package com.finallion.graveyard.entities.renders.features;


import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.GhoulEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;


public class GhoulEyesFeatureRenderer extends GeoLayerRenderer<GhoulEntity> {
    private RenderType TEXTURE;
    private final IGeoRenderer<GhoulEntity> renderer;

    public GhoulEyesFeatureRenderer(IGeoRenderer<GhoulEntity> entityRendererIn) {
        super(entityRendererIn);
        this.renderer = entityRendererIn;
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, GhoulEntity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        switch (entityLivingBaseIn.getVariant()) {
            case 1 -> TEXTURE = RenderType.eyes(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_eyes_skin2.png"));
            case 2 -> TEXTURE = RenderType.eyes(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_eyes_skin3.png"));
            case 3 -> TEXTURE = RenderType.eyes(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_eyes_skin4.png"));
            case 4 -> TEXTURE = RenderType.eyes(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_eyes_skin5.png"));
            case 5 -> TEXTURE = RenderType.eyes(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_eyes_skin6.png"));
            case 6 -> TEXTURE = RenderType.eyes(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_eyes_skin7.png"));
            case 7 -> TEXTURE = RenderType.eyes(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_eyes_skin8.png"));
            default -> TEXTURE = RenderType.eyes(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_eyes_skin1.png"));
        };

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

