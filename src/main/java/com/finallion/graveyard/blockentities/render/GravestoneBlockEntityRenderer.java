package com.finallion.graveyard.blockentities.render;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blockentities.GravestoneBlockEntity;
import com.finallion.graveyard.blocks.GravestoneBlock;
import com.finallion.graveyard.init.TGBlocks;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.List;


@OnlyIn(Dist.CLIENT)
public class GravestoneBlockEntityRenderer implements BlockEntityRenderer<GravestoneBlockEntity> {
    private static final int RENDER_DISTANCE = Mth.square(16);
    private final Font font;
    private static final HashMap<Block, RenderType> LAYERS = Maps.newHashMap();
    private static RenderType defaultLayer;

    public GravestoneBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.font = ctx.getFont();
    }


    public void render(GravestoneBlockEntity signBlockEntity, float f, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int p_112501_, int p_112502_) {
        BlockState blockState = signBlockEntity.getBlockState();
        matrixStack.pushPose();

        // text render location in world
        // offset on block
        matrixStack.translate(0.5D, 0.25D, 0.5D);

        float rotation = -(blockState.getValue(GravestoneBlock.FACING).toYRot());
        //float h = -((float)((Integer)blockState.getStructure(SignBlock.ROTATION) * 360) / 16.0F);
        matrixStack.mulPose(Axis.YP.rotationDegrees(rotation));
        matrixStack.pushPose();
        // size
        matrixStack.scale(0.6666667F, -0.6666667F, -0.6666667F);
        matrixStack.popPose();
        matrixStack.translate(0.0D, 0.3333333432674408D, 0.23);
        matrixStack.scale(0.010416667F, -0.010416667F, 0.010416667F);


        //int i = signBlockEntity.getColor().getTextColor();
        int i = getDarkColor(signBlockEntity.getText());

        FormattedCharSequence[] aformattedcharsequence = signBlockEntity.getText().getRenderMessages(Minecraft.getInstance().isTextFilteringEnabled(), (p_173653_) -> {
            List<FormattedCharSequence> list = this.font.split(p_173653_, 90);
            return list.isEmpty() ? FormattedCharSequence.EMPTY : list.get(0);
        });

        int k;
        boolean flag;
        int l;
        if (signBlockEntity.getText().hasGlowingText()) {
            k = signBlockEntity.getText().getColor().getTextColor();
            flag = isOutlineVisible(signBlockEntity, k);
            l = 15728880;
        } else {
            k = i;
            flag = false;
            l = p_112501_;
        }


        for(int i1 = 0; i1 < 4; ++i1) {
            FormattedCharSequence formattedcharsequence = aformattedcharsequence[i1];
            float f3 = (float)(-this.font.width(formattedcharsequence) / 2);
            if (flag) {
                this.font.drawInBatch8xOutline(formattedcharsequence, f3, (float)(i1 * 10 - 20), k, i, matrixStack.last().pose(), vertexConsumerProvider, l);
            } else {
                this.font.drawInBatch(formattedcharsequence, f3, (float)(i1 * 10 - 20), k, false, matrixStack.last().pose(), vertexConsumerProvider, Font.DisplayMode.POLYGON_OFFSET, 0, l);
            }
        }


        matrixStack.popPose();
        renderGrave(blockState, f, matrixStack, vertexConsumerProvider, p_112501_, p_112502_, signBlockEntity.getLevel());

    }

    public void renderGrave(BlockState state, float f, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, int j, Level level) {
        matrixStack.pushPose();
        matrixStack.translate(0.5, 0.43, 0.5);
        matrixStack.scale(2.28F, 2.15F, 2.28F);

        float rotation = -((float)state.getValue(GravestoneBlock.FACING).toYRot());
        matrixStack.mulPose(Axis.YP.rotationDegrees(rotation));
        Minecraft.getInstance().getItemRenderer().renderStatic(new ItemStack(state.getBlock().asItem(), 1), ItemDisplayContext.GROUND, i, j, matrixStack, vertexConsumerProvider, level, 2);



        matrixStack.popPose();
    }


    private static boolean isOutlineVisible(GravestoneBlockEntity p_173642_, int p_173643_) {
        if (p_173643_ == DyeColor.BLACK.getTextColor()) {
            return true;
        } else {
            Minecraft minecraft = Minecraft.getInstance();
            LocalPlayer localplayer = minecraft.player;
            if (localplayer != null && minecraft.options.getCameraType().isFirstPerson() && localplayer.isScoping()) {
                return true;
            } else {
                Entity entity = minecraft.getCameraEntity();
                return entity != null && entity.distanceToSqr(Vec3.atCenterOf(p_173642_.getBlockPos())) < (double)RENDER_DISTANCE;
            }
        }
    }


    static int getDarkColor(SignText p_277914_) {
        int i = p_277914_.getColor().getTextColor();
        if (i == DyeColor.BLACK.getTextColor() && p_277914_.hasGlowingText()) {
            return -988212;
        } else {
            double d0 = 0.4D;
            int j = (int)((double) FastColor.ARGB32.red(i) * 0.4D);
            int k = (int)((double)FastColor.ARGB32.green(i) * 0.4D);
            int l = (int)((double)FastColor.ARGB32.blue(i) * 0.4D);
            return FastColor.ARGB32.color(0, j, k, l);
        }
    }

    public static SignRenderer.SignModel createSignModel(EntityModelSet p_173647_, WoodType p_173648_) {
        return new SignRenderer.SignModel(p_173647_.bakeLayer(ModelLayers.createSignModelName(p_173648_)));
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
        RenderType layer10 = RenderType.entitySolid(TGBlocks.RED_SANDSTONE_GRAVESTONE_TEXTURE);
        RenderType layer11 = RenderType.entitySolid(TGBlocks.SANDSTONE_GRAVESTONE_TEXTURE);
        RenderType layer12 = RenderType.entitySolid(TGBlocks.GILDED_BLACKSTONE_GRAVESTONE_TEXTURE);
        RenderType layer13 = RenderType.entitySolid(TGBlocks.QUARTZ_BRICKS_GRAVESTONE_TEXTURE);
        LAYERS.put(TGBlocks.GRAVESTONE.get(), layer1);
        LAYERS.put(TGBlocks.COBBLESTONE_GRAVESTONE.get(), layer2);
        LAYERS.put(TGBlocks.MOSSY_COBBLESTONE_GRAVESTONE.get(), layer3);
        LAYERS.put(TGBlocks.DEEPSLATE_GRAVESTONE.get(), layer4);
        LAYERS.put(TGBlocks.BLACKSTONE_GRAVESTONE.get(), layer5);
        LAYERS.put(TGBlocks.CRACKED_BLACKSTONE_GRAVESTONE.get(), layer6);
        LAYERS.put(TGBlocks.STONE_BRICKS_GRAVESTONE.get(), layer7);
        LAYERS.put(TGBlocks.MOSSY_STONE_BRICKS_GRAVESTONE.get(), layer8);
        LAYERS.put(TGBlocks.BRICKS_GRAVESTONE.get(), layer9);
        LAYERS.put(TGBlocks.RED_SANDSTONE_GRAVESTONE.get(), layer10);
        LAYERS.put(TGBlocks.SANDSTONE_GRAVESTONE.get(), layer11);
        LAYERS.put(TGBlocks.GILDED_BLACKSTONE_GRAVESTONE.get(), layer12);
        LAYERS.put(TGBlocks.QUARTZ_BRICKS_GRAVESTONE.get(), layer13);
    }



    public static VertexConsumer getConsumer(MultiBufferSource provider, Block block) {
        return provider.getBuffer(LAYERS.getOrDefault(block, defaultLayer));
    }
}

