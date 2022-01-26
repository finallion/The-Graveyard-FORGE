package com.finallion.graveyard.world.features.surfaceFeatures;


import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class BushFeature extends Feature<NoneFeatureConfiguration> {


    public BushFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }


    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos pos = context.origin();
        WorldGenLevel world = context.level();
        Random random = context.random();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(pos);

        for (int i = 64; i < world.getHeight(); i++) {
            mutable.set(pos);
            mutable.move(random.nextInt(3) - random.nextInt(3), 0, random.nextInt(3) - random.nextInt(3));
            mutable.setY(i);
            if (FeatureHelper.canBePlaced(world.getBlockState(mutable)) && world.getBlockState(mutable.above()).isAir() && FeatureHelper.isCorrectBiome(world.getBiomeName(mutable).get())) {
                if (random.nextInt(7) == 0) {
                    world.setBlock(mutable.above(), Blocks.SPRUCE_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true), 2);
                    break;
                }
            }
        }

        return true;
    }
}

