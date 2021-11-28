package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.biomes.HauntedForestBiomes;
import com.finallion.graveyard.config.GraveyardConfig;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.*;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGBiomes {
    public static List<Biome> BIOMES = new ArrayList<>();

    public static Biome HAUNTED_FOREST = createBiome("haunted_forest", HauntedForestBiomes.HauntedForestBiome());
    public static Biome HAUNTED_LAKES = createBiome("haunted_lakes", HauntedForestBiomes.HauntedForestLakeBiome());
    public static Biome ERODED_HAUNTED_FOREST = createBiome("eroded_haunted_forest", HauntedForestBiomes.ErodedHauntedForestBiome());

    public static Biome createBiome(String id, Biome biome) {
        ResourceLocation resourceLocation = new ResourceLocation(TheGraveyard.MOD_ID, id);

        biome.setRegistryName(resourceLocation);
        BIOMES.add(biome);

        return biome;
    }

    public static void addToDictionaries() {
        BIOMES.forEach((biome -> BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(RegistryKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName()), 2))));
        BIOMES.forEach((biome -> BiomeDictionary.addTypes(RegistryKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName()), BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SPOOKY)));
    }


    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        addToDictionaries();
        BIOMES.forEach(biome -> event.getRegistry().register(biome));
    }



}
