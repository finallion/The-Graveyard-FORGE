package com.finallion.graveyard.client;


import com.finallion.graveyard.blockentities.render.GravestoneBlockEntityRenderer;
import com.finallion.graveyard.entites.renders.SkeletonCreeperRender;
import com.finallion.graveyard.init.TGBlocks;
import com.finallion.graveyard.init.TGEntities;
import com.finallion.graveyard.init.TGItems;
import com.finallion.graveyard.init.TGParticles;
import com.finallion.graveyard.utils.SpriteIdentifierRegistry;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TheGraveyardClient {
    private static final RenderType CUTOUT_MIPPED = RenderType.cutoutMipped();


    @SubscribeEvent
    public static void onBlockColorsInit(ColorHandlerEvent.Item event) {
        final BlockColors blockColors = event.getBlockColors();

        blockColors.register((unknown, lightReader, pos, unknown2) -> lightReader != null && pos != null ? BiomeColors.getAverageGrassColor(lightReader, pos) : GrassColors.get(0.5D, 1.0D), TGBlocks.TG_GRASS_BLOCK);
    }

    @SubscribeEvent
    public static void onItemColorsInit(ColorHandlerEvent.Item event) {
        final BlockColors blockColors = event.getBlockColors();
        final ItemColors itemColors = event.getItemColors();

        IItemColor itemBlockColourHandler = (stack, tintIndex) -> {
            BlockState state = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
            return blockColors.getColor(state, null, null, tintIndex);
        };


        itemColors.register(itemBlockColourHandler, TGBlocks.TG_GRASS_BLOCK);
    }

    @SubscribeEvent
    public static void clientInit() {

        TGParticles.init();

        RenderTypeLookup.setRenderLayer(TGBlocks.DARK_IRON_BARS, CUTOUT_MIPPED);
        RenderTypeLookup.setRenderLayer(TGBlocks.TG_GRASS_BLOCK, CUTOUT_MIPPED);

        // texture of the edit screen of the gravestone
        ResourceLocation texture = TGBlocks.GRAVESTONE_TEXTURE;
        SpriteIdentifierRegistry.INSTANCE.addRenderMaterial(new RenderMaterial(Atlases.SIGN_SHEET, texture));

        ClientRegistry.bindTileEntityRenderer(TGBlocks.GRAVESTONE_BLOCK_ENTITY.get(), GravestoneBlockEntityRenderer::new);

        // entities
        RenderingRegistry.registerEntityRenderingHandler(TGEntities.SKELETON_CREEPER.get(), SkeletonCreeperRender::new);

    }



}
