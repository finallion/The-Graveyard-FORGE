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



}
