package com.finallion.graveyard.blocks;

import com.finallion.graveyard.blockentities.GravestoneBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;


public class GravestoneBlock extends StandingSignBlock {
    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;
    public static final DirectionProperty FACING = HorizontalFaceBlock.FACING;
    public static final BooleanProperty FLOOR = BlockStateProperties.BOTTOM;
    private final ResourceLocation texture;

    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);

    public GravestoneBlock(ResourceLocation texture) {
        super(AbstractBlock.Properties.of(Material.STONE).noCollission().noOcclusion().sound(SoundType.BASALT).strength(1.5F), WoodType.OAK);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(FLOOR, true).setValue(WATERLOGGED, false));
        this.texture = texture;
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, FLOOR, WATERLOGGED, ROTATION);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }



    public ActionResultType use(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
        ItemStack itemstack = p_225533_4_.getItemInHand(p_225533_5_);
        boolean flag = itemstack.getItem() instanceof DyeItem && p_225533_4_.abilities.mayBuild;
        if (p_225533_2_.isClientSide) {
            return flag ? ActionResultType.SUCCESS : ActionResultType.CONSUME;
        } else {
            TileEntity tileentity = p_225533_2_.getBlockEntity(p_225533_3_);
            if (tileentity instanceof GravestoneBlockEntity) {
                GravestoneBlockEntity signtileentity = (GravestoneBlockEntity)tileentity;
                if (flag) {
                    boolean flag1 = signtileentity.setColor(((DyeItem)itemstack.getItem()).getDyeColor());
                    if (flag1 && !p_225533_4_.isCreative()) {
                        itemstack.shrink(1);
                    }
                }

                return signtileentity.executeClickCommands(p_225533_4_) ? ActionResultType.SUCCESS : ActionResultType.PASS;
            } else {
                return ActionResultType.PASS;
            }
        }
    }

    @Override
    public void onPlace(BlockState p_220082_1_, World p_220082_2_, BlockPos p_220082_3_, BlockState p_220082_4_, boolean p_220082_5_) {
        super.onPlace(p_220082_1_, p_220082_2_, p_220082_3_, p_220082_4_, p_220082_5_);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new GravestoneBlockEntity();
    }

    //public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
    //    return new GravestoneBlockEntity();
    //}

    /*
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (placer != null && placer instanceof PlayerEntity) {
            GravestoneBlockEntity sign = (GravestoneBlockEntity) world.getBlockEntity(pos);
            if (!world.isClient) {
                sign.setEditor(((PlayerEntity) placer).inventory.player);
                ((ServerPlayerEntity) placer).networkHandler.connection.send(new SignEditorOpenS2CPacket(pos));
            }
            else
                sign.setEditable(true);
        }
    }

     */



    public BlockState updateShape(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        if (p_196271_1_.getValue(WATERLOGGED)) {
            p_196271_4_.getLiquidTicks().scheduleTick(p_196271_5_, Fluids.WATER, Fluids.WATER.getTickDelay(p_196271_4_));
        }

        return super.updateShape(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
    }


    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        Direction direction = context.getNearestLookingDirection();
        return this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack(this));
    }



    public ResourceLocation getTexture() {
        return texture;
    }



    public boolean canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
        return p_196260_2_.getBlockState(p_196260_3_.below()).getMaterial().isSolid();
    }
}