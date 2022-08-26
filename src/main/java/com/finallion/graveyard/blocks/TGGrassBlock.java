package com.finallion.graveyard.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

public class TGGrassBlock extends GrassBlock {


    public TGGrassBlock(Properties settings) {
        super(settings);
    }


    @Override
    public ItemStack getCloneItemStack(BlockGetter p_49823_, BlockPos p_49824_, BlockState p_49825_) {
        return new ItemStack(Items.GRASS_BLOCK);
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, Level level, BlockPos pos, Player player, ItemStack stack, ToolAction toolAction) {
        if (ToolActions.SHOVEL_FLATTEN == toolAction) {
            return Blocks.DIRT_PATH.defaultBlockState();
        }
        return super.getToolModifiedState(state, level, pos, player, stack, toolAction);
    }
}
