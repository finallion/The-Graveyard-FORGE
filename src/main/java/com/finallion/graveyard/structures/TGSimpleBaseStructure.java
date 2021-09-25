package com.finallion.graveyard.structures;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.init.TGConfiguredFeatures;
import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import org.lwjgl.system.CallbackI;

public class TGSimpleBaseStructure extends Structure<NoFeatureConfig> {
    private final int SUNKEN_IN;
    private final double SIZE;
    private final String NAME;
    private int averageHeight;

    public TGSimpleBaseStructure(Codec<NoFeatureConfig> codec, String name, double size, int sunkenIn) {
        super(codec);
        this.SUNKEN_IN = sunkenIn;
        this.SIZE = size;
        this.NAME = name;

    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {

        if (!checkForOtherStructures(chunkGenerator, seed, chunkRandom, chunkX, chunkZ)) {
            return false;
        }

        return true;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    @Override
    public GenerationStage.Decoration step() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }





    public boolean checkForOtherStructures(ChunkGenerator chunkGenerator, long worldSeed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ) {

        /*
        //cannot be near other specified structure
        StructureSeparationSettings village = chunkGenerator.getSettings().getConfig(Structure.VILLAGE);
        StructureSeparationSettings small_graveyard = chunkGenerator.getSettings().getConfig(TGConfiguredFeatures.CONFIGURED_SMALL_WALLED_GRAVEYARD.feature);
        StructureSeparationSettings medium_graveyard = chunkGenerator.getSettings().getConfig(TGConfiguredFeatures.CONFIGURED_MEDIUM_WALLED_GRAVEYARD.feature);
        StructureSeparationSettings large_graveyard = chunkGenerator.getSettings().getConfig(TGConfiguredFeatures.CONFIGURED_LARGE_WALLED_GRAVEYARD.feature);
        StructureSeparationSettings mushroom_grave = chunkGenerator.getSettings().getConfig(TGConfiguredFeatures.CONFIGURED_MUSHROOM_GRAVE.feature);
        StructureSeparationSettings small_grave = chunkGenerator.getSettings().getConfig(TGConfiguredFeatures.CONFIGURED_SMALL_GRAVE.feature);
        StructureSeparationSettings large_birch_tree = chunkGenerator.getSettings().getConfig(TGConfiguredFeatures.CONFIGURED_LARGE_BIRCH_TREE.feature);

        for (int k = chunkX - 3; k <= chunkX + 3; ++k) {
            for (int m = chunkZ - 3; m <= chunkZ + 3; ++m) {
                if (village != null) {
                    ChunkPos possibleVillagePos = Structure.VILLAGE.getPotentialFeatureChunk(village, worldSeed, chunkRandom, k, m);
                    if (k == possibleVillagePos.x && m == possibleVillagePos.z) {
                        return false;
                    }
                }
                if (small_graveyard != null) {
                    ChunkPos posSmallGraveyard = TGConfiguredFeatures.CONFIGURED_SMALL_WALLED_GRAVEYARD.feature.getPotentialFeatureChunk(small_graveyard, worldSeed, chunkRandom, k, m);
                    if (k == posSmallGraveyard.x && m == posSmallGraveyard.z && !(this instanceof SmallWalledGraveyard)) {
                        return false;
                    }
                }
                if (medium_graveyard != null) {
                    ChunkPos posMediumGraveyard = TGConfiguredFeatures.CONFIGURED_MEDIUM_WALLED_GRAVEYARD.feature.getPotentialFeatureChunk(medium_graveyard, worldSeed, chunkRandom, k, m);
                    if (k == posMediumGraveyard.x && m == posMediumGraveyard.z && !(this instanceof MediumWalledGraveyard)) {
                        return false;
                    }
                }
                if (large_graveyard != null) {
                    ChunkPos posLargeGraveyard = TGConfiguredFeatures.CONFIGURED_LARGE_WALLED_GRAVEYARD.feature.getPotentialFeatureChunk(large_graveyard, worldSeed, chunkRandom, k, m);
                    if (k == posLargeGraveyard.x && m == posLargeGraveyard.z && !(this instanceof LargeWalledGraveyard)) {
                        return false;
                    }
                }
                if (mushroom_grave != null) {
                    ChunkPos posMushroom = TGConfiguredFeatures.CONFIGURED_MUSHROOM_GRAVE.feature.getPotentialFeatureChunk(mushroom_grave, worldSeed, chunkRandom, k, m);
                    if (k == posMushroom.x && m == posMushroom.z && !(this instanceof MushroomGrave)) {
                        return false;
                    }
                }
                if (small_grave != null) {
                    ChunkPos posGrave = TGConfiguredFeatures.CONFIGURED_SMALL_GRAVE.feature.getPotentialFeatureChunk(small_grave, worldSeed, chunkRandom, k, m);
                    if (k == posGrave.x && m == posGrave.z && !(this instanceof SmallGrave)) {
                        return false;
                    }
                }
                if (large_birch_tree != null) {
                    ChunkPos posTree = TGConfiguredFeatures.CONFIGURED_LARGE_BIRCH_TREE.feature.getPotentialFeatureChunk(large_birch_tree, worldSeed, chunkRandom, k, m);
                    if (k == posTree.x && m == posTree.z && !(this instanceof LargeBirchTree)) {
                        return false;
                    }
                }
            }
        }


         */


        return true;

    }



    private int getSunkenIn() {
        return SUNKEN_IN;
    }

    private String getStructureName() {
        return NAME;
    }

    private int getAverageHeight() {
        return averageHeight;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {
        private final int SUNKEN_IN;
        private final String NAME;
        private int averageHeight;


        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox blockBox, int referenceIn, long seedIn, String name, int sunkenIn, int averageHeight) {
            super(structureIn, chunkX, chunkZ, blockBox, referenceIn, seedIn);
            this.SUNKEN_IN = sunkenIn;
            this.NAME = name;
            this.averageHeight = averageHeight;

        }


        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox blockBox, int referenceIn, long seed) {
            this(structureIn, chunkX, chunkZ, blockBox, referenceIn, seed, ((TGSimpleBaseStructure) structureIn).getStructureName(), ((TGSimpleBaseStructure) structureIn).getSunkenIn(), ((TGSimpleBaseStructure) structureIn).getAverageHeight());

        }


        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {
            BlockPos pos = new BlockPos(chunkX << 4, 0, chunkZ << 4);

            JigsawManager.addPieces(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
                            .get(new ResourceLocation(TheGraveyard.MOD_ID, NAME + "/start_pool")),
                            10),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    templateManagerIn,
                    pos,
                    this.pieces,
                    this.random,
                    false,
                    true);


            this.pieces.forEach(piece -> piece.move(0, 1, 0));
            //this.pieces.forEach(piece -> piece.getBoundingBox().y0 -= 1);
            this.pieces.forEach(piece -> piece.getBoundingBox().move(0, SUNKEN_IN, 0));


            this.calculateBoundingBox();

        }

    }


}
