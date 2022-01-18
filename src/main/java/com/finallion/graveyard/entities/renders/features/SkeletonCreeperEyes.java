package com.finallion.graveyard.entities.renders.features;



import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class SkeletonCreeperEyes extends RenderLayer<Creeper, CreeperModel<Creeper>> {

    public SkeletonCreeperEyes(RenderLayerParent<Creeper, CreeperModel<Creeper>> p_117346_) {
        super(p_117346_);
    }

    public void render(PoseStack p_225628_1_, MultiBufferSource p_225628_2_, int p_225628_3_, Creeper p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        VertexConsumer vertex = p_225628_2_.getBuffer(RenderType.eyes(new ResourceLocation("graveyard:textures/entity/skeleton_creeper_eyes.png")));

        this.getParentModel().renderToBuffer(p_225628_1_, vertex, 15728640, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
    }

}

