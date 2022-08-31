package com.finallion.graveyard.init;

import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.structureKeys.TGStructureTypeKeys;
import com.finallion.graveyard.util.TGTags;
import com.finallion.graveyard.world.structures.*;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TGConfiguredStructureFeatures {
    public static List<Structure> structures = new ArrayList<>();

    public static final Holder<Structure> HAUNTED_HOUSE_STRUCTURE_CONFIG = register(TGStructureTypeKeys.HAUNTED_HOUSE,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("haunted_house"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    HauntedHouseStructure.HauntedHouseGenerator.STARTING_POOL, 2,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("haunted_house"),
                    getTerrainDifference("haunted_house"),
                    getBiomeWhiteList("haunted_house"),
                    getBiomeBlackList("haunted_house"),
                    "haunted_house"
            )
    );

    public static final Holder<Structure> LARGE_GRAVEYARD_STRUCTURE_CONFIG = register(TGStructureTypeKeys.LARGE_GRAVEYARD,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("large_graveyard"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    LargeGraveyardStructure.LargeGraveyardGenerator.STARTING_POOL, 4,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("large_graveyard"),
                    getTerrainDifference("large_graveyard"),
                    getBiomeWhiteList("large_graveyard"),
                    getBiomeBlackList("large_graveyard"),
                    "large_graveyard"
            )
    );

    public static final Holder<Structure> ALTAR_STRUCTURE_CONFIG = register(TGStructureTypeKeys.ALTAR,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("altar"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    AltarStructure.AltarGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("altar"),
                    getTerrainDifference("altar"),
                    getBiomeWhiteList("altar"),
                    getBiomeBlackList("altar"),
                    "altar"
            )
    );

    public static final Holder<Structure> CRYPT_STRUCTURE_CONFIG = register(TGStructureTypeKeys.CRYPT,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("crypt"), GenerationStep.Decoration.UNDERGROUND_STRUCTURES, TerrainAdjustment.NONE),
                    CryptStructure.CryptGenerator.STARTING_POOL, 7,
                    ConstantHeight.of(VerticalAnchor.absolute(-10)), false,
                    getTerrainCheck("crypt"),
                    getTerrainDifference("crypt"),
                    getBiomeWhiteList("crypt"),
                    getBiomeBlackList("crypt"),
                    "crypt"
            )
    );

    public static final Holder<Structure> GIANT_MUSHROOM_STRUCTURE_CONFIG = register(TGStructureTypeKeys.GIANT_MUSHROOM,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("giant_mushroom"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    GiantMushroomStructure.GiantMushroomGenerator.STARTING_POOL, 2,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), false, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("giant_mushroom"),
                    getTerrainDifference("giant_mushroom"),
                    getBiomeWhiteList("giant_mushroom"),
                    getBiomeBlackList("giant_mushroom"),
                    "giant_mushroom"
            )
    );

    public static final Holder<Structure> MEDIUM_GRAVEYARD_STRUCTURE_CONFIG = register(TGStructureTypeKeys.MEDIUM_GRAVEYARD,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("medium_graveyard"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    MediumGraveyardStructure.MediumGraveyardGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("medium_graveyard"),
                    getTerrainDifference("medium_graveyard"),
                    getBiomeWhiteList("medium_graveyard"),
                    getBiomeBlackList("medium_graveyard"),
                    "medium_graveyard"
            )
    );

    public static final Holder<Structure> SMALL_GRAVEYARD_STRUCTURE_CONFIG = register(TGStructureTypeKeys.SMALL_GRAVEYARD,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("small_graveyard"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    SmallGraveyardStructure.SmallGraveyardGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("small_graveyard"),
                    getTerrainDifference("small_graveyard"),
                    getBiomeWhiteList("small_graveyard"),
                    getBiomeBlackList("small_graveyard"),
                    "small_graveyard"
            )
    );

    public static final Holder<Structure> SMALL_DESERT_GRAVEYARD_STRUCTURE_CONFIG = register(TGStructureTypeKeys.SMALL_DESERT_GRAVEYARD,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("small_desert_graveyard"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    SmallDesertGraveyardStructure.SmallDesertGraveyardGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("small_desert_graveyard"),
                    getTerrainDifference("small_desert_graveyard"),
                    getBiomeWhiteList("small_desert_graveyard"),
                    getBiomeBlackList("small_desert_graveyard"),
                    "small_desert_graveyard"
            )
    );

    public static final Holder<Structure> SMALL_GRAVE_STRUCTURE_CONFIG = register(TGStructureTypeKeys.SMALL_GRAVE,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("small_grave"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    SmallGraveStructure.SmallGraveGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("small_grave"),
                    getTerrainDifference("small_grave"),
                    getBiomeWhiteList("small_grave"),
                    getBiomeBlackList("small_grave"),
                    "small_grave"
            )
    );

    public static final Holder<Structure> SMALL_SAVANNA_GRAVE_STRUCTURE_CONFIG = register(TGStructureTypeKeys.SMALL_SAVANNA_GRAVE,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("small_savanna_grave"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    SmallSavannaGraveStructure.SmallSavannaGraveGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("small_savanna_grave"),
                    getTerrainDifference("small_savanna_grave"),
                    getBiomeWhiteList("small_savanna_grave"),
                    getBiomeBlackList("small_savanna_grave"),
                    "small_savanna_grave"
            )
    );

    public static final Holder<Structure> SMALL_MOUNTAIN_GRAVE_STRUCTURE_CONFIG = register(TGStructureTypeKeys.SMALL_MOUNTAIN_GRAVE,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("small_mountain_grave"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    SmallMountainGraveStructure.SmallMountainGraveGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("small_mountain_grave"),
                    getTerrainDifference("small_mountain_grave"),
                    getBiomeWhiteList("small_mountain_grave"),
                    getBiomeBlackList("small_mountain_grave"),
                    "small_mountain_grave"
            )
    );


    public static final Holder<Structure> SMALL_DESERT_GRAVE_STRUCTURE_CONFIG = register(TGStructureTypeKeys.SMALL_DESERT_GRAVE,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("small_desert_grave"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    SmallDesertGraveStructure.SmallDesertGraveGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("small_desert_grave"),
                    getTerrainDifference("small_desert_grave"),
                    getBiomeWhiteList("small_desert_grave"),
                    getBiomeBlackList("small_desert_grave"),
                    "small_desert_grave"
            )
    );

    public static final Holder<Structure> MEMORIAL_TREE_STRUCTURE_CONFIG = register(TGStructureTypeKeys.MEMORIAL_TREE,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("memorial_tree"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    MemorialTreeStructure.MemorialTreeGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("memorial_tree"),
                    getTerrainDifference("memorial_tree"),
                    getBiomeWhiteList("memorial_tree"),
                    getBiomeBlackList("memorial_tree"),
                    "memorial_tree"
            )
    );

    public static final Holder<Structure> MUSHROOM_GRAVE_STRUCTURE_CONFIG = register(TGStructureTypeKeys.MUSHROOM_GRAVE,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("mushroom_grave"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    MushroomGraveStructure.MushroomGraveGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("mushroom_grave"),
                    getTerrainDifference("mushroom_grave"),
                    getBiomeWhiteList("mushroom_grave"),
                    getBiomeBlackList("mushroom_grave"),
                    "mushroom_grave"
            )
    );



    private static Structure.StructureSettings createConfig(TagKey<Biome> biomeTag, Map<MobCategory, StructureSpawnOverride> spawns, GenerationStep.Decoration featureStep, TerrainAdjustment terrainAdaptation) {
        return new Structure.StructureSettings(BuiltinRegistries.BIOME.getOrCreateTag(biomeTag), spawns, featureStep, terrainAdaptation);
    }

    private static Holder<Structure> register(ResourceKey<Structure> p_236534_, Structure p_236535_) {
        structures.add(p_236535_);
        return BuiltinRegistries.register(BuiltinRegistries.STRUCTURES, p_236534_, p_236535_);
    }

    public static void init() {}


    private static Map<MobCategory, StructureSpawnOverride> addMobSpawnsToStructure(String name) {
        if (canGenerate(name)) {
            return Map.of(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, TGJigsawStructure.MONSTER_SPAWNS));
        }

        if (name.equals("small_desert_graveyard")) {
            return Map.of(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, TGJigsawStructure.ILLAGER_SPAWNS));
        }

        return Map.of(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, TGJigsawStructure.EMPTY));
    }

    private static boolean canGenerate(String name) {
        return switch (name) {
            case "haunted_house" -> GraveyardConfig.COMMON.canSpawnGraveyardMobsHauntedHouse.get();
            case "large_graveyard" -> GraveyardConfig.COMMON.canSpawnGraveyardMobsLargeGraveyard.get();
            case "medium_graveyard" -> GraveyardConfig.COMMON.canSpawnGraveyardMobsMediumGraveyard.get();
            case "small_graveyard" -> GraveyardConfig.COMMON.canSpawnGraveyardMobsSmallGraveyard.get();
            case "small_desert_graveyard" -> GraveyardConfig.COMMON.canSpawnGraveyardMobsSmallDesertGraveyard.get();
            case "small_grave" -> GraveyardConfig.COMMON.canSpawnGraveyardMobsSmallGrave.get();
            case "small_mountain_grave" -> GraveyardConfig.COMMON.canSpawnGraveyardMobsSmallMountainGrave.get();
            case "small_savanna_grave" -> GraveyardConfig.COMMON.canSpawnGraveyardMobsSmallSavannaGrave.get();
            case "small_desert_grave" -> GraveyardConfig.COMMON.canSpawnGraveyardMobsSmallDesertGrave.get();
            case "mushroom_grave" -> GraveyardConfig.COMMON.canSpawnGraveyardMobsMushroomGrave.get();
            case "memorial_tree" -> GraveyardConfig.COMMON.canSpawnGraveyardMobsMemorialTree.get();
            case "crypt" -> GraveyardConfig.COMMON.canSpawnGraveyardMobsCrypt.get();
            case "altar" -> GraveyardConfig.COMMON.canSpawnGraveyardMobsAltar.get();
            case "giant_mushroom" -> GraveyardConfig.COMMON.canSpawnGraveyardMobsGiantMushroom.get();
            default -> false;
        };
    }

    private static int getTerrainCheck(String name) {
        return switch (name) {
            case "haunted_house" -> GraveyardConfig.COMMON.terrainCheckRadiusHauntedHouse.get();
            case "large_graveyard" -> GraveyardConfig.COMMON.terrainCheckRadiusLargeGraveyard.get();
            case "medium_graveyard" -> GraveyardConfig.COMMON.terrainCheckRadiusMediumGraveyard.get();
            case "small_graveyard" -> GraveyardConfig.COMMON.terrainCheckRadiusSmallGraveyard.get();
            case "small_desert_graveyard" -> GraveyardConfig.COMMON.terrainCheckRadiusSmallDesertGraveyard.get();
            case "small_grave" -> GraveyardConfig.COMMON.terrainCheckRadiusSmallGrave.get();
            case "small_mountain_grave" -> GraveyardConfig.COMMON.terrainCheckRadiusSmallMountainGrave.get();
            case "small_savanna_grave" -> GraveyardConfig.COMMON.terrainCheckRadiusSavannaGrave.get();
            case "small_desert_grave" -> GraveyardConfig.COMMON.terrainCheckRadiusSmallDesertGrave.get();
            case "mushroom_grave" -> GraveyardConfig.COMMON.terrainCheckRadiusMushroomGrave.get();
            case "memorial_tree" -> GraveyardConfig.COMMON.terrainCheckRadiusMemorialTree.get();
            case "crypt" -> GraveyardConfig.COMMON.terrainCheckRadiusCrypt.get();
            case "altar" -> GraveyardConfig.COMMON.terrainCheckRadiusAltar.get();
            case "giant_mushroom" -> GraveyardConfig.COMMON.terrainCheckRadiusGiantMushroom.get();
            default -> 0;
        };
    }

    private static int getTerrainDifference(String name) {
        return switch (name) {
            case "haunted_house" -> GraveyardConfig.COMMON.terrainHeightDifferenceHauntedHouse.get();
            case "large_graveyard" -> GraveyardConfig.COMMON.terrainHeightDifferenceLargeGraveyard.get();
            case "medium_graveyard" -> GraveyardConfig.COMMON.terrainHeightDifferenceMediumGraveyard.get();
            case "small_graveyard" -> GraveyardConfig.COMMON.terrainHeightDifferenceSmallGraveyard.get();
            case "small_desert_graveyard" -> GraveyardConfig.COMMON.terrainHeightDifferenceSmallDesertGraveyard.get();
            case "small_grave" -> GraveyardConfig.COMMON.terrainHeightDifferenceSmallGrave.get();
            case "small_mountain_grave" -> GraveyardConfig.COMMON.terrainHeightDifferenceSmallMountainGrave.get();
            case "small_savanna_grave" -> GraveyardConfig.COMMON.terrainHeightDifferenceSavannaGrave.get();
            case "small_desert_grave" -> GraveyardConfig.COMMON.terrainHeightDifferenceSmallDesertGrave.get();
            case "mushroom_grave" -> GraveyardConfig.COMMON.terrainHeightDifferenceMushroomGrave.get();
            case "memorial_tree" -> GraveyardConfig.COMMON.terrainHeightDifferenceMemorialTree.get();
            case "crypt" -> GraveyardConfig.COMMON.terrainHeightDifferenceCrypt.get();
            case "altar" -> GraveyardConfig.COMMON.terrainHeightDifferenceAltar.get();
            case "giant_mushroom" -> GraveyardConfig.COMMON.terrainHeightDifferenceGiantMushroom.get();
            default -> 0;
        };
    }

    public static List<String> getBiomeWhiteList(String name) {
        return switch (name) {
            case "haunted_house" -> (List<String>) GraveyardConfig.COMMON.whitelistHauntedHouse.get();
            case "large_graveyard" -> (List<String>) GraveyardConfig.COMMON.whitelistLargeGraveyard.get();
            case "medium_graveyard" -> (List<String>) GraveyardConfig.COMMON.whitelistMediumGraveyard.get();
            case "small_graveyard" -> (List<String>) GraveyardConfig.COMMON.whitelistSmallGraveyard.get();
            case "small_desert_graveyard" -> (List<String>) GraveyardConfig.COMMON.whitelistSmallDesertGraveyard.get();
            case "small_grave" -> (List<String>) GraveyardConfig.COMMON.whitelistSmallGrave.get();
            case "small_mountain_grave" -> (List<String>) GraveyardConfig.COMMON.whitelistSmallMountainGrave.get();
            case "small_savanna_grave" -> (List<String>) GraveyardConfig.COMMON.whitelistSmallSavannaGrave.get();
            case "small_desert_grave" -> (List<String>) GraveyardConfig.COMMON.whitelistSmallDesertGrave.get();
            case "mushroom_grave" -> (List<String>) GraveyardConfig.COMMON.whitelistMushroomGrave.get();
            case "memorial_tree" -> (List<String>) GraveyardConfig.COMMON.whitelistMemorialTree.get();
            case "crypt" -> (List<String>) GraveyardConfig.COMMON.whitelistCrypt.get();
            case "altar" -> (List<String>) GraveyardConfig.COMMON.whitelistAltar.get();
            case "giant_mushroom" -> (List<String>) GraveyardConfig.COMMON.whitelistGiantMushroom.get();
            default -> Collections.EMPTY_LIST;
        };

    }

    public static List<String> getBiomeBlackList(String name) {
        return switch (name) {
            case "haunted_house" -> (List<String>) GraveyardConfig.COMMON.blacklistHauntedHouse.get();
            case "large_graveyard" -> (List<String>) GraveyardConfig.COMMON.blacklistLargeGraveyard.get();
            case "medium_graveyard" -> (List<String>) GraveyardConfig.COMMON.blacklistMediumGraveyard.get();
            case "small_graveyard" -> (List<String>) GraveyardConfig.COMMON.blacklistSmallGraveyard.get();
            case "small_desert_graveyard" -> (List<String>) GraveyardConfig.COMMON.blacklistSmallDesertGraveyard.get();
            case "small_grave" -> (List<String>) GraveyardConfig.COMMON.blacklistSmallGrave.get();
            case "small_mountain_grave" -> (List<String>) GraveyardConfig.COMMON.blacklistSmallMountainGrave.get();
            case "small_savanna_grave" -> (List<String>) GraveyardConfig.COMMON.blacklistSmallSavannaGrave.get();
            case "small_desert_grave" -> (List<String>) GraveyardConfig.COMMON.blacklistSmallDesertGrave.get();
            case "mushroom_grave" -> (List<String>) GraveyardConfig.COMMON.blacklistMushroomGrave.get();
            case "memorial_tree" -> (List<String>) GraveyardConfig.COMMON.blacklistMemorialTree.get();
            case "crypt" -> (List<String>) GraveyardConfig.COMMON.blacklistCrypt.get();
            case "altar" -> (List<String>) GraveyardConfig.COMMON.blacklistAltar.get();
            case "giant_mushroom" -> (List<String>) GraveyardConfig.COMMON.blacklistGiantMushroom.get();
            default -> Collections.EMPTY_LIST;
        };

    }

}
