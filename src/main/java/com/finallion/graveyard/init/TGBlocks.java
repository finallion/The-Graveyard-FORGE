package com.finallion.graveyard.init;


import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blocks.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;


@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGBlocks {
    public static List<Block> blocks_list = new ArrayList<Block>();

    public static final ResourceLocation GRAVESTONE_TEXTURE = new ResourceLocation("minecraft", "block/polished_basalt_side");
    public static final ResourceLocation COBBLESTONE_GRAVESTONE_TEXTURE = new ResourceLocation("minecraft", "block/cobblestone");
    public static final ResourceLocation MOSSY_COBBLESTONE_GRAVESTONE_TEXTURE = new ResourceLocation("minecraft", "block/mossy_cobblestone");
    public static final ResourceLocation DEEPSLATE_GRAVESTONE_TEXTURE = new ResourceLocation(TheGraveyard.MOD_ID, "block/deepslate");

    public static Block registerBlock(Block block, String name) {
        block.setRegistryName(new ResourceLocation(TheGraveyard.MOD_ID, name));

        // block item registry
        Item.Properties properties = new Item.Properties().tab(TheGraveyard.GROUP);
        BlockItem item = new BlockItem(block, properties);
        item.setRegistryName(new ResourceLocation(TheGraveyard.MOD_ID, name));

        blocks_list.add(block);
        TGItems.items_list.add(item);

        return block;
    }

    public static Block registerBlockWithoutGroup(Block block, String name) {
        block.setRegistryName(new ResourceLocation(TheGraveyard.MOD_ID, name));
        BlockItem item = new BlockItem(block, new Item.Properties());
        item.setRegistryName(new ResourceLocation(TheGraveyard.MOD_ID, name));

        blocks_list.add(block);
        TGItems.items_list.add(item);

        return block;
    }

    public static Block registerBlockWithoutItem(Block block, String name) {
        block.setRegistryName(new ResourceLocation(TheGraveyard.MOD_ID, name));
        blocks_list.add(block);

        return block;
    }



    // helper blocks that get don't get replaced on world generation, generate ores or generate trees on
    public static Block TG_DEEPSLATE;
    public static Block TG_ROOTED_DIRT;
    public static Block TG_TUFF;
    public static Block TG_MOSS_BLOCK;

    public static Block TG_GRASS_BLOCK;
    public static Block TG_DIRT;
    public static Block TG_COARSE_DIRT;
    public static Block TG_ANDESITE;
    public static Block TG_GRANITE;
    public static Block TG_DIORITE;
    public static Block TG_STONE;
    public static Block TG_PODZOL;


    public static Block DARK_IRON_BARS;
    public static Block SKULL_WITH_RIB_CAGE;
    public static Block LEANING_SKELETON;
    public static Block SKULL_PILE;
    public static Block LYING_SKELETON;
    public static Block WITHER_SKULL_WITH_RIB_CAGE;
    public static Block LEANING_WITHER_SKELETON;
    public static Block WITHER_SKULL_PILE;
    public static Block LYING_WITHER_SKELETON;
    public static Block CREEPER_SKELETON;
    public static Block SKELETON_HAND;
    public static Block WITHER_SKELETON_HAND;


    public static Block VASE_BLOCK;

    public static Block GRAVESTONE;
    public static Block COBBLESTONE_GRAVESTONE;
    public static Block MOSSY_COBBLESTONE_GRAVESTONE;
    public static Block DEEPSLATE_GRAVESTONE;

    public static Block BLACK_URN;
    public static Block WHITE_URN;
    public static Block LIGHT_GRAY_URN;
    public static Block GRAY_URN;
    public static Block BROWN_URN;
    public static Block BLUE_URN;
    public static Block LIGHT_BLUE_URN;
    public static Block CYAN_URN;
    public static Block PURPLE_URN;
    public static Block MAGENTA_URN;
    public static Block PINK_URN;
    public static Block ORANGE_URN;
    public static Block RED_URN;
    public static Block YELLOW_URN;
    public static Block GREEN_URN;
    public static Block LIME_URN;

    public static Block SMALL_BLACK_URN;
    public static Block SMALL_WHITE_URN;
    public static Block SMALL_LIGHT_GRAY_URN;
    public static Block SMALL_GRAY_URN;
    public static Block SMALL_BROWN_URN;
    public static Block SMALL_BLUE_URN;
    public static Block SMALL_LIGHT_BLUE_URN;
    public static Block SMALL_CYAN_URN;
    public static Block SMALL_PURPLE_URN;
    public static Block SMALL_MAGENTA_URN;
    public static Block SMALL_PINK_URN;
    public static Block SMALL_ORANGE_URN;
    public static Block SMALL_RED_URN;
    public static Block SMALL_YELLOW_URN;
    public static Block SMALL_GREEN_URN;
    public static Block SMALL_LIME_URN;

    @SubscribeEvent
    public static void initBlocks(RegistryEvent.Register<Block> event) {
        TG_ROOTED_DIRT = registerBlockWithoutGroup(new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)), "tg_rooted_dirt");
        TG_TUFF = registerBlockWithoutGroup(new Block(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)), "tg_tuff");
        TG_MOSS_BLOCK = registerBlockWithoutGroup(new TGMossBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)), "tg_moss_block");
        TG_DEEPSLATE = registerBlockWithoutGroup(new TGDeepslateBlock(BlockBehaviour.Properties.copy(Blocks.STONE)), "tg_deepslate");

        // helper blocks that get don't get replaced on world generation, generate ores or generate trees on
        TG_GRASS_BLOCK = registerBlockWithoutGroup(new TGGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).lootFrom(() -> Blocks.GRASS_BLOCK)), "tg_grass_block");
        TG_DIRT = registerBlockWithoutGroup(new TGStoneBlock(() -> Blocks.DIRT, BlockBehaviour.Properties.copy(Blocks.DIRT).lootFrom(() -> Blocks.DIRT)), "tg_dirt");
        TG_COARSE_DIRT = registerBlockWithoutGroup(new TGStoneBlock(() -> Blocks.COARSE_DIRT, BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT).lootFrom(() -> Blocks.COARSE_DIRT)), "tg_coarse_dirt");
        TG_ANDESITE = registerBlockWithoutGroup(new TGStoneBlock(() -> Blocks.ANDESITE, BlockBehaviour.Properties.copy(Blocks.ANDESITE).lootFrom(() -> Blocks.ANDESITE)), "tg_andesite");
        TG_GRANITE = registerBlockWithoutGroup(new TGStoneBlock(() -> Blocks.GRANITE, BlockBehaviour.Properties.copy(Blocks.GRANITE).lootFrom(() -> Blocks.GRANITE)), "tg_granite");
        TG_DIORITE = registerBlockWithoutGroup(new TGStoneBlock(() -> Blocks.DIORITE, BlockBehaviour.Properties.copy(Blocks.DIORITE).lootFrom(() -> Blocks.DIORITE)), "tg_diorite");
        TG_STONE = registerBlockWithoutGroup(new TGStoneBlock(() -> Blocks.STONE, BlockBehaviour.Properties.copy(Blocks.STONE).lootFrom(() -> Blocks.STONE)), "tg_stone");
        TG_PODZOL = registerBlockWithoutGroup(new TGStoneBlock(() -> Blocks.PODZOL, BlockBehaviour.Properties.copy(Blocks.PODZOL).lootFrom(() -> Blocks.PODZOL)), "tg_podzol");


        DARK_IRON_BARS = registerBlock(new DarkIronBars(BlockBehaviour.Properties.of(Material.METAL).strength(1.0F).noOcclusion()), "dark_iron_bars");

        SKULL_WITH_RIB_CAGE = registerBlock(new BoneDisplayBlock(), "skull_with_rib_cage");
        LEANING_SKELETON = registerBlock(new BoneDisplayBlock(), "leaning_skeleton");
        SKULL_PILE = registerBlock(new BoneDisplayBlock(), "skull_pile");
        LYING_SKELETON = registerBlock(new BoneDisplayBlock(), "lying_skeleton");
        WITHER_SKULL_WITH_RIB_CAGE = registerBlock(new BoneDisplayBlock(), "wither_skull_with_rib_cage");
        LEANING_WITHER_SKELETON = registerBlock(new BoneDisplayBlock(), "leaning_wither_skeleton");
        WITHER_SKULL_PILE = registerBlock(new BoneDisplayBlock(), "wither_skull_pile");
        LYING_WITHER_SKELETON = registerBlock(new BoneDisplayBlock(), "lying_wither_skeleton");
        CREEPER_SKELETON = registerBlock(new BoneDisplayBlock(), "creeper_skeleton");
        SKELETON_HAND = registerBlock(new BoneDisplayBlock(), "skeleton_hand");
        WITHER_SKELETON_HAND = registerBlock(new BoneDisplayBlock(), "wither_skeleton_hand");


        VASE_BLOCK = registerBlock(new VaseBlock(), "vase_block");

        GRAVESTONE = registerBlock(new GravestoneBlock(GRAVESTONE_TEXTURE), "gravestone");
        COBBLESTONE_GRAVESTONE = registerBlock(new GravestoneBlock(COBBLESTONE_GRAVESTONE_TEXTURE), "cobblestone_gravestone");
        MOSSY_COBBLESTONE_GRAVESTONE = registerBlock(new GravestoneBlock(MOSSY_COBBLESTONE_GRAVESTONE_TEXTURE), "mossy_cobblestone_gravestone");
        DEEPSLATE_GRAVESTONE = registerBlock(new GravestoneBlock(DEEPSLATE_GRAVESTONE_TEXTURE), "deepslate_gravestone");

        BLACK_URN = registerBlock(new UrnBlock(), "black_urn");
        BROWN_URN = registerBlock(new UrnBlock(), "brown_urn");
        WHITE_URN = registerBlock(new UrnBlock(), "white_urn");
        GRAY_URN = registerBlock(new UrnBlock(), "gray_urn");
        LIGHT_GRAY_URN = registerBlock(new UrnBlock(), "light_gray_urn");
        BLUE_URN = registerBlock(new UrnBlock(), "blue_urn");
        CYAN_URN = registerBlock(new UrnBlock(), "cyan_urn");
        LIGHT_BLUE_URN = registerBlock(new UrnBlock(), "light_blue_urn");
        RED_URN = registerBlock(new UrnBlock(), "red_urn");
        ORANGE_URN = registerBlock(new UrnBlock(), "orange_urn");
        YELLOW_URN = registerBlock(new UrnBlock(), "yellow_urn");
        PINK_URN = registerBlock(new UrnBlock(), "pink_urn");
        MAGENTA_URN = registerBlock(new UrnBlock(), "magenta_urn");
        PURPLE_URN = registerBlock(new UrnBlock(), "purple_urn");
        GREEN_URN = registerBlock(new UrnBlock(), "green_urn");
        LIME_URN = registerBlock(new UrnBlock(), "lime_urn");

        SMALL_BLACK_URN = registerBlock(new UrnBlock(), "small_black_urn");
        SMALL_BROWN_URN = registerBlock(new UrnBlock(), "small_brown_urn");
        SMALL_WHITE_URN = registerBlock(new UrnBlock(), "small_white_urn");
        SMALL_GRAY_URN = registerBlock(new UrnBlock(), "small_gray_urn");
        SMALL_LIGHT_GRAY_URN = registerBlock(new UrnBlock(), "small_light_gray_urn");
        SMALL_BLUE_URN = registerBlock(new UrnBlock(), "small_blue_urn");
        SMALL_CYAN_URN = registerBlock(new UrnBlock(), "small_cyan_urn");
        SMALL_LIGHT_BLUE_URN = registerBlock(new UrnBlock(), "small_light_blue_urn");
        SMALL_RED_URN = registerBlock(new UrnBlock(), "small_red_urn");
        SMALL_ORANGE_URN = registerBlock(new UrnBlock(), "small_orange_urn");
        SMALL_YELLOW_URN = registerBlock(new UrnBlock(), "small_yellow_urn");
        SMALL_PINK_URN = registerBlock(new UrnBlock(), "small_pink_urn");
        SMALL_MAGENTA_URN = registerBlock(new UrnBlock(), "small_magenta_urn");
        SMALL_PURPLE_URN = registerBlock(new UrnBlock(), "small_purple_urn");
        SMALL_GREEN_URN = registerBlock(new UrnBlock(), "small_green_urn");
        SMALL_LIME_URN = registerBlock(new UrnBlock(), "small_lime_urn");

        event.getRegistry().registerAll(blocks_list.toArray(new Block[0]));
    }


}
