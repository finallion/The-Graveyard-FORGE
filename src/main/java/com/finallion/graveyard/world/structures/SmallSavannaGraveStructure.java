package com.finallion.graveyard.world.structures;


import com.finallion.graveyard.TheGraveyard;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class SmallSavannaGraveStructure {

    public static class SmallSavannaGraveGenerator {
        public static final Holder<StructureTemplatePool> STARTING_POOL;

        public SmallSavannaGraveGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "small_savanna_grave"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_savanna_grave/small_savanna_grave_01"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_savanna_grave/small_savanna_grave_02"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_savanna_grave/small_savanna_grave_03"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_savanna_grave/small_savanna_grave_04"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_savanna_grave/small_savanna_grave_05"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_savanna_grave/small_savanna_grave_06"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_savanna_grave/small_savanna_grave_07"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":small_savanna_grave/small_savanna_grave_08"), 1)
            ), StructureTemplatePool.Projection.RIGID));
        }
    }

}

