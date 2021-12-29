package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.init.TGProcessors;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.JigsawFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.Optional;

public class HauntedHouseStructure extends StructureFeature<JigsawConfiguration> {
    private static final int SIZE = 30;

    public HauntedHouseStructure(Codec<JigsawConfiguration> codec) {
        super(codec, (context) -> {
            if (!HauntedHouseStructure.isFeatureChunk(context)) {
                return Optional.empty();
            } else {
                return HauntedHouseStructure.createPiecesGenerator(context);
            }
        });
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        BlockPos centerOfChunk = new BlockPos(context.chunkPos().x * 16, 0, context.chunkPos().z * 16);

        if (!StructureUtil.isTerrainFlat(context.chunkGenerator(), centerOfChunk.getX(), centerOfChunk.getZ(), context.heightAccessor(), SIZE)) {
            return false;
        }

        if (!StructureUtil.isWater(context.chunkGenerator(), centerOfChunk.getX(), centerOfChunk.getZ(), context.heightAccessor(), SIZE)) {
            return false;
        }

        /*
        if (!StructureUtil.checkForOtherStructures(context.chunkGenerator(), context.seed(), centerOfChunk.getX(), centerOfChunk.getZ(), SIZE)) {
            return false;
        }

         */

        return true;
    }

    public static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);

        JigsawConfiguration newConfig = new JigsawConfiguration(() -> HauntedHouseGenerator.STARTING_POOL, 10);

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

    public static class HauntedHouseGenerator {
        public static final StructureTemplatePool STARTING_POOL;
        public static final StructureTemplatePool DOWNSTAIRS;

        public HauntedHouseGenerator() {
        }

        public static void init() {
        }

        // change from legacy single to processed single
        // if water is removed from blockentities (like chests) it might give the error message "Tried access block entity before it was created"
        static {
            STARTING_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "haunted_house/start_pool"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                    StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":haunted_house/start_pool/haunted_house_01"), 1)), StructureTemplatePool.Projection.RIGID));
            DOWNSTAIRS = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "haunted_house/downstairs_pool"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                    StructurePoolElement.single(TheGraveyard.MOD_ID + ":haunted_house/downstairs_pool/haunted_house_downstairs", TGProcessors.WATERLOGGED_LIST), 1)), StructureTemplatePool.Projection.RIGID));


        }
    }

}
