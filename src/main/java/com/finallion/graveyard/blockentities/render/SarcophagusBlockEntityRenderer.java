package com.finallion.graveyard.blockentities.render;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blockentities.SarcophagusBlockEntity;
import com.finallion.graveyard.blockentities.enums.SarcophagusPart;
import com.finallion.graveyard.blocks.SarcophagusBlock;
import com.finallion.graveyard.init.TGTileEntities;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
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

    public SarcophagusBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {}

    @Override
    public void render(SarcophagusBlockEntity entity, float tickDelta, PoseStack matrixStack, MultiBufferSource vertexConsumers, int light, int overlay) {
        BlockState blockState = entity.getBlockState();
        DoubleBlockCombiner.NeighborCombineResult<? extends SarcophagusBlockEntity> propertySource = DoubleBlockCombiner.combineWithNeigbour(TGTileEntities.SARCOPHAGUS_BLOCK_ENTITY.get(), SarcophagusBlock::getBlockType, SarcophagusBlock::getConnectedDirection, ChestBlock.FACING, blockState, entity.getLevel(), entity.getBlockPos(), (worldx, pos) -> {
            return false;
        });
        float g = propertySource.<Float2FloatFunction>apply(SarcophagusBlock.opennessCombiner(entity)).get(tickDelta);
        g = 1.0F - g;
        g = 1.0F - g * g * g;

        String base = ((SarcophagusBlock)blockState.getBlock()).getBase();
        String lid = ((SarcophagusBlock)blockState.getBlock()).getLid();
        BakedModel baseModel = getModel(base);
        BakedModel lidModel = getModel(lid);

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

    @Override
    public int getViewDistance() {
        return 45;
    }

    private void render(SarcophagusBlockEntity entity, PoseStack matrixStack, MultiBufferSource vertexConsumer, int light, int overlay, float g, BakedModel model, boolean isLid) {
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

    public static BakedModel getModel(String item) {
        return Minecraft.getInstance().getModelManager().getModel(new ResourceLocation(TheGraveyard.MOD_ID, "item/" + item));
    }
}

