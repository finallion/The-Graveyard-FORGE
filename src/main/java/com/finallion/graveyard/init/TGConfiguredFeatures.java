package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.world.features.surfaceFeatures.*;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGConfiguredFeatures {
    public static List<Feature<?>> features = new ArrayList<>();

    // other features
    public static final Feature<NoneFeatureConfiguration> GRAVESTONE_FEATURE = registerFeatures("gravestone_feature", new GraveFeature(NoneFeatureConfiguration.CODEC));

    // configured features
    public static final ConfiguredFeature<?, ?> GRAVESTONE_CONFIG = registerConfiguredFeatures("gravestone_config", GRAVESTONE_FEATURE.configured(new NoneFeatureConfiguration()));

    // placed features
    public static PlacedFeature GRAVESTONE_PLACED_FEATURE = registerPlacedFeatures("gravestone_placed_feature", GRAVESTONE_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(1)));


    public static <C extends FeatureConfiguration, F extends Feature<C>> F registerFeatures(String id, F feature) {
        ResourceLocation resourceLocation = new ResourceLocation(TheGraveyard.MOD_ID, id);

        feature.setRegistryName(resourceLocation);
        features.add(feature);
        return feature;
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>, CF extends ConfiguredFeature<FC, F>> CF registerConfiguredFeatures(String id, CF configuredFeature) {
        ResourceLocation resourceLocation = new ResourceLocation(TheGraveyard.MOD_ID, id);

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, resourceLocation, configuredFeature);
        return configuredFeature;
    }

    public static <PF extends PlacedFeature> PF registerPlacedFeatures(String id, PF placedFeature) {
        ResourceLocation resourceLocation = new ResourceLocation(TheGraveyard.MOD_ID, id);

        Registry.register(BuiltinRegistries.PLACED_FEATURE, resourceLocation, placedFeature);
        return placedFeature;
    }


    @SubscribeEvent
    public static void registerFeaturesAndConfigFeatures(RegistryEvent.Register<Feature<?>> event) {
        features.forEach(feature -> event.getRegistry().register(feature));
    }
}
