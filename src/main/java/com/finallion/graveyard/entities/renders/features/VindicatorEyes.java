package com.finallion.graveyard.entities.renders.features;


import com.finallion.graveyard.entities.CorruptedVindicator;
import com.finallion.graveyard.entities.models.CorruptedIllagerModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class VindicatorEyes extends RenderLayer<CorruptedVindicator, CorruptedIllagerModel<CorruptedVindicator>> {

    public VindicatorEyes(RenderLayerParent<CorruptedVindicator, CorruptedIllagerModel<CorruptedVindicator>> context) {
        super(context);
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumer, int p_117351_, CorruptedVindicator p_117352_, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
        VertexConsumer vertex = vertexConsumer.getBuffer(RenderType.eyes(new ResourceLocation("graveyard:textures/entity/corrupted_vindicator_eyes.png")));

        this.getParentModel().renderToBuffer(matrices, vertex, 15728640, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);

    }

}

