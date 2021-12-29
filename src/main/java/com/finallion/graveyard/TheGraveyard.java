package com.finallion.graveyard;

import com.finallion.graveyard.client.TheGraveyardClient;
import com.finallion.graveyard.events.ServerEvents;
import com.finallion.graveyard.init.*;
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
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.lang.reflect.Method;
import java.util.HashMap;
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
        //MinecraftForge.EVENT_BUS.register(new ServerEvents());


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
        if(event.getWorld() instanceof ServerLevel serverLevel){
            ChunkGenerator chunkGenerator = serverLevel.getChunkSource().getGenerator();

            if (chunkGenerator instanceof FlatLevelSource && serverLevel.dimension().equals(Level.OVERWORLD)) {
                return;
            }

            StructureSettings worldStructureConfig = chunkGenerator.getSettings();

            HashMap<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> STStructureToMultiMap = new HashMap<>();

            for(Map.Entry<ResourceKey<Biome>, Biome> biomeEntry : serverLevel.registryAccess().ownedRegistryOrThrow(Registry.BIOME_REGISTRY).entrySet()) {
                Biome.BiomeCategory biomeCategory = biomeEntry.getValue().getBiomeCategory();

                if (biomeEntry.getValue().getRegistryName().toString().contains("birch")) {
                    associateBiomeToConfiguredStructure(STStructureToMultiMap, TGConfiguredStructures.MEMORIAL_TREE_STRUCTURE_CONFIG, biomeEntry.getKey());
                }

                switch (biomeCategory) {
                    case PLAINS:
                        associateBiomeToConfiguredStructure(STStructureToMultiMap, TGConfiguredStructures.SMALL_GRAVEYARD_STRUCTURE_CONFIG, biomeEntry.getKey());
                        associateBiomeToConfiguredStructure(STStructureToMultiMap, TGConfiguredStructures.SMALL_GRAVE_STRUCTURE_CONFIG, biomeEntry.getKey());
                        break;
                    case SAVANNA:
                    case MESA:
                        associateBiomeToConfiguredStructure(STStructureToMultiMap, TGConfiguredStructures.SMALL_SAVANNA_GRAVE_STRUCTURE_CONFIG, biomeEntry.getKey());
                        break;
                    case MOUNTAIN:
                    case EXTREME_HILLS:
                        associateBiomeToConfiguredStructure(STStructureToMultiMap, TGConfiguredStructures.SMALL_MOUNTAIN_GRAVE_STRUCTURE_CONFIG, biomeEntry.getKey());
                        break;
                    case FOREST:
                        if (!biomeEntry.getValue().getRegistryName().toString().contains("birch") && !biomeEntry.getValue().getRegistryName().toString().contains("dark_forest")) {
                            associateBiomeToConfiguredStructure(STStructureToMultiMap, TGConfiguredStructures.MEDIUM_GRAVEYARD_STRUCTURE_CONFIG, biomeEntry.getKey());
                        }
                        if (biomeEntry.getValue().getRegistryName().toString().contains("dark_forest")) {
                            associateBiomeToConfiguredStructure(STStructureToMultiMap, TGConfiguredStructures.HAUNTED_HOUSE_STRUCTURE_CONFIG, biomeEntry.getKey());
                            associateBiomeToConfiguredStructure(STStructureToMultiMap, TGConfiguredStructures.LARGE_GRAVEYARD_STRUCTURE_CONFIG, biomeEntry.getKey());
                        }
                        associateBiomeToConfiguredStructure(STStructureToMultiMap, TGConfiguredStructures.SMALL_GRAVE_STRUCTURE_CONFIG, biomeEntry.getKey());
                        break;
                    case TAIGA:
                        associateBiomeToConfiguredStructure(STStructureToMultiMap, TGConfiguredStructures.SMALL_GRAVE_STRUCTURE_CONFIG, biomeEntry.getKey());
                        associateBiomeToConfiguredStructure(STStructureToMultiMap, TGConfiguredStructures.LARGE_GRAVEYARD_STRUCTURE_CONFIG, biomeEntry.getKey());
                        break;
                    case MUSHROOM:
                    case JUNGLE:
                        associateBiomeToConfiguredStructure(STStructureToMultiMap, TGConfiguredStructures.MUSHROOM_GRAVE_STRUCTURE_CONFIG, biomeEntry.getKey());
                        break;
                    case DESERT:
                        associateBiomeToConfiguredStructure(STStructureToMultiMap, TGConfiguredStructures.SMALL_DESERT_GRAVE_STRUCTURE_CONFIG, biomeEntry.getKey());
                        associateBiomeToConfiguredStructure(STStructureToMultiMap, TGConfiguredStructures.SMALL_DESERT_GRAVEYARD_STRUCTURE_CONFIG, biomeEntry.getKey());
                        break;
                    case SWAMP:
                        associateBiomeToConfiguredStructure(STStructureToMultiMap, TGConfiguredStructures.MUSHROOM_GRAVE_STRUCTURE_CONFIG, biomeEntry.getKey());
                        associateBiomeToConfiguredStructure(STStructureToMultiMap, TGConfiguredStructures.HAUNTED_HOUSE_STRUCTURE_CONFIG, biomeEntry.getKey());
                        break;
                    default:
                        break;

                }
            }

            ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> tempStructureToMultiMap = ImmutableMap.builder();
            worldStructureConfig.configuredStructures.entrySet().stream().filter(entry -> !STStructureToMultiMap.containsKey(entry.getKey())).forEach(tempStructureToMultiMap::put);

            STStructureToMultiMap.forEach((key, value) -> tempStructureToMultiMap.put(key, ImmutableMultimap.copyOf(value)));

            worldStructureConfig.configuredStructures = tempStructureToMultiMap.build();

            try {
                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "codec");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(chunkGenerator));
                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            }
            catch(Exception e){
                TheGraveyard.LOGGER.error("Was unable to check if " + serverLevel.dimension().location() + " is using Terraforged's ChunkGenerator.");
            }

            if(chunkGenerator instanceof FlatLevelSource &&
                    serverLevel.dimension().equals(Level.OVERWORLD)){
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

}
