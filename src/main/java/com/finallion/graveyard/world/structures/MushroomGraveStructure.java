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
import java.util.Collections;

public class MushroomGraveStructure extends AbstractGraveyardStructure {

    public MushroomGraveStructure(Codec<JigsawConfiguration> codec) {
        super(new StructureConfigEntry(24, 18, 379123039,
                        Arrays.asList("#" + Biome.BiomeCategory.MUSHROOM.getName(), "#" + Biome.BiomeCategory.JUNGLE.getName(), "#" + Biome.BiomeCategory.SWAMP.getName()),
                        Collections.emptyList(), Arrays.asList("#minecraft", "#terralith"), false),
                7, "mushroom_grave");
    }


    @Override
    public ConfiguredStructureFeature<?, ?> getStructureFeature() {
        return TGConfiguredStructureFeatures.MUSHROOM_GRAVE_STRUCTURE_CONFIG.m_203334_();
    }

    public static class MushroomGraveGenerator {
        public static final Holder<StructureTemplatePool> STARTING_POOL;

        public MushroomGraveGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.m_211103_(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "mushroom_grave"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":mushroom_grave/mushroom_grave_01"), 1),
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":mushroom_grave/mushroom_grave_02"), 1),
                    Pair.of(StructurePoolElement.m_210507_(TheGraveyard.MOD_ID + ":mushroom_grave/mushroom_grave_03"), 1)
            ), StructureTemplatePool.Projection.RIGID));
        }
    }

}
