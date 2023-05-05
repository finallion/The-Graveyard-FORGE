package com.finallion.graveyard.world.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Optional;

public class TGJigsawConfig implements FeatureConfiguration {

    public static final Codec<TGJigsawConfig> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                            //Structure.StructureSettings.CODEC.forGetter(feature -> feature.config),
                            StructureTemplatePool.f_210555_.fieldOf("start_pool").forGetter(config -> config.startPool),
                            ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(config -> config.startJigsawName),
                            Codec.intRange(0, 7).fieldOf("size").forGetter(config -> config.size),
                            HeightProvider.CODEC.fieldOf("start_height").forGetter(config -> config.startHeight),
                            Codec.BOOL.fieldOf("use_expansion_hack").forGetter(config -> config.useExpansionHack),
                            Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                            Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter),
                            Codec.INT.fieldOf("terrain_check_size").forGetter(structure -> structure.terrainCheckSize),
                            Codec.INT.fieldOf("max_height_difference").forGetter(structure -> structure.maxHeightDifference),
                            Codec.STRING.fieldOf("structure_name").forGetter(config -> config.structureName))
                    .apply(instance, TGJigsawConfig::new));


    public final Holder<StructureTemplatePool> startPool;
    public final Optional<ResourceLocation> startJigsawName;
    public final int size;
    public final HeightProvider startHeight;
    public final boolean useExpansionHack;
    public final Optional<Heightmap.Types> projectStartToHeightmap;
    public final int maxDistanceFromCenter;
    public final int terrainCheckSize;
    public final int maxHeightDifference;
    public final String structureName;

    public TGJigsawConfig(Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int size, HeightProvider startHeight, Boolean useExpansionHack, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter, int terrainCheckSize, int maxHeightDifference, String structureName) {
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.useExpansionHack = useExpansionHack;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
        this.maxHeightDifference = maxHeightDifference;
        this.terrainCheckSize = terrainCheckSize;
        this.structureName = structureName;
    }
}
