package com.finallion.graveyard.events;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.utils.ConfigConsts;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class BiomeEvents {
    private static double fogDensity;

    @SubscribeEvent
    public static void FogDensityEvent(EntityViewRenderEvent.FogDensity event) {
        BlockPos pos = event.getRenderer().getMainCamera().getBlockPosition();
        String biomeName = event.getInfo().getEntity().level.getBiome(pos).getRegistryName().toString();

        if (!ConfigConsts.enableFogLakes && !ConfigConsts.enableFogEroded && !ConfigConsts.enableFogForest) {
            return;
        }


        if (biomeName.contains("graveyard:haunted_lakes") && ConfigConsts.enableFogLakes) {
            if (pos.getY() > ConfigConsts.fogLakesMaxY || pos.getY() < ConfigConsts.fogLakesMinY) {
                return;
            }
            fogDensity = ConfigConsts.fogDensityLakes;
        } else if (biomeName.contains("graveyard:eroded_haunted") && ConfigConsts.enableFogEroded) {
            if (pos.getY() > ConfigConsts.fogErodedMaxY || pos.getY() < ConfigConsts.fogErodedMinY) {
                return;
            }
            fogDensity = ConfigConsts.fogDensityEroded;
        } else if (biomeName.contains("graveyard:haunted_forest") && ConfigConsts.enableFogForest) {
            if (pos.getY() > ConfigConsts.fogForestMaxY || pos.getY() < ConfigConsts.fogForestMinY) {
                return;
            }
            fogDensity = ConfigConsts.fogDensityForest;
        } else {
            return;
        }

        event.setDensity((float) fogDensity);
        event.setCanceled(true);


    }

}
