package com.finallion.graveyard.client;


import com.finallion.graveyard.blockentities.render.GravestoneBlockEntityRenderer;
import com.finallion.graveyard.entites.renders.AcolyteRender;
import com.finallion.graveyard.entites.renders.GhoulRenderer;
import com.finallion.graveyard.entites.renders.ReaperRenderer;
import com.finallion.graveyard.entites.renders.SkeletonCreeperRender;
import com.finallion.graveyard.init.TGBlocks;
import com.finallion.graveyard.init.TGEntities;
import com.finallion.graveyard.init.TGTileEntities;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
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

    }

    @SubscribeEvent
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(TGEntities.SKELETON_CREEPER, SkeletonCreeperRender::new);
        event.registerEntityRenderer(TGEntities.ACOLYTE, AcolyteRender::new);
        event.registerEntityRenderer(TGEntities.GHOUL, GhoulRenderer::new);
        event.registerEntityRenderer(TGEntities.REAPER, ReaperRenderer::new);

        event.registerBlockEntityRenderer(TGTileEntities.GRAVESTONE_BLOCK_ENTITY.get(), GravestoneBlockEntityRenderer::new);
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
    }
}
