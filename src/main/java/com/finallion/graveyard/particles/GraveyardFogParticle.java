package com.finallion.graveyard.particles;


import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GraveyardFogParticle extends SpriteTexturedParticle {
    private double startY;


    GraveyardFogParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
        super(clientWorld, d, e, f, g, h, i);
        this.hasPhysics = false;
        this.quadSize *= 6.0D;
        this.yd *= 0.002999999552965164D;
        this.startY = y;
    }


    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }


    // particle starts roughly at blocks y level (see TGMossBlock)
    // it slowly rises and dies when it reaches a certain height above the block
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        this.move(0, this.yo, 0);

        if (this.y >= startY + 1.0D + random.nextDouble()) {
            this.remove();
        }

    }

    @OnlyIn(Dist.CLIENT)
    public static class FogFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteProvider;

        public FogFactory(IAnimatedSprite spriteProvider) {
            this.spriteProvider = spriteProvider;
        }


        public Particle createParticle(BasicParticleType p_199234_1_, ClientWorld p_199234_2_, double p_199234_3_, double p_199234_5_, double p_199234_7_, double p_199234_9_, double p_199234_11_, double p_199234_13_) {
            GraveyardFogParticle suspendParticle = new GraveyardFogParticle(p_199234_2_, p_199234_3_, p_199234_5_, p_199234_7_, p_199234_9_, p_199234_11_, p_199234_13_);
            suspendParticle.pickSprite(this.spriteProvider);
            return suspendParticle;
        }
    }
}
