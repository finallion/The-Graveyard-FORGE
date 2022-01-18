package com.finallion.graveyard.config;

import com.finallion.graveyard.init.TGConfiguredStructures;
import com.finallion.graveyard.init.TGStructures;
import com.finallion.graveyard.world.structures.AbstractGraveyardStructure;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class CommonConfig {


    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistedBiomes;
    public final ForgeConfigSpec.BooleanValue enableHorde;
    public final ForgeConfigSpec.IntValue sizeHorde;
    public final ForgeConfigSpec.IntValue ticksUntilSpawnHorde;


    public CommonConfig(ForgeConfigSpec.Builder builder) {
        builder.push("The Graveyard - Structures Config");
        this.blacklistedBiomes = builder.comment("To whitelist biomes: add the biome category to the whitelist. If you want to allow a specific biome from a biome category, whitelist the category and blacklist all biomes except the one biome you like to be able to generate structures. \n" +
                "To blacklist biomes: add the biome name to the blacklist. A full list of all the biomes can be found here https://minecraft.fandom.com/wiki/Biome#Biome_IDs").defineList("global.blacklisted_biomes", getAllBiomesForCategory(Biome.BiomeCategory.RIVER, Biome.BiomeCategory.OCEAN, Biome.BiomeCategory.BEACH), this::validateBiome);

       // builder.comment("To whitelist biomes: add the biome category the whitelist. If you want to allow a specific biome from a biome category, whitelist the category and blacklist all biomes except the one biome you like to be able to generate structures. \n" +
       //         "To blacklist biomes: add the biome name to the blacklist. A full list of all the biomes can be found here https://minecraft.fandom.com/wiki/Biome#Biome_IDs \n");

        for (StructureFeature<?> structure : TGStructures.MOD_STRUCTURES) {
            AbstractGraveyardStructure graveyardStructure = (AbstractGraveyardStructure) structure;
            StructureConfigEntry structureConfig = graveyardStructure.getStructureConfigEntry();

            String name = ((AbstractGraveyardStructure) structure).getStructureName();

            structureConfig.canGenerate = builder.comment("Graveyard structure can generate: ").define(name + ".generate", true);
            structureConfig.spacing = builder.comment("Structure spacing (needs to be higher than separation): ").defineInRange(name + ".spacing", structureConfig.getSpacing(), 0, 200);
            structureConfig.spacing = builder.comment("Structure separation: ").defineInRange(name + ".separation", structureConfig.getSeparation(), 0, 200);
            structureConfig.biomeCategories = builder.comment("Biome categories the structure can generate in: " + Arrays.toString(Biome.BiomeCategory.values()).toLowerCase(Locale.ROOT)).defineList(name + ".validBiomeCategories", structureConfig.getBiomeCategories(), o -> o instanceof String);
            structureConfig.blacklistedBiomes = builder.comment("Biomes (not biome categories!) the structure can NOT generate in").defineList(name + ".blacklistedBiomeCategories", structureConfig.getBlacklistedBiomes(), this::validateBiome);
        }

        builder.pop();

        builder.push("The Graveyard - Horde Config");
        this.enableHorde = builder.comment("Enables large Graveyard mob groups to spawn: ").define("horde.generate", true);
        this.sizeHorde = builder.comment("Set the size of the horde: ").defineInRange("horde.size", 40, 0, 200);
        this.ticksUntilSpawnHorde = builder.comment("Set the ticks until the next horde spawn attempt: ").defineInRange("horde.ticksUntilSpawnHorde", 12000, 1, 1000000);
        builder.pop();
    }


    private boolean validateBiome(Object o) {
        return o == null || ((String) o).contains("*") || ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) o));
    }

    public static List<String> getAllBiomesForCategory(Biome.BiomeCategory... categories) {
        List<String> biomes = new ArrayList<>();

        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            for (Biome.BiomeCategory category : categories) {
                if (biome.getBiomeCategory() == category) {
                    biomes.add(Objects.requireNonNull(biome.getRegistryName()).toString());
                }
            }
        }

        return biomes;
    }
}
