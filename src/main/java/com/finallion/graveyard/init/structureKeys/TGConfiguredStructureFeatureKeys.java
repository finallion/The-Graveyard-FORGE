package com.finallion.graveyard.init.structureKeys;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;

public class TGConfiguredStructureFeatureKeys {
    public static ResourceKey<ConfiguredStructureFeature<?, ?>> HAUNTED_HOUSE = of("haunted_house");
    public static ResourceKey<ConfiguredStructureFeature<?, ?>> LARGE_GRAVEYARD = of("large_graveyard");
    public static ResourceKey<ConfiguredStructureFeature<?, ?>> MEDIUM_GRAVEYARD = of("medium_graveyard");
    public static ResourceKey<ConfiguredStructureFeature<?, ?>> SMALL_GRAVEYARD = of("small_graveyard");
    public static ResourceKey<ConfiguredStructureFeature<?, ?>> SMALL_DESERT_GRAVEYARD = of("small_desert_graveyard");
    public static ResourceKey<ConfiguredStructureFeature<?, ?>> SMALL_GRAVE = of("small_grave");
    public static ResourceKey<ConfiguredStructureFeature<?, ?>> SMALL_DESERT_GRAVE = of("small_desert_grave");
    public static ResourceKey<ConfiguredStructureFeature<?, ?>> SMALL_SAVANNA_GRAVE = of("small_savanna_grave");
    public static ResourceKey<ConfiguredStructureFeature<?, ?>> SMALL_MOUNTAIN_GRAVE = of("small_mountain_grave");
    public static ResourceKey<ConfiguredStructureFeature<?, ?>> MUSHROOM_GRAVE = of("mushroom_grave");
    public static ResourceKey<ConfiguredStructureFeature<?, ?>> MEMORIAL_TREE = of("memorial_tree");
    public static ResourceKey<ConfiguredStructureFeature<?, ?>> ALTAR = of("altar");
    public static ResourceKey<ConfiguredStructureFeature<?, ?>> GIANT_MUSHROOM = of("giant_mushroom");
    public static ResourceKey<ConfiguredStructureFeature<?, ?>> CRYPT = of("crypt");

    private static ResourceKey<ConfiguredStructureFeature<?, ?>> of(String id) {
        return ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(TheGraveyard.MOD_ID, id));
    }
}
