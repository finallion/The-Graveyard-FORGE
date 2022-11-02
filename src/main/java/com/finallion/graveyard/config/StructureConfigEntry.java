package com.finallion.graveyard.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class StructureConfigEntry {

    private final boolean default_canGenerate;
    public ForgeConfigSpec.BooleanValue canGenerate;
    private final int default_separation;
    public ForgeConfigSpec.IntValue separation;
    private final int default_spacing;
    public ForgeConfigSpec.IntValue spacing;
    private final int default_salt;
    public ForgeConfigSpec.LongValue salt;
    private final List<String> default_biomeWhitelist;
    public ForgeConfigSpec.ConfigValue<List<? extends String>> biomeWhitelist;
    private final List<String> default_biomeBlacklist;
    public ForgeConfigSpec.ConfigValue<List<? extends String>> biomeBlacklist;
    private final int default_terrainCheckRadius;
    public ForgeConfigSpec.IntValue terrainCheckRadius;
    private final int default_maxTerrainHeightDifference;
    public ForgeConfigSpec.IntValue maxTerrainHeightDifference;
    private final boolean default_canSpawnGraveyardMobs;
    public ForgeConfigSpec.BooleanValue canSpawnGraveyardMobs;

    public StructureConfigEntry(int spacing, int separation, int salt, List<String> biomeWhitelist, List<String> biomeBlacklist, int terrainCheckRadius, int maxTerrainHeightDifference, boolean canSpawnGraveyardMobs) {
        this(true, spacing, separation, salt, biomeWhitelist, biomeBlacklist, terrainCheckRadius, maxTerrainHeightDifference, canSpawnGraveyardMobs);
    }

    private StructureConfigEntry(boolean canGenerate, int spacing, int separation, int salt, List<String> biomeWhitelist, List<String> biomeBlacklist, int terrainCheckRadius, int maxTerrainHeightDifference, boolean canSpawnGraveyardMobs) {
        this.default_canGenerate = canGenerate;
        this.default_spacing = spacing;
        this.default_separation = separation;
        this.default_salt = salt;
        this.default_biomeWhitelist = biomeWhitelist;
        this.default_biomeBlacklist = biomeBlacklist;
        this.default_terrainCheckRadius = terrainCheckRadius;
        this.default_maxTerrainHeightDifference = maxTerrainHeightDifference;
        this.default_canSpawnGraveyardMobs = canSpawnGraveyardMobs;
    }

    public static StructureConfigEntry of(int spacing, int separation, int salt, List<String> whitelist, List<String> modWhitelist, int terrainCheckRadius, int maxTerrainHeightDifference, boolean canSpawnGraveyardMobs) {
        return new StructureConfigEntry(spacing, separation, salt, whitelist, modWhitelist, terrainCheckRadius, maxTerrainHeightDifference, canSpawnGraveyardMobs);
    }

    public boolean isDefault_canGenerate() {
        return default_canGenerate;
    }

    public int getDefault_separation() {
        return default_separation;
    }

    public int getDefault_spacing() {
        return default_spacing;
    }

    public int getDefault_salt() {
        return default_salt;
    }

    public List<String> getDefault_biomeWhitelist() {
        return default_biomeWhitelist;
    }

    public List<? extends String> getDefault_biomeBlacklist() {
        return default_biomeBlacklist;
    }

    public int getDefault_terrainCheckRadius() {
        return default_terrainCheckRadius;
    }

    public int getDefault_maxTerrainHeightDifference() {
        return default_maxTerrainHeightDifference;
    }

    public boolean isDefault_canSpawnGraveyardMobs() {
        return default_canSpawnGraveyardMobs;
    }
}
