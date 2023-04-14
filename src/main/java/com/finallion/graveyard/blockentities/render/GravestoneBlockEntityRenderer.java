package com.finallion.graveyard.blockentities.render;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blockentities.GravestoneBlockEntity;
import com.finallion.graveyard.blocks.GravestoneBlock;
import com.finallion.graveyard.init.TGBlocks;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WoodType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.List;


@OnlyIn(Dist.CLIENT)
public class GravestoneBlockEntityRenderer implements TileEntityRenderer<GravestoneBlockEntity> {
    private static final HashMap<Block, RenderType> LAYERS = Maps.newHashMap();
    private static RenderType defaultLayer;
    private final SignTileEntityRenderer.SignModel signModel = new SignTileEntityRenderer.SignModel();


    public GravestoneBlockEntityRenderer(TileEntityRendererDispatcher ctx) {
    }


    public void render(GravestoneBlockEntity signBlockEntity, float f, MatrixStack matrixStack, IRenderTypeBuffer vertexConsumerProvider, int p_112501_, int p_112502_) {
        BlockState blockState = signBlockEntity.getBlockState();
        matrixStack.pushPose();

        // text render location in world
        // offset on block
        matrixStack.translate(0.5D, 0.25D, 0.5D);

        float rotation = -(blockState.getValue(GravestoneBlock.FACING).toYRot());
        //float h = -((float)((Integer)blockState.getStructure(SignBlock.ROTATION) * 360) / 16.0F);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(rotation));
        matrixStack.pushPose();
        // size
        matrixStack.scale(0.6666667F, -0.6666667F, -0.6666667F);
        matrixStack.popPose();
        matrixStack.translate(0.0D, 0.3333333432674408D, 0.23);
        matrixStack.scale(0.010416667F, -0.010416667F, 0.010416667F);

        FontRenderer font = this.renderer.getFont();

        int i = signBlockEntity.getColor().getTextColor();
        double d0 = 0.4D;
        int j = (int)((double) NativeImage.getR(i) * 0.4D);
        int k = (int)((double)NativeImage.getG(i) * 0.4D);
        int l = (int)((double)NativeImage.getB(i) * 0.4D);
        int i1 = NativeImage.combine(0, l, k, j);
        int j1 = 20;

        for(int k1 = 0; k1 < 4; ++k1) {
            IReorderingProcessor ireorderingprocessor = signBlockEntity.getRenderMessage(k1, (p_243502_1_) -> {
                List<IReorderingProcessor> list = font.split(p_243502_1_, 90);
                return list.isEmpty() ? IReorderingProcessor.EMPTY : list.get(0);
            });
            if (ireorderingprocessor != null) {
                float f3 = (float)(-font.width(ireorderingprocessor) / 2);
                font.drawInBatch(ireorderingprocessor, f3, (float)(k1 * 10 - 20), i1, false, matrixStack.last().pose(), vertexConsumerProvider, false, 0, p_225616_5_);
            }
        }


        matrixStack.popPose();
        renderGrave(blockState, f, matrixStack, vertexConsumerProvider, p_112501_, p_112502_);

    }

    public void renderGrave(BlockState state, float f, MatrixStack matrixStack, IRenderTypeBuffer vertexConsumerProvider, int i, int j) {
        matrixStack.pushPose();
        matrixStack.translate(0.5, 0.43, 0.5);
        matrixStack.scale(2.28F, 2.15F, 2.28F);

        float rotation = -((float)state.getValue(GravestoneBlock.FACING).toYRot());
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(rotation));
        Minecraft.getInstance().getItemRenderer().renderStatic(new ItemStack(state.getBlock().asItem(), 1), ItemTransforms.TransformType.GROUND, i, j, matrixStack, vertexConsumerProvider, 2);



        matrixStack.popPose();
    }

    public static RenderMaterial getSignType(Block p_228877_0_) {
        WoodType woodtype;
        if (p_228877_0_ instanceof AbstractSignBlock) {
            woodtype = ((AbstractSignBlock)p_228877_0_).type();
        } else {
            woodtype = WoodType.OAK;
        }

        return Atlases.SIGN_MATERIALS.get(woodtype);
    }


    static {
        defaultLayer = RenderType.entitySolid(new ResourceLocation("textures/entity/signs/oak.png"));
        RenderType layer1 = RenderType.entitySolid(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/polished_basalt.png"));
        RenderType layer2 = RenderType.entitySolid(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/cobblestone.png"));
        RenderType layer3 = RenderType.entitySolid(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/mossy_cobblestone.png"));
        RenderType layer4 = RenderType.entitySolid(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/deepslate.png"));
        RenderType layer5 = RenderType.entitySolid(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/blackstone.png"));
        RenderType layer6 = RenderType.entitySolid(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/cracked_blackstone.png"));
        RenderType layer7 = RenderType.entitySolid(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/stone_bricks.png"));
        RenderType layer8 = RenderType.entitySolid(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/mossy_stone_bricks.png"));
        RenderType layer9 = RenderType.entitySolid(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/bricks.png"));
        LAYERS.put(TGBlocks.GRAVESTONE.get(), layer1);
        LAYERS.put(TGBlocks.COBBLESTONE_GRAVESTONE.get(), layer2);
        LAYERS.put(TGBlocks.MOSSY_COBBLESTONE_GRAVESTONE.get(), layer3);
        LAYERS.put(TGBlocks.DEEPSLATE_GRAVESTONE.get(), layer4);
        LAYERS.put(TGBlocks.BLACKSTONE_GRAVESTONE.get(), layer5);
        LAYERS.put(TGBlocks.CRACKED_BLACKSTONE_GRAVESTONE.get(), layer6);
        LAYERS.put(TGBlocks.STONE_BRICKS_GRAVESTONE.get(), layer7);
        LAYERS.put(TGBlocks.MOSSY_STONE_BRICKS_GRAVESTONE.get(), layer8);
        LAYERS.put(TGBlocks.BRICKS_GRAVESTONE.get(), layer9);
    }



    public static IVertexBuilder getConsumer(IRenderTypeBuffer provider, Block block) {
        return provider.getBuffer(LAYERS.getOrDefault(block, defaultLayer));
    }
}

