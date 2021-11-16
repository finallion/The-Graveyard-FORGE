package com.finallion.graveyard.utils;

import com.finallion.graveyard.TheGraveyard;

public class ConfigConsts {

    public static final boolean enableFogForest = TheGraveyard.CONFIG.haunted_forest_fog.get();
    public static final boolean enableFogLakes = TheGraveyard.CONFIG.haunted_lakes_fog.get();
    public static final boolean enableFogEroded = TheGraveyard.CONFIG.eroded_haunted_forest_fog.get();

    public static final double fogDensityForest = TheGraveyard.CONFIG.haunted_forest_fog_density.get();
    public static final double fogDensityLakes = TheGraveyard.CONFIG.haunted_forest_fog_density.get();
    public static final double fogDensityEroded = TheGraveyard.CONFIG.eroded_haunted_forest_fog_density.get();

    public static final boolean enableForestBiome = TheGraveyard.CONFIG.haunted_forest.get();
    public static final boolean enableLakesBiome = TheGraveyard.CONFIG.haunted_lakes.get();
    public static final boolean enableErodedBiome = TheGraveyard.CONFIG.eroded_haunted_forest.get();

    public static final double chanceForest = TheGraveyard.CONFIG.haunted_forest_chance.get();
    public static final double chanceLakes = TheGraveyard.CONFIG.haunted_lakes_chance.get();
    public static final double chanceEroded = TheGraveyard.CONFIG.eroded_haunted_forest_chance.get();

    public static final int fogForestMaxY = TheGraveyard.CONFIG.haunted_forest_fogMaxY.get();
    public static final int fogForestMinY = TheGraveyard.CONFIG.haunted_forest_fogMinY.get();
    public static final int fogLakesMaxY = TheGraveyard.CONFIG.haunted_lakes_fogMaxY.get();
    public static final int fogLakesMinY = TheGraveyard.CONFIG.haunted_lakes_fogMinY.get();
    public static final int fogErodedMaxY = TheGraveyard.CONFIG.eroded_haunted_forest_fogMaxY.get();
    public static final int fogErodedMinY = TheGraveyard.CONFIG.eroded_haunted_forest_fogMinY.get();





}
