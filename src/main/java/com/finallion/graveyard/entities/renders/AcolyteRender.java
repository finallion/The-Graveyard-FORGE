package com.finallion.graveyard.entities.renders;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.client.TheGraveyardClient;
import com.finallion.graveyard.entities.AcolyteEntity;
import com.finallion.graveyard.entities.models.CorruptedIllagerModel;
import com.finallion.graveyard.entities.renders.features.AcolyteEyes;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class AcolyteRender extends CorruptedIllagerRenderer<AcolyteEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(TheGraveyard.MOD_ID,"textures/entity/acolyte.png");

    public AcolyteRender(EntityRendererProvider.Context context) {
        super(context, new CorruptedIllagerModel<>(context.bakeLayer(TheGraveyardClient.CORRUPTED_ILLAGER_MODEL_LAYER)), 0.5F);
        this.model.getHat().visible = true;
        this.addLayer(new AcolyteEyes(this));
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()) {
            public void render(PoseStack p_225628_1_, MultiBufferSource p_225628_2_, int p_225628_3_, AcolyteEntity p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
                if (p_225628_4_.isAggressive()) {
                    super.render(p_225628_1_, p_225628_2_, p_225628_3_, p_225628_4_, p_225628_5_, p_225628_6_, p_225628_7_, p_225628_8_, p_225628_9_, p_225628_10_);
                }

            }
        });
    }

    @Override
    public ResourceLocation getTextureLocation(AcolyteEntity p_110775_1_) {
        return TEXTURE;
    }

}
