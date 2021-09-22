package com.finallion.graveyard.entites.renders.features;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class SkeletonCreeperEyes extends LayerRenderer<CreeperEntity, CreeperModel<CreeperEntity>> {

    public SkeletonCreeperEyes(IEntityRenderer<CreeperEntity, CreeperModel<CreeperEntity>> p_i50925_1_) {
        super(p_i50925_1_);
    }

    public void render(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, CreeperEntity p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        IVertexBuilder vertex = p_225628_2_.getBuffer(RenderType.eyes(new ResourceLocation("graveyard:textures/entity/skeleton_creeper_eyes.png")));

        this.getParentModel().renderToBuffer(p_225628_1_, vertex, 15728640, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
    }
}

