package com.finallion.graveyard.entities.renders;

import com.finallion.graveyard.entities.GhoulingEntity;
import com.finallion.graveyard.entities.models.GhoulingModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GhoulingRenderer extends GeoEntityRenderer<GhoulingEntity> {
    private GhoulingEntity ghouling;

    public GhoulingRenderer(EntityRendererProvider.Context context) {
        super(context, new GhoulingModel());
        this.shadowRadius = 0.6F;
    }

    @Override
    public RenderType getRenderType(GhoulingEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        ghouling = animatable;
        return RenderType.entityCutoutNoCull(texture);
    }

    // stops the vanilla death animation
    @Override
    protected float getDeathMaxRotation(GhoulingEntity entityLivingBaseIn) {
        return 0.0F;
    }

    @Override
    public void renderRecursively(PoseStack stack, GhoulingEntity animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("chain") && ghouling != null) {
            bone.setHidden(!ghouling.hasCoffin());
        }

        if (bone.getName().equals("torso") && ghouling != null && ghouling.hasCoffin()) {
            stack.pushPose();
            stack.mulPose(Axis.XP.rotationDegrees(90));
            stack.mulPose(Axis.YP.rotationDegrees(90));
            stack.mulPose(Axis.ZP.rotationDegrees(-25));
            //stack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(bone.getRotationZ()));
            stack.translate(1.3D, 0.86D, 0.0D);
            stack.scale(2.0F, 2.0F, 2.0F);
            Minecraft.getInstance().getItemRenderer().renderStatic(ghouling.getItemBySlot(EquipmentSlot.OFFHAND), ItemDisplayContext.THIRD_PERSON_LEFT_HAND, packedLight, packedOverlay, stack, bufferSource, animatable.level(), 0);
            stack.popPose();
            buffer = bufferSource.getBuffer(RenderType.entityTranslucent(getTextureLocation(animatable)));
        }

        super.renderRecursively(stack, animatable, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }


}
