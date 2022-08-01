package com.finallion.graveyard.data;

/*
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        //BiomeTagsProvider spawnTags = new SpawnTagProvider(generator, TheGraveyard.MOD_ID, event.getExistingFileHelper());
        //generator.addProvider(true, spawnTags);


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

    }




}         */