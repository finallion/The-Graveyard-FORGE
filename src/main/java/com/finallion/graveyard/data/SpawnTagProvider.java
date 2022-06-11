package com.finallion.graveyard.data;

import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.util.TGTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class SpawnTagProvider extends BiomeTagsProvider {

    public SpawnTagProvider(DataGenerator p_211094_) {
        super(p_211094_);
    }

    public SpawnTagProvider(DataGenerator p_211094_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_211094_, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        for (Map.Entry<ResourceKey<Biome>, Biome> biome : ForgeRegistries.BIOMES.getEntries()) {
            String biomeName = biome.getKey().location().toString();
            if (GraveyardConfig.COMMON.blacklistGhoul.get().contains(biomeName)) {
                this.tag(TGTags.GHOUL_SPAWN).add(biome.getKey());
            }
        }

    }
}
