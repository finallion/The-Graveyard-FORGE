package com.finallion.graveyard.structures;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.init.TGConfiguredFeatures;
import com.finallion.graveyard.init.TGStructures;
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
import net.minecraft.world.gen.feature.StructureFeature;
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
    private final int MAX_WATER_HITS = 8;
    private final int MAX_TERRAIN_DIFFERENCE_BASE = 2;
    private final int MAX_TERRAIN_DIFFERENCE_LG = 4;
    private final int OFFSET_WATER_CHECK_LG = 25;

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

        if (!isWater(chunkGenerator, centerOfChunk.getX(), centerOfChunk.getZ(), centerOfChunk)) {
            return false;
        }

        if (!checkForOtherStructures(chunkGenerator, seed, chunkRandom, centerOfChunk.getX(), centerOfChunk.getZ())) {
            return false;
        }

        return true;
    }

    protected boolean isWater(ChunkGenerator generator, int chunkX, int chunkZ, BlockPos centerOfChunk) {
        // center of generation is chunkX 0 chunkZ (i)
        // checks:
        //
        // n    j    l
        // o    i    k
        // q    p    m


        int offset = (int) SIZE * 8;

        // checks are in a larger radius if the structure spawns above water
        // needed for the large graveyard to make ugly generation over rivers and in oceans less likely
        // additionally checks are for oceans
        if (SIZE > 2) {
            offset += OFFSET_WATER_CHECK_LG;

            Set<Biome> biomesInAreaOne = generator.getBiomeSource().getBiomesWithin(chunkX, 0, chunkZ, 156);
            Set<Biome> biomesInAreaTwo = generator.getBiomeSource().getBiomesWithin(chunkX, 0, chunkZ, 28);

            for (Biome biome : biomesInAreaOne) {
                if (biome.getBiomeCategory() == Biome.Category.OCEAN) {
                    return false;
                }
            }

            // clears the inner circle of generation from water sources
            // following checks clear the outer circle from possible water
            for (Biome biome : biomesInAreaTwo) {
                if (biome.getBiomeCategory() == Biome.Category.RIVER) {
                    return false;
                }
            }

        }


        int i1 = generator.getFirstOccupiedHeight(chunkX, chunkZ, Heightmap.Type.WORLD_SURFACE_WG);
        int j1 = generator.getFirstOccupiedHeight(chunkX, chunkZ + offset, Heightmap.Type.WORLD_SURFACE_WG);
        int k1 = generator.getFirstOccupiedHeight(chunkX + offset, chunkZ, Heightmap.Type.WORLD_SURFACE_WG);
        int l1 = generator.getFirstOccupiedHeight(chunkX + offset, chunkZ + offset, Heightmap.Type.WORLD_SURFACE_WG);
        int m1 = generator.getFirstOccupiedHeight(chunkX - offset, chunkZ + offset, Heightmap.Type.WORLD_SURFACE_WG);
        int n1 = generator.getFirstOccupiedHeight(chunkX + offset, chunkZ - offset, Heightmap.Type.WORLD_SURFACE_WG);
        int o1 = generator.getFirstOccupiedHeight(chunkX, chunkZ - offset, Heightmap.Type.WORLD_SURFACE_WG);
        int p1 = generator.getFirstOccupiedHeight(chunkX - offset, chunkZ, Heightmap.Type.WORLD_SURFACE_WG);
        int q1 = generator.getFirstOccupiedHeight(chunkX - offset, chunkZ - offset, Heightmap.Type.WORLD_SURFACE_WG);

        IBlockReader vi1 = generator.getBaseColumn(chunkX, chunkZ);
        IBlockReader vj1 = generator.getBaseColumn(chunkX, chunkZ + offset);
        IBlockReader vk1 = generator.getBaseColumn(chunkX + offset, chunkZ);
        IBlockReader vl1 = generator.getBaseColumn(chunkX + offset, chunkZ + offset);
        IBlockReader vm1 = generator.getBaseColumn(chunkX - offset, chunkZ + offset);
        IBlockReader vn1 = generator.getBaseColumn(chunkX + offset, chunkZ - offset);
        IBlockReader vo1 = generator.getBaseColumn(chunkX, chunkZ - offset);
        IBlockReader vp1 = generator.getBaseColumn(chunkX - offset, chunkZ);
        IBlockReader vq1 = generator.getBaseColumn(chunkX - offset, chunkZ - offset);

        BlockState bi1 = vi1.getBlockState(centerOfChunk.above(i1));
        BlockState bj1 = vj1.getBlockState(centerOfChunk.above(j1));
        BlockState bk1 = vk1.getBlockState(centerOfChunk.above(k1));
        BlockState bl1 = vl1.getBlockState(centerOfChunk.above(l1));
        BlockState bm1 = vm1.getBlockState(centerOfChunk.above(m1));
        BlockState bn1 = vn1.getBlockState(centerOfChunk.above(n1));
        BlockState bo1 = vo1.getBlockState(centerOfChunk.above(o1));
        BlockState bp1 = vp1.getBlockState(centerOfChunk.above(p1));
        BlockState bq1 = vq1.getBlockState(centerOfChunk.above(q1));

        /*
        System.out.println("Water hits results: ");
        System.out.println("One: " + bi1 + " Height: " + i1 + " at: " + chunkX + " " + chunkZ);
        System.out.println("Two: " + bj1 + " Height: " + j1 + " at: " + chunkX + " " + (chunkZ + offset));
        System.out.println("Three: " + bk1 + " Height: " + k1 + " at: " + (chunkX + offset) + " " + chunkZ);
        System.out.println("Four: " + bl1 + " Height: " + l1 + " at: " + (chunkX + offset) + " " + (chunkZ + offset));
        System.out.println("Five: " + bm1 + " Height: " + m1 + " at: " + (chunkX - offset) + " " + (chunkZ + offset));
        System.out.println("Six: " + bn1 + " Height: " + n1 + " at: " + (chunkX + offset) + " " + (chunkZ - offset));
        System.out.println("Seven: " + bo1 + " Height: " + o1 + " at: " + chunkX + " " + (chunkZ - offset));
        System.out.println("Eight: " + bp1 + " Height: " + p1 + " at: " + (chunkX - offset) + " " + chunkZ);
        System.out.println("Nine: " + bq1 + " Height: " + q1 + " at: " + (chunkX - offset) + " " + (chunkZ - offset));

         */

        return countWaterMatches(bi1, bj1, bk1, bl1, bm1, bn1, bo1, bq1, bp1);

    }

    private boolean countWaterMatches(BlockState... blockStates) {
        // counts how many blockstates are in water
        // if there are more than two or equal to four return true and allow structure to spawn
        // if more checks are added the threshold number should be raised
        int count = 0;
        for (BlockState blockState : blockStates) {
            count += (blockState.getFluidState().isEmpty() ? 1 : 0);
        }

        return count >= MAX_WATER_HITS;
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
            if (SIZE <= 2) {
                return true;
            }


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
