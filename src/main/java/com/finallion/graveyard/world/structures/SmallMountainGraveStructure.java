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

public class SmallMountainGraveStructure extends AbstractGraveyardStructure {

    public SmallMountainGraveStructure(Codec<JigsawConfiguration> codec) {
        super(new StructureConfigEntry(12, 8, 725689810,
                        Arrays.asList("#minecraft:mountain", "#minecraft:extreme_hills"),
                        Collections.emptyList(), 4, 3, false),
                "small_mountain_grave");
    }

    @Override
    public ConfiguredStructureFeature<?, ?> getStructureFeature() {
        return TGConfiguredStructureFeatures.SMALL_MOUNTAIN_GRAVE_STRUCTURE_CONFIG.m_203334_();
    }

    public static class SmallMountainGraveGenerator {
        public static final Holder<StructureTemplatePool> STARTING_POOL;

        public SmallMountainGraveGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.m_211103_(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "small_mountain_grave"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":small_mountain_grave/small_mountains_grave_01"), 1),
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":small_mountain_grave/small_mountains_grave_02"), 1),
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":small_mountain_grave/small_mountains_grave_03"), 1),
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":small_mountain_grave/small_mountains_grave_04"), 1),
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":small_mountain_grave/small_mountains_grave_05"), 1),
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":small_mountain_grave/small_mountains_grave_06"), 1),
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":small_mountain_grave/small_mountains_grave_07"), 1),
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":small_mountain_grave/small_mountains_grave_08"), 1),
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":small_mountain_grave/small_mountains_grave_09"), 1),
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":small_mountain_grave/small_mountains_grave_10"), 1),
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":small_mountain_grave/small_mountains_grave_11"), 1)
            ), StructureTemplatePool.Projection.RIGID));
        }
    }

}
