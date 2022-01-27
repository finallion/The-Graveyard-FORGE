package com.finallion.graveyard.world.noise;


import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class TGNoiseParameters {
    public static final ResourceKey<NormalNoise.NoiseParameters> HAUNTED_FOREST_SURFACE = registerNoiseParameter("haunted_forest_surface");
    public static final ResourceKey<NormalNoise.NoiseParameters> HAUNTED_FOREST_NOISE = registerNoiseParameter("haunted_forest_noise");
    public static final ResourceKey<NormalNoise.NoiseParameters> HAUNTED_FOREST_PARTICLE_MOSS = registerNoiseParameter("haunted_forest_high_noise");
    public static final ResourceKey<NormalNoise.NoiseParameters> HAUNTED_FOREST_MOSS = registerNoiseParameter("haunted_forest_high_moss");
    public static final ResourceKey<NormalNoise.NoiseParameters> HAUNTED_FOREST_SOUL_SOIL = registerNoiseParameter("haunted_forest_soul_soil");


    public static NormalNoise.NoiseParameters init() {
        register(HAUNTED_FOREST_SURFACE, -5, 1.1, 0.5, 1.25, 1.4);
        register(HAUNTED_FOREST_PARTICLE_MOSS, -2, 1.0, 0.75);
        register(HAUNTED_FOREST_SOUL_SOIL, -6, 1.2, 0.5);
        register(HAUNTED_FOREST_NOISE, -1, 1.0);
        register(HAUNTED_FOREST_MOSS, -3, 1.0, 1.25);
        return BuiltinRegistries.NOISE.iterator().next();
    }



    private static void register(ResourceKey<NormalNoise.NoiseParameters> noise, int firstOctave, double firstAmplitude, double... amplitudes) {
        BuiltinRegistries.register(BuiltinRegistries.NOISE, noise, new NormalNoise.NoiseParameters(firstOctave, firstAmplitude, amplitudes));
    }

    private static ResourceKey<NormalNoise.NoiseParameters> registerNoiseParameter(String p_189310_) {
        return ResourceKey.create(Registry.NOISE_REGISTRY, new ResourceLocation(p_189310_));
    }

    public static NormalNoise instantiate(Registry<NormalNoise.NoiseParameters> p_189306_, PositionalRandomFactory p_189307_, ResourceKey<NormalNoise.NoiseParameters> p_189308_) {
        return NormalNoise.create(p_189307_.fromHashOf(p_189308_.location()), p_189306_.getOrThrow(p_189308_));
    }
}
