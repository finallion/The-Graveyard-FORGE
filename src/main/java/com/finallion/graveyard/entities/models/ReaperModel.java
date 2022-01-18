package com.finallion.graveyard.entities.models;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.ReaperEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ReaperModel extends AnimatedGeoModel<ReaperEntity> {

    @Override
    public ResourceLocation getModelLocation(ReaperEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "geo/reaper.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ReaperEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/reaper.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ReaperEntity animatable) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "animations/reaper/reaper.animation.json");
    }


}
