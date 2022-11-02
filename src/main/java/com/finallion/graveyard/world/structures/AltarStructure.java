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

public class AltarStructure extends AbstractGraveyardStructure {

    public AltarStructure(Codec<JigsawConfiguration> codec) {
        super(new StructureConfigEntry(30, 24, 1093123913,
                        Arrays.asList("minecraft:snowy_plains", "minecraft:ice_spikes"),
                        Collections.emptyList(), 7, 3, false), // only allow in birch forests
                "altar");
    }

    @Override
    public ConfiguredStructureFeature<?, ?> getStructureFeature() {
        return TGConfiguredStructureFeatures.ALTAR_STRUCTURE_CONFIG.m_203334_();
    }


    public static class AltarGenerator {
        public static final Holder<StructureTemplatePool> STARTING_POOL;

        public AltarGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.m_211103_(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "altar"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":altar/altar_01"), 1)), StructureTemplatePool.Projection.RIGID));
        }
    }

}
