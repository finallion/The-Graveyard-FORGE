package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.structures.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TGStructures {

    public static final List<Structure> MOD_STRUCTURES = new ArrayList<>();

    public static final DeferredRegister<Structure<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, TheGraveyard.MOD_ID);

    public static RegistryObject<Structure<NoFeatureConfig>> SMALL_WALLED_GRAVEYARD = DEFERRED_REGISTRY_STRUCTURE.register("small_walled_graveyard", () -> (new SmallWalledGraveyard(NoFeatureConfig.CODEC)));
    public static RegistryObject<Structure<NoFeatureConfig>> SMALL_WALLED_GRAVEYARD_SAVANNA = DEFERRED_REGISTRY_STRUCTURE.register("small_walled_graveyard_savanna", () -> (new SmallWalledGraveyardSavanna(NoFeatureConfig.CODEC)));
    public static RegistryObject<Structure<NoFeatureConfig>> SMALL_WALLED_GRAVEYARD_DESERT = DEFERRED_REGISTRY_STRUCTURE.register("small_walled_graveyard_desert", () -> (new SmallWalledGraveyardDesert(NoFeatureConfig.CODEC)));
    public static RegistryObject<Structure<NoFeatureConfig>> SMALL_GRAVE = DEFERRED_REGISTRY_STRUCTURE.register("small_grave", () -> (new SmallGrave(NoFeatureConfig.CODEC)));
    public static RegistryObject<Structure<NoFeatureConfig>> MUSHROOM_GRAVE = DEFERRED_REGISTRY_STRUCTURE.register("mushroom_grave", () -> (new MushroomGrave(NoFeatureConfig.CODEC)));
    public static RegistryObject<Structure<NoFeatureConfig>> LARGE_BIRCH_TREE = DEFERRED_REGISTRY_STRUCTURE.register("large_birch_tree", () -> (new LargeBirchTree(NoFeatureConfig.CODEC)));
    public static RegistryObject<Structure<NoFeatureConfig>> MEDIUM_WALLED_GRAVEYARD = DEFERRED_REGISTRY_STRUCTURE.register("medium_walled_graveyard", () -> (new MediumWalledGraveyard(NoFeatureConfig.CODEC)));
    public static RegistryObject<Structure<NoFeatureConfig>> LARGE_WALLED_GRAVEYARD = DEFERRED_REGISTRY_STRUCTURE.register("large_walled_graveyard", () -> (new LargeWalledGraveyard(NoFeatureConfig.CODEC)));
    public static RegistryObject<Structure<NoFeatureConfig>> HAUNTED_HOUSE = DEFERRED_REGISTRY_STRUCTURE.register("haunted_forest", () -> (new HauntedHouse(NoFeatureConfig.CODEC)));

    public static void register() {
        setupMapSpacingAndLand(SMALL_WALLED_GRAVEYARD.get(), TheGraveyard.CONFIG.small_walled_graveyard_separation.get(), TheGraveyard.CONFIG.small_walled_graveyard_spacing.get(), TheGraveyard.CONFIG.small_walled_graveyard_salt.get(), true);
        setupMapSpacingAndLand(SMALL_WALLED_GRAVEYARD_SAVANNA.get(), TheGraveyard.CONFIG.small_walled_graveyard_savanna_separation.get(), TheGraveyard.CONFIG.small_walled_graveyard_savanna_spacing.get(), TheGraveyard.CONFIG.small_walled_graveyard_savanna_salt.get(), true);
        setupMapSpacingAndLand(SMALL_WALLED_GRAVEYARD_DESERT.get(), TheGraveyard.CONFIG.small_walled_graveyard_desert_separation.get(), TheGraveyard.CONFIG.small_walled_graveyard_desert_spacing.get(), TheGraveyard.CONFIG.small_walled_graveyard_desert_salt.get(), true);
        setupMapSpacingAndLand(SMALL_GRAVE.get(), TheGraveyard.CONFIG.small_grave_separation.get(), TheGraveyard.CONFIG.small_grave_spacing.get(), TheGraveyard.CONFIG.small_grave_salt.get(), true);
        setupMapSpacingAndLand(MUSHROOM_GRAVE.get(), TheGraveyard.CONFIG.mushroom_grave_separation.get(), TheGraveyard.CONFIG.mushroom_grave_spacing.get(), TheGraveyard.CONFIG.mushroom_grave_salt.get(), true);
        setupMapSpacingAndLand(LARGE_BIRCH_TREE.get(), TheGraveyard.CONFIG.large_birch_tree_separation.get(), TheGraveyard.CONFIG.large_birch_tree_spacing.get(), TheGraveyard.CONFIG.large_birch_tree_salt.get(), true);
        setupMapSpacingAndLand(MEDIUM_WALLED_GRAVEYARD.get(), TheGraveyard.CONFIG.medium_walled_graveyard_separation.get(), TheGraveyard.CONFIG.medium_walled_graveyard_spacing.get(), TheGraveyard.CONFIG.medium_walled_graveyard_salt.get(), true);
        setupMapSpacingAndLand(LARGE_WALLED_GRAVEYARD.get(), TheGraveyard.CONFIG.large_walled_graveyard_separation.get(), TheGraveyard.CONFIG.large_walled_graveyard_spacing.get(), TheGraveyard.CONFIG.large_walled_graveyard_salt.get(), true);
        setupMapSpacingAndLand(HAUNTED_HOUSE.get(), TheGraveyard.CONFIG.haunted_house_separation.get(), TheGraveyard.CONFIG.haunted_house_spacing.get(), TheGraveyard.CONFIG.haunted_house_salt.get(), true);


    }


    public static <F extends Structure<?>> void setupMapSpacingAndLand(F structure, int separation, int spacing, int salt, boolean transformSurroundingLand) {
        MOD_STRUCTURES.add(structure);
        setupMapSpacingAndLand(structure, new StructureSeparationSettings(spacing, separation, salt), transformSurroundingLand);
    }

    public static <F extends Structure<?>> void setupMapSpacingAndLand(F structure, StructureSeparationSettings structureSeparationSettings, boolean transformSurroundingLand) {

        Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

        if (transformSurroundingLand) {
            Structure.NOISE_AFFECTING_FEATURES = ImmutableList.<Structure<?>>builder()
                            .addAll(Structure.NOISE_AFFECTING_FEATURES)
                            .add(structure)
                            .build();
        }


        DimensionStructuresSettings.DEFAULTS =
                ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                        .putAll(DimensionStructuresSettings.DEFAULTS)
                        .put(structure, structureSeparationSettings)
                        .build();

        WorldGenRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
            Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().structureSettings().structureConfig();


            if (structureMap instanceof ImmutableMap) {
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureSeparationSettings);
                settings.getValue().structureSettings().structureConfig = tempMap;
            } else {
                structureMap.put(structure, structureSeparationSettings);
            }
        });
    }


}
