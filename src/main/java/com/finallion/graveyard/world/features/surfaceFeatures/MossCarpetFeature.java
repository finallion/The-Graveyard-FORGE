package com.finallion.graveyard.world.features.surfaceFeatures;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class MossCarpetFeature extends Feature<NoneFeatureConfiguration> {


    public MossCarpetFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos pos = context.origin();
        WorldGenLevel world = context.level();
        Random random = context.random();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(pos);

        // cap at height 85
        for (int i = 64; i < 85; i++) {
            mutable.set(pos);
            mutable.move(random.nextInt(10) - random.nextInt(10), 0, random.nextInt(10) - random.nextInt(10));
            mutable.setY(i);
            if (world.getBlockState(mutable).getBlock() instanceof LeavesBlock && world.getBlockState(mutable.above()).isAir() && FeatureHelper.isCorrectBiome(world.getBiomeName(mutable).get())) {
                world.setBlock(mutable.above(), Blocks.MOSS_CARPET.defaultBlockState(), 2);
                break;

            } else if (world.getBlockState(mutable).getBlock() == Blocks.MOSS_BLOCK && world.getBlockState(mutable.above()).isAir()) {
                if (random.nextInt(5) == 0) {
                    world.setBlock(mutable.above(), Blocks.MOSS_CARPET.defaultBlockState(), 2);
                    break;
                }
            }

        }

        return true;

    }

}
