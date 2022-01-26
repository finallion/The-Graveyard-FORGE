package com.finallion.graveyard.world.biomes;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.init.TGConfiguredFeatures;
import com.finallion.graveyard.init.TGEntities;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class HauntedForestBiomes {

    public static Biome createHauntedForest() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TGEntities.GHOUL, 45, 2, 5));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TGEntities.SKELETON_CREEPER, 40, 1, 2));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TGEntities.REVENANT, 30, 2, 5));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TGEntities.NIGHTMARE, 3, 1, 1));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 3));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 3, 5));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 10, 3, 5));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 10, 3, 5));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CAT, 1, 1, 1));
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();

        addBasicFeatures(generationSettings);
        addTaigaFeatures(generationSettings);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.HAUNTED_FOREST_TREES_PLACED_FEATURE);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.MOSS_CARPET_PLACED_FEATURE);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.COBWEB_PLACED_FEATURE);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.GRAVESTONE_PLACED_FEATURE);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.BUSH_PLACED_FEATURE);

        /*
        BiomeModifications.create(new Identifier(TheGraveyard.MOD_ID + "haunted_forest_structures"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(TGBiomes.HAUNTED_FOREST_KEY), ctx -> {
                    ctx.getGenerationSettings().addStructure(RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier("ruined_portal")));
                });



         */



        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.RAIN)
                .biomeCategory(Biome.BiomeCategory.FOREST)
                .temperature(0.6F)
                .downfall(0.9F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .grassColorOverride(0x6F932A)
                        .foliageColorOverride(0x6F932A)
                        .waterColor(0xAEC1BE)
                        .waterFogColor(0xC9DDDA)
                        .fogColor(0x878787)
                        .skyColor(0x878787)
                        .backgroundMusic(Musics.GAME)
                        .ambientLoopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP)
                        .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D))
                        .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))
                        .build())
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }


    public static Biome createHauntedLakes() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TGEntities.GHOUL, 25, 2, 5));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TGEntities.SKELETON_CREEPER, 15, 1, 2));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TGEntities.REVENANT, 15, 2, 5));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TGEntities.NIGHTMARE, 1, 1, 1));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 3));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 3, 5));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 10, 3, 5));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 10, 3, 5));
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        addBasicFeatures(generationSettings);
        addHauntedLakesFeatures(generationSettings);

        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.HAUNTED_FOREST_TREES_PLACED_FEATURE);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.MOSS_CARPET_PLACED_FEATURE);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.COBWEB_PLACED_FEATURE);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.GRAVESTONE_PLACED_FEATURE);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.BUSH_PLACED_FEATURE);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.SOUL_LIGHT_PLACED_FEATURE);

        /*
        BiomeModifications.create(new Identifier(TheGraveyard.MOD_ID + "haunted_lakes_structures"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(TGBiomes.HAUNTED_LAKES_KEY), ctx -> {
                    ctx.getGenerationSettings().addStructure(RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier("ruined_portal")));
                });

         */

        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.RAIN)
                .biomeCategory(Biome.BiomeCategory.FOREST)
                .temperature(0.6F)
                .downfall(0.9F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .grassColorOverride(0x7EA530)
                        .foliageColorOverride(0x7EA530)
                        .waterColor(0x9B091C)
                        .waterFogColor(0xD80D28)
                        .fogColor(0xB2B2B2)
                        .skyColor(0xB2B2B2)
                        .backgroundMusic(Musics.GAME)
                        .ambientLoopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                        .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D))
                        .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))
                        .build())
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();

    }


    public static Biome createErodedHauntedForest() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TGEntities.GHOUL, 10, 2, 5));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TGEntities.REVENANT, 10, 2, 5));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TGEntities.NIGHTMARE, 1, 1, 1));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TGEntities.REAPER, 1, 1, 1));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(TGEntities.SKELETON_CREEPER, 10, 1, 2));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITHER_SKELETON, 8, 2, 3));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 8, 1, 2));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.BAT, 10, 1, 1));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CAT, 1, 1, 1));

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();

        addBasicFeatures(generationSettings);
        addTaigaFeatures(generationSettings);

        /*
        BiomeModifications.create(new Identifier(TheGraveyard.MOD_ID + "eroded_haunted_forest_structures"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(TGBiomes.ERODED_HAUNTED_FOREST_KEY), ctx -> {
                    ctx.getGenerationSettings().addStructure(RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier("nether_fossil")));
                })
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(TGBiomes.ERODED_HAUNTED_FOREST_KEY), ctx -> {
                    ctx.getGenerationSettings().addStructure(RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier("ruined_portal")));
                });



         */
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.ERODED_HAUNTED_FOREST_TREES_PLACED_FEATURE);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.FALLEN_TREE_PLACED_FEATURE);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.COBWEB_PLACED_FEATURE);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.GRAVESTONE_PLACED_FEATURE);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.BUSH_PLACED_FEATURE);
         

        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.RAIN)
                .biomeCategory(Biome.BiomeCategory.FOREST)
                .temperature(0.4F)
                .downfall(0.4F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .grassColorOverride(0x77AB2F)
                        .foliageColorOverride(0x77AB2F)
                        .waterColor(0xAEC1BE)
                        .waterFogColor(0xC9DDDA)
                        .fogColor(0x6B6B6B)
                        .skyColor(0x6B6B6B)
                        .backgroundMusic(Musics.GAME)
                        .ambientLoopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP)
                        .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D))
                        .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))
                        .build())
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();

    }

    private static void addBasicFeatures(BiomeGenerationSettings.Builder generationSettings) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);
    }

    private static void addTaigaFeatures(BiomeGenerationSettings.Builder generationSettings) {
        BiomeDefaultFeatures.addMossyStoneBlock(generationSettings);
        BiomeDefaultFeatures.addFerns(generationSettings);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
        BiomeDefaultFeatures.addGiantTaigaVegetation(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);

    }


    private static void addHauntedLakesFeatures(BiomeGenerationSettings.Builder generationSettings) {
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addSwampClayDisk(generationSettings);
        BiomeDefaultFeatures.addMossyStoneBlock(generationSettings);
        BiomeDefaultFeatures.addFerns(generationSettings);
        BiomeDefaultFeatures.addGiantTaigaVegetation(generationSettings);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_WATERLILY);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addSwampExtraVegetation(generationSettings);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_SWAMP);
    }



}
