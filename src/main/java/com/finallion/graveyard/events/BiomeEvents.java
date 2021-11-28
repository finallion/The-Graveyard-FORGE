package com.finallion.graveyard.events;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.TGBiomes;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class BiomeEvents {
    private static double fogDensity;
    private static final ResourceLocation HAUNTED_FOREST_LOC = TGBiomes.HAUNTED_FOREST.getRegistryName();
    private static final ResourceLocation HAUNTED_LAKES_LOC = TGBiomes.HAUNTED_LAKES.getRegistryName();
    private static final ResourceLocation ERODED_FOREST_LOC = TGBiomes.ERODED_HAUNTED_FOREST.getRegistryName();

    @SubscribeEvent
    public static void FogDensityEvent(EntityViewRenderEvent.FogDensity event) {
        BlockPos pos = event.getRenderer().getMainCamera().getBlockPosition();
        Entity entity = event.getInfo().getEntity();
        ResourceLocation location = entity.level.getBiome(pos).getRegistryName();
        int additionalFog = 0;

        if (!GraveyardConfig.INSTANCE.ENABLE_HAUNTED_FOREST_FOG && !GraveyardConfig.INSTANCE.ENABLE_HAUNTED_LAKES_FOG && !GraveyardConfig.INSTANCE.ENABLE_ERODED_HAUNTED_FOREST_FOG) {
            return;
        }

        if (location == null) {
            return;
        }


        if (entity instanceof LivingEntity && ((LivingEntity)entity).hasEffect(Effects.BLINDNESS)) {
            return;
        }


        if (location.equals(HAUNTED_FOREST_LOC) && GraveyardConfig.INSTANCE.ENABLE_HAUNTED_FOREST_FOG) {
            if (pos.getY() > GraveyardConfig.INSTANCE.HAUNTED_FOREST_FOG_MAXY || pos.getY() < GraveyardConfig.INSTANCE.HAUNTED_FOREST_FOG_MINY) {
                return;
            }
            fogDensity = GraveyardConfig.INSTANCE.HAUNTED_FOREST_FOG_DENSITY;
        } else if (location.equals(HAUNTED_LAKES_LOC) && GraveyardConfig.INSTANCE.ENABLE_HAUNTED_LAKES_FOG) {
            if (pos.getY() > GraveyardConfig.INSTANCE.HAUNTED_LAKES_FOG_MAXY || pos.getY() < GraveyardConfig.INSTANCE.HAUNTED_LAKES_FOG_MINY) {
                return;
            }
            fogDensity = GraveyardConfig.INSTANCE.HAUNTED_LAKES_FOG_DENSITY;
        } else if (location.equals(ERODED_FOREST_LOC) && GraveyardConfig.INSTANCE.ENABLE_ERODED_HAUNTED_FOREST_FOG) {
            if (pos.getY() > GraveyardConfig.INSTANCE.ERODED_HAUNTED_FOREST_FOG_MAXY || pos.getY() < GraveyardConfig.INSTANCE.ERODED_HAUNTED_FOREST_FOG_MINY) {
                return;
            }
            fogDensity = GraveyardConfig.INSTANCE.ERODED_HAUNTED_FOREST_FOG_DENSITY;
        } else {
            return;
        }

        event.setDensity((float) fogDensity);
        event.setCanceled(true);


    }

}
