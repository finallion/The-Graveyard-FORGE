package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.world.features.surfaceFeatures.*;
import com.finallion.graveyard.world.features.surfaceFeatures.coralFeatures.DeadCoralClawFeature;
import com.finallion.graveyard.world.features.surfaceFeatures.coralFeatures.DeadCoralMushroomFeature;
import com.finallion.graveyard.world.features.surfaceFeatures.coralFeatures.DeadCoralTreeFeature;
import com.finallion.graveyard.world.features.trees.*;
import com.finallion.graveyard.world.features.trees.config.TGTreeFeatureConfig;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGConfiguredFeatures {
    public static List<Feature<?>> features = new ArrayList<>();

    // tree features
    public static final Feature<TGTreeFeatureConfig> SMALL_SPRUCE_TREE_01 = registerFeatures("small_spruce_tree_01", new SmallSpruceTree01(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> SMALL_SPRUCE_TREE_02 = registerFeatures("small_spruce_tree_02", new SmallSpruceTree02(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> SMALL_SPRUCE_TREE_03 = registerFeatures("small_spruce_tree_03", new SmallSpruceTree03(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> SMALL_SPRUCE_TREE_04 = registerFeatures("small_spruce_tree_04", new SmallSpruceTree04(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> SMALL_SPRUCE_TREE_05 = registerFeatures("small_spruce_tree_05", new SmallSpruceTree05(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> SMALL_SPRUCE_TREE_06 = registerFeatures("small_spruce_tree_06", new SmallSpruceTree06(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> SMALL_BENT_SPRUCE_TREE_01 = registerFeatures("small_bent_spruce_tree_01", new SmallBentSpruceTree01(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> FALLEN_SPRUCE_TREE = registerFeatures("fallen_spruce_tree", new FallenSpruceTree(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> LARGE_BENT_SPRUCE_TREE_01 = registerFeatures("large_bent_spruce_tree_01", new LargeBentSpruceTree01(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> LARGE_BENT_SPRUCE_TREE_02 = registerFeatures("large_bent_spruce_tree_02", new LargeBentSpruceTree02(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> LARGE_SPRUCE_TREE_01 = registerFeatures("large_spruce_tree_01", new LargeSpruceTree01(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> LARGE_SPRUCE_TREE_02 = registerFeatures("large_spruce_tree_02", new LargeSpruceTree02(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> LARGE_SPRUCE_TREE_03 = registerFeatures("large_spruce_tree_03", new LargeSpruceTree03(TGTreeFeatureConfig.CODEC));

    // other features
    public static final Feature<NoneFeatureConfiguration> MOSS_CARPET_FEATURE = registerFeatures("moss_carpet_feature", new MossCarpetFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> COBWEB_FEATURE = registerFeatures("cobweb_feature", new CobwebFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> BUSH_FEATURE = registerFeatures("bush_feature", new BushFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> GRAVESTONE_FEATURE = registerFeatures("gravestone_feature", new GraveFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> SOUL_LIGHT_FEATURE = registerFeatures("soul_light_feature", new SoulLightFeature(NoneFeatureConfiguration.CODEC));
    private static final Feature<NoneFeatureConfiguration> FALLEN_TREE_FEATURE = registerFeatures("fallen_tree_feature", new FallenTreeFeature(NoneFeatureConfiguration.CODEC));
    private static final Feature<NoneFeatureConfiguration> DEAD_CORAL_TREE_FEATURE = registerFeatures("dead_coral_tree_feature", new DeadCoralTreeFeature(NoneFeatureConfiguration.CODEC));
    private static final Feature<NoneFeatureConfiguration> DEAD_CORAL_CLAW_FEATURE = registerFeatures("dead_coral_claw_feature", new DeadCoralClawFeature(NoneFeatureConfiguration.CODEC));
    private static final Feature<NoneFeatureConfiguration> DEAD_CORAL_MUSHROOM_FEATURE = registerFeatures("dead_coral_mushroom_feature", new DeadCoralMushroomFeature(NoneFeatureConfiguration.CODEC));
    private static final Feature<NoneFeatureConfiguration> DEAD_CORAL_PATCH_FEATURE = registerFeatures("dead_coral_patch_feature", new DeadCoralPatchFeature(NoneFeatureConfiguration.CODEC));

    // configured features
    public static final ConfiguredFeature<?, ?> MOSS_CARPET_CONFIG = registerConfiguredFeatures("moss_carpet_config", MOSS_CARPET_FEATURE.configured(new NoneFeatureConfiguration()));
    public static final ConfiguredFeature<?, ?> COBWEB_CONFIG = registerConfiguredFeatures("cobweb_config", COBWEB_FEATURE.configured(new NoneFeatureConfiguration()));
    public static final ConfiguredFeature<?, ?> BUSH_CONFIG = registerConfiguredFeatures("bush_config", BUSH_FEATURE.configured(new NoneFeatureConfiguration()));
    public static final ConfiguredFeature<?, ?> GRAVESTONE_CONFIG = registerConfiguredFeatures("gravestone_config", GRAVESTONE_FEATURE.configured(new NoneFeatureConfiguration()));
    public static final ConfiguredFeature<?, ?> SOUL_LIGHT_CONFIG = registerConfiguredFeatures("soul_light_config", SOUL_LIGHT_FEATURE.configured(new NoneFeatureConfiguration()));
    public static final ConfiguredFeature<?, ?> FALLEN_TREE_CONFIG = registerConfiguredFeatures("fallen_tree_config", FALLEN_TREE_FEATURE.configured(new NoneFeatureConfiguration()));
    public static final ConfiguredFeature<?, ?> DEAD_CORAL_TREE_CONFIG = registerConfiguredFeatures("dead_coral_tree_config", DEAD_CORAL_TREE_FEATURE.configured(new NoneFeatureConfiguration()));
    public static final ConfiguredFeature<?, ?> DEAD_CORAL_CLAW_CONFIG = registerConfiguredFeatures("dead_coral_claw_config", DEAD_CORAL_CLAW_FEATURE.configured(new NoneFeatureConfiguration()));
    public static final ConfiguredFeature<?, ?> DEAD_CORAL_MUSHROOM_CONFIG = registerConfiguredFeatures("dead_coral_mushroom_config", DEAD_CORAL_MUSHROOM_FEATURE.configured(new NoneFeatureConfiguration()));
    public static final ConfiguredFeature<?, ?> DEAD_CORAL_PATCH_CONFIG = registerConfiguredFeatures("dead_coral_patch_config", DEAD_CORAL_PATCH_FEATURE.configured(new NoneFeatureConfiguration()));

    // placed features
    public static PlacedFeature MOSS_CARPET_PLACED_FEATURE = registerPlacedFeatures("moss_carpet_placed_feature", MOSS_CARPET_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(100)));
    public static PlacedFeature COBWEB_PLACED_FEATURE = registerPlacedFeatures("cobweb_placed_feature", COBWEB_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(40)));
    public static PlacedFeature BUSH_PLACED_FEATURE = registerPlacedFeatures("bush_placed_feature", BUSH_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(35)));
    public static PlacedFeature GRAVESTONE_PLACED_FEATURE = registerPlacedFeatures("gravestone_placed_feature", GRAVESTONE_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(1)));
    public static PlacedFeature SOUL_LIGHT_PLACED_FEATURE = registerPlacedFeatures("soul_light_placed_feature", SOUL_LIGHT_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(100)));
    public static PlacedFeature FALLEN_TREE_PLACED_FEATURE = registerPlacedFeatures("fallen_tree_placed_feature", FALLEN_TREE_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(20)));
    public static PlacedFeature DEAD_CORAL_TREE_PLACED_FEATURE = registerPlacedFeatures("dead_coral_tree_placed_feature", DEAD_CORAL_TREE_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(20)));
    public static PlacedFeature DEAD_CORAL_CLAW_PLACED_FEATURE = registerPlacedFeatures("dead_coral_claw_placed_feature", DEAD_CORAL_CLAW_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(20)));
    public static PlacedFeature DEAD_CORAL_MUSHROOM_PLACED_FEATURE = registerPlacedFeatures("dead_coral_mushroom_placed_feature", DEAD_CORAL_MUSHROOM_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(20)));
    public static PlacedFeature DEAD_CORAL_PATCH_PLACED_FEATURE = registerPlacedFeatures("dead_coral_patch_placed_feature", DEAD_CORAL_PATCH_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(35)));


    // configured tree feature collections
    public static final ConfiguredFeature<?, ?> HAUNTED_FOREST_TREES = registerConfiguredFeatures("haunted_forest_trees", Feature.RANDOM_SELECTOR.configured(
            new RandomFeatureConfiguration(ImmutableList.of(
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.25F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.25F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.25F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.25F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.375F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.375F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_04.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_04.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_05.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_05.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_06.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_06.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.FALLEN_SPRUCE_TREE.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.FALLEN_SPRUCE_TREE.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.25F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.25F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F)),
                    TreePlacements.SPRUCE_CHECKED)
    ));

    public static final ConfiguredFeature<?, ?> ERODED_HAUNTED_FOREST_TREES = registerConfiguredFeatures("eroded_haunted_forest_trees", Feature.RANDOM_SELECTOR.configured(
            new RandomFeatureConfiguration(ImmutableList.of(
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.FALLEN_SPRUCE_TREE.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.075F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.FALLEN_SPRUCE_TREE.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.075F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.5F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.5F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).placed(), 0.1F)),
                    TreePlacements.SPRUCE_CHECKED)
    ));

    public static final ConfiguredFeature<?, ?> ANCIENT_DEAD_REEF_VEGETATION = registerConfiguredFeatures("ancient_dead_reef_vegetation", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SimpleRandomFeatureConfiguration(ImmutableList.of(() -> {
        return DEAD_CORAL_TREE_FEATURE.configured(FeatureConfiguration.NONE).placed();
    }, () -> {
        return DEAD_CORAL_CLAW_FEATURE.configured(FeatureConfiguration.NONE).placed();
    }, () -> {
        return DEAD_CORAL_MUSHROOM_FEATURE.configured(FeatureConfiguration.NONE).placed();
    }))));

    public static PlacedFeature ERODED_HAUNTED_FOREST_TREES_PLACED_FEATURE = registerPlacedFeatures("eroded_haunted_forest_trees_placed_feature", ERODED_HAUNTED_FOREST_TREES.placed(treePlacement(PlacementUtils.countExtra(10, 0.1F, 1))));
    public static PlacedFeature HAUNTED_FOREST_TREES_PLACED_FEATURE = registerPlacedFeatures("haunted_forest_trees_placed_feature", HAUNTED_FOREST_TREES.placed(treePlacement(PlacementUtils.countExtra(15, 0.1F, 1))));
    public static PlacedFeature ANCIENT_DEAD_CORAL_REEF_WATER_PLACED_FEATURE = registerPlacedFeatures("ancient_dead_coral_reef_water_placed_feature", ANCIENT_DEAD_REEF_VEGETATION.placed(NoiseBasedCountPlacement.of(20, 400.0D, 0.0D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static PlacedFeature ANCIENT_DEAD_CORAL_REEF_PLACED_FEATURE = registerPlacedFeatures("ancient_dead_coral_reef_placed_feature", ANCIENT_DEAD_REEF_VEGETATION.placed((PlacementUtils.countExtra(7, 0.1F, 1))));



    public static <C extends FeatureConfiguration, F extends Feature<C>> F registerFeatures(String id, F feature) {
        ResourceLocation resourceLocation = new ResourceLocation(TheGraveyard.MOD_ID, id);

        feature.setRegistryName(resourceLocation);
        features.add(feature);
        return feature;
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>, CF extends ConfiguredFeature<FC, F>> CF registerConfiguredFeatures(String id, CF configuredFeature) {
        ResourceLocation resourceLocation = new ResourceLocation(TheGraveyard.MOD_ID, id);

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, resourceLocation, configuredFeature);
        return configuredFeature;
    }

    public static <PF extends PlacedFeature> PF registerPlacedFeatures(String id, PF placedFeature) {
        ResourceLocation resourceLocation = new ResourceLocation(TheGraveyard.MOD_ID, id);

        Registry.register(BuiltinRegistries.PLACED_FEATURE, resourceLocation, placedFeature);
        return placedFeature;
    }


    @SubscribeEvent
    public static void registerFeaturesAndConfigFeatures(RegistryEvent.Register<Feature<?>> event) {
        features.forEach(feature -> event.getRegistry().register(feature));
    }
}
