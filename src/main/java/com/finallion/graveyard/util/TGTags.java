package com.finallion.graveyard.util;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class TGTags {

    public static void init() {}

    public static TagKey<Biome> IS_OVERWORLD = biomeTag("is_overworld");

    public static TagKey<Biome> IS_DESERT = biomeTag("is_desert");
    public static TagKey<Biome> IS_SAVANNA = biomeTag("is_savanna");
    public static TagKey<Biome> IS_MESA = biomeTag("is_mesa");
    public static TagKey<Biome> IS_PLAINS = biomeTag("is_plains");
    public static TagKey<Biome> IS_FOREST = biomeTag("is_forest");
    public static TagKey<Biome> IS_SNOWY = biomeTag("is_snowy");
    public static TagKey<Biome> IS_BEACH = biomeTag("is_beach");
    public static TagKey<Biome> IS_MOUNTAIN = biomeTag("is_mountain");
    public static TagKey<Biome> IS_JUNGLE = biomeTag("is_jungle");
    public static TagKey<Biome> IS_SWAMP = biomeTag("is_swamp");
    public static TagKey<Biome> IS_UNDERGROUND = biomeTag("is_underground");
    public static TagKey<Biome> IS_SPOOKY = biomeTag("is_spooky");

    public static final TagKey<Biome> GHOUL_SPAWN = biomeTag("ghoul_spawn");
    public static final TagKey<Biome> SPAWN_INCLUDE_LIST = biomeTag("spawn_include");

    public static TagKey<Biome> biomeTag(String name) {
        return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(TheGraveyard.MOD_ID, name));
    }

}
