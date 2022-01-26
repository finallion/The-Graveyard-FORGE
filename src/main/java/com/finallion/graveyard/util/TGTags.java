package com.finallion.graveyard.util;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;


public class TGTags {

    public static final Tag.Named<Block> DEAD_CORAL_BLOCKS = BlockTags.bind("graveyard:dead_coral_blocks");
    public static final Tag.Named<Block> DEAD_CORAL_PLANTS = BlockTags.bind("graveyard:dead_coral_plants");
    public static final Tag.Named<Block> DEAD_CORALS = BlockTags.bind("graveyard:dead_corals");
    public static final Tag.Named<Block> DEAD_WALL_CORALS = BlockTags.bind("graveyard:dead_wall_corals");
}
