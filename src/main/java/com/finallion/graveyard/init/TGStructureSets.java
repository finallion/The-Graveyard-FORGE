package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.config.StructureConfigEntry;
import com.finallion.graveyard.init.structureKeys.TGStructureSetKeys;
import com.finallion.graveyard.world.structures.AbstractGraveyardStructure;
import net.minecraft.data.worldgen.StructureSets;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

public class TGStructureSets {

    public static void init() {

        StructureSets.m_211131_(TGStructureSetKeys.HAUNTED_HOUSES, TGConfiguredStructureFeatures.HAUNTED_HOUSE_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        GraveyardConfig.COMMON.structureConfigEntries.get("haunted_house").spacing.get(),
                        GraveyardConfig.COMMON.structureConfigEntries.get("haunted_house").separation.get(),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(GraveyardConfig.COMMON.structureConfigEntries.get("haunted_house").salt.get())));

        StructureSets.m_211131_(TGStructureSetKeys.LARGE_GRAVEYARDS, TGConfiguredStructureFeatures.LARGE_GRAVEYARD_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        GraveyardConfig.COMMON.structureConfigEntries.get("large_graveyard").spacing.get(),
                        GraveyardConfig.COMMON.structureConfigEntries.get("large_graveyard").separation.get(),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(GraveyardConfig.COMMON.structureConfigEntries.get("large_graveyard").salt.get())));

        StructureSets.m_211131_(TGStructureSetKeys.MEDIUM_GRAVEYARDS, TGConfiguredStructureFeatures.MEDIUM_GRAVEYARD_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        GraveyardConfig.COMMON.structureConfigEntries.get("medium_graveyard").spacing.get(),
                        GraveyardConfig.COMMON.structureConfigEntries.get("medium_graveyard").separation.get(),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(GraveyardConfig.COMMON.structureConfigEntries.get("medium_graveyard").salt.get())));

        StructureSets.m_211131_(TGStructureSetKeys.SMALL_GRAVES, TGConfiguredStructureFeatures.SMALL_GRAVE_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        GraveyardConfig.COMMON.structureConfigEntries.get("small_grave").spacing.get(),
                        GraveyardConfig.COMMON.structureConfigEntries.get("small_grave").separation.get(),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(GraveyardConfig.COMMON.structureConfigEntries.get("small_grave").salt.get())));

        StructureSets.m_211131_(TGStructureSetKeys.SMALL_DESERT_GRAVES, TGConfiguredStructureFeatures.SMALL_DESERT_GRAVE_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        GraveyardConfig.COMMON.structureConfigEntries.get("small_desert_grave").spacing.get(),
                        GraveyardConfig.COMMON.structureConfigEntries.get("small_desert_grave").separation.get(),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(GraveyardConfig.COMMON.structureConfigEntries.get("small_desert_grave").salt.get())));

        StructureSets.m_211131_(TGStructureSetKeys.SMALL_SAVANNA_GRAVES, TGConfiguredStructureFeatures.SMALL_SAVANNA_GRAVE_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        GraveyardConfig.COMMON.structureConfigEntries.get("small_savanna_grave").spacing.get(),
                        GraveyardConfig.COMMON.structureConfigEntries.get("small_savanna_grave").separation.get(),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(GraveyardConfig.COMMON.structureConfigEntries.get("small_savanna_grave").salt.get())));

        StructureSets.m_211131_(TGStructureSetKeys.SMALL_MOUNTAIN_GRAVES, TGConfiguredStructureFeatures.SMALL_MOUNTAIN_GRAVE_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        GraveyardConfig.COMMON.structureConfigEntries.get("small_mountain_grave").spacing.get(),
                        GraveyardConfig.COMMON.structureConfigEntries.get("small_mountain_grave").separation.get(),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(GraveyardConfig.COMMON.structureConfigEntries.get("small_mountain_grave").salt.get())));

        StructureSets.m_211131_(TGStructureSetKeys.SMALL_GRAVEYARDS, TGConfiguredStructureFeatures.SMALL_GRAVEYARD_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        GraveyardConfig.COMMON.structureConfigEntries.get("small_graveyard").spacing.get(),
                        GraveyardConfig.COMMON.structureConfigEntries.get("small_graveyard").separation.get(),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(GraveyardConfig.COMMON.structureConfigEntries.get("small_graveyard").salt.get())));

        StructureSets.m_211131_(TGStructureSetKeys.SMALL_DESERT_GRAVEYARDS, TGConfiguredStructureFeatures.SMALL_DESERT_GRAVEYARD_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        GraveyardConfig.COMMON.structureConfigEntries.get("small_desert_graveyard").spacing.get(),
                        GraveyardConfig.COMMON.structureConfigEntries.get("small_desert_graveyard").separation.get(),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(GraveyardConfig.COMMON.structureConfigEntries.get("small_desert_graveyard").salt.get())));

        StructureSets.m_211131_(TGStructureSetKeys.MUSHROOM_GRAVES, TGConfiguredStructureFeatures.MUSHROOM_GRAVE_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        GraveyardConfig.COMMON.structureConfigEntries.get("mushroom_grave").spacing.get(),
                        GraveyardConfig.COMMON.structureConfigEntries.get("mushroom_grave").separation.get(),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(GraveyardConfig.COMMON.structureConfigEntries.get("mushroom_grave").salt.get())));

        StructureSets.m_211131_(TGStructureSetKeys.MEMORIAL_TREES, TGConfiguredStructureFeatures.MEMORIAL_TREE_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        GraveyardConfig.COMMON.structureConfigEntries.get("memorial_tree").spacing.get(),
                        GraveyardConfig.COMMON.structureConfigEntries.get("memorial_tree").separation.get(),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(GraveyardConfig.COMMON.structureConfigEntries.get("memorial_tree").salt.get())));

        StructureSets.m_211131_(TGStructureSetKeys.ALTARS, TGConfiguredStructureFeatures.ALTAR_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        GraveyardConfig.COMMON.structureConfigEntries.get("altar").spacing.get(),
                        GraveyardConfig.COMMON.structureConfigEntries.get("altar").separation.get(),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(GraveyardConfig.COMMON.structureConfigEntries.get("altar").salt.get())));

        StructureSets.m_211131_(TGStructureSetKeys.GIANT_MUSHROOMS, TGConfiguredStructureFeatures.GIANT_MUSHROOM_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        GraveyardConfig.COMMON.structureConfigEntries.get("giant_mushroom").spacing.get(),
                        GraveyardConfig.COMMON.structureConfigEntries.get("giant_mushroom").separation.get(),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(GraveyardConfig.COMMON.structureConfigEntries.get("giant_mushroom").salt.get())));

        StructureSets.m_211131_(TGStructureSetKeys.CRYPTS, TGConfiguredStructureFeatures.CRYPT_STRUCTURE_CONFIG,
                new RandomSpreadStructurePlacement(
                        GraveyardConfig.COMMON.structureConfigEntries.get("crypt").spacing.get(),
                        GraveyardConfig.COMMON.structureConfigEntries.get("crypt").separation.get(),
                        RandomSpreadType.LINEAR,
                        Math.toIntExact(GraveyardConfig.COMMON.structureConfigEntries.get("crypt").salt.get())));

    }

}
