package com.finallion.graveyard.init;


import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blockentities.GravestoneBlockEntity;
import com.finallion.graveyard.blockentities.UrnBlockEntity;
import com.finallion.graveyard.blocks.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGBlocks {

    public static final ResourceLocation GRAVESTONE_TEXTURE = new ResourceLocation("minecraft", "block/polished_basalt_side");
    private static final DeferredRegister<Block> TG_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TheGraveyard.MOD_ID);


    // blocks backported from 1.17
    public static final RegistryObject<Block> TG_DEEPSLATE = TG_BLOCKS.register("tg_deepslate", () -> new TGDeepslateBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> TG_ROOTED_DIRT = TG_BLOCKS.register("tg_rooted_dirt", () -> new Block(AbstractBlock.Properties.copy(Blocks.DIRT)));
    public static final RegistryObject<Block> TG_TUFF = TG_BLOCKS.register("tg_tuff", () -> new Block(AbstractBlock.Properties.copy(Blocks.NETHERRACK)));
    public static final RegistryObject<Block> TG_MOSS_BLOCK = TG_BLOCKS.register("tg_moss_block", () -> new TGMossBlock(AbstractBlock.Properties.copy(Blocks.GRASS_BLOCK)));

    public static final RegistryObject<Block> TG_COBBLED_DEEPSLATE = TG_BLOCKS.register("tg_cobbled_deepslate", () -> new Block(AbstractBlock.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> TG_COBBLED_DEEPSLATE_STAIRS = TG_BLOCKS.register("tg_cobbled_deepslate_stairs", () -> new TGStairsBlock(AbstractBlock.Properties.copy(Blocks.STONE_STAIRS), TG_COBBLED_DEEPSLATE.get().defaultBlockState()));
    public static final RegistryObject<Block> TG_COBBLED_DEEPSLATE_SLAB = TG_BLOCKS.register("tg_cobbled_deepslate_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE_SLAB)));
    public static final RegistryObject<Block> TG_COBBLED_DEEPSLATE_WALL = TG_BLOCKS.register("tg_cobbled_deepslate_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> TG_POLISHED_DEEPSLATE = TG_BLOCKS.register("tg_polished_deepslate", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> TG_POLISHED_DEEPSLATE_STAIRS = TG_BLOCKS.register("tg_polished_deepslate_stairs", () -> new TGStairsBlock(AbstractBlock.Properties.copy(Blocks.STONE_STAIRS), TG_POLISHED_DEEPSLATE.get().defaultBlockState()));
    public static final RegistryObject<Block> TG_POLISHED_DEEPSLATE_SLAB = TG_BLOCKS.register("tg_polished_deepslate_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE_SLAB)));
    public static final RegistryObject<Block> TG_POLISHED_DEEPSLATE_WALL = TG_BLOCKS.register("tg_polished_deepslate_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> TG_DEEPSLATE_BRICKS = TG_BLOCKS.register("tg_deepslate_bricks", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> TG_DEEPSLATE_BRICK_STAIRS = TG_BLOCKS.register("tg_deepslate_brick_stairs", () -> new TGStairsBlock(AbstractBlock.Properties.copy(Blocks.STONE_STAIRS), TG_DEEPSLATE_BRICKS.get().defaultBlockState()));
    public static final RegistryObject<Block> TG_DEEPSLATE_BRICK_SLAB = TG_BLOCKS.register("tg_deepslate_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE_SLAB)));
    public static final RegistryObject<Block> TG_DEEPSLATE_BRICK_WALL = TG_BLOCKS.register("tg_deepslate_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> TG_CRACKED_DEEPSLATE_BRICKS = TG_BLOCKS.register("tg_cracked_deepslate_bricks", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE_BRICKS)));

    public static final RegistryObject<Block> TG_DEEPSLATE_TILES = TG_BLOCKS.register("tg_deepslate_tiles", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> TG_DEEPSLATE_TILE_STAIRS = TG_BLOCKS.register("tg_deepslate_tile_stairs", () -> new TGStairsBlock(AbstractBlock.Properties.copy(Blocks.STONE_STAIRS), TG_DEEPSLATE_TILES.get().defaultBlockState()));
    public static final RegistryObject<Block> TG_DEEPSLATE_TILE_SLAB = TG_BLOCKS.register("tg_deepslate_tile_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE_SLAB)));
    public static final RegistryObject<Block> TG_DEEPSLATE_TILE_WALL = TG_BLOCKS.register("tg_deepslate_tile_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> TG_CRACKED_DEEPSLATE_TILES = TG_BLOCKS.register("tg_cracked_deepslate_tiles", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE_BRICKS)));

    public static final RegistryObject<Block> TG_CHISELED_DEEPSLATE = TG_BLOCKS.register("tg_chiseled_deepslate", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE_BRICKS)));


    // helper blocks that get don't get replaced on world generation, generate ores or generate trees on
    public static final RegistryObject<Block> TG_GRASS_BLOCK = TG_BLOCKS.register("tg_grass_block", () -> new TGGrassBlock(AbstractBlock.Properties.copy(Blocks.GRASS_BLOCK).lootFrom(() -> Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> TG_DIRT = TG_BLOCKS.register("tg_dirt", () -> new TGStoneBlock(() -> Blocks.DIRT, AbstractBlock.Properties.copy(Blocks.DIRT).lootFrom(() -> Blocks.DIRT)));
    public static final RegistryObject<Block> TG_COARSE_DIRT = TG_BLOCKS.register("tg_coarse_dirt", () -> new TGStoneBlock(() -> Blocks.COARSE_DIRT, AbstractBlock.Properties.copy(Blocks.COARSE_DIRT).lootFrom(() -> Blocks.COARSE_DIRT)));
    public static final RegistryObject<Block> TG_ANDESITE = TG_BLOCKS.register("tg_andesite", () -> new TGStoneBlock(() -> Blocks.ANDESITE, AbstractBlock.Properties.copy(Blocks.ANDESITE).lootFrom(() -> Blocks.ANDESITE)));
    public static final RegistryObject<Block> TG_GRANITE = TG_BLOCKS.register("tg_granite", () -> new TGStoneBlock(() -> Blocks.GRANITE, AbstractBlock.Properties.copy(Blocks.GRANITE).lootFrom(() -> Blocks.GRANITE)));
    public static final RegistryObject<Block> TG_DIORITE = TG_BLOCKS.register("tg_diortie", () -> new TGStoneBlock(() -> Blocks.DIORITE, AbstractBlock.Properties.copy(Blocks.DIORITE).lootFrom(() -> Blocks.DIORITE)));
    public static final RegistryObject<Block> TG_STONE = TG_BLOCKS.register("tg_stone", () -> new TGStoneBlock(() -> Blocks.STONE, AbstractBlock.Properties.copy(Blocks.STONE).lootFrom(() -> Blocks.STONE)));
    public static final RegistryObject<Block> TG_PODZOL = TG_BLOCKS.register("tg_podzol", () -> new TGStoneBlock(() -> Blocks.PODZOL, AbstractBlock.Properties.copy(Blocks.PODZOL).lootFrom(() -> Blocks.PODZOL)));


    public static final RegistryObject<Block> DARK_IRON_BARS = TG_BLOCKS.register("dark_iron_bars", () -> new DarkIronBars(AbstractBlock.Properties.of(Material.METAL).strength(1.0F).noOcclusion()));
    public static final RegistryObject<Block> SKULL_WITH_RIB_CAGE = TG_BLOCKS.register("skull_with_rib_cage", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> LEANING_SKELETON = TG_BLOCKS.register("leaning_skeleton", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> SKULL_PILE = TG_BLOCKS.register("skull_pile", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> LYING_SKELETON = TG_BLOCKS.register("lying_skeleton", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> WITHER_SKULL_WITH_RIB_CAGE = TG_BLOCKS.register("wither_skull_with_rib_cage", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> LEANING_WITHER_SKELETON = TG_BLOCKS.register("leaning_wither_skeleton", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> WITHER_SKULL_PILE = TG_BLOCKS.register("wither_skull_pile", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> LYING_WITHER_SKELETON = TG_BLOCKS.register("lying_wither_skeleton", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> CREEPER_SKELETON = TG_BLOCKS.register("creeper_skeleton", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> SKELETON_HAND = TG_BLOCKS.register("skeleton_hand", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> WITHER_SKELETON_HAND = TG_BLOCKS.register("wither_skeleton_hand", () -> new BoneDisplayBlock());

    public static final RegistryObject<Block> BLACK_URN = TG_BLOCKS.register("black_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> WHITE_URN = TG_BLOCKS.register("white_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> LIGHT_GRAY_URN = TG_BLOCKS.register("light_gray_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> GRAY_URN = TG_BLOCKS.register("gray_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> BROWN_URN = TG_BLOCKS.register("brown_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> BLUE_URN = TG_BLOCKS.register("blue_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> LIGHT_BLUE_URN = TG_BLOCKS.register("light_blue_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> CYAN_URN = TG_BLOCKS.register("cyan_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> PURPLE_URN = TG_BLOCKS.register("purple_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> MAGENTA_URN = TG_BLOCKS.register("magenta_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> PINK_URN = TG_BLOCKS.register("pink_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> ORANGE_URN = TG_BLOCKS.register("orange_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> RED_URN = TG_BLOCKS.register("red_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> YELLOW_URN = TG_BLOCKS.register("yellow_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> GREEN_URN = TG_BLOCKS.register("green_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> LIME_URN = TG_BLOCKS.register("lime_urn", () -> new UrnBlock());

    public static final RegistryObject<Block> SMALL_BLACK_URN = TG_BLOCKS.register("small_black_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_WHITE_URN = TG_BLOCKS.register("small_white_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_LIGHT_GRAY_URN = TG_BLOCKS.register("small_light_gray_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_GRAY_URN = TG_BLOCKS.register("small_gray_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_BROWN_URN = TG_BLOCKS.register("small_brown_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_BLUE_URN = TG_BLOCKS.register("small_blue_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_LIGHT_BLUE_URN = TG_BLOCKS.register("small_light_blue_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_CYAN_URN = TG_BLOCKS.register("small_cyan_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_PURPLE_URN = TG_BLOCKS.register("small_purple_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_MAGENTA_URN = TG_BLOCKS.register("small_magenta_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_PINK_URN = TG_BLOCKS.register("small_pink_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_ORANGE_URN = TG_BLOCKS.register("small_orange_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_RED_URN = TG_BLOCKS.register("small_red_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_YELLOW_URN = TG_BLOCKS.register("small_yellow_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_GREEN_URN = TG_BLOCKS.register("small_green_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_LIME_URN = TG_BLOCKS.register("small_lime_urn", () -> new UrnBlock());

    public static final RegistryObject<Block> VASE_BLOCK = TG_BLOCKS.register("vase_block", () -> new VaseBlock());

    public static final RegistryObject<Block> GRAVESTONE = TG_BLOCKS.register("gravestone", () -> new GravestoneBlock(GRAVESTONE_TEXTURE));


    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, TheGraveyard.MOD_ID);

    public static final RegistryObject<TileEntityType<GravestoneBlockEntity>> GRAVESTONE_BLOCK_ENTITY = register("gravestone_block_entity", () -> TileEntityType.Builder.of(GravestoneBlockEntity::new, GRAVESTONE.get()).build(null));
    public static final RegistryObject<TileEntityType<UrnBlockEntity>> URN_BLOCK_ENTITY = register("urn_block_entity", () -> TileEntityType.Builder.of(UrnBlockEntity::new,
            BLACK_URN.get(),
            BLUE_URN.get(),
            LIGHT_BLUE_URN.get(),
            CYAN_URN.get(),
            BROWN_URN.get(),
            GRAY_URN.get(),
            LIGHT_GRAY_URN.get(),
            PURPLE_URN.get(),
            MAGENTA_URN.get(),
            PINK_URN.get(),
            RED_URN.get(),
            YELLOW_URN.get(),
            ORANGE_URN.get(),
            GREEN_URN.get(),
            LIME_URN.get(),
            WHITE_URN.get()).build(null));

    private static <T extends TileEntityType<?>> RegistryObject<T> register(String name, Supplier<T> tileEntity) {
        return TILE_ENTITIES.register(name, tileEntity);
    }



}
