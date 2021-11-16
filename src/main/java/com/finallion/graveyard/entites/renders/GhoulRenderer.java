package com.finallion.graveyard.entites.renders;


import com.finallion.graveyard.entites.BaseGhoulEntity;
import com.finallion.graveyard.entites.models.BaseGhoulModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GhoulRenderer extends GeoEntityRenderer<BaseGhoulEntity> {


    protected GhoulRenderer(EntityRendererManager renderManager, AnimatedGeoModel<BaseGhoulEntity> modelProvider) {
        super(renderManager, modelProvider);
        this.shadowRadius = 0.7F;
    }


    // stops the vanilla death animation
    @Override
    protected float getDeathMaxRotation(BaseGhoulEntity entityLivingBaseIn) {
        return 0.0F;
    }

}
