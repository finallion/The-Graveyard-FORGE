package com.finallion.graveyard.entites.renders;


import com.finallion.graveyard.entites.BaseGhoulEntity;
import com.finallion.graveyard.entites.ReaperEntity;
import com.finallion.graveyard.entites.models.ReaperModel;
import com.finallion.graveyard.entites.renders.features.ReaperEyesFeatureRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class ReaperRenderer extends GeoEntityRenderer<ReaperEntity> {

    public ReaperRenderer(EntityRendererManager renderManager) {
        super(renderManager, new ReaperModel());
        this.addLayer(new ReaperEyesFeatureRenderer(this));
        this.shadowRadius = 0.7F;
    }

    // stops the vanilla death animation
    @Override
    protected float getDeathMaxRotation(ReaperEntity entityLivingBaseIn) {
        return 0.0F;
    }

}

