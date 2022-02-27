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

    private final List<String> defaultWhitelist;
    public ForgeConfigSpec.ConfigValue<List<? extends String>> whitelist;
    private final List<String> defaultBlacklist;
    public ForgeConfigSpec.ConfigValue<List<? extends String>> blacklist;
    private final List<String> defaultModWhitelist;
    public ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelist;
    private final boolean defaultCanSpawnGraveyardMobs;
    public ForgeConfigSpec.BooleanValue canSpawnGraveyardMobs;

    public StructureConfigEntry(int spacing, int separation, List<String> whitelist, List<String> blacklist, List<String> modWhitelist, boolean canSpawnGraveyardMobs) {
        this.defaultSpacing = spacing;
        this.defaultSeparation = separation;
        this.defaultWhitelist = whitelist;
        this.defaultBlacklist = blacklist;
        this.defaultModWhitelist = modWhitelist;
        this.defaultCanSpawnGraveyardMobs = canSpawnGraveyardMobs;
    }


    public int getSpacing() {
        return defaultSpacing;
    }

    public int getSeparation() {
        return defaultSeparation;
    }

    public boolean getCanSpawnGraveyardMobs() {
        return defaultCanSpawnGraveyardMobs;
    }

    public List<String> getWhitelist() {
        return defaultWhitelist;
    }

    public List<String> getBlacklist() {
        return defaultBlacklist;
    }

    public List<String> getModWhitelist() {
        return defaultModWhitelist;
    }
}
