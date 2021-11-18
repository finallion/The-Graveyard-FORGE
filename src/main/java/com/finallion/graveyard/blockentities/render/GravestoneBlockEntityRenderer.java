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
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.ItemLayerModel;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.registries.RegistryManager;

import java.util.HashMap;
import java.util.List;


@OnlyIn(Dist.CLIENT)
public class GravestoneBlockEntityRenderer extends TileEntityRenderer<GravestoneBlockEntity> {
    private static final HashMap<Block, RenderType> LAYERS = Maps.newHashMap();
    private static RenderType defaultLayer;
    private static ItemStack stack = new ItemStack(TGBlocks.GRAVESTONE.asItem(), 1);

    public GravestoneBlockEntityRenderer(TileEntityRendererDispatcher blockEntityRenderDispatcher) {
        super(blockEntityRenderDispatcher);
    }


    public void render(GravestoneBlockEntity signBlockEntity, float f, MatrixStack matrixStack, IRenderTypeBuffer vertexConsumerProvider, int i, int j) {
        BlockState blockState = signBlockEntity.getBlockState();
        matrixStack.pushPose();


        // text render location in world
        // offset on block
        matrixStack.translate(0.5D, 0.25D, 0.5D);

        float rotation = -(blockState.getValue(GravestoneBlock.FACING).toYRot());
        //float h = -((float)((Integer)blockState.get(SignBlock.ROTATION) * 360) / 16.0F);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(rotation));
        matrixStack.pushPose();
        // size
        matrixStack.scale(0.6666667F, -0.6666667F, -0.6666667F);
        matrixStack.popPose();
        FontRenderer fontrenderer = this.renderer.getFont();
        matrixStack.translate(0.0D, 0.3333333432674408D, 0.23);
        matrixStack.scale(0.010416667F, -0.010416667F, 0.010416667F);

        int m = signBlockEntity.getColor().getTextColor();
        int n = (int)((double) NativeImage.getR(m) * 0.4D);
        int k = (int)((double)NativeImage.getG(m) * 0.4D);
        int l = (int)((double)NativeImage.getB(m) * 0.4D);
        int i1 = NativeImage.combine(0, l, k, n);

        for(int k1 = 0; k1 < 4; ++k1) {
            IReorderingProcessor ireorderingprocessor = signBlockEntity.getRenderMessage(k1, (p_243502_1_) -> {
                List<IReorderingProcessor> list = fontrenderer.split(p_243502_1_, 90);
                return list.isEmpty() ? IReorderingProcessor.EMPTY : list.get(0);
            });
            if (ireorderingprocessor != null) {
                float f3 = (float)(-fontrenderer.width(ireorderingprocessor) / 2);
                fontrenderer.drawInBatch(ireorderingprocessor, f3, (float)(k1 * 10 - 20), i1, false, matrixStack.last().pose(), vertexConsumerProvider, false, 0, i);
            }
        }


        matrixStack.popPose();
        renderGrave(blockState, f, matrixStack, vertexConsumerProvider, i, j);

    }

    public void renderGrave(BlockState state, float f, MatrixStack matrixStack, IRenderTypeBuffer vertexConsumerProvider, int i, int j) {

        matrixStack.pushPose();
        matrixStack.translate(0.5, 0.43, 0.5);
        matrixStack.scale(2.28F, 2.15F, 2.28F);

        float rotation = -((float)state.getValue(GravestoneBlock.FACING).toYRot());
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(rotation));

        //Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemCameraTransforms.TransformType.GROUND, i, OverlayTexture.NO_OVERLAY, matrixStack, vertexConsumerProvider);
        Minecraft.getInstance().getItemRenderer().renderStatic(new ItemStack(state.getBlock().asItem(), 1), ItemCameraTransforms.TransformType.GROUND, i, OverlayTexture.NO_OVERLAY, matrixStack, vertexConsumerProvider);

        matrixStack.popPose();
    }

    static {
        defaultLayer = RenderType.entitySolid(new ResourceLocation("textures/entity/signs/oak.png"));
        RenderType layer1 = RenderType.entitySolid(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/polished_basalt.png"));
        RenderType layer2 = RenderType.entitySolid(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/cobblestone.png"));
        RenderType layer3 = RenderType.entitySolid(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/mossy_cobblestone.png"));
        RenderType layer4 = RenderType.entitySolid(new ResourceLocation(TheGraveyard.MOD_ID, "textures/entity/gravestone/deepslate.png"));
        LAYERS.put(TGBlocks.GRAVESTONE, layer1);
        LAYERS.put(TGBlocks.COBBLESTONE_GRAVESTONE, layer2);
        LAYERS.put(TGBlocks.MOSSY_COBBLESTONE_GRAVESTONE, layer3);
        LAYERS.put(TGBlocks.DEEPSLATE_GRAVESTONE, layer4);

    }



    public static IVertexBuilder getConsumer(IRenderTypeBuffer provider, Block block) {
        return provider.getBuffer(LAYERS.getOrDefault(block, defaultLayer));
    }


}

