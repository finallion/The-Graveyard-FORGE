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

    private final long defaultSalt;
    public ForgeConfigSpec.LongValue salt;

    private final List<? extends String> defaultWhitelist;
    public ForgeConfigSpec.ConfigValue<List<? extends String>> whitelist;
    private final List<? extends String> defaultBlacklist;
    public ForgeConfigSpec.ConfigValue<List<? extends String>> blacklist;
    private final List<? extends String> defaultModWhitelist;
    public ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelist;
    private final boolean defaultCanSpawnGraveyardMobs;
    public ForgeConfigSpec.BooleanValue canSpawnGraveyardMobs;

    public StructureConfigEntry(int spacing, int separation, int salt, List<? extends String> whitelist, List<? extends String> blacklist, List<? extends String> modWhitelist, boolean canSpawnGraveyardMobs) {
        this.defaultSpacing = spacing;
        this.defaultSeparation = separation;
        this.defaultSalt = salt;
        this.defaultWhitelist = whitelist;
        this.defaultBlacklist = blacklist;
        this.defaultModWhitelist = modWhitelist;
        this.defaultCanSpawnGraveyardMobs = canSpawnGraveyardMobs;
    }

    public List<? extends String> getDefaultWhitelist() {
        return defaultWhitelist;
    }

    public List<? extends String> getDefaultBlacklist() {
        return defaultBlacklist;
    }

    public List<? extends String> getDefaultModWhitelist() {
        return defaultModWhitelist;
    }


    public int getDefaultSpacing() {
        return defaultSpacing;
    }

    public int getDefaultSeparation() {
        return defaultSeparation;
    }

    public long getDefaultSalt() {
        return defaultSalt;
    }

    public boolean getDefaultCanSpawnGraveyardMobs() {
        return defaultCanSpawnGraveyardMobs;
    }
}
