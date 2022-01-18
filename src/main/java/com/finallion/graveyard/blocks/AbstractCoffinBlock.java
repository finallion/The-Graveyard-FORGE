package com.finallion.graveyard.blocks;

import java.util.function.Supplier;

import com.finallion.graveyard.blockentities.SarcophagusBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractCoffinBlock<E extends BlockEntity> extends BaseEntityBlock {
    protected final Supplier<BlockEntityType<? extends E>> blockEntityType;

    protected AbstractCoffinBlock(BlockBehaviour.Properties p_48677_, Supplier<BlockEntityType<? extends E>> p_48678_) {
        super(p_48677_);
        this.blockEntityType = p_48678_;
    }

    public abstract DoubleBlockCombiner.NeighborCombineResult<? extends SarcophagusBlockEntity> combine(BlockState p_48679_, Level p_48680_, BlockPos p_48681_, boolean p_48682_);
}