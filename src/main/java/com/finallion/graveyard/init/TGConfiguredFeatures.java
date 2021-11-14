package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.biomes.features.surfaceFeatures.*;
import com.finallion.graveyard.biomes.features.trees.*;
import com.finallion.graveyard.biomes.features.trees.config.TGTreeFeatureConfig;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;

public class TGConfiguredFeatures {

    public static List<Feature<?>> features = new ArrayList<>();

    // tree features
    public static final Feature<TGTreeFeatureConfig> SMALL_SPRUCE_TREE_01 = registerFeature("small_spruce_tree_01", new SmallSpruceTree01(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> SMALL_SPRUCE_TREE_02 = registerFeature("small_spruce_tree_02", new SmallSpruceTree02(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> SMALL_SPRUCE_TREE_03 = registerFeature("small_spruce_tree_03", new SmallSpruceTree03(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> SMALL_SPRUCE_TREE_04 = registerFeature("small_spruce_tree_04", new SmallSpruceTree04(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> SMALL_SPRUCE_TREE_05 = registerFeature("small_spruce_tree_05", new SmallSpruceTree05(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> SMALL_SPRUCE_TREE_06 = registerFeature("small_spruce_tree_06", new SmallSpruceTree06(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> SMALL_BENT_SPRUCE_TREE_01 = registerFeature("small_bent_spruce_tree_01", new SmallBentSpruceTree01(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> FALLEN_SPRUCE_TREE = registerFeature("fallen_spruce_tree", new FallenSpruceTree(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> LARGE_BENT_SPRUCE_TREE_01 = registerFeature("large_bent_spruce_tree_01", new LargeBentSpruceTree01(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> LARGE_BENT_SPRUCE_TREE_02 = registerFeature("large_bent_spruce_tree_02", new LargeBentSpruceTree02(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> LARGE_SPRUCE_TREE_01 = registerFeature("large_spruce_tree_01", new LargeSpruceTree01(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> LARGE_SPRUCE_TREE_02 = registerFeature("large_spruce_tree_02", new LargeSpruceTree02(TGTreeFeatureConfig.CODEC));
    public static final Feature<TGTreeFeatureConfig> LARGE_SPRUCE_TREE_03 = registerFeature("large_spruce_tree_03", new LargeSpruceTree03(TGTreeFeatureConfig.CODEC));

    // other features
    public static final Feature<NoFeatureConfig> MOSS_CARPET_FEATURE = registerFeature("moss_carpet_feature", new MossCarpetFeature(NoFeatureConfig.CODEC));
    public static final Feature<NoFeatureConfig> COBWEB_FEATURE = registerFeature("cobweb_feature", new CobwebFeature(NoFeatureConfig.CODEC));
    public static final Feature<NoFeatureConfig> BUSH_FEATURE = registerFeature("bush_feature", new BushFeature(NoFeatureConfig.CODEC));
    public static final Feature<NoFeatureConfig> GRAVESTONE_FEATURE = registerFeature("gravestone_feature", new GraveFeature(NoFeatureConfig.CODEC));
    public static final Feature<NoFeatureConfig> SOUL_LIGHT_FEATURE = registerFeature("soul_light_feature", new SoulLightFeature(NoFeatureConfig.CODEC));

    // configured features
    public static final ConfiguredFeature<?, ?> MOSS_CARPET_CONFIG = registerConfiguredFeatures("moss_carpet_feature_config", MOSS_CARPET_FEATURE.configured(new NoFeatureConfig()).decorated(Features.Placements.HEIGHTMAP.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(100, 0.1F, 1)))));
    public static final ConfiguredFeature<?, ?> COBWEB_CONFIG = registerConfiguredFeatures("cobweb_feature_config", COBWEB_FEATURE.configured(new NoFeatureConfig()).decorated(Features.Placements.HEIGHTMAP.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(60, 0.1F, 1)))));
    public static final ConfiguredFeature<?, ?> BUSH_CONFIG = registerConfiguredFeatures("bush_feature_config", BUSH_FEATURE.configured(new NoFeatureConfig()).decorated(Features.Placements.HEIGHTMAP.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(35, 0.1F, 1)))));
    public static final ConfiguredFeature<?, ?> GRAVESTONE_CONFIG = registerConfiguredFeatures("gravestone_feature_config", GRAVESTONE_FEATURE.configured(new NoFeatureConfig()).decorated(Features.Placements.HEIGHTMAP.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.1F, 1)))));
    public static final ConfiguredFeature<?, ?> SOUL_LIGHT_CONFIG = registerConfiguredFeatures("soul_light_feature_config", SOUL_LIGHT_FEATURE.configured(new NoFeatureConfig()).decorated(Features.Placements.HEIGHTMAP.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(100, 0.1F, 1)))));



    // configured tree feature collections
    public static final ConfiguredFeature<?, ?> HAUNTED_FOREST_TREES = registerConfiguredFeatures("haunted_forest_trees", Feature.RANDOM_SELECTOR.configured(
            new MultipleRandomFeatureConfig(
                    ImmutableList.of(
                            TGConfiguredFeatures.SMALL_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.25F),
                            TGConfiguredFeatures.SMALL_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.25F),
                            TGConfiguredFeatures.SMALL_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.375F),
                            TGConfiguredFeatures.SMALL_SPRUCE_TREE_04.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.SMALL_SPRUCE_TREE_05.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.SMALL_SPRUCE_TREE_06.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.FALLEN_SPRUCE_TREE.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.LARGE_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.25F),
                            TGConfiguredFeatures.LARGE_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.LARGE_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.SMALL_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.25F),
                            TGConfiguredFeatures.SMALL_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.25F),
                            TGConfiguredFeatures.SMALL_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.375F),
                            TGConfiguredFeatures.SMALL_SPRUCE_TREE_04.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.SMALL_SPRUCE_TREE_05.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.SMALL_SPRUCE_TREE_06.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.FALLEN_SPRUCE_TREE.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.LARGE_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.25F),
                            TGConfiguredFeatures.LARGE_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.LARGE_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F)
                            ),
                    Features.SPRUCE))
            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
            .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(17, 0.1F, 1))));

    public static final ConfiguredFeature<?, ?> ERODED_HAUNTED_FOREST_TREES = registerConfiguredFeatures("eroded_haunted_forest_trees", Feature.RANDOM_SELECTOR.configured(
            new MultipleRandomFeatureConfig(
                    ImmutableList.of(
                            TGConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.FALLEN_SPRUCE_TREE.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.075F),
                            TGConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.LARGE_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.5F),
                            TGConfiguredFeatures.LARGE_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.LARGE_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),

                            TGConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.FALLEN_SPRUCE_TREE.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.075F),
                            TGConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.LARGE_SPRUCE_TREE_01.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.5F),
                            TGConfiguredFeatures.LARGE_SPRUCE_TREE_02.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F),
                            TGConfiguredFeatures.LARGE_SPRUCE_TREE_03.configured(new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())).weighted(0.1F)
                    ),
                    Features.SPRUCE))
            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
            .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(17, 0.1F, 1))));




    public static <C extends IFeatureConfig, F extends Feature<C>> F registerFeature(String id, F feature) {
        ResourceLocation resourceLocation = new ResourceLocation(TheGraveyard.MOD_ID, id);

        feature.setRegistryName(resourceLocation);
        features.add(feature);
        return feature;
    }

    public static <FC extends IFeatureConfig, F extends Feature<FC>, CF extends ConfiguredFeature<FC, F>> CF registerConfiguredFeatures(String id, CF configuredFeature) {
        ResourceLocation resourceLocation = new ResourceLocation(TheGraveyard.MOD_ID, id);

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, resourceLocation, configuredFeature);
        return configuredFeature;
    }


}
