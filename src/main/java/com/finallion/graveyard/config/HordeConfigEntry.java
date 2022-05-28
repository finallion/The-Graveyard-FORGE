package com.finallion.graveyard.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HordeConfigEntry {

    public ForgeConfigSpec.BooleanValue enabled;

    private final int defaultMobSpawnAttempts;
    public ForgeConfigSpec.IntValue mobSpawnAttempts;

    private final int defaultTicksUntilSpawn;
    public ForgeConfigSpec.IntValue ticksUntilSpawn;

    private final int defaultAdditionalRandomizedTicks;
    public ForgeConfigSpec.IntValue additionalRandomizedTicks;

    public HordeConfigEntry(int mobSpawnAttempts, int ticksUntilSpawn, int additionalRandomizedTicks) {
        this.defaultMobSpawnAttempts = mobSpawnAttempts;
        this.defaultTicksUntilSpawn = ticksUntilSpawn;
        this.defaultAdditionalRandomizedTicks = additionalRandomizedTicks;
    }

    public int getSize() {
        return defaultMobSpawnAttempts;
    }

    public int getTicksUntilSpawn() {
        return defaultTicksUntilSpawn;
    }




}
