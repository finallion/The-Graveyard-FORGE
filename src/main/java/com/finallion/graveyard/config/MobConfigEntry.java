package com.finallion.graveyard.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MobConfigEntry {
    public ForgeConfigSpec.BooleanValue enabled;

    private final int defaultWeight;
    public ForgeConfigSpec.IntValue weight;

    private final int defaultMinGroupSize;
    public ForgeConfigSpec.IntValue minGroupSize;

    private final int defaultMaxGroupSize;
    public ForgeConfigSpec.IntValue maxGroupSize;


    public MobConfigEntry(int weight, int minGroupSize, int maxGroupSize) {
        this.defaultWeight = weight;
        this.defaultMinGroupSize = minGroupSize;
        this.defaultMaxGroupSize = maxGroupSize;
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

}
