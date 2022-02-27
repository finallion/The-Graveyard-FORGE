package com.finallion.graveyard.client;


import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blockentities.render.BrazierBlockEntityRenderer;
import com.finallion.graveyard.blockentities.render.GravestoneBlockEntityRenderer;
import com.finallion.graveyard.blockentities.render.SarcophagusBlockEntityRenderer;
import com.finallion.graveyard.entities.renders.*;
import com.finallion.graveyard.init.TGBlocks;
import com.finallion.graveyard.init.TGEntities;
import com.finallion.graveyard.init.TGTileEntities;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
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
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;



public class TheGraveyardClient {
    private static final RenderType CUTOUT_MIPPED = RenderType.cutoutMipped();


    public TheGraveyardClient() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::clientInit);
        modEventBus.addListener(this::onBlockColorsInit);
        modEventBus.addListener(this::onItemColorsInit);
        modEventBus.addListener(this::registerEntityRenderers);
        modEventBus.addListener(this::onRegisterModels);

    }

    @SubscribeEvent
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(TGEntities.SKELETON_CREEPER, SkeletonCreeperRender::new);
        event.registerEntityRenderer(TGEntities.ACOLYTE, AcolyteRender::new);
        event.registerEntityRenderer(TGEntities.GHOUL, GhoulRenderer::new);
        event.registerEntityRenderer(TGEntities.REAPER, ReaperRenderer::new);
        event.registerEntityRenderer(TGEntities.REVENANT, RevenantRenderer::new);
        event.registerEntityRenderer(TGEntities.NIGHTMARE, NightmareRenderer::new);

        event.registerBlockEntityRenderer(TGTileEntities.GRAVESTONE_BLOCK_ENTITY.get(), GravestoneBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(TGTileEntities.SARCOPHAGUS_BLOCK_ENTITY.get(), SarcophagusBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(TGTileEntities.BRAZIER_BLOCK_ENTITY.get(), BrazierBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public void onBlockColorsInit(ColorHandlerEvent.Item event) {
        final BlockColors blockColors = event.getBlockColors();
        blockColors.register((state, reader, pos, color) -> reader != null && pos != null ? BiomeColors.getAverageGrassColor(reader, pos) : GrassColor.get(0.5D, 1.0D), TGBlocks.TG_GRASS_BLOCK);

    }

    @SubscribeEvent
    public void onItemColorsInit(ColorHandlerEvent.Item event) {
        final BlockColors blockColors = event.getBlockColors();
        final ItemColors itemColors = event.getItemColors();


        itemColors.register((stack, color) -> {
            BlockState blockstate = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
            return blockColors.getColor(blockstate, null, null, color);
        }, TGBlocks.TG_GRASS_BLOCK);

    }

    @SubscribeEvent
    public void clientInit(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.DARK_IRON_BARS, CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.TG_GRASS_BLOCK, CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.SKULL_WITH_RIB_CAGE, CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.WITHER_SKULL_WITH_RIB_CAGE, CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.LEANING_SKELETON, CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.LEANING_WITHER_SKELETON, CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.LYING_SKELETON, CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.LYING_WITHER_SKELETON, CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.SOUL_FIRE_BRAZIER, CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.FIRE_BRAZIER, CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.CANDLE_HOLDER, CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.DARK_IRON_DOOR, CUTOUT_MIPPED);
        ItemBlockRenderTypes.setRenderLayer(TGBlocks.DARK_IRON_TRAPDOOR, CUTOUT_MIPPED);

    }

    @SubscribeEvent
    public void onRegisterModels(final ModelRegistryEvent event) {
        ForgeModelBakery.addSpecialModel(SarcophagusBlockEntityRenderer.SARCOPHAGUS_FOOT);
        ForgeModelBakery.addSpecialModel(SarcophagusBlockEntityRenderer.SARCOPHAGUS_HEAD);
        ForgeModelBakery.addSpecialModel(SarcophagusBlockEntityRenderer.SARCOPHAGUS_FOOT_LID);
        ForgeModelBakery.addSpecialModel(SarcophagusBlockEntityRenderer.SARCOPHAGUS_HEAD_LID);

        for (Block block : TGBlocks.coffins) {
            String woodType = block.getDescriptionId().split("\\.")[2];
            ForgeModelBakery.addSpecialModel(new ResourceLocation(TheGraveyard.MOD_ID, "block/" + woodType + "_foot"));
            ForgeModelBakery.addSpecialModel(new ResourceLocation(TheGraveyard.MOD_ID, "block/" + woodType + "_foot_lid"));
            ForgeModelBakery.addSpecialModel(new ResourceLocation(TheGraveyard.MOD_ID, "block/" + woodType + "_head"));
            ForgeModelBakery.addSpecialModel(new ResourceLocation(TheGraveyard.MOD_ID, "block/" + woodType + "_head_lid"));
        }

    }




}
