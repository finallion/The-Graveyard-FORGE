package com.finallion.graveyard.init;


import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blocks.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;


public class TGBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TheGraveyard.MOD_ID);
    public static List<Block> coffins = new ArrayList<Block>();

    public static final ResourceLocation GRAVESTONE_TEXTURE = new ResourceLocation("minecraft", "block/polished_basalt_side");
    public static final ResourceLocation COBBLESTONE_GRAVESTONE_TEXTURE = new ResourceLocation("minecraft", "block/cobblestone");
    public static final ResourceLocation MOSSY_COBBLESTONE_GRAVESTONE_TEXTURE = new ResourceLocation("minecraft", "block/mossy_cobblestone");
    public static final ResourceLocation DEEPSLATE_GRAVESTONE_TEXTURE = new ResourceLocation(TheGraveyard.MOD_ID, "block/deepslate");

    public static final RegistryObject<Block> TG_ROOTED_DIRT = BLOCKS.register("tg_rooted_dirt", () -> new Block(BlockBehaviour.Properties.copy(Blocks.ROOTED_DIRT).lootFrom(() -> Blocks.ROOTED_DIRT)));
    public static final RegistryObject<Block> TG_TUFF = BLOCKS.register("tg_tuff", () -> new Block(BlockBehaviour.Properties.copy(Blocks.TUFF).lootFrom(() -> Blocks.TUFF)));
    public static final RegistryObject<Block> TG_MOSS_BLOCK = BLOCKS.register("tg_moss_block", () -> new TGMossBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK).lootFrom(() -> Blocks.MOSS_BLOCK)));
    public static final RegistryObject<Block> TG_DEEPSLATE = BLOCKS.register("tg_deepslate", () -> new TGDeepslateBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).lootFrom(() -> Blocks.DEEPSLATE)));

    // helper blocks that get don't get replaced on world generation, generate ores or generate trees on
    public static final RegistryObject<Block> TG_GRASS_BLOCK = BLOCKS.register("tg_grass_block", () -> new TGGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).lootFrom(() -> Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> TG_DIRT = BLOCKS.register("tg_dirt", () -> new TGStoneBlock(() -> Blocks.DIRT, BlockBehaviour.Properties.copy(Blocks.DIRT).lootFrom(() -> Blocks.DIRT)));
    public static final RegistryObject<Block> TG_COARSE_DIRT = BLOCKS.register("tg_coarse_dirt", () -> new TGStoneBlock(() -> Blocks.COARSE_DIRT, BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT).lootFrom(() -> Blocks.COARSE_DIRT)));
    public static final RegistryObject<Block> TG_ANDESITE = BLOCKS.register("tg_andesite", () -> new TGStoneBlock(() -> Blocks.ANDESITE, BlockBehaviour.Properties.copy(Blocks.ANDESITE).lootFrom(() -> Blocks.ANDESITE)));
    public static final RegistryObject<Block> TG_GRANITE = BLOCKS.register("tg_granite", () -> new TGStoneBlock(() -> Blocks.GRANITE, BlockBehaviour.Properties.copy(Blocks.GRANITE).lootFrom(() -> Blocks.GRANITE)));
    public static final RegistryObject<Block> TG_DIORITE = BLOCKS.register("tg_diorite", () -> new TGStoneBlock(() -> Blocks.DIORITE, BlockBehaviour.Properties.copy(Blocks.DIORITE).lootFrom(() -> Blocks.DIORITE)));
    public static final RegistryObject<Block> TG_STONE = BLOCKS.register("tg_stone", () -> new TGStoneBlock(() -> Blocks.STONE, BlockBehaviour.Properties.copy(Blocks.STONE).lootFrom(() -> Blocks.STONE)));
    public static final RegistryObject<Block> TG_PODZOL = BLOCKS.register("tg_podzol", () -> new TGStoneBlock(() -> Blocks.PODZOL, BlockBehaviour.Properties.copy(Blocks.PODZOL).lootFrom(() -> Blocks.PODZOL)));


    public static final RegistryObject<Block> DARK_IRON_BARS = BLOCKS.register("dark_iron_bars", () -> new DarkIronBars(BlockBehaviour.Properties.of(Material.METAL).strength(1.0F).noOcclusion()));
    public static final RegistryObject<Block> SOUL_FIRE_BRAZIER = BLOCKS.register("soul_fire_brazier", () -> new BrazierBlock(BlockBehaviour.Properties.of(Material.METAL).strength(1.0F).noOcclusion().lightLevel(BrazierBlock.STATE_TO_LUMINANCE).sound(SoundType.METAL), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Block> FIRE_BRAZIER = BLOCKS.register("fire_brazier", () -> new BrazierBlock(BlockBehaviour.Properties.of(Material.METAL).strength(1.0F).noOcclusion().lightLevel(BrazierBlock.STATE_TO_LUMINANCE).sound(SoundType.METAL), ParticleTypes.FLAME));
    public static final RegistryObject<Block> PEDESTAL = BLOCKS.register("pedestal", () -> new PedestalBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.0F).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> CANDLE_HOLDER = BLOCKS.register("candle_holder", () -> new CandleHolderBlock(BlockBehaviour.Properties.of(Material.METAL).strength(1.0F).noOcclusion().sound(SoundType.METAL)));
    public static final RegistryObject<Block> DARK_IRON_TRAPDOOR = BLOCKS.register("dark_iron_trapdoor", () -> new TGTrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion()));
    public static final RegistryObject<Block> DARK_IRON_DOOR = BLOCKS.register("dark_iron_door", () -> new TGDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.RAW_IRON).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion()));
    public static final RegistryObject<Block> DARK_IRON_BLOCK = BLOCKS.register("dark_iron_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.RAW_IRON).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> SKULL_WITH_RIB_CAGE = BLOCKS.register("skull_with_rib_cage", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> LEANING_SKELETON = BLOCKS.register("leaning_skeleton", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> SKULL_PILE = BLOCKS.register("skull_pile", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> LYING_SKELETON = BLOCKS.register("lying_skeleton", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> WITHER_SKULL_WITH_RIB_CAGE = BLOCKS.register("wither_skull_with_rib_cage", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> LEANING_WITHER_SKELETON = BLOCKS.register("leaning_wither_skeleton", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> WITHER_SKULL_PILE = BLOCKS.register("wither_skull_pile", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> LYING_WITHER_SKELETON = BLOCKS.register("lying_wither_skeleton", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> CREEPER_SKELETON = BLOCKS.register("creeper_skeleton", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> SKELETON_HAND = BLOCKS.register("skeleton_hand", () -> new BoneDisplayBlock());
    public static final RegistryObject<Block> WITHER_SKELETON_HAND = BLOCKS.register("wither_skeleton_hand", () -> new BoneDisplayBlock());


    public static final RegistryObject<Block> VASE_BLOCK = BLOCKS.register("vase_block", () -> new VaseBlock());

    public static final RegistryObject<Block> GRAVESTONE = BLOCKS.register("gravestone", () -> new GravestoneBlock(GRAVESTONE_TEXTURE));
    public static final RegistryObject<Block> COBBLESTONE_GRAVESTONE = BLOCKS.register("cobblestone_gravestone", () -> new GravestoneBlock(COBBLESTONE_GRAVESTONE_TEXTURE));
    public static final RegistryObject<Block> MOSSY_COBBLESTONE_GRAVESTONE = BLOCKS.register("mossy_cobblestone_gravestone", () -> new GravestoneBlock(MOSSY_COBBLESTONE_GRAVESTONE_TEXTURE));
    public static final RegistryObject<Block> DEEPSLATE_GRAVESTONE = BLOCKS.register("deepslate_gravestone", () -> new GravestoneBlock(DEEPSLATE_GRAVESTONE_TEXTURE));

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

    public static final RegistryObject<Block> SARCOPHAGUS = BLOCKS.register("sarcophagus", () -> new SarcophagusBlock(BlockBehaviour.Properties.of(Material.STONE).noOcclusion().strength(1.5F), false));
    public static final RegistryObject<Block> OAK_COFFIN = BLOCKS.register("oak_coffin", () -> new SarcophagusBlock(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion().strength(1.0F), true));
    public static final RegistryObject<Block> SPRUCE_COFFIN = BLOCKS.register("spruce_coffin", () -> new SarcophagusBlock(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion().strength(1.0F), true));
    public static final RegistryObject<Block> BIRCH_COFFIN = BLOCKS.register("birch_coffin", () -> new SarcophagusBlock(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion().strength(1.0F), true));
    public static final RegistryObject<Block> DARK_OAK_COFFIN = BLOCKS.register("dark_oak_coffin", () -> new SarcophagusBlock(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion().strength(1.0F), true));
    public static final RegistryObject<Block> JUNGLE_COFFIN = BLOCKS.register("jungle_coffin", () -> new SarcophagusBlock(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion().strength(1.0F), true));
    public static final RegistryObject<Block> ACACIA_COFFIN = BLOCKS.register("acacia_coffin", () -> new SarcophagusBlock(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion().strength(1.0F), true));
    public static final RegistryObject<Block> WARPED_COFFIN = BLOCKS.register("warped_coffin", () -> new SarcophagusBlock(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion().strength(1.0F), true));
    public static final RegistryObject<Block> CRIMSON_COFFIN = BLOCKS.register("crimson_coffin", () -> new SarcophagusBlock(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion().strength(1.0F), true));

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
