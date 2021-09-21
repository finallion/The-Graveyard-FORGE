package com.finallion.graveyard.blocks;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;


public class VaseBlock extends Block implements IWaterLoggable {
    public static final IntegerProperty VASES;
    public static final BooleanProperty WATERLOGGED;
    private static final VoxelShape VASE_SHAPE_ONE;
    private static final VoxelShape VASE_SHAPE_TWO;
    private static final VoxelShape VASE_SHAPE_THREE;
    private static final VoxelShape VASE_SHAPE_FOUR;



    public VaseBlock() {
        super(AbstractBlock.Properties.of(Material.GLASS).instabreak().noCollission().noOcclusion().sound(SoundType.GLASS));
        this.registerDefaultState(this.getStateDefinition().any().setValue(VASES, 1).setValue(WATERLOGGED, false));
    }
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(VASES, WATERLOGGED);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        BlockState blockstate = p_196258_1_.getLevel().getBlockState(p_196258_1_.getClickedPos());
        if (blockstate.is(this)) {
            return blockstate.setValue(VASES, Integer.valueOf(Math.min(4, blockstate.getValue(VASES) + 1)));
        } else {
            FluidState fluidstate = p_196258_1_.getLevel().getFluidState(p_196258_1_.getClickedPos());
            boolean flag = fluidstate.getType() == Fluids.WATER;
            return super.getStateForPlacement(p_196258_1_).setValue(WATERLOGGED, Boolean.valueOf(flag));
        }
    }


    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public boolean canBeReplaced(BlockState p_196253_1_, BlockItemUseContext p_196253_2_) {
        return p_196253_2_.getItemInHand().getItem() == this.asItem() && p_196253_1_.getValue(VASES) < 4 ? true : super.canBeReplaced(p_196253_1_, p_196253_2_);
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        switch(p_220053_1_.getValue(VASES)) {
            case 1:
            default:
                return VASE_SHAPE_ONE;
            case 2:
                return VASE_SHAPE_TWO;
            case 3:
                return VASE_SHAPE_THREE;
            case 4:
                return VASE_SHAPE_FOUR;
        }
    }



    protected boolean mayPlaceOn(BlockState p_200014_1_, IBlockReader p_200014_2_, BlockPos p_200014_3_) {
        return !p_200014_1_.getCollisionShape(p_200014_2_, p_200014_3_).getFaceShape(Direction.UP).isEmpty() || p_200014_1_.isFaceSturdy(p_200014_2_, p_200014_3_, Direction.UP);
    }

    public boolean canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
        BlockPos blockpos = p_196260_3_.below();
        return this.mayPlaceOn(p_196260_2_.getBlockState(blockpos), p_196260_2_, blockpos);
    }

    static {
        VASES = BlockStateProperties.PICKLES;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        VASE_SHAPE_ONE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 12.0D, 12.0D);
        VASE_SHAPE_TWO = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 15.0D, 13.0D);
        VASE_SHAPE_THREE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        VASE_SHAPE_FOUR = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    }
}
