package com.finallion.graveyard.util;

import com.finallion.graveyard.init.TGEntities;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class StructureSpawnPool {

    public static final WeightedRandomList<MobSpawnSettings.SpawnerData> MONSTER_SPAWNS = WeightedRandomList.create(
            new MobSpawnSettings.SpawnerData(TGEntities.SKELETON_CREEPER.get(), 35, 1, 2),
            new MobSpawnSettings.SpawnerData(TGEntities.GHOUL.get(), 50, 1, 3),
            new MobSpawnSettings.SpawnerData(TGEntities.REVENANT.get(), 45, 1, 3)
    );

    public static final WeightedRandomList<MobSpawnSettings.SpawnerData> EMPTY = WeightedRandomList.create();

    public static final WeightedRandomList<MobSpawnSettings.SpawnerData> ILLAGER_SPAWNS = WeightedRandomList.create(
            new MobSpawnSettings.SpawnerData(EntityType.PILLAGER, 10, 1, 1),
            new MobSpawnSettings.SpawnerData(EntityType.VINDICATOR, 10, 1, 1)
    );

}
