package com.finallion.graveyard.structures;


import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class SmallDesertGrave extends TGBaseStructure {

    public SmallDesertGrave(Codec<NoFeatureConfig> codec) {
        super(codec, "small_desert_grave", 1, 0);
    }
}