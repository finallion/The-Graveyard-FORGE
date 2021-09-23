package com.finallion.graveyard.utils;

import com.finallion.graveyard.TheGraveyard;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.StructureProcessorList;

public class ProcessorRegistry {

    public static StructureProcessorList registerStructureProcessor(String id, ImmutableList<StructureProcessor> processorList) {
        ResourceLocation identifier = new ResourceLocation(TheGraveyard.MOD_ID, id);
        StructureProcessorList structureProcessorList = new StructureProcessorList(processorList);
        return WorldGenRegistries.register(WorldGenRegistries.PROCESSOR_LIST, identifier, structureProcessorList);
    }

}
