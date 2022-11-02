package com.finallion.graveyard.blocks;


import com.finallion.graveyard.init.TGBlocks;
import com.finallion.graveyard.init.TGSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.Random;

public class AltarBlock extends Block {
    public static final BooleanProperty BLOODY = BooleanProperty.create("bloody");
    private static BlockPattern COMPLETED_ALTAR;


    public AltarBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(BLOODY, false)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BLOODY);
    }


    public void animateTick(BlockState state, Level world, BlockPos pos, Random random) {
        super.animateTick(state, world, pos, random);
        if (state.getValue(BLOODY)) {
            if (random.nextInt(10) == 0) {
                world.playLocalSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, TGSounds.ALTAR_AMBIENT.get(), SoundSource.BLOCKS, 0.05F, random.nextFloat() * 0.4F + 0.8F, true);
            }
        }

    }

    public static BlockPattern getCompletedFramePattern() {
        if (COMPLETED_ALTAR == null) {
            COMPLETED_ALTAR = BlockPatternBuilder.start().aisle("???x???", "???????", "???????", "???????", "???????", "???????", "???????", "a??b??c")
                    .where('?', BlockInWorld.hasState(BlockStatePredicate.ANY))
                    .where('a', BlockInWorld.hasState(BlockStatePredicate.forBlock(TGBlocks.LOWER_BONE_STAFF.get()).or(BlockStatePredicate.forBlock(TGBlocks.UPPER_BONE_STAFF.get()))))
                    .where('b', BlockInWorld.hasState(BlockStatePredicate.forBlock(TGBlocks.MIDDLE_BONE_STAFF.get())))
                    .where('c', BlockInWorld.hasState(BlockStatePredicate.forBlock(TGBlocks.UPPER_BONE_STAFF.get()).or(BlockStatePredicate.forBlock(TGBlocks.LOWER_BONE_STAFF.get()))))
                    .where('x', BlockInWorld.hasState(BlockStatePredicate.forBlock(TGBlocks.ALTAR.get())))
                    .build();
        }

        return COMPLETED_ALTAR;
    }

}
