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

public class SoulLightFeature extends Feature<NoneFeatureConfiguration> {


    public SoulLightFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos pos = context.origin();
        WorldGenLevel world = context.level();
        Random random = context.random();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(pos);

        mutable.set(pos);
        mutable.move(random.nextInt(10) - random.nextInt(10), 0, random.nextInt(10) - random.nextInt(10));
        mutable.setY(63);


        if (world.getBlockState(mutable).is(Blocks.LILY_PAD) && world.getBlockState(mutable.above()).isAir() && world.getBlockState(mutable.above(2)).isAir() && FeatureHelper.isCorrectBiome(world.getBiomeName(mutable).get())) {
            world.setBlock(mutable.move(0, random.nextInt(2) + 1, 0), Blocks.BLACK_CANDLE.defaultBlockState().setValue(BlockStateProperties.LIT, true).setValue(BlockStateProperties.CANDLES, random.nextInt(3) + 2), 2);
            return true;
        }




        return false;
    }
}
