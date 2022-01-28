package com.finallion.graveyard.world.features.surfaceFeatures;

import com.finallion.graveyard.util.TGTags;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BaseCoralWallFanBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SeaPickleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Iterator;
import java.util.Random;

public abstract class DeadCoralFeature extends Feature<NoneFeatureConfiguration> {
    public DeadCoralFeature(Codec<NoneFeatureConfiguration> p_65429_) {
        super(p_65429_);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_159536_) {
        Random random = p_159536_.random();
        WorldGenLevel worldgenlevel = p_159536_.level();
        BlockPos blockpos = p_159536_.origin();
        BlockState blockState = ((Block) TGTags.DEAD_CORAL_BLOCKS.getRandomElement(random)).defaultBlockState();
        return this.placeFeature(worldgenlevel, random, blockpos, blockState);
    }

    protected abstract boolean placeFeature(LevelAccessor p_65430_, Random p_65431_, BlockPos p_65432_, BlockState p_65433_);

    protected boolean placeCoralBlock(LevelAccessor world, Random p_65448_, BlockPos pos, BlockState p_65450_) {
        BlockPos blockPos = pos.above();
        BlockState blockState = world.getBlockState(pos);
        if (world.getBlockState(pos.below()).is(Blocks.WATER) || world.getBlockState(pos.below()).is(Blocks.AIR) || !world.getBlockState(pos.below()).isCollisionShapeFullBlock(world, pos.below())) {
            return false;
        }

        if (((blockState.is(Blocks.AIR) || blockState.is(Blocks.WATER)) || blockState.is(TGTags.DEAD_CORALS)) && (world.getBlockState(blockPos).is(Blocks.AIR) || world.getBlockState(blockPos).is(Blocks.WATER))) {
            world.setBlock(pos, p_65450_, 3);
            if (p_65448_.nextFloat() < 0.25F) {

                if (world.getBlockState(blockPos).is(Blocks.WATER)) {
                    world.setBlock(blockPos, ((Block)TGTags.DEAD_CORALS.getRandomElement(p_65448_)).defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true), 2);
                } else if (world.getBlockState(blockPos).is(Blocks.AIR)) {
                    world.setBlock(blockPos, ((Block)TGTags.DEAD_CORALS.getRandomElement(p_65448_)).defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false), 2);
                }
            }

            for(Direction direction : Direction.Plane.HORIZONTAL) {
                if (p_65448_.nextFloat() < 0.2F) {
                    BlockPos blockpos1 = pos.relative(direction);
                    if (world.getBlockState(blockpos1).is(Blocks.WATER)) {
                        BlockState blockState2 = ((Block)TGTags.DEAD_WALL_CORALS.getRandomElement(p_65448_)).defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true);
                        if (blockState2.hasProperty(BaseCoralWallFanBlock.FACING)) {
                            blockState2 = (BlockState)blockState2.setValue(BaseCoralWallFanBlock.FACING, direction);
                        }

                        world.setBlock(blockpos1, blockState2, 2);
                    } else if (world.getBlockState(blockpos1).is(Blocks.AIR)) {
                        BlockState blockState2 = ((Block)TGTags.DEAD_WALL_CORALS.getRandomElement(p_65448_)).defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false);
                        if (blockState2.hasProperty(BaseCoralWallFanBlock.FACING)) {
                            blockState2 = (BlockState)blockState2.setValue(BaseCoralWallFanBlock.FACING, direction);
                        }

                        world.setBlock(blockpos1, blockState2, 2);
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }
}

