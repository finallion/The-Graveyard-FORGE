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


public class CobwebFeature extends Feature<NoneFeatureConfiguration> {


    public CobwebFeature(Codec<NoneFeatureConfiguration> configCodec) {
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
            mutable.move(random.nextInt(10) - random.nextInt(10), 0, random.nextInt(10) - random.nextInt(10));
            mutable.setY(i);

            if (world.getBlockState(mutable).getBlock() == Blocks.SPRUCE_LEAVES && world.getBlockState(mutable.below()).isAir() && random.nextBoolean() && FeatureHelper.isCorrectBiome(world.getBiomeName(mutable).get())) {
                world.setBlock(mutable.below(), Blocks.COBWEB.defaultBlockState(), 2);
                break;
            }
        }

        return true;
    }
}