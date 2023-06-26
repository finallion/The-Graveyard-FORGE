package com.finallion.graveyard.init;

import com.finallion.graveyard.world.structures.TGJigsawStructure;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class TGStructureType<S extends Structure> {
    public static void init() {
    }

    public static final StructureType<TGJigsawStructure> TG_JIGSAW = register("graveyard:tg_jigsaw", TGJigsawStructure.CODEC);

    private static <S extends Structure> StructureType<S> register(String p_226882_, Codec<S> p_226883_) {
        return Registry.register(BuiltInRegistries.STRUCTURE_TYPE, p_226882_, () -> {
            return p_226883_;
        });
    }
}
