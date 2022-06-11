package com.finallion.graveyard.world.structures;


import com.finallion.graveyard.TheGraveyard;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
public class MemorialTreeStructure {

    public static class MemorialTreeGenerator {
        public static final Holder<StructureTemplatePool> STARTING_POOL;

        public MemorialTreeGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "memorial_tree"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":memorial_tree/memorial_tree_01"), 1)), StructureTemplatePool.Projection.RIGID));
        }
    }

}

