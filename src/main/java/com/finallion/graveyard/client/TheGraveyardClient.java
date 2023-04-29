package com.finallion.graveyard.client;


import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blockentities.render.BrazierBlockEntityRenderer;
import com.finallion.graveyard.blockentities.render.GravestoneBlockEntityRenderer;
import com.finallion.graveyard.blockentities.render.SarcophagusBlockEntityRenderer;
import com.finallion.graveyard.entities.models.CorruptedIllagerModel;
import com.finallion.graveyard.entities.renders.*;
import com.finallion.graveyard.init.TGBlocks;
import com.finallion.graveyard.init.TGEntities;
import com.finallion.graveyard.init.TGParticles;
import com.finallion.graveyard.init.TGTileEntities;
import com.finallion.graveyard.particles.GraveyardFogParticle;
import com.finallion.graveyard.particles.GraveyardHandParticle;
import com.finallion.graveyard.particles.GraveyardSoulParticle;
import com.finallion.graveyard.particles.SonicBoomParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


public class TheGraveyardClient {
    private static final RenderType CUTOUT_MIPPED = RenderType.cutoutMipped();
    public static final ModelLayerLocation CORRUPTED_ILLAGER_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(TheGraveyard.MOD_ID, "corrupted_illager"), "main");


    public TheGraveyardClient() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::clientInit);
        modEventBus.addListener(this::initParticles);
        modEventBus.addListener(this::onBlockColorsInit);
        modEventBus.addListener(this::onItemColorsInit);
        modEventBus.addListener(this::registerEntityRenderers);
        modEventBus.addListener(this::registerLayerDefinition);
        modEventBus.addListener(this::registerEntityModels);
        modEventBus.addListener(this::initScreens);


    }

    @SubscribeEvent
    public void initScreens(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(TGScreens.OSSUARY_SCREEN_HANDLER.get(), OssuaryScreen::new);
        });
    }

    @SubscribeEvent
    public void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CORRUPTED_ILLAGER_MODEL_LAYER, CorruptedIllagerModel::createBodyModel);
    }


    @SubscribeEvent
    public void initParticles(ParticleFactoryRegisterEvent event){
        Minecraft.getInstance().particleEngine.register(TGParticles.GRAVEYARD_FOG_PARTICLE.get(), GraveyardFogParticle.FogFactory::new);
        Minecraft.getInstance().particleEngine.register(TGParticles.GRAVEYARD_SOUL_PARTICLE.get(), GraveyardSoulParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TGParticles.GRAVEYARD_HAND_PARTICLE.get(), GraveyardHandParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TGParticles.GRAVEYARD_LEFT_HAND_PARTICLE.get(), GraveyardHandParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TGParticles.GRAVEYARD_SOUL_BEAM_PARTICLE.get(), SonicBoomParticle.Provider::new);
    }

    @SubscribeEvent
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(TGEntities.SKELETON_CREEPER.get(), SkeletonCreeperRender::new);
        event.registerEntityRenderer(TGEntities.ACOLYTE.get(), AcolyteRender::new);
        event.registerEntityRenderer(TGEntities.GHOUL.get(), GhoulRenderer::new);
        event.registerEntityRenderer(TGEntities.REAPER.get(), ReaperRenderer::new);
        event.registerEntityRenderer(TGEntities.REVENANT.get(), RevenantRenderer::new);
        event.registerEntityRenderer(TGEntities.NIGHTMARE.get(), NightmareRenderer::new);
        event.registerEntityRenderer(TGEntities.CORRUPTED_PILLAGER.get(), CorruptedPillagerRenderer::new);
        event.registerEntityRenderer(TGEntities.CORRUPTED_VINDICATOR.get(), CorruptedVindicatorRenderer::new);
        event.registerEntityRenderer(TGEntities.WRAITH.get(), WraithRenderer::new);
        event.registerEntityRenderer(TGEntities.LICH.get(), LichRenderer::new);
        event.registerEntityRenderer(TGEntities.FALLING_CORPSE.get(), FallingCorpseRenderer::new);
        event.registerEntityRenderer(TGEntities.SKULL.get(), SkullEntityRenderer::new);
        event.registerEntityRenderer(TGEntities.GHOULING.get(), GhoulingRenderer::new);
        event.registerEntityRenderer(TGEntities.NAMELESS_HANGED.get(), NamelessHangedRenderer::new);


        event.registerBlockEntityRenderer(TGTileEntities.GRAVESTONE_BLOCK_ENTITY.get(), GravestoneBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(TGTileEntities.SARCOPHAGUS_BLOCK_ENTITY.get(), SarcophagusBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(TGTileEntities.BRAZIER_BLOCK_ENTITY.get(), BrazierBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(TGTileEntities.OSSUARY_BLOCK_ENTITY.get(), OssuaryBlockEntityRenderer::new);

    }

    @SubscribeEvent
    public void onBlockColorsInit(ColorHandlerEvent.Item event) {
        final BlockColors blockColors = event.getBlockColors();
        blockColors.register((state, reader, pos, color) -> reader != null && pos != null ? BiomeColors.getAverageGrassColor(reader, pos) : GrassColor.get(0.5D, 1.0D), TGBlocks.TG_GRASS_BLOCK.get());

    }

    @SubscribeEvent
    public void onItemColorsInit(ColorHandlerEvent.Item event) {
        final BlockColors blockColors = event.getBlockColors();
        final ItemColors itemColors = event.getItemColors();


        itemColors.register((stack, color) -> {
            BlockState blockstate = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
            return blockColors.getColor(blockstate, null, null, color);
        }, TGBlocks.TG_GRASS_BLOCK.get());

    }

    @SubscribeEvent
    public void clientInit(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.DARK_IRON_BARS.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.TG_GRASS_BLOCK.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.SKULL_WITH_RIB_CAGE.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.WITHER_SKULL_WITH_RIB_CAGE.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.LEANING_SKELETON.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.LEANING_WITHER_SKELETON.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.LYING_SKELETON.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.LYING_WITHER_SKELETON.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.SOUL_FIRE_BRAZIER.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.FIRE_BRAZIER.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.CANDLE_HOLDER.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.DARK_IRON_DOOR.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.DARK_IRON_TRAPDOOR.get(), CUTOUT_MIPPED);

        ItemBlockRenderTypes.setRenderLayer(TGBlocks.BONE_REMAINS.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.WITHER_BONE_REMAINS.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.TORSO_PILE.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.WITHER_TORSO_PILE.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.SKULL_ON_PIKE.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.WITHER_SKULL_ON_PIKE.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.LATERALLY_LYING_SKELETON.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.LATERALLY_LYING_WITHER_SKELETON.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.LOWER_BONE_STAFF.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.MIDDLE_BONE_STAFF.get(), CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.UPPER_BONE_STAFF.get(), CUTOUT_MIPPED);
    }


    @SubscribeEvent
    public void registerEntityModels(final ModelRegistryEvent event) {
        ForgeModelBakery.addSpecialModel(new ResourceLocation(TheGraveyard.MOD_ID, "item/sarcophagus_base"));
        ForgeModelBakery.addSpecialModel(new ResourceLocation(TheGraveyard.MOD_ID, "item/sarcophagus_lid"));

        for (Block block : TGBlocks.getCoffins()) {
            String woodType = block.getDescriptionId().split("\\.")[2];
            ForgeModelBakery.addSpecialModel(new ResourceLocation(TheGraveyard.MOD_ID, "item/" + woodType + "_base"));
            ForgeModelBakery.addSpecialModel(new ResourceLocation(TheGraveyard.MOD_ID, "item/" + woodType + "_lid"));
        }

    }






}
