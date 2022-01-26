package com.finallion.graveyard.world.features.trees;

import com.finallion.graveyard.world.features.surfaceFeatures.FeatureHelper;
import com.finallion.graveyard.world.features.trees.config.TGTreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.Material;

import java.util.Random;

public class LargeSpruceTree03 extends BaseSpruceTree {
    private final int trunkHeight = 25;


    public LargeSpruceTree03(Codec<TGTreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<TGTreeFeatureConfig> context) {
        BlockPos blockPos = context.origin();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(blockPos);
        BlockState wood = context.config().woodState;
        BlockState leaf = context.config().leafState;
        WorldGenLevel world = context.level();
        Random random = context.random();
        TGTreeFeatureConfig config = context.config();
        int offsetTrunk = random.nextInt(3);

        if (!FeatureHelper.canBePlaced(world, blockPos.below(), world.getBlockState(blockPos.below()))) {
            return false;
        }

        if (!FeatureHelper.canGenerate(world, blockPos, 25)) {
            return false;
        }

        for (int i = 0; i < trunkHeight + offsetTrunk; i++) {
            world.setBlock(blockPos.above(i), wood, 2);
            mutable.move(0, 1, 0);
        }

        // single tree top
        setLeaves(world, mutable.offset(0, 4, 0), leaf);
        setLeaves(world, mutable.offset(0, 3, 0), leaf);
        setLeaves(world, mutable.offset(0, 2, 0), leaf);
        setLeaves(world, mutable.offset(0, 1, 0), leaf);
        randomSpreadOne(world, mutable.offset(0, 1, 0), false, 4, config);
        setLeaves(world, mutable.offset(0, 0, 0), leaf);
        generateOneStar(world, mutable.offset(0, 0, 0), false, config);
        randomSpreadOne(world, mutable.offset(0, -1, 0), false, 2, config);
        randomSpreadTwo(world, mutable.offset(0, -2, 0), true, 2, config);
        randomSpreadOne(world, mutable.offset(0, -3, 0), false, 2, config);
        generateTwoStar(world, mutable.offset(0, -4, 0), false, config);
        randomSpreadThree(world, mutable.offset(0, -5, 0), true, 2, config);
        randomSpreadThree(world, mutable.offset(0, -6, 0), false, 2, config);
        generateTwoStar(world, mutable.offset(0, -7, 0), false, config);
        generateThreeStar(world, mutable.offset(0, -8, 0), true, config);
        randomSpreadThree(world, mutable.offset(0, -9, 0), true, 2, config);
        randomSpreadThree(world, mutable.offset(0, -10, 0), false, 2, config);
        randomSpreadTwo(world, mutable.offset(0, -11, 0), false, 2, config);

        generateThreeStar(world, mutable.offset(0, -12, 0), false, config);
        randomSpreadFour(world, mutable.offset(0, -13, 0), true, 2, config);
        randomSpreadFour(world, mutable.offset(0, -14, 0), false, 2, config);
        generateTwoStar(world, mutable.offset(0, -15, 0), false, config);

        randomSpreadFour(world, mutable.offset(0, -12, 0), true, 2, config);
        randomSpreadFour(world, mutable.offset(0, -13, 0), true, 4, config);
        randomSpreadFour(world, mutable.offset(0, -14, 0), false, 2, config);
        generateTwoStar(world, mutable.offset(0, -15, 0), false, config);

        generateFiveStar(world, mutable.offset(0, -16, 0), true, config);
        randomSpreadFive(world, mutable.offset(0, -17, 0), false, 2, config);
        randomSpreadFour(world, mutable.offset(0, -18, 0), false, 2, config);

        generateFiveStar(world, mutable.offset(0, -19, 0), false, config);
        randomSpreadFive(world, mutable.offset(0, -20, 0), false, 2, config);
        randomSpreadFour(world, mutable.offset(0, -21, 0), false, 2, config);

        randomSpreadFive(world, mutable.offset(0, -22, 0), false, 2, config);
        generateFiveStar(world, mutable.offset(0, -23, 0), false, config);
        randomSpreadFour(world, mutable.offset(0, -24, 0), false, 2, config);
        return false;
    }
}
