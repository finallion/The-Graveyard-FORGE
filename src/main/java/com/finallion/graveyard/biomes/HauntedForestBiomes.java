package com.finallion.graveyard.biomes;

import com.finallion.graveyard.biomes.surfacebuilders.TGSurfaceBuilders;
import com.finallion.graveyard.init.TGConfiguredFeatures;
import com.finallion.graveyard.init.TGConfiguredFeatures;
import com.finallion.graveyard.init.TGEntities;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;

public class HauntedForestBiomes {

    public static Biome HauntedForestBiome() {
        MobSpawnInfo.Builder spawnSettings = new MobSpawnInfo.Builder();
        spawnSettings.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(TGEntities.GHOUL, 45, 2, 5));
        spawnSettings.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(TGEntities.SKELETON_CREEPER, 40, 1, 2));
        spawnSettings.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.FOX, 8, 2, 3));
        spawnSettings.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.WOLF, 8, 3, 5));
        spawnSettings.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.SHEEP, 10, 3, 5));
        spawnSettings.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PIG, 10, 3, 5));
        spawnSettings.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.CAT, 1, 1, 1));
        DefaultBiomeFeatures.commonSpawns(spawnSettings);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        generationSettings.surfaceBuilder(TGSurfaceBuilders.HAUNTED_FOREST_SURFACE_CONFIG);
        generationSettings.addStructureStart(StructureFeatures.MINESHAFT);
        generationSettings.addStructureStart(StructureFeatures.STRONGHOLD);
        generationSettings.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(generationSettings);
        DefaultBiomeFeatures.addDefaultCarvers(generationSettings);
        DefaultBiomeFeatures.addDefaultMonsterRoom(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultSoftDisks(generationSettings);
        DefaultBiomeFeatures.addDefaultSprings(generationSettings);
        DefaultBiomeFeatures.addSurfaceFreezing(generationSettings);
        DefaultBiomeFeatures.addMossyStoneBlock(generationSettings);
        DefaultBiomeFeatures.addTaigaGrass(generationSettings);
        DefaultBiomeFeatures.addFerns(generationSettings);
        DefaultBiomeFeatures.addGiantTaigaVegetation(generationSettings);
        DefaultBiomeFeatures.addDefaultExtraVegetation(generationSettings);
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings);

        generationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_PUMPKIN);
        generationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_LARGE_FERN);

        generationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.MOSS_CARPET_CONFIG);
        generationSettings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, TGConfiguredFeatures.COBWEB_CONFIG);
        generationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.BUSH_CONFIG);
        generationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.GRAVESTONE_CONFIG);

        TGBiomeFeatures.addGraveyardSpruceTrees(generationSettings);



        return (new Biome.Builder())
                .precipitation(Biome.RainType.RAIN)
                .biomeCategory(Biome.Category.FOREST)
                .depth(0.125F)
                .scale(0.05F)
                .temperature(0.6F)
                .downfall(0.9F)
                .specialEffects((new BiomeAmbience.Builder())
                        .grassColorOverride(0x6F932A)
                        .foliageColorOverride(0x6F932A)
                        .waterColor(0xAEC1BE)
                        .waterFogColor(0xC9DDDA)
                        .fogColor(0x878787)
                        .skyColor(0x878787)
                        .backgroundMusic(BackgroundMusicTracks.GAME)
                        .ambientLoopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP)
                        .ambientMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D))
                        .ambientAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))
                        .build())
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();

    }

    public static Biome HauntedForestLakeBiome() {
        MobSpawnInfo.Builder spawnSettings = new MobSpawnInfo.Builder();
        spawnSettings.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(TGEntities.GHOUL, 25, 2, 5));
        spawnSettings.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(TGEntities.SKELETON_CREEPER, 15, 1, 2));
        spawnSettings.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.FOX, 8, 2, 3));
        spawnSettings.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.WOLF, 8, 3, 5));
        spawnSettings.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.SHEEP, 10, 3, 5));
        spawnSettings.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PIG, 10, 3, 5));
        DefaultBiomeFeatures.commonSpawns(spawnSettings);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        generationSettings.surfaceBuilder(TGSurfaceBuilders.HAUNTED_FOREST_SURFACE_CONFIG);
        generationSettings.addStructureStart(StructureFeatures.MINESHAFT);
        generationSettings.addStructureStart(StructureFeatures.STRONGHOLD);
        generationSettings.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
        generationSettings.addStructureStart(StructureFeatures.SWAMP_HUT);

        DefaultBiomeFeatures.addDefaultUndergroundVariety(generationSettings);
        DefaultBiomeFeatures.addDefaultCarvers(generationSettings);
        DefaultBiomeFeatures.addDefaultMonsterRoom(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultSoftDisks(generationSettings);
        DefaultBiomeFeatures.addDefaultSprings(generationSettings);
        DefaultBiomeFeatures.addSurfaceFreezing(generationSettings);
        DefaultBiomeFeatures.addMossyStoneBlock(generationSettings);
        DefaultBiomeFeatures.addTaigaGrass(generationSettings);
        DefaultBiomeFeatures.addFerns(generationSettings);
        DefaultBiomeFeatures.addGiantTaigaVegetation(generationSettings);
        DefaultBiomeFeatures.addDefaultExtraVegetation(generationSettings);
        DefaultBiomeFeatures.addSwampClayDisk(generationSettings);
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings);
        DefaultBiomeFeatures.addDefaultSeagrass(generationSettings);
        DefaultBiomeFeatures.addSwampVegetation(generationSettings);

        generationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_SWAMP);
        generationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_WATERLILLY);
        generationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_PUMPKIN);
        generationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_LARGE_FERN);

        generationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.MOSS_CARPET_CONFIG);
        generationSettings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, TGConfiguredFeatures.COBWEB_CONFIG);
        generationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.BUSH_CONFIG);
        generationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.SOUL_LIGHT_CONFIG);


        TGBiomeFeatures.addGraveyardSpruceTrees(generationSettings);



        return (new Biome.Builder())
                .precipitation(Biome.RainType.RAIN)
                .biomeCategory(Biome.Category.FOREST)
                .depth(-0.125F)
                .scale(0.75F)
                .temperature(0.6F)
                .downfall(0.9F)
                .specialEffects((new BiomeAmbience.Builder())
                        .grassColorOverride(0x7EA530)
                        .foliageColorOverride(0x7EA530)
                        .waterColor(0x9B091C)
                        .waterFogColor(0xD80D28)
                        .fogColor(0xB2B2B2)
                        .skyColor(0xB2B2B2)
                        .backgroundMusic(BackgroundMusicTracks.GAME)
                        .ambientLoopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                        .ambientMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D))
                        .ambientAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))
                        .build())
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();

    }

    public static Biome ErodedHauntedForestBiome() {
        MobSpawnInfo.Builder spawnSettings = new MobSpawnInfo.Builder();
        spawnSettings.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(TGEntities.GHOUL, 10, 2, 5));
        spawnSettings.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(TGEntities.REAPER, 1, 1, 1));
        spawnSettings.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(TGEntities.SKELETON_CREEPER, 10, 1, 2));
        spawnSettings.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITHER_SKELETON, 8, 2, 3));
        spawnSettings.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, 8, 1, 2));
        spawnSettings.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.BAT, 10, 1, 1));
        spawnSettings.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.CAT, 1, 1, 1));

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        generationSettings.surfaceBuilder(TGSurfaceBuilders.ERODED_HAUNTED_FOREST_SURFACE_CONFIG);
        generationSettings.addStructureStart(StructureFeatures.MINESHAFT);
        generationSettings.addStructureStart(StructureFeatures.STRONGHOLD);
        generationSettings.addStructureStart(StructureFeatures.PILLAGER_OUTPOST);
        generationSettings.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
        generationSettings.addStructureStart(StructureFeatures.NETHER_FOSSIL);

        DefaultBiomeFeatures.addDefaultUndergroundVariety(generationSettings);
        DefaultBiomeFeatures.addDefaultCarvers(generationSettings);
        DefaultBiomeFeatures.addDefaultMonsterRoom(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultSoftDisks(generationSettings);
        DefaultBiomeFeatures.addDefaultSprings(generationSettings);
        DefaultBiomeFeatures.addSurfaceFreezing(generationSettings);
        DefaultBiomeFeatures.addMossyStoneBlock(generationSettings);
        DefaultBiomeFeatures.addTaigaGrass(generationSettings);
        DefaultBiomeFeatures.addFerns(generationSettings);
        DefaultBiomeFeatures.addGiantTaigaVegetation(generationSettings);
        DefaultBiomeFeatures.addDefaultExtraVegetation(generationSettings);
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings);


        generationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_PUMPKIN);
        generationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_LARGE_FERN);

        generationSettings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, TGConfiguredFeatures.COBWEB_CONFIG);
        generationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TGConfiguredFeatures.BUSH_CONFIG);

        TGBiomeFeatures.addErodedGraveyardSpruceTrees(generationSettings);



        return (new Biome.Builder())
                .precipitation(Biome.RainType.RAIN)
                .biomeCategory(Biome.Category.FOREST)
                .depth(0.9F)
                .scale(0.05F)
                .temperature(0.4F)
                .downfall(0.4F)
                .specialEffects((new BiomeAmbience.Builder())
                        .grassColorOverride(0x77AB2F)
                        .foliageColorOverride(0x77AB2F)
                        .waterColor(0xAEC1BE)
                        .waterFogColor(0xC9DDDA)
                        .fogColor(0x6B6B6B)
                        .skyColor(0x6B6B6B)
                        .backgroundMusic(BackgroundMusicTracks.GAME)
                        .ambientLoopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP)
                        .ambientMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D))
                        .ambientAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))
                        .build())
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();

    }

}



