package com.finallion.graveyard.blockentities.render;

import com.finallion.graveyard.blockentities.OssuaryBlockEntity;
import com.finallion.graveyard.blockentities.models.OssuaryModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class OssuaryBlockEntityRenderer extends GeoBlockRenderer<OssuaryBlockEntity> {

    public OssuaryBlockEntityRenderer() {
        super(new OssuaryModel());
    }

    @Override
    public RenderType getRenderType(OssuaryBlockEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutout(getTextureLocation(animatable));
    }


}
