package com.finallion.graveyard.entities.renders.features;


import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.RevenantEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

@OnlyIn(Dist.CLIENT)
public class RevenantEyesFeatureRenderer extends GeoLayerRenderer<RevenantEntity> {
    private final RenderType TEXTURE = RenderType.eyes(new ResourceLocation("graveyard:textures/entity/revenant_eyes.png"));
    private final IGeoRenderer<RevenantEntity> renderer;

    public RevenantEyesFeatureRenderer(IGeoRenderer<RevenantEntity> entityRendererIn) {
        super(entityRendererIn);
        this.renderer = entityRendererIn;
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, RevenantEntity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer vertexConsumer = bufferIn.getBuffer(TEXTURE);
        renderer.render(
                getEntityModel().getModel(new ResourceLocation(TheGraveyard.MOD_ID, "geo/revenant.geo.json")),
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

