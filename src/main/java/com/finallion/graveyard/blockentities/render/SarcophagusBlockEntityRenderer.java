package com.finallion.graveyard.blockentities.render;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blockentities.SarcophagusBlockEntity;
import com.finallion.graveyard.blockentities.enums.SarcophagusPart;
import com.finallion.graveyard.blocks.SarcophagusBlock;
import com.finallion.graveyard.init.TGTileEntities;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class SarcophagusBlockEntityRenderer<T extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<SarcophagusBlockEntity> {
    private final ModelManager modelManager;
    private final ModelBlockRenderer modelBlockRenderer;
    private BakedModel baseModel;
    private BakedModel lidModel;

    public SarcophagusBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        modelManager = Minecraft.getInstance().getModelManager();
        modelBlockRenderer = Minecraft.getInstance().getBlockRenderer().getModelRenderer();
    }

    @Override
    public void render(SarcophagusBlockEntity entity, float tickDelta, PoseStack matrixStack, MultiBufferSource vertexConsumers, int light, int overlay) {
        if (baseModel == null) {
            BlockState blockState = entity.getBlockState();

            baseModel = modelManager.getModel(new ResourceLocation(TheGraveyard.MOD_ID, "item/" + ((SarcophagusBlock)blockState.getBlock()).getBase()));
            lidModel = modelManager.getModel(new ResourceLocation(TheGraveyard.MOD_ID, "item/" + ((SarcophagusBlock)blockState.getBlock()).getLid()));
        }

        if (entity.getLevel() != null && entity.getBlockState().getValue(SarcophagusBlock.PART) == SarcophagusPart.HEAD) {
            renderLid(entity, matrixStack, vertexConsumers, light, overlay, tickDelta);
            renderBase(entity, matrixStack, vertexConsumers, light, overlay);
        }
    }
    // prevents model from clipping to invis when in the corner of the screen, needs to override getRenderBoundingBox in the blockEntity
    @Override
    public boolean shouldRenderOffScreen(SarcophagusBlockEntity p_112306_) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 45;
    }

    private void renderBase(SarcophagusBlockEntity entity, PoseStack matrixStack, MultiBufferSource vertexConsumer, int light, int overlay) {
        matrixStack.pushPose();

        Direction direction = entity.getBlockState().getValue(SarcophagusBlock.FACING).getOpposite();
        float f = direction.toYRot();
        matrixStack.mulPose(Axis.YP.rotationDegrees(-f));

        switch (direction) {
            case EAST -> matrixStack.translate(-1.0F, 0F, 1.0F);
            case SOUTH -> matrixStack.translate(0, 0F, 1.0F);
            case NORTH -> matrixStack.translate(-1.0F, 0F, 0F);
        }

        modelBlockRenderer.renderModel(matrixStack.last(), vertexConsumer.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), entity.getBlockState(), baseModel, 1.0F, 1.0F, 1.0F, light, overlay);

        matrixStack.popPose();
    }

    private void renderLid(SarcophagusBlockEntity entity, PoseStack matrixStack, MultiBufferSource vertexConsumer, int light, int overlay, float tickDelta) {
        matrixStack.pushPose();

        Direction direction = entity.getBlockState().getValue(SarcophagusBlock.FACING).getOpposite();
        float f = direction.toYRot();
        matrixStack.mulPose(Axis.YP.rotationDegrees(-f));

        switch (direction) {
            case EAST -> matrixStack.translate(-1.0F, 0F, 1.0F);
            case SOUTH -> matrixStack.translate(0, 0F, 1.0F);
            case NORTH -> matrixStack.translate(-1.0F, 0F, 0F);
        }

        DoubleBlockCombiner.NeighborCombineResult<? extends SarcophagusBlockEntity> propertySource = DoubleBlockCombiner.combineWithNeigbour(TGTileEntities.SARCOPHAGUS_BLOCK_ENTITY.get(), SarcophagusBlock::getBlockType, SarcophagusBlock::getConnectedDirection, ChestBlock.FACING, entity.getBlockState(), entity.getLevel(), entity.getBlockPos(), (worldx, pos) -> false);
        float g = 1.0F - propertySource.apply(SarcophagusBlock.opennessCombiner(entity)).get(tickDelta);
        g = 1.0F - g * g * g;

        matrixStack.translate(g * 0.3, g * 0.3, 0.0F); // lid offset to the ground and away from body
        matrixStack.mulPose(Axis.ZN.rotationDegrees(g * 45)); // lid rotation

        modelBlockRenderer.renderModel(matrixStack.last(), vertexConsumer.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), entity.getBlockState(), lidModel, 1.0F, 1.0F, 1.0F, light, overlay);

        matrixStack.popPose();
    }
}

