package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.world.structures.*;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TGStructureFeatures {
    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, TheGraveyard.MOD_ID);


    public static final StructureFeature<JigsawConfiguration> MEDIUM_GRAVEYARD_STRUCTURE = register("medium_graveyard", new MediumGraveyardStructure(JigsawConfiguration.CODEC));
    public static final StructureFeature<JigsawConfiguration> SMALL_GRAVEYARD_STRUCTURE = register("small_graveyard", new SmallGraveyardStructure(JigsawConfiguration.CODEC));
    public static final StructureFeature<JigsawConfiguration> LARGE_GRAVEYARD_STRUCTURE = register("large_graveyard", new LargeGraveyardStructure(JigsawConfiguration.CODEC));
    public static final StructureFeature<JigsawConfiguration> MUSHROOM_GRAVE_STRUCTURE = register("mushroom_grave", new MushroomGraveStructure(JigsawConfiguration.CODEC));
    public static final StructureFeature<JigsawConfiguration> HAUNTED_HOUSE_STRUCTURE = register("haunted_house", new HauntedHouseStructure(JigsawConfiguration.CODEC));
    public static final StructureFeature<JigsawConfiguration> MEMORIAL_TREE_STRUCTURE = register("memorial_tree", new MemorialTreeStructure(JigsawConfiguration.CODEC));
    public static final StructureFeature<JigsawConfiguration> SMALL_DESERT_GRAVEYARD_STRUCTURE = register("small_desert_graveyard", new SmallDesertGraveyardStructure(JigsawConfiguration.CODEC));
    public static final StructureFeature<JigsawConfiguration> SMALL_GRAVE_STRUCTURE = register("small_grave", new SmallGraveStructure(JigsawConfiguration.CODEC));
    public static final StructureFeature<JigsawConfiguration> SMALL_DESERT_GRAVE_STRUCTURE = register("small_desert_grave", new SmallDesertGraveStructure(JigsawConfiguration.CODEC));
    public static final StructureFeature<JigsawConfiguration> SMALL_SAVANNA_GRAVE_STRUCTURE = register("small_savanna_grave", new SmallSavannaGraveStructure(JigsawConfiguration.CODEC));
    public static final StructureFeature<JigsawConfiguration> SMALL_MOUNTAIN_GRAVE_STRUCTURE = register("small_mountain_grave", new SmallMountainGraveStructure(JigsawConfiguration.CODEC));


    private static StructureFeature<JigsawConfiguration> register(String name, StructureFeature<JigsawConfiguration> feature) {
        STRUCTURES.register(name, () -> feature);
        return feature;
    }


}
