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

public class SmallDesertGraveyardStructure extends AbstractGraveyardStructure {

    public SmallDesertGraveyardStructure(Codec<JigsawConfiguration> codec) {
        super(new StructureConfigEntry(32, 28, 598017285,
                        Arrays.asList("minecraft:desert"),
                        Collections.emptyList(), 20, 3, false),
                "small_desert_graveyard");
    }

    @Override
    public ConfiguredStructureFeature<?, ?> getStructureFeature() {
        return TGConfiguredStructureFeatures.SMALL_DESERT_GRAVEYARD_STRUCTURE_CONFIG.m_203334_();
    }

    public static class SmallDesertGraveyardGenerator {
        public static final Holder<StructureTemplatePool> STARTING_POOL;

        public SmallDesertGraveyardGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.m_211103_(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "small_desert_graveyard"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":small_desert_graveyard/small_desert_graveyard_01"), 1)), StructureTemplatePool.Projection.RIGID));
        }
    }

}
