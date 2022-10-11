package com.finallion.graveyard.init;

import com.finallion.graveyard.advancements.TGAdvancementTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.resources.ResourceLocation;


public class TGAdvancements {

    public static TGAdvancementTrigger KILLED_BY_BONE_DAGGER = CriteriaTriggers.register(new TGAdvancementTrigger(new ResourceLocation("graveyard:killed_by_bone_dagger")));
    public static TGAdvancementTrigger KILL_WHILE_BLINDED = CriteriaTriggers.register(new TGAdvancementTrigger(new ResourceLocation("graveyard:kill_while_blinded")));
    public static TGAdvancementTrigger DIM_LIGHT = CriteriaTriggers.register(new TGAdvancementTrigger(new ResourceLocation("graveyard:dim_light")));
    public static TGAdvancementTrigger KILL_HORDE = CriteriaTriggers.register(new TGAdvancementTrigger(new ResourceLocation("graveyard:kill_horde")));
    public static TGAdvancementTrigger SPAWN_WRAITH = CriteriaTriggers.register(new TGAdvancementTrigger(new ResourceLocation("graveyard:spawn_wraith")));
    public static TGAdvancementTrigger EQUIP_COFFIN = CriteriaTriggers.register(new TGAdvancementTrigger(new ResourceLocation("graveyard:equip_coffin")));

    public static void init() {
    }
}
