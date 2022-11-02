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

public class MemorialTreeStructure extends AbstractGraveyardStructure {

    public MemorialTreeStructure(Codec<JigsawConfiguration> codec) {
        super(new StructureConfigEntry(14, 12, 529239621,
                        Arrays.asList("minecraft:old_growth_birch_forest", "minecraft:birch_forest", "terralith:birch_taiga"),
                        Collections.emptyList(), 10, 3, false), // only allow in birch forests
                "memorial_tree");
    }

    @Override
    public ConfiguredStructureFeature<?, ?> getStructureFeature() {
        return TGConfiguredStructureFeatures.MEMORIAL_TREE_STRUCTURE_CONFIG.m_203334_();
    }

    public static class MemorialTreeGenerator {
        public static final Holder<StructureTemplatePool> STARTING_POOL;

        public MemorialTreeGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.m_211103_(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "memorial_tree"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":memorial_tree/memorial_tree_01"), 1)), StructureTemplatePool.Projection.RIGID));
        }
    }

}
