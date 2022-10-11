package com.finallion.graveyard.entities.renders;

import com.finallion.graveyard.entities.GhoulingEntity;
import com.finallion.graveyard.entities.models.GhoulingModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GhoulingRenderer extends GeoEntityRenderer<GhoulingEntity> {
    private GhoulingEntity ghouling;

    public GhoulingRenderer(EntityRendererProvider.Context context) {
        super(context, new GhoulingModel());
        this.shadowRadius = 0.6F;
    }


    @Override
    public RenderType getRenderType(GhoulingEntity animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        ghouling = animatable;
        return RenderType.entityCutoutNoCull(textureLocation);
    }

    // stops the vanilla death animation
    @Override
    protected float getDeathMaxRotation(GhoulingEntity entityLivingBaseIn) {
        return 0.0F;
    }

    @Override
    public void renderRecursively(GeoBone bone, PoseStack stack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("chain") && ghouling != null) {
            bone.setHidden(!ghouling.hasCoffin());
        }

        if (bone.getName().equals("torso") && ghouling != null && ghouling.hasCoffin()) {
            stack.pushPose();
            stack.mulPose(Vector3f.XP.rotationDegrees(90));
            stack.mulPose(Vector3f.YP.rotationDegrees(90));
            stack.mulPose(Vector3f.ZP.rotationDegrees(-25));
            //stack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(bone.getRotationZ()));
            stack.translate(1.3D, 0.86D, 0.0D);
            stack.scale(2.0F, 2.0F, 2.0F);
            Minecraft.getInstance().getItemRenderer().renderStatic(offHand, ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND, packedLightIn, packedOverlayIn, stack, this.rtb, 0);
            stack.popPose();
            bufferIn = rtb.getBuffer(RenderType.entityTranslucent(whTexture));
        }
        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

}
