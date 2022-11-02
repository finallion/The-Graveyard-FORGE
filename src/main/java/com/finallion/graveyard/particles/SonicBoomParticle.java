package com.finallion.graveyard.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;


public class SonicBoomParticle extends GraveyardExplosionParticle {

    protected SonicBoomParticle(ClientLevel clientWorld, double d, double e, double f, double g, SpriteSet spriteProvider) {
        super(clientWorld, d, e, f, g, spriteProvider);
        this.lifetime = 16;
        this.quadSize = 1.5F;
        this.setSpriteFromAge(spriteProvider);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public Provider(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType defaultParticleType, ClientLevel clientWorld, double d, double e, double f, double g, double h, double i) {
            return new SonicBoomParticle(clientWorld, d, e, f, g, this.spriteProvider);
        }
    }
}

