package com.finallion.graveyard.mixin;

import com.finallion.graveyard.init.TGBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;
import java.util.function.BiConsumer;

@Mixin(TreeFeature.class)
public class TreeFeatureMixin {
    // helper blocks could be swapped to the vanilla ones during generation, but trees generate later than structures
    // therefore trees would spawn inside the structure
    // this mixin checks during tree generation if the game tries to place a tree on one of the helper blocks


    @Inject(method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z", at = @At(value = "HEAD"), cancellable = true)
    private void noTreesInStructures(FeaturePlaceContext<TreeConfiguration> context, CallbackInfoReturnable<Boolean> info) {
        BlockState state = context.level().getBlockState(context.origin().below());
        if (state.is(TGBlocks.TG_DIRT) || state.is(TGBlocks.TG_COARSE_DIRT) || state.is(TGBlocks.TG_GRASS_BLOCK) || state.is(TGBlocks.TG_MOSS_BLOCK) || state.is(TGBlocks.TG_ROOTED_DIRT) || state.is(TGBlocks.TG_PODZOL)) {
            info.setReturnValue(false);
        }
    }

}
