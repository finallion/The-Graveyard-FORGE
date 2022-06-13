package com.finallion.graveyard.world.biomes.surfacebuilders;

import com.finallion.graveyard.init.TGSurfaceBuilders;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class ErodedHauntedForestSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

    public ErodedHauntedForestSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
        super(codec);
    }

    @Override
    public void apply(Random random, IChunk chunk, Biome biome, int x, int z, int height, double noise, BlockState defaultBlock, BlockState defaultFluid, int i, long l, SurfaceBuilderConfig surfaceConfig) {
        if (noise > 1.75D) {
            SurfaceBuilder.DEFAULT.apply(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, i, l, SurfaceBuilder.CONFIG_COARSE_DIRT);
        } else if (noise > -0.95D) {
            if (random.nextBoolean()) {
                SurfaceBuilder.DEFAULT.apply(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, i, l, TGSurfaceBuilders.Configs.SOUL_SOIL_CONFIG);
            } else {
                SurfaceBuilder.DEFAULT.apply(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, i, l, TGSurfaceBuilders.Configs.SOUL_SAND_CONFIG);
            }
        } else {
            if (random.nextInt(4) == 0) {
                SurfaceBuilder.DEFAULT.apply(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, i, l, TGSurfaceBuilders.Configs.PARTICLE_MOSS_CONFIG);
            } else {
                SurfaceBuilder.DEFAULT.apply(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, i, l, TGSurfaceBuilders.Configs.MOSS_CONFIG);
            }

        }
    }



}
