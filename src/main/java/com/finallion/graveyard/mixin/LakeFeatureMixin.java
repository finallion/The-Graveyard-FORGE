package com.finallion.graveyard.mixin;


import com.finallion.graveyard.init.TGConfiguredStructureFeatures;
import com.finallion.graveyard.init.TGStructureFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LakeFeature.class)
public class LakeFeatureMixin {

    @Inject(method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z", at = @At(value = "HEAD"), cancellable = true)
    private void generateNoLakes(FeaturePlaceContext<BlockStateConfiguration> context, CallbackInfoReturnable<Boolean> info) {
        SectionPos sectionPos = SectionPos.of(context.origin());
        ChunkAccess chunkAccess = context.level().getChunk(context.origin());
        StructureFeatureManager structureFeatureManager = ((WorldGenRegionAccessor)context.level()).getStructureFeatureManager();

        for (ConfiguredStructureFeature<?, ?> configuredStructureFeature : TGConfiguredStructureFeatures.MOD_STRUCTURE_FEATURES) {
            StructureStart startForFeature = structureFeatureManager.m_207802_(sectionPos, configuredStructureFeature, chunkAccess);
            if (startForFeature != null && startForFeature.isValid()) {
                info.setReturnValue(false);
                return;
            }
        }
    }
}
