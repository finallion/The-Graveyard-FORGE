package com.finallion.graveyard.entities.models;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.GhoulEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BaseGhoulModel extends GeoModel<GhoulEntity> {
    private ResourceLocation texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin1.png");

    @Override
    public ResourceLocation getModelResource(GhoulEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "geo/ghoul.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GhoulEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin" + object.getVariant() + ".png");
    }
    @Override
    public ResourceLocation getAnimationResource(GhoulEntity animatable) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "animations/ghoul/ghoul.animation.json");
    }
}
