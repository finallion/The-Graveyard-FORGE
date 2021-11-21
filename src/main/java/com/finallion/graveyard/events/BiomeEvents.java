package com.finallion.graveyard.events;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.init.TGBiomes;
import net.minecraft.block.SoundType;
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
        ResourceLocation location = event.getInfo().getEntity().level.getBiome(pos).getRegistryName();


        if (!TheGraveyard.CONFIG.haunted_lakes_fog.get() && !TheGraveyard.CONFIG.eroded_haunted_forest_fog.get() && !TheGraveyard.CONFIG.haunted_forest_fog.get()) {
            return;
        }

        if (location == null) {
            return;
        }


        if (location.equals(HAUNTED_LAKES_LOC) && TheGraveyard.CONFIG.haunted_lakes_fog.get()) {
            if (pos.getY() > TheGraveyard.CONFIG.haunted_lakes_fogMaxY.get() || pos.getY() < TheGraveyard.CONFIG.haunted_lakes_fogMinY.get()) {
                return;
            }
            fogDensity = TheGraveyard.CONFIG.haunted_lakes_fog_density.get();
        } else if (location.equals(ERODED_FOREST_LOC) && TheGraveyard.CONFIG.eroded_haunted_forest_fog.get()) {
            if (pos.getY() > TheGraveyard.CONFIG.eroded_haunted_forest_fogMaxY.get() || pos.getY() < TheGraveyard.CONFIG.eroded_haunted_forest_fogMinY.get()) {
                return;
            }
            fogDensity = TheGraveyard.CONFIG.eroded_haunted_forest_fog_density.get();
        } else if (location.equals(HAUNTED_FOREST_LOC) && TheGraveyard.CONFIG.haunted_forest_fog.get()) {
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
