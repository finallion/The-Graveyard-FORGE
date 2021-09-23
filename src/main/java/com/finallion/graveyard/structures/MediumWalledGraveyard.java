package com.finallion.graveyard.structures;


import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.List;


public class MediumWalledGraveyard extends TGBaseStructure {


    public MediumWalledGraveyard(Codec<NoFeatureConfig> codec) {
        super(codec, "medium_walled_graveyard", 2, 0);
    }


    private static final List<MobSpawnInfo.Spawners> STRUCTURE_MONSTERS = ImmutableList.of(
            new MobSpawnInfo.Spawners(EntityType.ZOMBIE_VILLAGER, 1, 1, 4),
            new MobSpawnInfo.Spawners(EntityType.ZOMBIE, 1, 2, 4)
    );
    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return STRUCTURE_MONSTERS;
    }
}