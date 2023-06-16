package com.finallion.graveyard.blockentities.enums;


import net.minecraft.util.IStringSerializable;

public enum SarcophagusPart implements IStringSerializable {
    HEAD("head"),
    FOOT("foot");

    private final String name;

    private SarcophagusPart(String p_61339_) {
        this.name = p_61339_;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}
