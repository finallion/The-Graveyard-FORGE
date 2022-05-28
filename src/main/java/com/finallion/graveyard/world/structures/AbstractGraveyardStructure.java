package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.config.StructureConfigEntry;
import com.finallion.graveyard.util.BiomeCheckUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.data.BuiltinRegistries;
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
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractGraveyardStructure extends StructureFeature<JigsawConfiguration> {
    private final StructureConfigEntry structureConfigEntry;
    private String structureName;

    public AbstractGraveyardStructure(StructureConfigEntry config, int size, String name) {
        super(JigsawConfiguration.CODEC, (context -> AbstractGraveyardStructure.createPiecesGenerator(context, config, size, name)), PostPlacementProcessor.NONE);
        this.structureConfigEntry = config;
        this.structureName = name;
    }


    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context, StructureConfigEntry config, int size, String name) {
        BlockPos centerOfChunk = context.chunkPos().getMiddleBlockPosition(0);

        if (!isCorrectBiome(context, config, name)) {
            return false;
        }


        if (!isTerrainFlat(context.chunkGenerator(), centerOfChunk.getX(), centerOfChunk.getZ(), context.heightAccessor(), size)) {
            return false;
        }

        //if (!isWater(context.chunkGenerator(), centerOfChunk.getX(), centerOfChunk.getZ(), context.heightAccessor(), size)) {
        //    return false;
        //}


        return true;
    }

    public static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context, StructureConfigEntry config, int size, String name) {
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);

        if (!AbstractGraveyardStructure.isFeatureChunk(context, config, size, name)) {
            return Optional.empty();
        }

        Optional<PieceGenerator<JigsawConfiguration>> structurePiecesGenerator =
                JigsawPlacement.m_210284_(
                        context,
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

    public String getStructureName() {
        return structureName;
    }

    public abstract ConfiguredStructureFeature<?, ?> getStructureFeature();

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

        if (sample1.getBlock(i1).getFluidState().is(Fluids.WATER) || sample2.getBlock(j1).getFluidState().is(Fluids.WATER) || sample3.getBlock(k1).getFluidState().is(Fluids.WATER) || sample4.getBlock(o1).getFluidState().is(Fluids.WATER) || sample5.getBlock(p1).getFluidState().is(Fluids.WATER)) {
            return false;
        }


        int minSides = Math.min(Math.min(j1, p1), Math.min(o1, k1));
        int minHeight = Math.min(minSides, i1);

        int maxSides = Math.max(Math.max(j1, p1), Math.max(o1, k1));
        int maxHeight = Math.max(maxSides, i1);

        return Math.abs(maxHeight - minHeight) <= GraveyardConfig.COMMON.maxTerrainHeightDifference.get();
    }

    /*
    protected static boolean isWater(ChunkGenerator generator, int chunkX, int chunkZ, LevelHeightAccessor heightLimitView, int size) {
        Set<Biome> biomesInAreaOne = generator.getBiomeSource().getBiomesWithin(chunkX, 0, chunkZ, size, generator.climateSampler());

        for (Biome biome : biomesInAreaOne) {
            if (biome.getBiomeCategory() == Biome.BiomeCategory.OCEAN || biome.getBiomeCategory() == Biome.BiomeCategory.RIVER || biome.getBiomeCategory() == Biome.BiomeCategory.BEACH) {
                return false;
            }
        }

        return true;
    }
     */

    protected static boolean isCorrectBiome(PieceGeneratorSupplier.Context<JigsawConfiguration> context, StructureConfigEntry config, String name) {
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);

        Holder<Biome> biome = context.chunkGenerator().m_203495_(QuartPos.fromBlock(blockpos.getX()), QuartPos.fromBlock(blockpos.getY()), QuartPos.fromBlock(blockpos.getZ()));

        if (config.canGenerate.get() &&
                BiomeCheckUtil.parseBiomes(config.whitelist.get(), config.blacklist.get(), biome) &&
                BiomeCheckUtil.parseWhitelistedMods(config.modWhitelist.get(), biome)) {
            return true;
        }
        return false;
    }



}
