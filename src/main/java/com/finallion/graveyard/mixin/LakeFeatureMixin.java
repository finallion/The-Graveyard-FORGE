package com.finallion.graveyard.mixin;


import com.finallion.graveyard.init.TGConfiguredStructureFeatures;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LakeFeature.class)
public class LakeFeatureMixin {

    @Inject(method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z", at = @At(value = "HEAD"), cancellable = true)
    private void generateNoLakes(FeaturePlaceContext<BlockStateConfiguration> context, CallbackInfoReturnable<Boolean> info) {
        if (!(context.level() instanceof WorldGenRegion)) {
            return;
        }

        if (context.level() instanceof ServerLevel) {
            SectionPos sectionPos = SectionPos.of(context.origin());
            ChunkAccess chunkAccess = context.level().getChunk(context.origin());


            StructureManager structureFeatureManager = ((WorldGenRegionAccessor) context.level()).getStructureFeatureManager();

            for (Structure structure : TGConfiguredStructureFeatures.structures) {
                StructureStart structureStart = structureFeatureManager.getStartForStructure(sectionPos, structure, chunkAccess);

                if (structureStart != null && structureStart.isValid()) {
                    info.setReturnValue(false);
                }
            }
        }
    }


}
