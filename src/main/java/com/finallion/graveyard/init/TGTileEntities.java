package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blockentities.GravestoneBlockEntity;
import com.finallion.graveyard.blockentities.UrnBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGTileEntities {

    public static TileEntityType<GravestoneBlockEntity> GRAVESTONE_BLOCK_ENTITY;
    public static TileEntityType<UrnBlockEntity> URN_BLOCK_ENTITY;

    @SubscribeEvent
    public static void registerTE(RegistryEvent.Register<TileEntityType<?>> evt) {
        GRAVESTONE_BLOCK_ENTITY = TileEntityType.Builder.of(GravestoneBlockEntity::new, TGBlocks.GRAVESTONE).build(null);
        GRAVESTONE_BLOCK_ENTITY.setRegistryName(new ResourceLocation(TheGraveyard.MOD_ID, "gravestone_block_entity"));
        evt.getRegistry().register(GRAVESTONE_BLOCK_ENTITY);

        URN_BLOCK_ENTITY = TileEntityType.Builder.of(UrnBlockEntity::new,
                TGBlocks.BLACK_URN,
                TGBlocks.BLUE_URN,
                TGBlocks.LIGHT_BLUE_URN,
                TGBlocks.CYAN_URN,
                TGBlocks.BROWN_URN,
                TGBlocks.GRAY_URN,
                TGBlocks.LIGHT_GRAY_URN,
                TGBlocks.PURPLE_URN,
                TGBlocks.MAGENTA_URN,
                TGBlocks.PINK_URN,
                TGBlocks.RED_URN,
                TGBlocks.YELLOW_URN,
                TGBlocks.ORANGE_URN,
                TGBlocks.GREEN_URN,
                TGBlocks.LIME_URN,
                TGBlocks.WHITE_URN).build(null);
        URN_BLOCK_ENTITY.setRegistryName(new ResourceLocation(TheGraveyard.MOD_ID, "urn_block_entity"));
        evt.getRegistry().register(URN_BLOCK_ENTITY);


    }



}
