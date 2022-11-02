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

public class GiantMushroomStructure extends AbstractGraveyardStructure {

    public GiantMushroomStructure(Codec<JigsawConfiguration> codec) {
        super(new StructureConfigEntry(
                        20,
                        18,
                        365012356,
                        Arrays.asList("#forge:mushroom"),
                        Collections.emptyList(),
                        10, 3,
                        false),
               "giant_mushroom");
    }

    @Override
    public ConfiguredStructureFeature<?, ?> getStructureFeature() {
        return TGConfiguredStructureFeatures.GIANT_MUSHROOM_STRUCTURE_CONFIG.m_203334_();
    }

    public static class GiantMushroomGenerator {
        public static final Holder<StructureTemplatePool> STARTING_POOL;
        public static final Holder<StructureTemplatePool> BROWN_CAP;
        public static final Holder<StructureTemplatePool> RED_CAP;

        public GiantMushroomGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.m_211103_(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "giant_mushroom"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":giant_mushroom/giant_brown_mushroom_stem_01"), 1),
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":giant_mushroom/giant_red_mushroom_stem_01"), 1)
            ), StructureTemplatePool.Projection.RIGID));
            BROWN_CAP = Pools.m_211103_(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "giant_mushroom/brown_cap"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                    StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":giant_mushroom/brown_cap/giant_brown_mushroom_cap_01"), 1)), StructureTemplatePool.Projection.RIGID));
            RED_CAP = Pools.m_211103_(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "giant_mushroom/red_cap"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                    StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":giant_mushroom/red_cap/giant_red_mushroom_cap_01"), 1)), StructureTemplatePool.Projection.RIGID));

        }
    }

}
