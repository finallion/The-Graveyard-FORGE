package com.finallion.graveyard.entities.models;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.GhoulingEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GhoulingModel extends AnimatedGeoModel<GhoulingEntity> {
    private ResourceLocation texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/white_ghouling.png");

    @Override
    public ResourceLocation getModelResource(GhoulingEntity object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "geo/ghouling.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GhoulingEntity object) {
        return texture;
    }

    @Override
    public ResourceLocation getAnimationResource(GhoulingEntity animatable) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "animations/ghouling/ghouling.animation.json");
    }

    @Override
    public void setCustomAnimations(GhoulingEntity entity, int uniqueID, AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        switch (entity.getVariant()) {
            //case 0 -> texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/ghoul_skin1.png");
            case 1 -> texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/white_ghouling.png");
            case 2 -> texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/black_ghouling.png");
            case 3 -> texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/red_ghouling.png");
            case 4 -> texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/cyan_ghouling.png");
            case 5 -> texture = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/purple_ghouling.png");
        };
    }

}
