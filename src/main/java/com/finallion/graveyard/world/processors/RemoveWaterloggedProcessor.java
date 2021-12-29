package com.finallion.graveyard.world.processors;

import com.finallion.graveyard.init.TGProcessors;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.Nullable;

public class RemoveWaterloggedProcessor extends StructureProcessor {
    public static final RemoveWaterloggedProcessor INSTANCE = new RemoveWaterloggedProcessor();
    public static final Codec<RemoveWaterloggedProcessor> CODEC = Codec.unit(() -> INSTANCE);

    // removes waterlogged blockstate
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader worldReader, BlockPos pos, BlockPos pos2, StructureTemplate.StructureBlockInfo infoIn1, StructureTemplate.StructureBlockInfo infoIn2, StructurePlaceSettings settings) {
        ChunkPos currentChunkPos = new ChunkPos(infoIn2.pos);
        if (infoIn2.state.hasProperty(BlockStateProperties.WATERLOGGED) && !infoIn2.state.getValue(BlockStateProperties.WATERLOGGED)) {
            ChunkAccess currentChunk = worldReader.getChunk(currentChunkPos.x, currentChunkPos.z);
            if (worldReader.getFluidState(infoIn2.pos).is(FluidTags.WATER)) {
                currentChunk.setBlockState(infoIn2.pos, infoIn2.state, false);
            }
        }

        return infoIn2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return TGProcessors.REMOVE_WATERLOGGED;
    }
}
