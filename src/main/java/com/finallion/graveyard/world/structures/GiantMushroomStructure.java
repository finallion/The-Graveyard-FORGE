package com.finallion.graveyard.world.structures;


import com.finallion.graveyard.TheGraveyard;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class GiantMushroomStructure {

    public static class GiantMushroomGenerator {
        public static final Holder<StructureTemplatePool> STARTING_POOL;
        public static final Holder<StructureTemplatePool> BROWN_CAP;
        public static final Holder<StructureTemplatePool> RED_CAP;

        public GiantMushroomGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "giant_mushroom"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":giant_mushroom/giant_brown_mushroom_stem_01"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":giant_mushroom/giant_red_mushroom_stem_01"), 1)
            ), StructureTemplatePool.Projection.RIGID));
            BROWN_CAP = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "giant_mushroom/brown_cap"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                    StructurePoolElement.single(TheGraveyard.MOD_ID + ":giant_mushroom/brown_cap/giant_brown_mushroom_cap_01"), 1)), StructureTemplatePool.Projection.RIGID));
            RED_CAP = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "giant_mushroom/red_cap"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                    StructurePoolElement.single(TheGraveyard.MOD_ID + ":giant_mushroom/red_cap/giant_red_mushroom_cap_01"), 1)), StructureTemplatePool.Projection.RIGID));

        }
    }
}

