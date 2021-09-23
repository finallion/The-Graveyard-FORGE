package com.finallion.graveyard.mixin;

import com.finallion.graveyard.init.TGConfiguredFeatures;
import com.finallion.graveyard.init.TGStructures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;


@Mixin(NoiseChunkGenerator.class)
public class NoiseChunkGeneratorMixin {

    @Inject(at = @At("HEAD"), method = "getMobsAt", cancellable = true)
    public void injectSpawnList(Biome biome, StructureManager accessor, EntityClassification group, BlockPos pos, CallbackInfoReturnable<List<MobSpawnInfo.Spawners>> info) {
        if (accessor.getStructureAt(pos, false, TGConfiguredFeatures.CONFIGURED_MEDIUM_WALLED_GRAVEYARD.feature).isValid()) {
            if (group == EntityClassification.MONSTER) {
                info.setReturnValue(TGStructures.MEDIUM_WALLED_GRAVEYARD.get().getDefaultSpawnList());
            }
        }
        if (accessor.getStructureAt(pos, false, TGConfiguredFeatures.CONFIGURED_SMALL_WALLED_GRAVEYARD_DESERT.feature).isValid()) {
            if (group == EntityClassification.MONSTER) {
                info.setReturnValue(TGStructures.SMALL_WALLED_GRAVEYARD_DESERT.get().getDefaultSpawnList());
            }
        }
    }
}
