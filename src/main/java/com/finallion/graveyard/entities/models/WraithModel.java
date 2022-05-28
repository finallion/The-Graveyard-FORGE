package com.finallion.graveyard.entities.models;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.WraithEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class WraithModel extends AnimatedGeoModel<WraithEntity> {

    @Override
    public ResourceLocation getModelLocation(WraithEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "geo/wraith.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WraithEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/wraith_opaque.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WraithEntity animatable) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "animations/wraith/wraith.animation.json");
    }
}
