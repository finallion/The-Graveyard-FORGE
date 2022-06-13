package com.finallion.graveyard.world.biomes.features.trees;

import com.finallion.graveyard.world.biomes.features.features.FeatureHelper;
import com.finallion.graveyard.world.biomes.features.trees.config.TGTreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;

import java.util.Random;

public class SmallBentSpruceTree01 extends BaseSpruceTree {
    private final int trunkHeight = 12;

    public SmallBentSpruceTree01(Codec<TGTreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos blockPos, TGTreeFeatureConfig config) {
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(blockPos);
        BlockState wood = config.woodState;
        BlockState leaf = config.leafState;
        int offsetTrunk = random.nextInt(3);
        Direction.Axis axis = random.nextBoolean() ? Direction.Axis.X : Direction.Axis.Z;
        Direction direction = Direction.fromAxisAndDirection(axis, random.nextBoolean() ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE);


        if (!FeatureHelper.canBePlaced(world, blockPos.below(), world.getBlockState(blockPos.below()))) {
            return false;
        }

        if (!FeatureHelper.canGenerate(world, blockPos, 12)) {
            return false;
        }


        // straight half of the trunk
        for (int i = 0; i < trunkHeight - 6 + offsetTrunk; i++) {
            world.setBlock(blockPos.above(i), wood, 2);
        }

        // bent half of the trunk
        for (int i = 6 + offsetTrunk; i < trunkHeight  + offsetTrunk; i++) {
            world.setBlock(blockPos.above(i).relative(direction), wood, 2);
        }

        // start generating leaves from the top
        // shift leaf layer in bending direction
        mutable.move(0, trunkHeight + offsetTrunk, 0);
        mutable.move(direction);

        // single tree top
        setLeaves(world, mutable.offset(0, 3, 0), leaf);
        setLeaves(world, mutable.offset(0, 2, 0), leaf);
        setLeaves(world, mutable.offset(0, 1, 0), leaf);
        setLeaves(world, mutable.offset(0, 0, 0), leaf);

        generateOneStar(world, mutable.offset(0, 0, 0), false, config);
        randomSpreadTwo(world, mutable.offset(0, -1, 0), true, 2, config);

        // second layer
        generateOneStar(world, mutable.offset(0, -3, 0), false, config);
        generateTwoStar(world, mutable.offset(0, -4, 0), false, config);

        randomSpreadTwo(world, mutable.offset(0, -5, 0), false, 2, config);

        // third layer
        generateTwoStar(world, mutable.offset(0, -6, 0), false, config);
        generateThreeStar(world, mutable.offset(0, -7, 0), true, config);

        // revert bending direction
        mutable.move(direction.getOpposite());
        randomSpreadOne(world, mutable.offset(0, -8, 0), false, 4, config);

        // forth layer
        generateTwoStar(world, mutable.offset(0, -9, 0), false, config);
        generateThreeStar(world, mutable.offset(0, -10, 0), false, config);




        return false;
    }
}
