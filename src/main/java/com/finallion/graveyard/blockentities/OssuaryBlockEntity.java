package com.finallion.graveyard.blockentities;


import com.finallion.graveyard.blocks.OssuaryBlock;
import com.finallion.graveyard.init.TGSounds;
import com.finallion.graveyard.init.TGTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.client.audio.SoundSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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


public class OssuaryBlockEntity extends TileEntity implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private final AnimationBuilder OPEN = new AnimationBuilder().addAnimation("open", ILoopType.EDefaultLoopTypes.PLAY_ONCE).addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP);
    private final AnimationBuilder CLOSE = new AnimationBuilder().addAnimation("close", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME);
    private final AnimationBuilder IDLE = new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP);
    private boolean playedSound = false;
    private int timer = 0;

    public OssuaryBlockEntity() {
        super(TGTileEntities.OSSUARY_BLOCK_ENTITY.get());
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationState state = event.getController().getAnimationState();
        if (timer > 0) timer--;
        if (this.getBlockState().getValue(OssuaryBlock.OPEN)) {
            if (state == AnimationState.Stopped)  {
                if (level != null && !playedSound) {
                    PlayerEntity playerEntity = level.getNearestPlayer((double)worldPosition.getX() + 0.5D, (double)worldPosition.getY() + 0.5D, (double)worldPosition.getZ() + 0.5D, 4.0D, false);
                    if (playerEntity != null) {
                        playerEntity.playNotifySound(TGSounds.OSSUARY_OPEN.get(), SoundCategory.BLOCKS, 1.0F, -2.0F);
                        playedSound = true;
                        timer = 160;
                    }
                }

                event.getController().setAnimation(OPEN);
            }
            return PlayState.CONTINUE;
        } else if (!this.getBlockState().getValue(OssuaryBlock.OPEN) && timer == 0) {
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

    public static void tick(World world, BlockPos pos, BlockState state, OssuaryBlockEntity blockEntity) {
        if (world.random.nextInt(100) == 0) {
            world.playSound(null, pos, SoundEvents.SOUL_ESCAPE, SoundCategory.BLOCKS, 4.0F, -3.0F);
        }

        PlayerEntity playerEntity = world.getNearestPlayer((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, 4.0D, false);
        if (playerEntity != null && !state.getValue(OssuaryBlock.OPEN)) {
            world.setBlock(pos, state.setValue(OssuaryBlock.OPEN, true), 3);
        } else if (playerEntity == null && state.getValue(OssuaryBlock.OPEN)) {
            world.setBlock(pos, state.setValue(OssuaryBlock.OPEN, false), 3);
            //world.playSound((PlayerEntity) null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_ENDER_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, -3.0F);
        }

    }
}
