package com.finallion.graveyard.entities.models;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.ReaperEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ReaperModel extends GeoModel<ReaperEntity> {

    @Override
    public ResourceLocation getModelResource(ReaperEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "geo/reaper.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ReaperEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/reaper.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ReaperEntity animatable) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "animations/reaper/reaper.animation.json");
    }


}
