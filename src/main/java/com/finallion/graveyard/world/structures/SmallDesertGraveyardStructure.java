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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SmallDesertGraveyardStructure extends AbstractGraveyardStructure {

    public SmallDesertGraveyardStructure(Codec<JigsawConfiguration> codec) {
        super(codec, new StructureConfigEntry(32, 28,
                Arrays.asList("#" + Biome.BiomeCategory.DESERT.getName()),
                Collections.emptyList(), Arrays.asList("#minecraft"), false),
                20, 598017285, SmallDesertGraveyardGenerator.STARTING_POOL, "small_desert_graveyard");
    }

    public static final Lazy<List<MobSpawnSettings.SpawnerData>> ILLAGER_SPAWNS = Lazy.of(() -> ImmutableList.of(
            new MobSpawnSettings.SpawnerData(EntityType.PILLAGER, 10, 1, 1),
            new MobSpawnSettings.SpawnerData(EntityType.VINDICATOR, 10, 1, 1)
    ));



    @Override
    public ConfiguredStructureFeature<?, ?> getStructureFeature() {
        return TGConfiguredStructures.SMALL_DESERT_GRAVEYARD_STRUCTURE_CONFIG;
    }

    public static class SmallDesertGraveyardGenerator {
        public static final StructureTemplatePool STARTING_POOL;

        public SmallDesertGraveyardGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "small_desert_graveyard"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":small_desert_graveyard/small_desert_graveyard_01"), 1)), StructureTemplatePool.Projection.RIGID));
        }
    }

}
