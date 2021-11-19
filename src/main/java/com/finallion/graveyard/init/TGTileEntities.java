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
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


public class TGTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, TheGraveyard.MOD_ID);

    public static final RegistryObject<TileEntityType<GravestoneBlockEntity>> GRAVESTONE_BLOCK_ENTITY = register("gravestone_block_entity", () -> TileEntityType.Builder.of(GravestoneBlockEntity::new,
            TGBlocks.GRAVESTONE,
            TGBlocks.DEEPSLATE_GRAVESTONE,
            TGBlocks.MOSSY_COBBLESTONE_GRAVESTONE,
            TGBlocks.COBBLESTONE_GRAVESTONE
    ).build(null));

    public static final RegistryObject<TileEntityType<UrnBlockEntity>> URN_BLOCK_ENTITY = register("urn_block_entity", () -> TileEntityType.Builder.of(UrnBlockEntity::new,  TGBlocks.BLACK_URN,
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
            TGBlocks.WHITE_URN).build(null));

    private static <T extends TileEntityType<?>> RegistryObject<T> register(String name, Supplier<T> tileEntity) {
        return TILE_ENTITIES.register(name, tileEntity);
    }



}
