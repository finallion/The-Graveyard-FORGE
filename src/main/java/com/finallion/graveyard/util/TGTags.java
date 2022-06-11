package com.finallion.graveyard.util;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class TGTags {

    public static void init() {}

    public static TagKey<Biome> IS_OVERWORLD = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(TheGraveyard.MOD_ID, "is_overworld"));


    public static final TagKey<Biome> GHOUL_SPAWN = biomeTag("ghoul_spawn");



    public static final TagKey<Biome> SPAWN_INCLUDE_LIST = biomeTag("spawn_include");


    public static TagKey<Biome> biomeTag(String name) {
        return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(TheGraveyard.MOD_ID, name));
    }

}
