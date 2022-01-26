package com.finallion.graveyard.world.features.surfaceFeatures;


import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class FeatureHelper {

    public static boolean canBePlaced(WorldGenLevel world, BlockPos pos, BlockState state) {
        if (state.getBlock() == (Blocks.GRASS_BLOCK)) {
            world.setBlock(pos, Blocks.DIRT.defaultBlockState(), 2);
        }

        return state.getBlock() == (Blocks.MOSS_BLOCK) || state.getBlock() == (Blocks.GRASS_BLOCK) || state.getBlock() == (Blocks.COARSE_DIRT) || state.getBlock() == (Blocks.ROOTED_DIRT);
    }

    public static boolean canBePlaced(BlockState state) {
        return state.getBlock() == (Blocks.MOSS_BLOCK) || state.getBlock() == (Blocks.GRASS_BLOCK) || state.getBlock() == (Blocks.COARSE_DIRT) || state.getBlock() == (Blocks.ROOTED_DIRT);
    }

    public static boolean canGenerate(WorldGenLevel world, BlockPos pos, int height) {
        for (int i = pos.getY(); i <= pos.getY() + height; i++) {
            if (!world.getBlockState(pos).isAir()) {
                return false;
            }
        }
        return true;

    }

    public static boolean isCorrectBiome(ResourceKey<Biome> key) {
        if (key.getRegistryName().getPath().contains("haunted")) {
            return true;
        }
        return false;
    }

}
