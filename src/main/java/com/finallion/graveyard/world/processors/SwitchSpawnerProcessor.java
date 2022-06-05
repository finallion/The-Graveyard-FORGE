package com.finallion.graveyard.world.processors;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.TGProcessors;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.Nullable;

public class SwitchSpawnerProcessor extends StructureProcessor {
    public static final Codec<SwitchSpawnerProcessor> CODEC = Codec.unit(SwitchSpawnerProcessor::new);


    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader worldReader, BlockPos pos, BlockPos pos2, StructureTemplate.StructureBlockInfo infoIn1, StructureTemplate.StructureBlockInfo structureBlockInfo2, StructurePlaceSettings settings) {
        if (structureBlockInfo2.state.getBlock() instanceof SpawnerBlock && GraveyardConfig.COMMON.disableWitherSkeletonSpawner.get()) {
            BlockPos worldPos = structureBlockInfo2.pos;
            BlockEntity blockEntity = worldReader.getBlockEntity(worldPos);
            if (blockEntity instanceof SpawnerBlockEntity) {
                CompoundTag nbtCompound = structureBlockInfo2.nbt.getCompound("SpawnData");
                if (nbtCompound.toString().contains("wither_skeleton")) {
                    ((SpawnerBlockEntity)blockEntity).getSpawner().setEntityId(EntityType.SKELETON);
                    //TheGraveyard.LOGGER.error("The Graveyard Config: Wither Skeleton Spawner switched to Skeleton Spawner at " + worldPos);
                }
            }
        }
        return structureBlockInfo2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return TGProcessors.SWITCH_SPAWNER;
    }
}

