package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.StructureConfigEntry;
import com.finallion.graveyard.init.TGConfiguredStructures;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.JigsawFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.Optional;

public class SmallDesertGraveStructure extends AbstractGraveyardStructure {

    public SmallDesertGraveStructure(Codec<JigsawConfiguration> codec) {
        super(codec, new StructureConfigEntry(20, 16,
                Biome.BiomeCategory.DESERT.getName()), 4, 681236914, SmallDesertGraveGenerator.STARTING_POOL, "small_desert_grave");
    }


    @Override
    public ConfiguredStructureFeature<?, ?> getStructureFeature() {
        return TGConfiguredStructures.SMALL_DESERT_GRAVE_STRUCTURE_CONFIG;
    }

    public static class SmallDesertGraveGenerator {
        public static final StructureTemplatePool STARTING_POOL;

        public SmallDesertGraveGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "small_desert_grave"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":small_desert_grave/small_desert_grave_01"), 1),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":small_desert_grave/small_desert_grave_02"), 1),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":small_desert_grave/small_desert_grave_03"), 1),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":small_desert_grave/small_desert_grave_04"), 1),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":small_desert_grave/small_desert_grave_05"), 1),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":small_desert_grave/small_desert_grave_06"), 1),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":small_desert_grave/small_desert_grave_07"), 1),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":small_desert_grave/small_desert_grave_08"), 1),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":small_desert_grave/small_desert_grave_09"), 1),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":small_desert_grave/small_desert_grave_10"), 1),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":small_desert_grave/small_desert_grave_11"), 1),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":small_desert_grave/small_desert_grave_12"), 1),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":small_desert_grave/small_desert_grave_13"), 1)
            ), StructureTemplatePool.Projection.RIGID));
        }
    }

}
