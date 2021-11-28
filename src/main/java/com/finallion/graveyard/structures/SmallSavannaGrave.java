package com.finallion.graveyard.structures;


import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class SmallSavannaGrave extends TGBaseStructure {

    public SmallSavannaGrave(Codec<NoFeatureConfig> codec) {
        super(codec, "small_savanna_grave", 1, 0);
    }
}