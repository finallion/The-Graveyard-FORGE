package com.finallion.graveyard.world.features.surfaceFeatures;

import com.finallion.graveyard.init.TGBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class GraveFeature extends Feature<NoneFeatureConfiguration> {

    public GraveFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos pos = context.origin();
        WorldGenLevel world = context.level();
        RandomSource random = context.random();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(pos);

        if (FeatureHelper.canBePlaced(world.getBlockState(pos.below())) && world.getBlockState(pos).isAir() && random.nextInt(10) == 0 && FeatureHelper.isCorrectBiome(world.getBiome(mutable).unwrapKey().get())) {
            switch (random.nextInt(4)) {
                case 1: world.setBlock(pos, TGBlocks.COBBLESTONE_GRAVESTONE.get().defaultBlockState(), 2); break;
                case 2: world.setBlock(pos, TGBlocks.MOSSY_COBBLESTONE_GRAVESTONE.get().defaultBlockState(), 2); break;
                case 3: world.setBlock(pos, TGBlocks.DEEPSLATE_GRAVESTONE.get().defaultBlockState(), 2); break;
                default: world.setBlock(pos, TGBlocks.GRAVESTONE.get().defaultBlockState(), 2); break;
            }
        }


        return true;

    }

}
