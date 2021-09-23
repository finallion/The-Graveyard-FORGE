package com.finallion.graveyard.utils;

import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class TGBiomeKeys {

    public static List<RegistryKey<Biome>> birch_biomes = new ArrayList<>();
    public static List<RegistryKey<Biome>> forest_biomes = new ArrayList<>();
    public static List<RegistryKey<Biome>> thick_forest_biomes = new ArrayList<>();

    public static void init() {
        // villages generate in plains, savanna, desert, taiga and snowy tundra

        birch_biomes.add(Biomes.BIRCH_FOREST_HILLS);
        birch_biomes.add(Biomes.TALL_BIRCH_HILLS);
        birch_biomes.add(Biomes.BIRCH_FOREST);
        birch_biomes.add(Biomes.TALL_BIRCH_FOREST);

        forest_biomes.add(Biomes.DARK_FOREST_HILLS);
        forest_biomes.add(Biomes.WOODED_HILLS);
        forest_biomes.add(Biomes.WOODED_MOUNTAINS);
        forest_biomes.add(Biomes.GIANT_SPRUCE_TAIGA);
        forest_biomes.add(Biomes.GIANT_SPRUCE_TAIGA_HILLS);
        forest_biomes.add(Biomes.GIANT_TREE_TAIGA);
        forest_biomes.add(Biomes.GIANT_TREE_TAIGA_HILLS);
        forest_biomes.add(Biomes.TAIGA_MOUNTAINS);
        forest_biomes.add(Biomes.FLOWER_FOREST);

        thick_forest_biomes.add(Biomes.TAIGA_HILLS);
        thick_forest_biomes.add(Biomes.SNOWY_TAIGA_HILLS); //
        thick_forest_biomes.add(Biomes.SNOWY_TAIGA_MOUNTAINS); //
        thick_forest_biomes.add(Biomes.DARK_FOREST);
        thick_forest_biomes.add(Biomes.TAIGA);
        thick_forest_biomes.add(Biomes.SNOWY_TAIGA); //
        thick_forest_biomes.add(Biomes.JUNGLE);
        thick_forest_biomes.add(Biomes.FOREST);


    }


}
