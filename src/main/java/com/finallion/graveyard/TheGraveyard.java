package com.finallion.graveyard;

import com.finallion.graveyard.client.TheGraveyardClient;
import com.finallion.graveyard.config.ConfigHelper;
import com.finallion.graveyard.config.TheGraveyardConfig;
import com.finallion.graveyard.init.*;
import com.finallion.graveyard.structures.processors.SimpleSurfaceProcessors;
import com.finallion.graveyard.utils.ProcessorRegistry;
import com.finallion.graveyard.utils.TGBiomeKeys;
import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.StructureProcessorList;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod("graveyard")
public class TheGraveyard {
    public static final String MOD_ID = "graveyard";
    public static TheGraveyardConfig CONFIG;


    public static final IStructureProcessorType<SimpleSurfaceProcessors> SIMPLE_SURFACE_PROCESSOR = IStructureProcessorType.register("simple_surface_processor", SimpleSurfaceProcessors.CODEC);
    public static final StructureProcessorList SIMPLE_SURFACE_LIST = ProcessorRegistry.registerStructureProcessor("simple_surface_list", ImmutableList.of(
            new SimpleSurfaceProcessors()
    ));

    public TheGraveyard() {
        CONFIG = ConfigHelper.register(ModConfig.Type.COMMON, TheGraveyardConfig::new, "graveyard-forge-config-v1.toml");
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        modEventBus.addListener(EventPriority.HIGH, this::biomeModification);

        TGStructures.DEFERRED_REGISTRY_STRUCTURE.register(modEventBus);
        TGTileEntities.TILE_ENTITIES.register(modEventBus);
        TGParticles.PARTICLES.register(modEventBus);
    }

    public void biomeModification(final BiomeLoadingEvent event) {
        putStructures(event);
    }


    private void clientSetup(FMLClientSetupEvent event) {
        TheGraveyardClient.clientInit();
    }

    public static void putStructures(final BiomeLoadingEvent event) {
        if (event.getCategory().getName().contains("birch")) {
            add(event, TGConfiguredFeatures.CONFIGURED_LARGE_BIRCH_TREE, CONFIG.large_birch_tree.get());
        }

        if (event.getCategory().getName().contains("giant") || event.getCategory().getName().contains("wooded") || event.getCategory().getName().contains("taiga_mountains") || event.getCategory().getName().contains("flower") || event.getCategory().getName().contains("dark_forest_hills")) {
            add(event, TGConfiguredFeatures.CONFIGURED_MEDIUM_WALLED_GRAVEYARD, CONFIG.medium_walled_graveyard.get());
        }

        if (event.getCategory().getName().contains("snowy_taiga") || event.getCategory().getName().contains("dark_forest") || event.getCategory().getName().equals("jungle") || event.getCategory().getName().equals("forest") || event.getCategory().getName().equals("taiga") || event.getCategory().getName().equals("taiga_hills") || event.getCategory().getName().equals("dark_forest")) {
            add(event, TGConfiguredFeatures.CONFIGURED_LARGE_WALLED_GRAVEYARD, CONFIG.large_walled_graveyard.get());
        }


        Biome.Category category = event.getCategory();

        switch (category) {
            case PLAINS:
                add(event, TGConfiguredFeatures.CONFIGURED_SMALL_WALLED_GRAVEYARD, CONFIG.small_walled_graveyard.get());
                add(event, TGConfiguredFeatures.CONFIGURED_SMALL_GRAVE, CONFIG.small_grave.get());
                break;
            case SAVANNA:
                add(event, TGConfiguredFeatures.CONFIGURED_SMALL_WALLED_GRAVEYARD_SAVANNA, CONFIG.small_walled_graveyard_savanna.get());
                break;
            case FOREST:
                add(event, TGConfiguredFeatures.CONFIGURED_SMALL_GRAVE, CONFIG.small_grave.get());
                break;
            case TAIGA:
                add(event, TGConfiguredFeatures.CONFIGURED_SMALL_GRAVE, CONFIG.small_grave.get());
                break;
            case MUSHROOM:
                add(event, TGConfiguredFeatures.CONFIGURED_MUSHROOM_GRAVE, CONFIG.mushroom_grave.get());
                break;
            case DESERT:
                add(event, TGConfiguredFeatures.CONFIGURED_SMALL_WALLED_GRAVEYARD_DESERT, CONFIG.small_walled_graveyard_desert.get());
                break;
            case JUNGLE:
                add(event, TGConfiguredFeatures.CONFIGURED_MUSHROOM_GRAVE, CONFIG.mushroom_grave.get());
                break;
            case SWAMP:
                add(event, TGConfiguredFeatures.CONFIGURED_MUSHROOM_GRAVE, CONFIG.mushroom_grave.get());
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

    /*

    @Override
    public void onInitialize() {
        TGBlocks.registerBlocks();
        TGItems.registerItems();
        TGStructures.setupAndRegisterStructureFeatures();
        TGConfiguredFeatures.registerConfiguredStructures();
        TGBiomeKeys.init();
        TGProcessors.init();
        TGEntities.registerEntities();
        TGVillagerTrades.init();



    }

     */
    public static final ItemGroup GROUP = new ItemGroup(TheGraveyard.MOD_ID + "group") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.SKELETON_SKULL);
        }

    };



}
