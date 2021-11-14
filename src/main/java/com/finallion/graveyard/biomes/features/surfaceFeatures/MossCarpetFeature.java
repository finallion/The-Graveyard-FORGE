package com.finallion.graveyard.biomes.features.surfaceFeatures;

import com.finallion.graveyard.init.TGBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class MossCarpetFeature extends Feature<NoFeatureConfig> {


    public MossCarpetFeature(Codec<NoFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(pos);

        // cap at height 85
        for (int i = 64; i < 85; i++) {
            mutable.set(pos);
            mutable.move(random.nextInt(10) - random.nextInt(10), 0, random.nextInt(10) - random.nextInt(10));
            mutable.setY(i);
            if (world.getBlockState(mutable).getBlock() instanceof LeavesBlock && world.getBlockState(mutable.above()).isAir()) {
                world.setBlock(mutable.above(), TGBlocks.MOSS_CARPET.defaultBlockState(), 2);
                break;

            } else if (world.getBlockState(mutable).getBlock().is(TGBlocks.TG_MOSS_BLOCK) && world.getBlockState(mutable.above()).isAir()) {
                if (random.nextInt(5) == 0) {
                    world.setBlock(mutable.above(), TGBlocks.MOSS_CARPET.defaultBlockState(), 2);
                    break;
                }
            }

        }

        return true;

    }

}
