package com.finallion.graveyard.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TGGrassBlock extends GrassBlock {


    public TGGrassBlock(Properties settings) {
        super(settings);
    }


    @Override
    public ItemStack getCloneItemStack(BlockGetter p_49823_, BlockPos p_49824_, BlockState p_49825_) {
        return new ItemStack(Items.GRASS_BLOCK);
    }





}
