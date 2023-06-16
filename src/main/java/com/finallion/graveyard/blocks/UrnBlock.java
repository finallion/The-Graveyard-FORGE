package com.finallion.graveyard.blocks;

import com.finallion.graveyard.blockentities.UrnBlockEntity;
import com.finallion.graveyard.init.TGTileEntities;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;


public class UrnBlock extends ContainerBlock implements IWaterLoggable {
    public static final BooleanProperty WATERLOGGED;
    public static final DirectionProperty FACING;
    public static final BooleanProperty OPEN;
    private static final VoxelShape LARGE_URN = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    private static final VoxelShape SMALL_URN = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);

    public UrnBlock() {
        super(BlockBehaviour.Properties.of(Material.GLASS).noOcclusion().sound(SoundType.METAL).strength(0.3F));
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false).setValue(OPEN, false));
    }

    /*
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

     */

    @Override
    public boolean isPathfindable(BlockState p_60475_, IBlockReader p_60476_, BlockPos p_60477_, PathType p_60478_) {
        return false;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState p_49928_, IBlockReader p_49929_, BlockPos p_49930_) {
        return true;
    }

    @Override
    public float getShadeBrightness(BlockState p_60472_, IBlockReader p_60473_, BlockPos p_60474_) {
        return 1.0F;
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        if (this.asBlock().toString().contains("small")) {
            return SMALL_URN;
        }
        return LARGE_URN;
    }



    @Override
    public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
        if (this.asBlock().toString().contains("small")) {
            return SMALL_URN;
        }
        return LARGE_URN;
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public ActionResultType use(BlockState p_49069_, World p_49070_, BlockPos p_49071_, PlayerEntity p_49072_, Hand p_49073_, BlockRayTraceResult p_49074_) {
        if (p_49070_.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            TileEntity blockentity = p_49070_.getBlockEntity(p_49071_);
            if (blockentity instanceof UrnBlockEntity) {
                p_49072_.openMenu((UrnBlockEntity)blockentity);
                p_49072_.awardStat(Stats.OPEN_BARREL);
                PiglinAi.angerNearbyPiglins(p_49072_, true);
            }

            return ActionResultType.CONSUME;
        }
    }



    public void onRemove(BlockState p_49076_, World p_49077_, BlockPos p_49078_, BlockState p_49079_, boolean p_49080_) {
        if (!p_49076_.is(p_49079_.getBlock())) {
            BlockEntity blockentity = p_49077_.getBlockEntity(p_49078_);
            if (blockentity instanceof Container) {
                Containers.dropContents(p_49077_, p_49078_, (Container)blockentity);
                p_49077_.updateNeighbourForOutputSignal(p_49078_, this);
            }

            super.onRemove(p_49076_, p_49077_, p_49078_, p_49079_, p_49080_);
        }
    }


    public void tick(BlockState p_49060_, ServerLevel p_49061_, BlockPos p_49062_, Random p_49063_) {
        BlockEntity blockentity = p_49061_.getBlockEntity(p_49062_);
        if (blockentity instanceof UrnBlockEntity) {
            ((UrnBlockEntity)blockentity).recheckOpen();
        }

    }

    public RenderShape getRenderShape(BlockState p_49090_) {
        return RenderShape.MODEL;
    }

    public void setPlacedBy(Level p_49052_, BlockPos p_49053_, BlockState p_49054_, @Nullable LivingEntity p_49055_, ItemStack p_49056_) {
        if (p_49056_.hasCustomHoverName()) {
            BlockEntity blockentity = p_49052_.getBlockEntity(p_49053_);
            if (blockentity instanceof UrnBlockEntity) {
                ((UrnBlockEntity)blockentity).setCustomName(p_49056_.getHoverName());
            }
        }

    }


    public boolean hasAnalogOutputSignal(BlockState p_149740_1_) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState p_49065_, Level p_49066_, BlockPos p_49067_) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(p_49066_.getBlockEntity(p_49067_));
    }

    public BlockState rotate(BlockState p_49085_, Rotation p_49086_) {
        return p_49085_.setValue(FACING, p_49086_.rotate(p_49085_.getValue(FACING)));
    }

    public BlockState mirror(BlockState p_49082_, Mirror p_49083_) {
        return p_49082_.rotate(p_49083_.getRotation(p_49082_.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(FACING, OPEN, WATERLOGGED);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        Direction direction = context.getHorizontalDirection();
        return this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }


    @Nullable
    public TileEntity newBlockEntity(BlockPos p_152102_, BlockState p_152103_) {
        return TGTileEntities.URN_BLOCK_ENTITY.get().create(p_152102_, p_152103_);
    }


    static {
        FACING = BlockStateProperties.FACING;
        OPEN = BlockStateProperties.OPEN;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
    }



}
