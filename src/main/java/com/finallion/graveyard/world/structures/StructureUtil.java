package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.init.TGStructures;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;

import java.util.Set;

public abstract class StructureUtil {


    public static boolean checkForOtherStructures(ChunkGenerator generator, long seed, int chunkX, int chunkZ, int size) {
        StructureFeatureConfiguration village = generator.getSettings().getConfig(StructureFeature.VILLAGE);
        StructureFeatureConfiguration grave = generator.getSettings().getConfig(TGStructures.SMALL_GRAVE_STRUCTURE.get());
        StructureFeatureConfiguration graveyard = generator.getSettings().getConfig(TGStructures.SMALL_GRAVEYARD_STRUCTURE.get());
        StructureFeatureConfiguration tree = generator.getSettings().getConfig(TGStructures.MEMORIAL_TREE_STRUCTURE.get());
        StructureFeatureConfiguration m_graveyard = generator.getSettings().getConfig(TGStructures.MEDIUM_GRAVEYARD_STRUCTURE.get());
        StructureFeatureConfiguration house = generator.getSettings().getConfig(TGStructures.HAUNTED_HOUSE_STRUCTURE.get());
        StructureFeatureConfiguration mushroom = generator.getSettings().getConfig(TGStructures.MUSHROOM_GRAVE_STRUCTURE.get());



        for (int k = chunkX - 5; k <= chunkX + 5; ++k) {
            for (int m = chunkZ - 5; m <= chunkZ + 5; ++m) {
                if (village != null) {
                    ChunkPos possibleVillagePos = StructureFeature.VILLAGE.getPotentialFeatureChunk(village, seed, k, m);
                    if (k == possibleVillagePos.x && m == possibleVillagePos.z) {
                        return false;
                    }
                }

                if (graveyard != null) {
                    ChunkPos possibleVillagePos = TGStructures.SMALL_GRAVEYARD_STRUCTURE.get().getPotentialFeatureChunk(grave, seed, k, m);
                    if (k == possibleVillagePos.x && m == possibleVillagePos.z) {
                        return false;
                    }
                }
                /*
                if (tree != null) {
                    ChunkPos possibleVillagePos = TGStructures.MEMORIAL_TREE_STRUCTURE.getPotentialFeatureChunk(tree, seed, k, m);
                    if (k == possibleVillagePos.x && m == possibleVillagePos.z) {
                        return false;
                    }
                }

                if (m_graveyard != null) {
                    ChunkPos possibleVillagePos = TGStructures.MEDIUM_GRAVEYARD_STRUCTURE.getPotentialFeatureChunk(m_graveyard, seed, k, m);
                    if (k == possibleVillagePos.x && m == possibleVillagePos.z) {
                        return false;
                    }
                }

                if (mushroom != null) {
                    ChunkPos possibleVillagePos = TGStructures.MUSHROOM_GRAVE_STRUCTURE.getPotentialFeatureChunk(mushroom, seed, k, m);
                    if (k == possibleVillagePos.x && m == possibleVillagePos.z) {
                        return false;
                    }
                }

                if (house != null) {
                    ChunkPos possibleVillagePos = TGStructures.HAUNTED_HOUSE_STRUCTURE.getPotentialFeatureChunk(house, seed, k, m);
                    if (k == possibleVillagePos.x && m == possibleVillagePos.z) {
                        return false;
                    }
                }

                 */
            }
        }


        return true;
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
