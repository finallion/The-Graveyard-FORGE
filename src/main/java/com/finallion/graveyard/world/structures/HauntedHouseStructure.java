package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.config.StructureConfigEntry;
import com.finallion.graveyard.init.TGConfiguredStructureFeatures;
import com.finallion.graveyard.init.TGProcessors;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Arrays;

public class HauntedHouseStructure extends AbstractGraveyardStructure {

    public HauntedHouseStructure(Codec<JigsawConfiguration> codec) {
        super(new StructureConfigEntry(
                        25,
                        20,
                        451235912,
                        Arrays.asList("#" + Biome.BiomeCategory.FOREST.getName(), "#" + Biome.BiomeCategory.SWAMP.getName()),
                        Arrays.asList("minecraft:forest", "minecraft:flower_forest", "minecraft:birch_forest", "minecraft:old_growth_birch_forest", "minecraft:windswept_forest", "graveyard_biomes:haunted_forest"),
                        Arrays.asList("#minecraft", "#graveyard_biomes"),
                        false), // only allow in swamp and dark forest
                30,  "haunted_house");
    }


    @Override
    public ConfiguredStructureFeature<?, ?> getStructureFeature() {
        return TGConfiguredStructureFeatures.HAUNTED_HOUSE_STRUCTURE_CONFIG.m_203334_();
    }

    public static class HauntedHouseGenerator {
        public static final Holder<StructureTemplatePool> STARTING_POOL;
        public static final Holder<StructureTemplatePool> DOWNSTAIRS;

        public HauntedHouseGenerator() {
        }

        public static void init() {
        }

        // change from legacy single to processed single
        // if water is removed from blockentities (like chests) it might give the error message "Tried access block entity before it was created"
        static {
            STARTING_POOL = Pools.m_211103_(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "haunted_house/start_pool"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                    StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":haunted_house/start_pool/haunted_house_01"), 1)), StructureTemplatePool.Projection.RIGID));
            DOWNSTAIRS = Pools.m_211103_(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "haunted_house/downstairs_pool"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                    StructurePoolElement.m_210512_(TheGraveyard.MOD_ID + ":haunted_house/downstairs_pool/haunted_house_downstairs", TGProcessors.WATERLOGGED_LIST), 1)), StructureTemplatePool.Projection.RIGID));


        }
    }

}
