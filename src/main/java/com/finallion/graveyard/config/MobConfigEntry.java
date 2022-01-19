package com.finallion.graveyard.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MobConfigEntry {
    public ForgeConfigSpec.BooleanValue enabled;

    private final int defaultWeight;
    public ForgeConfigSpec.IntValue weight;

    private final int defaultMinGroupSize;
    public ForgeConfigSpec.IntValue minGroupSize;

    private final int defaultMaxGroupSize;
    public ForgeConfigSpec.IntValue maxGroupSize;

    private final List<String> defaultAllowedBiomesAndCategories;
    public ForgeConfigSpec.ConfigValue<List<? extends String>> allowedBiomesAndCategories;

    public MobConfigEntry(int weight, int minGroupSize, int maxGroupSize, List<String> allowedBiomesAndCategories) {
        this.defaultWeight = weight;
        this.defaultMinGroupSize = minGroupSize;
        this.defaultMaxGroupSize = maxGroupSize;
        this.defaultAllowedBiomesAndCategories = allowedBiomesAndCategories;
    }


    public int getWeight() {
        return defaultWeight;
    }

    public int getMinGroupSize() {
        return defaultMinGroupSize;
    }

    public int getMaxGroupSize() {
        return defaultMaxGroupSize;
    }

    public List<String> getBiomeCategories() {
        return defaultAllowedBiomesAndCategories;
    }

}
