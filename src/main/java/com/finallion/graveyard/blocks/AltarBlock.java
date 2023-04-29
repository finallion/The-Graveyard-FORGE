package com.finallion.graveyard.blocks;


import com.finallion.graveyard.init.TGBlocks;
import com.finallion.graveyard.init.TGSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.Random;

public class AltarBlock extends Block {
    public static final BooleanProperty BLOODY = BooleanProperty.create("bloody");
    private static BlockPattern COMPLETED_ALTAR;


    public AltarBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(BLOODY, false)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BLOODY);
    }


    public void animateTick(BlockState state, Level world, BlockPos pos, Random random) {
        super.animateTick(state, world, pos, random);
        if (state.getValue(BLOODY)) {
            if (random.nextInt(10) == 0) {
                world.playLocalSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, TGSounds.ALTAR_AMBIENT.get(), SoundSource.BLOCKS, 0.05F, random.nextFloat() * 0.4F + 0.8F, true);
            }
        }

    }

    public static BlockPattern getCompletedFramePattern() {
        if (COMPLETED_ALTAR == null) {
            COMPLETED_ALTAR = BlockPatternBuilder.start().aisle("???x???", "???????", "???????", "???????", "???????", "???????", "???????", "a??b??c")
                    .where('?', BlockInWorld.hasState(BlockStatePredicate.ANY))
                    .where('a', BlockInWorld.hasState(BlockStatePredicate.forBlock(TGBlocks.LOWER_BONE_STAFF.get()).or(BlockStatePredicate.forBlock(TGBlocks.UPPER_BONE_STAFF.get()))))
                    .where('b', BlockInWorld.hasState(BlockStatePredicate.forBlock(TGBlocks.MIDDLE_BONE_STAFF.get())))
                    .where('c', BlockInWorld.hasState(BlockStatePredicate.forBlock(TGBlocks.UPPER_BONE_STAFF.get()).or(BlockStatePredicate.forBlock(TGBlocks.LOWER_BONE_STAFF.get()))))
                    .where('x', BlockInWorld.hasState(BlockStatePredicate.forBlock(TGBlocks.ALTAR.get())))
                    .build();
        }

        return COMPLETED_ALTAR;
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult p_60508_) {
        ItemStack stack = player.getItemInHand(hand);

        float blood = 0.0F;
        if (stack.is(TGItems.VIAL_OF_BLOOD.get())) {
            blood = VialOfBlood.getBlood(stack);
        }

        if (state.is(TGBlocks.ALTAR.get()) && (blood >= 0.8F || GraveyardConfig.COMMON.isBossSummonableItem.get().contains(stack.getItem().getDescriptionId())) && world.getDifficulty() != Difficulty.PEACEFUL && (world.isNight() || world.dimensionType().hasFixedTime())) {
            BlockPattern.BlockPatternMatch result = AltarBlock.getCompletedFramePattern().find(world, pos);

            if (!state.getValue(AltarBlock.BLOODY) && (result != null || !GraveyardConfig.COMMON.summoningNeedsStaffFragments.get())) {
                player.level.playSound(null, player.blockPosition(), TGSounds.VIAL_SPLASH.get(), SoundSource.BLOCKS, 5.0F, 1.0F);
                world.setBlock(pos, state.setValue(AltarBlock.BLOODY, true), 3);

                Direction direction;

                if (result == null) {
                    direction = Direction.NORTH;
                } else {
                    direction = result.getUp();
                }

                if (!world.isClientSide()) {
                    if (!player.isCreative()) {
                        ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
                        ItemUtils.createFilledResult(stack, player, bottle);
                        player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 430));
                    }

                    BlockPos corner = pos.offset(-8, 0, -8);

                    // searches square around altar
                    for(int i = 0; i < 16; ++i) {
                        for(int j = 0; j < 16; ++j) {
                            for(int k = 0; k < 2;++k) {
                                BlockPos iteratorPos = new BlockPos(corner.offset(i, k, j));
                                BlockState blockState = world.getBlockState(iteratorPos);

                                if (blockState.getBlock() instanceof OminousBoneStaffFragment) {
                                    world.setBlock(iteratorPos, Blocks.AIR.defaultBlockState(), 3);
                                }
                            }
                        }
                    }

                    LichEntity lich = (LichEntity) TGEntities.LICH.get().create(world);
                    BlockPos blockPos = pos.above();
                    lich.setYHeadRot(direction.getOpposite().toYRot());
                    lich.setYBodyRot(direction.getOpposite().toYRot());
                    lich.setYRot(direction.getOpposite().toYRot());
                    lich.moveTo((double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.55D, (double)blockPos.getZ() + 0.5D, 0.0F, 0.0F);
                    lich.onSummoned(direction.getOpposite(), pos.above());


                    for (ServerPlayer serverplayer : world.getEntitiesOfClass(ServerPlayer.class, lich.getBoundingBox().inflate(50.0D))) {
                        CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayer, lich);
                    }



                    world.addFreshEntity(lich);
                    lich.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 5));

                    return InteractionResult.CONSUME;
                }

                return InteractionResult.sidedSuccess(player.level.isClientSide);
            }
        }


        return super.use(state, world, pos, player, hand, p_60508_);
    }
}
