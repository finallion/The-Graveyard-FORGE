package com.finallion.graveyard.blocks;

import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.TGParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Properties;
import java.util.Random;

public class TGMossBlock extends GrassBlock {

    public TGMossBlock(Properties settings) {
        super(settings);
    }

    @Override
    public void animateTick(BlockState p_180655_1_, World world, BlockPos pos, Random random) {
        super.animateTick(p_180655_1_, world, pos, random);

        if (GraveyardConfig.COMMON.enableMossParticle.get()) {
            if (random.nextInt(GraveyardConfig.COMMON.particleFrequency.get()) == 0) {
                if (world.isClientSide()) {
                    world.addParticle(TGParticles.GRAVEYARD_FOG_PARTICLE.get(), (double) pos.getX() + random.nextDouble(), (double) pos.getY() + random.nextDouble(), (double) pos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
                }

            }

            if (random.nextInt(250) == 0) {
                world.addParticle(TGParticles.GRAVEYARD_HAND_PARTICLE.get(), (double)pos.getX() + random.nextDouble(), (double)pos.getY() + 1.2D, (double)pos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
            }
        }
    }


}
