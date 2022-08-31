package com.finallion.graveyard.util;


import com.finallion.graveyard.TheGraveyard;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public class BiomeSelectionUtil {

    // structures
    public static boolean parseBiomes(List<String> biomeWhitelist, List<String> biomeBlacklist, Holder<Biome> biome) {
        if (biomeWhitelist == null || biomeBlacklist == null) {
            TheGraveyard.LOGGER.error("The Graveyard config file isn't up to date. Please delete the file in your .minecraft/config folder and restart the game to create a new config file. If the error keeps showing up, contact the mod developer via Github or Discord (links can be found here: https://www.curseforge.com/minecraft/mc-mods/the-graveyard-forge)!");
            return false;
        }

        String biomeName = biome.unwrapKey().orElseThrow().location().toString();

        if (biomeBlacklist.contains(biomeName)) {
            return false;
        }

        for (String biomeInList : biomeWhitelist) {
            if (biomeInList.startsWith("#")) { // check if biome is in tag
                String[] parts = biomeInList.substring(1).split(":");
                TagKey<Biome> tag = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(parts[0], parts[1]));
                Registry<Biome> registry = RegistryAccess.builtinCopy().registryOrThrow(Registry.BIOME_REGISTRY);

                if (registry.isKnownTagName(tag)) {
                    if (registry.getTag(tag).get().contains(biome)) {
                        return true;
                    }
                }
            } else if (biomeWhitelist.contains(biomeName)) { // check if biome is on whitelist
                return true;
            }
        }

        return false;
    }


}

