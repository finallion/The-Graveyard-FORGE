package com.finallion.graveyard.entites.renders.features;


import com.finallion.graveyard.entites.AcolyteEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AcolyteEyes extends RenderLayer<AcolyteEntity, IllagerModel<AcolyteEntity>> {

    public AcolyteEyes(RenderLayerParent<AcolyteEntity, IllagerModel<AcolyteEntity>> p_117346_) {
        super(p_117346_);
    }



    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumer, int p_117351_, AcolyteEntity p_117352_, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
        VertexConsumer vertex = vertexConsumer.getBuffer(RenderType.eyes(new ResourceLocation("graveyard:textures/entity/acolyte_eyes.png")));

        this.getParentModel().renderToBuffer(matrices, vertex, 15728640, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);

    }
}


