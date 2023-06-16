package com.finallion.graveyard.blocks;

import com.finallion.graveyard.init.TGParticles;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;


public class AltarCenterBlock extends Block {

    public AltarCenterBlock(AbstractBlock.Properties settings) {
        super(settings);
    }

    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        super.animateTick(state, world, pos, random);

        if (random.nextInt(10) == 0) {
            world.addParticle(TGParticles.GRAVEYARD_SOUL_PARTICLE.get(), (double)pos.getX() + random.nextDouble(), (double)pos.getY() + 1.3D, (double)pos.getZ() + random.nextDouble(), 0.0D, 0.01D, 0.0D);
        }

        if (random.nextInt(100) == 0) {
            world.addParticle(TGParticles.GRAVEYARD_HAND_PARTICLE.get(), (double)pos.getX() + random.nextDouble(), (double)pos.getY() + 1.2D, (double)pos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
        }

        if (random.nextInt(100) == 0) {
            world.addParticle(TGParticles.GRAVEYARD_LEFT_HAND_PARTICLE.get(), (double)pos.getX() + random.nextDouble(), (double)pos.getY() + 1.2D, (double)pos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
        }
        //MathUtil.createParticleFlare(world, pos.getX(), pos.getY(), pos.getZ(), random.nextInt(300) + 150, ParticleTypes.SOUL, random);
    }


}
