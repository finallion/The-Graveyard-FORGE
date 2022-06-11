package com.finallion.graveyard.util;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.TGEntities;
import com.mojang.realmsclient.util.JsonUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SpawnRules {

    public record ModSpawnModifier(HolderSet<Biome> biomes, MobSpawnSettings.SpawnerData spawn) implements BiomeModifier {
        private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(new ResourceLocation(TheGraveyard.MOD_ID, "mobspawns"), ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, TheGraveyard.MOD_ID);

        @Override
        public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
            if (phase == Phase.ADD && this.biomes.contains(biome)) {
                String modId = biome.unwrapKey().orElseThrow().location().getNamespace();
                String biomeName = biome.unwrapKey().orElseThrow().location().toString();

                if (this.spawn.type == TGEntities.GHOUL.get() && GraveyardConfig.COMMON.enableGhoul.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightGhoul.get(), GraveyardConfig.COMMON.minGroupSizeGhoul.get(), GraveyardConfig.COMMON.maxGroupSizeGhoul.get());
                    if (GraveyardConfig.COMMON.modWhitelistGhoul.get().contains("#" + modId) && !GraveyardConfig.COMMON.blacklistGhoul.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    } else if (GraveyardConfig.COMMON.whitelistGhoul.get().contains(biomeName) && !GraveyardConfig.COMMON.blacklistGhoul.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                } else if (this.spawn.type == TGEntities.REVENANT.get() && GraveyardConfig.COMMON.enableRevenant.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightRevenant.get(), GraveyardConfig.COMMON.minGroupSizeRevenant.get(), GraveyardConfig.COMMON.maxGroupSizeRevenant.get());
                    if (GraveyardConfig.COMMON.modWhitelistRevenant.get().contains("#" + modId) && !GraveyardConfig.COMMON.blacklistRevenant.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    } else if (GraveyardConfig.COMMON.whitelistRevenant.get().contains(biomeName) && !GraveyardConfig.COMMON.blacklistRevenant.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                } else if (this.spawn.type == TGEntities.REAPER.get() && GraveyardConfig.COMMON.enableReaper.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightReaper.get(), GraveyardConfig.COMMON.minGroupSizeReaper.get(), GraveyardConfig.COMMON.maxGroupSizeReaper.get());
                    if (GraveyardConfig.COMMON.modWhitelistReaper.get().contains("#" + modId) && !GraveyardConfig.COMMON.blacklistReaper.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    } else if (GraveyardConfig.COMMON.whitelistReaper.get().contains(biomeName) && !GraveyardConfig.COMMON.blacklistReaper.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                } else if (this.spawn.type == TGEntities.NIGHTMARE.get() && GraveyardConfig.COMMON.enableNightmare.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightNightmare.get(), GraveyardConfig.COMMON.minGroupSizeNightmare.get(), GraveyardConfig.COMMON.maxGroupSizeNightmare.get());
                    if (GraveyardConfig.COMMON.modWhitelistNightmare.get().contains("#" + modId) && !GraveyardConfig.COMMON.blacklistNightmare.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    } else if (GraveyardConfig.COMMON.whitelistNightmare.get().contains(biomeName) && !GraveyardConfig.COMMON.blacklistNightmare.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                } else if (this.spawn.type == TGEntities.WRAITH.get() && GraveyardConfig.COMMON.enableWraith.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightWraith.get(), GraveyardConfig.COMMON.minGroupSizeWraith.get(), GraveyardConfig.COMMON.maxGroupSizeWraith.get());
                    if (GraveyardConfig.COMMON.modWhitelistWraith.get().contains("#" + modId) && !GraveyardConfig.COMMON.blacklistWraith.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    } else if (GraveyardConfig.COMMON.whitelistWraith.get().contains(biomeName) && !GraveyardConfig.COMMON.blacklistWraith.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                } else if (this.spawn.type == TGEntities.SKELETON_CREEPER.get() && GraveyardConfig.COMMON.enableSkeletonCreeper.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightSkeletonCreeper.get(), GraveyardConfig.COMMON.minGroupSizeSkeletonCreeper.get(), GraveyardConfig.COMMON.maxGroupSizeSkeletonCreeper.get());
                    if (GraveyardConfig.COMMON.modWhitelistSkeletonCreeper.get().contains("#" + modId) && !GraveyardConfig.COMMON.blacklistSkeletonCreeper.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    } else if (GraveyardConfig.COMMON.whitelistSkeletonCreeper.get().contains(biomeName) && !GraveyardConfig.COMMON.blacklistSkeletonCreeper.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                } else if (this.spawn.type == TGEntities.ACOLYTE.get() && GraveyardConfig.COMMON.enableAcolyte.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightAcolyte.get(), GraveyardConfig.COMMON.minGroupSizeAcolyte.get(), GraveyardConfig.COMMON.maxGroupSizeAcolyte.get());
                    if (GraveyardConfig.COMMON.modWhitelistAcolyte.get().contains("#" + modId) && !GraveyardConfig.COMMON.blacklistAcolyte.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    } else if (GraveyardConfig.COMMON.whitelistAcolyte.get().contains(biomeName) && !GraveyardConfig.COMMON.blacklistAcolyte.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                } else if (this.spawn.type == TGEntities.CORRUPTED_VINDICATOR.get() && GraveyardConfig.COMMON.enableCorruptedVindicator.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightCorruptedVindicator.get(), GraveyardConfig.COMMON.minGroupSizeCorruptedVindicator.get(), GraveyardConfig.COMMON.maxGroupSizeCorruptedVindicator.get());
                    if (GraveyardConfig.COMMON.modWhitelistCorruptedVindicator.get().contains("#" + modId) && !GraveyardConfig.COMMON.blacklistCorruptedVindicator.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    } else if (GraveyardConfig.COMMON.whitelistCorruptedVindicator.get().contains(biomeName) && !GraveyardConfig.COMMON.blacklistCorruptedVindicator.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                } else if (this.spawn.type == TGEntities.CORRUPTED_PILLAGER.get() && GraveyardConfig.COMMON.enableCorruptedPillager.get()) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightCorruptedPillager.get(), GraveyardConfig.COMMON.minGroupSizeCorruptedPillager.get(), GraveyardConfig.COMMON.maxGroupSizeCorruptedPillager.get());
                    if (GraveyardConfig.COMMON.modWhitelistCorruptedPillager.get().contains("#" + modId) && !GraveyardConfig.COMMON.blacklistCorruptedPillager.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    } else if (GraveyardConfig.COMMON.whitelistCorruptedPillager.get().contains(biomeName) && !GraveyardConfig.COMMON.blacklistCorruptedPillager.get().contains(biomeName)) {
                        builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                    }
                }
            }
        }

        @Override
        public Codec<? extends BiomeModifier> codec() {
            return SERIALIZER.get();
        }


        //TODO: remove unnecessary spawner data
        public static Codec<ModSpawnModifier> makeCodec() {
            return RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(SpawnRules.ModSpawnModifier::biomes),
                    MobSpawnSettings.SpawnerData.CODEC.fieldOf("spawn").forGetter(SpawnRules.ModSpawnModifier::spawn)
            ).apply(builder, ModSpawnModifier::new));
        }
    }
}
