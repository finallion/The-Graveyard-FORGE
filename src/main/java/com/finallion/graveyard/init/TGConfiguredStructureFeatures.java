package com.finallion.graveyard.init;

import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.structureKeys.TGConfiguredStructureFeatureKeys;
import com.finallion.graveyard.util.StructureSpawnPool;
import com.finallion.graveyard.util.TGTags;
import com.finallion.graveyard.world.structures.*;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.PillagerOutpostPools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TGConfiguredStructureFeatures {
    public static final List<ConfiguredStructureFeature<?, ?>> MOD_STRUCTURE_FEATURES = new ArrayList<>();

    public static final Holder<ConfiguredStructureFeature<?, ?>> HAUNTED_HOUSE_STRUCTURE_CONFIG = register(TGConfiguredStructureFeatureKeys.HAUNTED_HOUSE,
            TGStructureFeatures.HAUNTED_HOUSE_STRUCTURE.m_209773_( // structure size: 30
                    new JigsawConfiguration(HauntedHouseStructure.HauntedHouseGenerator.STARTING_POOL, 2), TGTags.IS_OVERWORLD, true, addMobSpawnsToStructure(GraveyardConfig.COMMON.structureConfigEntries.get("haunted_house").canSpawnGraveyardMobs.get(), "")));

    public static final Holder<ConfiguredStructureFeature<?, ?>> LARGE_GRAVEYARD_STRUCTURE_CONFIG = register(TGConfiguredStructureFeatureKeys.LARGE_GRAVEYARD,
            TGStructureFeatures.LARGE_GRAVEYARD_STRUCTURE.m_209773_( // structure size: 45
                    new JigsawConfiguration(LargeGraveyardStructure.LargeGraveyardGenerator.STARTING_POOL, 3), TGTags.IS_OVERWORLD, true, addMobSpawnsToStructure(GraveyardConfig.COMMON.structureConfigEntries.get("large_graveyard").canSpawnGraveyardMobs.get(), "")));

    public static final Holder<ConfiguredStructureFeature<?, ?>> MEDIUM_GRAVEYARD_STRUCTURE_CONFIG = register(TGConfiguredStructureFeatureKeys.MEDIUM_GRAVEYARD,
            TGStructureFeatures.MEDIUM_GRAVEYARD_STRUCTURE.m_209773_( // structure size: 30
                    new JigsawConfiguration(MediumGraveyardStructure.MediumGraveyardGenerator.STARTING_POOL, 1), TGTags.IS_OVERWORLD, true, addMobSpawnsToStructure(GraveyardConfig.COMMON.structureConfigEntries.get("medium_graveyard").canSpawnGraveyardMobs.get(), "")));

    public static final Holder<ConfiguredStructureFeature<?, ?>> MEMORIAL_TREE_STRUCTURE_CONFIG = register(TGConfiguredStructureFeatureKeys.MEMORIAL_TREE,
            TGStructureFeatures.MEMORIAL_TREE_STRUCTURE.m_209773_( // structure size: 10
                    new JigsawConfiguration(MemorialTreeStructure.MemorialTreeGenerator.STARTING_POOL, 1), TGTags.IS_OVERWORLD, true, addMobSpawnsToStructure(GraveyardConfig.COMMON.structureConfigEntries.get("memorial_tree").canSpawnGraveyardMobs.get(), "")));

    public static final Holder<ConfiguredStructureFeature<?, ?>> MUSHROOM_GRAVE_STRUCTURE_CONFIG = register(TGConfiguredStructureFeatureKeys.MUSHROOM_GRAVE,
            TGStructureFeatures.MUSHROOM_GRAVE_STRUCTURE.m_209773_( // structure size: 7
                    new JigsawConfiguration(MushroomGraveStructure.MushroomGraveGenerator.STARTING_POOL, 1), TGTags.IS_OVERWORLD, true, addMobSpawnsToStructure(GraveyardConfig.COMMON.structureConfigEntries.get("mushroom_grave").canSpawnGraveyardMobs.get(), "")));

    public static final Holder<ConfiguredStructureFeature<?, ?>> SMALL_GRAVEYARD_STRUCTURE_CONFIG = register(TGConfiguredStructureFeatureKeys.SMALL_GRAVEYARD,
            TGStructureFeatures.SMALL_GRAVEYARD_STRUCTURE.m_209773_( // structure size: 15
                    new JigsawConfiguration(SmallGraveyardStructure.SmallGraveyardGenerator.STARTING_POOL, 1), TGTags.IS_OVERWORLD, true, addMobSpawnsToStructure(GraveyardConfig.COMMON.structureConfigEntries.get("small_graveyard").canSpawnGraveyardMobs.get(), "")));

    public static final Holder<ConfiguredStructureFeature<?, ?>> SMALL_DESERT_GRAVEYARD_STRUCTURE_CONFIG = register(TGConfiguredStructureFeatureKeys.SMALL_DESERT_GRAVEYARD,
            TGStructureFeatures.SMALL_DESERT_GRAVEYARD_STRUCTURE.m_209773_( // structure size: 20
                    new JigsawConfiguration(SmallDesertGraveyardStructure.SmallDesertGraveyardGenerator.STARTING_POOL, 1), TGTags.IS_OVERWORLD, true, addMobSpawnsToStructure(GraveyardConfig.COMMON.structureConfigEntries.get("small_desert_graveyard").canSpawnGraveyardMobs.get(), "small_desert_graveyard")));

    public static final Holder<ConfiguredStructureFeature<?, ?>> SMALL_GRAVE_STRUCTURE_CONFIG = register(TGConfiguredStructureFeatureKeys.SMALL_GRAVE,
            TGStructureFeatures.SMALL_GRAVE_STRUCTURE.m_209773_( // structure size: 4
                    new JigsawConfiguration(SmallGraveStructure.SmallGraveGenerator.STARTING_POOL, 1), TGTags.IS_OVERWORLD, true, addMobSpawnsToStructure(GraveyardConfig.COMMON.structureConfigEntries.get("small_grave").canSpawnGraveyardMobs.get(), "")));

    public static final Holder<ConfiguredStructureFeature<?, ?>> SMALL_DESERT_GRAVE_STRUCTURE_CONFIG = register(TGConfiguredStructureFeatureKeys.SMALL_DESERT_GRAVE,
            TGStructureFeatures.SMALL_DESERT_GRAVE_STRUCTURE.m_209773_(
                    new JigsawConfiguration(SmallDesertGraveStructure.SmallDesertGraveGenerator.STARTING_POOL, 1), TGTags.IS_OVERWORLD, true, addMobSpawnsToStructure(GraveyardConfig.COMMON.structureConfigEntries.get("small_desert_grave").canSpawnGraveyardMobs.get(), "")));

    public static final Holder<ConfiguredStructureFeature<?, ?>> SMALL_SAVANNA_GRAVE_STRUCTURE_CONFIG = register(TGConfiguredStructureFeatureKeys.SMALL_SAVANNA_GRAVE,
            TGStructureFeatures.SMALL_SAVANNA_GRAVE_STRUCTURE.m_209773_(
                    new JigsawConfiguration(SmallSavannaGraveStructure.SmallSavannaGraveGenerator.STARTING_POOL, 1), TGTags.IS_OVERWORLD, true, addMobSpawnsToStructure(GraveyardConfig.COMMON.structureConfigEntries.get("small_savanna_grave").canSpawnGraveyardMobs.get(), "")));

    public static final Holder<ConfiguredStructureFeature<?, ?>> SMALL_MOUNTAIN_GRAVE_STRUCTURE_CONFIG = register(TGConfiguredStructureFeatureKeys.SMALL_MOUNTAIN_GRAVE,
            TGStructureFeatures.SMALL_MOUNTAIN_GRAVE_STRUCTURE.m_209773_(
                    new JigsawConfiguration(SmallMountainGraveStructure.SmallMountainGraveGenerator.STARTING_POOL, 1), TGTags.IS_OVERWORLD, true, addMobSpawnsToStructure(GraveyardConfig.COMMON.structureConfigEntries.get("small_mountain_grave").canSpawnGraveyardMobs.get(), "")));

    public static final Holder<ConfiguredStructureFeature<?, ?>> ALTAR_STRUCTURE_CONFIG = register(TGConfiguredStructureFeatureKeys.ALTAR,
            TGStructureFeatures.ALTAR_STRUCTURE.m_209773_(
                    new JigsawConfiguration(AltarStructure.AltarGenerator.STARTING_POOL, 1), TGTags.IS_OVERWORLD, true, addMobSpawnsToStructure(GraveyardConfig.COMMON.structureConfigEntries.get("altar").canSpawnGraveyardMobs.get(), "")));

    public static final Holder<ConfiguredStructureFeature<?, ?>> GIANT_MUSHROOM_STRUCTURE_CONFIG = register(TGConfiguredStructureFeatureKeys.GIANT_MUSHROOM,
            TGStructureFeatures.GIANT_MUSHROOM_STRUCTURE.m_209773_(
                    new JigsawConfiguration(GiantMushroomStructure.GiantMushroomGenerator.STARTING_POOL, 2), TGTags.IS_OVERWORLD, true, addMobSpawnsToStructure(GraveyardConfig.COMMON.structureConfigEntries.get("giant_mushroom").canSpawnGraveyardMobs.get(), "")));

    public static final Holder<ConfiguredStructureFeature<?, ?>> CRYPT_STRUCTURE_CONFIG = register(TGConfiguredStructureFeatureKeys.CRYPT,
            TGStructureFeatures.CRYPT_STRUCTURE.m_209773_(
                    new JigsawConfiguration(CryptStructure.CryptGenerator.STARTING_POOL, 7), TGTags.IS_OVERWORLD, false, addMobSpawnsToStructure(GraveyardConfig.COMMON.structureConfigEntries.get("crypt").canSpawnGraveyardMobs.get(), "")));


    public static void init() {
    }

    private static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> Holder<ConfiguredStructureFeature<?, ?>> register(ResourceKey<ConfiguredStructureFeature<?, ?>> p_211107_, ConfiguredStructureFeature<FC, F> p_211108_) {
        MOD_STRUCTURE_FEATURES.add(p_211108_);
        return BuiltinRegistries.m_206384_(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, p_211107_, p_211108_);
    }


    private static Map<MobCategory, StructureSpawnOverride> addMobSpawnsToStructure(boolean bool, String name) {
        if (bool) {
            return Map.of(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, StructureSpawnPool.MONSTER_SPAWNS));
        }

        if (name.equals("small_desert_graveyard")) {
            return Map.of(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, StructureSpawnPool.ILLAGER_SPAWNS));
        }


        return Map.of(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, StructureSpawnPool.EMPTY));
    }
}
