package com.finallion.graveyard.mixin;


import net.minecraft.world.level.levelgen.feature.LakeFeature;
import org.spongepowered.asm.mixin.Mixin;


@Mixin(LakeFeature.class)
public class LakeFeatureMixin {

    /*
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

     */


}
