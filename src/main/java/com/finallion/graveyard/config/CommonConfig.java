package com.finallion.graveyard.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;

import java.io.File;
import java.util.*;

public class CommonConfig {
    public final ForgeConfigSpec.BooleanValue canGenerateHauntedHouse;
    public final ForgeConfigSpec.BooleanValue canGenerateMediumGraveyard;
    public final ForgeConfigSpec.BooleanValue canGenerateLargeGraveyard;
    public final ForgeConfigSpec.BooleanValue canGenerateSmallGrave;
    public final ForgeConfigSpec.BooleanValue canGenerateSmallDesertGrave;
    public final ForgeConfigSpec.BooleanValue canGenerateSmallSavannaGrave;
    public final ForgeConfigSpec.BooleanValue canGenerateSmallMountainGrave;
    public final ForgeConfigSpec.BooleanValue canGenerateSmallGraveyard;
    public final ForgeConfigSpec.BooleanValue canGenerateSmallDesertGraveyard;
    public final ForgeConfigSpec.BooleanValue canGenerateMushroomGrave;
    public final ForgeConfigSpec.BooleanValue canGenerateMemorialTree;
    public final ForgeConfigSpec.BooleanValue canGenerateAltar;
    public final ForgeConfigSpec.BooleanValue canGenerateCrypt;
    public final ForgeConfigSpec.BooleanValue canGenerateGiantMushroom;
    public final ForgeConfigSpec.BooleanValue canGenerateLichPrison;
    public final ForgeConfigSpec.BooleanValue canGenerateRuins;
    public final ForgeConfigSpec.BooleanValue canGenerateDeadTree;

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
    public final ForgeConfigSpec.BooleanValue acolyteCanBurnInSunlight;
    public final ForgeConfigSpec.BooleanValue acolyteCanBeWithered;

    public final ForgeConfigSpec.BooleanValue enableWraith;
    public final ForgeConfigSpec.BooleanValue wraithCanBurnInSunlight;
    public final ForgeConfigSpec.BooleanValue wraithCanBeWithered;

    public final ForgeConfigSpec.BooleanValue enableCorruptedPillager;
    public final ForgeConfigSpec.BooleanValue corruptedPillagerCanBurnInSunlight;
    public final ForgeConfigSpec.BooleanValue corruptedPillagerCanBeWithered;

    public final ForgeConfigSpec.BooleanValue enableCorruptedVindicator;
    public final ForgeConfigSpec.BooleanValue corruptedVindicatorCanBurnInSunlight;
    public final ForgeConfigSpec.BooleanValue corruptedVindicatorCanBeWithered;

    public final ForgeConfigSpec.BooleanValue enableHorde;
    public final ForgeConfigSpec.IntValue mobSpawnAttempts;
    public final ForgeConfigSpec.IntValue ticksUntilSpawnHorde;
    public final ForgeConfigSpec.IntValue additionalRandomizedTicks;

    public final ForgeConfigSpec.BooleanValue enableMossParticle;
    public final ForgeConfigSpec.IntValue particleFrequency;

    public final ForgeConfigSpec.BooleanValue urnHasDoubleInventory;
    public final ForgeConfigSpec.BooleanValue disableWitherSkeletonSpawner;
    public final ForgeConfigSpec.BooleanValue enableBossMusic;

    public final ForgeConfigSpec.DoubleValue healthInCastingPhase;
    public final ForgeConfigSpec.DoubleValue healthInHuntingPhase;
    public final ForgeConfigSpec.DoubleValue damageCastingPhase;
    public final ForgeConfigSpec.DoubleValue damageHuntingPhaseAddition;
    public final ForgeConfigSpec.DoubleValue armor;
    public final ForgeConfigSpec.DoubleValue armorToughness;
    public final ForgeConfigSpec.DoubleValue speedInHuntPhase;
    public final ForgeConfigSpec.IntValue durationHuntingPhase;
    public final ForgeConfigSpec.IntValue durationFallingCorpseSpell;
    public final ForgeConfigSpec.IntValue durationHealingSpell;
    public final ForgeConfigSpec.IntValue durationLevitationSpell;
    public final ForgeConfigSpec.IntValue maxAmountSkullsInShootSkullSpell;
    public final ForgeConfigSpec.IntValue maxSummonedMobs;
    public final ForgeConfigSpec.IntValue maxGroupSizeSummonedMobs;
    public final ForgeConfigSpec.IntValue ghoulSpawnTimerInFight;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> isBloodCollectableEntity;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> isBossSummonableItem;
    public final ForgeConfigSpec.BooleanValue summoningNeedsStaffFragments;
    public final ForgeConfigSpec.BooleanValue isMultiphaseFight;
    public final ForgeConfigSpec.BooleanValue isInvulnerableDuringSpells;
    public final ForgeConfigSpec.IntValue cooldownCorpseSpell;
    public final ForgeConfigSpec.IntValue cooldownTeleportPlayerAndHeal;
    public final ForgeConfigSpec.IntValue cooldownLevitationSpell;
    public final ForgeConfigSpec.IntValue playerTeleportYOffset;


    public CommonConfig(ForgeConfigSpec.Builder builder) {
        builder.comment(" Welcome to The Graveyard Config!" +
                        "Welcome to The Graveyard Config! \n " +
                        "\n" +
                        "Structures: \n " +
                        "Enable or disable structure spawns. \n " +
                        "\n" +
                        "Mobs: \n" +
                        "Configure spawning weight and group size of the spawn (ghoul, revenant, reaper, nightmare, skeleton creeper). \n " +
                        "Configure if mobs burn in sunlight and/or if mobs are affected by the wither effect. \n " +
                        "\n" +
                        "Additional: \n" +
                        "Configure graveyard fog particles rising from moss and set the chance of spawning them (higher numbers = lower chance of spawning). \n " +
                        "Configure if hordes of graveyard mobs can spawn and set their size and frequency. \n " +
                        "Configure if urns have a double chest inventory. \n " +
                        "Configure if the wither skeleton spawner in the large graveyard will be replaced by a skeleton spawner. \n ",
                "Configure if boss music plays during the fight against the Corrupted Champion. \n ",
                "\n",
                "Music: \n",
                "Incarnated Evil by Rotch Gwylt (Official The Graveyard Soundtrack). \n "
        );

        builder.push("The Graveyard - Structures Config");

        this.canGenerateHauntedHouse = builder.define("hauntedHouse.generate", true);
        this.canGenerateLargeGraveyard = builder.define("largeGraveyard.generate", true);
        this.canGenerateMediumGraveyard = builder.define("mediumGraveyard.generate", true);
        this.canGenerateMemorialTree = builder.define("memorialTree.generate", true);
        this.canGenerateMushroomGrave = builder.define("mushroomGrave.generate", true);
        this.canGenerateSmallDesertGrave = builder.define("smallDesertGrave.generate", true);
        this.canGenerateSmallDesertGraveyard = builder.define("smallDesertGraveyard.generate", true);
        this.canGenerateSmallGrave = builder.define("smallGrave.generate", true);
        this.canGenerateSmallSavannaGrave = builder.define("smallSavannaGrave.generate", true);
        this.canGenerateSmallGraveyard = builder.define("smallGraveyard.generate", true);
        this.canGenerateSmallMountainGrave = builder.define("smallMountainGraveyard.generate", true);
        this.canGenerateCrypt = builder.define("crypt.generate", true);
        this.canGenerateAltar = builder.define("altar.generate", true);
        this.canGenerateGiantMushroom = builder.define("giantMushroom.generate", true);
        this.canGenerateLichPrison = builder.define("lichPrison.generate", true);
        this.canGenerateRuins = builder.define("ruins.generate", true);
        this.canGenerateDeadTree = builder.define("deadTree.generate", true);

        builder.pop();

        builder.push("The Graveyard - Mob Spawning Config");
        this.enableGhoul = builder.define("ghoul.enabled", true);
        this.weightGhoul = builder.defineInRange("ghoul.weight", 25, 0, 100);
        this.minGroupSizeGhoul = builder.defineInRange("ghoul.minGroupSizeGhoul", 2, 1, 100);
        this.maxGroupSizeGhoul = builder.defineInRange("ghoul.maxGroupSizeGhoul", 5, 1, 100);
        this.ghoulCanBurnInSunlight = builder.define("ghoul.canBurnInSunlight", true);
        this.ghoulCanBeWithered = builder.define("ghoul.canBeWithered", true);
        this.whitelistGhoul = builder.defineList("ghoul.whitelist", getGhoulWhitelist(), o -> o instanceof String);
        this.blacklistGhoul = builder.defineList("ghoul.blacklist", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistGhoul = builder.defineList("ghoul.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableRevenant = builder.define("revenant.enabled", true);
        this.weightRevenant = builder.defineInRange("revenant.weight", 25, 0, 100);
        this.minGroupSizeRevenant = builder.defineInRange("revenant.minGroupSizeRevenant", 5, 1, 100);
        this.maxGroupSizeRevenant = builder.defineInRange("revenant.maxGroupSizeRevenant", 8, 1, 100);
        this.revenantCanBurnInSunlight = builder.define("revenant.canBurnInSunlight", true);
        this.revenantCanBeWithered = builder.define("revenant.canBeWithered", true);
        this.whitelistRevenant = builder.defineList("revenant.whitelist", getRevenantWhitelist(), o -> o instanceof String);
        this.blacklistRevenant = builder.defineList("revenant.blacklist", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistRevenant = builder.defineList("revenant.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableReaper = builder.define("reaper.enabled", true);
        this.weightReaper = builder.defineInRange("reaper.weight", 10, 0, 100);
        this.minGroupSizeReaper = builder.defineInRange("reaper.minGroupSizeReaper", 2, 1, 100);
        this.maxGroupSizeReaper = builder.defineInRange("reaper.maxGroupSizeGhoul", 3, 1, 100);
        this.reaperCanBurnInSunlight = builder.define("reaper.canBurnInSunlight", true);
        this.reaperCanBeWithered = builder.define("reaper.canBeWithered", true);

        this.enableNightmare = builder.define("nightmare.enabled", true);
        this.weightNightmare = builder.defineInRange("nightmare.weight", 10, 0, 100);
        this.minGroupSizeNightmare = builder.defineInRange("nightmare.minGroupSizeNightmare", 1, 1, 100);
        this.maxGroupSizeNightmare = builder.defineInRange("nightmare.maxGroupSizeNightmare", 1, 1, 100);
        this.nightmareCanBurnInSunlight = builder.define("nightmare.canBurnInSunlight", false);
        this.nightmareCanBeWithered = builder.define("nightmare.canBeWithered", false);
        this.whitelistNightmare = builder.defineList("nightmare.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistNightmare = builder.defineList("nightmare.blacklist", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistNightmare = builder.defineList("nightmare.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableSkeletonCreeper = builder.define("skeleton_creeper.enabled", true);
        this.weightSkeletonCreeper = builder.defineInRange("skeleton_creeper.weight", 25, 0, 100);
        this.minGroupSizeSkeletonCreeper = builder.defineInRange("skeleton_creeper.minGroupSizeSkeletonCreeper", 1, 1, 100);
        this.maxGroupSizeSkeletonCreeper = builder.defineInRange("skeleton_creeper.maxGroupSizeSkeletonCreeper", 4, 1, 100);
        this.skeletonCreeperCanBurnInSunlight = builder.define("skeleton_creeper.canBurnInSunlight", true);
        this.skeletonCreeperCanBeWithered = builder.define("skeleton_creeper.canBeWithered", true);
        this.whitelistSkeletonCreeper = builder.defineList("skeleton_creeper.whitelist", getAllOverworldBiomeCategories(), o -> o instanceof String);
        this.blacklistSkeletonCreeper = builder.defineList("skeleton_creeper.blacklist", getMobBlacklist(), o -> o instanceof String);
        this.modWhitelistSkeletonCreeper = builder.defineList("skeleton_creeper.modWhitelist", Arrays.asList("#minecraft", "#graveyard_biomes"), o -> o instanceof String);

        this.enableAcolyte = builder.define("acolyte.enabled", false);
        this.acolyteCanBurnInSunlight = builder.define("acolyte.canBurnInSunlight", false);
        this.acolyteCanBeWithered = builder.define("acolyte.canBeWithered", true);

        this.enableWraith = builder.define("wraith.enabled", false);
        this.wraithCanBurnInSunlight = builder.define("wraith.canBurnInSunlight", true);
        this.wraithCanBeWithered = builder.define("wraith.canBeWithered", false);

        this.enableCorruptedPillager = builder.define("corruptedPillager.enabled", false);
        this.corruptedPillagerCanBurnInSunlight = builder.define("corruptedPillager.canBurnInSunlight", true);
        this.corruptedPillagerCanBeWithered = builder.define("corruptedPillager.canBeWithered", true);

        this.enableCorruptedVindicator = builder.define("corruptedVindicator.enabled", false);
        this.corruptedVindicatorCanBurnInSunlight = builder.define("corruptedVindicator.canBurnInSunlight", true);
        this.corruptedVindicatorCanBeWithered = builder.define("corruptedVindicator.canBeWithered", true);

        builder.pop();

        builder.push("The Graveyard - Corrupted Champion Config");
        this.healthInCastingPhase = builder.defineInRange("corruptedChampion.healthInCastingPhase", 400.0D, 1.0D, 1024.0D);
        this.healthInHuntingPhase = builder.defineInRange("corruptedChampion.healthInHuntingPhase", 200.0D, 1.0D, 1024.0D);
        this.damageCastingPhase = builder.defineInRange("corruptedChampion.damageCastingPhase", 30.0D, 1.0D, 2048.0D);
        this.damageHuntingPhaseAddition = builder.defineInRange("corruptedChampion.damageHuntingPhaseAddition", 40.0D, 1.0D, 2048.0D);
        this.armor = builder.defineInRange("corruptedChampion.armor", 18.0D, 0, 30.0D);
        this.armorToughness = builder.defineInRange("corruptedChampion.armorToughness", 14.0D, 0, 20.0D);
        this.speedInHuntPhase = builder.defineInRange("corruptedChampion.speedInHuntPhase", 0.15D, 0, 1024.0D);
        this.durationHuntingPhase = builder.defineInRange("corruptedChampion.durationHuntingPhase", 800, 0, 100000);
        this.durationFallingCorpseSpell = builder.defineInRange("corruptedChampion.durationFallingCorpseSpell", 400, 0, 100000);
        this.durationHealingSpell = builder.defineInRange("corruptedChampion.durationHealingSpell", 700, 0, 100000);
        this.durationLevitationSpell = builder.defineInRange("corruptedChampion.durationLevitationSpell", 150, 0, 100000);
        this.maxAmountSkullsInShootSkullSpell = builder.defineInRange("corruptedChampion.maxAmountSkullsInShootSkullSpell", 5, 1, 100);
        this.maxSummonedMobs = builder.defineInRange("corruptedChampion.maxSummonedMobs", 30, 0, 100);
        this.maxGroupSizeSummonedMobs = builder.defineInRange("corruptedChampion.maxGroupSizeSummonedMobs", 5, 1, 100);
        this.ghoulSpawnTimerInFight = builder.defineInRange("corruptedChampion.ghoulSpawnTimerInFight", 6000, 0, 100000);
        this.isBloodCollectableEntity = builder.defineList("corruptedChampion.isBloodCollectableEntity", List.of("entity.minecraft.villager"), o -> o instanceof String);
        this.isBossSummonableItem = builder.defineList("corruptedChampion.isBossSummonableItem", List.of("item.minecraft.debug_stick"), o -> o instanceof String);
        this.summoningNeedsStaffFragments = builder.define("corruptedChampion.summoningNeedsStaffFragments", true);
        this.isMultiphaseFight = builder.define("corruptedChampion.isMultiphaseFight", true);
        this.isInvulnerableDuringSpells = builder.define("corruptedChampion.isInvulnerableDuringSpells", true);
        this.cooldownCorpseSpell = builder.defineInRange("corruptedChampion.cooldownCorpseSpell", 400, 1, 100000);
        this.cooldownTeleportPlayerAndHeal = builder.defineInRange("corruptedChampion.cooldownTeleportPlayerAndHeal", 600, 1, 100000);
        this.cooldownLevitationSpell = builder.defineInRange("corruptedChampion.cooldownLevitationSpell", 400, 1, 100000);
        this.playerTeleportYOffset = builder.defineInRange("corruptedChampion.playerTeleportYOffset", -15, -64, 100000);
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
        this.enableBossMusic = builder.define("bossMusic.enableBossMusic", true);
        this.disableWitherSkeletonSpawner = builder.define("spawner.disableWitherSkeletonSpawner", false);
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

    private List<String> getGhoulWhitelist() {
        Set<ResourceKey<Biome>> biomes = new HashSet<>();
        biomes.add(Biomes.TAIGA);
        biomes.add(Biomes.OLD_GROWTH_PINE_TAIGA);
        biomes.add(Biomes.OLD_GROWTH_SPRUCE_TAIGA);
        biomes.add(Biomes.JUNGLE);
        biomes.add(Biomes.BAMBOO_JUNGLE);
        biomes.add(Biomes.FOREST);
        biomes.add(Biomes.DARK_FOREST);
        biomes.add(Biomes.SWAMP);

        return new ArrayList<>(biomes.stream().map(value -> value.location().toString()).toList());
    }

    private List<String> getRevenantWhitelist() {
        Set<ResourceKey<Biome>> biomes = new HashSet<>();
        biomes.add(Biomes.SNOWY_PLAINS);
        biomes.add(Biomes.SNOWY_SLOPES);
        biomes.add(Biomes.SNOWY_TAIGA);
        biomes.add(Biomes.ICE_SPIKES);
        biomes.add(Biomes.FROZEN_PEAKS);
        biomes.add(Biomes.JAGGED_PEAKS);
        biomes.add(Biomes.WINDSWEPT_GRAVELLY_HILLS);

        return new ArrayList<>(biomes.stream().map(value -> value.location().toString()).toList());
    }

    private List<String> getMobBlacklist() {
        Set<ResourceKey<Biome>> biomes = new HashSet<>();
        biomes.add(Biomes.MUSHROOM_FIELDS);
        biomes.add(Biomes.FLOWER_FOREST);


        return new ArrayList<>(biomes.stream().map(value -> value.location().toString()).toList());
    }

    /*
    Credit to AzureDoom and Tslat
     */
    public static void loadConfig(ForgeConfigSpec config, String path) {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave()
                .writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }
}
