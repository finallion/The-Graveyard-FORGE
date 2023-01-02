package com.finallion.graveyard.blocks;


import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.TrapDoorBlock;

public class TGTrapDoorBlock extends TrapDoorBlock {

    // access to private trap door constructor
    public TGTrapDoorBlock(Properties settings) {
        super(settings, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN);
    }
}
