package com.finallion.graveyard.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Collection;

public class BiomeConfig {
    private String name;
    private double weight;

    private ForgeConfigSpec.ConfigValue<Double> configWeight;

    private BiomeConfig (String name, double weight, Collection<BiomeConfig> list) {
        this.name = name;
        this.weight = weight;
        list.add(this);
    }

    private BiomeConfig(String name, int weight, Collection<BiomeConfig> list) {
        this(name, (double) weight, list);
    }

    public String name() {
        return name;
    }

    public double weight() {
        return configWeight.get();
    }

    public void apply(ForgeConfigSpec.Builder builder) {
        builder.push(name);
        configWeight = builder.comment("Weight of the biome. Set to 0 to disable generation.").define("weight", weight);
        builder.pop();
    }

    public static class Builder {
        private final Collection<BiomeConfig> list;

        public Builder(Collection<BiomeConfig> list) {
            this.list = list;
        }

        public BiomeConfig build(String name, int weight) {
            return new BiomeConfig(name, weight, this.list);
        }
    }
}
