package com.finallion.graveyard.entities.models;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.FallingCorpse;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FallingCorpseModel extends AnimatedGeoModel<FallingCorpse> {

    @Override
    public ResourceLocation getModelLocation(FallingCorpse object) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "geo/falling_corpse.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FallingCorpse object) {
        return new ResourceLocation("textures/entity/skeleton/stray.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FallingCorpse animatable) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "animations/falling_corpse/falling_corpse.animation.json");
    }
}
