//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.minecraft.block;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.List;
import java.util.function.ToIntFunction;

public class CandleBlock extends net.minecraft.block.AbstractCandleBlock implements Waterloggable {
    public static final int field_31050 = 1;
    public static final int MAX_CANDLE_AMOUNT = 4;
    public static final IntProperty CANDLES;
    public static final BooleanProperty LIT;
    public static final BooleanProperty WATERLOGGED;
    public static final ToIntFunction<BlockState> STATE_TO_LUMINANCE;
    private static final Int2ObjectMap<List<Vec3d>> CANDLES_TO_PARTICLE_OFFSETS;
    private static final VoxelShape ONE_CANDLE_SHAPE;
    private static final VoxelShape TWO_CANDLES_SHAPE;
    private static final VoxelShape THREE_CANDLES_SHAPE;
    private static final VoxelShape FOUR_CANDLES_SHAPE;

    public CandleBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(CANDLES, 1)).with(LIT, false)).with(WATERLOGGED, false));
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getAbilities().allowModifyWorld && player.getStackInHand(hand).isEmpty() && (Boolean)state.get(LIT)) {
            extinguish(player, state, world, pos);
            return ActionResult.success(world.isClient);
        } else {
            return ActionResult.PASS;
        }
    }

    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.shouldCancelInteraction() && context.getStack().getItem() == this.asItem() && (Integer)state.get(CANDLES) < 4 ? true : super.canReplace(state, context);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (blockState.isOf(this)) {
            return (BlockState)blockState.cycle(CANDLES);
        } else {
            FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
            boolean bl = fluidState.getFluid() == Fluids.WATER;
            return (BlockState)super.getPlacementState(ctx).with(WATERLOGGED, bl);
        }
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if ((Boolean)state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch((Integer)state.get(CANDLES)) {
            case 1:
            default:
                return ONE_CANDLE_SHAPE;
            case 2:
                return TWO_CANDLES_SHAPE;
            case 3:
                return THREE_CANDLES_SHAPE;
            case 4:
                return FOUR_CANDLES_SHAPE;
        }
    }

    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(new Property[]{CANDLES, LIT, WATERLOGGED});
    }

    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        if (!(Boolean)state.get(WATERLOGGED) && fluidState.getFluid() == Fluids.WATER) {
            BlockState blockState = (BlockState)state.with(WATERLOGGED, true);
            if ((Boolean)state.get(LIT)) {
                extinguish((PlayerEntity)null, blockState, world, pos);
            } else {
                world.setBlockState(pos, blockState, 3);
            }

            world.scheduleFluidTick(pos, fluidState.getFluid(), fluidState.getFluid().getTickRate(world));
            return true;
        } else {
            return false;
        }
    }

    public static boolean canBeLit(BlockState state) {
        return state.isIn(BlockTags.CANDLES, (statex) -> {
            return statex.contains(LIT) && statex.contains(WATERLOGGED);
        }) && !(Boolean)state.get(LIT) && !(Boolean)state.get(WATERLOGGED);
    }

    protected Iterable<Vec3d> getParticleOffsets(BlockState state) {
        return (Iterable)CANDLES_TO_PARTICLE_OFFSETS.get((Integer)state.get(CANDLES));
    }

    protected boolean isNotLit(BlockState state) {
        return !(Boolean)state.get(WATERLOGGED) && super.isNotLit(state);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return Block.sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }

    static {
        CANDLES = Properties.CANDLES;
        LIT = net.minecraft.block.AbstractCandleBlock.LIT;
        WATERLOGGED = Properties.WATERLOGGED;
        STATE_TO_LUMINANCE = (state) -> {
            return (Boolean)state.get(LIT) ? 3 * (Integer)state.get(CANDLES) : 0;
        };
        CANDLES_TO_PARTICLE_OFFSETS = (Int2ObjectMap)Util.make(() -> {
            Int2ObjectMap<List<Vec3d>> int2ObjectMap = new Int2ObjectOpenHashMap();
            int2ObjectMap.defaultReturnValue(ImmutableList.of());
            int2ObjectMap.put(1, ImmutableList.of(new Vec3d(0.5D, 0.5D, 0.5D)));
            int2ObjectMap.put(2, ImmutableList.of(new Vec3d(0.375D, 0.44D, 0.5D), new Vec3d(0.625D, 0.5D, 0.44D)));
            int2ObjectMap.put(3, ImmutableList.of(new Vec3d(0.5D, 0.313D, 0.625D), new Vec3d(0.375D, 0.44D, 0.5D), new Vec3d(0.56D, 0.5D, 0.44D)));
            int2ObjectMap.put(4, ImmutableList.of(new Vec3d(0.44D, 0.313D, 0.56D), new Vec3d(0.625D, 0.44D, 0.56D), new Vec3d(0.375D, 0.44D, 0.375D), new Vec3d(0.56D, 0.5D, 0.375D)));
            return Int2ObjectMaps.unmodifiable(int2ObjectMap);
        });
        ONE_CANDLE_SHAPE = Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 6.0D, 9.0D);
        TWO_CANDLES_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 6.0D, 11.0D, 6.0D, 9.0D);
        THREE_CANDLES_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 6.0D, 10.0D, 6.0D, 11.0D);
        FOUR_CANDLES_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 10.0D);
    }
}
