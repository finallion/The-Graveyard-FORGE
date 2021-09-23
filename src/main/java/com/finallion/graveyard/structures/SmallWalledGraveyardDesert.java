package com.finallion.graveyard.structures;



import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.List;


public class SmallWalledGraveyardDesert extends TGBaseStructure {

    public SmallWalledGraveyardDesert(Codec<NoFeatureConfig> codec) {
        super(codec, "small_walled_graveyard_desert", 1.5, 0);
    }

    private static final List<MobSpawnInfo.Spawners> STRUCTURE_MONSTERS = ImmutableList.of(
            new MobSpawnInfo.Spawners(EntityType.PILLAGER, 1, 1, 2),
            new MobSpawnInfo.Spawners(EntityType.VINDICATOR, 1, 1, 2)
    );
    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return STRUCTURE_MONSTERS;
    }


}
