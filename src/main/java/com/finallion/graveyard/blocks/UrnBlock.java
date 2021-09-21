package com.finallion.graveyard.blocks;

import com.finallion.graveyard.blockentities.UrnBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.BarrelTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class UrnBlock extends ContainerBlock implements IWaterLoggable {
    public static final BooleanProperty WATERLOGGED;
    public static final DirectionProperty FACING;
    public static final BooleanProperty OPEN;
    private static final VoxelShape SMALL_URN = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D);

    public UrnBlock() {
        super(AbstractBlock.Properties.of(Material.GLASS).noOcclusion().sound(SoundType.BASALT).strength(0.3F));
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false).setValue(OPEN, false));
    }

    /*
    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }

     */



    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public ActionResultType use(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
        if (p_225533_2_.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            TileEntity tileentity = p_225533_2_.getBlockEntity(p_225533_3_);
            if (tileentity instanceof BarrelTileEntity) {
                p_225533_4_.openMenu((BarrelTileEntity)tileentity);
                p_225533_4_.awardStat(Stats.OPEN_BARREL);
                PiglinTasks.angerNearbyPiglins(p_225533_4_, true);
            }

            return ActionResultType.CONSUME;
        }
    }

    public void onRemove(BlockState p_196243_1_, World p_196243_2_, BlockPos p_196243_3_, BlockState p_196243_4_, boolean p_196243_5_) {
        if (!p_196243_1_.is(p_196243_4_.getBlock())) {
            TileEntity tileentity = p_196243_2_.getBlockEntity(p_196243_3_);
            if (tileentity instanceof IInventory) {
                InventoryHelper.dropContents(p_196243_2_, p_196243_3_, (IInventory)tileentity);
                p_196243_2_.updateNeighbourForOutputSignal(p_196243_3_, this);
            }

            super.onRemove(p_196243_1_, p_196243_2_, p_196243_3_, p_196243_4_, p_196243_5_);
        }
    }

    public void tick(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_) {
        TileEntity tileentity = p_225534_2_.getBlockEntity(p_225534_3_);
        if (tileentity instanceof BarrelTileEntity) {
            ((BarrelTileEntity)tileentity).recheckOpen();
        }

    }

    @Nullable
    public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
        return new BarrelTileEntity();
    }

    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.MODEL;
    }

    public void setPlacedBy(World p_180633_1_, BlockPos p_180633_2_, BlockState p_180633_3_, @Nullable LivingEntity p_180633_4_, ItemStack p_180633_5_) {
        if (p_180633_5_.hasCustomHoverName()) {
            TileEntity tileentity = p_180633_1_.getBlockEntity(p_180633_2_);
            if (tileentity instanceof BarrelTileEntity) {
                ((BarrelTileEntity)tileentity).setCustomName(p_180633_5_.getHoverName());
            }
        }

    }

    public boolean hasAnalogOutputSignal(BlockState p_149740_1_) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState p_180641_1_, World p_180641_2_, BlockPos p_180641_3_) {
        return Container.getRedstoneSignalFromBlockEntity(p_180641_2_.getBlockEntity(p_180641_3_));
    }

    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return p_185499_1_.setValue(FACING, p_185499_2_.rotate(p_185499_1_.getValue(FACING)));
    }

    public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_) {
        return p_185471_1_.rotate(p_185471_2_.getRotation(p_185471_1_.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(FACING, OPEN, WATERLOGGED);
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        Direction direction = context.getNearestLookingDirection();
        return this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    static {
        FACING = HorizontalFaceBlock.FACING;
        OPEN = BlockStateProperties.OPEN;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
    }


}
