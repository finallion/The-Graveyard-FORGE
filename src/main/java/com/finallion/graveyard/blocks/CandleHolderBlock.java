package com.finallion.graveyard.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFaceBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;

public class CandleHolderBlock extends Block implements IWaterLoggable {
    protected static final VoxelShape SHAPE_NORTH = Block.box(4.0D, 2.0D, 0.0D, 12.0D, 16.0D, 11.0D);
    protected static final VoxelShape SHAPE_SOUTH = Block.box(4.0D, 2.0D, 5.0D, 12.0D, 16.0D, 16.0D);
    protected static final VoxelShape SHAPE_EAST = Block.box(5.0D, 2.0D, 4.0D, 16.0D, 16.0D, 12.0D);
    protected static final VoxelShape SHAPE_WEST = Block.box(0.0D, 2.0D, 4.0D, 11.0D, 16.0D, 12.0D);
    public static final DirectionProperty FACING;
    public static final BooleanProperty WATERLOGGED;

    public CandleHolderBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }


    public VoxelShape getShape(BlockState p_51309_, IBlockReader p_51310_, BlockPos p_51311_, ISelectionContext p_51312_) {
        switch (p_51309_.getValue(FACING)) {
            case SOUTH:
                return SHAPE_SOUTH;
            case EAST:
                return SHAPE_EAST;
            case WEST:
                return SHAPE_WEST;
            default:
                return SHAPE_NORTH;
        }
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
        return (BlockState) this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection()).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }


    public BlockState updateShape(BlockState p_51298_, Direction p_51299_, BlockState p_51300_, IWorld p_51301_, BlockPos p_51302_, BlockPos p_51303_) {
        if (p_51298_.getValue(WATERLOGGED)) {
            p_51301_.getLiquidTicks().scheduleTick(p_51302_, Fluids.WATER, Fluids.WATER.getTickDelay(p_51301_));
        }

        return p_51299_ == Direction.DOWN ? p_51298_ : super.updateShape(p_51298_, p_51299_, p_51300_, p_51301_, p_51302_, p_51303_);
    }


    public FluidState getFluidState(BlockState p_51318_) {
        return p_51318_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_51318_);
    }


    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_51305_) {
        p_51305_.add(WATERLOGGED, FACING);
    }

    public BlockState rotate(BlockState p_51295_, Rotation p_51296_) {
        return p_51295_.setValue(FACING, p_51296_.rotate(p_51295_.getValue(FACING)));
    }

    static {
        FACING = HorizontalFaceBlock.FACING;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
    }
}
