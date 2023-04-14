package com.finallion.graveyard.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

public class TGStoneBlock extends Block {

    private final Supplier<Block> clickedBlock;

    public TGStoneBlock(Supplier<Block> clickedBlock, Properties settings) {
        super(settings);
        this.clickedBlock = clickedBlock;
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader p_185473_1_, BlockPos p_185473_2_, BlockState p_185473_3_) {
        return new ItemStack(clickedBlock.get());
    }

}
