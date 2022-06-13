package com.finallion.graveyard.world.biomes.features.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class BushFeature extends Feature<NoFeatureConfig> {


    public BushFeature(Codec<NoFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(pos);

        for (int i = 64; i < world.getHeight(); i++) {
            mutable.set(pos);
            mutable.move(random.nextInt(3) - random.nextInt(3), 0, random.nextInt(3) - random.nextInt(3));
            mutable.setY(i);
            if (FeatureHelper.canBePlaced(world.getBlockState(mutable)) && world.getBlockState(mutable.above()).isAir()) {
                if (random.nextInt(7) == 0) {
                    world.setBlock(mutable.above(), Blocks.SPRUCE_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true), 2);
                    break;
                }
            }
        }

        return true;
    }
}

