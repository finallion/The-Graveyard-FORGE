package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.world.structures.TGJigsawStructure;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class TGConfiguredStructureFeatures {
    public static List<Structure> structures = new ArrayList<>();

    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, TheGraveyard.MOD_ID);

    public static final RegistryObject<StructureType<TGJigsawStructure>> ALTAR = STRUCTURES.register("altar", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> DEAD_TREE = STRUCTURES.register("dead_tree", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> CRYPT = STRUCTURES.register("crypt", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> GIANT_MUSHROOM = STRUCTURES.register("giant_mushroom", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> HAUNTED_HOUSE = STRUCTURES.register("haunted_house", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> LARGE_GRAVEYARD = STRUCTURES.register("large_graveyard", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> LICH_PRISON = STRUCTURES.register("lich_prison", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> MEDIUM_GRAVEYARD = STRUCTURES.register("medium_graveyard", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> MEMORIAL_TREE = STRUCTURES.register("memorial_tree", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> MUSHROOM_GRAVE = STRUCTURES.register("mushroom_grave", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> RUINS = STRUCTURES.register("ruins", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> SMALL_DESERT_GRAVE = STRUCTURES.register("small_desert_grave", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> SMALL_DESERT_GRAVEYARD = STRUCTURES.register("small_desert_graveyard", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> SMALL_GRAVE = STRUCTURES.register("small_grave", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> SMALL_GRAVEYARD = STRUCTURES.register("small_graveyard", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> SMALL_MOUNTAIN_GRAVE = STRUCTURES.register("small_mountain_grave", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> SMALL_SAVANNA_GRAVE = STRUCTURES.register("small_savanna_grave", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));

    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(Codec<T> structureCodec) {
        return () -> structureCodec;
    }
}
