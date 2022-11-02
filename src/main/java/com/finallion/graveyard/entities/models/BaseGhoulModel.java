package com.finallion.graveyard.entities.models;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.GhoulEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BaseGhoulModel extends AnimatedGeoModel<GhoulEntity> {
    private ResourceLocation texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin1.png");

    @Override
    public ResourceLocation getModelLocation(GhoulEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "geo/ghoul.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GhoulEntity object) {
        return texture;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GhoulEntity animatable) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "animations/ghoul/ghoul.animation.json");
    }

    @Override
    public void setCustomAnimations(GhoulEntity animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);

        switch (animatable.getVariant()) {
            case 1 -> texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin2.png");
            case 2 -> texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin3.png");
            case 3 -> texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin4.png");
            case 4 -> texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin5.png");
            case 5 -> texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin6.png");
            case 6 -> texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin7.png");
            case 7 -> texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin8.png");
            case 10 -> texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin9.png");
            default -> texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin1.png");
        };
    }
}
