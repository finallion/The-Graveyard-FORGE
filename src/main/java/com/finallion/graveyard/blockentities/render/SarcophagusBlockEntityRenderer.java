package com.finallion.graveyard.blockentities.render;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blockentities.SarcophagusBlockEntity;
import com.finallion.graveyard.blockentities.enums.SarcophagusPart;
import com.finallion.graveyard.blocks.SarcophagusBlock;
import com.finallion.graveyard.init.TGTileEntities;
import com.mojang.blaze3d.matrix.MatrixStack;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class SarcophagusBlockEntityRenderer<T extends TileEntity & IChestLid> extends TileEntityRenderer<SarcophagusBlockEntity> {

    public SarcophagusBlockEntityRenderer(TileEntityRendererDispatcher ctx) {
        super(ctx);
    }

    @Override
    public void render(SarcophagusBlockEntity entity, float tickDelta, MatrixStack matrixStack, IRenderTypeBuffer vertexConsumers, int light, int overlay) {
        BlockState blockState = entity.getBlockState();

        TileEntityMerger.ICallbackWrapper<? extends SarcophagusBlockEntity> icallbackwrapper = TileEntityMerger.ICallback::acceptNone;
        float g = icallbackwrapper.<Float2FloatFunction>apply(SarcophagusBlock.opennessCombiner(entity)).get(tickDelta);

        g = 1.0F - g;
        g = 1.0F - g * g * g;

        String base = ((SarcophagusBlock)blockState.getBlock()).getBase();
        String lid = ((SarcophagusBlock)blockState.getBlock()).getLid();
        IBakedModel baseModel = getModel(base);
        IBakedModel lidModel = getModel(lid);

        if (entity.getLevel() != null && entity.getBlockState().getValue(SarcophagusBlock.PART) == SarcophagusPart.HEAD) {
            render(entity, matrixStack, vertexConsumers, light, overlay, g, lidModel, true);
            render(entity, matrixStack, vertexConsumers, light, overlay, g, baseModel, false);
        }
    }
    // prevents model from clipping to invis when in the corner of the screen, needs to override getRenderBoundingBox in the blockEntity
    @Override
    public boolean shouldRenderOffScreen(SarcophagusBlockEntity p_112306_) {
        return true;
    }

    private void render(SarcophagusBlockEntity entity, MatrixStack matrixStack, IRenderTypeBuffer vertexConsumer, int light, int overlay, float g, IBakedModel model, boolean isLid) {
        matrixStack.pushPose();

        Direction direction = entity.getBlockState().getValue(SarcophagusBlock.FACING).getOpposite();
        float f = direction.toYRot();
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(-f));

        switch (direction) {
            case EAST -> matrixStack.translate(-1.0F, 0F, 1.0F);
            case SOUTH -> matrixStack.translate(0, 0F, 1.0F);
            case NORTH -> matrixStack.translate(-1.0F, 0F, 0F);
        }

        if (isLid) {
            matrixStack.translate(g * 0.3, g * 0.3, 0.0F); // lid offset to the ground and away from body
            matrixStack.mulPose(Vector3f.ZN.rotationDegrees(g * 45)); // lid rotation
        }

        Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(matrixStack.last(), vertexConsumer.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), entity.getBlockState(), model, 1.0F, 1.0F, 1.0F, light, overlay);

        matrixStack.popPose();
    }

    public static IBakedModel getModel(String item) {
        return Minecraft.getInstance().getModelManager().getModel(new ResourceLocation(TheGraveyard.MOD_ID, "item/" + item));
    }
}

