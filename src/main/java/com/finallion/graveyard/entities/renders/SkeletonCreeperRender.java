package com.finallion.graveyard.entities.renders;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.renders.features.SkeletonCreeperEyes;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class SkeletonCreeperRender extends MobRenderer<Creeper, CreeperModel<Creeper>> {
    private static final ResourceLocation CREEPER_LOCATION = new ResourceLocation(TheGraveyard.MOD_ID,"textures/entity/skeleton_creeper.png");

    public SkeletonCreeperRender(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new CreeperModel<>(p_174304_.bakeLayer(ModelLayers.CREEPER)), 0.5F);
        this.addLayer(new SkeletonCreeperEyes(this));
        this.addLayer(new CreeperPowerLayer(this, p_174304_.getModelSet()));
    }

    protected void scale(Creeper p_225620_1_, PoseStack p_225620_2_, float p_225620_3_) {
        float f = p_225620_1_.getSwelling(p_225620_3_);
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        p_225620_2_.scale(f2, f3, f2);
    }

    protected float getWhiteOverlayProgress(Creeper p_225625_1_, float p_225625_2_) {
        float f = p_225625_1_.getSwelling(p_225625_2_);
        return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
    }

    public ResourceLocation getTextureLocation(Creeper p_110775_1_) {
        return CREEPER_LOCATION;
    }


}
