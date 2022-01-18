package com.finallion.graveyard.entities.renders;


import com.finallion.graveyard.entities.ReaperEntity;
import com.finallion.graveyard.entities.models.ReaperModel;
import com.finallion.graveyard.entities.renders.features.ReaperEyesFeatureRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class ReaperRenderer extends GeoEntityRenderer<ReaperEntity> {

    public ReaperRenderer(EntityRendererProvider.Context renderManager) {
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

