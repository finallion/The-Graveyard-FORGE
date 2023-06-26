package com.finallion.graveyard;

import com.finallion.graveyard.client.TheGraveyardClient;
import com.finallion.graveyard.config.CommonConfig;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.*;
import com.finallion.graveyard.item.VialOfBlood;
import com.finallion.graveyard.recipe.TGRecipeTypes;
import com.finallion.graveyard.util.SpawnRules;
import com.finallion.graveyard.util.TGTags;
import com.mojang.serialization.Codec;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
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
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib.GeckoLib;


@Mod("graveyard")
public class TheGraveyard {
    public static final String MOD_ID = "graveyard";
    public static final Logger LOGGER = LogManager.getLogger();
    private static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public TheGraveyard() {
        GeckoLib.initialize();

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> TheGraveyardClient::new);

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);

        CREATIVE_TABS.register(modEventBus);
        TGBlocks.BLOCKS.register(modEventBus);
        TGItems.ITEMS.register(modEventBus);
        TGSounds.SOUNDS.register(modEventBus);
        TGEntities.ENTITIES.register(modEventBus);
        TGConfiguredStructureFeatures.STRUCTURES.register(modEventBus);
        TGScreens.MENUS.register(modEventBus);
        TGRecipeTypes.RECIPE_TYPES.register(modEventBus);
        TGRecipeTypes.RECIPE_SERIALIZERS.register(modEventBus);
        TGTileEntities.TILE_ENTITIES.register(modEventBus);
        TGParticles.PARTICLES.register(modEventBus);

        modEventBus.addListener(this::setupClient);

        final DeferredRegister<Codec<? extends BiomeModifier>> serializers = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MOD_ID);
        serializers.register(modEventBus);
        serializers.register("mobspawns", SpawnRules.ModSpawnModifier::makeCodec);

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
        event.enqueueWork(() -> {
            TGAdvancements.init();
            TGTags.init();
            TGStructureType.init();
            TGProcessors.registerProcessors();
        });
    }

    public static final RegistryObject<CreativeModeTab> GROUP = CREATIVE_TABS.register("group", () -> new CreativeModeTab.Builder(CreativeModeTab.Row.TOP,0)
            .icon(() -> new ItemStack(Items.SKELETON_SKULL))
            .title(Component.literal("The Graveyard"))
            .displayItems((featureFlags, output) -> {
                output.accept(TGItems.INCARNATED_EVIL_MUSIC_DISC.get());

                output.accept(TGItems.CORRUPTION.get());
                output.accept(TGItems.DARK_IRON_BLOCK.get());
                output.accept(TGItems.DARK_IRON_INGOT.get());
                output.accept(TGItems.DARK_IRON_DOOR.get());
                output.accept(TGItems.DARK_IRON_TRAPDOOR.get());
                output.accept(TGItems.DARK_IRON_BARS.get());
                output.accept(TGItems.SOUL_FIRE_BRAZIER.get());
                output.accept(TGItems.FIRE_BRAZIER.get());
                output.accept(TGItems.PEDESTAL.get());
                output.accept(TGItems.CANDLE_HOLDER.get());
                output.accept(TGItems.GRAVESTONE.get());
                output.accept(TGItems.COBBLESTONE_GRAVESTONE.get());
                output.accept(TGItems.MOSSY_COBBLESTONE_GRAVESTONE.get());
                output.accept(TGItems.DEEPSLATE_GRAVESTONE.get());
                output.accept(TGItems.BLACKSTONE_GRAVESTONE.get());
                output.accept(TGItems.GILDED_BLACKSTONE_GRAVESTONE.get());
                output.accept(TGItems.CRACKED_BLACKSTONE_GRAVESTONE.get());
                output.accept(TGItems.STONE_BRICKS_GRAVESTONE.get());
                output.accept(TGItems.MOSSY_STONE_BRICKS_GRAVESTONE.get());
                output.accept(TGItems.BRICKS_GRAVESTONE.get());
                output.accept(TGItems.QUARTZ_BRICKS_GRAVESTONE.get());
                output.accept(TGItems.RED_SANDSTONE_GRAVESTONE.get());
                output.accept(TGItems.SANDSTONE_GRAVESTONE.get());

                output.accept(TGItems.SKULL_WITH_RIB_CAGE.get());
                output.accept(TGItems.LEANING_SKELETON.get());
                output.accept(TGItems.SKULL_PILE.get());
                output.accept(TGItems.LYING_SKELETON.get());
                output.accept(TGItems.WITHER_SKULL_WITH_RIB_CAGE.get());
                output.accept(TGItems.LEANING_WITHER_SKELETON.get());
                output.accept(TGItems.WITHER_SKULL_PILE.get());
                output.accept(TGItems.LYING_WITHER_SKELETON.get());
                output.accept(TGItems.CREEPER_SKELETON.get());
                output.accept(TGItems.SKELETON_HAND.get());
                output.accept(TGItems.WITHER_SKELETON_HAND.get());
                output.accept(TGItems.TORSO_PILE.get());
                output.accept(TGItems.WITHER_TORSO_PILE.get());
                output.accept(TGItems.SKULL_ON_PIKE.get());
                output.accept(TGItems.WITHER_SKULL_ON_PIKE.get());
                output.accept(TGItems.BONE_REMAINS.get());
                output.accept(TGItems.WITHER_BONE_REMAINS.get());
                output.accept(TGItems.LATERALLY_LYING_SKELETON.get());
                output.accept(TGItems.LATERALLY_LYING_WITHER_SKELETON.get());
                output.accept(TGItems.HANGED_SKELETON.get());
                output.accept(TGItems.HANGED_WITHER_SKELETON.get());

                output.accept(TGItems.OSSUARY.get());

                output.accept(TGItems.BLACK_URN.get());
                output.accept(TGItems.GRAY_URN.get());
                output.accept(TGItems.LIGHT_GRAY_URN.get());
                output.accept(TGItems.WHITE_URN.get());
                output.accept(TGItems.LIGHT_BLUE_URN.get());
                output.accept(TGItems.BLUE_URN.get());
                output.accept(TGItems.CYAN_URN.get());
                output.accept(TGItems.GREEN_URN.get());
                output.accept(TGItems.LIME_URN.get());
                output.accept(TGItems.PINK_URN.get());
                output.accept(TGItems.MAGENTA_URN.get());
                output.accept(TGItems.PURPLE_URN.get());
                output.accept(TGItems.RED_URN.get());
                output.accept(TGItems.ORANGE_URN.get());
                output.accept(TGItems.YELLOW_URN.get());
                output.accept(TGItems.BROWN_URN.get());

                output.accept(TGItems.SMALL_BLACK_URN.get());
                output.accept(TGItems.SMALL_GRAY_URN.get());
                output.accept(TGItems.SMALL_LIGHT_GRAY_URN.get());
                output.accept(TGItems.SMALL_WHITE_URN.get());
                output.accept(TGItems.SMALL_LIGHT_BLUE_URN.get());
                output.accept(TGItems.SMALL_BLUE_URN.get());
                output.accept(TGItems.SMALL_CYAN_URN.get());
                output.accept(TGItems.SMALL_GREEN_URN.get());
                output.accept(TGItems.SMALL_LIME_URN.get());
                output.accept(TGItems.SMALL_PINK_URN.get());
                output.accept(TGItems.SMALL_MAGENTA_URN.get());
                output.accept(TGItems.SMALL_PURPLE_URN.get());
                output.accept(TGItems.SMALL_RED_URN.get());
                output.accept(TGItems.SMALL_ORANGE_URN.get());
                output.accept(TGItems.SMALL_YELLOW_URN.get());
                output.accept(TGItems.SMALL_BROWN_URN.get());

                output.accept(TGItems.VASE_BLOCK.get());

                output.accept(TGItems.SARCOPHAGUS.get());
                output.accept(TGItems.OAK_COFFIN.get());
                output.accept(TGItems.DARK_OAK_COFFIN.get());
                output.accept(TGItems.SPRUCE_COFFIN.get());
                output.accept(TGItems.BIRCH_COFFIN.get());
                output.accept(TGItems.JUNGLE_COFFIN.get());
                output.accept(TGItems.ACACIA_COFFIN.get());
                output.accept(TGItems.WARPED_COFFIN.get());
                output.accept(TGItems.CRIMSON_COFFIN.get());
                output.accept(TGItems.MANGROVE_COFFIN.get());
                output.accept(TGItems.BAMBOO_COFFIN.get());
                output.accept(TGItems.CHERRY_COFFIN.get());

                output.accept(TGItems.SKELETON_CREEPER_SPAWN_EGG.get());
                output.accept(TGItems.ACOLYTE_SPAWN_EGG.get());
                output.accept(TGItems.GHOUL_SPAWN_EGG.get());
                output.accept(TGItems.REAPER_SPAWN_EGG.get());
                output.accept(TGItems.REVENANT_SPAWN_EGG.get());
                output.accept(TGItems.NIGHTMARE_SPAWN_EGG.get());
                output.accept(TGItems.CORRUPTED_VINDICATOR_SPAWN_EGG.get());
                output.accept(TGItems.CORRUPTED_PILLAGER_SPAWN_EGG.get());
                output.accept(TGItems.WRAITH_SPAWN_EGG.get());
                output.accept(TGItems.LICH_SPAWN_EGG.get());
                output.accept(TGItems.NAMELESS_HANGED_SPAWN_EGG.get());

                output.accept(TGItems.BONE_DAGGER.get());
                output.accept(TGItems.WHITE_BONE_STAFF.get());
                output.accept(TGItems.BLACK_BONE_STAFF.get());
                output.accept(TGItems.RED_BONE_STAFF.get());
                output.accept(TGItems.CYAN_BONE_STAFF.get());
                output.accept(TGItems.PURPLE_BONE_STAFF.get());

                output.accept(TGItems.ALTAR.get());
                output.accept(TGItems.ALTAR_SIDE.get());
                output.accept(TGItems.ALTAR_CORNER.get());
                output.accept(TGItems.ALTAR_CENTER.get());
                output.accept(TGItems.UPPER_BONE_STAFF.get());
                output.accept(TGItems.MIDDLE_BONE_STAFF.get());
                output.accept(TGItems.LOWER_BONE_STAFF.get());
                output.accept(TGItems.VIAL_OF_BLOOD.get());
            }).build()
    );


}
