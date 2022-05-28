package com.finallion.graveyard.entities.renders;

import com.finallion.graveyard.entities.CorruptedIllager;
import com.finallion.graveyard.entities.models.CorruptedIllagerModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class CorruptedIllagerRenderer<T extends CorruptedIllager> extends MobRenderer<T, CorruptedIllagerModel<T>> {
    protected CorruptedIllagerRenderer(EntityRendererProvider.Context ctx, CorruptedIllagerModel<T> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
        this.addLayer(new CustomHeadLayer<>(this, ctx.getModelSet()));
    }

    protected void scale(T illagerEntity, PoseStack matrixStack, float f) {
        float g = 0.9375F;
        matrixStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}

