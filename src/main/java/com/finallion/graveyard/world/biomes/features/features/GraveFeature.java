package com.finallion.graveyard.world.biomes.features.features;

import com.finallion.graveyard.init.TGBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class GraveFeature extends Feature<NoFeatureConfig> {

    public GraveFeature(Codec<NoFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {


        if (FeatureHelper.canBePlaced(world.getBlockState(pos.below())) && world.getBlockState(pos).isAir() && random.nextInt(10) == 0) {
            switch (random.nextInt(4)) {
                case 1: world.setBlock(pos, TGBlocks.COBBLESTONE_GRAVESTONE.defaultBlockState(), 2); break;
                case 2: world.setBlock(pos, TGBlocks.MOSSY_COBBLESTONE_GRAVESTONE.defaultBlockState(), 2); break;
                case 3: world.setBlock(pos, TGBlocks.DEEPSLATE_GRAVESTONE.defaultBlockState(), 2); break;
                default: world.setBlock(pos, TGBlocks.GRAVESTONE.defaultBlockState(), 2); break;
            }
        }


        return true;

    }

}
