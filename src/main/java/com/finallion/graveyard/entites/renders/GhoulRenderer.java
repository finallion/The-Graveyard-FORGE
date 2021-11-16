package com.finallion.graveyard.entites.renders;


import com.finallion.graveyard.entites.BaseGhoulEntity;
import com.finallion.graveyard.entites.models.BaseGhoulModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class GhoulRenderer extends GeoEntityRenderer<BaseGhoulEntity> {

    public GhoulRenderer(EntityRendererManager renderManager) {
        super(renderManager, new BaseGhoulModel());
        this.shadowRadius = 0.7F;
    }

    // stops the vanilla death animation
    @Override
    protected float getDeathMaxRotation(BaseGhoulEntity entityLivingBaseIn) {
        return 0.0F;
    }

}
