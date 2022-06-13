package com.finallion.graveyard.world.biomes.features.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class SoulLightFeature extends Feature<NoFeatureConfig> {


    public SoulLightFeature(Codec<NoFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(pos);

        mutable.set(pos);
        mutable.move(random.nextInt(10) - random.nextInt(10), 0, random.nextInt(10) - random.nextInt(10));
        mutable.setY(63);


        if (world.getBlockState(mutable).getBlock().is(Blocks.LILY_PAD) && world.getBlockState(mutable.above()).isAir() && world.getBlockState(mutable.above(2)).isAir()) {
            world.setBlock(mutable.move(0, random.nextInt(2) + 1, 0), Blocks.SOUL_LANTERN.defaultBlockState(), 2);
            return true;
        }




        return false;
    }
}
