package com.finallion.graveyard.entities.renders;


import com.finallion.graveyard.entities.GhoulEntity;
import com.finallion.graveyard.entities.models.BaseGhoulModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;


public class GhoulRenderer extends GeoEntityRenderer<GhoulEntity> {

    public GhoulRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BaseGhoulModel());
        this.shadowRadius = 0.7F;
    }

    // stops the vanilla death animation
    @Override
    protected float getDeathMaxRotation(GhoulEntity entityLivingBaseIn) {
        return 0.0F;
    }

}
