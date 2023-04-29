package com.finallion.graveyard.entities.models;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.NamelessHangedEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NamelessHangedModel extends AnimatedGeoModel<NamelessHangedEntity> {

    @Override
    public ResourceLocation getModelResource(NamelessHangedEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "geo/nameless_hanged.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NamelessHangedEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID,"textures/entity/nameless_hanged.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NamelessHangedEntity animatable) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "animations/nameless_hanged/nameless_hanged.animation.json");
    }
}
