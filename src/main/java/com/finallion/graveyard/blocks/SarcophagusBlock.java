package com.finallion.graveyard.blocks;

import com.finallion.graveyard.blockentities.SarcophagusBlockEntity;
import com.finallion.graveyard.blockentities.enums.SarcophagusPart;
import com.finallion.graveyard.entities.WraithEntity;
import com.finallion.graveyard.init.TGAdvancements;
import com.finallion.graveyard.init.TGEntities;
import com.finallion.graveyard.init.TGTileEntities;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.BiPredicate;

/*
THINGS TO CHECK IF THE MODEL CASTS UNWANTED SHADOWS:
- Is the hitbox smaller than a block? (!!!)
- It "ambientocclusion:false" set in the model json (see beacon model)?
- "shade:false" under parts in the model json might help (see torch model).
- Is the model renderer set up correctly? Especially the light value (look at chest/bed)?
- Make your block cast light.
- Methods: translucent and ambient occlusion from abstract block class might help.
- Set model sizes from 16 to 15.9 where shadow occurs.
- renderFlat in model renderer might help.
 */

public class SarcophagusBlock extends AbstractCoffinBlock<SarcophagusBlockEntity> implements SimpleWaterloggedBlock {
    public static final int EVENT_SET_OPEN_COUNT = 1;
    protected static final VoxelShape DOUBLE_NORTH_SHAPE = Block.box(1.0D, 1.0D, 1.0D, 15.0D, 14.0D, 15.0D);;
    protected static final VoxelShape DOUBLE_SOUTH_SHAPE = Block.box(1.0D, 1.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    protected static final VoxelShape DOUBLE_WEST_SHAPE = Block.box(1.0D, 1.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    protected static final VoxelShape DOUBLE_EAST_SHAPE = Block.box(1.0D, 1.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<SarcophagusPart> PART = EnumProperty.create("part", SarcophagusPart.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final BooleanProperty PLAYER_PLACED = BlockStateProperties.LOCKED;
    public static final BooleanProperty IS_COFFIN = BlockStateProperties.LIT;
    private final String lidRL;
    private final String baseRL;

    // open state missing
    public SarcophagusBlock(Properties properties, boolean isCoffin, String lid, String base) {
        super(properties, TGTileEntities.SARCOPHAGUS_BLOCK_ENTITY::get);
        this.registerDefaultState(this.stateDefinition.any().setValue(PART, SarcophagusPart.FOOT).setValue(WATERLOGGED, false).setValue(PLAYER_PLACED, false).setValue(IS_COFFIN, isCoffin));
        this.baseRL = base;
        this.lidRL = lid;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_51562_) {
        p_51562_.add(WATERLOGGED, OPEN, FACING, PART, PLAYER_PLACED, IS_COFFIN);
    }

    public FluidState getFluidState(BlockState p_51581_) {
        return p_51581_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_51581_);
    }


    public BlockState updateShape(BlockState p_49525_, Direction p_49526_, BlockState p_49527_, LevelAccessor p_49528_, BlockPos p_49529_, BlockPos p_49530_) {
        if (p_49526_ == getNeighbourDirection(p_49525_.getValue(PART), p_49525_.getValue(FACING))) {
            return p_49527_.is(this) && p_49527_.getValue(PART) != p_49525_.getValue(PART) ? p_49525_ : Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(p_49525_, p_49526_, p_49527_, p_49528_, p_49529_, p_49530_);
        }
    }

    private static Direction getNeighbourDirection(SarcophagusPart p_49534_, Direction p_49535_) {
        return p_49534_ == SarcophagusPart.FOOT ? p_49535_ : p_49535_.getOpposite();
    }

    @Override
    public boolean isPathfindable(BlockState p_60475_, BlockGetter p_60476_, BlockPos p_60477_, PathComputationType p_60478_) {
        return false;
    }

    public VoxelShape getShape(BlockState p_51569_, BlockGetter p_51570_, BlockPos p_51571_, CollisionContext p_51572_) {
        switch (p_51569_.getValue(FACING)) {
            case NORTH:
            default:
                return DOUBLE_NORTH_SHAPE;
            case SOUTH:
                return DOUBLE_SOUTH_SHAPE;
            case WEST:
                return DOUBLE_WEST_SHAPE;
            case EAST:
                return DOUBLE_EAST_SHAPE;
        }

    }


    public InteractionResult use(BlockState p_49515_, Level p_49516_, BlockPos p_49517_, Player p_49518_, InteractionHand p_49519_, BlockHitResult p_49520_) {
        Random random = new Random();

        if (p_49516_.isClientSide) {
            return InteractionResult.CONSUME;
        } else {
            BlockPos original = p_49517_;
            if (p_49515_.getValue(PART) == SarcophagusPart.HEAD) {
                p_49517_ = p_49517_.relative(p_49515_.getValue(FACING).getOpposite());
            }

            MenuProvider menuprovider = this.getMenuProvider(p_49515_, p_49516_, p_49517_);
            if (menuprovider != null) {
                p_49518_.openMenu(menuprovider);
            }

            spawnGhost(p_49515_, p_49516_, original, p_49518_, random);

            return InteractionResult.CONSUME;
        }
    }

    private static Direction getDirectionTowardsOtherPart(SarcophagusPart part, Direction direction) {
        return part == SarcophagusPart.FOOT ? direction : direction.getOpposite();
    }

    public static void spawnGhost(BlockState state, Level world, BlockPos pos, Player player, Random random) {
        if (!state.getValue(PLAYER_PLACED) && random.nextInt(4) == 0 && pos.getY() < 62) {
            BlockPos entityPos = pos;
            for (int i = 0; i < 10; i++) { // 10 spawn attempts to find air, else just spawn
                entityPos = player.getOnPos().offset(-2 + random.nextInt(5), 1, -2 + random.nextInt(5));
                if (world.getBlockState(entityPos).isAir() && world.getBlockState(entityPos.above()).isAir()) {
                    break;
                }
            }
            WraithEntity ghost = TGEntities.WRAITH.get().create(world);
            ghost.moveTo(entityPos, 0.0F, 0.0F);
            world.addFreshEntity(ghost);
            world.setBlock(pos, state.setValue(PLAYER_PLACED, true), 3);
            BlockPos otherPartPos = pos.relative(getDirectionTowardsOtherPart(state.getValue(PART), state.getValue(FACING)));
            BlockState otherPart = world.getBlockState(otherPartPos);
            if (player instanceof ServerPlayer) {
                TGAdvancements.SPAWN_WRAITH.trigger((ServerPlayer) player);
            }
            world.setBlock(otherPartPos, otherPart.setValue(PLAYER_PLACED, true), 3);
        }
    }


    public void setPlacedBy(Level p_49499_, BlockPos p_49500_, BlockState p_49501_, @javax.annotation.Nullable LivingEntity p_49502_, ItemStack p_49503_) {
        super.setPlacedBy(p_49499_, p_49500_, p_49501_, p_49502_, p_49503_);
        if (!p_49499_.isClientSide) {
            BlockPos blockpos = p_49500_.relative(p_49501_.getValue(FACING));
            p_49499_.setBlock(p_49500_, p_49501_.setValue(PLAYER_PLACED, true), 3);
            p_49499_.setBlock(blockpos, p_49501_.setValue(PART, SarcophagusPart.HEAD).setValue(PLAYER_PLACED, true), 3);
            p_49499_.blockUpdated(p_49500_, Blocks.AIR);
            p_49501_.updateNeighbourShapes(p_49499_, p_49500_, 3);
        }

    }


    public BlockState rotate(BlockState p_51552_, Rotation p_51553_) {
        return p_51552_.setValue(FACING, p_51553_.rotate(p_51552_.getValue(FACING)));
    }

    public BlockState mirror(BlockState p_54122_, Mirror p_54123_) {
        return p_54122_.rotate(p_54123_.getRotation(p_54122_.getValue(FACING)));
    }


    public long getSeed(BlockState p_49522_, BlockPos p_49523_) {
        BlockPos blockpos = p_49523_.relative(p_49522_.getValue(FACING), p_49522_.getValue(PART) == SarcophagusPart.HEAD ? 0 : 1);
        return Mth.getSeed(blockpos.getX(), p_49523_.getY(), blockpos.getZ());
    }


    public static DoubleBlockCombiner.BlockType getBlockType(BlockState state) {
        SarcophagusPart bedPart = (SarcophagusPart) state.getValue(PART);
        return bedPart == SarcophagusPart.HEAD ? DoubleBlockCombiner.BlockType.FIRST : DoubleBlockCombiner.BlockType.SECOND;
    }


    public void playerWillDestroy(Level p_49505_, BlockPos p_49506_, BlockState p_49507_, Player p_49508_) {
        if (!p_49505_.isClientSide && p_49508_.isCreative()) {
            SarcophagusPart part = p_49507_.getValue(PART);
            if (part == SarcophagusPart.FOOT) {
                BlockPos blockpos = p_49506_.relative(getNeighbourDirection(part, p_49507_.getValue(FACING)));
                BlockState blockstate = p_49505_.getBlockState(blockpos);
                if (blockstate.is(this) && blockstate.getValue(PART) == SarcophagusPart.HEAD) {
                    p_49505_.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
                    p_49505_.levelEvent(p_49508_, 2001, blockpos, Block.getId(blockstate));
                }
            }
        }

        super.playerWillDestroy(p_49505_, p_49506_, p_49507_, p_49508_);
    }


    public void onRemove(BlockState p_51538_, Level p_51539_, BlockPos p_51540_, BlockState p_51541_, boolean p_51542_) {
        if (!p_51538_.is(p_51541_.getBlock())) {
            BlockEntity blockentity = p_51539_.getBlockEntity(p_51540_);
            if (blockentity instanceof Container) {
                Containers.dropContents(p_51539_, p_51540_, (Container)blockentity);
                p_51539_.updateNeighbourForOutputSignal(p_51540_, this);
            }

            super.onRemove(p_51538_, p_51539_, p_51540_, p_51541_, p_51542_);
        }
    }


    public void tick(BlockState p_153059_, ServerLevel p_153060_, BlockPos p_153061_, Random p_153062_) {
        BlockEntity blockentity = p_153060_.getBlockEntity(p_153061_);
        if (blockentity instanceof SarcophagusBlockEntity) {
            ((SarcophagusBlockEntity)blockentity).recheckOpen();
        }

    }


    public BlockEntity newBlockEntity(BlockPos p_153064_, BlockState p_153065_) {
        return new SarcophagusBlockEntity(p_153064_, p_153065_);
    }

    public RenderShape getRenderShape(BlockState p_51567_) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }


    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext p_49479_) {
        FluidState fluidstate = p_49479_.getLevel().getFluidState(p_49479_.getClickedPos());
        Direction direction = p_49479_.getHorizontalDirection();
        BlockPos blockpos = p_49479_.getClickedPos();
        BlockPos blockpos1 = blockpos.relative(direction);
        Level level = p_49479_.getLevel();
        return level.getBlockState(blockpos1).canBeReplaced(p_49479_) && level.getWorldBorder().isWithinBounds(blockpos1) ? this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)) : null;
    }



    public static Direction getConnectedDirection(BlockState p_51585_) {
        Direction direction = p_51585_.getValue(FACING);
        return p_51585_.getValue(PART) == SarcophagusPart.HEAD ? direction.getOpposite() : direction;
    }

    public String getLid() {
        return lidRL;
    }

    public String getBase() {
        return baseRL;
    }

    /*
    ANIMATION STUFF
     */


    public static DoubleBlockCombiner.Combiner<SarcophagusBlockEntity, Float2FloatFunction> opennessCombiner(final LidBlockEntity p_51518_) {
        return new DoubleBlockCombiner.Combiner<>() {
            public Float2FloatFunction acceptDouble(SarcophagusBlockEntity p_51633_, SarcophagusBlockEntity p_51634_) {
                return (p_51638_) -> {
                    return Math.max(p_51633_.getOpenNess(p_51638_), p_51634_.getOpenNess(p_51638_));
                };
            }

            public Float2FloatFunction acceptSingle(SarcophagusBlockEntity p_51631_) {
                return p_51631_::getOpenNess;
            }

            public Float2FloatFunction acceptNone() {
                return p_51518_::getOpenNess;
            }
        };
    }


    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153055_, BlockState p_153056_, BlockEntityType<T> p_153057_) {
        return p_153055_.isClientSide ? createTickerHelper(p_153057_, this.blockEntityType(), SarcophagusBlockEntity::lidAnimateTick) : null;
    }


    public DoubleBlockCombiner.NeighborCombineResult<? extends SarcophagusBlockEntity> combine(BlockState p_51544_, Level p_51545_, BlockPos p_51546_, boolean p_51547_) {
        BiPredicate<LevelAccessor, BlockPos> bipredicate;
        bipredicate = (world, pos) -> {
            return false;
        };
        return DoubleBlockCombiner.combineWithNeigbour(this.blockEntityType.get(), SarcophagusBlock::getBlockType, SarcophagusBlock::getConnectedDirection, FACING, p_51544_, p_51545_, p_51546_, bipredicate);
    }


    public BlockEntityType<? extends SarcophagusBlockEntity> blockEntityType() {
        return this.blockEntityType.get();
    }





}
