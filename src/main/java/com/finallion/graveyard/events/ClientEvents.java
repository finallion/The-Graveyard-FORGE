package com.finallion.graveyard.events;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
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
    public static void FogDensityEvent(EntityViewRenderEvent.RenderFogEvent event) {
        BlockPos pos = event.getRenderer().getMainCamera().getBlockPosition();
        Entity entity = event.getCamera().getEntity();
        FogType cameraSubmersionType = event.getCamera().getFluidInCamera();
        String biomeName = entity.level.getBiomeName(pos).get().toString();


        if (!GraveyardConfig.COMMON.enableFogHauntedForest.get() &&
                !GraveyardConfig.COMMON.enableFogHauntedLakes.get() &&
                !GraveyardConfig.COMMON.enableFogErodedHauntedForest.get()) {
            return;
        }


        if (biomeName.contains("haunted_lakes") && GraveyardConfig.COMMON.enableFogHauntedLakes.get()) {
            if (pos.getY() > GraveyardConfig.COMMON.FogHauntedLakesMaxY.get()
                    || pos.getY() < GraveyardConfig.COMMON.FogHauntedLakesMinY.get()) {
                // fade fog out
                if (fogStart < 32.0F) {
                    fogStart *= 1.05F;
                } else {
                    fogStart = event.getFarPlaneDistance() / 2;
                    return;
                }
            }
            fogDensity = GraveyardConfig.COMMON.FogHauntedLakesDensity.get();
        } else if (biomeName.contains("eroded_haunted_forest") && GraveyardConfig.COMMON.enableFogErodedHauntedForest.get()) {
            if (pos.getY() > GraveyardConfig.COMMON.FogErodedHauntedForestMaxY.get()
                    || pos.getY() < GraveyardConfig.COMMON.FogErodedHauntedForestMinY.get()) {
                // fade fog out
                if (fogStart < 32.0F) {
                    fogStart *= 1.05F;
                } else {
                    fogStart = event.getFarPlaneDistance() / 2;
                    return;
                }
            }
            fogDensity = GraveyardConfig.COMMON.FogErodedHauntedForestDensity.get();
        } else if (biomeName.contains("haunted_forest") && GraveyardConfig.COMMON.enableFogHauntedForest.get()) {
            if (pos.getY() > GraveyardConfig.COMMON.FogHauntedForestMaxY.get()
                    || pos.getY() < GraveyardConfig.COMMON.FogHauntedForestMinY.get()) {
                // fade fog out
                if (fogStart < 32.0F) {
                    fogStart *= 1.05F;
                } else {
                    fogStart = event.getFarPlaneDistance() / 2;
                    return;
                }
            }
            fogDensity = GraveyardConfig.COMMON.FogHauntedForestDensity.get();
        } else {

            // fade fog out
            if (fogStart < 32.0F) {
                fogStart *= 1.05F;
            } else {
                fogStart = event.getFarPlaneDistance() / 2;
                return;
            }

        }


        if (cameraSubmersionType == FogType.NONE) {
            if (!(entity instanceof LivingEntity && ((LivingEntity) entity).hasEffect(MobEffects.BLINDNESS))) {

                float g = event.getRenderer().getRenderDistance();
                float viewDistance = Math.max(g - 16.0F, 32.0F);

                // fog fade in
                if (fogStart > fogDensity) {
                    fogStart *= 0.975F;
                }

                float start = viewDistance * 0.05F * fogStart;
                float end = Math.min(viewDistance, 192.0F) * 0.5F * fogStart;

                if (start > 64.0F) {
                    start = 64.0F;
                }

                if (end > event.getFarPlaneDistance()) {
                    end = event.getFarPlaneDistance();
                }

                RenderSystem.setShaderFogStart(start);
                RenderSystem.setShaderFogEnd(end);
            }
        }

    }


}