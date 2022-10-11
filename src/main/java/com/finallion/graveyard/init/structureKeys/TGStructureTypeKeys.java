package com.finallion.graveyard.init.structureKeys;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;

public class TGStructureTypeKeys {
    public static ResourceKey<Structure> HAUNTED_HOUSE = of("haunted_house");
    public static ResourceKey<Structure> LARGE_GRAVEYARD = of("large_graveyard");
    public static ResourceKey<Structure> MEDIUM_GRAVEYARD = of("medium_graveyard");
    public static ResourceKey<Structure> SMALL_GRAVEYARD = of("small_graveyard");
    public static ResourceKey<Structure> SMALL_DESERT_GRAVEYARD = of("small_desert_graveyard");
    public static ResourceKey<Structure> SMALL_GRAVE = of("small_grave");
    public static ResourceKey<Structure> SMALL_DESERT_GRAVE = of("small_desert_grave");
    public static ResourceKey<Structure> SMALL_SAVANNA_GRAVE = of("small_savanna_grave");
    public static ResourceKey<Structure> SMALL_MOUNTAIN_GRAVE = of("small_mountain_grave");
    public static ResourceKey<Structure> MUSHROOM_GRAVE = of("mushroom_grave");
    public static ResourceKey<Structure> MEMORIAL_TREE = of("memorial_tree");
    public static ResourceKey<Structure> ALTAR = of("altar");
    public static ResourceKey<Structure> GIANT_MUSHROOM = of("giant_mushroom");
    public static ResourceKey<Structure> CRYPT = of("crypt");
    public static ResourceKey<Structure> LICH_PRISON = of("lich_prison");
    public static ResourceKey<Structure> RUINS = of("ruins");

    private static ResourceKey<Structure> of(String id) {
        return ResourceKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(TheGraveyard.MOD_ID, id));
    }

}
