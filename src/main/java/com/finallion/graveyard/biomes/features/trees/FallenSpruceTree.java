package com.finallion.graveyard.biomes.features.trees;

import com.finallion.graveyard.biomes.features.surfaceFeatures.FeatureHelper;
import com.finallion.graveyard.biomes.features.trees.config.TGTreeFeatureConfig;
import com.finallion.graveyard.init.TGBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;


import java.util.Random;

public class FallenSpruceTree extends BaseSpruceTree {

    public FallenSpruceTree(Codec<TGTreeFeatureConfig> configCodec) {
        super(configCodec);
    }


    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos blockPos, TGTreeFeatureConfig config) {
        BlockState wood = config.woodState;

        Direction.Axis axis = random.nextBoolean() ? Direction.Axis.X : Direction.Axis.Z;
        Direction direction = Direction.fromAxisAndDirection(axis, random.nextBoolean() ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE);


        int length = random.nextInt(9) + 2;

        if (!world.getBlockState(blockPos).isAir()) return false;

        for (int i = 0; i < length; i++) {
            if (world.getBlockState(blockPos.relative(direction, i).below()).isSolidRender(world, blockPos.relative(direction, i).below()) && BaseSpruceTree.canReplace(world, blockPos.relative(direction, i))) {
                world.setBlock(blockPos.relative(direction, i), wood.setValue(BlockStateProperties.AXIS, axis), 2);
                if (random.nextBoolean() && world.getBlockState(blockPos.relative(direction, i).above()).isAir()) {
                    world.setBlock(blockPos.relative(direction, i).above(), TGBlocks.MOSS_CARPET.defaultBlockState(), 2);
                }
            } else {
                break;
            }
        }



        return false;

    }

}
