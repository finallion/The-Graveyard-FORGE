package com.finallion.graveyard.world.biomes.features.trees;

import com.finallion.graveyard.world.biomes.features.features.FeatureHelper;
import com.finallion.graveyard.world.biomes.features.trees.config.TGTreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;

import java.util.Random;

public class LargeSpruceTree02 extends BaseSpruceTree {
    private final int trunkHeight = 23;


    public LargeSpruceTree02(Codec<TGTreeFeatureConfig> configCodec) {
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

        if (!FeatureHelper.canGenerate(world, blockPos, 23)) {
            return false;
        }

        for (int i = 0; i < trunkHeight + offsetTrunk; i++) {
            world.setBlock(blockPos.above(i), wood, 2);
            mutable.move(0, 1, 0);
        }

        // single tree top
        setLeaves(world, mutable.offset(0, 3, 0), leaf);
        setLeaves(world, mutable.offset(0, 2, 0), leaf);
        setLeaves(world, mutable.offset(0, 1, 0), leaf);
        setLeaves(world, mutable.offset(0, 0, 0), leaf);
        generateOneStar(world, mutable.offset(0, 0, 0), false, config);
        randomSpreadOne(world, mutable.offset(0, -1, 0), false, 2, config);
        randomSpreadOne(world, mutable.offset(0, -2, 0), false, 2, config);
        generateOneStar(world, mutable.offset(0, -3, 0), false, config);
        randomSpreadTwo(world, mutable.offset(0, -3, 0), false, 2, config);

        randomSpreadTwo(world, mutable.offset(0, -5, 0), false, 2, config);
        generateTwoStar(world, mutable.offset(0, -6, 0), false, config);
        randomSpreadTwo(world, mutable.offset(0, -7, 0), false, 2, config);
        generateThreeStar(world, mutable.offset(0, -8, 0), true, config);
        generateThreeStar(world, mutable.offset(0, -9, 0), false, config);
        randomSpreadTwo(world, mutable.offset(0, -10, 0), false, 2, config);

        randomSpreadThree(world, mutable.offset(0, -11, 0), true, 2, config);
        generateThreeStar(world, mutable.offset(0, -12, 0), false, config);
        randomSpreadTwo(world, mutable.offset(0, -13, 0), false, 2, config);

        randomSpreadFour(world, mutable.offset(0, -14, 0), true, 2, config);
        generateFourStar(world, mutable.offset(0, -15, 0), false, config);
        randomSpreadTwo(world, mutable.offset(0, -16, 0), false, 2, config);

        randomSpreadFour(world, mutable.offset(0, -17, 0), true, 2, config);
        generateFourStar(world, mutable.offset(0, -18, 0), false, config);
        randomSpreadThree(world, mutable.offset(0, -19, 0), false, 2, config);

        randomSpreadFive(world, mutable.offset(0, -20, 0), true, 2, config);
        generateFiveStar(world, mutable.offset(0, -21, 0), false, config);
        randomSpreadThree(world, mutable.offset(0, -22, 0), false, 2, config);

        return false;
    }
}
