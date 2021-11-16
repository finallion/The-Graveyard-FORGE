package com.finallion.graveyard.mixin;


import com.finallion.graveyard.utils.ConfigConsts;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import javafx.scene.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
@OnlyIn(Dist.CLIENT)
public abstract class WorldRendererMixin {
    // smaller values = denser fog
    private double fogDensity;

    @Shadow
    private ClientWorld level;

    @Shadow
    private Minecraft minecraft;

    @Inject(method = "renderLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/FogRenderer;setupFog(Lnet/minecraft/client/render/ActiveRenderInfo;Lnet/minecraft/client/render/FogRenderer;FZ)V", ordinal = 0, shift = At.Shift.AFTER))
    public void renderLevel(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, ActiveRenderInfo camera, GameRenderer gameRenderer, LightTexture lightmapTextureManager, Matrix4f matrix4f, CallbackInfo callback) {
        BlockPos pos = camera.getBlockPosition();
        String biomeName = this.minecraft.level.getBiomeName(pos).get().toString();

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

        float f = gameRenderer.getRenderDistance();
        Vector3d vector3d = camera.getPosition();
        double d0 = vector3d.x();
        double d1 = vector3d.y();
        boolean flag1 = this.minecraft.level.effects().isFoggyAt(MathHelper.floor(d0), MathHelper.floor(d1)) || this.minecraft.gui.getBossOverlay().shouldCreateWorldFog();
        applyCustomFog(camera, FogRenderer.FogType.FOG_TERRAIN, Math.max(f - 16.0F, 32.0F), flag1, fogDensity);

    }

    // copy of the BackgroundRenderer method applyFog with density parameter
    private static void applyCustomFog(ActiveRenderInfo camera, FogRenderer.FogType fogType, float viewDistance, boolean thickFog, double density) {
        FluidState fluidstate = camera.getFluidInCamera();
        Entity entity = camera.getEntity();

        if (!(fluidstate.is(FluidTags.WATER) || fluidstate.is(FluidTags.LAVA))) {
            if (!(entity instanceof LivingEntity && ((LivingEntity) entity).hasEffect(Effects.BLINDNESS))) {
                float o = viewDistance * 0.05F * (float) density;
                float r = Math.min(viewDistance, 192.0F) * 0.5F * (float) density;
                RenderSystem.fogStart(o);
                RenderSystem.fogEnd(r);
                RenderSystem.fogMode(GlStateManager.FogMode.LINEAR);
                RenderSystem.setupNvFogDistance();
                //RenderSystem.setShaderFogStart(o);
                //RenderSystem.setShaderFogEnd(r);
            }
        }
    }
}
