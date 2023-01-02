package com.finallion.graveyard.blocks;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.DoorBlock;

public class TGDoorBlock extends DoorBlock {

    // access to private door constructor
    public TGDoorBlock(Properties settings) {
        super(settings, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN);
    }
}
