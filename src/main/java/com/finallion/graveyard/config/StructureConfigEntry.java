package com.finallion.graveyard.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class StructureConfigEntry {

    private final boolean default_canGenerate;
    public ForgeConfigSpec.BooleanValue canGenerate;

    private StructureConfigEntry(boolean canGenerate) {
        this.default_canGenerate = canGenerate;
    }

    public static StructureConfigEntry of() {
        return new StructureConfigEntry(true);
    }


}
