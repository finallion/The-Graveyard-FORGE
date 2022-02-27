package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.StructureConfigEntry;
import com.finallion.graveyard.init.TGConfiguredStructures;
import com.finallion.graveyard.init.TGEntities;
import com.finallion.graveyard.init.TGProcessors;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
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

public class LargeGraveyardStructure extends AbstractGraveyardStructure {

    public LargeGraveyardStructure(Codec<JigsawConfiguration> codec) {
        super(codec, new StructureConfigEntry(12, 10,
                        Arrays.asList("#" + Biome.BiomeCategory.FOREST.getName(), "#" + Biome.BiomeCategory.TAIGA.getName()),
                        Arrays.asList("minecraft:forest", "minecraft:flower_forest", "minecraft:birch_forest", "minecraft:old_growth_birch_forest", "minecraft:windswept_forest", "graveyard_biomes:eroded_haunted_forest", "graveyard_biomes:haunted_lakes"), Arrays.asList("#minecraft", "#graveyard_biomes"), true), // only allow in dark forest and taigas
                50, 304812394, LargeGraveyardGenerator.STARTING_POOL, "large_graveyard");
    }


    @Override
    public ConfiguredStructureFeature<?, ?> getStructureFeature() {
        return TGConfiguredStructures.LARGE_GRAVEYARD_STRUCTURE_CONFIG;
    }

    public static class LargeGraveyardGenerator {
        public static final StructureTemplatePool STARTING_POOL;
        public static final StructureTemplatePool BRANCH_POOL;
        public static final StructureTemplatePool FEATURE_POOL;
        public static final StructureTemplatePool CRYPT_POOL;
        public static final StructureTemplatePool STREET_POOL;


        public LargeGraveyardGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "large_walled_graveyard/start_pool"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/graveyard_entrance"), 1)), StructureTemplatePool.Projection.RIGID));
            BRANCH_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "large_walled_graveyard/branch_pool"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_large_grave_01"), 10),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_spider_den"), 6),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_lost_grave"), 10),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_graves_01"), 10),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_graves_02"), 10),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_graves_03"), 10),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_graves_04"), 10),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_graves_05"), 10),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_looted_graves_01"), 10),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_looted_graves_02"), 10),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_willow"), 6),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_large_grave_02"), 10),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_boulder_01"), 9),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_boulder_02"), 10),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_boulder_03"), 10),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_nature_01"), 11),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_nature_02"), 11),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_nature_03"), 11),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_nature_04"), 11)
            ), StructureTemplatePool.Projection.RIGID));
            FEATURE_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "large_walled_graveyard/feature_pool"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/feature_pool/wither_skeleton_mill"), 1),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/feature_pool/gallows_hill"), 1),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/feature_pool/walled_graves_with_crypt"), 2),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/walled_street_01"), 2),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/walled_street_02"), 2),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/walled_street_03"), 2)
            ), StructureTemplatePool.Projection.RIGID));
            CRYPT_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "large_walled_graveyard/small_crypt_pool"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/small_crypt_pool/small_crypt", TGProcessors.WATERLOGGED_LIST), 1)
            ), StructureTemplatePool.Projection.RIGID));
            STREET_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "large_walled_graveyard/street_pool"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_01"), 5),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_02"), 5),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_03"), 5),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_04"), 5),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_05"), 4),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_06"), 4),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_07"), 4),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_08"), 4),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_09"), 4),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/corner_01"), 7),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/corner_02"), 7),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/corner_03"), 7),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/corner_04"), 7),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/corner_05"), 7),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/corner_06"), 7),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/crossroad_01"), 6),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/crossroad_02"), 6),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_end_01"), 4),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_end_02"), 4),
                    Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_end_03"), 4)
            ), StructureTemplatePool.Projection.RIGID));
        }
    }

}
