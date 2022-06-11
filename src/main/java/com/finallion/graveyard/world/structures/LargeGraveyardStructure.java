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

public class LargeGraveyardStructure {

    public static class LargeGraveyardGenerator {
        public static final Holder<StructureTemplatePool> STARTING_POOL;
        public static final Holder<StructureTemplatePool> BRANCH_POOL;
        public static final Holder<StructureTemplatePool> FEATURE_POOL;
        public static final Holder<StructureTemplatePool> CRYPT_POOL;
        public static final Holder<StructureTemplatePool> STREET_POOL;


        public LargeGraveyardGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "large_walled_graveyard/start_pool"), new ResourceLocation("minecraft:empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/crossroad_01"), 1),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/crossroad_02"), 1)
            ), StructureTemplatePool.Projection.RIGID));
            BRANCH_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "large_walled_graveyard/branch_pool"), new ResourceLocation("minecraft:empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_large_grave_01"), 10),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_spider_den"), 6),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_lost_grave"), 10),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_graves_01"), 10),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_graves_02"), 10),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_graves_03"), 10),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_graves_04"), 10),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_graves_05"), 10),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_looted_graves_01"), 10),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_looted_graves_02"), 10),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_willow"), 6),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_large_grave_02"), 10),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_boulder_01"), 9),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_boulder_02"), 10),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_boulder_03"), 10),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_nature_01"), 15),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_nature_02"), 15),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_nature_03"), 15),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_nature_04"), 15),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/branch_pool/branch_nature_05"), 15)
            ), StructureTemplatePool.Projection.RIGID));
            FEATURE_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "large_walled_graveyard/feature_pool"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/feature_pool/wither_skeleton_mill", TGProcessors.SWITCH_SPAWNER_LIST), 2),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/feature_pool/gallows_hill"), 2),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/feature_pool/walled_graves_with_crypt"), 2),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/feature_pool/walled_street_01"), 2),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/feature_pool/walled_street_02"), 2),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/feature_pool/walled_street_03"), 2)
            ), StructureTemplatePool.Projection.RIGID));
            CRYPT_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "large_walled_graveyard/small_crypt_pool"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/small_crypt_pool/small_crypt", TGProcessors.WATERLOGGED_LIST), 1)
            ), StructureTemplatePool.Projection.RIGID));
            STREET_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "large_walled_graveyard/street_pool"), new ResourceLocation("empty"), ImmutableList.of(
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_01"), 5),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_02"), 5),
                    //Pair.of(StructurePoolElement.single()(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_03"), 5),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_04"), 5),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_05"), 4),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_06"), 4),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_07"), 4),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_08"), 4),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_09"), 4),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/corner_01"), 7),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/corner_02"), 7),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/corner_03"), 7),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/corner_04"), 7),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/corner_05"), 7),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/corner_06"), 7),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/graveyard_entrance"), 5),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_end_01"), 4),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_end_02"), 4),
                    Pair.of(StructurePoolElement.single(TheGraveyard.MOD_ID + ":large_graveyard/street_pool/street_end_03"), 4)
            ), StructureTemplatePool.Projection.RIGID));
        }
    }
}

