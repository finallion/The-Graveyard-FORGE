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
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


public class TheGraveyardClient {
    //private static final RenderType CUTOUT_MIPPED = RenderType.cutoutMipped();
    public static final ModelLayerLocation CORRUPTED_ILLAGER_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(TheGraveyard.MOD_ID, "corrupted_illager"), "main");


    public TheGraveyardClient() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //modEventBus.addListener(this::clientInit);
        modEventBus.addListener(this::initParticles);
        modEventBus.addListener(this::onBlockColorsInit);
        modEventBus.addListener(this::onItemColorsInit);
        modEventBus.addListener(this::registerEntityRenderers);
        modEventBus.addListener(this::registerLayerDefinition);
        modEventBus.addListener(this::registerEntityModels);
        //modEventBus.addListener(this::onRegisterModels);

    }

    @SubscribeEvent
    public void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CORRUPTED_ILLAGER_MODEL_LAYER, CorruptedIllagerModel::createBodyModel);
    }

    @SubscribeEvent
    public void initParticles(RegisterParticleProvidersEvent event){
        event.register(TGParticles.GRAVEYARD_FOG_PARTICLE.get(), GraveyardFogParticle.FogFactory::new);
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

        event.registerBlockEntityRenderer(TGTileEntities.GRAVESTONE_BLOCK_ENTITY.get(), GravestoneBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(TGTileEntities.SARCOPHAGUS_BLOCK_ENTITY.get(), SarcophagusBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(TGTileEntities.BRAZIER_BLOCK_ENTITY.get(), BrazierBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public void onBlockColorsInit(RegisterColorHandlersEvent.Block event) {
        final BlockColors blockColors = event.getBlockColors();
        blockColors.register((state, reader, pos, color) -> reader != null && pos != null ? BiomeColors.getAverageGrassColor(reader, pos) : GrassColor.get(0.5D, 1.0D), TGBlocks.TG_GRASS_BLOCK.get());

    }

    @SubscribeEvent
    public void onItemColorsInit(RegisterColorHandlersEvent.Item event) {
        final BlockColors blockColors = event.getBlockColors();
        final ItemColors itemColors = event.getItemColors();


        itemColors.register((stack, color) -> {
            BlockState blockstate = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
            return blockColors.getColor(blockstate, null, null, color);
        }, TGBlocks.TG_GRASS_BLOCK.get());

    }

    @SubscribeEvent
    public void registerEntityModels(ModelEvent.RegisterAdditional event) {
        event.register(SarcophagusBlockEntityRenderer.SARCOPHAGUS_FOOT);
        event.register(SarcophagusBlockEntityRenderer.SARCOPHAGUS_HEAD);
        event.register(SarcophagusBlockEntityRenderer.SARCOPHAGUS_FOOT_LID);
        event.register(SarcophagusBlockEntityRenderer.SARCOPHAGUS_HEAD_LID);

        for (Block block : TGBlocks.getCoffins()) {
            String woodType = block.getDescriptionId().split("\\.")[2];
            event.register(new ResourceLocation(TheGraveyard.MOD_ID, "block/" + woodType + "_foot"));
            event.register(new ResourceLocation(TheGraveyard.MOD_ID, "block/" + woodType + "_foot_lid"));
            event.register(new ResourceLocation(TheGraveyard.MOD_ID, "block/" + woodType + "_head"));
            event.register(new ResourceLocation(TheGraveyard.MOD_ID, "block/" + woodType + "_head_lid"));
        }

    }

}
