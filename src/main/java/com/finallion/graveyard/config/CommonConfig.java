package com.finallion.graveyard.config;


import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.*;

public class CommonConfig {
    public final Map<String, StructureConfigEntry> structureConfigEntries = new HashMap<>();

    public final ForgeConfigSpec.BooleanValue canGenerateHauntedHouse;
    public final ForgeConfigSpec.IntValue spacingHauntedHouse;
    public final ForgeConfigSpec.IntValue separationHauntedHouse;
    public final ForgeConfigSpec.LongValue saltHauntedHouse;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistHauntedHouse;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistHauntedHouse;
    public final ForgeConfigSpec.IntValue terrainCheckRadiusHauntedHouse;
    public final ForgeConfigSpec.IntValue terrainHeightDifferenceHauntedHouse;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsHauntedHouse;

    public final ForgeConfigSpec.BooleanValue canGenerateMediumGraveyard;
    public final ForgeConfigSpec.IntValue spacingMediumGraveyard;
    public final ForgeConfigSpec.IntValue separationMediumGraveyard;
    public final ForgeConfigSpec.LongValue saltMediumGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistMediumGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistMediumGraveyard;
    public final ForgeConfigSpec.IntValue terrainCheckRadiusMediumGraveyard;
    public final ForgeConfigSpec.IntValue terrainHeightDifferenceMediumGraveyard;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsMediumGraveyard;

    public final ForgeConfigSpec.BooleanValue canGenerateLargeGraveyard;
    public final ForgeConfigSpec.IntValue spacingLargeGraveyard;
    public final ForgeConfigSpec.IntValue separationLargeGraveyard;
    public final ForgeConfigSpec.LongValue saltLargeGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistLargeGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistLargeGraveyard;
    public final ForgeConfigSpec.IntValue terrainCheckRadiusLargeGraveyard;
    public final ForgeConfigSpec.IntValue terrainHeightDifferenceLargeGraveyard;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsLargeGraveyard;

    public final ForgeConfigSpec.BooleanValue canGenerateSmallGrave;
    public final ForgeConfigSpec.IntValue spacingSmallGrave;
    public final ForgeConfigSpec.IntValue separationSmallGrave;
    public final ForgeConfigSpec.LongValue saltSmallGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistSmallGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistSmallGrave;
    public final ForgeConfigSpec.IntValue terrainCheckRadiusSmallGrave;
    public final ForgeConfigSpec.IntValue terrainHeightDifferenceSmallGrave;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsSmallGrave;

    public final ForgeConfigSpec.BooleanValue canGenerateSmallDesertGrave;
    public final ForgeConfigSpec.IntValue spacingSmallDesertGrave;
    public final ForgeConfigSpec.IntValue separationSmallDesertGrave;
    public final ForgeConfigSpec.LongValue saltSmallDesertGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistSmallDesertGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistSmallDesertGrave;
    public final ForgeConfigSpec.IntValue terrainCheckRadiusSmallDesertGrave;
    public final ForgeConfigSpec.IntValue terrainHeightDifferenceSmallDesertGrave;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsSmallDesertGrave;

    public final ForgeConfigSpec.BooleanValue canGenerateSmallSavannaGrave;
    public final ForgeConfigSpec.IntValue spacingSmallSavannaGrave;
    public final ForgeConfigSpec.IntValue separationSmallSavannaGrave;
    public final ForgeConfigSpec.LongValue saltSmallSavannaGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistSmallSavannaGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistSmallSavannaGrave;
    public final ForgeConfigSpec.IntValue terrainCheckRadiusSavannaGrave;
    public final ForgeConfigSpec.IntValue terrainHeightDifferenceSavannaGrave;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsSmallSavannaGrave;

    public final ForgeConfigSpec.BooleanValue canGenerateSmallMountainGrave;
    public final ForgeConfigSpec.IntValue spacingSmallMountainGrave;
    public final ForgeConfigSpec.IntValue separationSmallMountainGrave;
    public final ForgeConfigSpec.LongValue saltSmallMountainGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistSmallMountainGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistSmallMountainGrave;
    public final ForgeConfigSpec.IntValue terrainCheckRadiusSmallMountainGrave;
    public final ForgeConfigSpec.IntValue terrainHeightDifferenceSmallMountainGrave;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsSmallMountainGrave;

    public final ForgeConfigSpec.BooleanValue canGenerateSmallGraveyard;
    public final ForgeConfigSpec.IntValue spacingSmallGraveyard;
    public final ForgeConfigSpec.IntValue separationSmallGraveyard;
    public final ForgeConfigSpec.LongValue saltSmallGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistSmallGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistSmallGraveyard;
    public final ForgeConfigSpec.IntValue terrainCheckRadiusSmallGraveyard;
    public final ForgeConfigSpec.IntValue terrainHeightDifferenceSmallGraveyard;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsSmallGraveyard;

    public final ForgeConfigSpec.BooleanValue canGenerateSmallDesertGraveyard;
    public final ForgeConfigSpec.IntValue spacingSmallDesertGraveyard;
    public final ForgeConfigSpec.IntValue separationSmallDesertGraveyard;
    public final ForgeConfigSpec.LongValue saltSmallDesertGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistSmallDesertGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistSmallDesertGraveyard;
    public final ForgeConfigSpec.IntValue terrainCheckRadiusSmallDesertGraveyard;
    public final ForgeConfigSpec.IntValue terrainHeightDifferenceSmallDesertGraveyard;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsSmallDesertGraveyard;

    public final ForgeConfigSpec.BooleanValue canGenerateMushroomGrave;
    public final ForgeConfigSpec.IntValue spacingMushroomGrave;
    public final ForgeConfigSpec.IntValue separationMushroomGrave;
    public final ForgeConfigSpec.LongValue saltMushroomGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistMushroomGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistMushroomGrave;
    public final ForgeConfigSpec.IntValue terrainCheckRadiusMushroomGrave;
    public final ForgeConfigSpec.IntValue terrainHeightDifferenceMushroomGrave;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsMushroomGrave;

    public final ForgeConfigSpec.BooleanValue canGenerateMemorialTree;
    public final ForgeConfigSpec.IntValue spacingMemorialTree;
    public final ForgeConfigSpec.IntValue separationMemorialTree;
    public final ForgeConfigSpec.LongValue saltMemorialTree;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistMemorialTree;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistMemorialTree;
    public final ForgeConfigSpec.IntValue terrainCheckRadiusMemorialTree;
    public final ForgeConfigSpec.IntValue terrainHeightDifferenceMemorialTree;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsMemorialTree;

    public final ForgeConfigSpec.BooleanValue canGenerateAltar;
    public final ForgeConfigSpec.IntValue spacingAltar;
    public final ForgeConfigSpec.IntValue separationAltar;
    public final ForgeConfigSpec.LongValue saltAltar;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistAltar;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistAltar;
    public final ForgeConfigSpec.IntValue terrainCheckRadiusAltar;
    public final ForgeConfigSpec.IntValue terrainHeightDifferenceAltar;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsAltar;

    public final ForgeConfigSpec.BooleanValue canGenerateCrypt;
    public final ForgeConfigSpec.IntValue spacingCrypt;
    public final ForgeConfigSpec.IntValue separationCrypt;
    public final ForgeConfigSpec.LongValue saltCrypt;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistCrypt;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistCrypt;
    public final ForgeConfigSpec.IntValue terrainCheckRadiusCrypt;
    public final ForgeConfigSpec.IntValue terrainHeightDifferenceCrypt;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsCrypt;

    public final ForgeConfigSpec.BooleanValue canGenerateGiantMushroom;
    public final ForgeConfigSpec.IntValue spacingGiantMushroom;
    public final ForgeConfigSpec.IntValue separationGiantMushroom;
    public final ForgeConfigSpec.LongValue saltGiantMushroom;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistGiantMushroom;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistGiantMushroom;
    public final ForgeConfigSpec.IntValue terrainCheckRadiusGiantMushroom;
    public final ForgeConfigSpec.IntValue terrainHeightDifferenceGiantMushroom;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsGiantMushroom;


    public final ForgeConfigSpec.BooleanValue enableGhoul;
    public final ForgeConfigSpec.IntValue weightGhoul;
    public final ForgeConfigSpec.IntValue minGroupSizeGhoul;
    public final ForgeConfigSpec.IntValue maxGroupSizeGhoul;
    public final ForgeConfigSpec.BooleanValue ghoulCanBurnInSunlight;
    public final ForgeConfigSpec.BooleanValue ghoulCanBeWithered;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistGhoul;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistGhoul;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistGhoul;

    public final ForgeConfigSpec.BooleanValue enableRevenant;
    public final ForgeConfigSpec.IntValue weightRevenant;
    public final ForgeConfigSpec.IntValue minGroupSizeRevenant;
    public final ForgeConfigSpec.IntValue maxGroupSizeRevenant;
    public final ForgeConfigSpec.BooleanValue revenantCanBurnInSunlight;
    public final ForgeConfigSpec.BooleanValue revenantCanBeWithered;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistRevenant;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistRevenant;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistRevenant;

    public final ForgeConfigSpec.BooleanValue enableReaper;
    public final ForgeConfigSpec.IntValue weightReaper;
    public final ForgeConfigSpec.IntValue minGroupSizeReaper;
    public final ForgeConfigSpec.IntValue maxGroupSizeReaper;
    public final ForgeConfigSpec.BooleanValue reaperCanBurnInSunlight;
    public final ForgeConfigSpec.BooleanValue reaperCanBeWithered;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistReaper;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistReaper;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistReaper;

    public final ForgeConfigSpec.BooleanValue enableNightmare;
    public final ForgeConfigSpec.IntValue weightNightmare;
    public final ForgeConfigSpec.IntValue minGroupSizeNightmare;
    public final ForgeConfigSpec.IntValue maxGroupSizeNightmare;
    public final ForgeConfigSpec.BooleanValue nightmareCanBurnInSunlight;
    public final ForgeConfigSpec.BooleanValue nightmareCanBeWithered;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistNightmare;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistNightmare;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistNightmare;

    public final ForgeConfigSpec.BooleanValue enableSkeletonCreeper;
    public final ForgeConfigSpec.IntValue weightSkeletonCreeper;
    public final ForgeConfigSpec.IntValue minGroupSizeSkeletonCreeper;
    public final ForgeConfigSpec.IntValue maxGroupSizeSkeletonCreeper;
    public final ForgeConfigSpec.BooleanValue skeletonCreeperCanBurnInSunlight;
    public final ForgeConfigSpec.BooleanValue skeletonCreeperCanBeWithered;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistSkeletonCreeper;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistSkeletonCreeper;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistSkeletonCreeper;

    public final ForgeConfigSpec.BooleanValue enableAcolyte;
    public final ForgeConfigSpec.IntValue weightAcolyte;
    public final ForgeConfigSpec.IntValue minGroupSizeAcolyte;
    public final ForgeConfigSpec.IntValue maxGroupSizeAcolyte;
    public final ForgeConfigSpec.BooleanValue acolyteCanBurnInSunlight;
    public final ForgeConfigSpec.BooleanValue acolyteCanBeWithered;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistAcolyte;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistAcolyte;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistAcolyte;

    public final ForgeConfigSpec.BooleanValue enableWraith;
    public final ForgeConfigSpec.IntValue weightWraith;
    public final ForgeConfigSpec.IntValue minGroupSizeWraith;
    public final ForgeConfigSpec.IntValue maxGroupSizeWraith;
    public final ForgeConfigSpec.BooleanValue wraithCanBurnInSunlight;
    public final ForgeConfigSpec.BooleanValue wraithCanBeWithered;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistWraith;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistWraith;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistWraith;

    public final ForgeConfigSpec.BooleanValue enableCorruptedPillager;
    public final ForgeConfigSpec.IntValue weightCorruptedPillager;
    public final ForgeConfigSpec.IntValue minGroupSizeCorruptedPillager;
    public final ForgeConfigSpec.IntValue maxGroupSizeCorruptedPillager;
    public final ForgeConfigSpec.BooleanValue corruptedPillagerCanBurnInSunlight;
    public final ForgeConfigSpec.BooleanValue corruptedPillagerCanBeWithered;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistCorruptedPillager;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistCorruptedPillager;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistCorruptedPillager;

    public final ForgeConfigSpec.BooleanValue enableCorruptedVindicator;
    public final ForgeConfigSpec.IntValue weightCorruptedVindicator;
    public final ForgeConfigSpec.IntValue minGroupSizeCorruptedVindicator;
    public final ForgeConfigSpec.IntValue maxGroupSizeCorruptedVindicator;
    public final ForgeConfigSpec.BooleanValue corruptedVindicatorCanBurnInSunlight;
    public final ForgeConfigSpec.BooleanValue corruptedVindicatorCanBeWithered;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistCorruptedVindicator;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistCorruptedVindicator;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistCorruptedVindicator;

    public final ForgeConfigSpec.BooleanValue enableHorde;
    public final ForgeConfigSpec.IntValue mobSpawnAttempts;
    public final ForgeConfigSpec.IntValue ticksUntilSpawnHorde;
    public final ForgeConfigSpec.IntValue additionalRandomizedTicks;

    public final ForgeConfigSpec.BooleanValue enableMossParticle;
    public final ForgeConfigSpec.IntValue particleFrequency;

    public final ForgeConfigSpec.BooleanValue urnHasDoubleInventory;
    public final ForgeConfigSpec.BooleanValue disableWitherSkeletonSpawner;
    public final ForgeConfigSpec.IntValue maxTerrainHeightDifference;

    public CommonConfig(ForgeConfigSpec.Builder builder) {
        builder.comment(" Welcome to The Graveyard Config!" +
                "\n" +
                "Structures:\n" +
                "Configure separation (Minimum distance between two structures of this type in chunks. Must be less than spacing).\n" +
                "Configure spacing (Average distance between two structure placement attempts of this type in chunks).\n" +
                "Configure whitelist: use \"minecraft:biome\" to whitelist biomes (mod identifier + \":\" + biome name).\n" +
                "Configure mod whitelist: use \"#modid\" to whitelist all biomes of the specified mod for structures to spawn in (examples: #minecraft, #graveyard, #graveyard_biomes, #terralith, #byg, #bop).\n" +
                "A full list of all the biomes can be found here https:minecraft.fandom.com/wiki/Biome#Biome_IDs.\n" +
                "Configure terrain check radius: set how far from structure placement a block is checked in all cardinal directions. Only necessary for the large graveyard.\n" +
                "Configure max terrain height: set how far apart the different heights from the terrain check can be. Increase this value to allow more structures to spawn, but to also increase chance of weird placement.\n" +
                "Configure if graveyard mobs can spawn naturally in structures.\n" +
                "\n" +
                "Mobs:\n" +
                "Configure spawning weight and group size of the spawn.\n" +
                "Configure if mobs burn in sunlight and/or if mobs are affected by the wither effect.\n" +
                "Configure whitelist: use \"minecraft:biome\" to whitelist biomes (mod identifier + \":\" + biome name).\n" +
                "Configure blacklist: use \"minecraft:biome\" to blacklist biomes (mod identifier + \":\" + biome name).\n" +
                "Configure mod whitelist: use \"#modid\" to whitelist all biomes of the specified mod for structures to spawn in (examples: #minecraft, #graveyard, #graveyard_biomes, #terralith, #byg, #bop).\n" +
                "\n" +
                "Additional:\n" +
                "Configure graveyard fog particles rising from moss and set the chance of spawning them (higher numbers = lower chance of spawning).\n" +
                "Configure if hordes of graveyard mobs can spawn and set their size and frequency.\n" +
                "Configure if urns have a double chest inventory.\n" +
                "Configure if the wither skeleton spawner in the large graveyard will be replaced by a skeleton spawner.\n");

        builder.push("The Graveyard - Structures Config");


        this.canGenerateHauntedHouse = builder.define("hauntedHouse.generate", true);
        this.spacingHauntedHouse = builder.defineInRange("hauntedHouse.spacing", 20, 0, 200);
        this.separationHauntedHouse = builder.defineInRange("hauntedHouse.separation", 15, 0, 200);
        this.saltHauntedHouse = builder.defineInRange("hauntedHouse.salt", 451235912, 0, 10000000000L);
        this.whitelistHauntedHouse = builder.defineList("hauntedHouse.whitelist", Arrays.asList(
                "minecraft:dark_forest",
                "minecraft:swamp",
                "minecraft:mangrove_swamp",
                "minecraft:old_growth_pine_taiga",
                "minecraft:old_growth_spruce_taiga",
                "minecraft:mangrove_swamp",
                "terralith:moonlight_valley",
                "terralith:cloud_forest",
                "graveyard_biomes:haunted_lakes"), o -> o instanceof String);
        this.modWhitelistHauntedHouse = builder.defineList("hauntedHouse.modWhitelist", Collections.emptyList(), o -> o instanceof String);
        this.terrainCheckRadiusHauntedHouse = builder.defineInRange("hauntedHouse.terrainCheckRadius", 30, 0, 250);
        this.terrainHeightDifferenceHauntedHouse = builder.defineInRange("hauntedHouse.terrainHeightDifference", 5, 1, 100);
        this.canSpawnGraveyardMobsHauntedHouse = builder.define("hauntedHouse.canSpawnMobs", false);

        this.canGenerateLargeGraveyard = builder.define("largeGraveyard.generate", true);
        this.spacingLargeGraveyard = builder.defineInRange("largeGraveyard.spacing", 12, 0, 200);
        this.separationLargeGraveyard = builder.defineInRange("largeGraveyard.separation", 10, 0, 200);
        this.saltLargeGraveyard = builder.defineInRange("largeGraveyard.salt", 304812394, 0, 10000000000L);
        this.whitelistLargeGraveyard = builder.defineList("largeGraveyard.whitelist", Arrays.asList(
                "minecraft:taiga",
                "minecraft:snowy_taiga",
                "minecraft:snowy_plains",
                "minecraft:plains",
                "terralith:forested_highlands",
                "terralith:lush_valley",
                "terralith:shield",
                "terralith:shield_clearing",
                "terralith:wintry_forest",
                "graveyard_biomes:haunted_forest"), o -> o instanceof String);
        this.modWhitelistLargeGraveyard = builder.defineList("largeGraveyard.modWhitelist", Collections.emptyList(), o -> o instanceof String);
        this.terrainCheckRadiusLargeGraveyard = builder.defineInRange("largeGraveyard.terrainCheckRadius", 60, 0, 250);
        this.terrainHeightDifferenceLargeGraveyard = builder.defineInRange("largeGraveyard.terrainHeightDifference", 5, 1, 100);
        this.canSpawnGraveyardMobsLargeGraveyard = builder.define("largeGraveyard.canSpawnMobs", true);

        this.canGenerateMediumGraveyard = builder.define("mediumGraveyard.generate", true);
        this.spacingMediumGraveyard = builder.defineInRange("mediumGraveyard.spacing", 18, 0, 200);
        this.separationMediumGraveyard = builder.defineInRange("mediumGraveyard.separation", 16, 0, 200);
        this.saltMediumGraveyard = builder.defineInRange("mediumGraveyard.salt", 1690192399, 0, 10000000000L);
        this.whitelistMediumGraveyard = builder.defineList("mediumGraveyard.whitelist", Arrays.asList(
                "minecraft:forest",
                "minecraft:flower_forest",
                "minecraft:windswept_forest",
                "terralith:brushland",
                "terralith:blooming_valley",
                "terralith:temperate_highlands",
                "graveyard_biomes:eroded_haunted_forest"), o -> o instanceof String);
        this.modWhitelistMediumGraveyard = builder.defineList("mediumGraveyard.modWhitelist", Collections.emptyList(), o -> o instanceof String);
        this.terrainCheckRadiusMediumGraveyard = builder.defineInRange("mediumGraveyard.terrainCheckRadius", 30, 0, 250);
        this.terrainHeightDifferenceMediumGraveyard = builder.defineInRange("mediumGraveyard.terrainHeightDifference", 3, 1, 100);
        this.canSpawnGraveyardMobsMediumGraveyard = builder.define("mediumGraveyard.canSpawnMobs", true);

        this.canGenerateMemorialTree = builder.define("memorialTree.generate", true);
        this.spacingMemorialTree = builder.defineInRange("memorialTree.spacing", 14, 0, 200);
        this.separationMemorialTree = builder.defineInRange("memorialTree.separation", 12, 0, 200);
        this.saltMemorialTree = builder.defineInRange("memorialTree.salt", 529239621, 0, 10000000000L);
        this.whitelistMemorialTree = builder.defineList("memorialTree.whitelist", Arrays.asList(
                "minecraft:old_growth_birch_forest",
                "minecraft:birch_forest",
                "terralith:birch_taiga"), o -> o instanceof String);
        this.modWhitelistMemorialTree = builder.defineList("memorialTree.modWhitelist", Collections.emptyList(), o -> o instanceof String);
        this.terrainCheckRadiusMemorialTree = builder.defineInRange("memorialTree.terrainCheckRadius", 10, 0, 250);
        this.terrainHeightDifferenceMemorialTree = builder.defineInRange("memorialTree.terrainHeightDifference", 3, 1, 100);
        this.canSpawnGraveyardMobsMemorialTree = builder.define("memorialTree.canSpawnMobs", false);

        this.canGenerateMushroomGrave = builder.define("mushroomGrave.generate", true);
        this.spacingMushroomGrave = builder.defineInRange("mushroomGrave.spacing", 24, 0, 200);
        this.separationMushroomGrave = builder.defineInRange("mushroomGrave.separation", 18, 0, 200);
        this.saltMushroomGrave = builder.defineInRange("mushroomGrave.salt", 379123039, 0, 10000000000L);
        this.whitelistMushroomGrave = builder.defineList("mushroomGrave.whitelist", Arrays.asList(
                "minecraft:mushroom_fields",
                "minecraft:jungle",
                "minecraft:sparse_jungle",
                "minecraft:bamboo_jungle",
                "minecraft:swamp",
                "minecraft:mangrove_swamp"), o -> o instanceof String);
        this.modWhitelistMushroomGrave = builder.defineList("mushroomGrave.modWhitelist", Collections.emptyList(), o -> o instanceof String);
        this.terrainCheckRadiusMushroomGrave = builder.defineInRange("mushroomGrave.terrainCheckRadius", 7, 0, 250);
        this.terrainHeightDifferenceMushroomGrave = builder.defineInRange("mushroomGrave.terrainHeightDifference", 3, 1, 100);
        this.canSpawnGraveyardMobsMushroomGrave = builder.define("mushroomGrave.canSpawnMobs", false);

        this.canGenerateSmallDesertGrave = builder.define("smallDesertGrave.generate", true);
        this.spacingSmallDesertGrave = builder.defineInRange("smallDesertGrave.spacing", 20, 0, 200);
        this.separationSmallDesertGrave = builder.defineInRange("smallDesertGrave.separation", 16, 0, 200);
        this.saltSmallDesertGrave = builder.defineInRange("smallDesertGrave.salt", 681236914, 0, 10000000000L);
        this.whitelistSmallDesertGrave = builder.defineList("smallDesertGrave.whitelist", Arrays.asList(
                "minecraft:desert",
                "terralith:desert_canyon",
                "terralith:desert_oasis",
                "terralith:desert_spires",
                "terralith:ancient_sands"), o -> o instanceof String);
        this.modWhitelistSmallDesertGrave = builder.defineList("smallDesertGrave.modWhitelist", Collections.emptyList(), o -> o instanceof String);
        this.terrainCheckRadiusSmallDesertGrave = builder.defineInRange("smallDesertGrave.terrainCheckRadius", 4, 0, 250);
        this.terrainHeightDifferenceSmallDesertGrave = builder.defineInRange("smallDesertGrave.terrainHeightDifference", 3, 1, 100);
        this.canSpawnGraveyardMobsSmallDesertGrave = builder.define("smallDesertGrave.canSpawnMobs", false);

        this.canGenerateSmallDesertGraveyard = builder.define("smallDesertGraveyard.generate", true);
        this.spacingSmallDesertGraveyard = builder.defineInRange("smallDesertGraveyard.spacing", 32, 0, 200);
        this.separationSmallDesertGraveyard = builder.defineInRange("smallDesertGraveyard.separation", 28, 0, 200);
        this.saltSmallDesertGraveyard = builder.defineInRange("smallDesertGraveyard.salt", 598017285, 0, 10000000000L);
        this.whitelistSmallDesertGraveyard = builder.defineList("smallDesertGraveyard.whitelist", Arrays.asList(
                "minecraft:desert",
                "terralith:desert_canyon",
                "terralith:desert_oasis",
                "terralith:desert_spires",
                "terralith:ancient_sands"), o -> o instanceof String);
        this.modWhitelistSmallDesertGraveyard = builder.defineList("smallDesertGraveyard.modWhitelist", Collections.emptyList(), o -> o instanceof String);
        this.terrainCheckRadiusSmallDesertGraveyard = builder.defineInRange("smallDesertGraveyard.terrainCheckRadius", 20, 0, 250);
        this.terrainHeightDifferenceSmallDesertGraveyard = builder.defineInRange("smallDesertGraveyard.terrainHeightDifference", 3, 1, 100);
        this.canSpawnGraveyardMobsSmallDesertGraveyard = builder.define("smallDesertGraveyard.canSpawnMobs", false);

        this.canGenerateSmallGrave = builder.define("smallGrave.generate", true);
        this.spacingSmallGrave = builder.defineInRange("smallGrave.spacing", 25, 0, 200);
        this.separationSmallGrave = builder.defineInRange("smallGrave.separation", 20, 0, 200);
        this.saltSmallGrave = builder.defineInRange("smallGrave.salt", 451235912, 0, 10000000000L);
        this.whitelistSmallGrave = builder.defineList("smallGrave.whitelist", Arrays.asList(
                "minecraft:forest",
                "minecraft:sunflower_plains",
                "minecraft:plains",
                "minecraft:meadow",
                "minecraft:windswept_forest",
                "minecraft:old_growth_birch_forest",
                "minecraft:taiga",
                "minecraft:flower_forest",
                "minecraft:birch_forest",
                "terralith:blooming_plateau",
                "terralith:blooming_valley"), o -> o instanceof String);
        this.modWhitelistSmallGrave = builder.defineList("smallGrave.modWhitelist", Collections.emptyList(), o -> o instanceof String);
        this.terrainCheckRadiusSmallGrave = builder.defineInRange("smallGrave.terrainCheckRadius", 4, 0, 250);
        this.terrainHeightDifferenceSmallGrave = builder.defineInRange("smallGrave.terrainHeightDifference", 3, 1, 100);
        this.canSpawnGraveyardMobsSmallGrave = builder.define("smallGrave.canSpawnMobs", false);

        this.canGenerateSmallSavannaGrave = builder.define("smallSavannaGrave.generate", true);
        this.spacingSmallSavannaGrave = builder.defineInRange("smallSavannaGrave.spacing", 12, 0, 200);
        this.separationSmallSavannaGrave = builder.defineInRange("smallSavannaGrave.separation", 8, 0, 200);
        this.saltSmallSavannaGrave = builder.defineInRange("smallSavannaGrave.salt", 709787761, 0, 10000000000L);
        this.whitelistSmallSavannaGrave = builder.defineList("smallSavannaGrave.whitelist", Arrays.asList(
                "minecraft:badlands",
                "minecraft:eroded_badlands",
                "minecraft:wooded_badlands",
                "minecraft:savanna",
                "minecraft:savanna_plateau",
                "minecraft:windswept_savanna",
                "terralith:savanna_badlands",
                "terralith:savanna_slopes",
                "terralith:fractured_savanna"), o -> o instanceof String);
        this.modWhitelistSmallSavannaGrave = builder.defineList("smallSavannaGrave.modWhitelist", Collections.emptyList(), o -> o instanceof String);
        this.terrainCheckRadiusSavannaGrave = builder.defineInRange("smallSavannaGrave.terrainCheckRadius", 4, 0, 250);
        this.terrainHeightDifferenceSavannaGrave = builder.defineInRange("smallSavannaGrave.terrainHeightDifference", 3, 1, 100);
        this.canSpawnGraveyardMobsSmallSavannaGrave = builder.define("smallSavannaGrave.canSpawnMobs", false);

        this.canGenerateSmallGraveyard = builder.define("smallGraveyard.generate", true);
        this.spacingSmallGraveyard = builder.defineInRange("smallGraveyard.spacing", 20, 0, 200);
        this.separationSmallGraveyard = builder.defineInRange("smallGraveyard.separation", 18, 0, 200);
        this.saltSmallGraveyard = builder.defineInRange("smallGraveyard.salt", 240451934, 0, 10000000000L);
        this.whitelistSmallGraveyard = builder.defineList("smallGraveyard.whitelist", Arrays.asList(
                "minecraft:sunflower_plains",
                "minecraft:plains",
                "minecraft:meadow",
                "terralith:blooming_plateau",
                "terralith:blooming_valley"), o -> o instanceof String);
        this.modWhitelistSmallGraveyard = builder.defineList("smallGraveyard.modWhitelist", Collections.emptyList(), o -> o instanceof String);
        this.terrainCheckRadiusSmallGraveyard = builder.defineInRange("smallGraveyard.terrainCheckRadius", 15, 0, 250);
        this.terrainHeightDifferenceSmallGraveyard = builder.defineInRange("smallGraveyard.terrainHeightDifference", 3, 1, 100);
        this.canSpawnGraveyardMobsSmallGraveyard = builder.define("smallGraveyard.canSpawnMobs", false);

        this.canGenerateSmallMountainGrave = builder.define("smallMountainGraveyard.generate", true);
        this.spacingSmallMountainGrave = builder.defineInRange("smallMountainGraveyard.spacing", 12, 0, 200);
        this.separationSmallMountainGrave = builder.defineInRange("smallMountainGraveyard.separation", 8, 0, 200);
        this.saltSmallMountainGrave = builder.defineInRange("smallMountainGraveyard.salt", 725689810, 0, 10000000000L);
        this.whitelistSmallMountainGrave = builder.defineList("smallMountainGraveyard.whitelist", Arrays.asList(
                "minecraft:grove",
                "minecraft:snowy_slopes",
                "minecraft:frozen_peaks",
                "minecraft:jagged_peaks",
                "minecraft:stony_peaks",
                "terralith:painted_mountains",
                "terralith:rocky_mountains",
                "terralith:haze_mountain",
                "terralith:mountain_steppe"), o -> o instanceof String);
        this.modWhitelistSmallMountainGrave = builder.defineList("smallMountainGraveyard.modWhitelist", Collections.emptyList(), o -> o instanceof String);
        this.terrainCheckRadiusSmallMountainGrave = builder.defineInRange("smallMountainGraveyard.terrainCheckRadius", 4, 0, 250);
        this.terrainHeightDifferenceSmallMountainGrave = builder.defineInRange("smallMountainGraveyard.terrainHeightDifference", 3, 1, 100);
        this.canSpawnGraveyardMobsSmallMountainGrave = builder.define("smallMountainGraveyard.canSpawnMobs", false);

        this.canGenerateCrypt = builder.define("crypt.generate", true);
        this.spacingCrypt = builder.defineInRange("crypt.spacing", 24, 0, 200);
        this.separationCrypt = builder.defineInRange("crypt.separation", 22, 0, 200);
        this.saltCrypt = builder.defineInRange("crypt.salt", 893183913, 0, 10000000000L);
        this.whitelistCrypt = builder.defineList("crypt.whitelist", Arrays.asList(
                "minecraft:dripstone_caves",
                "minecraft:lush_caves",
                "terralith:cave/andesite_caves",
                "terralith:cave/crystal_caves",
                "terralith:cave/deep_caves",
                "terralith:cave/desert_caves",
                "terralith:cave/diorite_caves",
                "terralith:cave/frostfire_caves",
                "terralith:cave/fungal_caves",
                "terralith:cave/granite_caves",
                "terralith:cave/ice_caves",
                "terralith:cave/infested_caves",
                "terralith:cave/mantle_caves",
                "terralith:cave/thermal_caves",
                "terralith:cave/tuff_caves"), o -> o instanceof String);
        this.modWhitelistCrypt = builder.defineList("crypt.modWhitelist", Collections.emptyList(), o -> o instanceof String);
        this.terrainCheckRadiusCrypt = builder.defineInRange("crypt.terrainCheckRadius", 1, 0, 250);
        this.terrainHeightDifferenceCrypt = builder.defineInRange("crypt.terrainHeightDifference", 3, 1, 100);
        this.canSpawnGraveyardMobsCrypt = builder.define("crypt.canSpawnMobs", false);

        this.canGenerateAltar = builder.define("altar.generate", true);
        this.spacingAltar = builder.defineInRange("altar.spacing", 30, 0, 200);
        this.separationAltar = builder.defineInRange("altar.separation", 24, 0, 200);
        this.saltAltar = builder.defineInRange("altar.salt", 1093123913, 0, 10000000000L);
        this.whitelistAltar = builder.defineList("altar.whitelist", Arrays.asList(
                "minecraft:snowy_plains",
                "minecraft:ice_spikes"), o -> o instanceof String);
        this.modWhitelistAltar = builder.defineList("altar.modWhitelist", Collections.emptyList(), o -> o instanceof String);
        this.terrainCheckRadiusAltar = builder.defineInRange("altar.terrainCheckRadius", 7, 0, 250);
        this.terrainHeightDifferenceAltar = builder.defineInRange("altar.terrainHeightDifference", 3, 1, 100);
        this.canSpawnGraveyardMobsAltar = builder.define("altar.canSpawnMobs", false);

        this.canGenerateGiantMushroom = builder.define("giantMushroom.generate", true);
        this.spacingGiantMushroom = builder.defineInRange("giantMushroom.spacing", 20, 0, 200);
        this.separationGiantMushroom = builder.defineInRange("giantMushroom.separation", 18, 0, 200);
        this.saltGiantMushroom = builder.defineInRange("giantMushroom.salt", 365012356, 0, 10000000000L);
        this.whitelistGiantMushroom = builder.defineList("giantMushroom.whitelist", Arrays.asList(
                "minecraft:mushroom_fields"), o -> o instanceof String);
        this.modWhitelistGiantMushroom = builder.defineList("giantMushroom.modWhitelist", Collections.emptyList(), o -> o instanceof String);
        this.terrainCheckRadiusGiantMushroom = builder.defineInRange("giantMushroom.terrainCheckRadius", 10, 0, 250);
        this.terrainHeightDifferenceGiantMushroom = builder.defineInRange("giantMushroom.terrainHeightDifference", 3, 1, 100);
        this.canSpawnGraveyardMobsGiantMushroom = builder.define("giantMushroom.canSpawnMobs", false);

        builder.pop();


        builder.push("The Graveyard - Mob Spawning Config");
        this.enableGhoul = builder.define("ghoul.enabled", true);
        this.weightGhoul = builder.defineInRange("ghoul.weight", 25, 0, 100);
        this.minGroupSizeGhoul = builder.defineInRange("ghoul.minGroupSizeGhoul", 2, 1, 100);
        this.maxGroupSizeGhoul = builder.defineInRange("ghoul.maxGroupSizeGhoul", 5, 1, 100);
        this.ghoulCanBurnInSunlight = builder.define("ghoul.canBurnInSunlight", true);
        this.ghoulCanBeWithered = builder.define("ghoul.canBeWithered", false);
        this.whitelistGhoul = builder.defineList("ghoul.whitelistedBiomes", Collections.emptyList(), o -> o instanceof String);
        this.blacklistGhoul = builder.defineList("ghoul.blacklistedBiomes", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistGhoul = builder.defineList("ghoul.whitelistedModId", Arrays.asList("#minecraft", "#graveyard_biomes", "#terralith"), o -> o instanceof String);

        this.enableRevenant = builder.define("revenant.enabled", true);
        this.weightRevenant = builder.defineInRange("revenant.weight", 25, 0, 100);
        this.minGroupSizeRevenant = builder.defineInRange("revenant.minGroupSizeRevenant", 5, 1, 100);
        this.maxGroupSizeRevenant = builder.defineInRange("revenant.maxGroupSizeRevenant", 8, 1, 100);
        this.revenantCanBurnInSunlight = builder.define("revenant.canBurnInSunlight", true);
        this.revenantCanBeWithered = builder.define("revenant.canBeWithered", false);
        this.whitelistRevenant = builder.defineList("revenant.whitelistedBiomes", Collections.emptyList(), o -> o instanceof String);
        this.blacklistRevenant = builder.defineList("revenant.blacklistedBiomes", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistRevenant = builder.defineList("revenant.whitelistedModId", Arrays.asList("#minecraft", "#graveyard_biomes", "#terralith"), o -> o instanceof String);

        this.enableReaper = builder.define("reaper.enabled", true);
        this.weightReaper = builder.defineInRange("reaper.weight", 10, 0, 100);
        this.minGroupSizeReaper = builder.defineInRange("reaper.minGroupSizeReaper", 2, 1, 100);
        this.maxGroupSizeReaper = builder.defineInRange("reaper.maxGroupSizeGhoul", 3, 1, 100);
        this.reaperCanBurnInSunlight = builder.define("reaper.canBurnInSunlight", true);
        this.reaperCanBeWithered = builder.define("reaper.canBeWithered", false);
        this.whitelistReaper = builder.defineList("reaper.whitelistedBiomes", Arrays.asList("graveyard_biomes:eroded_haunted_forest"), o -> o instanceof String);
        this.blacklistReaper = builder.defineList("reaper.blacklistedBiomes", Collections.emptyList(), o -> o instanceof String);
        this.modWhitelistReaper = builder.defineList("reaper.whitelistedModId", Collections.emptyList(), o -> o instanceof String);

        this.enableNightmare = builder.define("nightmare.enabled", true);
        this.weightNightmare = builder.defineInRange("nightmare.weight", 7, 0, 100);
        this.minGroupSizeNightmare = builder.defineInRange("nightmare.minGroupSizeNightmare", 1, 1, 100);
        this.maxGroupSizeNightmare = builder.defineInRange("nightmare.maxGroupSizeNightmare", 1, 1, 100);
        this.nightmareCanBurnInSunlight = builder.define("nightmare.canBurnInSunlight", false);
        this.nightmareCanBeWithered = builder.define("nightmare.canBeWithered", false);
        this.whitelistNightmare = builder.defineList("nightmare.whitelistedBiomes", Collections.emptyList(), o -> o instanceof String);
        this.blacklistNightmare = builder.defineList("nightmare.blacklistedBiomes", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistNightmare = builder.defineList("nightmare.whitelistedModId", Arrays.asList("#minecraft", "#graveyard_biomes", "#terralith"), o -> o instanceof String);

        this.enableSkeletonCreeper = builder.define("skeleton_creeper.enabled", true);
        this.weightSkeletonCreeper = builder.defineInRange("skeleton_creeper.weight", 20, 0, 100);
        this.minGroupSizeSkeletonCreeper = builder.defineInRange("skeleton_creeper.minGroupSizeSkeletonCreeper", 1, 1, 100);
        this.maxGroupSizeSkeletonCreeper = builder.defineInRange("skeleton_creeper.maxGroupSizeSkeletonCreeper", 4, 1, 100);
        this.skeletonCreeperCanBurnInSunlight = builder.define("skeleton_creeper.canBurnInSunlight", true);
        this.skeletonCreeperCanBeWithered = builder.define("skeleton_creeper.canBeWithered", false);
        this.whitelistSkeletonCreeper = builder.defineList("skeleton_creeper.whitelistedBiomes", Collections.emptyList(), o -> o instanceof String);
        this.blacklistSkeletonCreeper = builder.defineList("skeleton_creeper.blacklistedBiomes", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistSkeletonCreeper = builder.defineList("skeleton_creeper.whitelistedModId", Arrays.asList("#minecraft", "#graveyard_biomes", "#terralith"), o -> o instanceof String);

        this.enableAcolyte = builder.define("acolyte.enabled", false);
        this.weightAcolyte = builder.defineInRange("acolyte.weight", 0, 0, 100);
        this.minGroupSizeAcolyte = builder.defineInRange("acolyte.minGroupSizeAcolyte", 2, 1, 100);
        this.maxGroupSizeAcolyte = builder.defineInRange("acolyte.maxGroupSizeAcolyte", 3, 1, 100);
        this.acolyteCanBurnInSunlight = builder.define("acolyte.canBurnInSunlight", false);
        this.acolyteCanBeWithered = builder.define("acolyte.canBeWithered", false);
        this.whitelistAcolyte = builder.defineList("acolyte.whitelistedBiomes", Collections.emptyList(), o -> o instanceof String);
        this.blacklistAcolyte = builder.defineList("acolyte.blacklistedBiomes", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistAcolyte = builder.defineList("acolyte.whitelistedModId", Arrays.asList("#minecraft", "#graveyard_biomes", "#terralith"), o -> o instanceof String);

        this.enableWraith = builder.define("wraith.enabled", false);
        this.weightWraith = builder.defineInRange("wraith.weight", 0, 0, 100);
        this.minGroupSizeWraith = builder.defineInRange("wraith.minGroupSizeWraith", 2, 1, 100);
        this.maxGroupSizeWraith = builder.defineInRange("wraith.maxGroupSizeWraith", 3, 1, 100);
        this.wraithCanBurnInSunlight = builder.define("wraith.canBurnInSunlight", true);
        this.wraithCanBeWithered = builder.define("wraith.canBeWithered", false);
        this.whitelistWraith = builder.defineList("wraith.whitelistedBiomes", Collections.emptyList(), o -> o instanceof String);
        this.blacklistWraith = builder.defineList("wraith.blacklistedBiomes", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistWraith = builder.defineList("wraith.whitelistedModId", Arrays.asList("#minecraft", "#graveyard_biomes", "#terralith"), o -> o instanceof String);

        this.enableCorruptedPillager = builder.define("corruptedPillager.enabled", false);
        this.weightCorruptedPillager = builder.defineInRange("corruptedPillager.weight", 0, 0, 100);
        this.minGroupSizeCorruptedPillager = builder.defineInRange("corruptedPillager.minGroupSizeCorruptedPillager", 2, 1, 100);
        this.maxGroupSizeCorruptedPillager = builder.defineInRange("corruptedPillager.maxGroupSizeCorruptedPillager", 3, 1, 100);
        this.corruptedPillagerCanBurnInSunlight = builder.define("corruptedPillager.canBurnInSunlight", true);
        this.corruptedPillagerCanBeWithered = builder.define("corruptedPillager.canBeWithered", false);
        this.whitelistCorruptedPillager = builder.defineList("corruptedPillager.whitelistedBiomes", Collections.emptyList(), o -> o instanceof String);
        this.blacklistCorruptedPillager = builder.defineList("corruptedPillager.blacklistedBiomes", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistCorruptedPillager = builder.defineList("corruptedPillager.whitelistedModId", Arrays.asList("#minecraft", "#graveyard_biomes", "#terralith"), o -> o instanceof String);

        this.enableCorruptedVindicator = builder.define("corruptedVindicator.enabled", false);
        this.weightCorruptedVindicator = builder.defineInRange("corruptedVindicator.weight", 0, 0, 100);
        this.minGroupSizeCorruptedVindicator = builder.defineInRange("corruptedVindicator.minGroupSizeCorruptedVindicator", 2, 1, 100);
        this.maxGroupSizeCorruptedVindicator = builder.defineInRange("corruptedVindicator.maxGroupSizeCorruptedVindicator", 3, 1, 100);
        this.corruptedVindicatorCanBurnInSunlight = builder.define("corruptedVindicator.canBurnInSunlight", true);
        this.corruptedVindicatorCanBeWithered = builder.define("corruptedVindicator.canBeWithered", false);
        this.whitelistCorruptedVindicator = builder.defineList("corruptedVindicator.whitelistedBiomes", Collections.emptyList(), o -> o instanceof String);
        this.blacklistCorruptedVindicator = builder.defineList("corruptedVindicator.blacklistedBiomes", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistCorruptedVindicator = builder.defineList("corruptedVindicator.whitelistedModId", Arrays.asList("#minecraft", "#graveyard_biomes", "#terralith"), o -> o instanceof String);

        builder.pop();

        builder.push("The Graveyard - Horde Config");
        this.enableHorde = builder.define("horde.generate", true);
        this.mobSpawnAttempts = builder.defineInRange("horde.mobSpawnAttempts", 40, 0, 1000);
        this.ticksUntilSpawnHorde = builder.defineInRange("horde.ticksUntilSpawnHorde", 24000, 1, 1000000);
        this.additionalRandomizedTicks = builder.defineInRange("horde.additionalRandomizedTicks", 1200, 1, 1000000);
        builder.pop();

        builder.push("The Graveyard - Particle Config");
        this.enableMossParticle = builder.define("particle.generate", true);
        this.particleFrequency = builder.defineInRange("particle.frequency", 50, 1, 500);
        builder.pop();

        builder.push("The Graveyard - Additional Config");
        this.urnHasDoubleInventory = builder.define("urn.urnHasDoubleInventory", true);
        this.disableWitherSkeletonSpawner = builder.define("spawner.disableWitherSkeletonSpawner", false);
        this.maxTerrainHeightDifference = builder.defineInRange("terrain.maxTerrainHeightDifference", 5, 1, 100);
        builder.pop();
    }

    private List<String> getMobBlacklist() {
        Set<ResourceKey<Biome>> biomes = new HashSet<>();
        biomes.add(Biomes.END_BARRENS);
        biomes.add(Biomes.SMALL_END_ISLANDS);
        biomes.add(Biomes.END_MIDLANDS);
        biomes.add(Biomes.END_HIGHLANDS);
        biomes.add(Biomes.THE_END);
        biomes.add(Biomes.BASALT_DELTAS);
        biomes.add(Biomes.CRIMSON_FOREST);
        biomes.add(Biomes.WARPED_FOREST);
        biomes.add(Biomes.NETHER_WASTES);
        biomes.add(Biomes.DEEP_DARK);
        biomes.add(Biomes.MUSHROOM_FIELDS);
        biomes.add(Biomes.DEEP_FROZEN_OCEAN);
        biomes.add(Biomes.FROZEN_OCEAN);
        biomes.add(Biomes.DEEP_COLD_OCEAN);
        biomes.add(Biomes.COLD_OCEAN);
        biomes.add(Biomes.DEEP_OCEAN);
        biomes.add(Biomes.OCEAN);
        biomes.add(Biomes.DEEP_LUKEWARM_OCEAN);
        biomes.add(Biomes.LUKEWARM_OCEAN);
        biomes.add(Biomes.WARM_OCEAN);
        biomes.add(Biomes.FROZEN_RIVER);
        biomes.add(Biomes.RIVER);
        biomes.add(Biomes.FLOWER_FOREST);
        biomes.add(Biomes.THE_VOID);
        biomes.add(Biomes.SOUL_SAND_VALLEY);


        return new ArrayList<>(biomes.stream().map(value -> value.location().toString()).toList());

    }
}
