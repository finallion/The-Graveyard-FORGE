package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.world.biomes.AncientDeadCoralReef;
import com.finallion.graveyard.world.biomes.HauntedForestBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGBiomes {
    public static final ResourceKey<Biome> ERODED_HAUNTED_FOREST_KEY = registerBiomeKeys("eroded_haunted_forest");
    public static final ResourceKey<Biome> HAUNTED_LAKES_KEY = registerBiomeKeys("haunted_lakes");
    public static final ResourceKey<Biome> HAUNTED_FOREST_KEY = registerBiomeKeys("haunted_forest");
    public static final ResourceKey<Biome> ANCIENT_DEAD_CORAL_REEF_KEY = registerBiomeKeys("ancient_dead_coral_reef");

    private static ResourceKey<Biome> registerBiomeKeys(String name) {
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(TheGraveyard.MOD_ID, name));
    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        IForgeRegistry<Biome> registry = event.getRegistry();
        registry.register(HauntedForestBiomes.createHauntedForest().setRegistryName(HAUNTED_FOREST_KEY.location()));
        registry.register(HauntedForestBiomes.createErodedHauntedForest().setRegistryName(ERODED_HAUNTED_FOREST_KEY.location()));
        registry.register(HauntedForestBiomes.createHauntedLakes().setRegistryName(HAUNTED_LAKES_KEY.location()));
        registry.register(AncientDeadCoralReef.createAncientDeadCoralReef().setRegistryName(ANCIENT_DEAD_CORAL_REEF_KEY.location()));

    }




}
