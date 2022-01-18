package com.finallion.graveyard.entities.renders;

import com.finallion.graveyard.entities.RevenantEntity;
import com.finallion.graveyard.entities.models.RevenantModel;
import com.finallion.graveyard.entities.renders.features.RevenantEyesFeatureRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RevenantRenderer extends GeoEntityRenderer<RevenantEntity> {

    public RevenantRenderer(EntityRendererProvider.Context context) {
        super(context, new RevenantModel());
        this.addLayer(new RevenantEyesFeatureRenderer(this));
        this.shadowRadius = 0.4F;
    }

    // stops the vanilla death animation
    @Override
    protected float getDeathMaxRotation(RevenantEntity entityLivingBaseIn) {
        return 0.0F;
    }




}
