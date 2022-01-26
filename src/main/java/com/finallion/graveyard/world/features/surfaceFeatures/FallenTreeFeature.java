package com.finallion.graveyard.world.features.surfaceFeatures;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class FallenTreeFeature extends Feature<NoneFeatureConfiguration> {


    public FallenTreeFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        Random random = context.random();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(blockPos);
        BlockState wood = Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState();

        Direction.Axis axis = random.nextBoolean() ? Direction.Axis.X : Direction.Axis.Z;
        Direction direction = Direction.fromAxisAndDirection(axis, random.nextBoolean() ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE);

        if (!world.getBlockState(blockPos).isAir()) return false;

        int length = random.nextInt(9) + 2;

        for (int i = 93; i < world.getHeight(); i++) {
            for (int ii = 0; ii < length; ii++) {
                if (ii == 0) {
                    if (world.getBlockState(blockPos.relative(direction, ii + 1).below()).getBlock() != Blocks.SOUL_SAND || world.getBlockState(blockPos.relative(direction, ii + 2).below()).getBlock() != Blocks.SOUL_SAND) {
                        break;
                    }
                }

                if (world.getBlockState(blockPos.relative(direction, ii).below()).getBlock() == Blocks.SOUL_SAND && canBeReplaced(world.getBlockState(blockPos.relative(direction, ii)))) {
                    world.setBlock(blockPos.relative(direction, ii), wood.setValue(BlockStateProperties.AXIS, axis), 2);

                    // moss carpet on top of the trunk
                    if (random.nextBoolean() && world.getBlockState(blockPos.relative(direction, ii).above()).isAir()) {
                        world.setBlock(blockPos.relative(direction, ii).above(), Blocks.MOSS_CARPET.defaultBlockState(), 2);
                    }
                } else {
                    break;
                }
            }
        }

        return true;

    }

    private boolean canBeReplaced(BlockState state) {
        if (state.getBlock() instanceof BushBlock || state.isAir()) {
            return true;
        }
        return false;
    }

}

