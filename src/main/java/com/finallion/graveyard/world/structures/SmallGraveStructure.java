package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.TheGraveyard;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class SmallGraveStructure {

    public static class SmallGraveGenerator {
        public static final Holder<StructureTemplatePool> STARTING_POOL;

        public SmallGraveGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "small_grave"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/small_grave_1"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/small_grave_2"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/small_grave_3"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/small_grave_4"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/small_grave_5"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/small_grave_6"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/small_grave_7"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/small_grave_8"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/small_grave_9"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/small_grave_10"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/small_grave_11"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/small_grave_12"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/small_grave_13"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/small_grave_14"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/small_grave_15"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/small_grave_16"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/small_grave_17"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_grave/crashed_carriage"), 1)
            ), StructureTemplatePool.Projection.RIGID));
        }
    }

}

