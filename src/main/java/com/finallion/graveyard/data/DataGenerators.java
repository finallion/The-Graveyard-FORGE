package com.finallion.graveyard.data;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.init.TGEntities;
import com.finallion.graveyard.util.SpawnRules;
import com.finallion.graveyard.util.TGTags;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.nio.file.Path;

public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        //BiomeTagsProvider spawnTags = new SpawnTagProvider(generator, TheGraveyard.MOD_ID, event.getExistingFileHelper());
        //generator.addProvider(true, spawnTags);

        /*
        final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.BUILTIN.get());
        final String directory = PackType.SERVER_DATA.getDirectory();
        final ResourceLocation biomeModifiersRegistryID = ForgeRegistries.Keys.BIOME_MODIFIERS.location();
        final Path outputFolder = generator.getOutputFolder();
        final String biomeModifierPathString = String.join("/", directory, TheGraveyard.MOD_ID, biomeModifiersRegistryID.getNamespace(), biomeModifiersRegistryID.getPath(), "add_ghoul_spawns.json");
        final Path biomeModifierPath = outputFolder.resolve(biomeModifierPathString);
        final BiomeModifier biomeModifier = new SpawnRules.ModSpawnModifier(
                new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), TGTags.IS_OVERWORLD),
                new MobSpawnSettings.SpawnerData(TGEntities.GHOUL.get(), 100, 1, 4)
        );

        generator.addProvider(event.includeServer(), new DataProvider() {
            @Override
            public void run(final CachedOutput cache) throws IOException {

                BiomeModifier.DIRECT_CODEC.encodeStart(ops, biomeModifier)
                        .resultOrPartial(msg -> TheGraveyard.LOGGER.error("Failed to encode {}: {}", biomeModifierPathString, msg))
                        .ifPresent(json -> {
                            try {
                                DataProvider.saveStable(cache, json, biomeModifierPath);
                            } catch (IOException e) {
                                TheGraveyard.LOGGER.error("Failed to save " + biomeModifierPathString, e);
                            }
                        });
            }

            @Override
            public String getName()
            {
                return TheGraveyard.MOD_ID + " data provider";
            }
        });

         */
    }




}