package com.finallion.graveyard.events;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {
    // smaller values = denser fog
    private static double fogDensity;
    private static float fogStart = 32.0F;

    @SubscribeEvent
    public static void FogDensityEvent(EntityViewRenderEvent.FogDensity event) {
        BlockPos pos = event.getRenderer().getMainCamera().getBlockPosition();
        Entity entity = event.getCamera().getEntity();
        String biomeName = entity.level.getBiomeName(pos).get().getRegistryName().getPath();

        if (!GraveyardConfig.COMMON.enableFogHauntedForest.get() &&
                !GraveyardConfig.COMMON.enableFogHauntedLakes.get() &&
                !GraveyardConfig.COMMON.enableFogErodedHauntedForest.get()) {
            return;
        }

        if (entity instanceof LivingEntity && ((LivingEntity)entity).hasEffect(MobEffects.BLINDNESS)) {
            return;
        }


        if (biomeName.equals("haunted_lakes") && GraveyardConfig.COMMON.enableFogHauntedLakes.get()) {
            if (pos.getY() > GraveyardConfig.COMMON.FogHauntedLakesMaxY.get()
                    || pos.getY() < GraveyardConfig.COMMON.FogHauntedLakesMinY.get()) {
                // TODO: own method, no time, need sleep
                // fade fog out
                if (fogStart < 32.0F) {
                    fogStart *= 1.05F;

                } else {
                    fogStart = 32.0F;
                    return;
                }
            }
            fogDensity = GraveyardConfig.COMMON.FogHauntedLakesDensity.get();
        } else if (biomeName.equals("eroded_haunted_forest") && GraveyardConfig.COMMON.enableFogErodedHauntedForest.get()) {
            if (pos.getY() > GraveyardConfig.COMMON.FogErodedHauntedForestMaxY.get()
                    || pos.getY() < GraveyardConfig.COMMON.FogErodedHauntedForestMinY.get()) {
                // fade fog out
                if (fogStart < 32.0F) {
                    fogStart *= 1.05F;

                } else {
                    fogStart = 32.0F;
                    return;
                }
            }
            fogDensity = GraveyardConfig.COMMON.FogErodedHauntedForestDensity.get();
        } else if (biomeName.equals("haunted_forest") && GraveyardConfig.COMMON.enableFogHauntedForest.get()) {
            if (pos.getY() > GraveyardConfig.COMMON.FogHauntedForestMaxY.get()
                    || pos.getY() < GraveyardConfig.COMMON.FogHauntedForestMinY.get()) {
                // fade fog out
                if (fogStart < 32.0F) {
                    fogStart *= 1.05F;

                } else {
                    fogStart = 32.0F;
                    return;
                }
            }
            fogDensity = GraveyardConfig.COMMON.FogHauntedForestDensity.get();
        } else {

            // fade fog out
            if (fogStart < 32.0F) {
                fogStart *= 1.05F;

            } else {
                fogStart = 32.0F;
                return;
            }

        }

        event.setDensity((float) fogDensity);
        event.setCanceled(true);


    }

}