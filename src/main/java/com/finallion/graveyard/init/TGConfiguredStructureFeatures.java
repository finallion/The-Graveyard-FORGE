package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.world.structures.TGJigsawConfig;
import com.finallion.graveyard.world.structures.TGJigsawStructure;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class TGConfiguredStructureFeatures {
    public static List<ConfiguredStructureFeature<?, ?>> structures = new ArrayList<>();

    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, TheGraveyard.MOD_ID);

    public static final RegistryObject<StructureFeature<TGJigsawConfig>> ALTAR = STRUCTURES.register("altar", () -> new TGJigsawStructure(TGJigsawConfig.CODEC, "altar"));
    public static final RegistryObject<StructureFeature<TGJigsawConfig>> DEAD_TREE = STRUCTURES.register("dead_tree", () -> new TGJigsawStructure(TGJigsawConfig.CODEC, "dead_tree"));
    public static final RegistryObject<StructureFeature<TGJigsawConfig>> CRYPT = STRUCTURES.register("crypt", () -> new TGJigsawStructure(TGJigsawConfig.CODEC, "crypt"));
    public static final RegistryObject<StructureFeature<TGJigsawConfig>> GIANT_MUSHROOM = STRUCTURES.register("giant_mushroom", () -> new TGJigsawStructure(TGJigsawConfig.CODEC, "giant_mushroom"));
    public static final RegistryObject<StructureFeature<TGJigsawConfig>> HAUNTED_HOUSE = STRUCTURES.register("haunted_house", () -> new TGJigsawStructure(TGJigsawConfig.CODEC, "haunted_house"));
    public static final RegistryObject<StructureFeature<TGJigsawConfig>> LARGE_GRAVEYARD = STRUCTURES.register("large_graveyard", () -> new TGJigsawStructure(TGJigsawConfig.CODEC, "large_graveyard"));
    public static final RegistryObject<StructureFeature<TGJigsawConfig>> LICH_PRISON = STRUCTURES.register("lich_prison", () -> new TGJigsawStructure(TGJigsawConfig.CODEC, "lich_prison"));
    public static final RegistryObject<StructureFeature<TGJigsawConfig>> MEDIUM_GRAVEYARD = STRUCTURES.register("medium_graveyard", () -> new TGJigsawStructure(TGJigsawConfig.CODEC, "medium_graveyard"));
    public static final RegistryObject<StructureFeature<TGJigsawConfig>> MEMORIAL_TREE = STRUCTURES.register("memorial_tree", () -> new TGJigsawStructure(TGJigsawConfig.CODEC, "memorial_tree"));
    public static final RegistryObject<StructureFeature<TGJigsawConfig>> MUSHROOM_GRAVE = STRUCTURES.register("mushroom_grave", () -> new TGJigsawStructure(TGJigsawConfig.CODEC, "mushroom_grave"));
    public static final RegistryObject<StructureFeature<TGJigsawConfig>> RUINS = STRUCTURES.register("ruins", () -> new TGJigsawStructure(TGJigsawConfig.CODEC, "ruins"));
    public static final RegistryObject<StructureFeature<TGJigsawConfig>> SMALL_DESERT_GRAVE = STRUCTURES.register("small_desert_grave", () -> new TGJigsawStructure(TGJigsawConfig.CODEC, "small_desert_grave"));
    public static final RegistryObject<StructureFeature<TGJigsawConfig>> SMALL_DESERT_GRAVEYARD = STRUCTURES.register("small_desert_graveyard", () -> new TGJigsawStructure(TGJigsawConfig.CODEC, "small_desert_graveyard"));
    public static final RegistryObject<StructureFeature<TGJigsawConfig>> SMALL_GRAVE = STRUCTURES.register("small_grave", () -> new TGJigsawStructure(TGJigsawConfig.CODEC, "small_grave"));
    public static final RegistryObject<StructureFeature<TGJigsawConfig>> SMALL_GRAVEYARD = STRUCTURES.register("small_graveyard", () -> new TGJigsawStructure(TGJigsawConfig.CODEC, "small_graveyard"));
    public static final RegistryObject<StructureFeature<TGJigsawConfig>> SMALL_MOUNTAIN_GRAVE = STRUCTURES.register("small_mountain_grave", () -> new TGJigsawStructure(TGJigsawConfig.CODEC, "small_mountain_grave"));
    public static final RegistryObject<StructureFeature<TGJigsawConfig>> SMALL_SAVANNA_GRAVE = STRUCTURES.register("small_savanna_grave", () -> new TGJigsawStructure(TGJigsawConfig.CODEC, "small_savanna_grave"));


}
