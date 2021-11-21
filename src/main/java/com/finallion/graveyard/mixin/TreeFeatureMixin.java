package com.finallion.graveyard.mixin;

import com.finallion.graveyard.init.TGBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(TreeFeature.class)
public class TreeFeatureMixin {
    // helper blocks could be swapped to the vanilla ones during generation, but trees generate later than structures
    // therefore trees would spawn inside the structure
    // this mixin checks during tree generation if the game tries to place a tree on one of the helper blocks

    @Inject(method = "place", at = @At(value = "HEAD"), cancellable = true)
    private void noTreesInStructuresGenerate(ISeedReader world, ChunkGenerator p_241855_2_, Random p_241855_3_, BlockPos pos, BaseTreeFeatureConfig p_241855_5_, CallbackInfoReturnable<Boolean> info) {
        BlockState state = world.getBlockState(pos.below());
        if (state.getBlock().is(TGBlocks.TG_DIRT) || state.getBlock().is(TGBlocks.TG_COARSE_DIRT) || state.getBlock().is(TGBlocks.TG_GRASS_BLOCK) || state.getBlock().is(TGBlocks.TG_MOSS_BLOCK) || state.getBlock().is(TGBlocks.TG_ROOTED_DIRT) || state.getBlock().is(TGBlocks.TG_PODZOL) || state.getBlock().is(TGBlocks.MOSS_BLOCK)) {
            info.setReturnValue(false);
        }
    }

}
