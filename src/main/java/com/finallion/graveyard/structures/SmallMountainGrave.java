package com.finallion.graveyard.structures;


import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class SmallMountainGrave extends TGBaseStructure {

    public SmallMountainGrave(Codec<NoFeatureConfig> codec) {
        super(codec, "small_mountain_grave", 0.5, 0);
    }
}