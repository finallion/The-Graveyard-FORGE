package com.finallion.graveyard.config;

import com.finallion.graveyard.init.TGStructureFeatures;
import com.finallion.graveyard.world.structures.AbstractFloatingStructure;
import com.finallion.graveyard.world.structures.AbstractGraveyardStructure;
import com.finallion.graveyard.world.structures.AbstractUndergroundStructure;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.*;

public class CommonConfig {
    public final Map<String, StructureConfigEntry> structureConfigEntries = new HashMap<>();

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
                "Configure whitelist:\n" +
                "   1) Single Biome: use \"modId:biome\" to whitelist biomes (mod identifier (for example: minecraft) + \":\" + biome name).\n" +
                "   A full list of all minecraft biomes can be found here https:minecraft.fandom.com/wiki/Biome#Biome_IDs.\n" +
                "   2) Biome Collection Tag: use \"#forge:biomeTag\" to whitelist the structure for any biome in this tag (#forge + \":\" + tag name WITHOUT is_ (!!)).\n" +
                "   A list of all valid tags can be found here: https://github.com/MinecraftForge/MinecraftForge/tree/1.18.x/src/generated/resources/data/forge/tags/worldgen/biome\n" +
                "   3) Biome Collection Tag: use \"#minecraft:biomeTag\" to whitelist the structure for any biome in this tag (#minecraft + \":\" + tag name).\n" +
                "   A list of all valid tags can be found here: https://minecraft.fandom.com/wiki/Tag#Biomes\n" +
                "Configure blacklist:\n" +
                "   use \"modId:biome\" to blacklist biomes (mod identifier (for example: minecraft) + \":\" + biome name).\n" +
                "   for example: minecraft:plains, byg:allium_fields, terralith:moonlight_valley ... \n" +
                "Configure terrain check radius: set how far from structure placement a block is checked in all cardinal directions. Only necessary for the large graveyard.\n" +
                "Configure max terrain height: set how far apart the different heights from the terrain check can be. Increase this value to allow more structures to spawn, but to also increase chance of weird placement.\n" +
                "Configure if graveyard mobs can spawn naturally in structures.\n" +
                "\n" +
                "Mobs:\n" +
                "Configure spawning weight and group size of the spawn.\n" +
                "Configure if mobs burn in sunlight and/or if mobs are affected by the wither effect.\n" +
                "Configure whitelist and blacklist, and also mod-wide whitelist.\n" +
                "Additional:\n" +
                "Configure graveyard fog particles rising from moss and set the chance of spawning them (higher numbers = lower chance of spawning).\n" +
                "Configure if hordes of graveyard mobs can spawn and set their size and frequency.\n" +
                "Configure if urns have a double chest inventory.\n" +
                "Configure if the wither skeleton spawner in the large graveyard will be replaced by a skeleton spawner.\n");

        builder.push("The Graveyard - Structures Config");



        for (StructureFeature<?> structure : getStructures()) {
            AbstractGraveyardStructure abstractStructure = (AbstractGraveyardStructure) structure;
            StructureConfigEntry entry = abstractStructure.getStructureConfigEntry();
            String name = abstractStructure.getStructureName();

            entry.canGenerate = builder.define(name + ".canGenerate", true);
            entry.spacing = builder.defineInRange(name + ".spacing", entry.getDefault_spacing(),0, 200);
            entry.separation = builder.defineInRange(name + ".separation", entry.getDefault_separation(),0, 200);
            entry.salt = builder.defineInRange(name + ".salt", entry.getDefault_salt(), 0, 10000000000L);
            entry.biomeWhitelist = builder.defineList(name + ".whitelist", entry.getDefault_biomeWhitelist(), o -> o instanceof String);
            entry.biomeBlacklist = builder.defineList(name + ".blacklist", entry.getDefault_biomeBlacklist(), o -> o instanceof String);
            entry.terrainCheckRadius = builder.defineInRange(name + ".terrainCheckRadius", entry.getDefault_terrainCheckRadius(), 0, 200);
            entry.maxTerrainHeightDifference = builder.defineInRange(name + ".maxTerrainHeightDifference", entry.getDefault_maxTerrainHeightDifference(), 0, 200);
            entry.canSpawnGraveyardMobs = builder.define(name + ".canSpawnMobs", entry.isDefault_canSpawnGraveyardMobs());

            structureConfigEntries.putIfAbsent(abstractStructure.getStructureName(), entry);
        }

        for (StructureFeature<?> structure : getUndergroundStructures()) {
            AbstractUndergroundStructure abstractStructure = (AbstractUndergroundStructure) structure;
            StructureConfigEntry entry = abstractStructure.getStructureConfigEntry();
            String name = abstractStructure.getStructureName();

            entry.canGenerate = builder.define(name + ".canGenerate", true);
            entry.spacing = builder.defineInRange(name + ".spacing", entry.getDefault_spacing(),0, 200);
            entry.separation = builder.defineInRange(name + ".separation", entry.getDefault_separation(),0, 200);
            entry.salt = builder.defineInRange(name + ".salt", entry.getDefault_salt(), 0, 10000000000L);
            entry.biomeWhitelist = builder.defineList(name + ".whitelist", entry.getDefault_biomeWhitelist(), o -> o instanceof String);
            entry.biomeBlacklist = builder.defineList(name + ".blacklist", entry.getDefault_biomeBlacklist(), o -> o instanceof String);
            entry.terrainCheckRadius = builder.defineInRange(name + ".terrainCheckRadius", entry.getDefault_terrainCheckRadius(), 0, 200);
            entry.maxTerrainHeightDifference = builder.defineInRange(name + ".maxTerrainHeightDifference", entry.getDefault_maxTerrainHeightDifference(), 0, 200);
            entry.canSpawnGraveyardMobs = builder.define(name + ".canSpawnMobs", entry.isDefault_canSpawnGraveyardMobs());

            structureConfigEntries.putIfAbsent(abstractStructure.getStructureName(), entry);
        }

        for (StructureFeature<?> structure : getFloatingStructures()) {
            AbstractFloatingStructure abstractStructure = (AbstractFloatingStructure) structure;
            StructureConfigEntry entry = abstractStructure.getStructureConfigEntry();
            String name = abstractStructure.getStructureName();

            entry.canGenerate = builder.define(name + ".canGenerate", true);
            entry.spacing = builder.defineInRange(name + ".spacing", entry.getDefault_spacing(),0, 200);
            entry.separation = builder.defineInRange(name + ".separation", entry.getDefault_separation(),0, 200);
            entry.salt = builder.defineInRange(name + ".salt", entry.getDefault_salt(), 0, 10000000000L);
            entry.biomeWhitelist = builder.defineList(name + ".whitelist", entry.getDefault_biomeWhitelist(), o -> o instanceof String);
            entry.biomeBlacklist = builder.defineList(name + ".blacklist", entry.getDefault_biomeBlacklist(), o -> o instanceof String);
            entry.terrainCheckRadius = builder.defineInRange(name + ".terrainCheckRadius", entry.getDefault_terrainCheckRadius(), 0, 200);
            entry.maxTerrainHeightDifference = builder.defineInRange(name + ".maxTerrainHeightDifference", entry.getDefault_maxTerrainHeightDifference(), 0, 200);
            entry.canSpawnGraveyardMobs = builder.define(name + ".canSpawnMobs", entry.isDefault_canSpawnGraveyardMobs());

            structureConfigEntries.putIfAbsent(abstractStructure.getStructureName(), entry);
        }

        builder.pop();

        builder.push("The Graveyard - Mob Spawning Config");
        this.enableGhoul = builder.define("ghoul.enabled", true);
        this.weightGhoul = builder.defineInRange("ghoul.weight", 25, 0, 100);
        this.minGroupSizeGhoul = builder.defineInRange("ghoul.minGroupSizeGhoul", 2, 1, 100);
        this.maxGroupSizeGhoul = builder.defineInRange("ghoul.maxGroupSizeGhoul", 5, 1, 100);
        this.ghoulCanBurnInSunlight = builder.define("ghoul.canBurnInSunlight", true);
        this.ghoulCanBeWithered = builder.define("ghoul.canBeWithered", false);
        this.whitelistGhoul = builder.defineList("ghoul.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistGhoul = builder.defineList("ghoul.blacklist", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistGhoul = builder.defineList("ghoul.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableRevenant = builder.define("revenant.enabled", true);
        this.weightRevenant = builder.defineInRange("revenant.weight", 25, 0, 100);
        this.minGroupSizeRevenant = builder.defineInRange("revenant.minGroupSizeRevenant", 5, 1, 100);
        this.maxGroupSizeRevenant = builder.defineInRange("revenant.maxGroupSizeRevenant", 8, 1, 100);
        this.revenantCanBurnInSunlight = builder.define("revenant.canBurnInSunlight", true);
        this.revenantCanBeWithered = builder.define("revenant.canBeWithered", false);
        this.whitelistRevenant = builder.defineList("revenant.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistRevenant = builder.defineList("revenant.blacklist", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistRevenant = builder.defineList("revenant.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableReaper = builder.define("reaper.enabled", true);
        this.weightReaper = builder.defineInRange("reaper.weight", 10, 0, 100);
        this.minGroupSizeReaper = builder.defineInRange("reaper.minGroupSizeReaper", 2, 1, 100);
        this.maxGroupSizeReaper = builder.defineInRange("reaper.maxGroupSizeGhoul", 3, 1, 100);
        this.reaperCanBurnInSunlight = builder.define("reaper.canBurnInSunlight", true);
        this.reaperCanBeWithered = builder.define("reaper.canBeWithered", false);
        this.whitelistReaper = builder.defineList("reaper.whitelist", Arrays.asList("graveyard_biomes:eroded_haunted_forest"), o -> o instanceof String);
        this.blacklistReaper = builder.defineList("reaper.blacklist", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistReaper = builder.defineList("reaper.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableNightmare = builder.define("nightmare.enabled", true);
        this.weightNightmare = builder.defineInRange("nightmare.weight", 7, 0, 100);
        this.minGroupSizeNightmare = builder.defineInRange("nightmare.minGroupSizeNightmare", 1, 1, 100);
        this.maxGroupSizeNightmare = builder.defineInRange("nightmare.maxGroupSizeNightmare", 1, 1, 100);
        this.nightmareCanBurnInSunlight = builder.define("nightmare.canBurnInSunlight", false);
        this.nightmareCanBeWithered = builder.define("nightmare.canBeWithered", false);
        this.whitelistNightmare = builder.defineList("nightmare.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistNightmare = builder.defineList("nightmare.blacklist", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistNightmare = builder.defineList("nightmare.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableSkeletonCreeper = builder.define("skeleton_creeper.enabled", true);
        this.weightSkeletonCreeper = builder.defineInRange("skeleton_creeper.weight", 20, 0, 100);
        this.minGroupSizeSkeletonCreeper = builder.defineInRange("skeleton_creeper.minGroupSizeSkeletonCreeper", 1, 1, 100);
        this.maxGroupSizeSkeletonCreeper = builder.defineInRange("skeleton_creeper.maxGroupSizeSkeletonCreeper", 4, 1, 100);
        this.skeletonCreeperCanBurnInSunlight = builder.define("skeleton_creeper.canBurnInSunlight", true);
        this.skeletonCreeperCanBeWithered = builder.define("skeleton_creeper.canBeWithered", false);
        this.whitelistSkeletonCreeper = builder.defineList("skeleton_creeper.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistSkeletonCreeper = builder.defineList("skeleton_creeper.blacklist", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistSkeletonCreeper = builder.defineList("skeleton_creeper.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableAcolyte = builder.define("acolyte.enabled", false);
        this.weightAcolyte = builder.defineInRange("acolyte.weight", 0, 0, 100);
        this.minGroupSizeAcolyte = builder.defineInRange("acolyte.minGroupSizeAcolyte", 2, 1, 100);
        this.maxGroupSizeAcolyte = builder.defineInRange("acolyte.maxGroupSizeAcolyte", 3, 1, 100);
        this.acolyteCanBurnInSunlight = builder.define("acolyte.canBurnInSunlight", false);
        this.acolyteCanBeWithered = builder.define("acolyte.canBeWithered", false);
        this.whitelistAcolyte = builder.defineList("acolyte.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistAcolyte = builder.defineList("acolyte.blacklist", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistAcolyte = builder.defineList("acolyte.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableWraith = builder.define("wraith.enabled", false);
        this.weightWraith = builder.defineInRange("wraith.weight", 0, 0, 100);
        this.minGroupSizeWraith = builder.defineInRange("wraith.minGroupSizeWraith", 2, 1, 100);
        this.maxGroupSizeWraith = builder.defineInRange("wraith.maxGroupSizeWraith", 3, 1, 100);
        this.wraithCanBurnInSunlight = builder.define("wraith.canBurnInSunlight", true);
        this.wraithCanBeWithered = builder.define("wraith.canBeWithered", false);
        this.whitelistWraith = builder.defineList("wraith.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistWraith = builder.defineList("wraith.blacklist", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistWraith = builder.defineList("wraith.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableCorruptedPillager = builder.define("corruptedPillager.enabled", false);
        this.weightCorruptedPillager = builder.defineInRange("corruptedPillager.weight", 0, 0, 100);
        this.minGroupSizeCorruptedPillager = builder.defineInRange("corruptedPillager.minGroupSizeCorruptedPillager", 2, 1, 100);
        this.maxGroupSizeCorruptedPillager = builder.defineInRange("corruptedPillager.maxGroupSizeCorruptedPillager", 3, 1, 100);
        this.corruptedPillagerCanBurnInSunlight = builder.define("corruptedPillager.canBurnInSunlight", true);
        this.corruptedPillagerCanBeWithered = builder.define("corruptedPillager.canBeWithered", false);
        this.whitelistCorruptedPillager = builder.defineList("corruptedPillager.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistCorruptedPillager = builder.defineList("corruptedPillager.blacklist", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistCorruptedPillager = builder.defineList("corruptedPillager.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableCorruptedVindicator = builder.define("corruptedVindicator.enabled", false);
        this.weightCorruptedVindicator = builder.defineInRange("corruptedVindicator.weight", 0, 0, 100);
        this.minGroupSizeCorruptedVindicator = builder.defineInRange("corruptedVindicator.minGroupSizeCorruptedVindicator", 2, 1, 100);
        this.maxGroupSizeCorruptedVindicator = builder.defineInRange("corruptedVindicator.maxGroupSizeCorruptedVindicator", 3, 1, 100);
        this.corruptedVindicatorCanBurnInSunlight = builder.define("corruptedVindicator.canBurnInSunlight", true);
        this.corruptedVindicatorCanBeWithered = builder.define("corruptedVindicator.canBeWithered", false);
        this.whitelistCorruptedVindicator = builder.defineList("corruptedVindicator.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistCorruptedVindicator = builder.defineList("corruptedVindicator.blacklist", getMobBlacklist(), o -> o instanceof String);
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
        structures.add(TGStructureFeatures.RUINS_STRUCTURE);
        return structures;
    }

    private List<StructureFeature<?>> getUndergroundStructures() {
        List<StructureFeature<?>> structures = new ArrayList<>();
        structures.add(TGStructureFeatures.CRYPT_STRUCTURE);
        return structures;
    }

    private List<StructureFeature<?>> getFloatingStructures() {
        List<StructureFeature<?>> structures = new ArrayList<>();
        structures.add(TGStructureFeatures.LICH_PRISON_STRUCTURE);
        return structures;
    }

    private List<String> getMobBlacklist() {
        Set<ResourceKey<Biome>> biomes = new HashSet<>();
        biomes.add(Biomes.MUSHROOM_FIELDS);
        biomes.add(Biomes.FLOWER_FOREST);


        return new ArrayList<>(biomes.stream().map(value -> value.location().toString()).toList());

    }


}
