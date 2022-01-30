package com.finallion.graveyard.events;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.TGConfiguredStructures;
import com.finallion.graveyard.init.TGEntities;
import com.finallion.graveyard.init.TGStructures;
import com.finallion.graveyard.world.structures.LargeGraveyardStructure;
import com.finallion.graveyard.world.structures.MediumGraveyardStructure;
import com.finallion.graveyard.world.structures.SmallDesertGraveyardStructure;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;

import java.util.List;
import java.util.Locale;

public class ServerEvents {
    public static void setupStructureSpawns(final StructureSpawnListGatherEvent event) {
        if (event.getStructure() == TGStructures.MEDIUM_GRAVEYARD_STRUCTURE.get()) {
            event.addEntitySpawns(MobCategory.MONSTER, MediumGraveyardStructure.MONSTER_SPAWNS.get());
        }

        if (event.getStructure() == TGStructures.SMALL_DESERT_GRAVEYARD_STRUCTURE.get()) {
            event.addEntitySpawns(MobCategory.MONSTER, SmallDesertGraveyardStructure.MONSTER_SPAWNS.get());
        }

        if (event.getStructure() == TGStructures.LARGE_GRAVEYARD_STRUCTURE.get()) {
            event.addEntitySpawns(MobCategory.MONSTER, LargeGraveyardStructure.MONSTER_SPAWNS.get());
        }
    }

    public static void onBiomesLoad(BiomeLoadingEvent event) {
        List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(MobCategory.MONSTER);

        if (parseBiomes(GraveyardConfig.COMMON.allowedBiomesCategoriesGhoul.get(), GraveyardConfig.COMMON.blacklistedBiomesGhoul.get(), event)) {
            base.add(new MobSpawnSettings.SpawnerData(TGEntities.GHOUL,
                    GraveyardConfig.COMMON.weightGhoul.get(),
                    GraveyardConfig.COMMON.minGroupSizeGhoul.get(),
                    GraveyardConfig.COMMON.maxGroupSizeGhoul.get()));
        }

        if (parseBiomes(GraveyardConfig.COMMON.allowedBiomesCategoriesRevenant.get(), GraveyardConfig.COMMON.blacklistedBiomesRevenant.get(), event)) {
            base.add(new MobSpawnSettings.SpawnerData(TGEntities.REVENANT,
                    GraveyardConfig.COMMON.weightRevenant.get(),
                    GraveyardConfig.COMMON.minGroupSizeRevenant.get(),
                    GraveyardConfig.COMMON.maxGroupSizeRevenant.get()));
        }

        if (parseBiomes(GraveyardConfig.COMMON.allowedBiomesCategoriesReaper.get(), GraveyardConfig.COMMON.blacklistedBiomesReaper.get(), event)) {
            base.add(new MobSpawnSettings.SpawnerData(TGEntities.REAPER,
                    GraveyardConfig.COMMON.weightReaper.get(),
                    GraveyardConfig.COMMON.minGroupSizeReaper.get(),
                    GraveyardConfig.COMMON.maxGroupSizeReaper.get()));
        }

        if (parseBiomes(GraveyardConfig.COMMON.allowedBiomesCategoriesNightmare.get(), GraveyardConfig.COMMON.blacklistedBiomesNightmare.get(), event)) {
            base.add(new MobSpawnSettings.SpawnerData(TGEntities.NIGHTMARE,
                    GraveyardConfig.COMMON.weightNightmare.get(),
                    GraveyardConfig.COMMON.minGroupSizeNightmare.get(),
                    GraveyardConfig.COMMON.maxGroupSizeNightmare.get()));
        }

        if (parseBiomes(GraveyardConfig.COMMON.allowedBiomesCategoriesSkeletonCreeper.get(), GraveyardConfig.COMMON.blacklistedBiomesSkeletonCreeper.get(), event)) {
            base.add(new MobSpawnSettings.SpawnerData(TGEntities.SKELETON_CREEPER,
                    GraveyardConfig.COMMON.weightSkeletonCreeper.get(),
                    GraveyardConfig.COMMON.minGroupSizeSkeletonCreeper.get(),
                    GraveyardConfig.COMMON.maxGroupSizeSkeletonCreeper.get()));
        }

        if (parseBiomes(GraveyardConfig.COMMON.allowedBiomesCategoriesAcolyte.get(), GraveyardConfig.COMMON.blacklistedBiomesAcolyte.get(), event)) {
            base.add(new MobSpawnSettings.SpawnerData(TGEntities.ACOLYTE,
                    GraveyardConfig.COMMON.weightAcolyte.get(),
                    GraveyardConfig.COMMON.minGroupSizeAcolyte.get(),
                    GraveyardConfig.COMMON.maxGroupSizeAcolyte.get()));
        }


    }

    private static boolean parseBiomes(List<? extends String> allowedBiomeCategory, List<? extends String> blacklistedBiomes, BiomeLoadingEvent biomeContext) {
        if (allowedBiomeCategory == null) {
            TheGraveyard.LOGGER.error("Error reading from the config file: Allowed biome category is null. Try to delete the file and restart the game.");
            return false;
        }

        // no blacklist and biome is allowed
        if (allowedBiomeCategory.contains(biomeContext.getCategory().toString().toLowerCase(Locale.ROOT)) && blacklistedBiomes.isEmpty()) {
            return true;
        }

        // blacklist and check if biome is on the blacklist
        if (allowedBiomeCategory.contains(biomeContext.getCategory().toString().toLowerCase(Locale.ROOT)) && !blacklistedBiomes.isEmpty()) {
            if (blacklistedBiomes.contains(biomeContext.getName().getPath())) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }



}
