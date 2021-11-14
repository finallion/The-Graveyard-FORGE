package com.finallion.graveyard.biomes.features.trees;

import com.finallion.graveyard.biomes.features.trees.config.TGTreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public abstract class BaseSpruceTree extends Feature<TGTreeFeatureConfig> {


    public BaseSpruceTree(Codec<TGTreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, TGTreeFeatureConfig config) {
        return false;
    }


    public void setLeaves(ISeedReader world, BlockPos pos, BlockState state) {
        if (canReplace(world, pos)) {
            world.setBlock(pos, state, 2);
        }
    }

    public void setLeavesRandomized(ISeedReader world, BlockPos pos, BlockState state, int chance) {
        if (canReplace(world, pos) && world.getRandom().nextInt(chance) == 0) {
            world.setBlock(pos, state, 2);
        }
    }

    public void setBranchRandomized(ISeedReader world, BlockPos pos, BlockState state, int chance) {
        if (canReplace(world, pos) && world.getRandom().nextInt(chance) == 0) {
            world.setBlock(pos, state, 2);
        }
    }

    public static boolean canReplace(IWorldGenerationBaseReader p_236404_0_, BlockPos p_236404_1_) {
        return isAirOrLeaves(p_236404_0_, p_236404_1_) || isReplaceablePlant(p_236404_0_, p_236404_1_) || isWater(p_236404_0_, p_236404_1_);
    }

    private static boolean isWater(IWorldGenerationBaseReader p_236416_0_, BlockPos p_236416_1_) {
        return p_236416_0_.isStateAtPosition(p_236416_1_, (p_236413_0_) -> {
            return p_236413_0_.is(Blocks.WATER);
        });
    }


    public static boolean isAirOrLeaves(IWorldGenerationBaseReader p_236412_0_, BlockPos p_236412_1_) {
        return p_236412_0_.isStateAtPosition(p_236412_1_, (p_236411_0_) -> {
            return p_236411_0_.isAir() || p_236411_0_.is(BlockTags.LEAVES);
        });
    }

    private static boolean isReplaceablePlant(IWorldGenerationBaseReader p_236419_0_, BlockPos p_236419_1_) {
        return p_236419_0_.isStateAtPosition(p_236419_1_, (p_236406_0_) -> {
            Material material = p_236406_0_.getMaterial();
            return material == Material.REPLACEABLE_PLANT;
        });
    }


    public void generateBranchesOne(ISeedReader world, BlockPos pos, int chance, TGTreeFeatureConfig config) {

        setBranchRandomized(world, pos.offset(1, 0, 0), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(world, pos.offset(0, 0, 1), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
        setBranchRandomized(world, pos.offset(-1, 0, 0), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(world, pos.offset(0, 0, -1), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);

    }

    public void generateBranchesTwo(ISeedReader world, BlockPos pos, int chance, TGTreeFeatureConfig config) {
        setBranchRandomized(world, pos.offset(1, 0, 0), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(world, pos.offset(0, 0, 1), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
        setBranchRandomized(world, pos.offset(-1, 0, 0), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(world, pos.offset(0, 0, -1), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
        setBranchRandomized(world, pos.offset(2, 0, 0), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(world, pos.offset(0, 0, 2), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
        setBranchRandomized(world, pos.offset(-2, 0, 0), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(world, pos.offset(0, 0, -2), config.woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
    }



    public void randomSpreadOne(ISeedReader world, BlockPos pos, boolean beSquare, int chance) {
        setLeavesRandomized(world, pos.offset(1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);

    }

    public void randomSpreadTwo(ISeedReader world, BlockPos pos, boolean beSquare, int chance) {
        setLeavesRandomized(world, pos.offset(1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);

        if (!beSquare) {
            setLeavesRandomized(world, pos.offset(2, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
            setLeavesRandomized(world, pos.offset(0, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
            setLeavesRandomized(world, pos.offset(-2, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
            setLeavesRandomized(world, pos.offset(0, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        }
    }

    public void randomSpreadThree(ISeedReader world, BlockPos pos, boolean beSquare, int chance) {
        setLeavesRandomized(world, pos.offset(1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(2, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(2, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(2, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-2, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);

        if (!beSquare) {
            setLeavesRandomized(world, pos.offset(3, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
            setLeavesRandomized(world, pos.offset(0, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
            setLeavesRandomized(world, pos.offset(-3, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
            setLeavesRandomized(world, pos.offset(0, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        }
    }

    public void randomSpreadFour(ISeedReader world, BlockPos pos, boolean beSquare, int chance) {
        setLeavesRandomized(world, pos.offset(1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);

        setLeavesRandomized(world, pos.offset(2, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(2, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(2, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-2, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(2, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-2, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(2, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);

        setLeavesRandomized(world, pos.offset(3, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-3, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(3, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-3, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(3, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-3, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);

        if (!beSquare) {
            setLeavesRandomized(world, pos.offset(4, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
            setLeavesRandomized(world, pos.offset(0, 0, 4), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
            setLeavesRandomized(world, pos.offset(-4, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
            setLeavesRandomized(world, pos.offset(0, 0, -4), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        }
    }

    public void randomSpreadFive(ISeedReader world, BlockPos pos, boolean beSquare, int chance) {
        setLeavesRandomized(world, pos.offset(1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);

        setLeavesRandomized(world, pos.offset(2, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(2, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(2, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-2, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(2, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-2, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(2, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);

        setLeavesRandomized(world, pos.offset(3, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-3, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(3, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-3, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(3, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-3, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(3, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(2, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-3, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(2, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(3, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-2, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-3, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-2, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);

        setLeavesRandomized(world, pos.offset(4, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, 4), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-4, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(0, 0, -4), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(4, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, 4), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-4, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(1, 0, -4), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(4, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, 4), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-4, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        setLeavesRandomized(world, pos.offset(-1, 0, -4), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);

        if (!beSquare) {
            setLeavesRandomized(world, pos.offset(5, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
            setLeavesRandomized(world, pos.offset(0, 0, 5), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
            setLeavesRandomized(world, pos.offset(-5, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
            setLeavesRandomized(world, pos.offset(0, 0, -5), Blocks.SPRUCE_LEAVES.defaultBlockState(), chance);
        }
    }

    public void generateOneStar(ISeedReader world, BlockPos pos, boolean beSquare) {
        /*
                #
              # o #
                #
         */
        setLeaves(world, pos.offset(1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());

    }


    public void generateTwoStar(ISeedReader world, BlockPos pos, boolean beSquare) {
        /*
                #
              # # #
            # # o # #
              # # #
                #
         */

        setLeaves(world, pos.offset(1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());

        if (!beSquare) {
            setLeaves(world, pos.offset(2, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
            setLeaves(world, pos.offset(0, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState());
            setLeaves(world, pos.offset(-2, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
            setLeaves(world, pos.offset(0, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        }
    }



    public void generateThreeStar(ISeedReader world, BlockPos pos, boolean beSquare) {
        /*
                #
              # # #
            # # # # #
          # # # o # # #
            # # # # #
              # # #
                #
         */

        setLeaves(world, pos.offset(1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(2, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-2, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(2, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-2, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(2, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-2, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState());

        if (!beSquare) {
            setLeaves(world, pos.offset(3, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
            setLeaves(world, pos.offset(0, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState());
            setLeaves(world, pos.offset(-3, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
            setLeaves(world, pos.offset(0, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState());
        }

    }


    public void generateFourStar(ISeedReader world, BlockPos pos, boolean beSquare) {
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

        setLeaves(world, pos.offset(1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());

        setLeaves(world, pos.offset(2, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-2, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(2, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-2, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(2, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-2, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(2, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-2, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-2, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(2, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState());

        setLeaves(world, pos.offset(3, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-3, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(3, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-3, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(3, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-3, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState());

        if (!beSquare) {
            setLeaves(world, pos.offset(4, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
            setLeaves(world, pos.offset(0, 0, 4), Blocks.SPRUCE_LEAVES.defaultBlockState());
            setLeaves(world, pos.offset(-4, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
            setLeaves(world, pos.offset(0, 0, -4), Blocks.SPRUCE_LEAVES.defaultBlockState());
        }
    }

    public void generateFiveStar(ISeedReader world, BlockPos pos, boolean beSquare) {
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
        setLeaves(world, pos.offset(1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());

        setLeaves(world, pos.offset(2, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-2, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(2, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-2, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(2, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-2, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(2, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-2, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-2, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(2, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState());

        setLeaves(world, pos.offset(3, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-3, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(3, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-3, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(3, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-3, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(3, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(2, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-3, 0, 2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(2, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(3, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-2, 0, 3), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-3, 0, -2), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-2, 0, -3), Blocks.SPRUCE_LEAVES.defaultBlockState());

        setLeaves(world, pos.offset(4, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, 4), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-4, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(0, 0, -4), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(4, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, 4), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-4, 0, 1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(1, 0, -4), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(4, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, 4), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-4, 0, -1), Blocks.SPRUCE_LEAVES.defaultBlockState());
        setLeaves(world, pos.offset(-1, 0, -4), Blocks.SPRUCE_LEAVES.defaultBlockState());

        if (!beSquare) {
            setLeaves(world, pos.offset(5, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
            setLeaves(world, pos.offset(0, 0, 5), Blocks.SPRUCE_LEAVES.defaultBlockState());
            setLeaves(world, pos.offset(-5, 0, 0), Blocks.SPRUCE_LEAVES.defaultBlockState());
            setLeaves(world, pos.offset(0, 0, -5), Blocks.SPRUCE_LEAVES.defaultBlockState());
        }

    }


}
