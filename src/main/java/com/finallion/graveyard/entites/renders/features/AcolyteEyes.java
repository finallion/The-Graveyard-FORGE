package com.finallion.graveyard.entites.renders.features;


import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entites.AcolyteEntity;
import com.finallion.graveyard.entites.ReaperEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.IllagerModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AcolyteEyes extends LayerRenderer<AcolyteEntity, IllagerModel<AcolyteEntity>> {


    public AcolyteEyes(IEntityRenderer<AcolyteEntity, IllagerModel<AcolyteEntity>> p_i50926_1_) {
        super(p_i50926_1_);
    }

    @Override
    public void render(MatrixStack matrices, IRenderTypeBuffer vertexConsumer, int p_225628_3_, AcolyteEntity p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        IVertexBuilder vertex = vertexConsumer.getBuffer(RenderType.eyes(new ResourceLocation("graveyard:textures/entity/acolyte_eyes.png")));

        this.getParentModel().renderToBuffer(matrices, vertex, 15728640, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
    }
}


