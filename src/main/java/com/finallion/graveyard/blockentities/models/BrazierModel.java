package com.finallion.graveyard.blockentities.models;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blockentities.BrazierBlockEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BrazierModel extends AnimatedGeoModel<BrazierBlockEntity> {
    @Override
    public ResourceLocation getAnimationFileLocation(BrazierBlockEntity entity) {
        return new ResourceLocation(TheGraveyard.MOD_ID,"animations/brazier.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(BrazierBlockEntity animatable) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "geo/brazier.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BrazierBlockEntity entity) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "textures/block/brazier.png");
    }
}
