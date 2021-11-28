package com.finallion.graveyard.config;

import com.finallion.graveyard.TheGraveyard;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GraveyardConfig {
    public static final CommonConfig COMMON_CONFIG;
    public static final ForgeConfigSpec COMMON_SPEC;


    static {
        final Pair<CommonConfig, ForgeConfigSpec> COMMON_PAIR = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON_CONFIG = COMMON_PAIR.getLeft();
        COMMON_SPEC = COMMON_PAIR.getRight();
    }

    @SubscribeEvent
    public static void onModConfigEvent(final net.minecraftforge.fml.config.ModConfig.ModConfigEvent e) {
        if (e.getConfig().getSpec() == COMMON_SPEC) bakeCommonConfig();

    }

    public static final GraveyardConfig INSTANCE = new GraveyardConfig();

    public static final BiomeOptions BIOME_OPTIONS = new BiomeOptions();

    public boolean HAUNTED_FOREST, HAUNTED_LAKES, ERODED_HAUNTED_FOREST;
    public boolean ENABLE_HAUNTED_FOREST_FOG, ENABLE_HAUNTED_LAKES_FOG, ENABLE_ERODED_HAUNTED_FOREST_FOG, ENABLE_GRAVEYARD_FOG;
    public boolean ENABLE_BIRCH_TREE, ENABLE_MEDIUM_GRAVEYARD, ENABLE_LARGE_GRAVEYARD, ENABLE_SMALL_GRAVEYARD, ENABLE_SMALL_GRAVEYARD_DESERT, ENABLE_SMALL_GRAVEYARD_SAVANNA, ENABLE_MUSHROOM_GRAVE, ENABLE_GRAVE, ENABLE_HAUNTED_HOUSE;
    public int BIRCH_TREE_SEPARATION, MEDIUM_GRAVEYARD_SEPARATION, LARGE_GRAVEYARD_SEPARATION, SMALL_GRAVEYARD_SEPARATION, SMALL_GRAVEYARD_DESERT_SEPARATION, SMALL_GRAVEYARD_SAVANNA_SEPARATION, MUSHROOM_GRAVE_SEPARATION, GRAVE_SEPARATION, HAUNTED_HOUSE_SEPARATION;
    public int BIRCH_TREE_SPACING, MEDIUM_GRAVEYARD_SPACING, LARGE_GRAVEYARD_SPACING, SMALL_GRAVEYARD_SPACING, SMALL_GRAVEYARD_DESERT_SPACING, SMALL_GRAVEYARD_SAVANNA_SPACING, MUSHROOM_GRAVE_SPACING, GRAVE_SPACING, HAUNTED_HOUSE_SPACING;
    public int BIRCH_TREE_SALT, MEDIUM_GRAVEYARD_SALT, LARGE_GRAVEYARD_SALT, SMALL_GRAVEYARD_SALT, SMALL_GRAVEYARD_DESERT_SALT, SMALL_GRAVEYARD_SAVANNA_SALT, MUSHROOM_GRAVE_SALT, GRAVE_SALT, HAUNTED_HOUSE_SALT;
    public int HAUNTED_FOREST_FOG_MAXY, HAUNTED_LAKES_FOG_MAXY, ERODED_HAUNTED_FOREST_FOG_MAXY, HAUNTED_FOREST_FOG_MINY, HAUNTED_LAKES_FOG_MINY, ERODED_HAUNTED_FOREST_FOG_MINY, FOG_CHANCE;
    public int HAUNTED_FOREST_CHANCE, HAUNTED_LAKES_CHANCE, ERODED_HAUNTED_FOREST_CHANCE;
    public double HAUNTED_FOREST_FOG_DENSITY, HAUNTED_LAKES_FOG_DENSITY, ERODED_HAUNTED_FOREST_FOG_DENSITY;

    public static class CommonConfig{
        public ForgeConfigSpec.BooleanValue large_birch_tree, medium_walled_graveyard, small_walled_graveyard, small_walled_graveyard_desert, small_walled_graveyard_savanna, large_walled_graveyard, haunted_house, small_grave, mushroom_grave;
        public ForgeConfigSpec.BooleanValue fog_particle, haunted_forest_fog, haunted_lakes_fog, eroded_haunted_forest_fog;
        public ForgeConfigSpec.BooleanValue haunted_forest, haunted_lakes, eroded_haunted_forest;
        public ForgeConfigSpec.IntValue large_birch_tree_separation, large_birch_tree_spacing, large_birch_tree_salt,
                medium_walled_graveyard_separation, medium_walled_graveyard_spacing, medium_walled_graveyard_salt,
                small_walled_graveyard_separation, small_walled_graveyard_spacing, small_walled_graveyard_salt,
                small_walled_graveyard_desert_separation, small_walled_graveyard_desert_spacing, small_walled_graveyard_desert_salt,
                small_walled_graveyard_savanna_separation, small_walled_graveyard_savanna_spacing, small_walled_graveyard_savanna_salt,
                large_walled_graveyard_separation, large_walled_graveyard_spacing, large_walled_graveyard_salt,
                haunted_house_separation, haunted_house_spacing, haunted_house_salt,
                small_grave_separation, small_grave_spacing, small_grave_salt,
                mushroom_grave_separation, mushroom_grave_spacing, mushroom_grave_salt;
        public ForgeConfigSpec.IntValue fog_particle_chance,
                haunted_forest_fogMaxY, haunted_forest_fogMinY,
                haunted_lakes_fogMaxY, haunted_lakes_fogMinY,
                eroded_haunted_forest_fogMaxY, eroded_haunted_forest_fogMinY;
        public ForgeConfigSpec.IntValue haunted_forest_chance, haunted_lakes_chance, eroded_haunted_forest_chance;
        public ForgeConfigSpec.DoubleValue haunted_forest_fog_density, haunted_lakes_fog_density, eroded_haunted_forest_fog_density;


        public CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.push("Structure Generation");
            large_birch_tree = builder.comment("Enable Large Birch Tree Structure:").translation(TheGraveyard.MOD_ID + ":config:large_birch_tree").worldRestart().define("large_birch_tree", true);
            large_birch_tree_separation = builder.comment("Separation:").translation(TheGraveyard.MOD_ID + ":config:large_birch_tree_separation").worldRestart().defineInRange("large_birch_tree_separation", 12, 0, Integer.MAX_VALUE);
            large_birch_tree_spacing = builder.comment("Spacing:").translation(TheGraveyard.MOD_ID + ":config:large_birch_tree_spacing").worldRestart().defineInRange("large_birch_tree_spacing", 14, 0, Integer.MAX_VALUE);
            large_birch_tree_salt = builder.comment("Salt:").translation(TheGraveyard.MOD_ID + ":config:large_birch_tree_salt").worldRestart().defineInRange("large_birch_tree_salt", 304812394, 0, Integer.MAX_VALUE);

            medium_walled_graveyard = builder.comment("Enable Medium Graveyard Structure:").translation(TheGraveyard.MOD_ID + ":config:medium_walled_graveyard").worldRestart().define("medium_walled_graveyard", true);
            medium_walled_graveyard_separation = builder.comment("Separation:").translation(TheGraveyard.MOD_ID + ":config:medium_walled_graveyard_separation").worldRestart().defineInRange("medium_walled_graveyard_separation", 14, 0, Integer.MAX_VALUE);
            medium_walled_graveyard_spacing = builder.comment("Spacing:").translation(TheGraveyard.MOD_ID + ":config:medium_walled_graveyard_spacing").worldRestart().defineInRange("medium_walled_graveyard_spacing", 16, 0, Integer.MAX_VALUE);
            medium_walled_graveyard_salt = builder.comment("Salt:").translation(TheGraveyard.MOD_ID + ":config:medium_walled_graveyard_salt").worldRestart().defineInRange("medium_walled_graveyard_salt", 379123039, 0, Integer.MAX_VALUE);

            large_walled_graveyard = builder.comment("Enable Large Graveyard Structure:").translation(TheGraveyard.MOD_ID + ":config:large_walled_graveyard").worldRestart().define("large_walled_graveyard", true);
            large_walled_graveyard_separation = builder.comment("Separation:").translation(TheGraveyard.MOD_ID + ":config:large_walled_graveyard_separation").worldRestart().defineInRange("large_walled_graveyard_separation", 9, 0, Integer.MAX_VALUE);
            large_walled_graveyard_spacing = builder.comment("Spacing:").translation(TheGraveyard.MOD_ID + ":config:large_walled_graveyard_spacing").worldRestart().defineInRange("large_walled_graveyard_spacing", 11, 0, Integer.MAX_VALUE);
            large_walled_graveyard_salt = builder.comment("Salt:").translation(TheGraveyard.MOD_ID + ":config:large_walled_graveyard_salt").worldRestart().defineInRange("large_walled_graveyard_salt", 739017628, 0, Integer.MAX_VALUE);

            small_walled_graveyard = builder.comment("Enable Small Graveyard Structure:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard").worldRestart().define("small_walled_graveyard", true);
            small_walled_graveyard_separation = builder.comment("Separation:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_separation").worldRestart().defineInRange("small_walled_graveyard_separation", 18, 0, Integer.MAX_VALUE);
            small_walled_graveyard_spacing = builder.comment("Spacing:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_spacing").worldRestart().defineInRange("small_walled_graveyard_spacing", 20, 0, Integer.MAX_VALUE);
            small_walled_graveyard_salt = builder.comment("Salt:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_salt").worldRestart().defineInRange("small_walled_graveyard_salt", 1690192399, 0, Integer.MAX_VALUE);

            small_walled_graveyard_desert = builder.comment("Enable Small Graveyard Desert Structure:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_desert").worldRestart().define("small_walled_graveyard_desert", true);
            small_walled_graveyard_desert_separation = builder.comment("Separation:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_desert_separation").worldRestart().defineInRange("small_walled_graveyard_desert_separation", 28, 0, Integer.MAX_VALUE);
            small_walled_graveyard_desert_spacing = builder.comment("Spacing:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_desert_spacing").worldRestart().defineInRange("small_walled_graveyard_desert_spacing", 32, 0, Integer.MAX_VALUE);
            small_walled_graveyard_desert_salt = builder.comment("Salt:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_desert_salt").worldRestart().defineInRange("small_walled_graveyard_desert_salt", 661903018, 0, Integer.MAX_VALUE);

            small_walled_graveyard_savanna = builder.comment("Enable Small Graveyard Savanna Structure:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_savanna").worldRestart().define("small_walled_graveyard_savanna", true);
            small_walled_graveyard_savanna_separation = builder.comment("Separation:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_savanna_separation").worldRestart().defineInRange("small_walled_graveyard_savanna_separation", 12, 0, Integer.MAX_VALUE);
            small_walled_graveyard_savanna_spacing = builder.comment("Spacing:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_savanna_spacing").worldRestart().defineInRange("small_walled_graveyard_savanna_spacing", 14, 0, Integer.MAX_VALUE);
            small_walled_graveyard_savanna_salt = builder.comment("Salt:").translation(TheGraveyard.MOD_ID + ":config:small_walled_graveyard_savanna_salt").worldRestart().defineInRange("small_walled_graveyard_savanna_salt", 451235912, 0, Integer.MAX_VALUE);

            small_grave = builder.comment("Enable Small Grave Structure:").translation(TheGraveyard.MOD_ID + ":config:small_grave").worldRestart().define("small_grave", true);
            small_grave_separation = builder.comment("Separation:").translation(TheGraveyard.MOD_ID + ":config:small_grave_separation").worldRestart().defineInRange("small_grave_separation", 8, 0, Integer.MAX_VALUE);
            small_grave_spacing = builder.comment("Spacing:").translation(TheGraveyard.MOD_ID + ":config:small_grave_spacing").worldRestart().defineInRange("small_grave_spacing", 12, 0, Integer.MAX_VALUE);
            small_grave_salt = builder.comment("Salt:").translation(TheGraveyard.MOD_ID + ":config:small_grave_salt").worldRestart().defineInRange("small_grave_salt", 240451934, 0, Integer.MAX_VALUE);

            mushroom_grave = builder.comment("Enable Mushroom Grave Structure:").translation(TheGraveyard.MOD_ID + ":config:mushroom_grave").worldRestart().define("mushroom_grave", true);
            mushroom_grave_separation = builder.comment("Separation:").translation(TheGraveyard.MOD_ID + ":config:mushroom_grave_separation").worldRestart().defineInRange("mushroom_grave_separation", 18, 0, Integer.MAX_VALUE);
            mushroom_grave_spacing = builder.comment("Spacing:").translation(TheGraveyard.MOD_ID + ":config:mushroom_grave_spacing").worldRestart().defineInRange("mushroom_grave_spacing", 24, 0, Integer.MAX_VALUE);
            mushroom_grave_salt = builder.comment("Salt:").translation(TheGraveyard.MOD_ID + ":config:mushroom_grave_salt").worldRestart().defineInRange("mushroom_grave_salt", 598017285, 0, Integer.MAX_VALUE);

            haunted_house = builder.comment("Enable Haunted House Structure:").translation(TheGraveyard.MOD_ID + ":config:haunted_house").worldRestart().define("haunted_house", true);
            haunted_house_separation = builder.comment("Separation:").translation(TheGraveyard.MOD_ID + ":config:haunted_house_separation").worldRestart().defineInRange("haunted_house_separation", 20, 0, Integer.MAX_VALUE);
            haunted_house_spacing = builder.comment("Spacing:").translation(TheGraveyard.MOD_ID + ":config:haunted_house_spacing").worldRestart().defineInRange("haunted_house_spacing", 25, 0, Integer.MAX_VALUE);
            haunted_house_salt = builder.comment("Salt:").translation(TheGraveyard.MOD_ID + ":config:haunted_house_salt").worldRestart().defineInRange("haunted_house_salt", 529239621, 0, Integer.MAX_VALUE);
            builder.pop();

            /*
            builder.push("World Generation");
            haunted_forest = builder.comment("Enable Haunted Forest").translation(TheGraveyard.MOD_ID + ".config.haunted_forest").worldRestart().define("haunted_forest", true);
            haunted_forest_chance = builder.comment("Haunted Forest Generation Chance").translation(TheGraveyard.MOD_ID + ".config.haunted_forest_chance").worldRestart().defineInRange("haunted_forest_chance", 3, 0, Integer.MAX_VALUE);

            haunted_lakes = builder.comment("Enable Haunted Lakes").translation(TheGraveyard.MOD_ID + ".config.haunted_lakes").worldRestart().define("haunted_lakes", true);
            haunted_lakes_chance = builder.comment("Haunted Lakes Generation Chance").translation(TheGraveyard.MOD_ID + ".config.haunted_lakes_chance").worldRestart().defineInRange("haunted_lakes_chance", 3, 0, Integer.MAX_VALUE);

            eroded_haunted_forest = builder.comment("Enable Eroded Haunted Forest").translation(TheGraveyard.MOD_ID + ".config.eroded_haunted_forest").worldRestart().define("eroded_haunted_forest", true);
            eroded_haunted_forest_chance = builder.comment("Eroded Haunted Forest Generation Chance").translation(TheGraveyard.MOD_ID + ".config.eroded_haunted_forest_chance").worldRestart().defineInRange("eroded_haunted_forest_chance", 2, 0, Integer.MAX_VALUE);
            builder.pop();

             */


            builder.push("Fog Generation");
            fog_particle = builder.comment("Enable fog rising from moss in graveyards").translation(TheGraveyard.MOD_ID + ".config.fog_particle").worldRestart().define("fog_particle", true);
            fog_particle_chance = builder.comment("Particle chance 1 in ...:").translation(TheGraveyard.MOD_ID + ":config:fog_particle_chance").worldRestart().defineInRange("fog_particle_chance", 50, 0, Integer.MAX_VALUE);

            haunted_forest_fog = builder.comment("Enable fog in Haunted Forest biome").translation(TheGraveyard.MOD_ID + ".config.haunted_forest_fog").worldRestart().define("haunted_forest_fog", true);
            haunted_forest_fogMaxY = builder.comment("Fog upper limit").translation(TheGraveyard.MOD_ID + ":config:haunted_forest_fogMaxY").worldRestart().defineInRange("haunted_forest_fogMaxY", 256, 0, Integer.MAX_VALUE);
            haunted_forest_fogMinY = builder.comment("Fog lower limit").translation(TheGraveyard.MOD_ID + ":config:haunted_forest_fogMinY").worldRestart().defineInRange("haunted_forest_fogMinY", 62, 0, Integer.MAX_VALUE);
            haunted_forest_fog_density = builder.comment("Fog densitiy").translation(TheGraveyard.MOD_ID + ":config:haunted_forest_fog_density").worldRestart().defineInRange("haunted_forest_fog_density", 0.02, 0, Double.MAX_VALUE);

            haunted_lakes_fog = builder.comment("Enable fog in Haunted Lakes biome").translation(TheGraveyard.MOD_ID + ".config.haunted_lakes_fog").worldRestart().define("haunted_lakes_fog", true);
            haunted_lakes_fogMaxY = builder.comment("Fog upper limit").translation(TheGraveyard.MOD_ID + ":config:haunted_lakes_fogMaxY").worldRestart().defineInRange("haunted_lakes_fogMaxY", 256, 0, Integer.MAX_VALUE);
            haunted_lakes_fogMinY = builder.comment("Fog lower limit").translation(TheGraveyard.MOD_ID + ":config:haunted_lakes_fogMinY").worldRestart().defineInRange("haunted_lakes_fogMinY", 62, 0, Integer.MAX_VALUE);
            haunted_lakes_fog_density = builder.comment("Fog densitiy").translation(TheGraveyard.MOD_ID + ":config:haunted_lakes_fog_density").worldRestart().defineInRange("haunted_lakes_fog_density", 0.015, 0, Double.MAX_VALUE);

            eroded_haunted_forest_fog = builder.comment("Enable fog in Eroded Haunted Forest biome").translation(TheGraveyard.MOD_ID + ".config.eroded_haunted_forest_fog").worldRestart().define("eroded_haunted_forest_fog", true);
            eroded_haunted_forest_fogMaxY = builder.comment("Fog upper limit").translation(TheGraveyard.MOD_ID + ":config:eroded_haunted_forest_fogMaxY").worldRestart().defineInRange("eroded_haunted_forest_fogMaxY", 256, 0, Integer.MAX_VALUE);
            eroded_haunted_forest_fogMinY = builder.comment("Fog lower limit").translation(TheGraveyard.MOD_ID + ":config:eroded_haunted_forest_fogMinY").worldRestart().defineInRange("eroded_haunted_forest_fogMinY", 62, 0, Integer.MAX_VALUE);
            eroded_haunted_forest_fog_density = builder.comment("Fog densitiy").translation(TheGraveyard.MOD_ID + ":config:eroded_haunted_forest_fog_density").worldRestart().defineInRange("eroded_haunted_forest_fog_density", 0.01, 0, Double.MAX_VALUE);


        }
    }

    public static class BiomeOptions {
        public boolean HAUNTED_FOREST, HAUNTED_LAKES, ERODED_HAUNTED_FOREST;
    }

    private static void bakeCommonConfig() {
        //BIOME_OPTIONS.HAUNTED_FOREST = COMMON_CONFIG.haunted_forest.get();
        //BIOME_OPTIONS.HAUNTED_LAKES = COMMON_CONFIG.haunted_lakes.get();
        //BIOME_OPTIONS.ERODED_HAUNTED_FOREST = COMMON_CONFIG.eroded_haunted_forest.get();

        INSTANCE.ENABLE_HAUNTED_FOREST_FOG = COMMON_CONFIG.haunted_forest_fog.get();
        INSTANCE.ENABLE_HAUNTED_LAKES_FOG = COMMON_CONFIG.haunted_lakes_fog.get();
        INSTANCE.ENABLE_ERODED_HAUNTED_FOREST_FOG = COMMON_CONFIG.eroded_haunted_forest_fog.get();
        INSTANCE.ENABLE_GRAVEYARD_FOG = COMMON_CONFIG.fog_particle.get();

        //INSTANCE.HAUNTED_FOREST_CHANCE = COMMON_CONFIG.haunted_forest_chance.get();
        //INSTANCE.HAUNTED_LAKES_CHANCE = COMMON_CONFIG.haunted_lakes_chance.get();
        //INSTANCE.ERODED_HAUNTED_FOREST_CHANCE = COMMON_CONFIG.eroded_haunted_forest_chance.get();

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

        INSTANCE.ENABLE_MUSHROOM_GRAVE = COMMON_CONFIG.mushroom_grave.get();
        INSTANCE.MUSHROOM_GRAVE_SEPARATION = COMMON_CONFIG.mushroom_grave_separation.get();
        INSTANCE.MUSHROOM_GRAVE_SPACING = COMMON_CONFIG.mushroom_grave_spacing.get();
        INSTANCE.MUSHROOM_GRAVE_SALT = COMMON_CONFIG.mushroom_grave_salt.get();

        INSTANCE.ENABLE_HAUNTED_HOUSE = COMMON_CONFIG.haunted_house.get();
        INSTANCE.HAUNTED_HOUSE_SEPARATION = COMMON_CONFIG.haunted_house_separation.get();
        INSTANCE.HAUNTED_HOUSE_SPACING = COMMON_CONFIG.haunted_house_spacing.get();
        INSTANCE.HAUNTED_HOUSE_SALT = COMMON_CONFIG.haunted_house_salt.get();

    }
}
