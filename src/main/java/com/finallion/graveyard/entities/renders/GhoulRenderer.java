package com.finallion.graveyard.entities.renders;


import com.finallion.graveyard.entities.BaseGhoulEntity;
import com.finallion.graveyard.entities.models.BaseGhoulModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class GhoulRenderer extends GeoEntityRenderer<BaseGhoulEntity> {

    public GhoulRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BaseGhoulModel());
        this.shadowRadius = 0.7F;
    }

    // stops the vanilla death animation
    @Override
    protected float getDeathMaxRotation(BaseGhoulEntity entityLivingBaseIn) {
        return 0.0F;
    }

}
