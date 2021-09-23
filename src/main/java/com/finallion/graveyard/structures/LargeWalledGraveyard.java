package com.finallion.graveyard.structures;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class LargeWalledGraveyard extends TGBaseStructure {

    public LargeWalledGraveyard(Codec<NoFeatureConfig> codec) {
        super(codec, "large_walled_graveyard", 5, 0);
    }


}