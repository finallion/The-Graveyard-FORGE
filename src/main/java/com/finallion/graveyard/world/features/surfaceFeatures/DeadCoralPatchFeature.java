package com.finallion.graveyard.world.features.surfaceFeatures;

import com.finallion.graveyard.util.TGTags;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;


import java.util.Random;

public class DeadCoralPatchFeature extends Feature<NoneFeatureConfiguration> {


    public DeadCoralPatchFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        Random random = context.random();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(blockPos);


        for (int i = 61; i < 80; i++) {
            mutable.set(blockPos);
            mutable.move(random.nextInt(3) - random.nextInt(3), 0, random.nextInt(3) - random.nextInt(3));
            mutable.setY(i);
            if (world.getBlockState(mutable).isSolidRender(world, mutable) && world.getBlockState(mutable.above()).isAir() && world.getBiomeName(mutable).get().toString().contains("ancient_dead_coral_reef")) {
                if (random.nextInt(7) == 0) {
                    BlockState coral = TGTags.DEAD_CORALS.getRandomElement(random).defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false);
                    world.setBlock(mutable.above(), coral, 2);
                    break;
                }
            } else if (world.getBlockState(mutable).isSolidRender(world, mutable) && world.getBlockState(mutable.above()).getBlock() == Blocks.WATER && world.getBiomeName(mutable).get().toString().contains("ancient_dead_coral_reef")) {
                if (random.nextBoolean()) {
                    BlockState coral = BlockTags.CORALS.getRandomElement(random).defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true);
                    world.setBlock(mutable.above(), coral, 2);
                    break;
                }
            }
        }

        return true;

    }

}

