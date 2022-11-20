package com.finallion.graveyard.blocks;

import com.finallion.graveyard.blockentities.GravestoneBlockEntity;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ClientboundOpenSignEditorPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Collections;
import java.util.List;


public class GravestoneBlock extends StandingSignBlock {
    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty FLOOR = BlockStateProperties.BOTTOM;
    private static final VoxelShape SHAPE_FACING_EW = Block.box(4.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);
    private static final VoxelShape SHAPE_FACING_NS = Block.box(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 12.0D);
    private final ResourceLocation texture;

    public GravestoneBlock(ResourceLocation texture) {
        super(BlockBehaviour.Properties.of(Material.STONE).noCollission().noOcclusion().sound(SoundType.BASALT).strength(1.5F), WoodType.OAK);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(FLOOR, true).setValue(WATERLOGGED, false).setValue(ROTATION, 0));
        this.texture = texture;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, FLOOR, WATERLOGGED, ROTATION);
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        if (Direction.NORTH == state.getValue(FACING) || Direction.SOUTH == state.getValue(FACING)) {
            return SHAPE_FACING_NS;
        } else {
            return SHAPE_FACING_EW;
        }
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }



    public InteractionResult use(BlockState p_56278_, Level p_56279_, BlockPos p_56280_, Player p_56281_, InteractionHand p_56282_, BlockHitResult p_56283_) {
        ItemStack itemstack = p_56281_.getItemInHand(p_56282_);
        Item item = itemstack.getItem();
        boolean flag = item instanceof DyeItem;
        boolean flag1 = itemstack.is(Items.GLOW_INK_SAC);
        boolean flag2 = itemstack.is(Items.INK_SAC);
        boolean flag3 = (flag1 || flag || flag2) && p_56281_.getAbilities().mayBuild;
        if (p_56279_.isClientSide) {
            return flag3 ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
        } else {
            BlockEntity blockentity = p_56279_.getBlockEntity(p_56280_);
            if (!(blockentity instanceof GravestoneBlockEntity)) {
                return InteractionResult.PASS;
            } else {
                GravestoneBlockEntity signblockentity = (GravestoneBlockEntity) blockentity;
                boolean flag4 = signblockentity.hasGlowingText();
                if ((!flag1 || !flag4) && (!flag2 || flag4)) {
                    if (flag3) {
                        boolean flag5;
                        if (flag1) {
                            p_56279_.playSound((Player) null, p_56280_, SoundEvents.GLOW_INK_SAC_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                            flag5 = signblockentity.setHasGlowingText(true);
                            if (p_56281_ instanceof ServerPlayer) {
                                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) p_56281_, p_56280_, itemstack);
                            }
                        } else if (flag2) {
                            p_56279_.playSound((Player) null, p_56280_, SoundEvents.INK_SAC_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                            flag5 = signblockentity.setHasGlowingText(false);
                        } else {
                            p_56279_.playSound((Player) null, p_56280_, SoundEvents.DYE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                            flag5 = signblockentity.setColor(((DyeItem) item).getDyeColor());
                        }

                        if (flag5) {
                            if (!p_56281_.isCreative()) {
                                itemstack.shrink(1);
                            }

                            p_56281_.awardStat(Stats.ITEM_USED.get(item));
                        }
                    }

                    return signblockentity.executeClickCommands((ServerPlayer) p_56281_) ? InteractionResult.SUCCESS : InteractionResult.PASS;
                } else {
                    return InteractionResult.PASS;
                }
            }
        }
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GravestoneBlockEntity(pos, state);
    }


    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @org.jetbrains.annotations.Nullable LivingEntity placer, ItemStack p_49851_) {
        if (placer != null && placer instanceof Player) {
            GravestoneBlockEntity sign = (GravestoneBlockEntity) world.getBlockEntity(pos);
            if (!world.isClientSide) {
                sign.setAllowedPlayerEditor(placer.getUUID());
                ((ServerPlayer) placer).connection.send(new ClientboundOpenSignEditorPacket(pos));
            }
            else
                sign.setEditable(true);
        }
    }


    @Override
    public boolean isPathfindable(BlockState p_60475_, BlockGetter p_60476_, BlockPos p_60477_, PathComputationType p_60478_) {
        return false;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter p_220071_2_, BlockPos p_220071_3_, CollisionContext p_220071_4_) {
        if (Direction.NORTH == state.getValue(FACING) || Direction.SOUTH == state.getValue(FACING)) {
            return SHAPE_FACING_NS;
        } else {
            return SHAPE_FACING_EW;
        }
    }



    public BlockState updateShape(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, LevelAccessor p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        if (p_196271_1_.getValue(WATERLOGGED)) {
            p_196271_4_.scheduleTick(p_196271_5_, Fluids.WATER, Fluids.WATER.getTickDelay(p_196271_4_));
        }

        return super.updateShape(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
    }


    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        Direction direction = context.getHorizontalDirection();
        return this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    @Override
    public List<ItemStack> getDrops(BlockState p_60537_, LootContext.Builder p_60538_) {
        return Collections.singletonList(new ItemStack(this));
    }


    public ResourceLocation getTexture() {
        return texture;
    }


    public boolean canSurvive(BlockState p_196260_1_, LevelReader p_196260_2_, BlockPos p_196260_3_) {
        return p_196260_2_.getBlockState(p_196260_3_.below()).getMaterial().isSolid();
    }
}