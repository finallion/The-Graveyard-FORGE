package com.finallion.graveyard.mixin;

import com.finallion.graveyard.blocks.TGGrassBlock;
import com.finallion.graveyard.blocks.TGMossBlock;
import com.finallion.graveyard.init.TGBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin (BushBlock.class)
public class PlantBlockMixin {
    // allows to plant plants on replaceable helper blocks

    @Inject(method = "canSurvive", at = @At("HEAD"), cancellable = true)
    private void canSurvive(BlockState state, IWorldReader world, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
        Block block = world.getBlockState(pos.below()).getBlock();
        if (block instanceof TGGrassBlock || block instanceof TGMossBlock || block.defaultBlockState().is(TGBlocks.TG_ROOTED_DIRT) || block.defaultBlockState().is(TGBlocks.TG_DIRT) || block.defaultBlockState().is(TGBlocks.TG_COARSE_DIRT) || block.defaultBlockState().is(TGBlocks.TG_PODZOL)) {
            info.setReturnValue(true);
        }
    }
}
