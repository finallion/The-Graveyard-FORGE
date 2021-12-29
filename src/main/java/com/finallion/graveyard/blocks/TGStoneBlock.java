package com.finallion.graveyard.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class TGStoneBlock extends Block {

    private final Supplier<Block> clickedBlock;

    public TGStoneBlock(Supplier<Block> clickedBlock, Properties settings) {
        super(settings);
        this.clickedBlock = clickedBlock;
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter p_49823_, BlockPos p_49824_, BlockState p_49825_) {
        return new ItemStack(clickedBlock.get());
    }


}
