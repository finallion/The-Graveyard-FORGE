package com.finallion.graveyard.config;

import com.finallion.graveyard.init.TGStructures;
import com.finallion.graveyard.world.structures.AbstractGraveyardStructure;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class CommonConfig {
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

    public final ForgeConfigSpec.BooleanValue enableHorde;
    public final ForgeConfigSpec.IntValue sizeHorde;
    public final ForgeConfigSpec.IntValue ticksUntilSpawnHorde;

    public final ForgeConfigSpec.BooleanValue enableMossParticle;
    public final ForgeConfigSpec.IntValue particleFrequency;

    public final ForgeConfigSpec.BooleanValue urnHasDoubleInventory;

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
        for (StructureFeature<?> structure : TGStructures.MOD_STRUCTURES) {
            AbstractGraveyardStructure graveyardStructure = (AbstractGraveyardStructure) structure;
            StructureConfigEntry structureConfig = graveyardStructure.getStructureConfigEntry();

            String name = ((AbstractGraveyardStructure) structure).getStructureName();

            structureConfig.canGenerate = builder.define(name + ".generate", true);
            structureConfig.spacing = builder.defineInRange(name + ".spacing", structureConfig.getSpacing(), 0, 200);
            structureConfig.spacing = builder.defineInRange(name + ".separation", structureConfig.getSeparation(), 0, 200);
            structureConfig.whitelist = builder.defineList(name + ".whitelist", structureConfig.getWhitelist(), o -> o instanceof String);
            structureConfig.blacklist = builder.defineList(name + ".blacklist", structureConfig.getBlacklist(), o -> o instanceof String);
            structureConfig.modWhitelist = builder.defineList(name + ".modWhitelist", structureConfig.getModWhitelist(), o -> o instanceof String);
            structureConfig.canSpawnGraveyardMobs = builder.define(name + ".canSpawnGraveyardMobs", structureConfig.getCanSpawnGraveyardMobs());
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
        builder.pop();

        builder.push("The Graveyard - Horde Config");
        this.enableHorde = builder.define("horde.generate", true);
        this.sizeHorde = builder.defineInRange("horde.size", 40, 0, 1000);
        this.ticksUntilSpawnHorde = builder.defineInRange("horde.ticksUntilSpawnHorde", 24000, 1, 1000000);
        builder.pop();

        builder.push("The Graveyard - Particle Config");
        this.enableMossParticle = builder.define("particle.generate", true);
        this.particleFrequency = builder.defineInRange("particle.frequency",50, 1, 500);
        builder.pop();

        builder.push("The Graveyard - Additional Config");
        this.urnHasDoubleInventory = builder.define("urn.urnHasDoubleInventory", true);
        builder.pop();
    }



    public static List<String> getAllBiomesForCategory(Biome.BiomeCategory... categories) {
        List<String> biomes = new ArrayList<>();

        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            for (Biome.BiomeCategory category : categories) {
                if (biome.getBiomeCategory() == category) {
                    biomes.add(Objects.requireNonNull(biome.getRegistryName()).toString());
                }
            }
        }

        return biomes;
    }

    private List<String> getAllOverworldBiomeCategories() {
        Set<String> biomeNames = new HashSet<>();
        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            if (biome.getBiomeCategory() == Biome.BiomeCategory.OCEAN || biome.getBiomeCategory() == Biome.BiomeCategory.RIVER || biome.getBiomeCategory() == Biome.BiomeCategory.NONE || biome.getBiomeCategory() == Biome.BiomeCategory.THEEND || biome.getBiomeCategory() == Biome.BiomeCategory.NETHER || biome.getBiomeCategory() == Biome.BiomeCategory.MUSHROOM) {
                continue;
            }
            biomeNames.add("#" + biome.getBiomeCategory().getName().toLowerCase(Locale.ROOT));
        }
        return new ArrayList<>(biomeNames);

    }
}
