package com.finallion.graveyard;

import com.finallion.graveyard.client.TheGraveyardClient;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.data.DataGenerators;
import com.finallion.graveyard.events.ServerEvents;
import com.finallion.graveyard.init.*;
import com.finallion.graveyard.util.SpawnRules;
import com.finallion.graveyard.util.TGTags;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.NetherFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountOnEveryLayerPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.util.List;


@Mod("graveyard")
public class TheGraveyard {
    public static final String MOD_ID = "graveyard";
    public static final Logger LOGGER = LogManager.getLogger();

    public TheGraveyard() {
        GeckoLib.initialize();

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> TheGraveyardClient::new);

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //forgeBus.addListener(EventPriority.NORMAL, ServerEvents::onBiomesLoad);
        //forgeBus.addListener(EventPriority.NORMAL, ServerEvents::addBiomeFeatures);

        modEventBus.addListener(this::setup);
        TGBlocks.BLOCKS.register(modEventBus);
        TGItems.ITEMS.register(modEventBus);
        TGEntities.ENTITIES.register(modEventBus);
        TGFeatures.FEATURES.register(modEventBus);
        TGTileEntities.TILE_ENTITIES.register(modEventBus);
        TGParticles.PARTICLES.register(modEventBus);

        final DeferredRegister<Codec<? extends BiomeModifier>> serializers = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MOD_ID);
        serializers.register(modEventBus);
        serializers.register("mobspawns", SpawnRules.ModSpawnModifier::makeCodec);


        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GraveyardConfig.COMMON_SPEC);


        modEventBus.addListener(DataGenerators::gatherData);

    }

    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            TGAdvancements.init();
            TGTags.init();
            TGStructureType.init();
            TGConfiguredFeatures.registerConfiguredFeatures();
            TGConfiguredFeatures.registerPlacedFeatures();
            TGProcessors.registerProcessors();
            TGStructureSets.init();
            TGConfiguredStructureFeatures.init();
        });
    }


    public static final CreativeModeTab GROUP = new CreativeModeTab ("graveyard_group") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.SKELETON_SKULL);
        }

    };

}
