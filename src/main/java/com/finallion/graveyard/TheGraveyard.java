package com.finallion.graveyard;

import com.finallion.graveyard.client.TheGraveyardClient;
import com.finallion.graveyard.config.CommonConfig;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.*;
import com.finallion.graveyard.item.VialOfBlood;
import com.finallion.graveyard.util.SpawnRules;
import com.finallion.graveyard.util.TGTags;
import com.mojang.serialization.Codec;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;


@Mod("graveyard")
public class TheGraveyard {
    public static final String MOD_ID = "graveyard";
    public static final Logger LOGGER = LogManager.getLogger();

    public TheGraveyard() {
        GeckoLib.initialize();

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> TheGraveyardClient::new);

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);

        TGBlocks.BLOCKS.register(modEventBus);
        TGItems.ITEMS.register(modEventBus);
        TGSounds.SOUNDS.register(modEventBus);
        TGEntities.ENTITIES.register(modEventBus);
        TGFeatures.FEATURES.register(modEventBus);
        TGTileEntities.TILE_ENTITIES.register(modEventBus);
        TGParticles.PARTICLES.register(modEventBus);

        modEventBus.addListener(this::setupClient);

        final DeferredRegister<Codec<? extends BiomeModifier>> serializers = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MOD_ID);
        serializers.register(modEventBus);
        serializers.register("mobspawns", SpawnRules.ModSpawnModifier::makeCodec);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GraveyardConfig.COMMON_SPEC);
        CommonConfig.loadConfig(GraveyardConfig.COMMON_SPEC, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-common.toml").toString());

        //modEventBus.addListener(this::onCreateEntityAttributes);

        //modEventBus.addListener(DataGenerators::gatherData);

    }

    public void setupClient(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            /* CHANGING ITEM TEXTURE */
            ItemProperties.register(TGItems.VIAL_OF_BLOOD.get(), new ResourceLocation("charged"), (stack, world, entity, seed) -> {
                if (entity != null && stack.is(TGItems.VIAL_OF_BLOOD.get())) {
                    return VialOfBlood.getBlood(stack);
                }
                return 0.0F;
            });

        });
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


    /*
    public void onCreateEntityAttributes(EntityAttributeModificationEvent event) {
        event.add(TGEntities.LICH.get(), Attributes.MAX_HEALTH, GraveyardConfig.COMMON.healthInCastingPhase.get());
        event.add(TGEntities.LICH.get(), Attributes.ATTACK_DAMAGE, GraveyardConfig.COMMON.damageCastingPhase.get());
        event.add(TGEntities.LICH.get(), Attributes.ARMOR, GraveyardConfig.COMMON.armor.get());
        event.add(TGEntities.LICH.get(), Attributes.ARMOR_TOUGHNESS, GraveyardConfig.COMMON.armorToughness.get());

        event.put(TGEntities.LICH.get(), AttributeSupplier.builder()
                .add(Attributes.MAX_HEALTH, GraveyardConfig.COMMON.healthInCastingPhase.get())
                .add(Attributes.ATTACK_DAMAGE, GraveyardConfig.COMMON.damageCastingPhase.get())
                .add(Attributes.ARMOR, GraveyardConfig.COMMON.armor.get())
                .add(Attributes.ARMOR_TOUGHNESS, GraveyardConfig.COMMON.armorToughness.get()).build());


    }

     */

    public static final CreativeModeTab GROUP = new CreativeModeTab ("graveyard_group") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.SKELETON_SKULL);
        }

    };

}
