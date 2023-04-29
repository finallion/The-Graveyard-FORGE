package com.finallion.graveyard.util;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class TGTags {

    public static void init() {
    }


    public static final TagKey<Biome> GHOUL_SPAWNS = biomeTag("ghoul_spawns");
    public static final TagKey<Biome> NIGHTMARE_SPAWNS = biomeTag("nightmare_spawns");
    public static final TagKey<Biome> REAPER_SPAWNS = biomeTag("reaper_spawns");
    public static final TagKey<Biome> REVENANT_SPAWNS = biomeTag("revenant_spawns");
    public static final TagKey<Biome> SKELETON_CREEPER_SPAWNS = biomeTag("skeleton_creeper_spawns");


    public static TagKey<Biome> biomeTag(String name) {
        return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(TheGraveyard.MOD_ID, name));
    }

}
