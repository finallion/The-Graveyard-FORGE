package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.world.processors.RemoveWaterloggedProcessor;
import com.finallion.graveyard.world.processors.SwitchSpawnerProcessor;
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
    // Wither Mill

    public static StructureProcessorType<RemoveWaterloggedProcessor> REMOVE_WATERLOGGED = () -> RemoveWaterloggedProcessor.CODEC;
    public static StructureProcessorType<SwitchSpawnerProcessor> SWITCH_SPAWNER = () -> SwitchSpawnerProcessor.CODEC;
    public static StructureProcessorType<RemoveWaterloggedCryptProcessor> REMOVE_WATERLOGGED_CRYPT = () -> RemoveWaterloggedCryptProcessor.CODEC;


    public static void registerProcessors() {
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(TheGraveyard.MOD_ID, "remove_waterlogged_processor"), REMOVE_WATERLOGGED);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(TheGraveyard.MOD_ID, "switch_spawner_processor"), SWITCH_SPAWNER);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(TheGraveyard.MOD_ID, "waterlogged_crypt_processor"), REMOVE_WATERLOGGED_CRYPT);

    }
}
