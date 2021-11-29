package com.finallion.graveyard.structures;

import com.finallion.graveyard.TheGraveyard;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

import java.util.Set;

public class TGBaseStructure extends Structure<NoFeatureConfig> {
    private final int SUNKEN_IN;
    private final double SIZE;
    private final String NAME;
    private int averageHeight;
    private final int MAX_TERRAIN_DIFFERENCE_BASE = 2;
    private final int MAX_TERRAIN_DIFFERENCE_LG = 4;

    public TGBaseStructure(Codec<NoFeatureConfig> codec, String name, double size, int sunkenIn) {
        super(codec);
        this.SUNKEN_IN = sunkenIn;
        this.SIZE = size;
        this.NAME = name;
    }

    @Override
    public  IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    @Override
    public GenerationStage.Decoration step() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }



    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        BlockPos centerOfChunk = new BlockPos(chunkPos.x * 16, 0, chunkPos.z * 16);


        if (!isTerrainFlat(chunkGenerator, centerOfChunk.getX(), centerOfChunk.getZ())) {
            return false;
        }

        if (!isWater(chunkGenerator, centerOfChunk.getX(), centerOfChunk.getZ())) {
            return false;
        }

        if (SIZE > 2) {
            if (!checkForOtherStructures(chunkGenerator, seed, chunkRandom, centerOfChunk.getX(), centerOfChunk.getZ())) {
                return false;
            }
        }

        return true;
    }

    protected boolean isWater(ChunkGenerator generator, int chunkX, int chunkZ) {
        int offset = (int) SIZE * 15;

        Set<Biome> biomesInArea = generator.getBiomeSource().getBiomesWithin(chunkX, 0, chunkZ, offset);

        for (Biome biome : biomesInArea) {
            if (biome.getBiomeCategory() == Biome.Category.OCEAN || biome.getBiomeCategory() == Biome.Category.RIVER) {
                return false;
            }
        }

        return true;
    }


    protected boolean isTerrainFlat(ChunkGenerator generator, int chunkX, int chunkZ) {
        // center of generation is chunkX 0 chunkZ (i)
        // checks:
        //
        // n    j    l
        // o    i    k
        // q    p    m

        int offset = (int) SIZE * 8;

        int i1 = generator.getFirstFreeHeight(chunkX, chunkZ, Heightmap.Type.WORLD_SURFACE_WG);
        int j1 = generator.getFirstFreeHeight(chunkX, chunkZ + offset, Heightmap.Type.WORLD_SURFACE_WG);
        int k1 = generator.getFirstFreeHeight(chunkX + offset, chunkZ, Heightmap.Type.WORLD_SURFACE_WG);
        int l1 = generator.getFirstFreeHeight(chunkX + offset, chunkZ + offset, Heightmap.Type.WORLD_SURFACE_WG);
        int m1 = generator.getFirstFreeHeight(chunkX - offset, chunkZ + offset, Heightmap.Type.WORLD_SURFACE_WG);
        int n1 = generator.getFirstFreeHeight(chunkX + offset, chunkZ - offset, Heightmap.Type.WORLD_SURFACE_WG);
        int o1 = generator.getFirstFreeHeight(chunkX, chunkZ - offset, Heightmap.Type.WORLD_SURFACE_WG);
        int p1 = generator.getFirstFreeHeight(chunkX - offset, chunkZ, Heightmap.Type.WORLD_SURFACE_WG);
        int q1 = generator.getFirstFreeHeight(chunkX - offset, chunkZ - offset, Heightmap.Type.WORLD_SURFACE_WG);

        /*
        System.out.println("Terrain flatness results: ");
        System.out.println("One: " + " Height: " + i1 + " at: " + chunkX + " " + chunkZ);
        System.out.println("Two: " + " Height: " + j1 + " at: " + chunkX + " " + (chunkZ + offset));
        System.out.println("Three: " + " Height: " + k1 + " at: " + (chunkX + offset) + " " + chunkZ);
        System.out.println("Four: " + " Height: " + l1 + " at: " + (chunkX + offset) + " " + (chunkZ + offset));
        System.out.println("Five: " + " Height: " + m1 + " at: " + (chunkX - offset) + " " + (chunkZ + offset));
        System.out.println("Six: " + " Height: " + n1 + " at: " + (chunkX + offset) + " " + (chunkZ - offset));
        System.out.println("Seven: " + " Height: " + o1 + " at: " + chunkX + " " + (chunkZ - offset));
        System.out.println("Eight: " + " Height: " + p1 + " at: " + (chunkX - offset) + " " + chunkZ);
        System.out.println("Nine: " + " Height: " + q1 + " at: " + (chunkX - offset) + " " + (chunkZ - offset));
         */

        int minCorners = Math.min(Math.min(n1, m1), Math.min(q1, l1));
        int minSides = Math.min(Math.min(j1, p1), Math.min(o1, k1));
        int minHeight = Math.min(Math.min(minCorners, minSides), i1);

        int maxCorners = Math.max(Math.max(n1, m1), Math.max(q1, l1));
        int maxSides = Math.max(Math.max(j1, p1), Math.max(o1, k1));
        int maxHeight = Math.max(Math.max(maxCorners, maxSides), i1);

        averageHeight = Math.abs((maxHeight + minHeight) / 2) - 1;

        if (SIZE > 2) {
            return Math.abs(maxHeight - minHeight) <= MAX_TERRAIN_DIFFERENCE_LG;
        }

        return Math.abs(maxHeight - minHeight) <= MAX_TERRAIN_DIFFERENCE_BASE;
    }


    public boolean checkForOtherStructures(ChunkGenerator generator, long seed, SharedSeedRandom random, int chunkX, int chunkZ) {
        StructureSeparationSettings structureConfig = generator.getSettings().getConfig(Structure.VILLAGE);

        if (structureConfig == null) {
            return false;
        } else {
            int blocksAwayToCheck = 15;
            for (int k = chunkX - blocksAwayToCheck; k <= chunkX + blocksAwayToCheck; ++k) {
                for (int l = chunkZ - blocksAwayToCheck; l <= chunkZ + blocksAwayToCheck; ++l) {
                    ChunkPos chunkPos = Structure.VILLAGE.getPotentialFeatureChunk(structureConfig, seed, random, k, l);
                    if (k == chunkPos.x && l == chunkPos.z) {
                        return false;
                    }
                }
            }
            return true;
        }


    }

    private int getSunkenIn() {
        return SUNKEN_IN;
    }

    private double getSize() {
        return SIZE;
    }

    private String getStructureName() {
        return NAME;
    }

    private int getAverageHeight() {
        return averageHeight;
    }


    public static class Start extends StructureStart<NoFeatureConfig> {
        private final int SUNKEN_IN;
        private final double SIZE;
        private final String NAME;
        private int averageHeight;


        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox blockBox, int referenceIn, long seedIn, String name, double size, int sunkenIn, int averageHeight) {
            super(structureIn, chunkX, chunkZ, blockBox, referenceIn, seedIn);
            this.SIZE = size;
            this.SUNKEN_IN = sunkenIn;
            this.NAME = name;
            this.averageHeight = averageHeight;

        }


        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox blockBox, int referenceIn, long seed) {
            this(structureIn, chunkX, chunkZ, blockBox, referenceIn, seed, ((TGBaseStructure) structureIn).getStructureName(), ((TGBaseStructure) structureIn).getSize(), ((TGBaseStructure) structureIn).getSunkenIn(), ((TGBaseStructure) structureIn).getAverageHeight());

        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {
            int x = chunkX * 16;
            int z = chunkZ * 16;

            BlockPos.Mutable centerPos = new BlockPos.Mutable(x, averageHeight, z);

            JigsawManager.addPieces(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
                            .get(new ResourceLocation(TheGraveyard.MOD_ID, NAME + "/start_pool")),
                            10),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    templateManagerIn,
                    centerPos,
                    this.pieces,
                    this.random,
                    false,
                    false);


            this.pieces.forEach(piece -> piece.move(0, 1, 0));
            this.pieces.forEach(piece -> piece.getBoundingBox().move(0, SUNKEN_IN, 0));
            //this.pieces.forEach(piece -> piece.getBoundingBox().move(0, SUNKEN_IN, 0));



            Vector3i structureCenter = this.pieces.get(0).getBoundingBox().getCenter();
            int xOffset = centerPos.getX() - structureCenter.getX();
            int zOffset = centerPos.getZ() - structureCenter.getZ();
            for(StructurePiece structurePiece : this.pieces){
                structurePiece.move(xOffset, 0, zOffset);
            }



            this.calculateBoundingBox();

        }

    }
}
