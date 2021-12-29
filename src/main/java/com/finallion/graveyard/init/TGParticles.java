package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.particles.GraveyardFogParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, TheGraveyard.MOD_ID);
    public static final RegistryObject<SimpleParticleType> GRAVEYARD_FOG_PARTICLE = PARTICLES.register("graveyard_fog_particle", () -> new SimpleParticleType(true));


    @SubscribeEvent
    public static void initParticles(ParticleFactoryRegisterEvent event){
        Minecraft.getInstance().particleEngine.register(GRAVEYARD_FOG_PARTICLE.get(), GraveyardFogParticle.FogFactory::new);
    }



}
