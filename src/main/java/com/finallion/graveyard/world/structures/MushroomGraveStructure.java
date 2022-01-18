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

public class MushroomGraveStructure extends AbstractGraveyardStructure {

    public MushroomGraveStructure(Codec<JigsawConfiguration> codec) {
        super(codec, new StructureConfigEntry(24, 18,
                Biome.BiomeCategory.MUSHROOM.getName(),
                Biome.BiomeCategory.JUNGLE.getName(),
                Biome.BiomeCategory.SWAMP.getName()
                ), 7, 379123039, MushroomGraveGenerator.STARTING_POOL, "mushroom_grave");
    }


    @Override
    public ConfiguredStructureFeature<?, ?> getStructureFeature() {
        return TGConfiguredStructures.MUSHROOM_GRAVE_STRUCTURE_CONFIG;
    }

    public static class MushroomGraveGenerator {
        public static final StructureTemplatePool STARTING_POOL;

        public MushroomGraveGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "mushroom_grave"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":mushroom_grave/mushroom_grave_01"), 1),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":mushroom_grave/mushroom_grave_02"), 1),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":mushroom_grave/mushroom_grave_03"), 1)
            ), StructureTemplatePool.Projection.RIGID));
        }
    }

}
