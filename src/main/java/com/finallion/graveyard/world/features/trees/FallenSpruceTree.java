package com.finallion.graveyard.world.features.trees;


import com.finallion.graveyard.init.TGBlocks;
import com.finallion.graveyard.world.features.trees.config.TGTreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;


import java.util.Random;

public class FallenSpruceTree extends BaseSpruceTree {

    public FallenSpruceTree(Codec<TGTreeFeatureConfig> configCodec) {
        super(configCodec);
    }


    @Override
    public boolean place(FeaturePlaceContext<TGTreeFeatureConfig> context) {
        BlockState wood = context.config().woodState;
        Random random = context.random();
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();

        Direction.Axis axis = random.nextBoolean() ? Direction.Axis.X : Direction.Axis.Z;
        Direction direction = Direction.fromAxisAndDirection(axis, random.nextBoolean() ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE);


        int length = random.nextInt(9) + 2;

        if (!world.getBlockState(blockPos).isAir()) return false;

        for (int i = 0; i < length; i++) {
            if (world.getBlockState(blockPos.relative(direction, i).below()).isSolidRender(world, blockPos.relative(direction, i).below()) && BaseSpruceTree.canReplace(world, blockPos.relative(direction, i))) {
                world.setBlock(blockPos.relative(direction, i), wood.setValue(BlockStateProperties.AXIS, axis), 2);
                if (random.nextBoolean() && world.getBlockState(blockPos.relative(direction, i).above()).isAir()) {
                    world.setBlock(blockPos.relative(direction, i).above(), Blocks.MOSS_CARPET.defaultBlockState(), 2);
                }
            } else {
                break;
            }
        }



        return false;

    }

}
