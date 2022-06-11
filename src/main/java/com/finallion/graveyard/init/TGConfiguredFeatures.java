package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.world.features.surfaceFeatures.GraveFeature;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;


public class TGConfiguredFeatures {

    public static ConfiguredFeature<?, ?> GRAVESTONE_CONFIG_FEATURE = new ConfiguredFeature<>(TGFeatures.GRAVESTONE_FEATURE.get(), FeatureConfiguration.NONE);
    public static PlacedFeature GRAVESTONE_PLACED_FEATURE = new PlacedFeature(Holder.direct(GRAVESTONE_CONFIG_FEATURE), List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(1)));

    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_FEATURE;
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "gravestone_feature"), GRAVESTONE_CONFIG_FEATURE);
    }

    public static void registerPlacedFeatures() {
        Registry<PlacedFeature> registry = BuiltinRegistries.PLACED_FEATURE;

        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "gravestone_feature"), GRAVESTONE_PLACED_FEATURE);
    }
}
