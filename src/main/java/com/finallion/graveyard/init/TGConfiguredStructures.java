package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.world.structures.*;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;

import java.util.ArrayList;
import java.util.List;

public class TGConfiguredStructures {
    public static final List<ConfiguredStructureFeature<?, ?>> MOD_STRUCTURE_FEATURES = new ArrayList<>();

    public static final ConfiguredStructureFeature<?, ?> MEDIUM_GRAVEYARD_STRUCTURE_CONFIG = TGStructures.MEDIUM_GRAVEYARD_STRUCTURE.get().configured(
            new JigsawConfiguration(() -> MediumGraveyardStructure.MediumGraveyardGenerator.STARTING_POOL, 0));

    public static final ConfiguredStructureFeature<?, ?> SMALL_GRAVEYARD_STRUCTURE_CONFIG = TGStructures.SMALL_GRAVEYARD_STRUCTURE.get().configured(
            new JigsawConfiguration(() -> SmallGraveyardStructure.SmallGraveyardGenerator.STARTING_POOL, 0));

    public static final ConfiguredStructureFeature<?, ?> LARGE_GRAVEYARD_STRUCTURE_CONFIG = TGStructures.LARGE_GRAVEYARD_STRUCTURE.get().configured(
            new JigsawConfiguration(() -> LargeGraveyardStructure.LargeGraveyardGenerator.STARTING_POOL, 0));

    public static final ConfiguredStructureFeature<?, ?> MUSHROOM_GRAVE_STRUCTURE_CONFIG = TGStructures.MUSHROOM_GRAVE_STRUCTURE.get().configured(
            new JigsawConfiguration(() -> MushroomGraveStructure.MushroomGraveGenerator.STARTING_POOL, 0));

    public static final ConfiguredStructureFeature<?, ?> HAUNTED_HOUSE_STRUCTURE_CONFIG = TGStructures.HAUNTED_HOUSE_STRUCTURE.get().configured(
            new JigsawConfiguration(() -> HauntedHouseStructure.HauntedHouseGenerator.STARTING_POOL, 0));

    public static final ConfiguredStructureFeature<?, ?> MEMORIAL_TREE_STRUCTURE_CONFIG = TGStructures.MEMORIAL_TREE_STRUCTURE.get().configured(
            new JigsawConfiguration(() -> MemorialTreeStructure.MemorialTreeGenerator.STARTING_POOL, 0));

    public static final ConfiguredStructureFeature<?, ?> SMALL_DESERT_GRAVEYARD_STRUCTURE_CONFIG = TGStructures.SMALL_DESERT_GRAVEYARD_STRUCTURE.get().configured(
            new JigsawConfiguration(() -> SmallDesertGraveyardStructure.SmallDesertGraveyardGenerator.STARTING_POOL, 0));

    public static final ConfiguredStructureFeature<?, ?> SMALL_GRAVE_STRUCTURE_CONFIG = TGStructures.SMALL_GRAVE_STRUCTURE.get().configured(
            new JigsawConfiguration(() -> SmallGraveStructure.SmallGraveGenerator.STARTING_POOL, 0));

    public static final ConfiguredStructureFeature<?, ?> SMALL_DESERT_GRAVE_STRUCTURE_CONFIG = TGStructures.SMALL_DESERT_GRAVE_STRUCTURE.get().configured(
            new JigsawConfiguration(() -> SmallDesertGraveStructure.SmallDesertGraveGenerator.STARTING_POOL, 0));

    public static final ConfiguredStructureFeature<?, ?> SMALL_SAVANNA_GRAVE_STRUCTURE_CONFIG = TGStructures.SMALL_SAVANNA_GRAVE_STRUCTURE.get().configured(
            new JigsawConfiguration(() -> SmallSavannaGraveStructure.SmallSavannaGraveGenerator.STARTING_POOL, 0));

    public static final ConfiguredStructureFeature<?, ?> SMALL_MOUNTAIN_GRAVE_STRUCTURE_CONFIG = TGStructures.SMALL_MOUNTAIN_GRAVE_STRUCTURE.get().configured(
            new JigsawConfiguration(() -> SmallMountainGraveStructure.SmallMountainGraveGenerator.STARTING_POOL, 0));



    public static void registerConfiguredStructures() {
        MOD_STRUCTURE_FEATURES.add(SMALL_GRAVEYARD_STRUCTURE_CONFIG);
        MOD_STRUCTURE_FEATURES.add(SMALL_DESERT_GRAVEYARD_STRUCTURE_CONFIG);
        MOD_STRUCTURE_FEATURES.add(MEDIUM_GRAVEYARD_STRUCTURE_CONFIG);
        MOD_STRUCTURE_FEATURES.add(LARGE_GRAVEYARD_STRUCTURE_CONFIG);
        MOD_STRUCTURE_FEATURES.add(SMALL_GRAVE_STRUCTURE_CONFIG);
        MOD_STRUCTURE_FEATURES.add(SMALL_DESERT_GRAVE_STRUCTURE_CONFIG);
        MOD_STRUCTURE_FEATURES.add(SMALL_SAVANNA_GRAVE_STRUCTURE_CONFIG);
        MOD_STRUCTURE_FEATURES.add(SMALL_MOUNTAIN_GRAVE_STRUCTURE_CONFIG);
        MOD_STRUCTURE_FEATURES.add(MUSHROOM_GRAVE_STRUCTURE_CONFIG);
        MOD_STRUCTURE_FEATURES.add(HAUNTED_HOUSE_STRUCTURE_CONFIG);
        MOD_STRUCTURE_FEATURES.add(MEMORIAL_TREE_STRUCTURE_CONFIG);

        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "small_graveyard_structure_config"), SMALL_GRAVEYARD_STRUCTURE_CONFIG);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "small_desert_graveyard_structure_config"), SMALL_DESERT_GRAVEYARD_STRUCTURE_CONFIG);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "medium_graveyard_structure_config"), MEDIUM_GRAVEYARD_STRUCTURE_CONFIG);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "large_graveyard_structure_config"), LARGE_GRAVEYARD_STRUCTURE_CONFIG);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "small_grave_structure_config"), SMALL_GRAVE_STRUCTURE_CONFIG);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "small_desert_grave_structure_config"), SMALL_DESERT_GRAVE_STRUCTURE_CONFIG);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "small_savanna_grave_structure_config"), SMALL_SAVANNA_GRAVE_STRUCTURE_CONFIG);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "small_mountain_grave_structure_config"), SMALL_MOUNTAIN_GRAVE_STRUCTURE_CONFIG);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "mushroom_grave_structure_config"), MUSHROOM_GRAVE_STRUCTURE_CONFIG);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "haunted_house_structure_config"), HAUNTED_HOUSE_STRUCTURE_CONFIG);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "memorial_tree_structure_config"), MEMORIAL_TREE_STRUCTURE_CONFIG);
    }
}
