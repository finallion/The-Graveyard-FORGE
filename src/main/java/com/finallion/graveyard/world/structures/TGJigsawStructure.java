package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.TGEntities;
import com.finallion.graveyard.init.TGStructureType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
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

import java.util.Optional;

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
    public final String structureName;
    protected final Structure.StructureSettings config;

    public TGJigsawStructure(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int size, HeightProvider startHeight, Boolean useExpansionHack, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter, int terrainCheckSize, int maxHeightDifference, String structureName) {
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
        this.structureName = structureName;
    }

    public TGJigsawStructure(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool, int size, HeightProvider startHeight, boolean useExpansionHack, Heightmap.Types projectStartToHeightmap, int terrainCheckSize, int maxHeightDifference, String structureName) {
        this(config, startPool, Optional.empty(), size, startHeight, useExpansionHack, Optional.of(projectStartToHeightmap), 80, terrainCheckSize, maxHeightDifference, structureName);
    }

    public TGJigsawStructure(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool, int size, HeightProvider startHeight, boolean useExpansionHack, int terrainCheckSize, int maxHeightDifference, String structureName) {
        this(config, startPool, Optional.empty(), size, startHeight, useExpansionHack, Optional.empty(), 80, terrainCheckSize, maxHeightDifference, structureName);
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

        } else if (structureName.equals("lich_prison")) {
            ChunkPos chunkPos = context.chunkPos();
            RandomSource random = context.random();

            int x = random.nextInt(chunkPos.getMaxBlockX() - chunkPos.getMinBlockX()) + chunkPos.getMinBlockX();
            int z = random.nextInt(chunkPos.getMaxBlockZ() - chunkPos.getMiddleBlockZ()) + chunkPos.getMiddleBlockZ();
            int y = 210;
            blockpos = new BlockPos(x, y, z);

        }else {
            if (!TGJigsawStructure.canGenerate(context, terrainCheckSize, blockpos, maxHeightDifference)) {
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

    private static boolean canGenerate(Structure.GenerationContext context, int size, BlockPos centerOfChunk, int maxHeightDifference) {
        if (!isTerrainFlat(context, centerOfChunk, size, maxHeightDifference)) {
            return false;
        }

        return true;
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
            case "ruins" -> GraveyardConfig.COMMON.canGenerateRuins.get();
            case "lich_prison" -> GraveyardConfig.COMMON.canGenerateLichPrison.get();
            default -> false;
        };
    }
}