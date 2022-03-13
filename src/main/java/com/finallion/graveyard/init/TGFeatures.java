package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.world.features.surfaceFeatures.GraveFeature;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;



public class TGFeatures {

    public TGFeatures() {}

    public static final DeferredRegister<Feature<?>> FEATURES  = DeferredRegister.create(ForgeRegistries.FEATURES, TheGraveyard.MOD_ID);
    // features
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> GRAVESTONE_FEATURE = FEATURES .register("gravestone_feature", () -> new GraveFeature(NoneFeatureConfiguration.CODEC));

}
