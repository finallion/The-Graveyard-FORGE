package com.finallion.graveyard.events;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.TGConfiguredFeatures;
import com.finallion.graveyard.init.TGEntities;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ServerEvents {

    /*
    public static void addBiomeFeatures(BiomeModifier event) {
        ResourceKey<Biome> biome = ResourceKey.create(ForgeRegistries.Keys.BIOMES, event.getName());
        BiomeGenerationSettingsBuilder generation = event.getGeneration();

        if (biome.location().getNamespace().equals("graveyard_biomes") && biome.location().getPath().contains("haunted")) {
            generation.m_204201_(GenerationStep.Decoration.VEGETAL_DECORATION, Holder.m_205709_(TGConfiguredFeatures.GRAVESTONE_PLACED_FEATURE));
        }
    }


    public static void onBiomesLoad(BiomeLoadingEvent event) {
        List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(MobCategory.MONSTER);

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

        if (parseBiomes(GraveyardConfig.COMMON.whitelistReaper.get(), GraveyardConfig.COMMON.blacklistReaper.get(), event) &&
                parseWhitelistedMods(GraveyardConfig.COMMON.modWhitelistReaper.get(), event) && GraveyardConfig.COMMON.enableReaper.get()) {
            base.add(new MobSpawnSettings.SpawnerData(TGEntities.REAPER.get(),
                    GraveyardConfig.COMMON.weightReaper.get(),
                    GraveyardConfig.COMMON.minGroupSizeReaper.get(),
                    GraveyardConfig.COMMON.maxGroupSizeReaper.get()));
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

        if (parseBiomes(GraveyardConfig.COMMON.whitelistAcolyte.get(), GraveyardConfig.COMMON.blacklistAcolyte.get(), event) &&
                parseWhitelistedMods(GraveyardConfig.COMMON.modWhitelistAcolyte.get(), event) && GraveyardConfig.COMMON.enableAcolyte.get()) {
            base.add(new MobSpawnSettings.SpawnerData(TGEntities.ACOLYTE.get(),
                    GraveyardConfig.COMMON.weightAcolyte.get(),
                    GraveyardConfig.COMMON.minGroupSizeAcolyte.get(),
                    GraveyardConfig.COMMON.maxGroupSizeAcolyte.get()));
        }

        if (parseBiomes(GraveyardConfig.COMMON.whitelistWraith.get(), GraveyardConfig.COMMON.blacklistWraith.get(), event) &&
                parseWhitelistedMods(GraveyardConfig.COMMON.modWhitelistWraith.get(), event) && GraveyardConfig.COMMON.enableWraith.get()) {
            base.add(new MobSpawnSettings.SpawnerData(TGEntities.ACOLYTE.get(),
                    GraveyardConfig.COMMON.weightWraith.get(),
                    GraveyardConfig.COMMON.minGroupSizeWraith.get(),
                    GraveyardConfig.COMMON.maxGroupSizeWraith.get()));
        }

        if (parseBiomes(GraveyardConfig.COMMON.whitelistCorruptedPillager.get(), GraveyardConfig.COMMON.blacklistCorruptedPillager.get(), event) &&
                parseWhitelistedMods(GraveyardConfig.COMMON.modWhitelistCorruptedPillager.get(), event) && GraveyardConfig.COMMON.enableCorruptedPillager.get()) {
            base.add(new MobSpawnSettings.SpawnerData(TGEntities.ACOLYTE.get(),
                    GraveyardConfig.COMMON.weightCorruptedPillager.get(),
                    GraveyardConfig.COMMON.minGroupSizeCorruptedPillager.get(),
                    GraveyardConfig.COMMON.maxGroupSizeCorruptedPillager.get()));
        }

        if (parseBiomes(GraveyardConfig.COMMON.whitelistCorruptedVindicator.get(), GraveyardConfig.COMMON.blacklistCorruptedVindicator.get(), event) &&
                parseWhitelistedMods(GraveyardConfig.COMMON.modWhitelistCorruptedVindicator.get(), event) && GraveyardConfig.COMMON.enableCorruptedVindicator.get()) {
            base.add(new MobSpawnSettings.SpawnerData(TGEntities.ACOLYTE.get(),
                    GraveyardConfig.COMMON.weightCorruptedVindicator.get(),
                    GraveyardConfig.COMMON.minGroupSizeCorruptedVindicator.get(),
                    GraveyardConfig.COMMON.maxGroupSizeCorruptedVindicator.get()));
        }


    }


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
                TheGraveyard.LOGGER.debug("Biome " +  biomeIdentifier + " is on whitelist and blacklist.");
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

     */



}
