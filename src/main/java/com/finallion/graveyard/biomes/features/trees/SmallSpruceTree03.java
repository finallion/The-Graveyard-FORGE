package com.finallion.graveyard.biomes.features.trees;

import com.finallion.graveyard.biomes.features.surfaceFeatures.FeatureHelper;
import com.finallion.graveyard.biomes.features.trees.config.TGTreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;

import java.util.Random;

public class SmallSpruceTree03 extends BaseSpruceTree {
    private final int trunkHeight = 13;


    public SmallSpruceTree03(Codec<TGTreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos blockPos, TGTreeFeatureConfig config) {
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(blockPos);
        BlockState wood = config.woodState;
        BlockState leaf = config.leafState;
        int offsetTrunk = random.nextInt(3);

        if (!FeatureHelper.canBePlaced(world, blockPos.below(), world.getBlockState(blockPos.below()))) {
            return false;
        }

        if (!FeatureHelper.canGenerate(world, blockPos, 13)) {
            return false;
        }

        for (int i = 0; i < trunkHeight + offsetTrunk; i++) {
            world.setBlock(blockPos.above(i), wood, 2);
            mutable.move(0, 1, 0);
        }

        // single tree top
        setLeaves(world, mutable.offset(0, 1, 0), leaf);
        setLeaves(world, mutable.offset(0, 0, 0), leaf);
        setLeaves(world, mutable.offset(0, -1, 0), leaf);
        generateOneStar(world, mutable.offset(0, -1, 0), false);

        randomSpreadOne(world, mutable.offset(0, -2, 0), false, 2);
        randomSpreadOne(world, mutable.offset(0, -3, 0), false, 2);
        generateOneStar(world, mutable.offset(0, -4, 0), false);
        generateTwoStar(world, mutable.offset(0, -5, 0), false);
        generateOneStar(world, mutable.offset(0, -6, 0), false);
        randomSpreadTwo(world, mutable.offset(0, -6, 0), false, 3);
        generateOneStar(world, mutable.offset(0, -7, 0), false);
        randomSpreadTwo(world, mutable.offset(0, -7, 0), false, 3);
        generateTwoStar(world, mutable.offset(0, -8, 0), false);
        generateBranchesOne(world, mutable.offset(0, -8, 0), 1, config);
        randomSpreadTwo(world, mutable.offset(0, -9, 0), false, 3);
        generateThreeStar(world, mutable.offset(0, -10, 0), false);
        generateFourStar(world, mutable.offset(0, -11, 0), true);

        return false;
    }
}
