package com.finallion.graveyard.structures;


import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class SmallGrave extends TGBaseStructure {

    public SmallGrave(Codec<NoFeatureConfig> codec) {
        super(codec, "small_grave", 1, 0);
    }
}