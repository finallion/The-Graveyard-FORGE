package com.finallion.graveyard.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class TGGrassBlock extends GrassBlock {


    public TGGrassBlock(Properties settings) {
        super(settings);
    }



    public ItemStack getCloneItemStack(IBlockReader p_185473_1_, BlockPos p_185473_2_, BlockState p_185473_3_) {
        return new ItemStack(Items.GRASS_BLOCK);
    }



}
