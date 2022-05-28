package com.finallion.graveyard.entities.renders;

import com.finallion.graveyard.entities.WraithEntity;
import com.finallion.graveyard.entities.models.WraithModel;
import com.finallion.graveyard.entities.renders.features.WraithEyesFeatureRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WraithRenderer extends GeoEntityRenderer<WraithEntity> {

    public WraithRenderer(EntityRendererProvider.Context context) {
        super(context, new WraithModel());
        this.addLayer(new WraithEyesFeatureRenderer(this));
        this.shadowRadius = 0.3F;
    }

    // stops the vanilla death animation
    @Override
    protected float getDeathMaxRotation(WraithEntity entityLivingBaseIn) {
        return 0.0F;
    }

    @Override
    public RenderType getRenderType(WraithEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

}



