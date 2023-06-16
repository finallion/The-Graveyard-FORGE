package com.finallion.graveyard.init;


import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blocks.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;


public class TGBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TheGraveyard.MOD_ID);
    public static List<Block> coffins = new ArrayList<Block>();

    public static final ResourceLocation POLISHED_BASALT_GRAVESTONE_TEXTURE = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/polished_basalt.png");
    public static final ResourceLocation COBBLESTONE_GRAVESTONE_TEXTURE = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/cobblestone.png");
    public static final ResourceLocation MOSSY_COBBLESTONE_GRAVESTONE_TEXTURE = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/mossy_cobblestone.png");
    public static final ResourceLocation DEEPSLATE_GRAVESTONE_TEXTURE = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/deepslate.png");
    public static final ResourceLocation BLACKSTONE_GRAVESTONE_TEXTURE = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/blackstone.png");
    public static final ResourceLocation BRICKS_GRAVESTONE_TEXTURE = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/bricks.png");
    public static final ResourceLocation CRACKED_BLACKSTONE_GRAVESTONE_TEXTURE = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/cracked_blackstoe.png");
    public static final ResourceLocation GILDED_BLACKSTONE_GRAVESTONE_TEXTURE = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/gilded_blackstone.png");
    public static final ResourceLocation MOSSY_STONE_BRICKS_GRAVESTONE_TEXTURE = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/mossy_stone_bricks.png");
    public static final ResourceLocation QUARTZ_BRICKS_GRAVESTONE_TEXTURE = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/quartz_bricks.png");
    public static final ResourceLocation RED_SANDSTONE_GRAVESTONE_TEXTURE = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/red_sandstone.png");
    public static final ResourceLocation SANDSTONE_GRAVESTONE_TEXTURE = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/sandstone.png");
    public static final ResourceLocation STONE_BRICKS_GRAVESTONE_TEXTURE = new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/stone_bricks.png");

    public static final RegistryObject<Block> TG_ROOTED_DIRT = BLOCKS.register("tg_rooted_dirt", () -> new Block(AbstractBlock.Properties.copy(Blocks.DIRT).lootFrom(() -> Blocks.DIRT)));
    public static final RegistryObject<Block> TG_TUFF = BLOCKS.register("tg_tuff", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> TG_MOSS_BLOCK = BLOCKS.register("tg_moss_block", () -> new TGMossBlock(AbstractBlock.Properties.copy(Blocks.GRASS_BLOCK).noDrops()));
    public static final RegistryObject<Block> TG_MOSS_CARPET_BLOCK = BLOCKS.register("tg_moss_carpet_block", () -> new CarpetBlock(DyeColor.GREEN, AbstractBlock.Properties.copy(Blocks.GRASS_BLOCK).noDrops()));

    public static final RegistryObject<Block> TG_DEEPSLATE = BLOCKS.register("tg_deepslate", () -> new TGDeepslateBlock(AbstractBlock.Properties.copy(Blocks.STONE)));

    public static RegistryObject<Block> TG_COBBLED_DEEPSLATE = BLOCKS.register("tg_cobbled_deepslate", () -> new TGDeepslateBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static RegistryObject<Block> TG_COBBLED_DEEPSLATE_STAIRS = BLOCKS.register("tg_cobbled_deepslate_stairs", () -> new TGStairsBlock(AbstractBlock.Properties.copy(Blocks.STONE), TGBlocks.TG_COBBLED_DEEPSLATE.get().defaultBlockState()));
    public static RegistryObject<Block> TG_COBBLED_DEEPSLATE_SLAB = BLOCKS.register("tg_cobbled_deepslate_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static RegistryObject<Block> TG_COBBLED_DEEPSLATE_WALL = BLOCKS.register("tg_cobbled_deepslate_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.STONE)));

    public static RegistryObject<Block> TG_POLISHED_DEEPSLATE = BLOCKS.register("tg_polished_deepslate", () -> new TGDeepslateBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static RegistryObject<Block> TG_POLISHED_DEEPSLATE_STAIRS = BLOCKS.register("tg_polished_deepslate_stairs", () -> new TGStairsBlock(AbstractBlock.Properties.copy(Blocks.STONE), TGBlocks.TG_POLISHED_DEEPSLATE.get().defaultBlockState()));
    public static RegistryObject<Block> TG_POLISHED_DEEPSLATE_SLAB = BLOCKS.register("tg_polished_deepslate_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static RegistryObject<Block> TG_POLISHED_DEEPSLATE_WALL = BLOCKS.register("tg_polished_deepslate_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.STONE)));

    public static RegistryObject<Block> TG_DEEPSLATE_BRICKS = BLOCKS.register("tg_deepslate_bricks", () -> new TGDeepslateBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static RegistryObject<Block> TG_DEEPSLATE_BRICK_STAIRS = BLOCKS.register("tg_deepslate_brick_stairs", () -> new TGStairsBlock(AbstractBlock.Properties.copy(Blocks.STONE), TGBlocks.TG_DEEPSLATE_BRICKS.get().defaultBlockState()));
    public static RegistryObject<Block> TG_DEEPSLATE_BRICK_SLAB = BLOCKS.register("tg_deepslate_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static RegistryObject<Block> TG_DEEPSLATE_BRICK_WALL = BLOCKS.register("tg_deepslate_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static RegistryObject<Block> TG_CRACKED_DEEPSLATE_BRICKS = BLOCKS.register("tg_cracked_deepslate_bricks", () -> new TGDeepslateBlock(AbstractBlock.Properties.copy(Blocks.STONE)));

    public static RegistryObject<Block> TG_DEEPSLATE_TILES = BLOCKS.register("tg_deepslate_tiles", () -> new TGDeepslateBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static RegistryObject<Block> TG_DEEPSLATE_TILE_STAIRS = BLOCKS.register("tg_deepslate_tiles_stairs", () -> new TGStairsBlock(AbstractBlock.Properties.copy(Blocks.STONE), TGBlocks.TG_DEEPSLATE_TILES.get().defaultBlockState()));
    public static RegistryObject<Block> TG_DEEPSLATE_TILE_SLAB = BLOCKS.register("tg_deepslate_tiles_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static RegistryObject<Block> TG_DEEPSLATE_TILE_WALL = BLOCKS.register("tg_deepslate_tiles_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static RegistryObject<Block> TG_CRACKED_DEEPSLATE_TILES = BLOCKS.register("tg_cracked_deepslate_tiles", () -> new TGDeepslateBlock(AbstractBlock.Properties.copy(Blocks.STONE)));

    public static RegistryObject<Block> TG_CHISELED_DEEPSLATE = BLOCKS.register("tg_chiseled_deepslate", () -> new TGDeepslateBlock(AbstractBlock.Properties.copy(Blocks.STONE)));

    public static final Block TG_MOSS_CARPET =  new CarpetBlock(FabricBlockSettings.of(Material.PLANT, MapColor.GREEN).strength(0.1F));
            Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "tg_moss_carpet"), TG_MOSS_CARPET);

    public static final Block CANDLE = new CandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.PALE_YELLOW).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE));
    public static final Block WHITE_CANDLE = new CandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.WHITE_GRAY).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE));
    public static final Block ORANGE_CANDLE = new CandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.ORANGE).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE));
    public static final Block MAGENTA_CANDLE = new CandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.MAGENTA).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE));
    public static final Block LIGHT_BLUE_CANDLE = new CandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.LIGHT_BLUE).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE));
    public static final Block YELLOW_CANDLE = new CandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.YELLOW).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE));
    public static final Block LIME_CANDLE = new CandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.LIME).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE));
    public static final Block PINK_CANDLE = new CandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.PINK).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE));
    public static final Block GRAY_CANDLE = new CandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.GRAY).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE));
    public static final Block LIGHT_GRAY_CANDLE = new CandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.LIGHT_GRAY).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE));
    public static final Block CYAN_CANDLE = new CandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.CYAN).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE));
    public static final Block PURPLE_CANDLE = new CandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.PURPLE).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE));
    public static final Block BLUE_CANDLE = new CandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.BLUE).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE));
    public static final Block BROWN_CANDLE = new CandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.BROWN).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE));
    public static final Block GREEN_CANDLE = new CandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.GREEN).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE));
    public static final Block RED_CANDLE = new CandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.RED).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE));
    public static final Block BLACK_CANDLE = new CandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.BLACK).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE));
            Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "candle"), CANDLE);
        Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "white_candle"), WHITE_CANDLE);
        Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "orange_candle"), ORANGE_CANDLE);
        Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "magenta_candle"), MAGENTA_CANDLE);
        Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "light_blue_candle"), LIGHT_BLUE_CANDLE);
        Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "yellow_candle"), YELLOW_CANDLE);
        Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "lime_candle"), LIME_CANDLE);
        Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "pink_candle"), PINK_CANDLE);
        Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "gray_candle"), GRAY_CANDLE);
        Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "light_gray_candle"), LIGHT_GRAY_CANDLE);
        Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "cyan_candle"), CYAN_CANDLE);
        Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "purple_candle"), PURPLE_CANDLE);
        Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "blue_candle"), BLUE_CANDLE);
        Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "brown_candle"), BROWN_CANDLE);
        Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "green_candle"), GREEN_CANDLE);
        Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "red_candle"), RED_CANDLE);
        Registry.register(Registries.BLOCK, new Identifier(TheGraveyard.MOD_ID, "black_candle"), BLACK_CANDLE);


    // helper blocks that get don't get replaced on world generation, generate ores or generate trees on
    public static final RegistryObject<Block> TG_GRASS_BLOCK = BLOCKS.register("tg_grass_block", () -> new TGGrassBlock(AbstractBlock.Properties.copy(Blocks.GRASS_BLOCK).lootFrom(() -> Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> TG_DIRT = BLOCKS.register("tg_dirt", () -> new TGStoneBlock(() -> Blocks.DIRT, AbstractBlock.Properties.copy(Blocks.DIRT).lootFrom(() -> Blocks.DIRT)));
    public static final RegistryObject<Block> TG_COARSE_DIRT = BLOCKS.register("tg_coarse_dirt", () -> new TGStoneBlock(() -> Blocks.COARSE_DIRT, AbstractBlock.Properties.copy(Blocks.COARSE_DIRT).lootFrom(() -> Blocks.COARSE_DIRT)));
    public static final RegistryObject<Block> TG_ANDESITE = BLOCKS.register("tg_andesite", () -> new TGStoneBlock(() -> Blocks.ANDESITE, AbstractBlock.Properties.copy(Blocks.ANDESITE).lootFrom(() -> Blocks.ANDESITE)));
    public static final RegistryObject<Block> TG_GRANITE = BLOCKS.register("tg_granite", () -> new TGStoneBlock(() -> Blocks.GRANITE, AbstractBlock.Properties.copy(Blocks.GRANITE).lootFrom(() -> Blocks.GRANITE)));
    public static final RegistryObject<Block> TG_DIORITE = BLOCKS.register("tg_diorite", () -> new TGStoneBlock(() -> Blocks.DIORITE, AbstractBlock.Properties.copy(Blocks.DIORITE).lootFrom(() -> Blocks.DIORITE)));
    public static final RegistryObject<Block> TG_STONE = BLOCKS.register("tg_stone", () -> new TGStoneBlock(() -> Blocks.STONE, AbstractBlock.Properties.copy(Blocks.STONE).lootFrom(() -> Blocks.STONE)));
    public static final RegistryObject<Block> TG_PODZOL = BLOCKS.register("tg_podzol", () -> new TGStoneBlock(() -> Blocks.PODZOL, AbstractBlock.Properties.copy(Blocks.PODZOL).lootFrom(() -> Blocks.PODZOL)));


    public static final RegistryObject<Block> DARK_IRON_BARS = BLOCKS.register("dark_iron_bars", () -> new DarkIronBars(AbstractBlock.Properties.of(Material.METAL).strength(1.0F).noOcclusion()));
    public static final RegistryObject<Block> SOUL_FIRE_BRAZIER = BLOCKS.register("soul_fire_brazier", () -> new BrazierBlock(AbstractBlock.Properties.of(Material.METAL).strength(1.0F).noOcclusion().lightLevel(BrazierBlock.STATE_TO_LUMINANCE).sound(SoundType.METAL), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Block> FIRE_BRAZIER = BLOCKS.register("fire_brazier", () -> new BrazierBlock(AbstractBlock.Properties.of(Material.METAL).strength(1.0F).noOcclusion().lightLevel(BrazierBlock.STATE_TO_LUMINANCE).sound(SoundType.METAL), ParticleTypes.FLAME));
    public static final RegistryObject<Block> PEDESTAL = BLOCKS.register("pedestal", () -> new PedestalBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.0F).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> CANDLE_HOLDER = BLOCKS.register("candle_holder", () -> new CandleHolderBlock(AbstractBlock.Properties.of(Material.METAL).strength(1.0F).noOcclusion().sound(SoundType.METAL)));
    public static final RegistryObject<Block> DARK_IRON_TRAPDOOR = BLOCKS.register("dark_iron_trapdoor", () -> new TGTrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion()));
    public static final RegistryObject<Block> DARK_IRON_DOOR = BLOCKS.register("dark_iron_door", () -> new TGDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.RAW_IRON).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion()));
    public static final RegistryObject<Block> DARK_IRON_BLOCK = BLOCKS.register("dark_iron_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.RAW_IRON).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> SKULL_WITH_RIB_CAGE = BLOCKS.register("skull_with_rib_cage", BoneDisplayBlock::new);
    public static final RegistryObject<Block> LEANING_SKELETON = BLOCKS.register("leaning_skeleton", BoneDisplayBlock::new);
    public static final RegistryObject<Block> SKULL_PILE = BLOCKS.register("skull_pile", BoneDisplayBlock::new);
    public static final RegistryObject<Block> LYING_SKELETON = BLOCKS.register("lying_skeleton", BoneDisplayBlock::new);
    public static final RegistryObject<Block> WITHER_SKULL_WITH_RIB_CAGE = BLOCKS.register("wither_skull_with_rib_cage", BoneDisplayBlock::new);
    public static final RegistryObject<Block> LEANING_WITHER_SKELETON = BLOCKS.register("leaning_wither_skeleton", BoneDisplayBlock::new);
    public static final RegistryObject<Block> WITHER_SKULL_PILE = BLOCKS.register("wither_skull_pile", BoneDisplayBlock::new);
    public static final RegistryObject<Block> LYING_WITHER_SKELETON = BLOCKS.register("lying_wither_skeleton", BoneDisplayBlock::new);
    public static final RegistryObject<Block> CREEPER_SKELETON = BLOCKS.register("creeper_skeleton", BoneDisplayBlock::new);
    public static final RegistryObject<Block> SKELETON_HAND = BLOCKS.register("skeleton_hand", BoneDisplayBlock::new);
    public static final RegistryObject<Block> WITHER_SKELETON_HAND = BLOCKS.register("wither_skeleton_hand", BoneDisplayBlock::new);
    public static final RegistryObject<Block> BONE_REMAINS = BLOCKS.register("bone_remains", BoneDisplayBlock::new);
    public static final RegistryObject<Block> WITHER_BONE_REMAINS = BLOCKS.register("wither_bone_remains", BoneDisplayBlock::new);
    public static final RegistryObject<Block> LATERALLY_LYING_SKELETON = BLOCKS.register("laterally_lying_skeleton", BoneDisplayBlock::new);
    public static final RegistryObject<Block> LATERALLY_LYING_WITHER_SKELETON = BLOCKS.register("laterally_lying_wither_skeleton", BoneDisplayBlock::new);
    public static final RegistryObject<Block> SKULL_ON_PIKE = BLOCKS.register("skull_on_pike", BoneDisplayBlock::new);
    public static final RegistryObject<Block> WITHER_SKULL_ON_PIKE = BLOCKS.register("wither_skull_on_pike", BoneDisplayBlock::new);
    public static final RegistryObject<Block> TORSO_PILE = BLOCKS.register("torso_pile", BoneDisplayBlock::new);
    public static final RegistryObject<Block> WITHER_TORSO_PILE = BLOCKS.register("wither_torso_pile", BoneDisplayBlock::new);
    public static final RegistryObject<Block> HANGED_SKELETON = BLOCKS.register("hanged_skeleton", BoneDisplayBlock::new);
    public static final RegistryObject<Block> HANGED_WITHER_SKELETON = BLOCKS.register("hanged_wither_skeleton", BoneDisplayBlock::new);


    public static final RegistryObject<Block> VASE_BLOCK = BLOCKS.register("vase_block", () -> new VaseBlock());

    public static final RegistryObject<Block> GRAVESTONE = BLOCKS.register("gravestone", () -> new GravestoneBlock(POLISHED_BASALT_GRAVESTONE_TEXTURE));
    public static final RegistryObject<Block> COBBLESTONE_GRAVESTONE = BLOCKS.register("cobblestone_gravestone", () -> new GravestoneBlock(COBBLESTONE_GRAVESTONE_TEXTURE));
    public static final RegistryObject<Block> MOSSY_COBBLESTONE_GRAVESTONE = BLOCKS.register("mossy_cobblestone_gravestone", () -> new GravestoneBlock(MOSSY_COBBLESTONE_GRAVESTONE_TEXTURE));
    public static final RegistryObject<Block> DEEPSLATE_GRAVESTONE = BLOCKS.register("deepslate_gravestone", () -> new GravestoneBlock(DEEPSLATE_GRAVESTONE_TEXTURE));
    public static final RegistryObject<Block> BLACKSTONE_GRAVESTONE = BLOCKS.register("blackstone_gravestone", () -> new GravestoneBlock(BLACKSTONE_GRAVESTONE_TEXTURE));
    public static final RegistryObject<Block> CRACKED_BLACKSTONE_GRAVESTONE = BLOCKS.register("cracked_blackstone_gravestone", () -> new GravestoneBlock(CRACKED_BLACKSTONE_GRAVESTONE_TEXTURE));
    public static final RegistryObject<Block> STONE_BRICKS_GRAVESTONE = BLOCKS.register("stone_bricks_gravestone", () -> new GravestoneBlock(STONE_BRICKS_GRAVESTONE_TEXTURE));
    public static final RegistryObject<Block> MOSSY_STONE_BRICKS_GRAVESTONE = BLOCKS.register("mossy_stone_bricks_gravestone", () -> new GravestoneBlock(MOSSY_STONE_BRICKS_GRAVESTONE_TEXTURE));
    public static final RegistryObject<Block> BRICKS_GRAVESTONE = BLOCKS.register("bricks_gravestone", () -> new GravestoneBlock(BRICKS_GRAVESTONE_TEXTURE));
    public static final RegistryObject<Block> SANDSTONE_GRAVESTONE = BLOCKS.register("sandstone_gravestone", () -> new GravestoneBlock(SANDSTONE_GRAVESTONE_TEXTURE));
    public static final RegistryObject<Block> RED_SANDSTONE_GRAVESTONE = BLOCKS.register("red_sandstone_gravestone", () -> new GravestoneBlock(RED_SANDSTONE_GRAVESTONE_TEXTURE));
    public static final RegistryObject<Block> GILDED_BLACKSTONE_GRAVESTONE = BLOCKS.register("gilded_blackstone_gravestone", () -> new GravestoneBlock(GILDED_BLACKSTONE_GRAVESTONE_TEXTURE));
    public static final RegistryObject<Block> QUARTZ_BRICKS_GRAVESTONE = BLOCKS.register("quartz_bricks_gravestone", () -> new GravestoneBlock(QUARTZ_BRICKS_GRAVESTONE_TEXTURE));

    public static final RegistryObject<Block> BLACK_URN = BLOCKS.register("black_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> BROWN_URN = BLOCKS.register("brown_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> WHITE_URN = BLOCKS.register("white_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> GRAY_URN = BLOCKS.register("gray_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> LIGHT_GRAY_URN = BLOCKS.register("light_gray_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> BLUE_URN = BLOCKS.register("blue_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> CYAN_URN = BLOCKS.register("cyan_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> LIGHT_BLUE_URN = BLOCKS.register("light_blue_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> RED_URN = BLOCKS.register("red_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> ORANGE_URN = BLOCKS.register("orange_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> YELLOW_URN = BLOCKS.register("yellow_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> PINK_URN = BLOCKS.register("pink_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> MAGENTA_URN = BLOCKS.register("magenta_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> PURPLE_URN = BLOCKS.register("purple_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> GREEN_URN = BLOCKS.register("green_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> LIME_URN = BLOCKS.register("lime_urn", () -> new UrnBlock());

    public static final RegistryObject<Block> SMALL_BLACK_URN = BLOCKS.register("small_black_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_BROWN_URN = BLOCKS.register("small_brown_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_WHITE_URN = BLOCKS.register("small_white_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_GRAY_URN = BLOCKS.register("small_gray_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_LIGHT_GRAY_URN = BLOCKS.register("small_light_gray_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_BLUE_URN = BLOCKS.register("small_blue_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_CYAN_URN = BLOCKS.register("small_cyan_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_LIGHT_BLUE_URN = BLOCKS.register("small_light_blue_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_RED_URN = BLOCKS.register("small_red_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_ORANGE_URN = BLOCKS.register("small_orange_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_YELLOW_URN = BLOCKS.register("small_yellow_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_PINK_URN = BLOCKS.register("small_pink_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_MAGENTA_URN = BLOCKS.register("small_magenta_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_PURPLE_URN = BLOCKS.register("small_purple_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_GREEN_URN = BLOCKS.register("small_green_urn", () -> new UrnBlock());
    public static final RegistryObject<Block> SMALL_LIME_URN = BLOCKS.register("small_lime_urn", () -> new UrnBlock());

    public static final RegistryObject<Block> SARCOPHAGUS = BLOCKS.register("sarcophagus", () -> new SarcophagusBlock(AbstractBlock.Properties.of(Material.STONE).noOcclusion().strength(1.5F), false, "sarcophagus_lid", "sarcophagus_base"));
    public static final RegistryObject<Block> OAK_COFFIN = BLOCKS.register("oak_coffin", () -> new SarcophagusBlock(AbstractBlock.Properties.of(Material.WOOD).noOcclusion().strength(1.0F), true, "oak_coffin_lid", "oak_coffin_base"));
    public static final RegistryObject<Block> SPRUCE_COFFIN = BLOCKS.register("spruce_coffin", () -> new SarcophagusBlock(AbstractBlock.Properties.of(Material.WOOD).noOcclusion().strength(1.0F), true, "spruce_coffin_lid", "spruce_coffin_base"));
    public static final RegistryObject<Block> BIRCH_COFFIN = BLOCKS.register("birch_coffin", () -> new SarcophagusBlock(AbstractBlock.Properties.of(Material.WOOD).noOcclusion().strength(1.0F), true, "birch_coffin_lid", "birch_coffin_base"));
    public static final RegistryObject<Block> DARK_OAK_COFFIN = BLOCKS.register("dark_oak_coffin", () -> new SarcophagusBlock(AbstractBlock.Properties.of(Material.WOOD).noOcclusion().strength(1.0F), true, "dark_oak_coffin_lid", "dark_oak_coffin_base"));
    public static final RegistryObject<Block> JUNGLE_COFFIN = BLOCKS.register("jungle_coffin", () -> new SarcophagusBlock(AbstractBlock.Properties.of(Material.WOOD).noOcclusion().strength(1.0F), true, "jungle_coffin_lid", "jungle_coffin_base"));
    public static final RegistryObject<Block> ACACIA_COFFIN = BLOCKS.register("acacia_coffin", () -> new SarcophagusBlock(AbstractBlock.Properties.of(Material.WOOD).noOcclusion().strength(1.0F), true, "acacia_coffin_lid", "acacia_coffin_base"));
    public static final RegistryObject<Block> WARPED_COFFIN = BLOCKS.register("warped_coffin", () -> new SarcophagusBlock(AbstractBlock.Properties.of(Material.WOOD).noOcclusion().strength(1.0F), true, "warped_coffin_lid", "warped_coffin_base"));
    public static final RegistryObject<Block> CRIMSON_COFFIN = BLOCKS.register("crimson_coffin", () -> new SarcophagusBlock(AbstractBlock.Properties.of(Material.WOOD).noOcclusion().strength(1.0F), true, "crimson_coffin_lid", "crimson_coffin_base"));

    public static final RegistryObject<Block> ALTAR = BLOCKS.register("altar", () -> new AltarBlock(AbstractBlock.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noLootTable()));
    public static final RegistryObject<Block> ALTAR_SIDE = BLOCKS.register("altar_side", () -> new AltarSideBlock(AbstractBlock.Properties.of(Material.STONE).strength(-1.0F, 1200.0F).noLootTable()));
    public static final RegistryObject<Block> ALTAR_CORNER = BLOCKS.register("altar_corner", () -> new AltarCornerBlock(AbstractBlock.Properties.of(Material.STONE).strength(-1.0F, 1200.0F).noLootTable()));
    public static final RegistryObject<Block> ALTAR_CENTER = BLOCKS.register("altar_center", () -> new AltarCenterBlock(AbstractBlock.Properties.of(Material.STONE).strength(-1.0F, 1200.0F).noLootTable()));

    public static final RegistryObject<Block> LOWER_BONE_STAFF = BLOCKS.register("lower_bone_staff", OminousBoneStaffFragment::new);
    public static final RegistryObject<Block> MIDDLE_BONE_STAFF = BLOCKS.register("middle_bone_staff", OminousBoneStaffFragment::new);
    public static final RegistryObject<Block> UPPER_BONE_STAFF = BLOCKS.register("upper_bone_staff", OminousBoneStaffFragment::new);

    public static final RegistryObject<Block> OSSUARY = BLOCKS.register("ossuary", () -> new OssuaryBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().noOcclusion().strength(3.5F)));


    public static List<Block> getCoffins() {
        coffins.add(OAK_COFFIN.get());
        coffins.add(SPRUCE_COFFIN.get());
        coffins.add(DARK_OAK_COFFIN.get());
        coffins.add(BIRCH_COFFIN.get());
        coffins.add(JUNGLE_COFFIN.get());
        coffins.add(ACACIA_COFFIN.get());
        coffins.add(CRIMSON_COFFIN.get());
        coffins.add(WARPED_COFFIN.get());

        return coffins;
    }


}
