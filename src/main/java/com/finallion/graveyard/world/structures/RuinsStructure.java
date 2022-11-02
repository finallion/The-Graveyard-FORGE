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
import java.util.Collections;

public class RuinsStructure extends AbstractGraveyardStructure {

    public RuinsStructure(Codec<JigsawConfiguration> codec) {
        super(new StructureConfigEntry(
                        20,
                        18,
                        467108394,
                        Arrays.asList(
                                "#minecraft:forest"
                        ),
                        Collections.emptyList(),
                        25, 3,
                        true), // only allow in dark forest and taigas
                "ruins");
    }


    @Override
    public ConfiguredStructureFeature<?, ?> getStructureFeature() {
        return TGConfiguredStructureFeatures.LICH_PRISON_STRUCTURE_CONFIG.m_203334_();
    }

    public static class RuinsGenerator {
        public static final Holder<StructureTemplatePool> STARTING_POOL;

        public RuinsGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.m_211103_(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "ruins"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":ruins/ruins_01"), 1),
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":ruins/ruins_02"), 1),
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":ruins/ruins_03"), 1)
            ), StructureTemplatePool.Projection.RIGID));
        }
    }

}

