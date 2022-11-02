package com.finallion.graveyard.item;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blocks.AltarBlock;
import com.finallion.graveyard.blocks.OminousBoneStaffFragment;
import com.finallion.graveyard.entities.LichEntity;
import com.finallion.graveyard.init.TGBlocks;
import com.finallion.graveyard.init.TGEntities;
import com.finallion.graveyard.init.TGSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VialOfBlood extends Item {
    private static final String BlOOD_KEY = "Blood";

    public VialOfBlood() {
        super(new Item.Properties().stacksTo(1).tab(TheGraveyard.GROUP));
    }

    public static float getBlood(ItemStack stack) {
        CompoundTag nbtCompound = stack.getTag();
        if (nbtCompound == null) {
            return 0.1F;
        } else {
            return nbtCompound.getFloat(BlOOD_KEY);
        }
    }

    public static void setBlood(ItemStack stack, float blood) {
        CompoundTag nbtCompound = stack.getOrCreateTag();
        if (blood < 0.9F) {
            nbtCompound.putFloat(BlOOD_KEY, blood);
        }
    }


    public InteractionResult useOn(UseOnContext context) {
        BlockState blockState = context.getLevel().getBlockState(context.getClickedPos());
        ItemStack stack = context.getItemInHand();
        Player playerEntity = context.getPlayer();
        Level world = context.getLevel();
        float blood = VialOfBlood.getBlood(stack);
        if (blockState.is(TGBlocks.ALTAR.get()) && playerEntity!= null && blood >= 0.8F && world.getDifficulty() != Difficulty.PEACEFUL && world.isNight()) {
            BlockPattern.BlockPatternMatch result = AltarBlock.getCompletedFramePattern().find(world, context.getClickedPos());

            if (!blockState.getValue(AltarBlock.BLOODY) && result != null) {
                playerEntity.level.playSound(null, playerEntity.blockPosition(), TGSounds.VIAL_SPLASH.get(), SoundSource.BLOCKS, 5.0F, 1.0F);
                world.setBlock(context.getClickedPos(), blockState.setValue(AltarBlock.BLOODY, true), 3);
                if (!world.isClientSide()) {
                    if (!playerEntity.isCreative()) {
                        ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
                        ItemUtils.createFilledResult(stack, playerEntity, bottle);
                    }

                    //playerEntity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 430));

                    BlockPos corner = context.getClickedPos().offset(-8, 0, -8);

                    // searches square around altar
                    for(int i = 0; i < 16; ++i) {
                        for(int j = 0; j < 16; ++j) {
                            for(int k = 0; k < 2;++k) {
                                BlockPos iteratorPos = new BlockPos(corner.offset(i, k, j));
                                BlockState state = world.getBlockState(iteratorPos);

                                if (state.getBlock() instanceof OminousBoneStaffFragment) {
                                    world.setBlock(iteratorPos, Blocks.AIR.defaultBlockState(), 3);
                                }
                            }
                        }
                    }

                    LichEntity lich = (LichEntity) TGEntities.LICH.get().create(world);
                    BlockPos blockPos = context.getClickedPos().above();
                    lich.setYHeadRot(result.getUp().getOpposite().toYRot());
                    lich.setYBodyRot(result.getUp().getOpposite().toYRot());
                    lich.setYRot(result.getUp().getOpposite().toYRot());
                    lich.moveTo((double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.55D, (double)blockPos.getZ() + 0.5D, 0.0F, 0.0F);
                    lich.onSummoned(result.getUp().getOpposite(), context.getClickedPos().above());


                    for(ServerPlayer serverplayer : world.getEntitiesOfClass(ServerPlayer.class, lich.getBoundingBox().inflate(50.0D))) {
                        CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayer, lich);
                    }



                    world.addFreshEntity(lich);
                    lich.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 5));

                    return InteractionResult.CONSUME;
                }

                return InteractionResult.sidedSuccess(playerEntity.level.isClientSide);
            }
        }

        return InteractionResult.PASS;
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        float blood = 0;
        if (stack.hasTag()) {
            blood = stack.getTag().getFloat(BlOOD_KEY);
        }

        if (blood > 0.8F && blood < 0.9F) {
            tooltip.add(new TextComponent("Blood level: full").withStyle(ChatFormatting.GRAY));
        } else {
            int level = (int)(blood * 10);
            if (level == 0) {
                tooltip.add(new TextComponent("Blood level: 1/8").withStyle(ChatFormatting.GRAY));
            } else {
                tooltip.add(new TextComponent("Blood level: " + level + "/8").withStyle(ChatFormatting.GRAY));
            }
        }

    }
}
