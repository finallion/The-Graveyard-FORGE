package com.finallion.graveyard.world.biomes;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.init.TGConfiguredFeatures;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.features.AquaticFeatures;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;


public class AncientDeadCoralReef {

    public static Biome createAncientDeadCoralReef() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.oceanSpawns(spawnSettings, 3, 4, 15);
        spawnSettings.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.SALMON, 15, 1, 5));
        spawnSettings.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.PUFFERFISH, 15, 1, 3));
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticFeatures.SEAGRASS_MID);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticFeatures.KELP);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.ANCIENT_DEAD_CORAL_REEF_PLACED_FEATURE);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.ANCIENT_DEAD_CORAL_REEF_WATER_PLACED_FEATURE);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.DEAD_CORAL_PATCH_PLACED_FEATURE);

        /*
        BiomeModifications.create(new Identifier(TheGraveyard.MOD_ID + "ancient_dead_coral_reef_structures"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(TGBiomes.ANCIENT_DEAD_CORAL_REEF_KEY), ctx -> {
                    ctx.getGenerationSettings().addStructure(RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier("nether_fossil")));
                }).add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(TGBiomes.ANCIENT_DEAD_CORAL_REEF_KEY), ctx -> {
                    ctx.getGenerationSettings().addStructure(RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier("ocean_ruin_cold")));
                }).add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(TGBiomes.ANCIENT_DEAD_CORAL_REEF_KEY), ctx -> {
                    ctx.getGenerationSettings().addStructure(RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier("shipwreck")));
                }).add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(TGBiomes.ANCIENT_DEAD_CORAL_REEF_KEY), ctx -> {
                    ctx.getGenerationSettings().addStructure(RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier("shipwreck_beached")));
                });

         */

        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.RAIN)
                .biomeCategory(Biome.BiomeCategory.BEACH)
                .temperature(0.5F)
                .downfall(0.5F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xFFFFFF)
                        .waterFogColor(0xFFFFFF)
                        .fogColor(12638463) // default
                        .skyColor(12638463) // default
                        .build())
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();

    }
}
