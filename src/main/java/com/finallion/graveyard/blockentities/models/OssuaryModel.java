package com.finallion.graveyard.blockentities.models;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blockentities.OssuaryBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;


public class OssuaryModel extends GeoModel<OssuaryBlockEntity> {
    @Override
    public ResourceLocation getAnimationResource(OssuaryBlockEntity entity) {
        return new ResourceLocation(TheGraveyard.MOD_ID,"animations/ossuary/ossuary.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(OssuaryBlockEntity animatable) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "geo/ossuary.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OssuaryBlockEntity entity) {
        return new ResourceLocation(TheGraveyard.MOD_ID, "textures/block/ossuary.png");
    }




}
