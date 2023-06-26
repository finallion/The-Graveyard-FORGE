package com.finallion.graveyard.entities.models;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.WraithEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;


public class WraithModel extends GeoModel<WraithEntity> {

    @Override
    public ResourceLocation getModelResource(WraithEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "geo/wraith.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WraithEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/wraith_opaque.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WraithEntity animatable) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "animations/wraith/wraith.animation.json");
    }
}
