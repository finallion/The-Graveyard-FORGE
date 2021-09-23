package com.finallion.graveyard.structures;



import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class SmallWalledGraveyardSavanna extends TGBaseStructure {


    public SmallWalledGraveyardSavanna(Codec<NoFeatureConfig> codec) {
        super(codec, "small_walled_graveyard_savanna", 1.5, 0);
    }
}

