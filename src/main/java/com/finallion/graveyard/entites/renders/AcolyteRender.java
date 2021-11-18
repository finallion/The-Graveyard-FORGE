package com.finallion.graveyard.entites.renders;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entites.AcolyteEntity;
import com.finallion.graveyard.entites.renders.features.AcolyteEyes;
import com.finallion.graveyard.entites.renders.features.ReaperEyesFeatureRenderer;
import com.finallion.graveyard.entites.renders.features.SkeletonCreeperEyes;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CreeperChargeLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.client.renderer.entity.model.IllagerModel;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AcolyteRender extends IllagerRenderer<AcolyteEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(TheGraveyard.MOD_ID,"textures/entity/acolyte.png");

    public AcolyteRender(EntityRendererManager context) {
        super(context, new IllagerModel<>(0.0F, 0.0F, 64, 64), 0.5F);
        this.model.getHat().visible = true;
        this.addLayer(new AcolyteEyes(this));
        this.addLayer(new HeldItemLayer<AcolyteEntity, IllagerModel<AcolyteEntity>>(this) {
            public void render(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, AcolyteEntity p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
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
