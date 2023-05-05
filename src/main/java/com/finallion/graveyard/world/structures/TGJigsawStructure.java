package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.config.GraveyardConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.material.Fluids;

import java.util.Optional;
import java.util.Random;

public class TGJigsawStructure extends StructureFeature<TGJigsawConfig> {
    private String name;

    public TGJigsawStructure(Codec<TGJigsawConfig> codec, String name) {
        super(codec, (context -> TGJigsawStructure.createPiecesGenerator(context, name)), PostPlacementProcessor.NONE);
        this.name = name;
    }

    @Override
    public GenerationStep.Decoration step() {
        if (name.equals("crypt")) {
            return GenerationStep.Decoration.UNDERGROUND_STRUCTURES;
        }
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }


    public static Optional<PieceGenerator<TGJigsawConfig>> createPiecesGenerator(PieceGeneratorSupplier.Context<TGJigsawConfig> context, String structureName) {
        if (!canGenerate(structureName)) {
            return Optional.empty();
        }

        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);
        boolean generatesAtWorldSurface = true;

        if (structureName.equals("crypt")) {
            int minHeight = -40; // default: -40
            int maxHeight = -10; // default: -10

            ChunkPos chunkPos = context.chunkPos();
            Random random = new Random();

            int x = random.nextInt(chunkPos.getMaxBlockX() - chunkPos.getMinBlockX()) + chunkPos.getMinBlockX();
            int z = random.nextInt(chunkPos.getMaxBlockZ() - chunkPos.getMiddleBlockZ()) + chunkPos.getMiddleBlockZ();
            int y = random.nextInt(maxHeight - minHeight) + minHeight;
            blockpos = new BlockPos(x, y, z);
            generatesAtWorldSurface = false;

            if (!TGJigsawStructure.canGenerateUnderground(context)) {
                return Optional.empty();
            }
        } else if (structureName.equals("lich_prison")) {
            ChunkPos chunkPos = context.chunkPos();
            Random random = new Random();

            int x = random.nextInt(chunkPos.getMaxBlockX() - chunkPos.getMinBlockX()) + chunkPos.getMinBlockX();
            int z = random.nextInt(chunkPos.getMaxBlockZ() - chunkPos.getMiddleBlockZ()) + chunkPos.getMiddleBlockZ();
            int y = 210;
            blockpos = new BlockPos(x, y, z);
            generatesAtWorldSurface = false;

            if (!TGJigsawStructure.canGenerateInTheAir(context)) {
                return Optional.empty();
            }
        } else {
            if (!TGJigsawStructure.canGenerate(context, context.config().terrainCheckSize, blockpos, context.config().maxHeightDifference)) {
                return Optional.empty();
            }
        }


        Optional<PieceGenerator<TGJigsawConfig>> structurePiecesGenerator =
                TGJigsawPlacement.m_210284_(
                        context,
                        PoolElementStructurePiece::new,
                        blockpos,
                        false,
                        generatesAtWorldSurface
                );


        return structurePiecesGenerator;
    }


    private static boolean canGenerateUnderground(PieceGeneratorSupplier.Context<TGJigsawConfig> context) {
        return true;
    }

    private static boolean canGenerateInTheAir(PieceGeneratorSupplier.Context<TGJigsawConfig> context) {
        return true;
    }


    private static boolean canGenerate(PieceGeneratorSupplier.Context<TGJigsawConfig> context, int size, BlockPos centerOfChunk, int maxHeightDifference) {
        if (!isTerrainFlat(context, centerOfChunk, size, maxHeightDifference)) {
            return false;
        }

        return true;
    }


    protected static boolean isTerrainFlat(PieceGeneratorSupplier.Context<TGJigsawConfig> context, BlockPos centerChunk, int size, int maxHeightDifference) {
        ChunkGenerator generator = context.chunkGenerator();
        LevelHeightAccessor heightLimitView = context.heightAccessor();
        int chunkX = centerChunk.getX();
        int chunkZ = centerChunk.getZ();

        int i1 = generator.getFirstOccupiedHeight(chunkX, chunkZ, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int j1 = generator.getFirstOccupiedHeight(chunkX, chunkZ + size, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int k1 = generator.getFirstOccupiedHeight(chunkX + size, chunkZ, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int o1 = generator.getFirstOccupiedHeight(chunkX, chunkZ - size, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int p1 = generator.getFirstOccupiedHeight(chunkX - size, chunkZ, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);

        NoiseColumn sample1 = generator.getBaseColumn(chunkX, chunkZ, heightLimitView);
        NoiseColumn sample2 = generator.getBaseColumn(chunkX, chunkZ + size, heightLimitView);
        NoiseColumn sample3 = generator.getBaseColumn(chunkX + size, chunkZ, heightLimitView);
        NoiseColumn sample4 = generator.getBaseColumn(chunkX, chunkZ - size, heightLimitView);
        NoiseColumn sample5 = generator.getBaseColumn(chunkX - size, chunkZ, heightLimitView);

        // subtract -1 if getHeightOnGround
        if (sample1.getBlock(i1).getFluidState().is(Fluids.WATER) || sample2.getBlock(j1).getFluidState().is(Fluids.WATER) || sample3.getBlock(k1).getFluidState().is(Fluids.WATER) || sample4.getBlock(o1).getFluidState().is(Fluids.WATER) || sample5.getBlock(p1).getFluidState().is(Fluids.WATER)) {
            return false;
        }

        int minSides = Math.min(Math.min(j1, p1), Math.min(o1, k1));
        int minHeight = Math.min(minSides, i1);

        int maxSides = Math.max(Math.max(j1, p1), Math.max(o1, k1));
        int maxHeight = Math.max(maxSides, i1);

        return Math.abs(maxHeight - minHeight) <= maxHeightDifference;

    }

    /*
    public StructureType<?> type() {
        return TGStructureType.TG_JIGSAW;
    }

     */

    private static boolean canGenerate(String name) {
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
            case "dead_tree" -> GraveyardConfig.COMMON.canGenerateDeadTree.get();
            default -> false;
        };
    }
}