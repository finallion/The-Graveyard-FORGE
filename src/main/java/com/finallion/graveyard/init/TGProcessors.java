package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.world.processors.RemoveWaterloggedProcessor;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public class TGProcessors {

    // Processor uses:
    // Haunted House
    // Small Crypt

    public static StructureProcessorType<RemoveWaterloggedProcessor> REMOVE_WATERLOGGED = () -> RemoveWaterloggedProcessor.CODEC;

    public static final Holder<StructureProcessorList> WATERLOGGED_LIST = BuiltinRegistries.m_206388_(BuiltinRegistries.PROCESSOR_LIST,
            new ResourceLocation(TheGraveyard.MOD_ID, "waterlogged_processor_list"),
            new StructureProcessorList(ImmutableList.of(new RemoveWaterloggedProcessor())));

    public static void registerProcessors() {
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(TheGraveyard.MOD_ID, "remove_waterlogged_processor"), REMOVE_WATERLOGGED);

    }
}
