package com.finallion.graveyard.util;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public class BiomeCheckUtil {
    public static boolean parseBiomes(List<? extends String> whitelist, List<? extends String> blacklist, Holder<Biome> biome) {
        String biomeName = biome.m_203334_().getRegistryName().toString();

        //System.out.println(biome.m_203334_().getRegistryName());
        //System.out.println(biome.m_203543_().orElseThrow().location().toString());

        String biomeCategory = BuiltinRegistries.BIOME.get(biome.m_203334_().getRegistryName()).m_204183_(biome).getName();

        if (whitelist == null) {
            TheGraveyard.LOGGER.error("Error reading from the Graveyard config file: Allowed biome category/biome is null. Try to delete the file and restart the game.");
            return false;
        }

        // no blacklist and biome is allowed
        if (whitelist.contains(biomeName) && blacklist.isEmpty()) {
            return true;
        }

        // no blacklist and biomeCategory is allowed
        if (whitelist.contains("#" + biomeCategory) && blacklist.isEmpty()) {
            return true;
        }

        // blacklist exists and check if biome is on the blacklist
        if (whitelist.contains(biomeName) && !blacklist.isEmpty()) {
            if (blacklist.contains("#" + biomeCategory)) { // whitelist weighs higher than blacklist
                //TheGraveyard.LOGGER.error("Blacklisted biome category #" + biomeCategory + " contains whitelisted biome " + biomeName + ".");
                return true;
            } else if (blacklist.contains(biomeName)) {  // blacklist weighs higher than whitelist
                TheGraveyard.LOGGER.debug("Biome " +  biomeName + " is on whitelist and blacklist.");
                return false;
            } else {
                return true;
            }
        }

        // blacklist exists and check if biomeCategory is on the blacklist
        if (whitelist.contains("#" + biomeCategory) && !blacklist.isEmpty()) {
            if (blacklist.contains("#" + biomeCategory)) { // blacklist weighs higher than whitelist
                TheGraveyard.LOGGER.debug("Biome category #" + biomeCategory + " is on whitelist and blacklist.");
                return false;
            } else if (blacklist.contains(biomeName)) { // blacklist weighs higher than whitelist
                //TheGraveyard.LOGGER.error("Biome category #" + biomeCategory + " is on whitelist and subsidiary biome " + biomeName + " is on blacklist.");
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean parseWhitelistedMods(List<? extends String> modWhitelist, Holder<Biome> biome) {
        if (modWhitelist == null) {
            TheGraveyard.LOGGER.error("Error reading from the Graveyard config file: Allowed biome category/biome is null. Try to delete the file and restart the game.");
            return false;
        }

        String modid = biome.m_203543_().get().getRegistryName().getNamespace();
        return modWhitelist.contains("#" + modid);
    }

}
