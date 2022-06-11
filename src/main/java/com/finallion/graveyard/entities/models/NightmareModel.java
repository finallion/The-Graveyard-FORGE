package com.finallion.graveyard.entities.models;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.NightmareEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class NightmareModel extends AnimatedGeoModel<NightmareEntity> {

    @Override
    public ResourceLocation getModelResource(NightmareEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "geo/nightmare.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NightmareEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/nightmare.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NightmareEntity animatable) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "animations/nightmare/nightmare.animation.json");
    }
}
