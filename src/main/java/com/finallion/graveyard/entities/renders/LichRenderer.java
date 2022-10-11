package com.finallion.graveyard.entities.renders;

import com.finallion.graveyard.entities.LichEntity;
import com.finallion.graveyard.entities.models.LichModel;
import com.finallion.graveyard.entities.renders.features.LichEyesFeatureRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class LichRenderer extends GeoEntityRenderer<LichEntity> {

    public LichRenderer(EntityRendererProvider.Context context) {
        super(context, new LichModel());
        this.shadowRadius = 1.0F;
        this.addLayer(new LichEyesFeatureRenderer(this));
    }

    @Override
    public RenderType getRenderType(LichEntity animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityCutoutNoCull(textureLocation);
    }

    // stops the vanilla death animation
    @Override
    protected float getDeathMaxRotation(LichEntity entityLivingBaseIn) {
        return 0.0F;
    }

}
