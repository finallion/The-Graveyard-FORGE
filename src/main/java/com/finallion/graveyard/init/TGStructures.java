package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.world.structures.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TGStructures {
    public static final List<StructureFeature<?>> MOD_STRUCTURES = new ArrayList<>();

    public static final DeferredRegister<StructureFeature<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, TheGraveyard.MOD_ID);


    // structure features
    public static RegistryObject<StructureFeature<JigsawConfiguration>> SMALL_GRAVEYARD_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("small_graveyard_structure", () -> (new SmallGraveyardStructure(JigsawConfiguration.CODEC)));
    public static RegistryObject<StructureFeature<JigsawConfiguration>> SMALL_DESERT_GRAVEYARD_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("small_desert_graveyard_structure", () -> (new SmallDesertGraveyardStructure(JigsawConfiguration.CODEC)));
    public static RegistryObject<StructureFeature<JigsawConfiguration>> SMALL_GRAVE_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("small_grave_structure", () -> (new SmallGraveStructure(JigsawConfiguration.CODEC)));
    public static RegistryObject<StructureFeature<JigsawConfiguration>> SMALL_DESERT_GRAVE_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("small_desert_grave_structure", () -> (new SmallDesertGraveStructure(JigsawConfiguration.CODEC)));
    public static RegistryObject<StructureFeature<JigsawConfiguration>> SMALL_SAVANNA_GRAVE_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("small_savanna_grave_structure", () -> (new SmallSavannaGraveStructure(JigsawConfiguration.CODEC)));
    public static RegistryObject<StructureFeature<JigsawConfiguration>> SMALL_MOUNTAIN_GRAVE_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("small_mountain_grave_structure", () -> (new SmallMountainGraveStructure(JigsawConfiguration.CODEC)));
    public static RegistryObject<StructureFeature<JigsawConfiguration>> MUSHROOM_GRAVE_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("mushroom_grave_structure", () -> (new MushroomGraveStructure(JigsawConfiguration.CODEC)));
    public static RegistryObject<StructureFeature<JigsawConfiguration>> MEMORIAL_TREE_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("memorial_tree_structure", () -> (new MemorialTreeStructure(JigsawConfiguration.CODEC)));
    public static RegistryObject<StructureFeature<JigsawConfiguration>> MEDIUM_GRAVEYARD_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("medium_graveyard_structure", () -> (new MediumGraveyardStructure(JigsawConfiguration.CODEC)));
    public static RegistryObject<StructureFeature<JigsawConfiguration>> LARGE_GRAVEYARD_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("large_graveyard_structure", () -> (new LargeGraveyardStructure(JigsawConfiguration.CODEC)));
    public static RegistryObject<StructureFeature<JigsawConfiguration>> HAUNTED_HOUSE_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("haunted_house_structure", () -> (new HauntedHouseStructure(JigsawConfiguration.CODEC)));



    public static void setupStructures() {
        setupMapSpacingAndLand(MEDIUM_GRAVEYARD_STRUCTURE.get(), new StructureFeatureConfiguration(18, 16, 1690192399), true);
        setupMapSpacingAndLand(SMALL_GRAVEYARD_STRUCTURE.get(), new StructureFeatureConfiguration(20, 18, 240451934), true);
        setupMapSpacingAndLand(LARGE_GRAVEYARD_STRUCTURE.get(), new StructureFeatureConfiguration(12, 10, 304812394), true);
        setupMapSpacingAndLand(MUSHROOM_GRAVE_STRUCTURE.get(), new StructureFeatureConfiguration(24, 18, 379123039), true);
        setupMapSpacingAndLand(HAUNTED_HOUSE_STRUCTURE.get(), new StructureFeatureConfiguration(25, 20, 451235912), true);
        setupMapSpacingAndLand(MEMORIAL_TREE_STRUCTURE.get(), new StructureFeatureConfiguration(14, 12, 529239621), true);
        setupMapSpacingAndLand(SMALL_DESERT_GRAVEYARD_STRUCTURE.get(), new StructureFeatureConfiguration(32, 28, 598017285), true);
        setupMapSpacingAndLand(SMALL_GRAVE_STRUCTURE.get(), new StructureFeatureConfiguration(12, 8, 661903018), true);
        setupMapSpacingAndLand(SMALL_DESERT_GRAVE_STRUCTURE.get(), new StructureFeatureConfiguration(20, 16, 681236914), true);
        setupMapSpacingAndLand(SMALL_SAVANNA_GRAVE_STRUCTURE.get(), new StructureFeatureConfiguration(12, 8, 709787761), true);
        setupMapSpacingAndLand(SMALL_MOUNTAIN_GRAVE_STRUCTURE.get(), new StructureFeatureConfiguration(12, 8, 725689810), true);
    }

    public static void initGenerators() {
        // link to the structure pools
        MediumGraveyardStructure.MediumGraveyardGenerator.init();
        SmallGraveyardStructure.SmallGraveyardGenerator.init();
        LargeGraveyardStructure.LargeGraveyardGenerator.init();
        MushroomGraveStructure.MushroomGraveGenerator.init();
        HauntedHouseStructure.HauntedHouseGenerator.init();
        MemorialTreeStructure.MemorialTreeGenerator.init();
        SmallDesertGraveyardStructure.SmallDesertGraveyardGenerator.init();
        SmallGraveStructure.SmallGraveGenerator.init();
        SmallDesertGraveStructure.SmallDesertGraveGenerator.init();
        SmallSavannaGraveStructure.SmallSavannaGraveGenerator.init();
        SmallMountainGraveStructure.SmallMountainGraveGenerator.init();
    }


    public static <F extends StructureFeature<?>> void setupMapSpacingAndLand(F structure, StructureFeatureConfiguration structureFeatureConfiguration, boolean transformSurroundingLand) {
        MOD_STRUCTURES.add(structure);
        StructureFeature.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

        if (transformSurroundingLand) {
            StructureFeature.NOISE_AFFECTING_FEATURES =
                    ImmutableList.<StructureFeature<?>>builder()
                            .addAll(StructureFeature.NOISE_AFFECTING_FEATURES)
                            .add(structure)
                            .build();
        }

        StructureSettings.DEFAULTS =
                ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder()
                        .putAll(StructureSettings.DEFAULTS)
                        .put(structure, structureFeatureConfiguration)
                        .build();

        BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
            Map<StructureFeature<?>, StructureFeatureConfiguration> structureMap = settings.getValue().structureSettings().structureConfig();

            if (structureMap instanceof ImmutableMap){
                Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureFeatureConfiguration);
                settings.getValue().structureSettings().structureConfig = tempMap;
            } else {
                structureMap.put(structure, structureFeatureConfiguration);
            }
        });
    }



}
