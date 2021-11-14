package com.finallion.graveyard.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class TheGraveyardConfig {
    public final ConfigHelper.ConfigValueListener<Boolean> large_birch_tree;
    public final ConfigHelper.ConfigValueListener<Integer> large_birch_tree_separation;
    public final ConfigHelper.ConfigValueListener<Integer> large_birch_tree_spacing;
    public final ConfigHelper.ConfigValueListener<Integer> large_birch_tree_salt;

    public final ConfigHelper.ConfigValueListener<Boolean> medium_walled_graveyard;
    public final ConfigHelper.ConfigValueListener<Integer> medium_walled_graveyard_separation;
    public final ConfigHelper.ConfigValueListener<Integer> medium_walled_graveyard_spacing;
    public final ConfigHelper.ConfigValueListener<Integer> medium_walled_graveyard_salt;

    public final ConfigHelper.ConfigValueListener<Boolean> small_walled_graveyard;
    public final ConfigHelper.ConfigValueListener<Integer> small_walled_graveyard_separation;
    public final ConfigHelper.ConfigValueListener<Integer> small_walled_graveyard_spacing;
    public final ConfigHelper.ConfigValueListener<Integer> small_walled_graveyard_salt;

    public final ConfigHelper.ConfigValueListener<Boolean> small_walled_graveyard_desert;
    public final ConfigHelper.ConfigValueListener<Integer> small_walled_graveyard_desert_separation;
    public final ConfigHelper.ConfigValueListener<Integer> small_walled_graveyard_desert_spacing;
    public final ConfigHelper.ConfigValueListener<Integer> small_walled_graveyard_desert_salt;

    public final ConfigHelper.ConfigValueListener<Boolean> small_walled_graveyard_savanna;
    public final ConfigHelper.ConfigValueListener<Integer> small_walled_graveyard_savanna_separation;
    public final ConfigHelper.ConfigValueListener<Integer> small_walled_graveyard_savanna_spacing;
    public final ConfigHelper.ConfigValueListener<Integer> small_walled_graveyard_savanna_salt;

    public final ConfigHelper.ConfigValueListener<Boolean> large_walled_graveyard;
    public final ConfigHelper.ConfigValueListener<Integer> large_walled_graveyard_separation;
    public final ConfigHelper.ConfigValueListener<Integer> large_walled_graveyard_spacing;
    public final ConfigHelper.ConfigValueListener<Integer> large_walled_graveyard_salt;

    public final ConfigHelper.ConfigValueListener<Boolean> haunted_house;
    public final ConfigHelper.ConfigValueListener<Integer> haunted_house_separation;
    public final ConfigHelper.ConfigValueListener<Integer> haunted_house_spacing;
    public final ConfigHelper.ConfigValueListener<Integer> haunted_house_salt;

    public final ConfigHelper.ConfigValueListener<Boolean> small_grave;
    public final ConfigHelper.ConfigValueListener<Integer> small_grave_separation;
    public final ConfigHelper.ConfigValueListener<Integer> small_grave_spacing;
    public final ConfigHelper.ConfigValueListener<Integer> small_grave_salt;

    public final ConfigHelper.ConfigValueListener<Boolean> mushroom_grave;
    public final ConfigHelper.ConfigValueListener<Integer> mushroom_grave_separation;
    public final ConfigHelper.ConfigValueListener<Integer> mushroom_grave_spacing;
    public final ConfigHelper.ConfigValueListener<Integer> mushroom_grave_salt;

    public final ConfigHelper.ConfigValueListener<Boolean> fog_particle;
    public final ConfigHelper.ConfigValueListener<Integer> fog_particle_chance;

    public final ConfigHelper.ConfigValueListener<Boolean> haunted_forest;
    public final ConfigHelper.ConfigValueListener<Boolean> haunted_forest_fog;
    public final ConfigHelper.ConfigValueListener<Double> haunted_forest_fog_density;
    public final ConfigHelper.ConfigValueListener<Double> haunted_forest_chance;
    public final ConfigHelper.ConfigValueListener<Integer> haunted_forest_fogMaxY;
    public final ConfigHelper.ConfigValueListener<Integer> haunted_forest_fogMinY;

    public final ConfigHelper.ConfigValueListener<Boolean> haunted_lakes;
    public final ConfigHelper.ConfigValueListener<Boolean> haunted_lakes_fog;
    public final ConfigHelper.ConfigValueListener<Double> haunted_lakes_fog_density;
    public final ConfigHelper.ConfigValueListener<Double> haunted_lakes_chance;
    public final ConfigHelper.ConfigValueListener<Integer> haunted_lakes_fogMaxY;
    public final ConfigHelper.ConfigValueListener<Integer> haunted_lakes_fogMinY;

    public final ConfigHelper.ConfigValueListener<Boolean> eroded_haunted_forest;
    public final ConfigHelper.ConfigValueListener<Boolean> eroded_haunted_forest_fog;
    public final ConfigHelper.ConfigValueListener<Double> eroded_haunted_forest_fog_density;
    public final ConfigHelper.ConfigValueListener<Double> eroded_haunted_forest_chance;
    public final ConfigHelper.ConfigValueListener<Integer> eroded_haunted_forest_fogMaxY;
    public final ConfigHelper.ConfigValueListener<Integer> eroded_haunted_forest_fogMinY;




    public TheGraveyardConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber) {
        builder.comment(
                "Welcome to The Graveyard Config!\n" + "\n" +
                "Here, you can disable structures to spawn, change their chance of spawning, change their separation and also change their salt.\n" +
                "Additionally, you can turn the graveyard fog particles on and set the chance of spawning them (higher numbers = lower chance of spawning).\n" +
                "Graveyard fog particles spawn from graveyard moss and add an atmospheric and spooky element while exploring.\n" +
                "\n" +
                "To disable a structure to spawn, simply go to the corresponding structure and change true to false.\n" +
                "\n" +
                "The Graveyard uses the vanilla structure spawning system. That is -\n" +
                "- Separation is the minimum chunks between structures\n" +
                "- Spacing is the average chunks between structures (Spacing value needs to be higher than Separation value!)\n" +
                "- Salt is a special field that gives structures unique spawning positions. (Only change if you know what you are doing!)\n" +
                "\n" +
                "Notice: reducing the generation settings will increase the risk of structures overlapping. Especially jigsaw structures are unpredictable!"
                );

        builder.push("Large Birch Tree");
        large_birch_tree = booleanConfigValue(builder, subscriber, "Large Birch Tree Generation", "large_birch_tree", true);
        large_birch_tree_separation = integerConfigValue(builder, subscriber, "Large Birch Tree Separation", "large_birch_tree_separation", 12);
        large_birch_tree_spacing = integerConfigValue(builder, subscriber, "Large Birch Tree Spacing", "large_birch_tree_spacing", 14);
        large_birch_tree_salt = integerConfigValue(builder, subscriber, "Large Birch Tree Salt", "large_birch_tree_salt", 304812394);
        builder.pop();

        builder.push("Medium Walled Graveyard");
        medium_walled_graveyard = booleanConfigValue(builder, subscriber, "Medium Walled Graveyard Generation", "medium_walled_graveyard", true);
        medium_walled_graveyard_separation = integerConfigValue(builder, subscriber, "Medium Walled Graveyard Separation", "medium_walled_graveyard_separation", 14);
        medium_walled_graveyard_spacing = integerConfigValue(builder, subscriber, "Medium Walled Graveyard Spacing", "medium_walled_graveyard_spacing", 16);
        medium_walled_graveyard_salt = integerConfigValue(builder, subscriber, "Medium Walled Graveyard Salt", "medium_walled_graveyard_salt", 379123039);
        builder.pop();

        builder.push("Mushroom Grave");
        mushroom_grave = booleanConfigValue(builder, subscriber, "Mushroom Grave Generation", "mushroom_grave", true);
        mushroom_grave_separation = integerConfigValue(builder, subscriber, "Mushroom Grave Separation", "mushroom_grave_separation", 18);
        mushroom_grave_spacing = integerConfigValue(builder, subscriber, "Mushroom Grave Spacing", "mushroom_grave_spacing", 24);
        mushroom_grave_salt = integerConfigValue(builder, subscriber, "Mushroom Grave Salt", "mushroom_grave_salt", 598017285);
        builder.pop();

        builder.push("Small Grave");
        small_grave = booleanConfigValue(builder, subscriber, "Small Grave Generation", "small_grave", true);
        small_grave_separation = integerConfigValue(builder, subscriber, "Small Grave Separation", "small_grave_separation", 8);
        small_grave_spacing = integerConfigValue(builder, subscriber, "Small Grave Spacing", "small_grave_spacing", 12);
        small_grave_salt = integerConfigValue(builder, subscriber, "Small Grave Salt", "small_grave_salt", 240451934);
        builder.pop();

        builder.push("Small Walled Graveyard");
        small_walled_graveyard = booleanConfigValue(builder, subscriber, "Small Walled Graveyard Generation", "small_walled_graveyard", true);
        small_walled_graveyard_separation = integerConfigValue(builder, subscriber, "Small Walled Graveyard Separation", "small_walled_graveyard_separation", 18);
        small_walled_graveyard_spacing = integerConfigValue(builder, subscriber, "Small Walled Graveyard Spacing", "small_walled_graveyard_spacing", 20);
        small_walled_graveyard_salt = integerConfigValue(builder, subscriber, "Small Walled Graveyard Salt", "small_walled_graveyard_salt", 1690192399);
        builder.pop();

        builder.push("Small Walled Graveyard Desert");
        small_walled_graveyard_desert = booleanConfigValue(builder, subscriber, "Small Walled Graveyard Desert Generation", "small_walled_graveyard_desert", true);
        small_walled_graveyard_desert_separation = integerConfigValue(builder, subscriber, "Small Walled Graveyard Desert Separation", "small_walled_graveyard_desert_separation", 28);
        small_walled_graveyard_desert_spacing = integerConfigValue(builder, subscriber, "Small Walled Graveyard Desert Spacing", "small_walled_graveyard_desert_spacing", 32);
        small_walled_graveyard_desert_salt = integerConfigValue(builder, subscriber, "Small Walled Graveyard Desert Salt", "small_walled_graveyard_desert_salt", 661903018);
        builder.pop();

        builder.push("Small Walled Graveyard Savanna");
        small_walled_graveyard_savanna = booleanConfigValue(builder, subscriber, "Small Walled Graveyard Savanna Generation", "small_walled_graveyard_savanna", true);
        small_walled_graveyard_savanna_separation = integerConfigValue(builder, subscriber, "Small Walled Graveyard Savanna Separation", "small_walled_graveyard_savanna_separation", 12);
        small_walled_graveyard_savanna_spacing = integerConfigValue(builder, subscriber, "Small Walled Graveyard Savanna Spacing", "small_walled_graveyard_savanna_spacing", 14);
        small_walled_graveyard_savanna_salt = integerConfigValue(builder, subscriber, "Small Walled Graveyard Savanna Salt", "small_walled_graveyard_savanna_salt", 451235912);
        builder.pop();


        builder.push("Large Walled Graveyard");
        large_walled_graveyard = booleanConfigValue(builder, subscriber, "Large Walled Graveyard Generation", "large_walled_graveyard", true);
        large_walled_graveyard_separation = integerConfigValue(builder, subscriber, "Large Walled Graveyard Separation", "large_walled_graveyard_separation", 9);
        large_walled_graveyard_spacing = integerConfigValue(builder, subscriber, "Large Walled Graveyard Spacing", "large_walled_graveyard_spacing", 11);
        large_walled_graveyard_salt = integerConfigValue(builder, subscriber, "Large Walled Graveyard Salt", "large_walled_graveyard_salt", 739017628);
        builder.pop();

        builder.push("Haunted House");
        haunted_house = booleanConfigValue(builder, subscriber, "Haunted House Generation", "haunted_house", true);
        haunted_house_separation = integerConfigValue(builder, subscriber, "Haunted House Separation", "haunted_house_separation", 20);
        haunted_house_spacing = integerConfigValue(builder, subscriber, "Haunted House Spacing", "haunted_house_spacing", 25);
        haunted_house_salt = integerConfigValue(builder, subscriber, "Haunted House Salt", "haunted_house_salt", 529239621);
        builder.pop();

        builder.push("Graveyard Fog Particle");
        fog_particle = booleanConfigValue(builder, subscriber, "Graveyard Fog Particle Generation", "fog_particle", true);
        fog_particle_chance = integerConfigValue(builder, subscriber, "Graveyard Fog Particle Generation Chance (1 in ...)", "fog_particle_chance", 50);
        builder.pop();

        builder.push("Haunted Forest");
        haunted_forest = booleanConfigValue(builder, subscriber, "Haunted Forest Generation", "haunted_forest", true);
        haunted_forest_fog = booleanConfigValue(builder, subscriber, "Haunted Forest Fog", "haunted_forest_fog", true);
        haunted_forest_fog_density = doubleConfigValue(builder, subscriber, "Haunted Forest Fog Density", "haunted_forest_fog_density", 0.5F);
        haunted_forest_chance = doubleConfigValue(builder, subscriber, "Haunted Forest Chance", "haunted_forest_chance", 0.15F);
        haunted_forest_fogMaxY = integerConfigValue(builder, subscriber, "Haunted Forest Fog MaxY", "haunted_forest_fogMaxY", 256);
        haunted_forest_fogMinY = integerConfigValue(builder, subscriber, "Haunted Forest Fog MinY", "haunted_forest_fogMinY", 62);
        builder.pop();

        builder.push("Haunted Lakes");
        haunted_lakes = booleanConfigValue(builder, subscriber, "Haunted Lakes Generation", "haunted_lakes", true);
        haunted_lakes_fog = booleanConfigValue(builder, subscriber, "Haunted Lakes Fog", "haunted_lakes_fog", true);
        haunted_lakes_fog_density = doubleConfigValue(builder, subscriber, "Haunted Lakes Fog Density", "haunted_lakes_fog_density", 0.7F);
        haunted_lakes_chance = doubleConfigValue(builder, subscriber, "Haunted Lakes Chance", "haunted_lakes_chance", 0.15F);
        haunted_lakes_fogMaxY = integerConfigValue(builder, subscriber, "Haunted Lakes Fog MaxY", "haunted_lakes_fogMaxY", 256);
        haunted_lakes_fogMinY = integerConfigValue(builder, subscriber, "Haunted Lakes Fog MinY", "haunted_lakes_fogMinY", 62);
        builder.pop();

        builder.push("Eroded Haunted Forest");
        eroded_haunted_forest = booleanConfigValue(builder, subscriber, "Eroded Haunted Forest Generation", "eroded_haunted_forest", true);
        eroded_haunted_forest_fog = booleanConfigValue(builder, subscriber, "Eroded Haunted Forest Fog", "eroded_haunted_forest_fog", true);
        eroded_haunted_forest_fog_density = doubleConfigValue(builder, subscriber, "Eroded Haunted Forest Fog Density", "eroded_haunted_forest_fog_density", 0.9F);
        eroded_haunted_forest_chance = doubleConfigValue(builder, subscriber, "Eroded Haunted Forest Chance", "eroded_haunted_forest_chance", 0.1F);
        eroded_haunted_forest_fogMaxY = integerConfigValue(builder, subscriber, "Eroded Haunted Forest Fog MaxY", "eroded_haunted_forest_fogMaxY", 256);
        eroded_haunted_forest_fogMinY = integerConfigValue(builder, subscriber, "Eroded Haunted Forest Fog MinY", "eroded_haunted_forest_fogMinY", 62);
        builder.pop();


        builder.build();

    }



    public static ConfigHelper.ConfigValueListener<Boolean> booleanConfigValue(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber, String comment, String name, boolean value) {
        return subscriber.subscribe(builder
                .comment(comment)
                .define(name, value)
        );
    }

    public static ConfigHelper.ConfigValueListener<Integer> integerConfigValue(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber, String comment, String name, int value) {
        return subscriber.subscribe(builder
                .comment(comment)
                .define(name, value)
        );
    }

    public static ConfigHelper.ConfigValueListener<Double> doubleConfigValue(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber, String comment, String name, double value) {
        return subscriber.subscribe(builder
                .comment(comment)
                .define(name, value)
        );
    }


}
