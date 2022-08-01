package com.finallion.graveyard.blockentities.render;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blockentities.SarcophagusBlockEntity;
import com.finallion.graveyard.blockentities.enums.SarcophagusPart;
import com.finallion.graveyard.blocks.SarcophagusBlock;
import com.finallion.graveyard.init.TGTileEntities;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class SarcophagusBlockEntityRenderer<T extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<SarcophagusBlockEntity> {
    public static final ResourceLocation SARCOPHAGUS_FOOT = new ResourceLocation(TheGraveyard.MOD_ID, "block/sarcophagus_foot");
    public static final ResourceLocation SARCOPHAGUS_FOOT_LID = new ResourceLocation(TheGraveyard.MOD_ID, "block/sarcophagus_foot_lid");
    public static final ResourceLocation SARCOPHAGUS_HEAD_LID = new ResourceLocation(TheGraveyard.MOD_ID, "block/sarcophagus_head_lid");
    public static final ResourceLocation SARCOPHAGUS_HEAD = new ResourceLocation(TheGraveyard.MOD_ID, "block/sarcophagus_head");
    private final BakedModel sarcophagusModelHead;
    private final BakedModel sarcophagusModelFoot;
    private final BakedModel sarcophagusModelFootLid;
    private final BakedModel sarcophagusModelHeadLid;

    public SarcophagusBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        Minecraft client = Minecraft.getInstance();
        this.sarcophagusModelHead = client.getModelManager().getModel(SARCOPHAGUS_HEAD);
        this.sarcophagusModelFoot = client.getModelManager().getModel(SARCOPHAGUS_FOOT);
        this.sarcophagusModelFootLid = client.getModelManager().getModel(SARCOPHAGUS_FOOT_LID);
        this.sarcophagusModelHeadLid = client.getModelManager().getModel(SARCOPHAGUS_HEAD_LID);
    }


    @Override
    public void render(SarcophagusBlockEntity entity, float tickDelta, PoseStack matrixStack, MultiBufferSource vertexConsumers, int light, int overlay) {
        Level world = entity.getLevel();

        BlockState blockState = entity.getBlockState();
        String name = entity.getBlockState().getBlock().getDescriptionId();
        boolean isCoffin = entity.isCoffin();

        DoubleBlockCombiner.NeighborCombineResult<? extends SarcophagusBlockEntity> propertySource = DoubleBlockCombiner.combineWithNeigbour(TGTileEntities.SARCOPHAGUS_BLOCK_ENTITY.get(), SarcophagusBlock::getBlockType, SarcophagusBlock::getConnectedDirection, ChestBlock.FACING, blockState, world, entity.getBlockPos(), (worldx, pos) -> {
            return false;
        });
        float g = propertySource.<Float2FloatFunction>apply(SarcophagusBlock.opennessCombiner(entity)).get(tickDelta);
        g = 1.0F - g;
        g = 1.0F - g * g * g;
        int k = propertySource.<Int2IntFunction>apply(new BrightnessCombiner<>()).applyAsInt(light);

        BakedModel footLid = getModel(name, isCoffin, 0);
        BakedModel headLid = getModel(name, isCoffin, 1);
        BakedModel head = getModel(name, isCoffin, 2);
        BakedModel foot = getModel(name, isCoffin, 3);

        if (world != null) {
            if (isCoffin) {
                this.renderPart(entity, matrixStack, vertexConsumers, blockState.getValue(SarcophagusBlock.PART) == SarcophagusPart.HEAD ? head : foot, (Direction) blockState.getValue(SarcophagusBlock.FACING), vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, false);
                this.renderLid(entity, matrixStack, vertexConsumers, blockState.getValue(SarcophagusBlock.PART) == SarcophagusPart.HEAD ? headLid : footLid, Direction.SOUTH, vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, false, g);
            } else {
                this.renderPart(entity, matrixStack, vertexConsumers, blockState.getValue(SarcophagusBlock.PART) == SarcophagusPart.HEAD ? this.sarcophagusModelHead : this.sarcophagusModelFoot, (Direction) blockState.getValue(SarcophagusBlock.FACING), (VertexConsumer) vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, false);
                this.renderLid(entity, matrixStack, vertexConsumers, blockState.getValue(SarcophagusBlock.PART) == SarcophagusPart.HEAD ? this.sarcophagusModelHeadLid : this.sarcophagusModelFootLid, Direction.SOUTH, (VertexConsumer) vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, false, g);
            }
        } else {
            if (isCoffin) {
                this.renderLid(entity, matrixStack, vertexConsumers, footLid, Direction.SOUTH, vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, true, g);
                this.renderPart(entity, matrixStack, vertexConsumers, foot, Direction.SOUTH, vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, true);
                this.renderLid(entity, matrixStack, vertexConsumers, headLid, Direction.SOUTH, vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, false, g);
                this.renderPart(entity, matrixStack, vertexConsumers, head, Direction.SOUTH, vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, false);
            } else {
                this.renderLid(entity, matrixStack, vertexConsumers, sarcophagusModelFootLid, Direction.SOUTH, (VertexConsumer) vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, true, g);
                this.renderPart(entity, matrixStack, vertexConsumers, sarcophagusModelFoot, Direction.SOUTH, (VertexConsumer) vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, true);
                this.renderLid(entity, matrixStack, vertexConsumers, sarcophagusModelHeadLid, Direction.SOUTH, (VertexConsumer) vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, false, g);
                this.renderPart(entity, matrixStack, vertexConsumers, sarcophagusModelHead, Direction.SOUTH, (VertexConsumer) vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, false);
            }
        }
    }


    private void renderPart(SarcophagusBlockEntity entity, PoseStack matrices, MultiBufferSource vertexConsumers, BakedModel model, Direction direction, VertexConsumer vertexConsumer, int light, int overlay, boolean isFoot) {
        ModelBlockRenderer renderer = Minecraft.getInstance().getBlockRenderer().getModelRenderer();
        Level world = entity.getLevel();

        matrices.pushPose();
        matrices.translate(0.0D, 0.0D, isFoot ? -1.0D : 0.0D);

        float f = ((Direction) entity.getBlockState().getValue(SarcophagusBlock.FACING)).getOpposite().toYRot();
        matrices.translate(0.5D, 0.5D, 0.5D);
        matrices.mulPose(Vector3f.YP.rotationDegrees(-f));

        matrices.translate(-0.5D, -0.5D, -0.5D);

        renderer.renderModel(matrices.last(), vertexConsumer, entity.getBlockState(), model, 1.0F, 1.0F, 1.0F, light, overlay);
        matrices.popPose();
    }

    private void renderLid(SarcophagusBlockEntity entity, PoseStack matrices, MultiBufferSource vertexConsumers, BakedModel model, Direction direction, VertexConsumer vertexConsumer, int light, int overlay, boolean isFoot, float openFactor) {
        ModelBlockRenderer renderer = Minecraft.getInstance().getBlockRenderer().getModelRenderer();
        Level world = entity.getLevel();

        matrices.pushPose();
        matrices.translate(0.0D, 0.0D, isFoot ? -1.0D : 0.0D);

        float f = ((Direction) entity.getBlockState().getValue(SarcophagusBlock.FACING)).getOpposite().toYRot();
        matrices.translate(0.5D, 0.5D, 0.5D);
        matrices.mulPose(Vector3f.YP.rotationDegrees(-f));

        // ANIMATION START
        matrices.mulPose(Vector3f.ZN.rotationDegrees(openFactor * 70)); // lid rotation
        matrices.translate(isFoot ? -(openFactor * 0.25) : openFactor * 0.25, openFactor * 0.25, 0.0F); // lid offset to the ground and away from body
        // ANIMATION END

        matrices.translate(-0.5D, -0.5D, -0.5D);

        renderer.renderModel(matrices.last(), vertexConsumer, entity.getBlockState(), model, 1.0F, 1.0F, 1.0F, light, overlay);
        matrices.popPose();
    }

    private BakedModel getModel(String name, boolean isCoffin, int part) {
        Minecraft client = Minecraft.getInstance();

        if (isCoffin) {
            String woodType = name.split("\\.")[2];
            switch (part) {
                default -> {
                    return client.getModelManager().getModel(new ResourceLocation(TheGraveyard.MOD_ID, "block/" + woodType + "_head_lid"));
                }
                case 1 -> {
                    return client.getModelManager().getModel(new ResourceLocation(TheGraveyard.MOD_ID, "block/" + woodType + "_foot_lid"));
                }
                case 2 -> {
                    return client.getModelManager().getModel(new ResourceLocation(TheGraveyard.MOD_ID, "block/" + woodType + "_foot"));
                }
                case 3 -> {
                    return client.getModelManager().getModel(new ResourceLocation(TheGraveyard.MOD_ID, "block/" + woodType + "_head"));
                }
            }
        } else {
            return client.getModelManager().getModel(new ResourceLocation(TheGraveyard.MOD_ID, "block/oak_coffin_head"));
        }

    }
}

