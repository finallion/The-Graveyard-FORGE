package com.finallion.graveyard.blocks;


import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class TGGrassBlock extends GrassBlock {


    public TGGrassBlock(Properties settings) {
        super(settings);
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader p_185473_1_, BlockPos p_185473_2_, BlockState p_185473_3_) {
        return new ItemStack(Items.GRASS_BLOCK);
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
        if (ToolType.SHOVEL == toolType) {
            return Blocks.GRASS_PATH.defaultBlockState();
        }
        return super.getToolModifiedState(state, world, pos, player, stack, toolType);
    }

}
