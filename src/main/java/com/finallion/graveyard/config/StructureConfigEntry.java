package com.finallion.graveyard.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StructureConfigEntry {

    public ForgeConfigSpec.BooleanValue canGenerate;

    private final int defaultSpacing;
    public ForgeConfigSpec.IntValue spacing;

    private final int defaultSeparation;
    public ForgeConfigSpec.IntValue separation;

    private final List<String> defaultBiomeCategories;
    public ForgeConfigSpec.ConfigValue<List<? extends String>> biomeCategories;
    private final List<String> defaultBlacklistedBiomes;
    public ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistedBiomes;

    public StructureConfigEntry(int spacing, int separation, String... biomeCategories) {
        this.defaultSpacing = spacing;
        this.defaultSeparation = separation;
        this.defaultBiomeCategories = Arrays.asList(biomeCategories);
        this.defaultBlacklistedBiomes = Collections.emptyList();
    }

    public StructureConfigEntry(int spacing, int separation, List<String> biomeCategories, List<String> blacklistedBiomes) {
        this.defaultSpacing = spacing;
        this.defaultSeparation = separation;
        this.defaultBiomeCategories = biomeCategories;
        this.defaultBlacklistedBiomes = blacklistedBiomes;
    }


    public int getSpacing() {
        return defaultSpacing;
    }

    public int getSeparation() {
        return defaultSeparation;
    }

    public List<String> getBiomeCategories() {
        return defaultBiomeCategories;
    }

    public List<String> getBlacklistedBiomes() {
        return defaultBlacklistedBiomes;
    }
}
