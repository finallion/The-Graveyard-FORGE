package com.finallion.graveyard;

import com.finallion.graveyard.client.TheGraveyardClient;
import com.finallion.graveyard.config.ConfigHelper;
import com.finallion.graveyard.config.TheGraveyardConfig;
import com.finallion.graveyard.entites.AcolyteEntity;
import com.finallion.graveyard.entites.BaseGhoulEntity;
import com.finallion.graveyard.entites.ReaperEntity;
import com.finallion.graveyard.entites.SkeletonCreeper;
import com.finallion.graveyard.init.*;
import com.finallion.graveyard.structures.processors.SimpleSurfaceProcessors;
import com.finallion.graveyard.utils.ProcessorRegistry;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.StructureProcessorList;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


@Mod("graveyard")
@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TheGraveyard {
    public static final String MOD_ID = "graveyard";
    public static TheGraveyardConfig CONFIG;
    private static Method GETCODEC_METHOD;


    public static final IStructureProcessorType<SimpleSurfaceProcessors> SIMPLE_SURFACE_PROCESSOR = IStructureProcessorType.register("simple_surface_processor", SimpleSurfaceProcessors.CODEC);
    public static final StructureProcessorList SIMPLE_SURFACE_LIST = ProcessorRegistry.registerStructureProcessor("simple_surface_list", ImmutableList.of(
            new SimpleSurfaceProcessors()
    ));


    // TODO: WorldRenderer mixin
    // TODO: lang
    // TODO: Gravestones, texture deepslate, moss carpet double, sound moss, mobs spawn, bone dagger
    // TODO: house rebuild
    public TheGraveyard() {
        GeckoLib.initialize();
        CONFIG = ConfigHelper.register(ModConfig.Type.COMMON, TheGraveyardConfig::new, "graveyard-forge-config-v1.toml");

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> TheGraveyardClient::new);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        modEventBus.addListener(this::setup);

        forgeBus.addListener(EventPriority.HIGH, this::biomeModification);
        forgeBus.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);

        TGStructures.DEFERRED_REGISTRY_STRUCTURE.register(modEventBus);
        TGTileEntities.TILE_ENTITIES.register(modEventBus);
        TGParticles.PARTICLES.register(modEventBus);
    }

    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            TGStructures.register();
            TGConfiguredStructureFeatures.registerConfiguredStructures();
        });
    }

    public void biomeModification(final BiomeLoadingEvent event) {
        putStructures(event);
    }


    public static void putStructures(final BiomeLoadingEvent event) {
        Biome.Category category = event.getCategory();


        switch (category) {
            case PLAINS:
                add(event, TGConfiguredStructureFeatures.CONFIGURED_SMALL_WALLED_GRAVEYARD, CONFIG.small_walled_graveyard.get());
                add(event, TGConfiguredStructureFeatures.CONFIGURED_SMALL_GRAVE, CONFIG.small_grave.get());
                break;
            case SAVANNA:
                add(event, TGConfiguredStructureFeatures.CONFIGURED_SMALL_WALLED_GRAVEYARD_SAVANNA, CONFIG.small_walled_graveyard_savanna.get());
                break;
            case FOREST:
                add(event, TGConfiguredStructureFeatures.CONFIGURED_SMALL_GRAVE, CONFIG.small_grave.get());
                add(event, TGConfiguredStructureFeatures.CONFIGURED_LARGE_WALLED_GRAVEYARD, CONFIG.large_walled_graveyard.get());
                add(event, TGConfiguredStructureFeatures.CONFIGURED_MEDIUM_WALLED_GRAVEYARD, CONFIG.medium_walled_graveyard.get());
                add(event, TGConfiguredStructureFeatures.CONFIGURED_LARGE_BIRCH_TREE, CONFIG.large_birch_tree.get());
                add(event, TGConfiguredStructureFeatures.CONFIGURED_HAUNTED_HOUSE, CONFIG.haunted_house.get());
                break;
            case TAIGA:
                add(event, TGConfiguredStructureFeatures.CONFIGURED_SMALL_GRAVE, CONFIG.small_grave.get());
                add(event, TGConfiguredStructureFeatures.CONFIGURED_LARGE_WALLED_GRAVEYARD, CONFIG.large_walled_graveyard.get());
                add(event, TGConfiguredStructureFeatures.CONFIGURED_MEDIUM_WALLED_GRAVEYARD, CONFIG.medium_walled_graveyard.get());
                break;
            case MUSHROOM:
                add(event, TGConfiguredStructureFeatures.CONFIGURED_MUSHROOM_GRAVE, CONFIG.mushroom_grave.get());
                break;
            case DESERT:
                add(event, TGConfiguredStructureFeatures.CONFIGURED_SMALL_WALLED_GRAVEYARD_DESERT, CONFIG.small_walled_graveyard_desert.get());
                break;
            case JUNGLE:
                add(event, TGConfiguredStructureFeatures.CONFIGURED_MUSHROOM_GRAVE, CONFIG.mushroom_grave.get());
                break;
            case SWAMP:
                add(event, TGConfiguredStructureFeatures.CONFIGURED_MUSHROOM_GRAVE, CONFIG.mushroom_grave.get());
                add(event, TGConfiguredStructureFeatures.CONFIGURED_HAUNTED_HOUSE, CONFIG.haunted_house.get());
                break;
            default:
                break;
        }





    }

    public static void add(final BiomeLoadingEvent e, StructureFeature<?, ?> s, boolean config) {
        if (config) {
            e.getGeneration().getStructures().add(Lazy.of(() -> s));
        }
    }


    public static final ItemGroup GROUP = new ItemGroup("group") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.SKELETON_SKULL);
        }

    };

    public void addDimensionalSpacing(final WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();
            if (!serverWorld.dimension().equals(World.OVERWORLD)) {
                return;
            }

            try {
                if (GETCODEC_METHOD == null)
                    GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));
                if (cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            } catch (Exception e) {
                //StructureTutorialMain.LOGGER.error("Was unable to check if " + serverWorld.dimension().location() + " is using Terraforged's ChunkGenerator.");
            }


            if (serverWorld.getChunkSource().getGenerator() instanceof FlatChunkGenerator &&
                    serverWorld.dimension().equals(World.OVERWORLD)) {
                return;
            }

            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
            tempMap.putIfAbsent(TGStructures.SMALL_WALLED_GRAVEYARD.get(), DimensionStructuresSettings.DEFAULTS.get(TGStructures.SMALL_WALLED_GRAVEYARD.get()));
            tempMap.putIfAbsent(TGStructures.SMALL_WALLED_GRAVEYARD_DESERT.get(), DimensionStructuresSettings.DEFAULTS.get(TGStructures.SMALL_WALLED_GRAVEYARD_DESERT.get()));
            tempMap.putIfAbsent(TGStructures.SMALL_WALLED_GRAVEYARD_SAVANNA.get(), DimensionStructuresSettings.DEFAULTS.get(TGStructures.SMALL_WALLED_GRAVEYARD_SAVANNA.get()));
            tempMap.putIfAbsent(TGStructures.MEDIUM_WALLED_GRAVEYARD.get(), DimensionStructuresSettings.DEFAULTS.get(TGStructures.MEDIUM_WALLED_GRAVEYARD.get()));
            tempMap.putIfAbsent(TGStructures.LARGE_WALLED_GRAVEYARD.get(), DimensionStructuresSettings.DEFAULTS.get(TGStructures.LARGE_WALLED_GRAVEYARD.get()));
            tempMap.putIfAbsent(TGStructures.LARGE_BIRCH_TREE.get(), DimensionStructuresSettings.DEFAULTS.get(TGStructures.LARGE_BIRCH_TREE.get()));
            tempMap.putIfAbsent(TGStructures.MUSHROOM_GRAVE.get(), DimensionStructuresSettings.DEFAULTS.get(TGStructures.MUSHROOM_GRAVE.get()));
            tempMap.putIfAbsent(TGStructures.MUSHROOM_GRAVE.get(), DimensionStructuresSettings.DEFAULTS.get(TGStructures.MUSHROOM_GRAVE.get()));
            tempMap.putIfAbsent(TGStructures.HAUNTED_HOUSE.get(), DimensionStructuresSettings.DEFAULTS.get(TGStructures.HAUNTED_HOUSE.get()));


            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }



}
