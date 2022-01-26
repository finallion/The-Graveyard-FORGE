package com.finallion.graveyard.world.features.trees.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;


public class TGTreeFeatureConfig implements FeatureConfiguration {
    public static final Codec<TGTreeFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BlockState.CODEC.fieldOf("wood_state").forGetter((config) -> config.woodState),
            BlockState.CODEC.fieldOf("leaf_state").forGetter((config) -> config.leafState))
            .apply(instance, TGTreeFeatureConfig::new));

    public final BlockState woodState;
    public final BlockState leafState;

    public TGTreeFeatureConfig(BlockState woodState, BlockState leafState) {
        this.woodState = woodState;

        if (leafState.getProperties().contains(BlockStateProperties.DISTANCE)) {
            this.leafState = leafState.setValue(BlockStateProperties.DISTANCE, 1);
        } else {
            this.leafState = leafState;
        }
    }
}
