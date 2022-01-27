package com.finallion.graveyard.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class BiomeFogConfigEntry {

    public ForgeConfigSpec.BooleanValue enabled;

    private final float defaultDensity;
    public ForgeConfigSpec.DoubleValue density;

    private final int defaultMinY;
    public ForgeConfigSpec.IntValue minY;

    private final int defaultMaxY;
    public ForgeConfigSpec.IntValue maxY;


    public BiomeFogConfigEntry(float density, int minY, int maxY) {
        this.defaultDensity = density;
        this.defaultMinY = minY;
        this.defaultMaxY = maxY;
    }

    public float getDensity() {
        return defaultDensity;
    }




}
