package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.StructureConfigEntry;
import com.finallion.graveyard.init.TGConfiguredStructures;
import com.finallion.graveyard.init.TGProcessors;
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
import org.lwjgl.system.CallbackI;

import java.util.Arrays;
import java.util.Optional;

public class HauntedHouseStructure extends AbstractGraveyardStructure {

    public HauntedHouseStructure(Codec<JigsawConfiguration> codec) {
        super(codec, new StructureConfigEntry(25, 20,
                        Arrays.asList(Biome.BiomeCategory.FOREST.getName(), Biome.BiomeCategory.SWAMP.getName()),
                        Arrays.asList("forest", "flower_forest", "birch_forest", "old_growth_birch_forest", "windswept_forest")), // only allow in swamp and dark forest
                30, 451235912, HauntedHouseGenerator.STARTING_POOL, "haunted_house");
    }


    @Override
    public ConfiguredStructureFeature<?, ?> getStructureFeature() {
        return TGConfiguredStructures.HAUNTED_HOUSE_STRUCTURE_CONFIG;
    }

    public static class HauntedHouseGenerator {
        public static final StructureTemplatePool STARTING_POOL;
        public static final StructureTemplatePool DOWNSTAIRS;

        public HauntedHouseGenerator() {
        }

        public static void init() {
        }

        // change from legacy single to processed single
        // if water is removed from blockentities (like chests) it might give the error message "Tried access block entity before it was created"
        static {
            STARTING_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "haunted_house/start_pool"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                    StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":haunted_house/start_pool/haunted_house_01"), 1)), StructureTemplatePool.Projection.RIGID));
            DOWNSTAIRS = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "haunted_house/downstairs_pool"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                    StructurePoolElement.single(TheGraveyard.MOD_ID + ":haunted_house/downstairs_pool/haunted_house_downstairs", TGProcessors.WATERLOGGED_LIST), 1)), StructureTemplatePool.Projection.RIGID));


        }
    }

}
