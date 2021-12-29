package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.world.processors.RemoveWaterloggedProcessor;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public class TGProcessors {

    // Processor uses:
    // Haunted House

    public static StructureProcessorType<RemoveWaterloggedProcessor> REMOVE_WATERLOGGED = () -> RemoveWaterloggedProcessor.CODEC;

    //public static final StructureProcessorType<RemoveWaterloggedProcessor> REMOVE_WATERLOGGED = StructureProcessorType.register("remove_waterlogged_processor_type", RemoveWaterloggedProcessor.CODEC);
    public static final StructureProcessorList WATERLOGGED_LIST = TGProcessors.register("waterlogged_processor_list", ImmutableList.of(new RemoveWaterloggedProcessor()));

    public static StructureProcessorList register(String id, ImmutableList<StructureProcessor> processorList) {
        ResourceLocation identifier = new ResourceLocation(TheGraveyard.MOD_ID, id);
        StructureProcessorList structureProcessorList = new StructureProcessorList(processorList);
        return BuiltinRegistries.register(BuiltinRegistries.PROCESSOR_LIST, identifier, structureProcessorList);
    }

    public static void registerProcessors() {
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(TheGraveyard.MOD_ID, "remove_waterlogged_processor"), REMOVE_WATERLOGGED);

    }
}
