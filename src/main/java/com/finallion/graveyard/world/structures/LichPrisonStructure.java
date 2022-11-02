package com.finallion.graveyard.world.structures;


import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.StructureConfigEntry;
import com.finallion.graveyard.init.TGConfiguredStructureFeatures;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Arrays;

public class LichPrisonStructure extends AbstractFloatingStructure {

    public LichPrisonStructure(Codec<JigsawConfiguration> codec) {
        super(new StructureConfigEntry(
                        30,
                        28,
                        258195719,
                        Arrays.asList(
                            "#minecraft:ocean"
                        ),
                        Arrays.asList(
                                "minecraft:cold_ocean",
                                "minecraft:deep_cold_ocean",
                                "minecraft:frozen_ocean",
                                "minecraft:deep_frozen_ocean"),
                        1, 1,
                        true), // only allow in dark forest and taigas
                "lich_prison");
    }


    @Override
    public ConfiguredStructureFeature<?, ?> getStructureFeature() {
        return TGConfiguredStructureFeatures.LICH_PRISON_STRUCTURE_CONFIG.m_203334_();
    }


    public static class LichPrisonGenerator {
        public static final Holder<StructureTemplatePool> STARTING_POOL;

        public LichPrisonGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.m_211103_(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "lich_prison"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":lich_prison/lich_prison"), 1)), StructureTemplatePool.Projection.RIGID));
        }
    }

}

