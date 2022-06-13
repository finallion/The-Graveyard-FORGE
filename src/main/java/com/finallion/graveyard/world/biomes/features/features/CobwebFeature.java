package com.finallion.graveyard.world.biomes.features.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class CobwebFeature extends Feature<NoFeatureConfig> {


    public CobwebFeature(Codec<NoFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(pos);

        for (int i = 64; i < world.getHeight(); i++) {
            mutable.set(pos);
            mutable.move(random.nextInt(10) - random.nextInt(10), 0, random.nextInt(10) - random.nextInt(10));
            mutable.setY(i);

            if (world.getBlockState(mutable).getBlock().is(Blocks.SPRUCE_LEAVES) && world.getBlockState(mutable.below()).isAir() && random.nextBoolean()) {
                world.setBlock(mutable.below(), Blocks.COBWEB.defaultBlockState(), 2);
                break;
            }
        }

        return true;
    }
}