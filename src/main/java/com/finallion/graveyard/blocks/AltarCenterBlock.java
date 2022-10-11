package com.finallion.graveyard.blocks;

import com.finallion.graveyard.init.TGParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class AltarCenterBlock extends Block {

    public AltarCenterBlock(BlockBehaviour.Properties settings) {
        super(settings);
    }

    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
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
