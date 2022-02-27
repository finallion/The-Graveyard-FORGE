package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.StructureConfigEntry;
import com.finallion.graveyard.init.TGConfiguredStructures;
import com.finallion.graveyard.init.TGEntities;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
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
import net.minecraftforge.common.util.Lazy;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MediumGraveyardStructure extends AbstractGraveyardStructure {

    public MediumGraveyardStructure(Codec<JigsawConfiguration> codec) {
        super(codec, new StructureConfigEntry(18, 16,
                        Arrays.asList("#" + Biome.BiomeCategory.FOREST.getName()),
                        Arrays.asList("minecraft:dark_forest", "minecraft:birch_forest", "minecraft:old_growth_birch_forest", "graveyard_biomes:haunted_lakes", "graveyard_biomes:haunted_forest"), Arrays.asList("#minecraft", "#graveyard_biomes"), true), // blacklist birch and dark forest
                30, 1690192399, MediumGraveyardGenerator.STARTING_POOL, "medium_graveyard");
    }


    @Override
    public ConfiguredStructureFeature<?, ?> getStructureFeature() {
        return TGConfiguredStructures.MEDIUM_GRAVEYARD_STRUCTURE_CONFIG;
    }

    public static class MediumGraveyardGenerator {
        public static final StructureTemplatePool STARTING_POOL;

        public MediumGraveyardGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "medium_graveyard"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":medium_graveyard/medium_graveyard_01"), 1),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":medium_graveyard/medium_graveyard_02"), 1)
            ), StructureTemplatePool.Projection.RIGID));
        }
    }

}
