package com.finallion.graveyard.world.biomes;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.TGBiomes;
import com.finallion.graveyard.world.surfacerules.TGSurfaceRules;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.SurfaceRules;
import terrablender.api.BiomeProvider;
import terrablender.worldgen.TBClimate;

import java.util.Optional;
import java.util.function.Consumer;


public class TGBiomeProvider extends BiomeProvider {

    public TGBiomeProvider(ResourceLocation name, int overworldWeight){
            super(name, overworldWeight);
    }


    @Override
    public void addOverworldBiomes(Registry<Biome> registry, Consumer<Pair<TBClimate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {

            if (GraveyardConfig.COMMON.enableHauntedForest.get()) {
                builder.replaceBiome(Biomes.WINDSWEPT_FOREST, TGBiomes.HAUNTED_FOREST_KEY);
            }

            if (GraveyardConfig.COMMON.enableErodedHauntedForest.get()) {
                builder.replaceBiome(Biomes.MEADOW, TGBiomes.ERODED_HAUNTED_FOREST_KEY);
            }

            if (GraveyardConfig.COMMON.enableHauntedLakes.get()) {
                builder.replaceBiome(Biomes.SWAMP, TGBiomes.HAUNTED_LAKES_KEY);
            }

            if (GraveyardConfig.COMMON.enableAncientDeadCoralReef.get()) {
                builder.replaceBiome(Biomes.BEACH, TGBiomes.ANCIENT_DEAD_CORAL_REEF_KEY);
            }

        });
    }

    @Override
    public Optional<SurfaceRules.RuleSource> getOverworldSurfaceRules() {
        return Optional.of(TGSurfaceRules.makeRules());
    }

}

