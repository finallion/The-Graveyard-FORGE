package com.finallion.graveyard.structures.processors;

import com.finallion.graveyard.TheGraveyard;
import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

import javax.annotation.Nullable;

public class SimpleSurfaceProcessors extends StructureProcessor {
    public static final SimpleSurfaceProcessors INSTANCE = new SimpleSurfaceProcessors();
    public static final Codec<SimpleSurfaceProcessors> CODEC = Codec.unit(() -> INSTANCE);


    // allows to swap out blocks on generation (before tree and ore generation sadly)
    // this is just a test case
    @Nullable
    @Override
    public Template.BlockInfo process(IWorldReader worldReader, BlockPos jigsawPiecePos, BlockPos jigsawPieceBottomCenterPos, Template.BlockInfo blockInfoLocal, Template.BlockInfo blockInfoGlobal, PlacementSettings structurePlacementData, @Nullable Template template) {
        if (blockInfoGlobal.state.getBlock().is(Blocks.NETHERRACK)) {
            worldReader.getChunk(blockInfoGlobal.pos).setBlockState(blockInfoGlobal.pos, Blocks.AIR.defaultBlockState(), false);
        }
        return blockInfoGlobal;
    }


    @Override
    protected IStructureProcessorType<?> getType() {
        return TheGraveyard.SIMPLE_SURFACE_PROCESSOR;
    }


}
