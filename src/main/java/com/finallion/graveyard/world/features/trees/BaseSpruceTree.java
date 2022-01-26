package com.finallion.graveyard.world.features.trees;

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

public abstract class BaseSpruceTree extends Feature<TGTreeFeatureConfig> {


    public BaseSpruceTree(Codec<TGTreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<TGTreeFeatureConfig> p_159749_) {
        return false;
    }

    public void setLeaves(WorldGenLevel world, BlockPos pos, BlockState state) {
        if (canReplace(world, pos)) {
            world.setBlock(pos, state, 2);
        }
    }

    public void setLeavesRandomized(WorldGenLevel world, BlockPos pos, BlockState state, int chance) {
        if (canReplace(world, pos) && world.getRandom().nextInt(chance) == 0) {
            world.setBlock(pos, state, 2);
        }
    }

    public void setBranchRandomized(WorldGenLevel world, BlockPos pos, BlockState state, int chance) {
        if (canReplace(world, pos) && world.getRandom().nextInt(chance) == 0) {
            world.setBlock(pos, state, 2);
        }
    }

    public static boolean canReplace(LevelSimulatedReader p_236404_0_, BlockPos p_236404_1_) {
        return isAirOrLeaves(p_236404_0_, p_236404_1_) || isReplaceablePlant(p_236404_0_, p_236404_1_) || isWater(p_236404_0_, p_236404_1_);
    }

    private static boolean isWater(LevelSimulatedReader p_236416_0_, BlockPos p_236416_1_) {
        return p_236416_0_.isStateAtPosition(p_236416_1_, (p_236413_0_) -> {
            return p_236413_0_.is(Blocks.WATER);
        });
    }


    public static boolean isAirOrLeaves(LevelSimulatedReader p_236412_0_, BlockPos p_236412_1_) {
        return p_236412_0_.isStateAtPosition(p_236412_1_, (p_236411_0_) -> {
            return p_236411_0_.isAir() || p_236411_0_.is(BlockTags.LEAVES);
        });
    }

    private static boolean isReplaceablePlant(LevelSimulatedReader p_236419_0_, BlockPos p_236419_1_) {
        return p_236419_0_.isStateAtPosition(p_236419_1_, (p_236406_0_) -> {
            Material material = p_236406_0_.getMaterial();
            return material == Material.REPLACEABLE_PLANT;
        });
    }


    public void generateBranchesOne(WorldGenLevel world, BlockPos pos, int chance, TGTreeFeatureConfig config) {

        setBranchRandomized(world, pos.offset(1, 0, 0), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(world, pos.offset(0, 0, 1), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
        setBranchRandomized(world, pos.offset(-1, 0, 0), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(world, pos.offset(0, 0, -1), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);

    }

    public void generateBranchesTwo(WorldGenLevel world, BlockPos pos, int chance, TGTreeFeatureConfig config) {
        setBranchRandomized(world, pos.offset(1, 0, 0), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(world, pos.offset(0, 0, 1), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
        setBranchRandomized(world, pos.offset(-1, 0, 0), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(world, pos.offset(0, 0, -1), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
        setBranchRandomized(world, pos.offset(2, 0, 0), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(world, pos.offset(0, 0, 2), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
        setBranchRandomized(world, pos.offset(-2, 0, 0), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(world, pos.offset(0, 0, -2), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
    }



    public void randomSpreadOne(WorldGenLevel world, BlockPos pos, boolean beSquare, int chance, TGTreeFeatureConfig config) {
        setLeavesRandomized(world, pos.offset(1, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, -1), config.leafState, chance);

    }

    public void randomSpreadTwo(WorldGenLevel world, BlockPos pos, boolean beSquare, int chance, TGTreeFeatureConfig config) {
        setLeavesRandomized(world, pos.offset(1, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, -1), config.leafState, chance);

        if (!beSquare) {
            setLeavesRandomized(world, pos.offset(2, 0, 0), config.leafState, chance);
            setLeavesRandomized(world, pos.offset(0, 0, 2), config.leafState, chance);
            setLeavesRandomized(world, pos.offset(-2, 0, 0), config.leafState, chance);
            setLeavesRandomized(world, pos.offset(0, 0, -2), config.leafState, chance);
        }
    }

    public void randomSpreadThree(WorldGenLevel world, BlockPos pos, boolean beSquare, int chance, TGTreeFeatureConfig config) {
        setLeavesRandomized(world, pos.offset(1, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(2, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, 2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, -2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(2, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, 2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, -2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(2, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-2, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -2), config.leafState, chance);

        if (!beSquare) {
            setLeavesRandomized(world, pos.offset(3, 0, 0), config.leafState, chance);
            setLeavesRandomized(world, pos.offset(0, 0, 3), config.leafState, chance);
            setLeavesRandomized(world, pos.offset(-3, 0, 0), config.leafState, chance);
            setLeavesRandomized(world, pos.offset(0, 0, -3), config.leafState, chance);
        }
    }

    public void randomSpreadFour(WorldGenLevel world, BlockPos pos, boolean beSquare, int chance, TGTreeFeatureConfig config) {
        setLeavesRandomized(world, pos.offset(1, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, -1), config.leafState, chance);

        setLeavesRandomized(world, pos.offset(2, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, 2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, -2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(2, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, 2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, -2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(2, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-2, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(2, 0, 2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-2, 0, -2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(2, 0, -2), config.leafState, chance);

        setLeavesRandomized(world, pos.offset(3, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, 3), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-3, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, -3), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(3, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, 3), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-3, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, -3), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(3, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 3), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-3, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -3), config.leafState, chance);

        if (!beSquare) {
            setLeavesRandomized(world, pos.offset(4, 0, 0), config.leafState, chance);
            setLeavesRandomized(world, pos.offset(0, 0, 4), config.leafState, chance);
            setLeavesRandomized(world, pos.offset(-4, 0, 0), config.leafState, chance);
            setLeavesRandomized(world, pos.offset(0, 0, -4), config.leafState, chance);
        }
    }

    public void randomSpreadFive(WorldGenLevel world, BlockPos pos, boolean beSquare, int chance, TGTreeFeatureConfig config) {
        setLeavesRandomized(world, pos.offset(1, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, -1), config.leafState, chance);

        setLeavesRandomized(world, pos.offset(2, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, 2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, -2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(2, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, 2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, -2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(2, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-2, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(2, 0, 2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-2, 0, -2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(2, 0, -2), config.leafState, chance);

        setLeavesRandomized(world, pos.offset(3, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, 3), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-3, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, -3), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(3, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, 3), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-3, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, -3), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(3, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 3), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-3, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -3), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(3, 0, 2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(2, 0, 3), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-3, 0, 2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(2, 0, -3), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(3, 0, -2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 3), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-3, 0, -2), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-2, 0, -3), config.leafState, chance);

        setLeavesRandomized(world, pos.offset(4, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, 4), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-4, 0, 0), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(0, 0, -4), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(4, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, 4), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-4, 0, 1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(1, 0, -4), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(4, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 4), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-4, 0, -1), config.leafState, chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -4), config.leafState, chance);

        if (!beSquare) {
            setLeavesRandomized(world, pos.offset(5, 0, 0), config.leafState, chance);
            setLeavesRandomized(world, pos.offset(0, 0, 5), config.leafState, chance);
            setLeavesRandomized(world, pos.offset(-5, 0, 0), config.leafState, chance);
            setLeavesRandomized(world, pos.offset(0, 0, -5), config.leafState, chance);
        }
    }

    public void generateOneStar(WorldGenLevel world, BlockPos pos, boolean beSquare, TGTreeFeatureConfig config) {
        /*
                #
              # o #
                #
         */
        setLeaves(world, pos.offset(1, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, 1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, -1), config.leafState);

    }


    public void generateTwoStar(WorldGenLevel world, BlockPos pos, boolean beSquare, TGTreeFeatureConfig config) {
        /*
                #
              # # #
            # # o # #
              # # #
                #
         */

        setLeaves(world, pos.offset(1, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, 1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, -1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, -1), config.leafState);
        setLeaves(world, pos.offset(1, 0, 1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, 1), config.leafState);
        setLeaves(world, pos.offset(1, 0, -1), config.leafState);

        if (!beSquare) {
            setLeaves(world, pos.offset(2, 0, 0), config.leafState);
            setLeaves(world, pos.offset(0, 0, 2), config.leafState);
            setLeaves(world, pos.offset(-2, 0, 0), config.leafState);
            setLeaves(world, pos.offset(0, 0, -2), config.leafState);
        }
    }



    public void generateThreeStar(WorldGenLevel world, BlockPos pos, boolean beSquare, TGTreeFeatureConfig config) {
        /*
                #
              # # #
            # # # # #
          # # # o # # #
            # # # # #
              # # #
                #
         */

        setLeaves(world, pos.offset(1, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, 1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, -1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, -1), config.leafState);
        setLeaves(world, pos.offset(1, 0, 1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, 1), config.leafState);
        setLeaves(world, pos.offset(1, 0, -1), config.leafState);
        setLeaves(world, pos.offset(2, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, 2), config.leafState);
        setLeaves(world, pos.offset(-2, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, -2), config.leafState);
        setLeaves(world, pos.offset(2, 0, 1), config.leafState);
        setLeaves(world, pos.offset(1, 0, 2), config.leafState);
        setLeaves(world, pos.offset(-2, 0, 1), config.leafState);
        setLeaves(world, pos.offset(1, 0, -2), config.leafState);
        setLeaves(world, pos.offset(2, 0, -1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, 2), config.leafState);
        setLeaves(world, pos.offset(-2, 0, -1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, -2), config.leafState);

        if (!beSquare) {
            setLeaves(world, pos.offset(3, 0, 0), config.leafState);
            setLeaves(world, pos.offset(0, 0, 3), config.leafState);
            setLeaves(world, pos.offset(-3, 0, 0), config.leafState);
            setLeaves(world, pos.offset(0, 0, -3), config.leafState);
        }

    }


    public void generateFourStar(WorldGenLevel world, BlockPos pos, boolean beSquare, TGTreeFeatureConfig config) {
        /*
                  #
                # # #
              # # # # #
            # # # # # # #
          # # # # o # # # #
            # # # # # # #
              # # # # #
                # # #
                  #
         */

        setLeaves(world, pos.offset(1, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, 1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, -1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, -1), config.leafState);
        setLeaves(world, pos.offset(1, 0, 1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, 1), config.leafState);
        setLeaves(world, pos.offset(1, 0, -1), config.leafState);

        setLeaves(world, pos.offset(2, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, 2), config.leafState);
        setLeaves(world, pos.offset(-2, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, -2), config.leafState);
        setLeaves(world, pos.offset(2, 0, 1), config.leafState);
        setLeaves(world, pos.offset(1, 0, 2), config.leafState);
        setLeaves(world, pos.offset(-2, 0, 1), config.leafState);
        setLeaves(world, pos.offset(1, 0, -2), config.leafState);
        setLeaves(world, pos.offset(2, 0, -1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, 2), config.leafState);
        setLeaves(world, pos.offset(-2, 0, -1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, -2), config.leafState);
        setLeaves(world, pos.offset(2, 0, 2), config.leafState);
        setLeaves(world, pos.offset(-2, 0, 2), config.leafState);
        setLeaves(world, pos.offset(-2, 0, -2), config.leafState);
        setLeaves(world, pos.offset(2, 0, -2), config.leafState);

        setLeaves(world, pos.offset(3, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, 3), config.leafState);
        setLeaves(world, pos.offset(-3, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, -3), config.leafState);
        setLeaves(world, pos.offset(3, 0, 1), config.leafState);
        setLeaves(world, pos.offset(1, 0, 3), config.leafState);
        setLeaves(world, pos.offset(-3, 0, 1), config.leafState);
        setLeaves(world, pos.offset(1, 0, -3), config.leafState);
        setLeaves(world, pos.offset(3, 0, -1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, 3), config.leafState);
        setLeaves(world, pos.offset(-3, 0, -1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, -3), config.leafState);

        if (!beSquare) {
            setLeaves(world, pos.offset(4, 0, 0), config.leafState);
            setLeaves(world, pos.offset(0, 0, 4), config.leafState);
            setLeaves(world, pos.offset(-4, 0, 0), config.leafState);
            setLeaves(world, pos.offset(0, 0, -4), config.leafState);
        }
    }

    public void generateFiveStar(WorldGenLevel world, BlockPos pos, boolean beSquare, TGTreeFeatureConfig config) {
        /*
                    #
                  # # #
                # # # # #
              # # # # # # #
            # # # # # # # # #
          # # # # # o # # # # #
            # # # # # # # # #
              # # # # # # #
                # # # # #
                  # # #
                    #
         */
        setLeaves(world, pos.offset(1, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, 1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, -1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, -1), config.leafState);
        setLeaves(world, pos.offset(1, 0, 1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, 1), config.leafState);
        setLeaves(world, pos.offset(1, 0, -1), config.leafState);

        setLeaves(world, pos.offset(2, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, 2), config.leafState);
        setLeaves(world, pos.offset(-2, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, -2), config.leafState);
        setLeaves(world, pos.offset(2, 0, 1), config.leafState);
        setLeaves(world, pos.offset(1, 0, 2), config.leafState);
        setLeaves(world, pos.offset(-2, 0, 1), config.leafState);
        setLeaves(world, pos.offset(1, 0, -2), config.leafState);
        setLeaves(world, pos.offset(2, 0, -1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, 2), config.leafState);
        setLeaves(world, pos.offset(-2, 0, -1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, -2), config.leafState);
        setLeaves(world, pos.offset(2, 0, 2), config.leafState);
        setLeaves(world, pos.offset(-2, 0, 2), config.leafState);
        setLeaves(world, pos.offset(-2, 0, -2), config.leafState);
        setLeaves(world, pos.offset(2, 0, -2), config.leafState);

        setLeaves(world, pos.offset(3, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, 3), config.leafState);
        setLeaves(world, pos.offset(-3, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, -3), config.leafState);
        setLeaves(world, pos.offset(3, 0, 1), config.leafState);
        setLeaves(world, pos.offset(1, 0, 3), config.leafState);
        setLeaves(world, pos.offset(-3, 0, 1), config.leafState);
        setLeaves(world, pos.offset(1, 0, -3), config.leafState);
        setLeaves(world, pos.offset(3, 0, -1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, 3), config.leafState);
        setLeaves(world, pos.offset(-3, 0, -1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, -3), config.leafState);
        setLeaves(world, pos.offset(3, 0, 2), config.leafState);
        setLeaves(world, pos.offset(2, 0, 3), config.leafState);
        setLeaves(world, pos.offset(-3, 0, 2), config.leafState);
        setLeaves(world, pos.offset(2, 0, -3), config.leafState);
        setLeaves(world, pos.offset(3, 0, -2), config.leafState);
        setLeaves(world, pos.offset(-2, 0, 3), config.leafState);
        setLeaves(world, pos.offset(-3, 0, -2), config.leafState);
        setLeaves(world, pos.offset(-2, 0, -3), config.leafState);

        setLeaves(world, pos.offset(4, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, 4), config.leafState);
        setLeaves(world, pos.offset(-4, 0, 0), config.leafState);
        setLeaves(world, pos.offset(0, 0, -4), config.leafState);
        setLeaves(world, pos.offset(4, 0, 1), config.leafState);
        setLeaves(world, pos.offset(1, 0, 4), config.leafState);
        setLeaves(world, pos.offset(-4, 0, 1), config.leafState);
        setLeaves(world, pos.offset(1, 0, -4), config.leafState);
        setLeaves(world, pos.offset(4, 0, -1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, 4), config.leafState);
        setLeaves(world, pos.offset(-4, 0, -1), config.leafState);
        setLeaves(world, pos.offset(-1, 0, -4), config.leafState);

        if (!beSquare) {
            setLeaves(world, pos.offset(5, 0, 0), config.leafState);
            setLeaves(world, pos.offset(0, 0, 5), config.leafState);
            setLeaves(world, pos.offset(-5, 0, 0), config.leafState);
            setLeaves(world, pos.offset(0, 0, -5), config.leafState);
        }

    }


}
