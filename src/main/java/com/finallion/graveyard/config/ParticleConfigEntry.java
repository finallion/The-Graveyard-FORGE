package com.finallion.graveyard.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ParticleConfigEntry {

    public ForgeConfigSpec.BooleanValue enabled;

    private final int defaultFrequency;
    public ForgeConfigSpec.IntValue frequency;


    public ParticleConfigEntry(int frequency) {
        this.defaultFrequency = frequency;
    }

    public int getFrequency() {
        return defaultFrequency;
    }




}
