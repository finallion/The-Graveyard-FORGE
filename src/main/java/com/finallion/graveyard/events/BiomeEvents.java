package com.finallion.graveyard.events;

import com.finallion.graveyard.TheGraveyard;
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

        if (!TheGraveyard.CONFIG.haunted_lakes_fog.get() && !TheGraveyard.CONFIG.eroded_haunted_forest_fog.get() && !TheGraveyard.CONFIG.haunted_forest_fog.get()) {
            return;
        }


        if (biomeName.contains("graveyard:haunted_lakes") && TheGraveyard.CONFIG.haunted_lakes_fog.get()) {
            if (pos.getY() > TheGraveyard.CONFIG.haunted_lakes_fogMaxY.get() || pos.getY() < TheGraveyard.CONFIG.haunted_lakes_fogMinY.get()) {
                return;
            }
            fogDensity = TheGraveyard.CONFIG.haunted_lakes_fog_density.get();
        } else if (biomeName.contains("graveyard:eroded_haunted") && TheGraveyard.CONFIG.eroded_haunted_forest_fog.get()) {
            if (pos.getY() > TheGraveyard.CONFIG.eroded_haunted_forest_fogMaxY.get() || pos.getY() < TheGraveyard.CONFIG.eroded_haunted_forest_fogMinY.get()) {
                return;
            }
            fogDensity = TheGraveyard.CONFIG.eroded_haunted_forest_fog_density.get();
        } else if (biomeName.contains("graveyard:haunted_forest") && TheGraveyard.CONFIG.haunted_forest_fog.get()) {
            if (pos.getY() > TheGraveyard.CONFIG.haunted_forest_fogMaxY.get() || pos.getY() < TheGraveyard.CONFIG.haunted_forest_fogMinY.get()) {
                return;
            }
            fogDensity = TheGraveyard.CONFIG.haunted_forest_fog_density.get();
        } else {
            return;
        }

        event.setDensity((float) fogDensity);
        event.setCanceled(true);


    }

}
