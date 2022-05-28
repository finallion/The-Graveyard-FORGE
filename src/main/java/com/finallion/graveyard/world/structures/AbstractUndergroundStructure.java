package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.StructureConfigEntry;
import com.finallion.graveyard.util.BiomeCheckUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public abstract class AbstractUndergroundStructure extends StructureFeature<JigsawConfiguration> {
    private final StructureConfigEntry config;
    private String structureName;

    public AbstractUndergroundStructure(StructureConfigEntry config, String name) {
        super(JigsawConfiguration.CODEC, (context -> AbstractUndergroundStructure.createPiecesGenerator(context, config, name)), PostPlacementProcessor.NONE);
        this.config = config;
        this.structureName = name;
    }

    public StructureConfigEntry getStructureConfigEntry() {
        return config;
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.UNDERGROUND_STRUCTURES;
    }

    public abstract ConfiguredStructureFeature<?, ?> getStructureFeature();

    public String getStructureName() {
        return structureName;
    }


    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context, StructureConfigEntry config, String name) {
        if (!isCorrectBiome(context, config, name)) {
            return false;
        }

        return true;
    }

    public static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context, StructureConfigEntry config, String name) {
        int minHeight = -40; // default: -40
        int maxHeight = -10; // default: -10


        if (!AbstractUndergroundStructure.isFeatureChunk(context, config, name)) {
            return Optional.empty();
        }

        ChunkPos chunkPos = context.chunkPos();
        Random random = new Random();

        int x = random.nextInt(chunkPos.getMaxBlockX() - chunkPos.getMiddleBlockX()) + chunkPos.getMinBlockX();
        int z = random.nextInt(chunkPos.getMaxBlockZ() - chunkPos.getMiddleBlockZ()) + chunkPos.getMinBlockZ();
        int y = random.nextInt(maxHeight - minHeight) + minHeight;

        Optional<PieceGenerator<JigsawConfiguration>> structurePiecesGenerator =
                JigsawPlacement.m_210284_(
                        context,
                        PoolElementStructurePiece::new,
                        new BlockPos(x, y, z),
                        false,
                        false
                );


        return structurePiecesGenerator;
    }


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
