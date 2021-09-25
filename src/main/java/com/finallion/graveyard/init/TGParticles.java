package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.particles.GraveyardFogParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, TheGraveyard.MOD_ID);
    public static final RegistryObject<BasicParticleType> GRAVEYARD_FOG_PARTICLE = PARTICLES.register("graveyard_fog_particle", () -> new BasicParticleType(true));


    @SubscribeEvent
    public static void initParticles(ParticleFactoryRegisterEvent event){
        Minecraft.getInstance().particleEngine.register(GRAVEYARD_FOG_PARTICLE.get(), GraveyardFogParticle.FogFactory::new);
    }



}
