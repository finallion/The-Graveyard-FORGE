package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.StructureConfigEntry;
import com.finallion.graveyard.init.TGEntities;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractGraveyardStructure extends StructureFeature<JigsawConfiguration> {
    private int seed;
    private final StructureConfigEntry structureConfigEntry;
    private String structureName;

    public AbstractGraveyardStructure(Codec<JigsawConfiguration> codec, StructureConfigEntry structureConfigEntry, int size, int seed, StructureTemplatePool pool, String name) {
        super(codec, (context) -> {
            if (!AbstractGraveyardStructure.isFeatureChunk(context, size)) {
                return Optional.empty();
            } else {
                return AbstractGraveyardStructure.createPiecesGenerator(context, pool);
            }
        });
        this.seed = seed;
        this.structureConfigEntry = structureConfigEntry;
        this.structureName = name;
    }

    public static final Lazy<List<MobSpawnSettings.SpawnerData>> MONSTER_SPAWNS = Lazy.of(() -> ImmutableList.of(
            new MobSpawnSettings.SpawnerData(TGEntities.SKELETON_CREEPER, 35, 1, 2),
            new MobSpawnSettings.SpawnerData(TGEntities.GHOUL, 50, 1, 3),
            new MobSpawnSettings.SpawnerData(TGEntities.REVENANT, 45, 1, 3)
    ));


    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context, int size) {
        BlockPos centerOfChunk = context.chunkPos().getMiddleBlockPosition(0);

        if (!isTerrainFlat(context.chunkGenerator(), centerOfChunk.getX(), centerOfChunk.getZ(), context.heightAccessor(), size)) {
            return false;
        }

        if (!isWater(context.chunkGenerator(), centerOfChunk.getX(), centerOfChunk.getZ(), context.heightAccessor(), size)) {
            return false;
        }

        return true;
    }

    public static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context, StructureTemplatePool pool) {
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);

        JigsawConfiguration newConfig = new JigsawConfiguration(() -> pool, 3);

        PieceGeneratorSupplier.Context<JigsawConfiguration> newContext = new PieceGeneratorSupplier.Context<>(
                context.chunkGenerator(),
                context.biomeSource(),
                context.seed(),
                context.chunkPos(),
                newConfig,
                context.heightAccessor(),
                context.validBiome(),
                context.structureManager(),
                context.registryAccess()
        );

        Optional<PieceGenerator<JigsawConfiguration>> structurePiecesGenerator =
                JigsawPlacement.addPieces(
                        newContext,
                        PoolElementStructurePiece::new,
                        blockpos,
                        false,
                        true
                );


        return structurePiecesGenerator;
    }

    public StructureConfigEntry getStructureConfigEntry() {
        return structureConfigEntry;
    }

    public StructureFeatureConfiguration getStructureFeatureConfiguration() {
        return new StructureFeatureConfiguration(structureConfigEntry.getSpacing(), structureConfigEntry.getSeparation(), seed);
    }


    public abstract ConfiguredStructureFeature<?, ?> getStructureFeature();

    public String getStructureName() {
        return structureName;
    }


    protected static boolean isTerrainFlat(ChunkGenerator generator, int chunkX, int chunkZ, LevelHeightAccessor heightLimitView, int size) {
        // center of generation is chunkX 0 chunkZ (i)
        // checks:
        //
        // n    j    l
        // o    i    k
        // q    p    m

        int offset = size;

        int i1 = generator.getFirstOccupiedHeight(chunkX, chunkZ, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int j1 = generator.getFirstOccupiedHeight(chunkX, chunkZ + offset, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int k1 = generator.getFirstOccupiedHeight(chunkX + offset, chunkZ, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int o1 = generator.getFirstOccupiedHeight(chunkX, chunkZ - offset, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int p1 = generator.getFirstOccupiedHeight(chunkX - offset, chunkZ, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);

        NoiseColumn sample1 = generator.getBaseColumn(chunkX, chunkZ, heightLimitView);
        NoiseColumn sample2 = generator.getBaseColumn(chunkX, chunkZ + size, heightLimitView);
        NoiseColumn sample3 = generator.getBaseColumn(chunkX + size, chunkZ, heightLimitView);
        NoiseColumn sample4 = generator.getBaseColumn(chunkX, chunkZ - size, heightLimitView);
        NoiseColumn sample5 = generator.getBaseColumn(chunkX - size, chunkZ, heightLimitView);

        if (sample1.getBlock(i1).getFluidState().is(FluidTags.WATER) || sample2.getBlock(j1).getFluidState().is(FluidTags.WATER) || sample3.getBlock(k1).getFluidState().is(FluidTags.WATER) || sample4.getBlock(o1).getFluidState().is(FluidTags.WATER) || sample5.getBlock(p1).getFluidState().is(FluidTags.WATER)) {
            return false;
        }


        int minSides = Math.min(Math.min(j1, p1), Math.min(o1, k1));
        int minHeight = Math.min(minSides, i1);

        int maxSides = Math.max(Math.max(j1, p1), Math.max(o1, k1));
        int maxHeight = Math.max(maxSides, i1);

        return Math.abs(maxHeight - minHeight) <= 3;

    }

    protected static boolean isWater(ChunkGenerator generator, int chunkX, int chunkZ, LevelHeightAccessor heightLimitView, int size) {
        Set<Biome> biomesInAreaOne = generator.getBiomeSource().getBiomesWithin(chunkX, 0, chunkZ, size, generator.climateSampler());

        for (Biome biome : biomesInAreaOne) {
            if (biome.getBiomeCategory() == Biome.BiomeCategory.OCEAN || biome.getBiomeCategory() == Biome.BiomeCategory.RIVER || biome.getBiomeCategory() == Biome.BiomeCategory.BEACH) {
                return false;
            }
        }

        return true;

    }
}
