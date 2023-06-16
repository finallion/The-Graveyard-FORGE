package com.finallion.graveyard.events;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.TGEntities;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;

public class TGEvents {

    public static void onBiomesLoad(BiomeLoadingEvent event) {
        List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(MobCategory.MONSTER);
        if (event.getCategory() == Biome.BiomeCategory.MUSHROOM) {
            return;
        }

        if (parseBiomes(GraveyardConfig.COMMON.whitelistGhoul.get(), GraveyardConfig.COMMON.blacklistGhoul.get(), event) &&
                parseWhitelistedMods(GraveyardConfig.COMMON.modWhitelistGhoul.get(), event) && GraveyardConfig.COMMON.enableGhoul.get()) {
            base.add(new MobSpawnSettings.SpawnerData(TGEntities.GHOUL.get(),
                    GraveyardConfig.COMMON.weightGhoul.get(),
                    GraveyardConfig.COMMON.minGroupSizeGhoul.get(),
                    GraveyardConfig.COMMON.maxGroupSizeGhoul.get()));
        }

        if (parseBiomes(GraveyardConfig.COMMON.whitelistRevenant.get(), GraveyardConfig.COMMON.blacklistRevenant.get(), event) &&
                parseWhitelistedMods(GraveyardConfig.COMMON.modWhitelistRevenant.get(), event) && GraveyardConfig.COMMON.enableRevenant.get()) {
            base.add(new MobSpawnSettings.SpawnerData(TGEntities.REVENANT.get(),
                    GraveyardConfig.COMMON.weightRevenant.get(),
                    GraveyardConfig.COMMON.minGroupSizeRevenant.get(),
                    GraveyardConfig.COMMON.maxGroupSizeRevenant.get()));
        }

        if (parseBiomes(GraveyardConfig.COMMON.whitelistNightmare.get(), GraveyardConfig.COMMON.blacklistNightmare.get(), event) &&
                parseWhitelistedMods(GraveyardConfig.COMMON.modWhitelistNightmare.get(), event) && GraveyardConfig.COMMON.enableNightmare.get()) {
            base.add(new MobSpawnSettings.SpawnerData(TGEntities.NIGHTMARE.get(),
                    GraveyardConfig.COMMON.weightNightmare.get(),
                    GraveyardConfig.COMMON.minGroupSizeNightmare.get(),
                    GraveyardConfig.COMMON.maxGroupSizeNightmare.get()));
        }

        if (parseBiomes(GraveyardConfig.COMMON.whitelistSkeletonCreeper.get(), GraveyardConfig.COMMON.blacklistSkeletonCreeper.get(), event) &&
                parseWhitelistedMods(GraveyardConfig.COMMON.modWhitelistSkeletonCreeper.get(), event) && GraveyardConfig.COMMON.enableSkeletonCreeper.get()) {
            base.add(new MobSpawnSettings.SpawnerData(TGEntities.SKELETON_CREEPER.get(),
                    GraveyardConfig.COMMON.weightSkeletonCreeper.get(),
                    GraveyardConfig.COMMON.minGroupSizeSkeletonCreeper.get(),
                    GraveyardConfig.COMMON.maxGroupSizeSkeletonCreeper.get()));
        }
    }


    //Please don't look at this or try to understand what I'm doing here. Upgrade your mod to 1.19 and use biome tags instead.
    private static boolean parseBiomes(List<? extends String> whitelist, List<? extends String> blacklist, BiomeLoadingEvent biomeContext) {
        String biomeIdentifier = biomeContext.getName().toString();
        String biomeCategory = biomeContext.getCategory().getName();

        if (whitelist == null) {
            TheGraveyard.LOGGER.error("Error reading from the Graveyard config file: Allowed biome category/biome is null. Try to delete the file and restart the game.");
            return false;
        }

        // no blacklist and biome is allowed
        if (whitelist.contains(biomeIdentifier) && blacklist.isEmpty()) {
            return true;
        }

        // no blacklist and biomeCategory is allowed
        if (whitelist.contains("#" + biomeCategory) && blacklist.isEmpty()) {
            return true;
        }

        // blacklist exists and check if biome is on the blacklist
        if (whitelist.contains(biomeIdentifier) && !blacklist.isEmpty()) {
            if (blacklist.contains("#" + biomeCategory)) { // whitelist weighs higher than blacklist
                //TheGraveyard.LOGGER.error("Blacklisted biome category #" + biomeCategory + " contains whitelisted biome " + biomeIdentifier + ".");
                return true;
            } else if (blacklist.contains(biomeIdentifier)) {  // blacklist weighs higher than whitelist
                TheGraveyard.LOGGER.debug("Biome " + biomeIdentifier + " is on whitelist and blacklist.");
                return false;
            } else {
                return true;
            }
        }


        // blacklist exists and check if biomeCategory is on the blacklist
        if (whitelist.contains("#" + biomeCategory) && !blacklist.isEmpty()) {
            if (blacklist.contains("#" + biomeCategory)) { // blacklist weighs higher than whitelist
                TheGraveyard.LOGGER.debug("Biome category #" + biomeCategory + " is on whitelist and blacklist.");
                return false;
            } else if (blacklist.contains(biomeIdentifier)) { // blacklist weighs higher than whitelist
                //TheGraveyard.LOGGER.error("Biome category #" + biomeCategory + " is on whitelist and subsidiary biome " + biomeIdentifier + " is on blacklist.");
                return false;
            } else {
                return true;
            }
        }

        return false;
    }

    private static boolean parseWhitelistedMods(List<? extends String> whitelist, BiomeLoadingEvent biomeContext) {
        if (whitelist == null) {
            TheGraveyard.LOGGER.error("Error reading from the Graveyard config file: Allowed biome category/biome is null. Try to delete the file and restart the game.");
            return false;
        }

        String modid = biomeContext.getName().getNamespace();
        return whitelist.contains("#" + modid);

    }

}