package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class TGConfiguredStructureFeatures {

    public static StructureFeature<?, ?> CONFIGURED_SMALL_WALLED_GRAVEYARD = TGStructures.SMALL_WALLED_GRAVEYARD.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_SMALL_WALLED_GRAVEYARD_SAVANNA = TGStructures.SMALL_WALLED_GRAVEYARD_SAVANNA.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_SMALL_WALLED_GRAVEYARD_DESERT = TGStructures.SMALL_WALLED_GRAVEYARD_DESERT.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_SMALL_GRAVE = TGStructures.SMALL_GRAVE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_SMALL_DESERT_GRAVE = TGStructures.SMALL_DESERT_GRAVE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_SMALL_SAVANNA_GRAVE = TGStructures.SMALL_SAVANNA_GRAVE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_SMALL_MOUNTAIN_GRAVE = TGStructures.SMALL_MOUNTAIN_GRAVE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_MUSHROOM_GRAVE = TGStructures.MUSHROOM_GRAVE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_LARGE_BIRCH_TREE = TGStructures.LARGE_BIRCH_TREE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_MEDIUM_WALLED_GRAVEYARD = TGStructures.MEDIUM_WALLED_GRAVEYARD.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_LARGE_WALLED_GRAVEYARD = TGStructures.LARGE_WALLED_GRAVEYARD.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_HAUNTED_HOUSE = TGStructures.HAUNTED_HOUSE.get().configured(IFeatureConfig.NONE);

    public static void registerConfiguredStructures() {

        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;

        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "configured_small_walled_graveyard"), CONFIGURED_SMALL_WALLED_GRAVEYARD);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "configured_small_walled_graveyard_savanna"), CONFIGURED_SMALL_WALLED_GRAVEYARD_SAVANNA);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "configured_small_walled_graveyard_desert"), CONFIGURED_SMALL_WALLED_GRAVEYARD_DESERT);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "configured_small_grave"), CONFIGURED_SMALL_GRAVE);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "configured_desert_small_grave"), CONFIGURED_SMALL_DESERT_GRAVE);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "configured_savanna_small_grave"), CONFIGURED_SMALL_SAVANNA_GRAVE);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "configured_mountain_small_grave"), CONFIGURED_SMALL_MOUNTAIN_GRAVE);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "configured_mushroom_grave"), CONFIGURED_MUSHROOM_GRAVE);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "configured_large_birch_tree"), CONFIGURED_LARGE_BIRCH_TREE);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "configured_medium_walled_graveyard"), CONFIGURED_MEDIUM_WALLED_GRAVEYARD);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "configured_large_walled_graveyard"), CONFIGURED_LARGE_WALLED_GRAVEYARD);
        Registry.register(registry, new ResourceLocation(TheGraveyard.MOD_ID, "configured_haunted_house"), CONFIGURED_HAUNTED_HOUSE);



    }
}
