package com.finallion.graveyard.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HordeConfigEntry {

    public ForgeConfigSpec.BooleanValue enabled;

    private final int defaultSize;
    public ForgeConfigSpec.IntValue size;

    private final int defaultTicksUntilSpawn;
    public ForgeConfigSpec.IntValue ticksUntilSpawn;

    public HordeConfigEntry(int size, int ticksUntilSpawn) {
        this.defaultSize = size;
        this.defaultTicksUntilSpawn = ticksUntilSpawn;
    }

    public int getSize() {
        return defaultSize;
    }

    public int getTicksUntilSpawn() {
        return defaultTicksUntilSpawn;
    }



}
