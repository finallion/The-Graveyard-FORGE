package com.finallion.graveyard.config;

import com.finallion.graveyard.init.TGStructureFeatures;
import com.finallion.graveyard.world.structures.AbstractGraveyardStructure;
import com.finallion.graveyard.world.structures.AbstractUndergroundStructure;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;

public class CommonConfig {
    public final Map<String, StructureConfigEntry> structureConfigEntries = new HashMap<>();

    /*
    public final ForgeConfigSpec.BooleanValue canGenerateHauntedHouse;
    public final ForgeConfigSpec.IntValue spacingHauntedHouse;
    public final ForgeConfigSpec.IntValue separationHauntedHouse;
    public final ForgeConfigSpec.LongValue saltHauntedHouse;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistHauntedHouse;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistHauntedHouse;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistHauntedHouse;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsHauntedHouse;

    public final ForgeConfigSpec.BooleanValue canGenerateMediumGraveyard;
    public final ForgeConfigSpec.IntValue spacingMediumGraveyard;
    public final ForgeConfigSpec.IntValue separationMediumGraveyard;
    public final ForgeConfigSpec.LongValue saltMediumGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistMediumGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistMediumGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistMediumGraveyard;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsMediumGraveyard;

    public final ForgeConfigSpec.BooleanValue canGenerateLargeGraveyard;
    public final ForgeConfigSpec.IntValue spacingLargeGraveyard;
    public final ForgeConfigSpec.IntValue separationLargeGraveyard;
    public final ForgeConfigSpec.LongValue saltLargeGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistLargeGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistLargeGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistLargeGraveyard;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsLargeGraveyard;

    public final ForgeConfigSpec.BooleanValue canGenerateSmallGrave;
    public final ForgeConfigSpec.IntValue spacingSmallGrave;
    public final ForgeConfigSpec.IntValue separationSmallGrave;
    public final ForgeConfigSpec.LongValue saltSmallGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistSmallGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistSmallGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistSmallGrave;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsSmallGrave;

    public final ForgeConfigSpec.BooleanValue canGenerateSmallDesertGrave;
    public final ForgeConfigSpec.IntValue spacingSmallDesertGrave;
    public final ForgeConfigSpec.IntValue separationSmallDesertGrave;
    public final ForgeConfigSpec.LongValue saltSmallDesertGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistSmallDesertGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistSmallDesertGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistSmallDesertGrave;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsSmallDesertGrave;

    public final ForgeConfigSpec.BooleanValue canGenerateSmallSavannaGrave;
    public final ForgeConfigSpec.IntValue spacingSmallSavannaGrave;
    public final ForgeConfigSpec.IntValue separationSmallSavannaGrave;
    public final ForgeConfigSpec.LongValue saltSmallSavannaGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistSmallSavannaGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistSmallSavannaGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistSmallSavannaGrave;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsSmallSavannaGrave;

    public final ForgeConfigSpec.BooleanValue canGenerateSmallMountainGrave;
    public final ForgeConfigSpec.IntValue spacingSmallMountainGrave;
    public final ForgeConfigSpec.IntValue separationSmallMountainGrave;
    public final ForgeConfigSpec.LongValue saltSmallMountainGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistSmallMountainGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistSmallMountainGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistSmallMountainGrave;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsSmallMountainGrave;

    public final ForgeConfigSpec.BooleanValue canGenerateSmallGraveyard;
    public final ForgeConfigSpec.IntValue spacingSmallGraveyard;
    public final ForgeConfigSpec.IntValue separationSmallGraveyard;
    public final ForgeConfigSpec.LongValue saltSmallGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistSmallGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistSmallGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistSmallGraveyard;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsSmallGraveyard;

    public final ForgeConfigSpec.BooleanValue canGenerateSmallDesertGraveyard;
    public final ForgeConfigSpec.IntValue spacingSmallDesertGraveyard;
    public final ForgeConfigSpec.IntValue separationSmallDesertGraveyard;
    public final ForgeConfigSpec.LongValue saltSmallDesertGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistSmallDesertGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistSmallDesertGraveyard;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistSmallDesertGraveyard;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsSmallDesertGraveyard;

    public final ForgeConfigSpec.BooleanValue canGenerateMushroomGrave;
    public final ForgeConfigSpec.IntValue spacingMushroomGrave;
    public final ForgeConfigSpec.IntValue separationMushroomGrave;
    public final ForgeConfigSpec.LongValue saltMushroomGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistMushroomGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistMushroomGrave;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistMushroomGrave;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsMushroomGrave;

    public final ForgeConfigSpec.BooleanValue canGenerateMemorialTree;
    public final ForgeConfigSpec.IntValue spacingMemorialTree;
    public final ForgeConfigSpec.IntValue separationMemorialTree;
    public final ForgeConfigSpec.LongValue saltMemorialTree;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistMemorialTree;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistMemorialTree;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> modWhitelistMemorialTree;
    public final ForgeConfigSpec.BooleanValue canSpawnGraveyardMobsMemorialTree;


     */
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
        builder.comment(" Welcome to The Graveyard Config!\n" +
                "\n" +
                " Structures:\n" +
                " Configure separation (Minimum distance between two structures of this type in chunks. Must be less than spacing).\n" +
                " Configure spacing (Average distance between two structure placement attempts of this type in chunks).\n" +
                " Configure whitelist: use \"#biomeCategory\" for biome categories and \"minecraft:biome\" for biomes (mod identifier + \":\" + biome name).\n" +
                " Configure blacklist: use \"#biomeCategory\" for biome categories and \"minecraft:biome\" for biomes (mod identifier + \":\" + biome name).\n" +
                " Configure mod whitelist: use \"#modid\" to whitelist the biomes of the specified mod for structures to spawn in (examples: #minecraft, #graveyard, #graveyard_biomes, #terralith, #byg, #bop).\n" +
                " Valid vanilla biome categories are: #taiga, #extreme_hills, #jungle, #mesa, #plains, #savanna, #icy, #beach, #forest, #desert, #swamp, #mushroom, #underground, #mountain.\n" +
                " A full list of all the biomes can be found here https:minecraft.fandom.com/wiki/Biome#Biome_IDs.\n" +
                "\n" +
                " Mobs:\n" +
                " Configure spawning weight and group size of the spawn.\n" +
                " Configure if mobs burn in sunlight and/or if mobs are affected by the wither effect.\n" +
                " Configure whitelist: use \"#biomeCategory\" for biome categories and \"minecraft:biome\" for biomes (mod identifier + \":\" + biome name).\n" +
                " Configure blacklist: use \"#biomeCategory\" for biome categories and \"minecraft:biome\" for biomes (mod identifier + \":\" + biome name).\n" +
                " Configure mod whitelist: use \"#modid\" to whitelist the biomes of the specified mod for structures to spawn in (examples: #minecraft, #graveyard, #graveyard_biomes, #terralith, #byg, #bop).\n" +
                "\n" +
                " Additional:\n" +
                " Configure graveyard fog particles rising from moss and set the chance of spawning them (higher numbers = lower chance of spawning).\n" +
                " Configure if hordes of graveyard mobs can spawn and set their size and frequency.\n" +
                " Configure if urns have a double chest inventory.\n");

        builder.push("The Graveyard - Structures Config");

        for (StructureFeature<?> structure : getStructures()) {
            AbstractGraveyardStructure abstractStructure = (AbstractGraveyardStructure) structure;
            StructureConfigEntry entry = abstractStructure.getStructureConfigEntry();
            String name = abstractStructure.getStructureName();

            entry.canGenerate = builder.define(name + ".canGenerate", true);
            entry.spacing = builder.defineInRange(name + ".spacing", entry.getDefaultSpacing(),0, 200);
            entry.separation = builder.defineInRange(name + ".separation", entry.getDefaultSeparation(),0, 200);
            entry.salt = builder.defineInRange(name + ".salt", entry.getDefaultSalt(), 0, 10000000000L);
            entry.whitelist = builder.defineList(name + ".whitelist", entry.getDefaultWhitelist(), o -> o instanceof String);
            entry.blacklist = builder.defineList(name + ".blacklist", entry.getDefaultBlacklist(), o -> o instanceof String);
            entry.modWhitelist = builder.defineList(name + ".modWhitelist", entry.getDefaultModWhitelist(), o -> o instanceof String);
            entry.canSpawnGraveyardMobs = builder.define(name + ".canSpawnMobs", entry.getDefaultCanSpawnGraveyardMobs());

            structureConfigEntries.putIfAbsent(abstractStructure.getStructureName(), entry);
        }

        for (StructureFeature<?> structure : getUndergroundStructures()) {
            AbstractUndergroundStructure abstractStructure = (AbstractUndergroundStructure) structure;
            StructureConfigEntry entry = abstractStructure.getStructureConfigEntry();
            String name = abstractStructure.getStructureName();

            entry.canGenerate = builder.define(name + ".canGenerate", true);
            entry.spacing = builder.defineInRange(name + ".spacing", entry.getDefaultSpacing(),0, 200);
            entry.separation = builder.defineInRange(name + ".separation", entry.getDefaultSeparation(),0, 200);
            entry.salt = builder.defineInRange(name + ".salt", entry.getDefaultSalt(), 0, 10000000000L);
            entry.whitelist = builder.defineList(name + ".whitelist", entry.getDefaultWhitelist(), o -> o instanceof String);
            entry.blacklist = builder.defineList(name + ".blacklist", entry.getDefaultBlacklist(), o -> o instanceof String);
            entry.modWhitelist = builder.defineList(name + ".modWhitelist", entry.getDefaultModWhitelist(), o -> o instanceof String);
            entry.canSpawnGraveyardMobs = builder.define(name + ".canSpawnMobs", entry.getDefaultCanSpawnGraveyardMobs());

            structureConfigEntries.putIfAbsent(abstractStructure.getStructureName(), entry);
        }

        /*
        this.canGenerateHauntedHouse = builder.define("hauntedHouse.generate", true);
        this.spacingHauntedHouse = builder.defineInRange("hauntedHouse.spacing", 25,0, 200);
        this.separationHauntedHouse = builder.defineInRange("hauntedHouse.separation", 20,0, 200);
        this.saltHauntedHouse = builder.defineInRange("hauntedHouse.salt", 451235912, 0, 10000000000L);
        this.whitelistHauntedHouse = builder.defineList("hauntedHouse.whitelist", Arrays.asList("#" + Biome.BiomeCategory.FOREST.getName(), "#" + Biome.BiomeCategory.SWAMP.getName()), o -> o instanceof String);
        this.blacklistHauntedHouse = builder.defineList("hauntedHouse.blacklist", Arrays.asList("minecraft:forest", "minecraft:flower_forest", "minecraft:birch_forest", "minecraft:old_growth_birch_forest", "minecraft:windswept_forest", "graveyard_biomes:haunted_forest"), o -> o instanceof String);
        this.modWhitelistHauntedHouse = builder.defineList("hauntedHouse.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);
        this.canSpawnGraveyardMobsHauntedHouse = builder.define("hauntedHouse.canSpawnMobs", false);

        this.canGenerateLargeGraveyard = builder.define("largeGraveyard.generate", true);
        this.spacingLargeGraveyard = builder.defineInRange("largeGraveyard.spacing", 12,0, 200);
        this.separationLargeGraveyard = builder.defineInRange("largeGraveyard.separation", 10,0, 200);
        this.saltLargeGraveyard = builder.defineInRange("largeGraveyard.salt", 304812394,0, 10000000000L);
        this.whitelistLargeGraveyard = builder.defineList("largeGraveyard.whitelist", Arrays.asList("#" + Biome.BiomeCategory.FOREST.getName(), "#" + Biome.BiomeCategory.TAIGA.getName()), o -> o instanceof String);
        this.blacklistLargeGraveyard = builder.defineList("largeGraveyard.blacklist", Arrays.asList("minecraft:forest", "minecraft:flower_forest", "minecraft:birch_forest", "minecraft:old_growth_birch_forest", "minecraft:windswept_forest", "graveyard_biomes:eroded_haunted_forest", "graveyard_biomes:haunted_lakes"), o -> o instanceof String);
        this.modWhitelistLargeGraveyard = builder.defineList("largeGraveyard.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);
        this.canSpawnGraveyardMobsLargeGraveyard = builder.define("largeGraveyard.canSpawnMobs", true);

        this.canGenerateMediumGraveyard = builder.define("mediumGraveyard.generate", true);
        this.spacingMediumGraveyard = builder.defineInRange("mediumGraveyard.spacing", 18,0, 200);
        this.separationMediumGraveyard = builder.defineInRange("mediumGraveyard.separation", 16,0, 200);
        this.saltMediumGraveyard = builder.defineInRange("mediumGraveyard.salt", 1690192399,0, 10000000000L);
        this.whitelistMediumGraveyard = builder.defineList("mediumGraveyard.whitelist", Arrays.asList("#" + Biome.BiomeCategory.FOREST.getName()), o -> o instanceof String);
        this.blacklistMediumGraveyard = builder.defineList("mediumGraveyard.blacklist", Arrays.asList("minecraft:dark_forest", "minecraft:birch_forest", "minecraft:old_growth_birch_forest", "graveyard_biomes:haunted_lakes", "graveyard_biomes:haunted_forest"), o -> o instanceof String);
        this.modWhitelistMediumGraveyard = builder.defineList("mediumGraveyard.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);
        this.canSpawnGraveyardMobsMediumGraveyard = builder.define("mediumGraveyard.canSpawnMobs", true);

        this.canGenerateMemorialTree = builder.define("memorialTree.generate", true);
        this.spacingMemorialTree = builder.defineInRange("memorialTree.spacing", 14,0, 200);
        this.separationMemorialTree = builder.defineInRange("memorialTree.separation", 12,0, 200);
        this.saltMemorialTree = builder.defineInRange("memorialTree.salt", 529239621,0, 10000000000L);
        this.whitelistMemorialTree = builder.defineList("memorialTree.whitelist", Arrays.asList("minecraft:old_growth_birch_forest", "minecraft:birch_forest", "terralith:birch_taiga"), o -> o instanceof String);
        this.blacklistMemorialTree = builder.defineList("memorialTree.blacklist", Collections.emptyList(), o -> o instanceof String);
        this.modWhitelistMemorialTree = builder.defineList("memorialTree.modWhitelist", Arrays.asList("#minecraft", "#terralith"), o -> o instanceof String);
        this.canSpawnGraveyardMobsMemorialTree = builder.define("memorialTree.canSpawnMobs", false);

        this.canGenerateMushroomGrave = builder.define("mushroomGrave.generate", true);
        this.spacingMushroomGrave = builder.defineInRange("mushroomGrave.spacing", 24,0, 200);
        this.separationMushroomGrave = builder.defineInRange("mushroomGrave.separation", 18,0, 200);
        this.saltMushroomGrave = builder.defineInRange("mushroomGrave.salt", 379123039,0, 10000000000L);
        this.whitelistMushroomGrave = builder.defineList("mushroomGrave.whitelist", Arrays.asList("#" + Biome.BiomeCategory.MUSHROOM.getName(), "#" + Biome.BiomeCategory.JUNGLE.getName(), "#" + Biome.BiomeCategory.SWAMP.getName()), o -> o instanceof String);
        this.blacklistMushroomGrave = builder.defineList("mushroomGrave.blacklist", Collections.emptyList(), o -> o instanceof String);
        this.modWhitelistMushroomGrave = builder.defineList("mushroomGrave.modWhitelist", Arrays.asList("#minecraft", "#terralith"), o -> o instanceof String);
        this.canSpawnGraveyardMobsMushroomGrave = builder.define("mushroomGrave.canSpawnMobs", false);

        this.canGenerateSmallDesertGrave = builder.define("smallDesertGrave.generate", true);
        this.spacingSmallDesertGrave = builder.defineInRange("smallDesertGrave.spacing", 20,0, 200);
        this.separationSmallDesertGrave = builder.defineInRange("smallDesertGrave.separation", 16,0, 200);
        this.saltSmallDesertGrave = builder.defineInRange("smallDesertGrave.salt", 681236914,0, 10000000000L);
        this.whitelistSmallDesertGrave = builder.defineList("smallDesertGrave.whitelist", Arrays.asList("#" + Biome.BiomeCategory.DESERT.getName()), o -> o instanceof String);
        this.blacklistSmallDesertGrave = builder.defineList("smallDesertGrave.blacklist", Collections.emptyList(), o -> o instanceof String);
        this.modWhitelistSmallDesertGrave = builder.defineList("smallDesertGrave.modWhitelist", Arrays.asList("#minecraft", "#terralith"), o -> o instanceof String);
        this.canSpawnGraveyardMobsSmallDesertGrave = builder.define("smallDesertGrave.canSpawnMobs", false);

        this.canGenerateSmallDesertGraveyard = builder.define("smallDesertGraveyard.generate", true);
        this.spacingSmallDesertGraveyard = builder.defineInRange("smallDesertGraveyard.spacing", 32,0, 200);
        this.separationSmallDesertGraveyard = builder.defineInRange("smallDesertGraveyard.separation", 28,0, 200);
        this.saltSmallDesertGraveyard = builder.defineInRange("smallDesertGraveyard.salt", 598017285,0, 10000000000L);
        this.whitelistSmallDesertGraveyard = builder.defineList("smallDesertGraveyard.whitelist", Arrays.asList("#" + Biome.BiomeCategory.DESERT.getName()), o -> o instanceof String);
        this.blacklistSmallDesertGraveyard = builder.defineList("smallDesertGraveyard.blacklist", Collections.emptyList(), o -> o instanceof String);
        this.modWhitelistSmallDesertGraveyard = builder.defineList("smallDesertGraveyard.modWhitelist", Arrays.asList("#minecraft", "#terralith"), o -> o instanceof String);
        this.canSpawnGraveyardMobsSmallDesertGraveyard = builder.define("smallDesertGraveyard.canSpawnMobs", false);

        this.canGenerateSmallGrave = builder.define("smallGrave.generate", true);
        this.spacingSmallGrave = builder.defineInRange("smallGrave.spacing", 25,0, 200);
        this.separationSmallGrave = builder.defineInRange("smallGrave.separation", 20,0, 200);
        this.saltSmallGrave = builder.defineInRange("smallGrave.salt", 451235912,0, 10000000000L);
        this.whitelistSmallGrave = builder.defineList("smallGrave.whitelist", Arrays.asList("#" + Biome.BiomeCategory.FOREST.getName(), "#" + Biome.BiomeCategory.SWAMP.getName()), o -> o instanceof String);
        this.blacklistSmallGrave = builder.defineList("smallGrave.blacklist", Arrays.asList("minecraft:forest", "minecraft:flower_forest", "minecraft:birch_forest", "minecraft:old_growth_birch_forest", "minecraft:windswept_forest", "graveyard_biomes:haunted_forest"), o -> o instanceof String);
        this.modWhitelistSmallGrave = builder.defineList("smallGrave.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);
        this.canSpawnGraveyardMobsSmallGrave = builder.define("smallGrave.canSpawnMobs", false);

        this.canGenerateSmallSavannaGrave = builder.define("smallSavannaGrave.generate", true);
        this.spacingSmallSavannaGrave = builder.defineInRange("smallSavannaGrave.spacing", 12,0, 200);
        this.separationSmallSavannaGrave = builder.defineInRange("smallSavannaGrave.separation", 8,0, 200);
        this.saltSmallSavannaGrave = builder.defineInRange("smallSavannaGrave.salt", 709787761,0, 10000000000L);
        this.whitelistSmallSavannaGrave = builder.defineList("smallSavannaGrave.whitelist", Arrays.asList("#" + Biome.BiomeCategory.MESA.getName(), "#" + Biome.BiomeCategory.SAVANNA.getName()), o -> o instanceof String);
        this.blacklistSmallSavannaGrave = builder.defineList("smallSavannaGrave.blacklist", Collections.emptyList(), o -> o instanceof String);
        this.modWhitelistSmallSavannaGrave = builder.defineList("smallSavannaGrave.modWhitelist", Arrays.asList("#minecraft", "#terralith"), o -> o instanceof String);
        this.canSpawnGraveyardMobsSmallSavannaGrave = builder.define("smallSavannaGrave.canSpawnMobs", false);

        this.canGenerateSmallGraveyard = builder.define("smallGraveyard.generate", true);
        this.spacingSmallGraveyard = builder.defineInRange("smallGraveyard.spacing", 20,0, 200);
        this.separationSmallGraveyard = builder.defineInRange("smallGraveyard.separation", 18,0, 200);
        this.saltSmallGraveyard = builder.defineInRange("smallGraveyard.salt", 240451934,0, 10000000000L);
        this.whitelistSmallGraveyard = builder.defineList("smallGraveyard.whitelist", Arrays.asList("#" + Biome.BiomeCategory.PLAINS.getName()), o -> o instanceof String);
        this.blacklistSmallGraveyard = builder.defineList("smallGraveyard.blacklist", Collections.emptyList(), o -> o instanceof String);
        this.modWhitelistSmallGraveyard = builder.defineList("smallGraveyard.modWhitelist", Arrays.asList("#minecraft", "#terralith"), o -> o instanceof String);
        this.canSpawnGraveyardMobsSmallGraveyard = builder.define("smallGraveyard.canSpawnMobs", false);

        this.canGenerateSmallMountainGrave = builder.define("smallMountainGraveyard.generate", true);
        this.spacingSmallMountainGrave = builder.defineInRange("smallMountainGraveyard.spacing", 12,0, 200);
        this.separationSmallMountainGrave = builder.defineInRange("smallMountainGraveyard.separation", 8,0, 200);
        this.saltSmallMountainGrave = builder.defineInRange("smallMountainGraveyard.salt", 725689810,0, 10000000000L);
        this.whitelistSmallMountainGrave = builder.defineList("smallMountainGraveyard.whitelist", Arrays.asList("#" + Biome.BiomeCategory.MOUNTAIN.getName(), "#" + Biome.BiomeCategory.EXTREME_HILLS.getName()), o -> o instanceof String);
        this.blacklistSmallMountainGrave = builder.defineList("smallMountainGraveyard.blacklist", Collections.emptyList(), o -> o instanceof String);
        this.modWhitelistSmallMountainGrave = builder.defineList("smallMountainGraveyard.modWhitelist", Arrays.asList("#minecraft", "#terralith"), o -> o instanceof String);
        this.canSpawnGraveyardMobsSmallMountainGrave = builder.define("smallMountainGraveyard.canSpawnMobs", false);



         */
        builder.pop();

        builder.push("The Graveyard - Mob Spawning Config");
        this.enableGhoul = builder.define("ghoul.enabled", true);
        this.weightGhoul = builder.defineInRange("ghoul.weight", 25, 0, 100);
        this.minGroupSizeGhoul = builder.defineInRange("ghoul.minGroupSizeGhoul", 2, 1, 100);
        this.maxGroupSizeGhoul = builder.defineInRange("ghoul.maxGroupSizeGhoul", 5, 1, 100);
        this.ghoulCanBurnInSunlight = builder.define("ghoul.canBurnInSunlight", true);
        this.ghoulCanBeWithered = builder.define("ghoul.canBeWithered", false);
        this.whitelistGhoul = builder.defineList("ghoul.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistGhoul = builder.defineList("ghoul.blacklist", Arrays.asList("minecraft:flower_forest", "minecraft:lush_caves"), o -> o instanceof String);
        this.modWhitelistGhoul = builder.defineList("ghoul.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableRevenant = builder.define("revenant.enabled", true);
        this.weightRevenant = builder.defineInRange("revenant.weight", 25, 0, 100);
        this.minGroupSizeRevenant = builder.defineInRange("revenant.minGroupSizeRevenant", 5, 1, 100);
        this.maxGroupSizeRevenant = builder.defineInRange("revenant.maxGroupSizeRevenant", 8, 1, 100);
        this.revenantCanBurnInSunlight = builder.define("revenant.canBurnInSunlight", true);
        this.revenantCanBeWithered = builder.define("revenant.canBeWithered", false);
        this.whitelistRevenant = builder.defineList("revenant.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistRevenant = builder.defineList("revenant.blacklist", Arrays.asList("minecraft:flower_forest", "minecraft:lush_caves"), o -> o instanceof String);
        this.modWhitelistRevenant = builder.defineList("revenant.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableReaper = builder.define("reaper.enabled", true);
        this.weightReaper = builder.defineInRange("reaper.weight", 10, 0, 100);
        this.minGroupSizeReaper = builder.defineInRange("reaper.minGroupSizeReaper", 2, 1, 100);
        this.maxGroupSizeReaper = builder.defineInRange("reaper.maxGroupSizeGhoul", 3, 1, 100);
        this.reaperCanBurnInSunlight = builder.define("reaper.canBurnInSunlight", true);
        this.reaperCanBeWithered = builder.define("reaper.canBeWithered", false);
        this.whitelistReaper = builder.defineList("reaper.whitelist", Arrays.asList("graveyard_biomes:eroded_haunted_forest"), o -> o instanceof String);
        this.blacklistReaper = builder.defineList("reaper.blacklist", Arrays.asList("minecraft:flower_forest", "minecraft:lush_caves"), o -> o instanceof String);
        this.modWhitelistReaper = builder.defineList("reaper.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableNightmare = builder.define("nightmare.enabled", true);
        this.weightNightmare = builder.defineInRange("nightmare.weight", 7, 0, 100);
        this.minGroupSizeNightmare = builder.defineInRange("nightmare.minGroupSizeNightmare", 1, 1, 100);
        this.maxGroupSizeNightmare = builder.defineInRange("nightmare.maxGroupSizeNightmare", 1, 1, 100);
        this.nightmareCanBurnInSunlight = builder.define("nightmare.canBurnInSunlight", false);
        this.nightmareCanBeWithered = builder.define("nightmare.canBeWithered", false);
        this.whitelistNightmare = builder.defineList("nightmare.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistNightmare = builder.defineList("nightmare.blacklist", Arrays.asList("minecraft:flower_forest", "minecraft:lush_caves"), o -> o instanceof String);
        this.modWhitelistNightmare = builder.defineList("nightmare.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableSkeletonCreeper = builder.define("skeleton_creeper.enabled", true);
        this.weightSkeletonCreeper = builder.defineInRange("skeleton_creeper.weight", 20, 0, 100);
        this.minGroupSizeSkeletonCreeper = builder.defineInRange("skeleton_creeper.minGroupSizeSkeletonCreeper", 1, 1, 100);
        this.maxGroupSizeSkeletonCreeper = builder.defineInRange("skeleton_creeper.maxGroupSizeSkeletonCreeper", 4, 1, 100);
        this.skeletonCreeperCanBurnInSunlight = builder.define("skeleton_creeper.canBurnInSunlight", true);
        this.skeletonCreeperCanBeWithered = builder.define("skeleton_creeper.canBeWithered", false);
        this.whitelistSkeletonCreeper = builder.defineList("skeleton_creeper.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistSkeletonCreeper = builder.defineList("skeleton_creeper.blacklist", Arrays.asList("minecraft:flower_forest", "minecraft:lush_caves"), o -> o instanceof String);
        this.modWhitelistSkeletonCreeper = builder.defineList("skeleton_creeper.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableAcolyte = builder.define("acolyte.enabled", false);
        this.weightAcolyte = builder.defineInRange("acolyte.weight", 0, 0, 100);
        this.minGroupSizeAcolyte = builder.defineInRange("acolyte.minGroupSizeAcolyte", 2, 1, 100);
        this.maxGroupSizeAcolyte = builder.defineInRange("acolyte.maxGroupSizeAcolyte", 3, 1, 100);
        this.acolyteCanBurnInSunlight = builder.define("acolyte.canBurnInSunlight", false);
        this.acolyteCanBeWithered = builder.define("acolyte.canBeWithered", false);
        this.whitelistAcolyte = builder.defineList("acolyte.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistAcolyte = builder.defineList("acolyte.blacklist", Arrays.asList("minecraft:flower_forest", "minecraft:lush_caves"), o -> o instanceof String);
        this.modWhitelistAcolyte = builder.defineList("acolyte.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableWraith = builder.define("wraith.enabled", false);
        this.weightWraith = builder.defineInRange("wraith.weight", 0, 0, 100);
        this.minGroupSizeWraith = builder.defineInRange("wraith.minGroupSizeWraith", 2, 1, 100);
        this.maxGroupSizeWraith = builder.defineInRange("wraith.maxGroupSizeWraith", 3, 1, 100);
        this.wraithCanBurnInSunlight = builder.define("wraith.canBurnInSunlight", true);
        this.wraithCanBeWithered = builder.define("wraith.canBeWithered", false);
        this.whitelistWraith = builder.defineList("wraith.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistWraith = builder.defineList("wraith.blacklist", Arrays.asList("minecraft:flower_forest", "minecraft:lush_caves"), o -> o instanceof String);
        this.modWhitelistWraith = builder.defineList("wraith.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableCorruptedPillager = builder.define("corruptedPillager.enabled", false);
        this.weightCorruptedPillager = builder.defineInRange("corruptedPillager.weight", 0, 0, 100);
        this.minGroupSizeCorruptedPillager = builder.defineInRange("corruptedPillager.minGroupSizeCorruptedPillager", 2, 1, 100);
        this.maxGroupSizeCorruptedPillager = builder.defineInRange("corruptedPillager.maxGroupSizeCorruptedPillager", 3, 1, 100);
        this.corruptedPillagerCanBurnInSunlight = builder.define("corruptedPillager.canBurnInSunlight", true);
        this.corruptedPillagerCanBeWithered = builder.define("corruptedPillager.canBeWithered", false);
        this.whitelistCorruptedPillager = builder.defineList("corruptedPillager.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistCorruptedPillager = builder.defineList("corruptedPillager.blacklist", Arrays.asList("minecraft:flower_forest", "minecraft:lush_caves"), o -> o instanceof String);
        this.modWhitelistCorruptedPillager = builder.defineList("corruptedPillager.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableCorruptedVindicator = builder.define("corruptedVindicator.enabled", false);
        this.weightCorruptedVindicator = builder.defineInRange("corruptedVindicator.weight", 0, 0, 100);
        this.minGroupSizeCorruptedVindicator = builder.defineInRange("corruptedVindicator.minGroupSizeCorruptedVindicator", 2, 1, 100);
        this.maxGroupSizeCorruptedVindicator = builder.defineInRange("corruptedVindicator.maxGroupSizeCorruptedVindicator", 3, 1, 100);
        this.corruptedVindicatorCanBurnInSunlight = builder.define("corruptedVindicator.canBurnInSunlight", true);
        this.corruptedVindicatorCanBeWithered = builder.define("corruptedVindicator.canBeWithered", false);
        this.whitelistCorruptedVindicator = builder.defineList("corruptedVindicator.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistCorruptedVindicator = builder.defineList("corruptedVindicator.blacklist", Arrays.asList("minecraft:flower_forest", "minecraft:lush_caves"), o -> o instanceof String);
        this.modWhitelistCorruptedVindicator = builder.defineList("corruptedVindicator.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        builder.pop();

        builder.push("The Graveyard - Horde Config");
        this.enableHorde = builder.define("horde.generate", true);
        this.mobSpawnAttempts = builder.defineInRange("horde.mobSpawnAttempts", 40, 0, 1000);
        this.ticksUntilSpawnHorde = builder.defineInRange("horde.ticksUntilSpawnHorde", 24000, 1, 1000000);
        this.additionalRandomizedTicks = builder.defineInRange("horde.additionalRandomizedTicks", 1200, 1, 1000000);
        builder.pop();

        builder.push("The Graveyard - Particle Config");
        this.enableMossParticle = builder.define("particle.generate", true);
        this.particleFrequency = builder.defineInRange("particle.frequency",50, 1, 500);
        builder.pop();

        builder.push("The Graveyard - Additional Config");
        this.urnHasDoubleInventory = builder.define("urn.urnHasDoubleInventory", true);
        this.disableWitherSkeletonSpawner = builder.define("spawner.disableWitherSkeletonSpawner", false);
        this.maxTerrainHeightDifference = builder.defineInRange("terrain.maxTerrainHeightDifference", 6, 1, 100);
        builder.pop();
    }


    private List<String> getAllOverworldBiomeCategories() {
        Biome.BiomeCategory[] biomeCategory = Biome.BiomeCategory.values();
        List<String> biomeNames = new ArrayList<>();
        for (Biome.BiomeCategory biome : biomeCategory) {
            if (biome.getName().contains("river") || biome.getName().contains("ocean") || biome.getName().contains("none") || biome.getName().contains("the_end") || biome.getName().contains("nether") || biome.getName().contains("mushroom")) {
                continue;
            }
            biomeNames.add("#" + biome.getName());
        }
        return biomeNames;

    }


    private List<StructureFeature<?>> getStructures() {
        List<StructureFeature<?>> structures = new ArrayList<>();
        structures.add(TGStructureFeatures.MEDIUM_GRAVEYARD_STRUCTURE);
        structures.add(TGStructureFeatures.SMALL_GRAVEYARD_STRUCTURE);
        structures.add(TGStructureFeatures.LARGE_GRAVEYARD_STRUCTURE);
        structures.add(TGStructureFeatures.MUSHROOM_GRAVE_STRUCTURE);
        structures.add(TGStructureFeatures.HAUNTED_HOUSE_STRUCTURE);
        structures.add(TGStructureFeatures.MEMORIAL_TREE_STRUCTURE);
        structures.add(TGStructureFeatures.SMALL_DESERT_GRAVEYARD_STRUCTURE);
        structures.add(TGStructureFeatures.SMALL_GRAVE_STRUCTURE);
        structures.add(TGStructureFeatures.SMALL_DESERT_GRAVE_STRUCTURE);
        structures.add(TGStructureFeatures.SMALL_SAVANNA_GRAVE_STRUCTURE);
        structures.add(TGStructureFeatures.SMALL_MOUNTAIN_GRAVE_STRUCTURE);
        structures.add(TGStructureFeatures.GIANT_MUSHROOM_STRUCTURE);
        structures.add(TGStructureFeatures.ALTAR_STRUCTURE);
        return structures;
    }

    private List<StructureFeature<?>> getUndergroundStructures() {
        List<StructureFeature<?>> structures = new ArrayList<>();
        structures.add(TGStructureFeatures.CRYPT_STRUCTURE);
        return structures;
    }
}
