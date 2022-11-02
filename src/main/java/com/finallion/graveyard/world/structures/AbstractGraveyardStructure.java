package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.config.StructureConfigEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Biome;
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
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.BiomeDictionary;

import java.util.List;
import java.util.Optional;

public abstract class AbstractGraveyardStructure extends StructureFeature<JigsawConfiguration> {
    private final StructureConfigEntry structureConfigEntry;
    private String structureName;

    public AbstractGraveyardStructure(StructureConfigEntry config, String name) {
        super(JigsawConfiguration.CODEC, (context -> AbstractGraveyardStructure.createPiecesGenerator(context, config, name)), PostPlacementProcessor.NONE);
        this.structureConfigEntry = config;
        this.structureName = name;
    }


    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context, StructureConfigEntry config, String name) {
        BlockPos centerOfChunk = context.chunkPos().getMiddleBlockPosition(0);

        if (!isCorrectBiome(context, config, name)) {
            return false;
        }


        if (!isTerrainFlat(context.chunkGenerator(), centerOfChunk.getX(), centerOfChunk.getZ(), context.heightAccessor(), config.terrainCheckRadius.get(), config.maxTerrainHeightDifference.get())) {
            return false;
        }

        //if (!isWater(context.chunkGenerator(), centerOfChunk.getX(), centerOfChunk.getZ(), context.heightAccessor(), size)) {
        //    return false;
        //}


        return true;
    }

    public static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context, StructureConfigEntry config, String name) {
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);

        if (!AbstractGraveyardStructure.isFeatureChunk(context, config, name)) {
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

    protected static boolean isTerrainFlat(ChunkGenerator generator, int chunkX, int chunkZ, LevelHeightAccessor heightLimitView, int size, int maxHeightDifference) {
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

        return Math.abs(maxHeight - minHeight) <= maxHeightDifference;
    }


    protected static boolean isCorrectBiome(PieceGeneratorSupplier.Context<JigsawConfiguration> context, StructureConfigEntry config, String name) {
        if (config.canGenerate.get() && parseWhitelistedMods(context, config.biomeWhitelist.get(), config.biomeBlacklist.get())) {
            return true;
        }
        return false;
    }


    public static boolean parseWhitelistedMods(PieceGeneratorSupplier.Context<JigsawConfiguration> context, List<? extends  String> whitelist, List<? extends  String> blacklist) {
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);

        Holder<Biome> biome = context.chunkGenerator().m_203495_(QuartPos.fromBlock(blockpos.getX()), QuartPos.fromBlock(blockpos.getY()), QuartPos.fromBlock(blockpos.getZ()));

        String biomeName = biome.m_203543_().orElseThrow().location().toString();

        if (blacklist.contains(biomeName)) {
            return false;
        }

        for (String biomeInList : whitelist) {
            if (biomeInList.startsWith("#")) { // check if biome is in tag
                String[] parts = biomeInList.substring(1).split(":");
                TagKey<Biome> tag = TagKey.m_203882_(Registry.BIOME_REGISTRY, new ResourceLocation(parts[0], parts[1]));
                Registry<Biome> registry = context.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY);

                // vanilla tags
                if (registry.m_203658_(tag)) { // registry has tag
                    if (registry.m_203431_(tag).orElseThrow().m_203333_(biome)) { // tag contains biome
                        return true;
                    }
                }

                // forge tags
                if (BiomeDictionary.Type.hasType(parts[1])) {   // if the tag from the whitelist exists
                    for (BiomeDictionary.Type type : BiomeDictionary.Type.getAll()) {
                        if (type.getName().equalsIgnoreCase(parts[1]) && biome.m_203543_().isPresent()) { // find the biomes from the tag
                            if (BiomeDictionary.getBiomes(type).contains(biome.m_203543_().get())) { // check if tag contains the biome, the structure placement is looking at!
                                return true;
                            }
                        }
                    }
                }

            } else if (whitelist.contains(biomeName)) { // check if biome is on whitelist
                return true;
            }
        }
        return false;
    }




}
