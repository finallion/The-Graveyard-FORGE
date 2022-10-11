package com.finallion.graveyard.util;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.TGEntities;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class SpawnRules {

    public record ModSpawnModifier(HolderSet<Biome> biomes,
                                   MobSpawnSettings.SpawnerData spawn) implements BiomeModifier {
        private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(new ResourceLocation(TheGraveyard.MOD_ID, "mobspawns"), ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, TheGraveyard.MOD_ID);

        @Override
        public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
            if (phase == Phase.ADD) {
                if (this.spawn.type == TGEntities.GHOUL.get() && GraveyardConfig.COMMON.enableGhoul.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightGhoul.get(), GraveyardConfig.COMMON.minGroupSizeGhoul.get(), GraveyardConfig.COMMON.maxGroupSizeGhoul.get());
                    if (parseBiomes(GraveyardConfig.COMMON.whitelistGhoul.get(), GraveyardConfig.COMMON.blacklistGhoul.get(), biome)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                } else if (this.spawn.type == TGEntities.REVENANT.get() && GraveyardConfig.COMMON.enableRevenant.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightRevenant.get(), GraveyardConfig.COMMON.minGroupSizeRevenant.get(), GraveyardConfig.COMMON.maxGroupSizeRevenant.get());
                    if (parseBiomes(GraveyardConfig.COMMON.whitelistRevenant.get(), GraveyardConfig.COMMON.blacklistRevenant.get(), biome)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                } else if (this.spawn.type == TGEntities.REAPER.get() && GraveyardConfig.COMMON.enableReaper.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightReaper.get(), GraveyardConfig.COMMON.minGroupSizeReaper.get(), GraveyardConfig.COMMON.maxGroupSizeReaper.get());
                    if (parseBiomes(GraveyardConfig.COMMON.whitelistReaper.get(), GraveyardConfig.COMMON.blacklistReaper.get(), biome)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                } else if (this.spawn.type == TGEntities.NIGHTMARE.get() && GraveyardConfig.COMMON.enableNightmare.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightNightmare.get(), GraveyardConfig.COMMON.minGroupSizeNightmare.get(), GraveyardConfig.COMMON.maxGroupSizeNightmare.get());
                    if (parseBiomes(GraveyardConfig.COMMON.whitelistNightmare.get(), GraveyardConfig.COMMON.blacklistNightmare.get(), biome)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                } else if (this.spawn.type == TGEntities.WRAITH.get() && GraveyardConfig.COMMON.enableWraith.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightWraith.get(), GraveyardConfig.COMMON.minGroupSizeWraith.get(), GraveyardConfig.COMMON.maxGroupSizeWraith.get());
                    if (parseBiomes(GraveyardConfig.COMMON.whitelistWraith.get(), GraveyardConfig.COMMON.blacklistWraith.get(), biome)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                } else if (this.spawn.type == TGEntities.SKELETON_CREEPER.get() && GraveyardConfig.COMMON.enableSkeletonCreeper.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightSkeletonCreeper.get(), GraveyardConfig.COMMON.minGroupSizeSkeletonCreeper.get(), GraveyardConfig.COMMON.maxGroupSizeSkeletonCreeper.get());
                    if (parseBiomes(GraveyardConfig.COMMON.whitelistSkeletonCreeper.get(), GraveyardConfig.COMMON.blacklistSkeletonCreeper.get(), biome)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                } else if (this.spawn.type == TGEntities.ACOLYTE.get() && GraveyardConfig.COMMON.enableAcolyte.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightAcolyte.get(), GraveyardConfig.COMMON.minGroupSizeAcolyte.get(), GraveyardConfig.COMMON.maxGroupSizeAcolyte.get());
                    if (parseBiomes(GraveyardConfig.COMMON.whitelistAcolyte.get(), GraveyardConfig.COMMON.blacklistAcolyte.get(), biome)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                } else if (this.spawn.type == TGEntities.CORRUPTED_VINDICATOR.get() && GraveyardConfig.COMMON.enableCorruptedVindicator.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightCorruptedVindicator.get(), GraveyardConfig.COMMON.minGroupSizeCorruptedVindicator.get(), GraveyardConfig.COMMON.maxGroupSizeCorruptedVindicator.get());
                    if (parseBiomes(GraveyardConfig.COMMON.whitelistCorruptedVindicator.get(), GraveyardConfig.COMMON.blacklistCorruptedVindicator.get(), biome)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                } else if (this.spawn.type == TGEntities.CORRUPTED_PILLAGER.get() && GraveyardConfig.COMMON.enableCorruptedPillager.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightCorruptedPillager.get(), GraveyardConfig.COMMON.minGroupSizeCorruptedPillager.get(), GraveyardConfig.COMMON.maxGroupSizeCorruptedPillager.get());
                    if (parseBiomes(GraveyardConfig.COMMON.whitelistCorruptedPillager.get(), GraveyardConfig.COMMON.blacklistCorruptedPillager.get(), biome)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                }
            }
        }

        @Override
        public Codec<? extends BiomeModifier> codec() {
            return SERIALIZER.get();
        }

        public static Codec<ModSpawnModifier> makeCodec() {
            return RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(SpawnRules.ModSpawnModifier::biomes),
                    MobSpawnSettings.SpawnerData.CODEC.fieldOf("spawn").forGetter(SpawnRules.ModSpawnModifier::spawn)
            ).apply(builder, ModSpawnModifier::new));
        }
    }

    public static boolean parseBiomes(List<? extends String> biomeWhitelist, List<? extends String> biomeBlacklist, Holder<Biome> biome) {
        if (biomeWhitelist == null || biomeBlacklist == null) {
            TheGraveyard.LOGGER.error("The Graveyard config file isn't up to date. Please delete the file in your .minecraft/config folder and restart the game to create a new config file. If the error keeps showing up, contact the mod developer via Github or Discord (links can be found here: https://www.curseforge.com/minecraft/mc-mods/the-graveyard-forge)!");
            return false;
        }

        String biomeName = biome.unwrapKey().orElseThrow().location().toString();

        if (biomeBlacklist.contains(biomeName)) {
            return false;
        }


        for (String biomeInList : biomeWhitelist) {
            if (biomeInList.startsWith("#")) { // check if biome is in tag
                String[] parts = biomeInList.substring(1).split(":");
                TagKey<Biome> tag = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(parts[0], parts[1]));
                // RegistryAccess causes massive delay on game startup. Since this loads only once and not on runtime, use BuiltinRegistries.
                //Registry<Biome> registry = RegistryAccess.builtinCopy().registryOrThrow(Registry.BIOME_REGISTRY);


                if (BuiltinRegistries.BIOME.isKnownTagName(tag)) {
                    if (BuiltinRegistries.BIOME.getTag(tag).orElseThrow().contains(biome)) {
                        return true;
                    }
                }



            } else if (biomeWhitelist.contains(biomeName)) { // check if biome is on whitelist
                return true;
            }
        }


        return false;
    }
}
