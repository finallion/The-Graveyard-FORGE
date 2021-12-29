package com.finallion.graveyard.mixin;


import com.finallion.graveyard.init.TGStructures;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LakeFeature.class)
public class LakeFeatureMixin {

    @Inject(method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/WorldGenLevel;startsForFeature(Lnet/minecraft/core/SectionPos;Lnet/minecraft/world/level/levelgen/feature/StructureFeature;)Ljava/util/List;"), cancellable = true)
    private void repurposedstructures_noLakesInStructures(FeaturePlaceContext<BlockStateConfiguration> context, CallbackInfoReturnable<Boolean> cir) {
        SectionPos chunkPos = SectionPos.of(context.origin());
        for (StructureFeature<?> structure : TGStructures.MOD_STRUCTURES) {
            if (!context.level().startsForFeature(chunkPos, structure).isEmpty()) {
                cir.setReturnValue(false);
            }
        }
    }
}
