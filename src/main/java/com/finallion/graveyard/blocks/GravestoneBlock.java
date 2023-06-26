package com.finallion.graveyard.blocks;

import com.finallion.graveyard.blockentities.GravestoneBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.contents.LiteralContents;
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
import net.minecraft.world.item.*;
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
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


public class GravestoneBlock extends StandingSignBlock {
    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty FLOOR = BlockStateProperties.BOTTOM;
    private static final VoxelShape SHAPE_FACING_EW = Block.box(4.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);
    private static final VoxelShape SHAPE_FACING_NS = Block.box(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 12.0D);
    private final ResourceLocation texture;

    public GravestoneBlock(ResourceLocation texture) {
        super(BlockBehaviour.Properties.of().noCollission().noOcclusion().sound(SoundType.BASALT).strength(1.5F), WoodType.OAK);
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
        Item $$11 = itemstack.getItem();
        SignApplicator signapplicator2;
        if ($$11 instanceof SignApplicator signapplicator1) {
            signapplicator2 = signapplicator1;
        } else {
            signapplicator2 = null;
        }

        SignApplicator signapplicator = signapplicator2;
        boolean flag1 = signapplicator != null && p_56281_.mayBuild();
        BlockEntity $$12 = p_56279_.getBlockEntity(p_56280_);
        if ($$12 instanceof GravestoneBlockEntity signblockentity) {
            if (!p_56279_.isClientSide) {
                SignText signtext = signblockentity.getText();
                boolean flag = signblockentity.executeClickCommandsIfPresent(p_56281_, p_56279_, p_56280_);

                if (signblockentity.isWaxed()) {
                    p_56279_.playSound((Player)null, signblockentity.getBlockPos(), SoundEvents.WAXED_SIGN_INTERACT_FAIL, SoundSource.BLOCKS);
                    return InteractionResult.PASS;
                } else if (flag1 && !this.otherPlayerIsEditingSign(p_56281_, signblockentity) && signapplicator.canApplyToSign(signtext, p_56281_)) {
                    boolean useOnSign = false;

                    if (signapplicator instanceof DyeItem dyeItem) {
                        useOnSign = useDyeOnSign(p_56279_, signblockentity, dyeItem.getDyeColor());
                    } else if (signapplicator instanceof InkSacItem) {
                        useOnSign = useInkOnSign(p_56279_, signblockentity);
                    } else if (signapplicator instanceof HoneycombItem) {
                        useOnSign = useWaxOnSign(p_56279_, signblockentity);
                    } else if (signapplicator instanceof GlowInkSacItem) {
                        useOnSign = useGlowInkOnSign(p_56279_, signblockentity);
                    }

                    if (useOnSign) {
                        if (!p_56281_.isCreative()) {
                            itemstack.shrink(1);
                        }

                        p_56279_.gameEvent(GameEvent.BLOCK_CHANGE, signblockentity.getBlockPos(), GameEvent.Context.of(p_56281_, signblockentity.getBlockState()));
                        p_56281_.awardStat(Stats.ITEM_USED.get(item));
                        return InteractionResult.SUCCESS;
                    }
                } else if (flag) {
                    return InteractionResult.SUCCESS;
                } else if (!this.otherPlayerIsEditingSign(p_56281_, signblockentity) && p_56281_.mayBuild() && this.hasEditableText(p_56281_, signblockentity)) {
                    this.openEditScreen(p_56281_, signblockentity, p_56279_, p_56280_, p_56278_);
                    return InteractionResult.SUCCESS;
                } else {
                    return InteractionResult.PASS;
                }
            } else {
                return !flag1 && !signblockentity.isWaxed() ? InteractionResult.CONSUME : InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    private boolean hasEditableText(Player p_279394_, GravestoneBlockEntity p_279187_) {
        SignText signtext = p_279187_.getText();
        return Arrays.stream(signtext.getMessages(p_279394_.isTextFilteringEnabled())).allMatch((p_279411_) -> {
            return p_279411_.equals(CommonComponents.EMPTY) || p_279411_.getContents() instanceof LiteralContents;
        });
    }

    public boolean useDyeOnSign(Level world, GravestoneBlockEntity signBlockEntity, DyeColor color) {
        if (signBlockEntity.updateText((text) -> text.setColor(color))) {
            world.playSound(null, signBlockEntity.getBlockPos(), SoundEvents.DYE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            return true;
        } else {
            return false;
        }
    }

    public boolean useInkOnSign(Level world, GravestoneBlockEntity signBlockEntity) {
        if (signBlockEntity.updateText((text) -> {
            return text.setHasGlowingText(false);
        })) {
            world.playSound(null, signBlockEntity.getBlockPos(), SoundEvents.INK_SAC_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            return true;
        } else {
            return false;
        }
    }

    public boolean useGlowInkOnSign(Level world, GravestoneBlockEntity signBlockEntity) {
        if (signBlockEntity.updateText((text) -> text.setHasGlowingText(true))) {
            world.playSound(null, signBlockEntity.getBlockPos(), SoundEvents.GLOW_INK_SAC_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            return true;
        } else {
            return false;
        }
    }

    public boolean useWaxOnSign(Level world, GravestoneBlockEntity signBlockEntity) {
        if (signBlockEntity.setWaxed(true)) {
            world.levelEvent(null, 3003, signBlockEntity.getBlockPos(), 0);
            return true;
        } else {
            return false;
        }
    }

    private boolean otherPlayerIsEditingSign(Player p_277952_, GravestoneBlockEntity p_277599_) {
        UUID uuid = p_277599_.getPlayerWhoMayEdit();
        return uuid != null && !uuid.equals(p_277952_.getUUID());
    }


    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GravestoneBlockEntity(pos, state);
    }

    private void openScreen(Level world, BlockPos pos, Player placer) {
        GravestoneBlockEntity sign = (GravestoneBlockEntity) world.getBlockEntity(pos);
        if (!world.isClientSide) {
            sign.setAllowedPlayerEditor(placer.getUUID());
            ((ServerPlayer) placer).connection.send(new ClientboundOpenSignEditorPacket(pos, true));
        }
    }


    public void openEditScreen(Player player, GravestoneBlockEntity blockEntity, Level world, BlockPos pos, BlockState state) {
        blockEntity.setAllowedPlayerEditor(player.getUUID());
        openScreen(world, pos, player);
    }


    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @org.jetbrains.annotations.Nullable LivingEntity placer, ItemStack p_49851_) {
        if (placer instanceof Player player) {
            openScreen(world, pos, player);
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
    public List<ItemStack> getDrops(BlockState p_287732_, LootParams.Builder p_287596_) {
        return Collections.singletonList(new ItemStack(this));
    }

    public ResourceLocation getTexture() {
        return texture;
    }


    public boolean canSurvive(BlockState p_196260_1_, LevelReader p_196260_2_, BlockPos p_196260_3_) {
        return p_196260_2_.getBlockState(p_196260_3_.below()).isSolid();
    }
}