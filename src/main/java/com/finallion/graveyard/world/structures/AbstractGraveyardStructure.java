package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.StructureConfigEntry;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
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

import javax.annotation.Nonnull;
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

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context, int size) {
        BlockPos centerOfChunk = new BlockPos(context.chunkPos().x * 16, 0, context.chunkPos().z * 16);

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

        int i1 = generator.getFirstFreeHeight(chunkX, chunkZ, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int j1 = generator.getFirstFreeHeight(chunkX, chunkZ + offset, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int k1 = generator.getFirstFreeHeight(chunkX + offset, chunkZ, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int o1 = generator.getFirstFreeHeight(chunkX, chunkZ - offset, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int p1 = generator.getFirstFreeHeight(chunkX - offset, chunkZ, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);


        /*
        System.out.println("Terrain flatness results: ");
        System.out.println("One: " + " Height: " + i1 + " at: " + chunkX + " " + chunkZ);
        System.out.println("Two: " + " Height: " + j1 + " at: " + chunkX + " " + (chunkZ + offset));
        System.out.println("Three: " + " Height: " + k1 + " at: " + (chunkX + offset) + " " + chunkZ);
        //System.out.println("Four: " + " Height: " + l1 + " at: " + (chunkX + offset) + " " + (chunkZ + offset));
        //System.out.println("Five: " + " Height: " + m1 + " at: " + (chunkX - offset) + " " + (chunkZ + offset));
        //System.out.println("Six: " + " Height: " + n1 + " at: " + (chunkX + offset) + " " + (chunkZ - offset));
        System.out.println("Seven: " + " Height: " + o1 + " at: " + chunkX + " " + (chunkZ - offset));
        System.out.println("Eight: " + " Height: " + p1 + " at: " + (chunkX - offset) + " " + chunkZ);
        //System.out.println("Nine: " + " Height: " + q1 + " at: " + (chunkX - offset) + " " + (chunkZ - offset));

         */


        int minSides = Math.min(Math.min(j1, p1), Math.min(o1, k1));
        int minHeight = Math.min(minSides, i1);

        int maxSides = Math.max(Math.max(j1, p1), Math.max(o1, k1));
        int maxHeight = Math.max(maxSides, i1);

        return Math.abs(maxHeight - minHeight) <= 3;

    }

    protected static boolean isWater(ChunkGenerator generator, int chunkX, int chunkZ, LevelHeightAccessor heightLimitView, int size) {
        int offset = size;

        Set<Biome> biomesInAreaOne = generator.getBiomeSource().getBiomesWithin(chunkX, 0, chunkZ, size, generator.climateSampler());

        for (Biome biome : biomesInAreaOne) {
            if (biome.getBiomeCategory() == Biome.BiomeCategory.OCEAN || biome.getBiomeCategory() == Biome.BiomeCategory.RIVER || biome.getBiomeCategory() == Biome.BiomeCategory.BEACH) {
                return false;
            }
        }

        return true;

    }
}
