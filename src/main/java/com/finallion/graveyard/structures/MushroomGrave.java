package com.finallion.graveyard.structures;


import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class MushroomGrave extends TGBaseStructure {
    public MushroomGrave(Codec<NoFeatureConfig> codec) {
        super(codec, "mushroom_grave", 0.5, 0);
    }
}
