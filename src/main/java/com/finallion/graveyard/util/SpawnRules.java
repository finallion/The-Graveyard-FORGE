package com.finallion.graveyard.util;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.TGEntities;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SpawnRules {

    public record ModSpawnModifier(HolderSet<Biome> biomes,
                                   MobSpawnSettings.SpawnerData spawn) implements BiomeModifier {
        private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(new ResourceLocation(TheGraveyard.MOD_ID, "mobspawns"), ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, TheGraveyard.MOD_ID);

        @Override
        public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
            if (phase == Phase.ADD) {
                if (biome.is(Biomes.MUSHROOM_FIELDS)) {
                    return;
                }

                if (this.spawn.type == TGEntities.GHOUL.get() && GraveyardConfig.COMMON.enableGhoul.get() && biome.is(TGTags.GHOUL_SPAWNS)) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightGhoul.get(), GraveyardConfig.COMMON.minGroupSizeGhoul.get(), GraveyardConfig.COMMON.maxGroupSizeGhoul.get());
                    builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                } else if (this.spawn.type == TGEntities.REVENANT.get() && GraveyardConfig.COMMON.enableRevenant.get() && biome.is(TGTags.REVENANT_SPAWNS)) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightRevenant.get(), GraveyardConfig.COMMON.minGroupSizeRevenant.get(), GraveyardConfig.COMMON.maxGroupSizeRevenant.get());
                    builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                } else if (this.spawn.type == TGEntities.REAPER.get() && GraveyardConfig.COMMON.enableReaper.get() && biome.is(TGTags.REAPER_SPAWNS)) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightReaper.get(), GraveyardConfig.COMMON.minGroupSizeReaper.get(), GraveyardConfig.COMMON.maxGroupSizeReaper.get());
                    builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                } else if (this.spawn.type == TGEntities.NIGHTMARE.get() && GraveyardConfig.COMMON.enableNightmare.get() && biome.is(TGTags.NIGHTMARE_SPAWNS)) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightNightmare.get(), GraveyardConfig.COMMON.minGroupSizeNightmare.get(), GraveyardConfig.COMMON.maxGroupSizeNightmare.get());
                    builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);
                }  else if (this.spawn.type == TGEntities.SKELETON_CREEPER.get() && GraveyardConfig.COMMON.enableSkeletonCreeper.get() && biome.is(TGTags.SKELETON_CREEPER_SPAWNS)) {
                    MobSpawnSettings.SpawnerData data = new MobSpawnSettings.SpawnerData(this.spawn.type, GraveyardConfig.COMMON.weightSkeletonCreeper.get(), GraveyardConfig.COMMON.minGroupSizeSkeletonCreeper.get(), GraveyardConfig.COMMON.maxGroupSizeSkeletonCreeper.get());
                    builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), data);

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
}
