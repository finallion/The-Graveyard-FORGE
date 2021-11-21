package com.finallion.graveyard.structures;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class HauntedHouse extends TGBaseStructure {
    public HauntedHouse(Codec<NoFeatureConfig> codec) {
        super(codec, "haunted_house", 1.5, 0);
    }
}
