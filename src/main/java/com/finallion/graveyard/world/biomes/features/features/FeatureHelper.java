package com.finallion.graveyard.world.biomes.features.features;

import com.finallion.graveyard.init.TGBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;

public class FeatureHelper {

    public static boolean canBePlaced(ISeedReader world, BlockPos pos, BlockState state) {
        if (state.getBlock().is(Blocks.GRASS_BLOCK)) {
            world.setBlock(pos, Blocks.DIRT.defaultBlockState(), 2);
        }

        return state.getBlock().is(TGBlocks.MOSS_BLOCK) || state.getBlock().is(Blocks.GRASS_BLOCK) || state.getBlock().is(Blocks.COARSE_DIRT) || state.getBlock().is(TGBlocks.TG_ROOTED_DIRT);
    }

    public static boolean canBePlaced(BlockState state) {
        return state.getBlock().is(TGBlocks.MOSS_BLOCK) || state.getBlock().is(Blocks.GRASS_BLOCK) || state.getBlock().is(Blocks.COARSE_DIRT) || state.getBlock().is(TGBlocks.TG_ROOTED_DIRT);
    }

    public static boolean canGenerate(ISeedReader world, BlockPos pos, int height) {
        for (int i = pos.getY(); i <= pos.getY() + height; i++) {
            if (!world.getBlockState(pos).isAir()) {
                return false;
            }
        }
        return true;

    }

}
