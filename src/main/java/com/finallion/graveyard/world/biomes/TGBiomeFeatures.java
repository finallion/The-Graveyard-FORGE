package com.finallion.graveyard.world.biomes;

import com.finallion.graveyard.init.TGConfiguredFeatures;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;

public class TGBiomeFeatures {

    public static void addGraveyardSpruceTrees(BiomeGenerationSettings.Builder gen) {
        gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.HAUNTED_FOREST_TREES);
    }

    public static void addErodedGraveyardSpruceTrees(BiomeGenerationSettings.Builder gen) {
        gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.ERODED_HAUNTED_FOREST_TREES);
    }
}
