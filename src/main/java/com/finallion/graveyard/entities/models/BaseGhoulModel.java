package com.finallion.graveyard.entities.models;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.GhoulEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BaseGhoulModel extends AnimatedGeoModel<GhoulEntity> {
    private ResourceLocation texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin1.png");

    @Override
    public ResourceLocation getModelResource(GhoulEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "geo/ghoul.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GhoulEntity object) {
        return texture;
    }

    @Override
    public ResourceLocation getAnimationResource(GhoulEntity animatable) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "animations/ghoul/ghoul.animation.json");
    }

    @Override
    public void setLivingAnimations(GhoulEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        switch (entity.getVariant()) {
            case 1: texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin2.png"); break;
            case 2: texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin3.png"); break;
            case 3: texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin4.png"); break;
            case 4: texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin5.png"); break;
            case 5: texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin6.png"); break;
            case 6: texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin7.png"); break;
            case 7: texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin8.png"); break;
            default: texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin1.png"); break;
        };
    }
}
