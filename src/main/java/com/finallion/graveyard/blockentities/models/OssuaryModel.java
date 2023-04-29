package com.finallion.graveyard.blockentities.models;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blockentities.OssuaryBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class OssuaryModel extends AnimatedGeoModel<OssuaryBlockEntity> {
    @Override
    public ResourceLocation getAnimationFileLocation(OssuaryBlockEntity entity) {
        return new ResourceLocation(TheGraveyard.MOD_ID,"animations/ossuary/ossuary.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(OssuaryBlockEntity animatable) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "geo/ossuary.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(OssuaryBlockEntity entity) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "textures/block/ossuary.png");
    }




}
