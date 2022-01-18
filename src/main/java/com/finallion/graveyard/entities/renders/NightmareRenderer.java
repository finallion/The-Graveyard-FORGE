package com.finallion.graveyard.entities.renders;


import com.finallion.graveyard.entities.NightmareEntity;
import com.finallion.graveyard.entities.models.NightmareModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NightmareRenderer extends GeoEntityRenderer<NightmareEntity> {

    public NightmareRenderer(EntityRendererProvider.Context context) {
        super(context, new NightmareModel());
        this.shadowRadius = 0.4F;
    }

    // stops the vanilla death animation
    @Override
    protected float getDeathMaxRotation(NightmareEntity entityLivingBaseIn) {
        return 0.0F;
    }




}
