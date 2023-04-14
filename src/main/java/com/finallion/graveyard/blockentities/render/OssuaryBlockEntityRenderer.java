package com.finallion.graveyard.blockentities.render;

import com.finallion.graveyard.blockentities.BrazierBlockEntity;
import com.finallion.graveyard.blockentities.OssuaryBlockEntity;
import com.finallion.graveyard.blockentities.models.BrazierModel;
import com.finallion.graveyard.blockentities.models.OssuaryModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import javax.annotation.Nullable;

public class OssuaryBlockEntityRenderer extends GeoBlockRenderer<OssuaryBlockEntity> {

    public OssuaryBlockEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new OssuaryModel());
    }

    @Override
    public RenderType getRenderType(OssuaryBlockEntity animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityCutout(getTextureLocation(animatable));
    }
}
