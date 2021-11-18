package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.biomes.surfacebuilders.ErodedHauntedForestSurfaceBuilder;
import com.finallion.graveyard.biomes.surfacebuilders.HauntedForestSurfaceBuilder;
import com.finallion.graveyard.init.TGBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGSurfaceBuilders {
    public static List<SurfaceBuilder<?>> surfaceBuilders = new ArrayList<>();

    @SubscribeEvent
    public static void registerSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {
        surfaceBuilders.forEach(surfaceBuilder -> event.getRegistry().register(surfaceBuilder));
    }

    public static final SurfaceBuilder<SurfaceBuilderConfig> HAUNTED_FOREST_SURFACE = registerSurfaceBuilder(new ResourceLocation(TheGraveyard.MOD_ID, "haunted_forest_surface"), new HauntedForestSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final ConfiguredSurfaceBuilder<?> HAUNTED_FOREST_SURFACE_CONFIG = registerConfiguredSurfaceBuilder("haunted_forest_surface", new ConfiguredSurfaceBuilder<>(HAUNTED_FOREST_SURFACE, SurfaceBuilder.CONFIG_COARSE_DIRT));
    //Configs.MOSS_CONFIG

    public static final SurfaceBuilder<SurfaceBuilderConfig> ERODED_HAUNTED_FOREST_SURFACE = registerSurfaceBuilder(new ResourceLocation(TheGraveyard.MOD_ID,"eroded_haunted_forest_surface"), new ErodedHauntedForestSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final ConfiguredSurfaceBuilder<?> ERODED_HAUNTED_FOREST_SURFACE_CONFIG = registerConfiguredSurfaceBuilder("eroded_haunted_forest_surface", new ConfiguredSurfaceBuilder<>(ERODED_HAUNTED_FOREST_SURFACE, SurfaceBuilder.CONFIG_COARSE_DIRT));


    public static SurfaceBuilder<SurfaceBuilderConfig> registerSurfaceBuilder(ResourceLocation location, SurfaceBuilder<SurfaceBuilderConfig> builder) {
        builder.setRegistryName(location);
        surfaceBuilders.add(builder);
        return builder;
    }


    private static ConfiguredSurfaceBuilder<?> registerConfiguredSurfaceBuilder(String id, ConfiguredSurfaceBuilder<?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(TheGraveyard.MOD_ID, id), feature);
    }


    public static class Configs {

        public static final SurfaceBuilderConfig PARTICLE_MOSS_CONFIG = new SurfaceBuilderConfig(TGBlocks.TG_MOSS_BLOCK.defaultBlockState(), TGBlocks.TG_ROOTED_DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
        public static final SurfaceBuilderConfig MOSS_CONFIG = new SurfaceBuilderConfig(TGBlocks.MOSS_BLOCK.defaultBlockState(), TGBlocks.TG_ROOTED_DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
        public static final SurfaceBuilderConfig SOUL_SOIL_CONFIG = new SurfaceBuilderConfig(Blocks.SOUL_SOIL.defaultBlockState(), Blocks.COARSE_DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
        public static final SurfaceBuilderConfig SOUL_SAND_CONFIG = new SurfaceBuilderConfig(Blocks.SOUL_SAND.defaultBlockState(), Blocks.COARSE_DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState());
    }

}
