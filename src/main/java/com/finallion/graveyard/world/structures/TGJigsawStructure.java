package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.TGEntities;

import com.finallion.graveyard.init.TGStructureType;
import com.finallion.graveyard.util.BiomeSelectionUtil;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;


import java.util.*;

public class TGJigsawStructure extends Structure {
    public static final int MAX_SIZE = 128;

    public static final WeightedRandomList<MobSpawnSettings.SpawnerData> MONSTER_SPAWNS = WeightedRandomList.create(
            new MobSpawnSettings.SpawnerData(TGEntities.SKELETON_CREEPER.get(), 35, 1, 2),
            new MobSpawnSettings.SpawnerData(TGEntities.GHOUL.get(), 50, 1, 3),
            new MobSpawnSettings.SpawnerData(TGEntities.REVENANT.get(), 45, 1, 3)
    );

    public static final WeightedRandomList<MobSpawnSettings.SpawnerData> EMPTY = WeightedRandomList.create();

    public static final WeightedRandomList<MobSpawnSettings.SpawnerData> ILLAGER_SPAWNS = WeightedRandomList.create(
            new MobSpawnSettings.SpawnerData(EntityType.PILLAGER, 10, 1, 1),
            new MobSpawnSettings.SpawnerData(EntityType.VINDICATOR, 10, 1, 1)
    );


    //StructureConfigEntry config
    public static final Codec<TGJigsawStructure> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Structure.StructureSettings.CODEC.forGetter(feature -> feature.config),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(config -> config.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(config -> config.startJigsawName),
                    Codec.intRange(0, 7).fieldOf("size").forGetter(config -> config.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(config -> config.startHeight),
                    Codec.BOOL.fieldOf("use_expansion_hack").forGetter(config -> config.useExpansionHack),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter),
                    Codec.INT.fieldOf("terrain_check_size").forGetter(structure -> structure.terrainCheckSize),
                    Codec.INT.fieldOf("max_height_difference").forGetter(structure -> structure.maxHeightDifference),
                    Codec.STRING.listOf().fieldOf("whitelist").orElse(new ArrayList<>()).forGetter(config -> config.whitelist),
                    Codec.STRING.listOf().fieldOf("mod_whitelist").orElse(new ArrayList<>()).forGetter(config -> config.modWhitelist),
                    Codec.STRING.fieldOf("structure_name").forGetter(config -> config.structureName))
                    .apply(instance, TGJigsawStructure::new));


    public final Holder<StructureTemplatePool> startPool;
    public final Optional<ResourceLocation> startJigsawName;
    public final int size;
    public final HeightProvider startHeight;
    public final boolean useExpansionHack;
    public final Optional<Heightmap.Types> projectStartToHeightmap;
    public final int maxDistanceFromCenter;
    public final int terrainCheckSize;
    public final int maxHeightDifference;
    public final List<String> whitelist;
    public final List<String> modWhitelist;
    public final String structureName;
    protected final Structure.StructureSettings config;

    public TGJigsawStructure(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int size, HeightProvider startHeight, Boolean useExpansionHack, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter, int terrainCheckSize, int maxHeightDifference, List<String> whitelist, List<String> modWhitelist, String structureName) {
        super(config);
        this.config = config;
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.useExpansionHack = useExpansionHack;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
        this.maxHeightDifference = maxHeightDifference;
        this.terrainCheckSize = terrainCheckSize;
        this.whitelist = whitelist;
        this.modWhitelist = modWhitelist;
        this.structureName = structureName;
    }

    public TGJigsawStructure(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool, int size, HeightProvider startHeight, boolean useExpansionHack, Heightmap.Types projectStartToHeightmap, int terrainCheckSize, int maxHeightDifference, List<String> whitelist, List<String> modWhitelist, String structureName) {
        this(config, startPool, Optional.empty(), size, startHeight, useExpansionHack, Optional.of(projectStartToHeightmap), 80, terrainCheckSize, maxHeightDifference, whitelist, modWhitelist, structureName);
    }

    public TGJigsawStructure(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool, int size, HeightProvider startHeight, boolean useExpansionHack, int terrainCheckSize, int maxHeightDifference, List<String> whitelist, List<String> modWhitelist, String structureName) {
        this(config, startPool, Optional.empty(), size, startHeight, useExpansionHack, Optional.empty(), 80, terrainCheckSize, maxHeightDifference, whitelist, modWhitelist, structureName);
    }


    @Override
    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context) {
        if (!canGenerate(structureName)) {
            return Optional.empty();
        }


        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);

        if (structureName.equals("crypt")) {
            int minHeight = -40; // default: -40
            int maxHeight = -10; // default: -10

            ChunkPos chunkPos = context.chunkPos();
            WorldgenRandom random = context.random();

            int x = random.nextInt(chunkPos.getMaxBlockX() - chunkPos.getMinBlockX()) + chunkPos.getMinBlockX();
            int z = random.nextInt(chunkPos.getMaxBlockZ() - chunkPos.getMiddleBlockZ()) + chunkPos.getMiddleBlockZ();
            int y = random.nextInt(maxHeight - minHeight) + minHeight;
            blockpos = new BlockPos(x, y, z);

            if (!TGJigsawStructure.canGenerateUnderground(context, structureName)) {
                return Optional.empty();
            }
        } else {
            if (!TGJigsawStructure.canGenerate(context, terrainCheckSize, structureName, blockpos, maxHeightDifference)) {
                return Optional.empty();
            }
        }


        return JigsawPlacement.addPieces(
                context,
                this.startPool,
                this.startJigsawName,
                this.size, blockpos,
                this.useExpansionHack,
                this.projectStartToHeightmap,
                this.maxDistanceFromCenter);
    }

    private static boolean canGenerateUnderground(Structure.GenerationContext context, String name) {
        if (!isCorrectBiome(context, name)) {
            return false;
        }

        return true;
    }

    private static boolean canGenerate(Structure.GenerationContext context, int size, String name, BlockPos centerOfChunk, int maxHeightDifference) {
        if (!isCorrectBiome(context, name)) {
            return false;
        }

        if (!isTerrainFlat(context, centerOfChunk, size / 2, maxHeightDifference)) {
            return false;
        }

        if (!isTerrainFlat(context, centerOfChunk, size, maxHeightDifference)) {
            return false;
        }

        return true;
    }

    protected static boolean isCorrectBiome(Structure.GenerationContext context, String name) {
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);

        Holder<Biome> biome = context.chunkGenerator().getBiomeSource().getNoiseBiome(
                QuartPos.fromBlock(blockpos.getX()),
                QuartPos.fromBlock(blockpos.getY()),
                QuartPos.fromBlock(blockpos.getZ()), context.randomState().sampler());

        if (BiomeSelectionUtil.parseBiomes(getBiomeWhiteList(name), getModIdWhiteList(name), biome)) {
            return true;
        }

        return false;
    }


    protected static boolean isTerrainFlat(Structure.GenerationContext context, BlockPos centerChunk, int size, int maxHeightDifference) {
        ChunkGenerator generator = context.chunkGenerator();
        LevelHeightAccessor heightLimitView = context.heightAccessor();
        int chunkX = centerChunk.getX();
        int chunkZ = centerChunk.getZ();
        RandomState noiseConfig = context.randomState();

        int i1 = generator.getFirstOccupiedHeight(chunkX, chunkZ, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView, noiseConfig);
        int j1 = generator.getFirstOccupiedHeight(chunkX, chunkZ + size, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView, noiseConfig);
        int k1 = generator.getFirstOccupiedHeight(chunkX + size, chunkZ, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView, noiseConfig);
        int o1 = generator.getFirstOccupiedHeight(chunkX, chunkZ - size, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView, noiseConfig);
        int p1 = generator.getFirstOccupiedHeight(chunkX - size, chunkZ, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView, noiseConfig);
        //int l1 = generator.getFirstOccupiedHeight(chunkX + size, chunkZ + size, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView, noiseConfig);
        //int m1 = generator.getFirstOccupiedHeight(chunkX - size, chunkZ - size, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView, noiseConfig);
        //int n1 = generator.getFirstOccupiedHeight(chunkX + size, chunkZ - size, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView, noiseConfig);
        //int q1 = generator.getFirstOccupiedHeight(chunkX - size, chunkZ + size, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView, noiseConfig);

        NoiseColumn sample1 = generator.getBaseColumn(chunkX, chunkZ, heightLimitView, noiseConfig);
        NoiseColumn sample2 = generator.getBaseColumn(chunkX, chunkZ + size, heightLimitView, noiseConfig);
        NoiseColumn sample3 = generator.getBaseColumn(chunkX + size, chunkZ, heightLimitView, noiseConfig);
        NoiseColumn sample4 = generator.getBaseColumn(chunkX, chunkZ - size, heightLimitView, noiseConfig);
        NoiseColumn sample5 = generator.getBaseColumn(chunkX - size, chunkZ, heightLimitView, noiseConfig);
        //VerticalBlockSample sample6 = generator.getColumnSample(chunkX + size, chunkZ + size, heightLimitView, noiseConfig);
        //VerticalBlockSample sample7 = generator.getColumnSample(chunkX - size, chunkZ - size, heightLimitView, noiseConfig);
        //VerticalBlockSample sample8 = generator.getColumnSample(chunkX + size, chunkZ - size, heightLimitView, noiseConfig);
        //VerticalBlockSample sample9 = generator.getColumnSample(chunkX - size, chunkZ + size, heightLimitView, noiseConfig);

        // subtract -1 if getHeightOnGround
        if (sample1.getBlock(i1).getFluidState().is(FluidTags.WATER) || sample2.getBlock(j1).getFluidState().is(FluidTags.WATER) || sample3.getBlock(k1).getFluidState().is(FluidTags.WATER) || sample4.getBlock(o1).getFluidState().is(FluidTags.WATER) || sample5.getBlock(p1).getFluidState().is(FluidTags.WATER)) {
            return false;
        }

        int minSides = Math.min(Math.min(j1, p1), Math.min(o1, k1));
        int minHeight = Math.min(minSides, i1);

        int maxSides = Math.max(Math.max(j1, p1), Math.max(o1, k1));
        int maxHeight = Math.max(maxSides, i1);

        return Math.abs(maxHeight - minHeight) <= maxHeightDifference;

    }

    public StructureType<?> type() {
        return TGStructureType.TG_JIGSAW;
    }

    // TODO move to StructureConfigEntry, this is just a very dirty, very quick way to make 1.19 work
    private boolean canGenerate(String name) {
        return switch (name) {
            case "haunted_house" -> GraveyardConfig.COMMON.canGenerateHauntedHouse.get();
            case "large_graveyard" -> GraveyardConfig.COMMON.canGenerateLargeGraveyard.get();
            case "medium_graveyard" -> GraveyardConfig.COMMON.canGenerateMediumGraveyard.get();
            case "small_graveyard" -> GraveyardConfig.COMMON.canGenerateSmallGraveyard.get();
            case "small_desert_graveyard" -> GraveyardConfig.COMMON.canGenerateSmallDesertGraveyard.get();
            case "small_grave" -> GraveyardConfig.COMMON.canGenerateSmallGrave.get();
            case "small_mountain_grave" -> GraveyardConfig.COMMON.canGenerateSmallMountainGrave.get();
            case "small_savanna_grave" -> GraveyardConfig.COMMON.canGenerateSmallSavannaGrave.get();
            case "small_desert_grave" -> GraveyardConfig.COMMON.canGenerateSmallDesertGrave.get();
            case "mushroom_grave" -> GraveyardConfig.COMMON.canGenerateMushroomGrave.get();
            case "memorial_tree" -> GraveyardConfig.COMMON.canGenerateMemorialTree.get();
            case "crypt" -> GraveyardConfig.COMMON.canGenerateCrypt.get();
            case "altar" -> GraveyardConfig.COMMON.canGenerateAltar.get();
            case "giant_mushroom" -> GraveyardConfig.COMMON.canGenerateGiantMushroom.get();
            default -> false;
        };
    }

    public static List<String> getBiomeWhiteList(String name) {
        return switch (name) {
            case "haunted_house" -> (List<String>) GraveyardConfig.COMMON.whitelistHauntedHouse.get();
            case "large_graveyard" -> (List<String>) GraveyardConfig.COMMON.whitelistLargeGraveyard.get();
            case "medium_graveyard" -> (List<String>) GraveyardConfig.COMMON.whitelistMediumGraveyard.get();
            case "small_graveyard" -> (List<String>) GraveyardConfig.COMMON.whitelistSmallGraveyard.get();
            case "small_desert_graveyard" -> (List<String>) GraveyardConfig.COMMON.whitelistSmallDesertGraveyard.get();
            case "small_grave" -> (List<String>) GraveyardConfig.COMMON.whitelistSmallGrave.get();
            case "small_mountain_grave" -> (List<String>) GraveyardConfig.COMMON.whitelistSmallMountainGrave.get();
            case "small_savanna_grave" -> (List<String>) GraveyardConfig.COMMON.whitelistSmallSavannaGrave.get();
            case "small_desert_grave" -> (List<String>) GraveyardConfig.COMMON.whitelistSmallDesertGrave.get();
            case "mushroom_grave" -> (List<String>) GraveyardConfig.COMMON.whitelistMushroomGrave.get();
            case "memorial_tree" -> (List<String>) GraveyardConfig.COMMON.whitelistMemorialTree.get();
            case "crypt" -> (List<String>) GraveyardConfig.COMMON.whitelistCrypt.get();
            case "altar" -> (List<String>) GraveyardConfig.COMMON.whitelistAltar.get();
            case "giant_mushroom" -> (List<String>) GraveyardConfig.COMMON.whitelistGiantMushroom.get();
            default -> Collections.EMPTY_LIST;
        };

    }

    public static List<String> getModIdWhiteList(String name) {
        return switch (name) {
            case "haunted_house" -> (List<String>) GraveyardConfig.COMMON.modWhitelistHauntedHouse.get();
            case "large_graveyard" -> (List<String>) GraveyardConfig.COMMON.modWhitelistLargeGraveyard.get();
            case "medium_graveyard" -> (List<String>) GraveyardConfig.COMMON.modWhitelistMediumGraveyard.get();
            case "small_graveyard" -> (List<String>) GraveyardConfig.COMMON.modWhitelistSmallGraveyard.get();
            case "small_desert_graveyard" -> (List<String>) GraveyardConfig.COMMON.modWhitelistSmallDesertGraveyard.get();
            case "small_grave" -> (List<String>) GraveyardConfig.COMMON.modWhitelistSmallGrave.get();
            case "small_mountain_grave" -> (List<String>) GraveyardConfig.COMMON.modWhitelistSmallMountainGrave.get();
            case "small_savanna_grave" -> (List<String>) GraveyardConfig.COMMON.modWhitelistSmallSavannaGrave.get();
            case "small_desert_grave" -> (List<String>) GraveyardConfig.COMMON.modWhitelistSmallDesertGrave.get();
            case "mushroom_grave" -> (List<String>) GraveyardConfig.COMMON.modWhitelistMushroomGrave.get();
            case "memorial_tree" -> (List<String>) GraveyardConfig.COMMON.modWhitelistMemorialTree.get();
            case "crypt" -> (List<String>) GraveyardConfig.COMMON.modWhitelistCrypt.get();
            case "altar" -> (List<String>) GraveyardConfig.COMMON.modWhitelistAltar.get();
            case "giant_mushroom" -> (List<String>) GraveyardConfig.COMMON.modWhitelistGiantMushroom.get();
            default -> Collections.EMPTY_LIST;
        };

    }



}