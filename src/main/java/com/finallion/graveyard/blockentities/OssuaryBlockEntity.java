package com.finallion.graveyard.blockentities;


import com.finallion.graveyard.blocks.OssuaryBlock;
import com.finallion.graveyard.init.TGTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;


public class OssuaryBlockEntity extends BlockEntity implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private final AnimationBuilder OPEN = new AnimationBuilder().addAnimation("open", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME);
    private final AnimationBuilder CLOSE = new AnimationBuilder().addAnimation("close", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME);
    private boolean playedSound = false;

    public OssuaryBlockEntity(BlockPos pos, BlockState state) {
        super(TGTileEntities.OSSUARY_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationState state = event.getController().getAnimationState();
        if (this.getBlockState().getValue(OssuaryBlock.OPEN)) {
            if (state == AnimationState.Stopped)  {
                if (level != null && !playedSound) {
                    Player playerEntity = level.getNearestPlayer((double)worldPosition.getX() + 0.5D, (double)worldPosition.getY() + 0.5D, (double)worldPosition.getZ() + 0.5D, 4.0D, false);
                    if (playerEntity != null) {
                        playerEntity.playNotifySound(SoundEvents.ENDER_CHEST_OPEN, SoundSource.BLOCKS, 1.0F, -2.0F);
                        playedSound = true;
                    }
                }

                event.getController().setAnimation(OPEN);
            }
            return PlayState.CONTINUE;
        } else if (!this.getBlockState().hasProperty(OssuaryBlock.OPEN)) {
            event.getController().setAnimation(CLOSE);
            if (playedSound) playedSound = false;

            return PlayState.CONTINUE;
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public static void tick(Level world, BlockPos pos, BlockState state, OssuaryBlockEntity blockEntity) {
        if (world.random.nextInt(100) == 0) {
            world.playSound(null, pos, SoundEvents.SOUL_ESCAPE, SoundSource.BLOCKS, 4.0F, -3.0F);
        }

        Player playerEntity = world.getNearestPlayer((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, 4.0D, false);
        if (playerEntity != null && !state.hasProperty(OssuaryBlock.OPEN)) {
            world.setBlock(pos, state.setValue(OssuaryBlock.OPEN, true), 3);
        } else if (playerEntity == null && state.hasProperty(OssuaryBlock.OPEN)) {
            world.setBlock(pos, state.setValue(OssuaryBlock.OPEN, false), 3);
            //world.playSound((PlayerEntity) null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_ENDER_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, -3.0F);
        }

    }
}
