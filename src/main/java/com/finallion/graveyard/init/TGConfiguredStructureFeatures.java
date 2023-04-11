package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.world.structures.TGJigsawStructure;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TGConfiguredStructureFeatures {

    public static final DeferredRegister<StructureType<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, TheGraveyard.MOD_ID);

    public static final RegistryObject<StructureType<TGJigsawStructure>> ALTAR = DEFERRED_REGISTRY_STRUCTURE.register("altar", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> DEAD_TREE = DEFERRED_REGISTRY_STRUCTURE.register("", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> CRYPT = DEFERRED_REGISTRY_STRUCTURE.register("crypt", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> GIANT_MUSHROOM = DEFERRED_REGISTRY_STRUCTURE.register("giant_mushroom", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> HAUNTED_HOUSE = DEFERRED_REGISTRY_STRUCTURE.register("haunted_house", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> LARGE_GRAVEYARD = DEFERRED_REGISTRY_STRUCTURE.register("large_graveyard", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> LICH_PRISON = DEFERRED_REGISTRY_STRUCTURE.register("lich_prison", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> MEDIUM_GRAVEYARD = DEFERRED_REGISTRY_STRUCTURE.register("medium_graveyard", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> MEMORIAL_TREE = DEFERRED_REGISTRY_STRUCTURE.register("memorial_tree", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> MUSHROOM_GRAVE = DEFERRED_REGISTRY_STRUCTURE.register("mushroom_grave", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> RUINS = DEFERRED_REGISTRY_STRUCTURE.register("ruins", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> SMALL_DESERT_GRAVE = DEFERRED_REGISTRY_STRUCTURE.register("small_desert_grave", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> SMALL_DESERT_GRAVEYARD = DEFERRED_REGISTRY_STRUCTURE.register("small_desert_graveyard", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> SMALL_GRAVE = DEFERRED_REGISTRY_STRUCTURE.register("small_grave", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> SMALL_GRAVEYARD = DEFERRED_REGISTRY_STRUCTURE.register("small_graveyard", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> SMALL_MOUNTAIN_GRAVE = DEFERRED_REGISTRY_STRUCTURE.register("small_mountain_grave", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<TGJigsawStructure>> SMALL_SAVANNA_GRAVE = DEFERRED_REGISTRY_STRUCTURE.register("small_savanna_grave", () -> explicitStructureTypeTyping(TGJigsawStructure.CODEC));

    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(Codec<T> structureCodec) {
        return () -> structureCodec;
    }
}
