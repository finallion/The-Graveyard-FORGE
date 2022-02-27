package com.finallion.graveyard;

import com.finallion.graveyard.client.TheGraveyardClient;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.config.StructureConfigEntry;
import com.finallion.graveyard.events.ServerEvents;
import com.finallion.graveyard.init.*;
import com.finallion.graveyard.world.structures.AbstractGraveyardStructure;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mod("graveyard")
public class TheGraveyard {
    public static final String MOD_ID = "graveyard";
    public static final Logger LOGGER = LogManager.getLogger();

    public TheGraveyard() {
        GeckoLib.initialize();

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> TheGraveyardClient::new);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);
        forgeBus.addListener(EventPriority.NORMAL, ServerEvents::setupStructureSpawns);
        forgeBus.addListener(EventPriority.NORMAL, ServerEvents::onBiomesLoad);
        forgeBus.addListener(EventPriority.NORMAL, ServerEvents::addBiomeFeatures);
        //MinecraftForge.EVENT_BUS.register(new ServerEvents());

        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, GraveyardConfig.COMMON_SPEC);

        TGStructures.DEFERRED_REGISTRY_STRUCTURE.register(modEventBus);
        TGTileEntities.TILE_ENTITIES.register(modEventBus);
        TGParticles.PARTICLES.register(modEventBus);
        TGProcessors.registerProcessors();

    }

    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            TGStructures.setupStructures();
            TGConfiguredStructures.registerConfiguredStructures();
        });
    }


    public static final CreativeModeTab GROUP = new CreativeModeTab ("group") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.SKELETON_SKULL);
        }

    };

    private static Method GETCODEC_METHOD;
    public void addDimensionalSpacing(final WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerLevel serverLevel) {
            ChunkGenerator chunkGenerator = serverLevel.getChunkSource().getGenerator();

            if (chunkGenerator instanceof FlatLevelSource && serverLevel.dimension().equals(Level.OVERWORLD)) {
                return;
            }


            StructureSettings worldStructureConfig = chunkGenerator.getSettings();

            HashMap<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> STStructureToMultiMap = new HashMap<>();

            biomeLoop: for (Map.Entry<ResourceKey<Biome>, Biome> biomeEntry : serverLevel.registryAccess().ownedRegistryOrThrow(Registry.BIOME_REGISTRY).entrySet()) {
                Biome.BiomeCategory biomeCategory = biomeEntry.getValue().getBiomeCategory();
                ResourceKey<Biome> biomeKey = biomeEntry.getKey();

                ResourceLocation name = biomeEntry.getValue().getRegistryName();

                if (name == null) {
                    continue;
                }

                for (StructureFeature<?> structure : TGStructures.MOD_STRUCTURES) {
                    AbstractGraveyardStructure abstractStructure = (AbstractGraveyardStructure) structure;
                    StructureConfigEntry structureConfig = abstractStructure.getStructureConfigEntry();

                    if (parseBiomes(structureConfig.whitelist.get(), structureConfig.blacklist.get(), biomeKey, biomeCategory) &&
                            parseWhitelistedMods(structureConfig.modWhitelist.get(), biomeKey) && structureConfig.canGenerate.get()) {
                        associateBiomeToConfiguredStructure(STStructureToMultiMap, abstractStructure.getStructureFeature(), biomeEntry.getKey());
                    }
                }
            }

            ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> tempStructureToMultiMap = ImmutableMap.builder();
            worldStructureConfig.configuredStructures.entrySet().stream().filter(entry -> !STStructureToMultiMap.containsKey(entry.getKey())).forEach(tempStructureToMultiMap::put);

            STStructureToMultiMap.forEach((key, value) -> tempStructureToMultiMap.put(key, ImmutableMultimap.copyOf(value)));

            worldStructureConfig.configuredStructures = tempStructureToMultiMap.build();

            try {
                if (GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "codec");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(chunkGenerator));
                if (cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            } catch (Exception e) {
                TheGraveyard.LOGGER.error("Was unable to check if " + serverLevel.dimension().location() + " is using Terraforged's ChunkGenerator.");
            }

            if (chunkGenerator instanceof FlatLevelSource && serverLevel.dimension().equals(Level.OVERWORLD)) {
                return;
            }


            Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(worldStructureConfig.structureConfig());
            tempMap.putIfAbsent(TGStructures.HAUNTED_HOUSE_STRUCTURE.get(), StructureSettings.DEFAULTS.get(TGStructures.HAUNTED_HOUSE_STRUCTURE.get()));
            tempMap.putIfAbsent(TGStructures.SMALL_GRAVE_STRUCTURE.get(), StructureSettings.DEFAULTS.get(TGStructures.SMALL_GRAVE_STRUCTURE.get()));
            tempMap.putIfAbsent(TGStructures.SMALL_DESERT_GRAVE_STRUCTURE.get(), StructureSettings.DEFAULTS.get(TGStructures.SMALL_DESERT_GRAVE_STRUCTURE.get()));
            tempMap.putIfAbsent(TGStructures.SMALL_MOUNTAIN_GRAVE_STRUCTURE.get(), StructureSettings.DEFAULTS.get(TGStructures.SMALL_MOUNTAIN_GRAVE_STRUCTURE.get()));
            tempMap.putIfAbsent(TGStructures.SMALL_SAVANNA_GRAVE_STRUCTURE.get(), StructureSettings.DEFAULTS.get(TGStructures.SMALL_SAVANNA_GRAVE_STRUCTURE.get()));
            tempMap.putIfAbsent(TGStructures.SMALL_GRAVEYARD_STRUCTURE.get(), StructureSettings.DEFAULTS.get(TGStructures.SMALL_GRAVEYARD_STRUCTURE.get()));
            tempMap.putIfAbsent(TGStructures.SMALL_DESERT_GRAVEYARD_STRUCTURE.get(), StructureSettings.DEFAULTS.get(TGStructures.SMALL_DESERT_GRAVEYARD_STRUCTURE.get()));
            tempMap.putIfAbsent(TGStructures.MEDIUM_GRAVEYARD_STRUCTURE.get(), StructureSettings.DEFAULTS.get(TGStructures.MEDIUM_GRAVEYARD_STRUCTURE.get()));
            tempMap.putIfAbsent(TGStructures.MUSHROOM_GRAVE_STRUCTURE.get(), StructureSettings.DEFAULTS.get(TGStructures.MUSHROOM_GRAVE_STRUCTURE.get()));
            tempMap.putIfAbsent(TGStructures.MEMORIAL_TREE_STRUCTURE.get(), StructureSettings.DEFAULTS.get(TGStructures.MEMORIAL_TREE_STRUCTURE.get()));
            tempMap.putIfAbsent(TGStructures.LARGE_GRAVEYARD_STRUCTURE.get(), StructureSettings.DEFAULTS.get(TGStructures.LARGE_GRAVEYARD_STRUCTURE.get()));

            worldStructureConfig.structureConfig = tempMap;
        }
    }


    private static void associateBiomeToConfiguredStructure(Map<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> STStructureToMultiMap, ConfiguredStructureFeature<?, ?> configuredStructureFeature, ResourceKey<Biome> biomeRegistryKey) {
        STStructureToMultiMap.putIfAbsent(configuredStructureFeature.feature, HashMultimap.create());
        HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> configuredStructureToBiomeMultiMap = STStructureToMultiMap.get(configuredStructureFeature.feature);
        if(configuredStructureToBiomeMultiMap.containsValue(biomeRegistryKey)) {
            TheGraveyard.LOGGER.error("""
                    Detected 2 ConfiguredStructureFeatures that share the same base StructureFeature trying to be added to same biome. One will be prevented from spawning.
                    This issue happens with vanilla too and is why a Snowy Village and Plains Village cannot spawn in the same biome because they both use the Village base structure.
                    The two conflicting ConfiguredStructures are: {}, {}
                    The biome that is attempting to be shared: {}
                """,
                    BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureFeature),
                    BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureToBiomeMultiMap.entries().stream().filter(e -> e.getValue() == biomeRegistryKey).findFirst().get().getKey()),
                    biomeRegistryKey
            );
        }
        else{
            configuredStructureToBiomeMultiMap.put(configuredStructureFeature, biomeRegistryKey);
        }
    }

    private static boolean parseBiomes(List<? extends String> whitelist, List<? extends String> blacklist, ResourceKey<Biome> key, Biome.BiomeCategory category) {
        String biomeIdentifier = key.location().toString();
        String biomeCategory = category.getName();

        if (whitelist == null) {
            TheGraveyard.LOGGER.error("Error reading from the Graveyard config file: Allowed biome category/biome is null. Try to delete the file and restart the game.");
            return false;
        }

        // no blacklist and biome is allowed
        if (whitelist.contains(biomeIdentifier) && blacklist.isEmpty()) {
            return true;
        }

        // no blacklist and biomeCategory is allowed
        if (whitelist.contains("#" + biomeCategory) && blacklist.isEmpty()) {
            return true;
        }

        // blacklist exists and check if biome is on the blacklist
        if (whitelist.contains(biomeIdentifier) && !blacklist.isEmpty()) {
            if (blacklist.contains("#" + biomeCategory)) { // whitelist weighs higher than blacklist
                //TheGraveyard.LOGGER.error("Blacklisted biome category #" + biomeCategory + " contains whitelisted biome " + biomeIdentifier + ".");
                return true;
            } else if (blacklist.contains(biomeIdentifier)) {  // blacklist weighs higher than whitelist
                TheGraveyard.LOGGER.error("Biome " +  biomeIdentifier + " is on whitelist and blacklist.");
                return false;
            } else {
                return true;
            }
        }


        // blacklist exists and check if biomeCategory is on the blacklist
        if (whitelist.contains("#" + biomeCategory) && !blacklist.isEmpty()) {
            if (blacklist.contains("#" + biomeCategory)) { // blacklist weighs higher than whitelist
                TheGraveyard.LOGGER.error("Biome category #" + biomeCategory + " is on whitelist and blacklist.");
                return false;
            } else if (blacklist.contains(biomeIdentifier)) { // blacklist weighs higher than whitelist
                return false;
            } else {
                return true;
            }
        }

        return false;
    }

    private static boolean parseWhitelistedMods(List<? extends String> whitelist, ResourceKey<Biome> key) {
        if (whitelist == null) {
            TheGraveyard.LOGGER.error("Error reading from the Graveyard config file: Allowed biome category/biome is null. Try to delete the file and restart the game.");
            return false;
        }

        String modid = key.location().getNamespace();
        return whitelist.contains("#" + modid);

    }


}
