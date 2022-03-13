package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.config.StructureConfigEntry;
import com.finallion.graveyard.init.TGConfiguredStructureFeatures;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Arrays;

public class MediumGraveyardStructure extends AbstractGraveyardStructure {

    public MediumGraveyardStructure(Codec<JigsawConfiguration> codec) {
        super(new StructureConfigEntry(18, 16,1690192399,
                        Arrays.asList("#" + Biome.BiomeCategory.FOREST.getName()),
                        Arrays.asList("minecraft:dark_forest", "minecraft:birch_forest", "minecraft:old_growth_birch_forest", "graveyard_biomes:haunted_lakes", "graveyard_biomes:haunted_forest"),
                        Arrays.asList("#minecraft", "#graveyard_biomes"), true), // blacklist birch and dark forest
                30, "medium_graveyard");
    }


    @Override
    public ConfiguredStructureFeature<?, ?> getStructureFeature() {
        return TGConfiguredStructureFeatures.MEDIUM_GRAVEYARD_STRUCTURE_CONFIG.m_203334_();
    }

    public static class MediumGraveyardGenerator {
        public static final Holder<StructureTemplatePool> STARTING_POOL;

        public MediumGraveyardGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.m_211103_(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "medium_graveyard"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":medium_graveyard/medium_graveyard_01"), 1),
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":medium_graveyard/medium_graveyard_02"), 1)
            ), StructureTemplatePool.Projection.RIGID));
        }
    }

}
