package com.finallion.graveyard.events;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.init.TGConfiguredStructures;
import com.finallion.graveyard.init.TGStructures;
import com.finallion.graveyard.world.structures.MediumGraveyardStructure;
import com.finallion.graveyard.world.structures.SmallDesertGraveyardStructure;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;

public class ServerEvents {
    public static void setupStructureSpawns(final StructureSpawnListGatherEvent event) {
        if (event.getStructure() == TGStructures.MEDIUM_GRAVEYARD_STRUCTURE.get()) {
            event.addEntitySpawns(MobCategory.MONSTER, MediumGraveyardStructure.MONSTER_SPAWNS.get());
        }

        if (event.getStructure() == TGStructures.SMALL_DESERT_GRAVEYARD_STRUCTURE.get()) {
            event.addEntitySpawns(MobCategory.MONSTER, SmallDesertGraveyardStructure.MONSTER_SPAWNS.get());
        }
    }



}
