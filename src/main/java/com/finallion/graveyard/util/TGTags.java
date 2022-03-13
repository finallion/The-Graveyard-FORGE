package com.finallion.graveyard.util;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class TGTags {

    public static void init() {}

    public static TagKey<Biome> IS_OVERWORLD = TagKey.m_203882_(Registry.BIOME_REGISTRY,
            new ResourceLocation(TheGraveyard.MOD_ID, "is_overworld"));
}
