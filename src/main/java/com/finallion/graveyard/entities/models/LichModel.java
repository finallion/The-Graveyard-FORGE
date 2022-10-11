package com.finallion.graveyard.entities.models;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.LichEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LichModel extends AnimatedGeoModel<LichEntity> {
    private ResourceLocation texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/lich_texture.png");

    @Override
    public ResourceLocation getModelResource(LichEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "geo/lich.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LichEntity object) {
        return texture;
    }

    @Override
    public ResourceLocation getAnimationResource(LichEntity animatable) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "animations/lich/lich.animation.json");
    }

}
