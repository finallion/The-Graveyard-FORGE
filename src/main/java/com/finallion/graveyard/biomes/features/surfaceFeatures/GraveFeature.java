package com.finallion.graveyard.biomes.features.surfaceFeatures;

import com.finallion.graveyard.init.TGBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class GraveFeature extends Feature<NoFeatureConfig> {

    private static WeightedBlockStateProvider graveStones = new WeightedBlockStateProvider();

    static {
        graveStones.add(TGBlocks.GRAVESTONE.defaultBlockState(), 25);
        graveStones.add(TGBlocks.COBBLESTONE_GRAVESTONE.defaultBlockState(), 25);
        graveStones.add(TGBlocks.DEEPSLATE_GRAVESTONE.defaultBlockState(), 25);
        graveStones.add(TGBlocks.MOSSY_COBBLESTONE_GRAVESTONE.defaultBlockState(), 25);
    }



    public GraveFeature(Codec<NoFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
        if (FeatureHelper.canBePlaced(world.getBlockState(pos.below())) && world.getBlockState(pos).isAir() && random.nextInt(10) == 0) {
            world.setBlock(pos, graveStones.getState(random, pos), 2);
        }


        return true;

    }

}
