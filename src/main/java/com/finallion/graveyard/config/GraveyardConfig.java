package com.finallion.graveyard.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.finallion.graveyard.TheGraveyard;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GraveyardConfig {
    public static final CommonConfig COMMON_CONFIG;
    public static ForgeConfigSpec COMMON_SPEC;
    public static final GraveyardConfig INSTANCE = new GraveyardConfig();
    public static final List<BiomeConfig> BIOMES = new ArrayList<>();

    // biome config
    private static final BiomeConfig.Builder config = new BiomeConfig.Builder(BIOMES);

    @SubscribeEvent
    public static void onModConfigEvent(final net.minecraftforge.fml.config.ModConfig.ModConfigEvent e) {
        if (e.getConfig().getSpec() == COMMON_SPEC) bakeCommonConfig();

    }

    public static final BiomeConfig HAUNTED_FOREST = config.build("haunted_forest", 3);
    public static final BiomeConfig HAUNTED_LAKES = config.build("haunted_lakes", 3);
    public static final BiomeConfig ERODED_HAUNTED_FOREST = config.build("eroded_haunted_forest", 2);

    public boolean ENABLE_HAUNTED_FOREST_FOG, ENABLE_HAUNTED_LAKES_FOG, ENABLE_ERODED_HAUNTED_FOREST_FOG, ENABLE_GRAVEYARD_FOG;
    public boolean ENABLE_BIRCH_TREE, ENABLE_MEDIUM_GRAVEYARD, ENABLE_LARGE_GRAVEYARD, ENABLE_SMALL_GRAVEYARD, ENABLE_SMALL_GRAVEYARD_DESERT, ENABLE_SMALL_GRAVEYARD_SAVANNA, ENABLE_MUSHROOM_GRAVE, ENABLE_GRAVE, ENABLE_HAUNTED_HOUSE, ENABLE_DESERT_GRAVE, ENABLE_SAVANNA_GRAVE, ENABLE_MOUNTAIN_GRAVE;
    public int BIRCH_TREE_SEPARATION, MEDIUM_GRAVEYARD_SEPARATION, LARGE_GRAVEYARD_SEPARATION, SMALL_GRAVEYARD_SEPARATION, SMALL_GRAVEYARD_DESERT_SEPARATION, SMALL_GRAVEYARD_SAVANNA_SEPARATION, MUSHROOM_GRAVE_SEPARATION, GRAVE_SEPARATION, HAUNTED_HOUSE_SEPARATION, DESERT_GRAVE_SEPARATION, SAVANNA_GRAVE_SEPARATION, MOUNTAIN_GRAVE_SEPARATION;
    public int BIRCH_TREE_SPACING, MEDIUM_GRAVEYARD_SPACING, LARGE_GRAVEYARD_SPACING, SMALL_GRAVEYARD_SPACING, SMALL_GRAVEYARD_DESERT_SPACING, SMALL_GRAVEYARD_SAVANNA_SPACING, MUSHROOM_GRAVE_SPACING, GRAVE_SPACING, HAUNTED_HOUSE_SPACING, DESERT_GRAVE_SPACING, SAVANNA_GRAVE_SPACING, MOUNTAIN_GRAVE_SPACING;
    public int BIRCH_TREE_SALT, MEDIUM_GRAVEYARD_SALT, LARGE_GRAVEYARD_SALT, SMALL_GRAVEYARD_SALT, SMALL_GRAVEYARD_DESERT_SALT, SMALL_GRAVEYARD_SAVANNA_SALT, MUSHROOM_GRAVE_SALT, GRAVE_SALT, HAUNTED_HOUSE_SALT, DESERT_GRAVE_SALT, SAVANNA_GRAVE_SALT, MOUNTAIN_GRAVE_SALT;
    public int HAUNTED_FOREST_FOG_MAXY, HAUNTED_LAKES_FOG_MAXY, ERODED_HAUNTED_FOREST_FOG_MAXY, HAUNTED_FOREST_FOG_MINY, HAUNTED_LAKES_FOG_MINY, ERODED_HAUNTED_FOREST_FOG_MINY, FOG_CHANCE;
    public double HAUNTED_FOREST_FOG_DENSITY, HAUNTED_LAKES_FOG_DENSITY, ERODED_HAUNTED_FOREST_FOG_DENSITY;

    public static class CommonConfig {
        public ForgeConfigSpec.BooleanValue large_birch_tree, medium_walled_graveyard, small_walled_graveyard, small_walled_graveyard_desert, small_walled_graveyard_savanna, large_walled_graveyard, haunted_house, small_grave, mushroom_grave, small_desert_grave, small_savanna_grave, small_mountain_grave;
        public ForgeConfigSpec.BooleanValue fog_particle, haunted_forest_fog, haunted_lakes_fog, eroded_haunted_forest_fog;
        public ForgeConfigSpec.ConfigValue<Integer> large_birch_tree_separation, large_birch_tree_spacing, large_birch_tree_salt,
                medium_walled_graveyard_separation, medium_walled_graveyard_spacing, medium_walled_graveyard_salt,
                small_walled_graveyard_separation, small_walled_graveyard_spacing, small_walled_graveyard_salt,
                small_walled_graveyard_desert_separation, small_walled_graveyard_desert_spacing, small_walled_graveyard_desert_salt,
                small_walled_graveyard_savanna_separation, small_walled_graveyard_savanna_spacing, small_walled_graveyard_savanna_salt,
                large_walled_graveyard_separation, large_walled_graveyard_spacing, large_walled_graveyard_salt,
                haunted_house_separation, haunted_house_spacing, haunted_house_salt,
                small_grave_separation, small_grave_spacing, small_grave_salt,
                small_desert_grave_separation, small_desert_grave_spacing, small_desert_grave_salt,
                small_savanna_grave_separation, small_savanna_grave_spacing, small_savanna_grave_salt,
                small_mountain_grave_separation, small_mountain_grave_spacing, small_mountain_grave_salt,
                mushroom_grave_separation, mushroom_grave_spacing, mushroom_grave_salt;
        public ForgeConfigSpec.ConfigValue<Integer> fog_particle_chance,
                haunted_forest_fogMaxY, haunted_forest_fogMinY,
                haunted_lakes_fogMaxY, haunted_lakes_fogMinY,
                eroded_haunted_forest_fogMaxY, eroded_haunted_forest_fogMinY;
        public ForgeConfigSpec.ConfigValue<Double> haunted_forest_fog_density, haunted_lakes_fog_density, eroded_haunted_forest_fog_density;


        public CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.push("Structure Generation");
            large_birch_tree = builder.comment("Enable Large Birch Tree Structure:").translation(TheGraveyard.MOD_ID + ":config:large_birch_tree").worldRestart().define("large_birch_tree", true);
            large_birch_tree_separation = builder.comment("Large Birch Tree Separation:").translation(TheGraveyard.MOD_ID + ":config:large_birch_tree_separation").worldRestart().define("large_birch_tree_separation", 12);
            large_birch_tree_spacing = builder.comment("Large Birch Tree Spacing:").translation(TheGraveyard.MOD_ID + ":config:large_birch_tree_spacing").worldRestart().define("large_birch_tree_spacing", 14);
            large_birch_tree_salt = builder.comment("Large Birch Tree Salt:").translation(TheGraveyard.MOD_ID + ":config:large_birch_tree_salt").worldRestart().define("large_birch_tree_salt", 304812394);

            medium_walled_graveyard = builder.comment("Enable Medium Graveyard Structure:").translation(TheGraveyard.MOD_ID + ":config:medium_walled_graveyard").worldRestart().define("medium_walled_graveyard", true);
            medium_walled_graveyard_separation = builder.comment("Medium Graveyard Separation:").translation(TheGraveyard.MOD_ID + ":config:medium_walled_graveyard_separation").worldRestart().define("medium_walled_graveyard_separation", 14);
            medium_walled_graveyard_spacing = builder.comment("Medium Graveyard Spacing:").translation(TheGraveyard.MOD_ID + ":config:medium_walled_graveyard_spacing").worldRestart().define("medium_walled_graveyard_spacing", 16);
            medium_walled_graveyard_salt = builder.comment("Medium Graveyard Salt:").translation(TheGraveyard.MOD_ID + ":config:medium_walled_graveyard_salt").worldRestart().define("medium_walled_graveyard_salt", 379123039);

            large_walled_graveyard = builder.comment("Enable Large Graveyard Structure:").translation(TheGraveyard.MOD_ID + ":config:large_walled_graveyard").worldRestart().define("large_walled_graveyard", true);
            large_walled_graveyard_separation = builder.comment("Large Graveyard Separation:").translation(TheGraveyard.MOD_ID + ":config:large_walled_graveyard_separation").worldRestart().define("large_walled_graveyard_separation", 9);
            large_walled_graveyard_spacing = builder.comment("Large Graveyard Spacing:").translation(TheGraveyard.MOD_ID + ":config:large_walled_graveyard_spacing").worldRestart().define("large_walled_graveyard_spacing", 11);
            large_walled_graveyard_salt = builder.comment("Large Graveyard Salt:").translation(TheGraveyard.MOD_ID + ":config:large_walled_graveyard_salt").worldRestart().define("large_walled_graveyard_salt", 739017628);

            small_walled_graveyard = builder.comment("Enable Small Graveyard Structure:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard").worldRestart().define("small_walled_graveyard", true);
            small_walled_graveyard_separation = builder.comment("Small Graveyard Separation:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_separation").worldRestart().define("small_walled_graveyard_separation", 18);
            small_walled_graveyard_spacing = builder.comment("Small Graveyard Spacing:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_spacing").worldRestart().define("small_walled_graveyard_spacing", 20);
            small_walled_graveyard_salt = builder.comment("Small Graveyard Salt:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_salt").worldRestart().define("small_walled_graveyard_salt", 1690192399);

            small_walled_graveyard_desert = builder.comment("Enable Small Graveyard Desert Structure:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_desert").worldRestart().define("small_walled_graveyard_desert", true);
            small_walled_graveyard_desert_separation = builder.comment("Small Graveyard Desert Separation:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_desert_separation").worldRestart().define("small_walled_graveyard_desert_separation", 28);
            small_walled_graveyard_desert_spacing = builder.comment("Small Graveyard Desert Spacing:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_desert_spacing").worldRestart().define("small_walled_graveyard_desert_spacing", 32);
            small_walled_graveyard_desert_salt = builder.comment("Small Graveyard Desert Salt:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_desert_salt").worldRestart().define("small_walled_graveyard_desert_salt", 661903018);

            small_walled_graveyard_savanna = builder.comment("Enable Small Graveyard Savanna Structure:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_savanna").worldRestart().define("small_walled_graveyard_savanna", true);
            small_walled_graveyard_savanna_separation = builder.comment("Small Graveyard Savanna Separation:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_savanna_separation").worldRestart().define("small_walled_graveyard_savanna_separation", 12);
            small_walled_graveyard_savanna_spacing = builder.comment("Small Graveyard Savanna Spacing:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_savanna_spacing").worldRestart().define("small_walled_graveyard_savanna_spacing", 14);
            small_walled_graveyard_savanna_salt = builder.comment("Small Graveyard Savanna Salt:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_savanna_salt").worldRestart().define("small_walled_graveyard_savanna_salt", 451235912);

            small_grave = builder.comment("Enable Small Grave Structure:").translation(TheGraveyard.MOD_ID + ":config:small_grave").worldRestart().define("small_grave", true);
            small_grave_separation = builder.comment("Small Grave Separation:").translation(TheGraveyard.MOD_ID + ":config:small_grave_separation").worldRestart().define("small_grave_separation", 8);
            small_grave_spacing = builder.comment("Small Grave Spacing:").translation(TheGraveyard.MOD_ID + ":config:small_grave_spacing").worldRestart().define("small_grave_spacing", 12);
            small_grave_salt = builder.comment("Small Grave Salt:").translation(TheGraveyard.MOD_ID + ":config:small_grave_salt").worldRestart().define("small_grave_salt", 240451934);

            small_desert_grave = builder.comment("Enable Small Desert Grave Structure:").translation(TheGraveyard.MOD_ID + ":config:small_desert_grave").worldRestart().define("small_desert_grave", true);
            small_desert_grave_separation = builder.comment("Small Desert Grave Separation:").translation(TheGraveyard.MOD_ID + ":config:small_desert_grave_separation").worldRestart().define("small_desert_grave_separation", 20);
            small_desert_grave_spacing = builder.comment("Small Desert Grave Spacing:").translation(TheGraveyard.MOD_ID + ":config:small_desert_grave_spacing").worldRestart().define("small_desert_grave_spacing", 22);
            small_desert_grave_salt = builder.comment("Small Desert Grave Salt:").translation(TheGraveyard.MOD_ID + ":config:small_desert_grave_salt").worldRestart().define("small_desert_grave_salt", 240451934);

            small_savanna_grave = builder.comment("Enable Small Savanna Grave Structure:").translation(TheGraveyard.MOD_ID + ":config:small_savanna_grave").worldRestart().define("small_savanna_grave", true);
            small_savanna_grave_separation = builder.comment("Small Savanna Grave Separation:").translation(TheGraveyard.MOD_ID + ":config:small_savanna_grave_separation").worldRestart().define("small_savanna_grave_separation", 20);
            small_savanna_grave_spacing = builder.comment("Small Savanna Grave Spacing:").translation(TheGraveyard.MOD_ID + ":config:small_savanna_grave_spacing").worldRestart().define("small_savanna_grave_spacing", 22);
            small_savanna_grave_salt = builder.comment("Small Savanna Grave Salt:").translation(TheGraveyard.MOD_ID + ":config:small_savanna_grave_salt").worldRestart().define("small_savanna_grave_salt", 240451934);

            small_mountain_grave = builder.comment("Enable Small Mountain Grave Structure:").translation(TheGraveyard.MOD_ID + ":config:small_mountain_grave").worldRestart().define("small_mountain_grave", true);
            small_mountain_grave_separation = builder.comment("Small Mountain Grave Separation:").translation(TheGraveyard.MOD_ID + ":config:small_mountain_grave_separation").worldRestart().define("small_mountain_grave_separation", 8);
            small_mountain_grave_spacing = builder.comment("Small Mountain Grave Spacing:").translation(TheGraveyard.MOD_ID + ":config:small_mountain_grave_spacing").worldRestart().define("small_mountain_grave_spacing", 12);
            small_mountain_grave_salt = builder.comment("Small Mountain Grave Salt:").translation(TheGraveyard.MOD_ID + ":config:small_mountain_grave_salt").worldRestart().define("small_mountain_grave_salt", 240451934);

            mushroom_grave = builder.comment("Enable Mushroom Grave Structure:").translation(TheGraveyard.MOD_ID + ":config:mushroom_grave").worldRestart().define("mushroom_grave", true);
            mushroom_grave_separation = builder.comment("Mushroom Grave Separation:").translation(TheGraveyard.MOD_ID + ":config:mushroom_grave_separation").worldRestart().define("mushroom_grave_separation", 18);
            mushroom_grave_spacing = builder.comment("Mushroom Grave Spacing:").translation(TheGraveyard.MOD_ID + ":config:mushroom_grave_spacing").worldRestart().define("mushroom_grave_spacing", 24);
            mushroom_grave_salt = builder.comment("Mushroom Grave Salt:").translation(TheGraveyard.MOD_ID + ":config:mushroom_grave_salt").worldRestart().define("mushroom_grave_salt", 598017285);

            haunted_house = builder.comment("Enable Haunted House Structure:").translation(TheGraveyard.MOD_ID + ":config:haunted_house").worldRestart().define("haunted_house", true);
            haunted_house_separation = builder.comment("Haunted House Separation:").translation(TheGraveyard.MOD_ID + ":config:haunted_house_separation").worldRestart().define("haunted_house_separation", 20);
            haunted_house_spacing = builder.comment("Haunted House Spacing:").translation(TheGraveyard.MOD_ID + ":config:haunted_house_spacing").worldRestart().define("haunted_house_spacing", 25);
            haunted_house_salt = builder.comment("Haunted House Salt:").translation(TheGraveyard.MOD_ID + ":config:haunted_house_salt").worldRestart().define("haunted_house_salt", 529239621);
            builder.pop();

            builder.push("World Generation");
            HAUNTED_FOREST.apply(builder);
            HAUNTED_LAKES.apply(builder);
            ERODED_HAUNTED_FOREST.apply(builder);
            builder.pop();

            builder.push("Fog Generation in Graveyards");
            fog_particle = builder.comment("Enable fog rising from moss in graveyards").translation(TheGraveyard.MOD_ID + ".config.fog_particle").worldRestart().define("fog_particle", true);
            fog_particle_chance = builder.comment("Particle chance 1 in ...:").translation(TheGraveyard.MOD_ID + ":config:fog_particle_chance").worldRestart().define("fog_particle_chance", 50);
            builder.pop();

            builder.push("Fog Generation in Biomes");
            haunted_forest_fog = builder.comment("Enable fog in Haunted Forest biome").translation(TheGraveyard.MOD_ID + ".config.haunted_forest_fog").worldRestart().define("haunted_forest_fog", true);
            haunted_forest_fogMaxY = builder.translation(TheGraveyard.MOD_ID + ":config:haunted_forest_fogMaxY").worldRestart().define("haunted_forest_fogMaxY", 256);
            haunted_forest_fogMinY = builder.translation(TheGraveyard.MOD_ID + ":config:haunted_forest_fogMinY").worldRestart().define("haunted_forest_fogMinY", 62);
            haunted_forest_fog_density = builder.comment("Fog densitiy").translation(TheGraveyard.MOD_ID + ":config:haunted_forest_fog_density").worldRestart().define("haunted_forest_fog_density", 0.02);

            haunted_lakes_fog = builder.comment("Enable fog in Haunted Lakes biome").translation(TheGraveyard.MOD_ID + ".config.haunted_lakes_fog").worldRestart().define("haunted_lakes_fog", true);
            haunted_lakes_fogMaxY = builder.translation(TheGraveyard.MOD_ID + ":config:haunted_lakes_fogMaxY").worldRestart().define("haunted_lakes_fogMaxY", 256);
            haunted_lakes_fogMinY = builder.translation(TheGraveyard.MOD_ID + ":config:haunted_lakes_fogMinY").worldRestart().define("haunted_lakes_fogMinY", 62);
            haunted_lakes_fog_density = builder.comment("Fog densitiy").translation(TheGraveyard.MOD_ID + ":config:haunted_lakes_fog_density").worldRestart().define("haunted_lakes_fog_density", 0.015);

            eroded_haunted_forest_fog = builder.comment("Enable fog in Eroded Haunted Forest biome").translation(TheGraveyard.MOD_ID + ".config.eroded_haunted_forest_fog").worldRestart().define("eroded_haunted_forest_fog", true);
            eroded_haunted_forest_fogMaxY = builder.translation(TheGraveyard.MOD_ID + ":config:eroded_haunted_forest_fogMaxY").worldRestart().define("eroded_haunted_forest_fogMaxY", 256);
            eroded_haunted_forest_fogMinY = builder.translation(TheGraveyard.MOD_ID + ":config:eroded_haunted_forest_fogMinY").worldRestart().define("eroded_haunted_forest_fogMinY", 62);
            eroded_haunted_forest_fog_density = builder.comment("Fog densitiy").translation(TheGraveyard.MOD_ID + ":config:eroded_haunted_forest_fog_density").worldRestart().define("eroded_haunted_forest_fog_density", 0.01);
            builder.pop();


        }
    }


    // kind of unnecessary
    private static void bakeCommonConfig() {
        INSTANCE.ENABLE_HAUNTED_FOREST_FOG = COMMON_CONFIG.haunted_forest_fog.get();
        INSTANCE.ENABLE_HAUNTED_LAKES_FOG = COMMON_CONFIG.haunted_lakes_fog.get();
        INSTANCE.ENABLE_ERODED_HAUNTED_FOREST_FOG = COMMON_CONFIG.eroded_haunted_forest_fog.get();
        INSTANCE.ENABLE_GRAVEYARD_FOG = COMMON_CONFIG.fog_particle.get();

        INSTANCE.HAUNTED_FOREST_FOG_MAXY = COMMON_CONFIG.haunted_forest_fogMaxY.get();
        INSTANCE.HAUNTED_LAKES_FOG_MAXY = COMMON_CONFIG.haunted_lakes_fogMaxY.get();
        INSTANCE.ERODED_HAUNTED_FOREST_FOG_MAXY = COMMON_CONFIG.eroded_haunted_forest_fogMaxY.get();
        INSTANCE.HAUNTED_FOREST_FOG_MINY = COMMON_CONFIG.haunted_forest_fogMinY.get();
        INSTANCE.HAUNTED_LAKES_FOG_MINY = COMMON_CONFIG.haunted_lakes_fogMinY.get();
        INSTANCE.ERODED_HAUNTED_FOREST_FOG_MINY = COMMON_CONFIG.eroded_haunted_forest_fogMinY.get();

        INSTANCE.HAUNTED_FOREST_FOG_DENSITY = COMMON_CONFIG.haunted_forest_fog_density.get();
        INSTANCE.HAUNTED_LAKES_FOG_DENSITY = COMMON_CONFIG.haunted_lakes_fog_density.get();
        INSTANCE.ERODED_HAUNTED_FOREST_FOG_DENSITY = COMMON_CONFIG.eroded_haunted_forest_fog_density.get();

        INSTANCE.FOG_CHANCE = COMMON_CONFIG.fog_particle_chance.get();

        INSTANCE.ENABLE_BIRCH_TREE = COMMON_CONFIG.large_birch_tree.get();
        INSTANCE.BIRCH_TREE_SEPARATION = COMMON_CONFIG.large_birch_tree_separation.get();
        INSTANCE.BIRCH_TREE_SPACING = COMMON_CONFIG.large_birch_tree_spacing.get();
        INSTANCE.BIRCH_TREE_SALT = COMMON_CONFIG.large_birch_tree_salt.get();

        INSTANCE.ENABLE_MEDIUM_GRAVEYARD = COMMON_CONFIG.medium_walled_graveyard.get();
        INSTANCE.MEDIUM_GRAVEYARD_SEPARATION = COMMON_CONFIG.medium_walled_graveyard_separation.get();
        INSTANCE.MEDIUM_GRAVEYARD_SPACING = COMMON_CONFIG.medium_walled_graveyard_spacing.get();
        INSTANCE.MEDIUM_GRAVEYARD_SALT = COMMON_CONFIG.medium_walled_graveyard_salt.get();

        INSTANCE.ENABLE_LARGE_GRAVEYARD = COMMON_CONFIG.large_walled_graveyard.get();
        INSTANCE.LARGE_GRAVEYARD_SEPARATION = COMMON_CONFIG.large_walled_graveyard_separation.get();
        INSTANCE.LARGE_GRAVEYARD_SPACING = COMMON_CONFIG.large_walled_graveyard_spacing.get();
        INSTANCE.LARGE_GRAVEYARD_SALT = COMMON_CONFIG.large_walled_graveyard_salt.get();

        INSTANCE.ENABLE_SMALL_GRAVEYARD = COMMON_CONFIG.small_walled_graveyard.get();
        INSTANCE.SMALL_GRAVEYARD_SEPARATION = COMMON_CONFIG.small_walled_graveyard_separation.get();
        INSTANCE.SMALL_GRAVEYARD_SPACING = COMMON_CONFIG.small_walled_graveyard_spacing.get();
        INSTANCE.SMALL_GRAVEYARD_SALT = COMMON_CONFIG.small_walled_graveyard_salt.get();

        INSTANCE.ENABLE_SMALL_GRAVEYARD_DESERT = COMMON_CONFIG.small_walled_graveyard_desert.get();
        INSTANCE.SMALL_GRAVEYARD_DESERT_SEPARATION = COMMON_CONFIG.small_walled_graveyard_desert_separation.get();
        INSTANCE.SMALL_GRAVEYARD_DESERT_SPACING = COMMON_CONFIG.small_walled_graveyard_desert_spacing.get();
        INSTANCE.SMALL_GRAVEYARD_DESERT_SALT = COMMON_CONFIG.small_walled_graveyard_desert_salt.get();

        INSTANCE.ENABLE_SMALL_GRAVEYARD_SAVANNA = COMMON_CONFIG.small_walled_graveyard_savanna.get();
        INSTANCE.SMALL_GRAVEYARD_SAVANNA_SEPARATION = COMMON_CONFIG.small_walled_graveyard_savanna_separation.get();
        INSTANCE.SMALL_GRAVEYARD_SAVANNA_SPACING = COMMON_CONFIG.small_walled_graveyard_savanna_spacing.get();
        INSTANCE.SMALL_GRAVEYARD_SAVANNA_SALT = COMMON_CONFIG.small_walled_graveyard_savanna_salt.get();

        INSTANCE.ENABLE_GRAVE = COMMON_CONFIG.small_grave.get();
        INSTANCE.GRAVE_SEPARATION = COMMON_CONFIG.small_grave_separation.get();
        INSTANCE.GRAVE_SPACING = COMMON_CONFIG.small_grave_spacing.get();
        INSTANCE.GRAVE_SALT = COMMON_CONFIG.small_grave_salt.get();

        INSTANCE.ENABLE_DESERT_GRAVE = COMMON_CONFIG.small_desert_grave.get();
        INSTANCE.DESERT_GRAVE_SEPARATION = COMMON_CONFIG.small_desert_grave_separation.get();
        INSTANCE.DESERT_GRAVE_SPACING = COMMON_CONFIG.small_desert_grave_spacing.get();
        INSTANCE.DESERT_GRAVE_SALT = COMMON_CONFIG.small_desert_grave_salt.get();

        INSTANCE.ENABLE_SAVANNA_GRAVE = COMMON_CONFIG.small_savanna_grave.get();
        INSTANCE.SAVANNA_GRAVE_SEPARATION = COMMON_CONFIG.small_savanna_grave_separation.get();
        INSTANCE.SAVANNA_GRAVE_SPACING = COMMON_CONFIG.small_savanna_grave_spacing.get();
        INSTANCE.SAVANNA_GRAVE_SALT = COMMON_CONFIG.small_savanna_grave_salt.get();

        INSTANCE.ENABLE_MOUNTAIN_GRAVE = COMMON_CONFIG.small_mountain_grave.get();
        INSTANCE.MOUNTAIN_GRAVE_SEPARATION = COMMON_CONFIG.small_mountain_grave_separation.get();
        INSTANCE.MOUNTAIN_GRAVE_SPACING = COMMON_CONFIG.small_mountain_grave_spacing.get();
        INSTANCE.MOUNTAIN_GRAVE_SALT = COMMON_CONFIG.small_mountain_grave_salt.get();

        INSTANCE.ENABLE_MUSHROOM_GRAVE = COMMON_CONFIG.mushroom_grave.get();
        INSTANCE.MUSHROOM_GRAVE_SEPARATION = COMMON_CONFIG.mushroom_grave_separation.get();
        INSTANCE.MUSHROOM_GRAVE_SPACING = COMMON_CONFIG.mushroom_grave_spacing.get();
        INSTANCE.MUSHROOM_GRAVE_SALT = COMMON_CONFIG.mushroom_grave_salt.get();

        INSTANCE.ENABLE_HAUNTED_HOUSE = COMMON_CONFIG.haunted_house.get();
        INSTANCE.HAUNTED_HOUSE_SEPARATION = COMMON_CONFIG.haunted_house_separation.get();
        INSTANCE.HAUNTED_HOUSE_SPACING = COMMON_CONFIG.haunted_house_spacing.get();
        INSTANCE.HAUNTED_HOUSE_SALT = COMMON_CONFIG.haunted_house_salt.get();

    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        configData.load();
        spec.setConfig(configData);
    }

    static {
        final Pair<CommonConfig, ForgeConfigSpec> COMMON_PAIR = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON_CONFIG = COMMON_PAIR.getLeft();
        COMMON_SPEC = COMMON_PAIR.getRight();
    }

}
