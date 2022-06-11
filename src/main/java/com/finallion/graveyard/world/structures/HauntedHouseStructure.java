package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.init.TGProcessors;
import com.finallion.graveyard.TheGraveyard;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class HauntedHouseStructure {

    public static class HauntedHouseGenerator {
        public static final Holder<StructureTemplatePool> STARTING_POOL;
        public static final Holder<StructureTemplatePool> DOWNSTAIRS;

        public HauntedHouseGenerator() {
        }

        public static void init() {
        }

        // change from legacy single to processed single
        // if water is removed from blockentities (like chests) it might give the error message "Tried access block entity before it was created"
        static {
            STARTING_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "haunted_house/start_pool"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                    StructurePoolElement.single(TheGraveyard.MOD_ID + ":haunted_house/start_pool/haunted_house_01"), 1)), StructureTemplatePool.Projection.RIGID));
            DOWNSTAIRS = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "haunted_house/downstairs_pool"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                    StructurePoolElement.single(TheGraveyard.MOD_ID + ":haunted_house/downstairs_pool/haunted_house_downstairs", TGProcessors.WATERLOGGED_LIST), 1)), StructureTemplatePool.Projection.RIGID));


        }
    }
}

