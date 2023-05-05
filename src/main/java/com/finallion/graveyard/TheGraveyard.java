package com.finallion.graveyard;

import com.finallion.graveyard.client.TheGraveyardClient;
import com.finallion.graveyard.config.CommonConfig;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.events.TGEvents;
import com.finallion.graveyard.init.*;
import com.finallion.graveyard.item.VialOfBlood;
import com.finallion.graveyard.recipe.TGRecipeTypes;
import com.finallion.graveyard.util.TGTags;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
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

        forgeBus.addListener(EventPriority.NORMAL, TGEvents::onBiomesLoad);

        modEventBus.addListener(this::setup);
        TGBlocks.BLOCKS.register(modEventBus);
        TGItems.ITEMS.register(modEventBus);
        TGSounds.SOUNDS.register(modEventBus);
        TGEntities.ENTITIES.register(modEventBus);
        TGConfiguredStructureFeatures.STRUCTURES.register(modEventBus);
        TGScreens.MENUS.register(modEventBus);
        //TGRecipeTypes.RECIPE_TYPES.register(modEventBus);
        TGRecipeTypes.RECIPE_SERIALIZERS.register(modEventBus);
        TGTileEntities.TILE_ENTITIES.register(modEventBus);
        TGParticles.PARTICLES.register(modEventBus);

        modEventBus.addListener(this::setupClient);


        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GraveyardConfig.COMMON_SPEC, "graveyard-1.19.x-common.toml");
        CommonConfig.loadConfig(GraveyardConfig.COMMON_SPEC, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-1.19.x-common.toml").toString());

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
        Registry.register(Registry.RECIPE_TYPE, "graveyard:ossuary_carving", TGRecipeTypes.Type.INSTANCE);

        event.enqueueWork(() -> {
            TGAdvancements.init();
            TGTags.init();
            //TGStructureType.init();
            TGProcessors.registerProcessors();
        });
    }

    public static final CreativeModeTab GROUP = new CreativeModeTab ("graveyard_group") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.SKELETON_SKULL);
        }
    };

}
