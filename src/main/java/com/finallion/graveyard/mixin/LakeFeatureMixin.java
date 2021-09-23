package com.finallion.graveyard.mixin;


import com.finallion.graveyard.init.TGStructures;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.LakesFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;


@Mixin(LakesFeature.class)
public class LakeFeatureMixin {

    @Inject(at = @At("HEAD"), method = "place", cancellable = true)
    public void structures_fixLake(ISeedReader world, ChunkGenerator p_241855_2_, Random p_241855_3_, BlockPos blockPos, BlockStateFeatureConfig config, CallbackInfoReturnable<Boolean> info) {
        SectionPos sectionPos = SectionPos.of(blockPos);
        for (Structure<?> structure : TGStructures.MOD_STRUCTURES) {
            if (world.startsForFeature(sectionPos, structure).findAny().isPresent()) {
                info.setReturnValue(false);
            }
        }
    }
}
