package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.config.StructureConfigEntry;
import com.finallion.graveyard.init.structureKeys.TGStructureSetKeys;
import net.minecraft.data.worldgen.StructureSets;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

public class TGStructureSets {

    public static void init() {

        StructureSets.register(TGStructureSetKeys.HAUNTED_HOUSES, TGConfiguredStructureFeatures.HAUNTED_HOUSE_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        getSpacing("haunted_house"),
                        getSeparation("haunted_house"),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(getSalt("haunted_house"))));

        StructureSets.register(TGStructureSetKeys.LARGE_GRAVEYARDS, TGConfiguredStructureFeatures.LARGE_GRAVEYARD_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        getSpacing("large_graveyard"),
                        getSeparation("large_graveyard"),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(getSalt("large_graveyard"))));

        StructureSets.register(TGStructureSetKeys.MEDIUM_GRAVEYARDS, TGConfiguredStructureFeatures.MEDIUM_GRAVEYARD_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        getSpacing("medium_graveyard"),
                        getSeparation("medium_graveyard"),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(getSalt("medium_graveyard"))));

        StructureSets.register(TGStructureSetKeys.SMALL_GRAVES, TGConfiguredStructureFeatures.SMALL_GRAVE_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        getSpacing("small_grave"),
                        getSeparation("small_grave"),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(getSalt("small_grave"))));

        StructureSets.register(TGStructureSetKeys.SMALL_DESERT_GRAVES, TGConfiguredStructureFeatures.SMALL_DESERT_GRAVE_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        getSpacing("small_desert_grave"),
                        getSeparation("small_desert_grave"),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(getSalt("small_desert_grave"))));

        StructureSets.register(TGStructureSetKeys.SMALL_SAVANNA_GRAVES, TGConfiguredStructureFeatures.SMALL_SAVANNA_GRAVE_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        getSpacing("small_savanna_grave"),
                        getSeparation("small_savanna_grave"),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(getSalt("small_savanna_grave"))));

        StructureSets.register(TGStructureSetKeys.SMALL_MOUNTAIN_GRAVES, TGConfiguredStructureFeatures.SMALL_MOUNTAIN_GRAVE_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        getSpacing("small_mountain_grave"),
                        getSeparation("small_mountain_grave"),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(getSalt("small_mountain_grave"))));

        StructureSets.register(TGStructureSetKeys.SMALL_GRAVEYARDS, TGConfiguredStructureFeatures.SMALL_GRAVEYARD_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        getSpacing("small_graveyard"),
                        getSeparation("small_graveyard"),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(getSalt("small_graveyard"))));

        StructureSets.register(TGStructureSetKeys.SMALL_DESERT_GRAVEYARDS, TGConfiguredStructureFeatures.SMALL_DESERT_GRAVEYARD_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        getSpacing("small_desert_graveyard"),
                        getSeparation("small_desert_graveyard"),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(getSalt("small_desert_graveyard"))));

        StructureSets.register(TGStructureSetKeys.MUSHROOM_GRAVES, TGConfiguredStructureFeatures.MUSHROOM_GRAVE_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        getSpacing("mushroom_grave"),
                        getSeparation("mushroom_grave"),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(getSalt("mushroom_grave"))));

        StructureSets.register(TGStructureSetKeys.MEMORIAL_TREES, TGConfiguredStructureFeatures.MEMORIAL_TREE_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        getSpacing("memorial_tree"),
                        getSeparation("memorial_tree"),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(getSalt("memorial_tree"))));

        StructureSets.register(TGStructureSetKeys.ALTARS, TGConfiguredStructureFeatures.ALTAR_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        getSpacing("altar"),
                        getSeparation("altar"),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(getSalt("altar"))));

        StructureSets.register(TGStructureSetKeys.GIANT_MUSHROOMS, TGConfiguredStructureFeatures.GIANT_MUSHROOM_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        getSpacing("giant_mushroom"),
                        getSeparation("giant_mushroom"),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(getSalt("giant_mushroom"))));

        StructureSets.register(TGStructureSetKeys.CRYPTS, TGConfiguredStructureFeatures.CRYPT_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        getSpacing("crypt"),
                        getSeparation("crypt"),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(getSalt("crypt"))));

    }


    // TODO move to StructureConfigEntry, this is just a very dirty, very quick way to make 1.19 work
    private static long getSalt(String name) {
        return switch (name) {
            case "haunted_house" -> GraveyardConfig.COMMON.saltHauntedHouse.get();
            case "large_graveyard" -> GraveyardConfig.COMMON.saltLargeGraveyard.get();
            case "medium_graveyard" -> GraveyardConfig.COMMON.saltMediumGraveyard.get();
            case "small_graveyard" -> GraveyardConfig.COMMON.saltSmallGraveyard.get();
            case "small_desert_graveyard" -> GraveyardConfig.COMMON.saltSmallDesertGraveyard.get();
            case "small_grave" -> GraveyardConfig.COMMON.saltSmallGrave.get();
            case "small_mountain_grave" -> GraveyardConfig.COMMON.saltSmallMountainGrave.get();
            case "small_savanna_grave" -> GraveyardConfig.COMMON.saltSmallSavannaGrave.get();
            case "small_desert_grave" -> GraveyardConfig.COMMON.saltSmallDesertGrave.get();
            case "mushroom_grave" -> GraveyardConfig.COMMON.saltMushroomGrave.get();
            case "memorial_tree" -> GraveyardConfig.COMMON.saltMemorialTree.get();
            case "crypt" -> GraveyardConfig.COMMON.saltCrypt.get();
            case "altar" -> GraveyardConfig.COMMON.saltAltar.get();
            case "giant_mushroom" -> GraveyardConfig.COMMON.saltGiantMushroom.get();
            default -> 0;
        };
    }

    private static int getSpacing(String name) {
        return switch (name) {
            case "haunted_house" -> GraveyardConfig.COMMON.spacingHauntedHouse.get();
            case "large_graveyard" -> GraveyardConfig.COMMON.spacingLargeGraveyard.get();
            case "medium_graveyard" -> GraveyardConfig.COMMON.spacingMediumGraveyard.get();
            case "small_graveyard" -> GraveyardConfig.COMMON.spacingSmallGraveyard.get();
            case "small_desert_graveyard" -> GraveyardConfig.COMMON.spacingSmallDesertGraveyard.get();
            case "small_grave" -> GraveyardConfig.COMMON.spacingSmallGrave.get();
            case "small_mountain_grave" -> GraveyardConfig.COMMON.spacingSmallMountainGrave.get();
            case "small_savanna_grave" -> GraveyardConfig.COMMON.spacingSmallSavannaGrave.get();
            case "small_desert_grave" -> GraveyardConfig.COMMON.spacingSmallDesertGrave.get();
            case "mushroom_grave" -> GraveyardConfig.COMMON.spacingMushroomGrave.get();
            case "memorial_tree" -> GraveyardConfig.COMMON.spacingMemorialTree.get();
            case "crypt" -> GraveyardConfig.COMMON.spacingCrypt.get();
            case "altar" -> GraveyardConfig.COMMON.spacingAltar.get();
            case "giant_mushroom" -> GraveyardConfig.COMMON.spacingGiantMushroom.get();
            default -> 0;
        };
    }

    private static int getSeparation(String name) {
        return switch (name) {
            case "haunted_house" -> GraveyardConfig.COMMON.separationHauntedHouse.get();
            case "large_graveyard" -> GraveyardConfig.COMMON.separationLargeGraveyard.get();
            case "medium_graveyard" -> GraveyardConfig.COMMON.separationMediumGraveyard.get();
            case "small_graveyard" -> GraveyardConfig.COMMON.separationSmallGraveyard.get();
            case "small_desert_graveyard" -> GraveyardConfig.COMMON.separationSmallDesertGraveyard.get();
            case "small_grave" -> GraveyardConfig.COMMON.separationSmallGrave.get();
            case "small_mountain_grave" -> GraveyardConfig.COMMON.separationSmallMountainGrave.get();
            case "small_savanna_grave" -> GraveyardConfig.COMMON.separationSmallSavannaGrave.get();
            case "small_desert_grave" -> GraveyardConfig.COMMON.separationSmallDesertGrave.get();
            case "mushroom_grave" -> GraveyardConfig.COMMON.separationMushroomGrave.get();
            case "memorial_tree" -> GraveyardConfig.COMMON.separationMemorialTree.get();
            case "crypt" -> GraveyardConfig.COMMON.separationCrypt.get();
            case "altar" -> GraveyardConfig.COMMON.separationAltar.get();
            case "giant_mushroom" -> GraveyardConfig.COMMON.separationGiantMushroom.get();
            default -> 0;
        };
    }

}
