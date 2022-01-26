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

    // configured features registry keys
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSS_CARPET_FEATURE_KEY = ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, new ResourceLocation(TheGraveyard.MOD_ID, "moss_carpet_feature"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> COBWEB_FEATURE_KEY = ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, new ResourceLocation(TheGraveyard.MOD_ID, "cobweb_feature"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> BUSH_FEATURE_KEY = ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, new ResourceLocation(TheGraveyard.MOD_ID, "bush_feature"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRAVESTONE_FEATURE_KEY = ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, new ResourceLocation(TheGraveyard.MOD_ID, "gravestone_feature"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOUL_LIGHT_FEATURE_KEY = ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, new ResourceLocation(TheGraveyard.MOD_ID, "soul_light_feature"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_TREE_FEATURE_KEY = ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, new ResourceLocation(TheGraveyard.MOD_ID, "fallen_tree_feature"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_CORAL_TREE_FEATURE_KEY = ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, new ResourceLocation(TheGraveyard.MOD_ID, "dead_coral_tree_feature"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_CORAL_CLAW_FEATURE_KEY = ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, new ResourceLocation(TheGraveyard.MOD_ID, "dead_coral_claw_feature"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_CORAL_MUSHROOM_FEATURE_KEY = ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, new ResourceLocation(TheGraveyard.MOD_ID, "dead_coral_mushroom_feature"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_CORAL_PATCH_FEATURE_KEY = ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, new ResourceLocation(TheGraveyard.MOD_ID, "dead_coral_patch_feature"));

    // configured features
    public static final ConfiguredFeature<?, ?> MOSS_CARPET_CONFIG = MOSS_CARPET_FEATURE.configured(new NoneFeatureConfiguration());
    public static final ConfiguredFeature<?, ?> COBWEB_CONFIG = COBWEB_FEATURE.configured(new NoneFeatureConfiguration());
    public static final ConfiguredFeature<?, ?> BUSH_CONFIG = BUSH_FEATURE.configured(new NoneFeatureConfiguration());
    public static final ConfiguredFeature<?, ?> GRAVESTONE_CONFIG = GRAVESTONE_FEATURE.configured(new NoneFeatureConfiguration());
    public static final ConfiguredFeature<?, ?> SOUL_LIGHT_CONFIG = SOUL_LIGHT_FEATURE.configured(new NoneFeatureConfiguration());
    public static final ConfiguredFeature<?, ?> FALLEN_TREE_CONFIG = FALLEN_TREE_FEATURE.configured(new NoneFeatureConfiguration());
    public static final ConfiguredFeature<?, ?> DEAD_CORAL_TREE_CONFIG = DEAD_CORAL_TREE_FEATURE.configured(new NoneFeatureConfiguration());
    public static final ConfiguredFeature<?, ?> DEAD_CORAL_CLAW_CONFIG = DEAD_CORAL_CLAW_FEATURE.configured(new NoneFeatureConfiguration());
    public static final ConfiguredFeature<?, ?> DEAD_CORAL_MUSHROOM_CONFIG = DEAD_CORAL_MUSHROOM_FEATURE.configured(new NoneFeatureConfiguration());
    public static final ConfiguredFeature<?, ?> DEAD_CORAL_PATCH_CONFIG = DEAD_CORAL_PATCH_FEATURE.configured(new NoneFeatureConfiguration());

    // placed features
    public static PlacedFeature MOSS_CARPET_PLACED_FEATURE = MOSS_CARPET_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(100));
    public static PlacedFeature COBWEB_PLACED_FEATURE = COBWEB_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(40));
    public static PlacedFeature BUSH_PLACED_FEATURE = BUSH_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(35));
    public static PlacedFeature GRAVESTONE_PLACED_FEATURE = GRAVESTONE_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(1));
    public static PlacedFeature SOUL_LIGHT_PLACED_FEATURE = SOUL_LIGHT_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(100));
    public static PlacedFeature FALLEN_TREE_PLACED_FEATURE = FALLEN_TREE_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(20));
    public static PlacedFeature DEAD_CORAL_TREE_PLACED_FEATURE = DEAD_CORAL_TREE_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(20));
    public static PlacedFeature DEAD_CORAL_CLAW_PLACED_FEATURE = DEAD_CORAL_CLAW_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(20));
    public static PlacedFeature DEAD_CORAL_MUSHROOM_PLACED_FEATURE = DEAD_CORAL_MUSHROOM_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(20));
    public static PlacedFeature DEAD_CORAL_PATCH_PLACED_FEATURE = DEAD_CORAL_PATCH_CONFIG.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(35));

    // configured tree feature collections
    public static final ConfiguredFeature<?, ?> HAUNTED_FOREST_TREES = registerConfiguredFeatureCollections("haunted_forest_trees", Feature.RANDOM_SELECTOR.configured(
            new RandomFeatureConfiguration(ImmutableList.of(
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.25F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.25F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.25F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.25F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.375F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.375F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_04.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_04.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_05.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_05.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_06.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_SPRUCE_TREE_06.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.FALLEN_SPRUCE_TREE.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.FALLEN_SPRUCE_TREE.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.25F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.25F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F)),
                    TreePlacements.SPRUCE_CHECKED)
    ));

    public static final ConfiguredFeature<?, ?> ERODED_HAUNTED_FOREST_TREES = registerConfiguredFeatureCollections("eroded_haunted_forest_trees", Feature.RANDOM_SELECTOR.configured(
            new RandomFeatureConfiguration(ImmutableList.of(
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.FALLEN_SPRUCE_TREE.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.075F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.FALLEN_SPRUCE_TREE.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.075F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.5F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.5F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F),
                    new WeightedPlacedFeature(TGConfiguredFeatures.LARGE_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), 0.1F)),
                    TreePlacements.SPRUCE_CHECKED)
    ));

    public static final ConfiguredFeature<?, ?> ANCIENT_DEAD_REEF_VEGETATION = registerConfiguredFeatureCollections("ancient_dead_reef_vegetation", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SimpleRandomFeatureConfiguration(ImmutableList.of(() -> {
        return DEAD_CORAL_TREE_FEATURE.configured(FeatureConfiguration.NONE).placed();
    }, () -> {
        return DEAD_CORAL_CLAW_FEATURE.configured(FeatureConfiguration.NONE).placed();
    }, () -> {
        return DEAD_CORAL_MUSHROOM_FEATURE.configured(FeatureConfiguration.NONE).placed();
    }))));

    public static PlacedFeature ERODED_HAUNTED_FOREST_TREES_PLACED_FEATURE = ERODED_HAUNTED_FOREST_TREES.placed(PlacementUtils.countExtra(10, 0.1F, 1));
    public static PlacedFeature HAUNTED_FOREST_TREES_PLACED_FEATURE = HAUNTED_FOREST_TREES.placed(PlacementUtils.countExtra(15, 0.1F, 1));
    public static PlacedFeature ANCIENT_DEAD_CORAL_REEF_WATER_PLACED_FEATURE = ANCIENT_DEAD_REEF_VEGETATION.placed(NoiseBasedCountPlacement.of(20, 400.0D, 0.0D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
    public static PlacedFeature ANCIENT_DEAD_CORAL_REEF_PLACED_FEATURE = ANCIENT_DEAD_REEF_VEGETATION.placed((PlacementUtils.countExtra(7, 0.1F, 1)));


    public static void registerConfiguredFeatures() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, MOSS_CARPET_FEATURE_KEY.getRegistryName(), MOSS_CARPET_CONFIG);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, COBWEB_FEATURE_KEY.getRegistryName(), COBWEB_CONFIG);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, BUSH_FEATURE_KEY.getRegistryName(), BUSH_CONFIG);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, GRAVESTONE_FEATURE_KEY.getRegistryName(), GRAVESTONE_CONFIG);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, SOUL_LIGHT_FEATURE_KEY.getRegistryName(), SOUL_LIGHT_CONFIG);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, FALLEN_TREE_FEATURE_KEY.getRegistryName(), FALLEN_TREE_CONFIG);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, DEAD_CORAL_TREE_FEATURE_KEY.getRegistryName(), DEAD_CORAL_TREE_CONFIG);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, DEAD_CORAL_CLAW_FEATURE_KEY.getRegistryName(), DEAD_CORAL_CLAW_CONFIG);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, DEAD_CORAL_MUSHROOM_FEATURE_KEY.getRegistryName(), DEAD_CORAL_MUSHROOM_CONFIG);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, DEAD_CORAL_PATCH_FEATURE_KEY.getRegistryName(), DEAD_CORAL_PATCH_CONFIG);

        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(TheGraveyard.MOD_ID, "moss_carpet_placed_feature"), MOSS_CARPET_PLACED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(TheGraveyard.MOD_ID, "cobweb_placed_feature"), COBWEB_PLACED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(TheGraveyard.MOD_ID, "bush_placed_feature"), BUSH_PLACED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(TheGraveyard.MOD_ID, "gravestone_placed_feature"), GRAVESTONE_PLACED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(TheGraveyard.MOD_ID, "soul_light_placed_feature"), SOUL_LIGHT_PLACED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(TheGraveyard.MOD_ID, "fallen_tree_placed_feature"), FALLEN_TREE_PLACED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(TheGraveyard.MOD_ID, "dead_coral_tree_placed_feature"), DEAD_CORAL_TREE_PLACED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(TheGraveyard.MOD_ID, "dead_coral_claw_placed_feature"), DEAD_CORAL_CLAW_PLACED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(TheGraveyard.MOD_ID, "dead_coral_mushroom_placed_feature"), DEAD_CORAL_MUSHROOM_PLACED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(TheGraveyard.MOD_ID, "dead_coral_patch_placed_feature"), DEAD_CORAL_PATCH_PLACED_FEATURE);

        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(TheGraveyard.MOD_ID, "eroded_haunted_forest_trees_placed_feature"), ERODED_HAUNTED_FOREST_TREES_PLACED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(TheGraveyard.MOD_ID, "haunted_forest_trees_placed_feature"), HAUNTED_FOREST_TREES_PLACED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(TheGraveyard.MOD_ID, "ancient_dead_coral_reef_placed_feature"), ANCIENT_DEAD_CORAL_REEF_PLACED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(TheGraveyard.MOD_ID, "ancient_dead_coral_reef_water_placed_feature"), ANCIENT_DEAD_CORAL_REEF_WATER_PLACED_FEATURE);
    }


    public static <C extends FeatureConfiguration, F extends Feature<C>> F registerFeatures(String id, F feature) {
        ResourceLocation resourceLocation = new ResourceLocation(TheGraveyard.MOD_ID, id);

        feature.setRegistryName(resourceLocation);
        features.add(feature);
        return feature;
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>, CF extends ConfiguredFeature<FC, F>> CF registerConfiguredFeatureCollections(String id, CF configuredFeature) {
        ResourceLocation resourceLocation = new ResourceLocation(TheGraveyard.MOD_ID, id);

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, resourceLocation, configuredFeature);
        return configuredFeature;
    }


    @SubscribeEvent
    public static void registerFeaturesAndConfigFeatures(RegistryEvent.Register<Feature<?>> event) {
        features.forEach(feature -> event.getRegistry().register(feature));
    }
}
