package com.finallion.graveyard.entities.renders.features;


import com.finallion.graveyard.entities.CorruptedPillager;
import com.finallion.graveyard.entities.models.CorruptedIllagerModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;


public class PillagerEyes extends RenderLayer<CorruptedPillager, CorruptedIllagerModel<CorruptedPillager>> {

    public PillagerEyes(RenderLayerParent<CorruptedPillager, CorruptedIllagerModel<CorruptedPillager>> context) {
        super(context);
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumer, int p_117351_, CorruptedPillager p_117352_, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
        VertexConsumer vertex = vertexConsumer.getBuffer(RenderType.eyes(new ResourceLocation("graveyard:textures/entity/corrupted_illager_eyes.png")));

        this.getParentModel().renderToBuffer(matrices, vertex, 15728640, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);

    }

}

