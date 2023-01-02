package com.finallion.graveyard.util;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class TGTags {

    public static void init() {
    }

    public static TagKey<Biome> IS_OVERWORLD = biomeTag("is_overworld");

    public static TagKey<Biome> biomeTag(String name) {
        return TagKey.create(Registries.f_256952_, new ResourceLocation(TheGraveyard.MOD_ID, name));
    }

}
