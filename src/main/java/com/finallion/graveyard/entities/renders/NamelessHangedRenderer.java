package com.finallion.graveyard.entities.renders;

import com.finallion.graveyard.entities.NamelessHangedEntity;
import com.finallion.graveyard.entities.models.NamelessHangedModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NamelessHangedRenderer extends GeoEntityRenderer<NamelessHangedEntity> {

    public NamelessHangedRenderer(EntityRendererProvider.Context context) {
        super(context, new NamelessHangedModel());
        this.shadowRadius = 0.5F;
    }

    @Override
    public RenderType getRenderType(NamelessHangedEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }


}
