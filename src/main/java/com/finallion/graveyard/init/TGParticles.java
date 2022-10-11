package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TGParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, TheGraveyard.MOD_ID);

    public static final RegistryObject<SimpleParticleType> GRAVEYARD_FOG_PARTICLE = PARTICLES.register("graveyard_fog_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> GRAVEYARD_SOUL_PARTICLE = PARTICLES.register("graveyard_soul_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> GRAVEYARD_HAND_PARTICLE = PARTICLES.register("graveyard_hand_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> GRAVEYARD_LEFT_HAND_PARTICLE = PARTICLES.register("graveyard_left_hand_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> GRAVEYARD_SOUL_BEAM_PARTICLE = PARTICLES.register("graveyard_soul_beam_particle", () -> new SimpleParticleType(true));


}
