package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
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
                    TGJigsawStructure.getBiomeWhiteList("haunted_house"),
                    TGJigsawStructure.getModIdWhiteList("haunted_house"),
                    "haunted_house"
            )
    );

    public static final Holder<Structure> LARGE_GRAVEYARD_STRUCTURE_CONFIG = register(TGStructureTypeKeys.LARGE_GRAVEYARD,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("large_graveyard"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    LargeGraveyardStructure.LargeGraveyardGenerator.STARTING_POOL, 4,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("large_graveyard"),
                    getTerrainDifference("large_graveyard"),
                    TGJigsawStructure.getBiomeWhiteList("large_graveyard"),
                    TGJigsawStructure.getModIdWhiteList("large_graveyard"),
                    "large_graveyard"
            )
    );

    public static final Holder<Structure> ALTAR_STRUCTURE_CONFIG = register(TGStructureTypeKeys.ALTAR,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("altar"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    AltarStructure.AltarGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("altar"),
                    getTerrainDifference("altar"),
                    TGJigsawStructure.getBiomeWhiteList("altar"),
                    TGJigsawStructure.getModIdWhiteList("altar"),
                    "altar"
            )
    );

    public static final Holder<Structure> CRYPT_STRUCTURE_CONFIG = register(TGStructureTypeKeys.CRYPT,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("crypt"), GenerationStep.Decoration.UNDERGROUND_STRUCTURES, TerrainAdjustment.NONE),
                    CryptStructure.CryptGenerator.STARTING_POOL, 7,
                    ConstantHeight.of(VerticalAnchor.absolute(-10)), false,
                    getTerrainCheck("crypt"),
                    getTerrainDifference("crypt"),
                    TGJigsawStructure.getBiomeWhiteList("crypt"),
                    TGJigsawStructure.getModIdWhiteList("crypt"),
                    "crypt"
            )
    );

    public static final Holder<Structure> GIANT_MUSHROOM_STRUCTURE_CONFIG = register(TGStructureTypeKeys.GIANT_MUSHROOM,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("giant_mushroom"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    GiantMushroomStructure.GiantMushroomGenerator.STARTING_POOL, 2,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), false, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("giant_mushroom"),
                    getTerrainDifference("giant_mushroom"),
                    TGJigsawStructure.getBiomeWhiteList("giant_mushroom"),
                    TGJigsawStructure.getModIdWhiteList("giant_mushroom"),
                    "giant_mushroom"
            )
    );

    public static final Holder<Structure> MEDIUM_GRAVEYARD_STRUCTURE_CONFIG = register(TGStructureTypeKeys.MEDIUM_GRAVEYARD,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("medium_graveyard"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    MediumGraveyardStructure.MediumGraveyardGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("medium_graveyard"),
                    getTerrainDifference("medium_graveyard"),
                    TGJigsawStructure.getBiomeWhiteList("medium_graveyard"),
                    TGJigsawStructure.getModIdWhiteList("medium_graveyard"),
                    "medium_graveyard"
            )
    );

    public static final Holder<Structure> SMALL_GRAVEYARD_STRUCTURE_CONFIG = register(TGStructureTypeKeys.SMALL_GRAVEYARD,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("small_graveyard"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    SmallGraveyardStructure.SmallGraveyardGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("small_graveyard"),
                    getTerrainDifference("small_graveyard"),
                    TGJigsawStructure.getBiomeWhiteList("small_graveyard"),
                    TGJigsawStructure.getModIdWhiteList("small_graveyard"),
                    "small_graveyard"
            )
    );

    public static final Holder<Structure> SMALL_DESERT_GRAVEYARD_STRUCTURE_CONFIG = register(TGStructureTypeKeys.SMALL_DESERT_GRAVEYARD,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("small_desert_graveyard"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    SmallDesertGraveyardStructure.SmallDesertGraveyardGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("small_desert_graveyard"),
                    getTerrainDifference("small_desert_graveyard"),
                    TGJigsawStructure.getBiomeWhiteList("small_desert_graveyard"),
                    TGJigsawStructure.getModIdWhiteList("small_desert_graveyard"),
                    "small_desert_graveyard"
            )
    );

    public static final Holder<Structure> SMALL_GRAVE_STRUCTURE_CONFIG = register(TGStructureTypeKeys.SMALL_GRAVE,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("small_grave"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    SmallGraveStructure.SmallGraveGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("small_grave"),
                    getTerrainDifference("small_grave"),
                    TGJigsawStructure.getBiomeWhiteList("small_grave"),
                    TGJigsawStructure.getModIdWhiteList("small_grave"),
                    "small_grave"
            )
    );

    public static final Holder<Structure> SMALL_SAVANNA_GRAVE_STRUCTURE_CONFIG = register(TGStructureTypeKeys.SMALL_SAVANNA_GRAVE,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("small_savanna_grave"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    SmallSavannaGraveStructure.SmallSavannaGraveGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("small_savanna_grave"),
                    getTerrainDifference("small_savanna_grave"),
                    TGJigsawStructure.getBiomeWhiteList("small_savanna_grave"),
                    TGJigsawStructure.getModIdWhiteList("small_savanna_grave"),
                    "small_savanna_grave"
            )
    );

    public static final Holder<Structure> SMALL_MOUNTAIN_GRAVE_STRUCTURE_CONFIG = register(TGStructureTypeKeys.SMALL_MOUNTAIN_GRAVE,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("small_mountain_grave"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    SmallMountainGraveStructure.SmallMountainGraveGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("small_mountain_grave"),
                    getTerrainDifference("small_mountain_grave"),
                    TGJigsawStructure.getBiomeWhiteList("small_mountain_grave"),
                    TGJigsawStructure.getModIdWhiteList("small_mountain_grave"),
                    "small_mountain_grave"
            )
    );


    public static final Holder<Structure> SMALL_DESERT_GRAVE_STRUCTURE_CONFIG = register(TGStructureTypeKeys.SMALL_DESERT_GRAVE,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("small_desert_grave"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    SmallDesertGraveStructure.SmallDesertGraveGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("small_desert_grave"),
                    getTerrainDifference("small_desert_grave"),
                    TGJigsawStructure.getBiomeWhiteList("small_desert_grave"),
                    TGJigsawStructure.getModIdWhiteList("small_desert_grave"),
                    "small_desert_grave"
            )
    );

    public static final Holder<Structure> MEMORIAL_TREE_STRUCTURE_CONFIG = register(TGStructureTypeKeys.MEMORIAL_TREE,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("memorial_tree"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    MemorialTreeStructure.MemorialTreeGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("memorial_tree"),
                    getTerrainDifference("memorial_tree"),
                    TGJigsawStructure.getBiomeWhiteList("memorial_tree"),
                    TGJigsawStructure.getModIdWhiteList("memorial_tree"),
                    "memorial_tree"
            )
    );

    public static final Holder<Structure> MUSHROOM_GRAVE_STRUCTURE_CONFIG = register(TGStructureTypeKeys.MUSHROOM_GRAVE,
            new TGJigsawStructure(createConfig(TGTags.IS_OVERWORLD, addMobSpawnsToStructure("mushroom_grave"), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_BOX),
                    MushroomGraveStructure.MushroomGraveGenerator.STARTING_POOL, 1,
                    ConstantHeight.of(VerticalAnchor.absolute(0)), true, Heightmap.Types.WORLD_SURFACE_WG,
                    getTerrainCheck("mushroom_grave"),
                    getTerrainDifference("mushroom_grave"),
                    TGJigsawStructure.getBiomeWhiteList("mushroom_grave"),
                    TGJigsawStructure.getModIdWhiteList("mushroom_grave"),
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

    // TODO move to StructureConfigEntry, this is just a very dirty, very quick way to make 1.19 work
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




}