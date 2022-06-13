package com.finallion.graveyard.world.structures.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;

import java.util.ArrayList;
import java.util.List;

public class GraveyardStructureConfig implements IFeatureConfig {


    public static final Codec<GraveyardStructureConfig> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                            Codec.BOOL.fieldOf("can_generate").forGetter(config -> config.canGenerate),
                            Codec.INT.fieldOf("spacing").forGetter(structure -> structure.spacing),
                            Codec.INT.fieldOf("separation").forGetter(structure -> structure.separation),
                            Codec.INT.fieldOf("salt").forGetter(structure -> structure.salt),
                            JigsawPattern.DIRECT_CODEC.fieldOf("start_pool").forGetter(config -> config.startPool),
                            Codec.intRange(0, 7).fieldOf("size").forGetter(config -> config.size),
                            Codec.BOOL.fieldOf("use_expansion_hack").forGetter(config -> config.useExpansionHack),
                            Codec.INT.fieldOf("terrain_check_size").forGetter(structure -> structure.terrainCheckSize),
                            Codec.INT.fieldOf("max_height_difference").forGetter(structure -> structure.maxHeightDifference),
                            Codec.STRING.listOf().fieldOf("whitelist").orElse(new ArrayList<>()).forGetter(config -> config.whitelist),
                            Codec.STRING.listOf().fieldOf("blacklist").orElse(new ArrayList<>()).forGetter(config -> config.blacklist),
                            Codec.STRING.listOf().fieldOf("mod_whitelist").orElse(new ArrayList<>()).forGetter(config -> config.modWhitelist),
                            Codec.STRING.fieldOf("structure_name").forGetter(config -> config.structureName))
                    .apply(instance, GraveyardStructureConfig::new));

    public final boolean canGenerate;
    public final int spacing;
    public final int separation;
    public final int salt;
    public final JigsawPattern startPool;
    public final int size;
    public final boolean useExpansionHack;
    public final int terrainCheckSize;
    public final int maxHeightDifference;
    public final List<String> whitelist;
    public final List<String> blacklist;
    public final List<String> modWhitelist;
    public final String structureName;

    public GraveyardStructureConfig(boolean canGenerate, int spacing, int separation, int salt, JigsawPattern startPool, int size, Boolean useExpansionHack, int terrainCheckSize, int maxHeightDifference, List<String> whitelist, List<String> blacklist, List<String> modWhitelist, String structureName) {
        this.canGenerate = canGenerate;
        this.spacing = spacing;
        this.separation = separation;
        this.salt = salt;
        this.startPool = startPool;
        this.size = size;
        this.useExpansionHack = useExpansionHack;
        this.maxHeightDifference = maxHeightDifference;
        this.terrainCheckSize = terrainCheckSize;
        this.whitelist = whitelist;
        this.blacklist = blacklist;
        this.modWhitelist = modWhitelist;
        this.structureName = structureName;
    }


}