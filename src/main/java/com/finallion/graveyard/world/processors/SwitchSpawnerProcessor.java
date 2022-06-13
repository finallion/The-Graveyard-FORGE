package com.finallion.graveyard.world.processors;

import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.TGProcessors;
import com.mojang.serialization.Codec;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

import javax.annotation.Nullable;


public class SwitchSpawnerProcessor extends StructureProcessor {
    public static final Codec<SwitchSpawnerProcessor> CODEC = Codec.unit(SwitchSpawnerProcessor::new);


    @Nullable
    @Override
    public Template.BlockInfo processBlock(IWorldReader worldReader, BlockPos pos, BlockPos pos2, Template.BlockInfo infoIn1, Template.BlockInfo structureBlockInfo2, PlacementSettings settings) {
        if (structureBlockInfo2.state.getBlock() instanceof SpawnerBlock && GraveyardConfig.COMMON.disableWitherSkeletonSpawner.get()) {
            BlockPos worldPos = structureBlockInfo2.pos;
            TileEntity blockEntity = worldReader.getBlockEntity(worldPos);
            if (blockEntity instanceof MobSpawnerTileEntity) {
                CompoundNBT nbtCompound = structureBlockInfo2.nbt.getCompound("SpawnData");
                if (nbtCompound.toString().contains("wither_skeleton")) {
                    ((MobSpawnerTileEntity)blockEntity).getSpawner().setEntityId(EntityType.SKELETON);
                    //TheGraveyard.LOGGER.error("The Graveyard Config: Wither Skeleton Spawner switched to Skeleton Spawner at " + worldPos);
                }
            }
        }
        return structureBlockInfo2;
    }

    @Override
    protected IStructureProcessorType<?> getType() {
        return TGProcessors.SWITCH_SPAWNER;
    }
}

