package com.finallion.graveyard.world.features.surfaceFeatures;


import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class FeatureHelper {


    public static boolean canBePlaced(BlockState state) {
        return state.is(Blocks.SAND) || state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.ROOTED_DIRT);
    }

    public static boolean isCorrectBiome(ResourceKey<Biome> key) {
        if (key.location().getPath().contains("haunted")) {
            return true;
        }
        return false;
    }

}
