package com.finallion.graveyard.entities.renders;

import com.finallion.graveyard.entities.FallingCorpse;
import com.finallion.graveyard.entities.models.FallingCorpseModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FallingCorpseRenderer extends GeoEntityRenderer<FallingCorpse> {

    public FallingCorpseRenderer(EntityRendererProvider.Context context) {
        super(context, new FallingCorpseModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    protected void applyRotations(FallingCorpse entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        rotationYaw = entityLiving.getRotation();
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    public RenderType getRenderType(FallingCorpse animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutoutNoCull(texture);
    }

}
