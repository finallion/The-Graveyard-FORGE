package com.finallion.graveyard.client.gui;

import com.finallion.graveyard.blockentities.GravestoneBlockEntity;
import com.finallion.graveyard.blockentities.render.GravestoneBlockEntityRenderer;
import com.finallion.graveyard.blocks.GravestoneBlock;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.fonts.TextInputUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.network.play.client.CUpdateSignPacket;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

@OnlyIn(Dist.CLIENT)
public class GravestoneScreen extends Screen {
    private final GravestoneBlockEntity sign;
    private SignTileEntityRenderer.SignModel model;
    private int frame;
    private int line;
    private TextInputUtil signField;
    private final String[] messages;


    public GravestoneScreen(GravestoneBlockEntity sign) {
        super(new TranslationTextComponent("gravestone.edit"));
        this.messages = IntStream.range(0, 4).mapToObj(sign::getMessage).map(ITextComponent::getString).toArray((p_243354_0_) -> {
            return new String[p_243354_0_];
        });
        this.sign = sign;
    }



    protected void init() {
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 120, 200, 20, DialogTexts.GUI_DONE, (p_238847_1_) -> {
            this.onDone();
        }));
        this.sign.setEditable(false);
        this.signField = new TextInputUtil(() -> {
            return this.messages[this.line];
        }, (p_238850_1_) -> {
            this.messages[this.line] = p_238850_1_;
            this.sign.setMessage(this.line, new StringTextComponent(p_238850_1_));
        }, TextInputUtil.createClipboardGetter(this.minecraft), TextInputUtil.createClipboardSetter(this.minecraft), (p_238848_1_) -> {
            return this.minecraft.font.width(p_238848_1_) <= 90;
        });
    }

    public void removed() {
        this.minecraft.keyboardHandler.setSendRepeatsToGui(false);
        ClientPlayNetHandler clientplaynethandler = this.minecraft.getConnection();
        if (clientplaynethandler != null) {
            clientplaynethandler.send(new CUpdateSignPacket(this.sign.getBlockPos(), this.messages[0], this.messages[1], this.messages[2], this.messages[3]));
        }

        this.sign.setEditable(true);
    }

    public void tick() {
        ++this.frame;
        if (!this.sign.getType().isValid(this.sign.getBlockState().getBlock())) {
            this.onDone();
        }

    }

    private void onDone() {
        this.sign.setChanged();
        this.minecraft.setScreen((Screen)null);
    }

    public boolean charTyped(char p_231042_1_, int p_231042_2_) {
        this.signField.charTyped(p_231042_1_);
        return true;
    }

    public void onClose() {
        this.onDone();
    }

    public boolean keyPressed(int p_231046_1_, int p_231046_2_, int p_231046_3_) {
        if (p_231046_1_ == 265) {
            this.line = this.line - 1 & 3;
            this.signField.setCursorToEnd();
            return true;
        } else if (p_231046_1_ != 264 && p_231046_1_ != 257 && p_231046_1_ != 335) {
            return this.signField.keyPressed(p_231046_1_) ? true : super.keyPressed(p_231046_1_, p_231046_2_, p_231046_3_);
        } else {
            this.line = this.line + 1 & 3;
            this.signField.setCursorToEnd();
            return true;
        }
    }

    public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        RenderHelper.setupForFlatItems();
        this.renderBackground(p_230430_1_);
        drawCenteredString(p_230430_1_, this.font, this.title, this.width / 2, 40, 16777215);
        p_230430_1_.pushPose();
        p_230430_1_.translate((double)(this.width / 2), 0.0D, 50.0D);
        float f = 93.75F;
        p_230430_1_.scale(93.75F, -93.75F, 93.75F);
        p_230430_1_.translate(0.0D, -1.3125D, 0.0D);
        BlockState blockstate = this.sign.getBlockState();
        boolean flag = blockstate.getBlock() instanceof StandingSignBlock;
        if (!flag) {
            p_230430_1_.translate(0.0D, -0.3125D, 0.0D);
        }

        boolean flag1 = this.frame / 6 % 2 == 0;
        float f1 = 0.6666667F;
        p_230430_1_.pushPose();
        p_230430_1_.scale(0.6666667F, -0.6666667F, -0.6666667F);
        IRenderTypeBuffer.Impl irendertypebuffer$impl = this.minecraft.renderBuffers().bufferSource();
        //RenderMaterial rendermaterial = SignTileEntityRenderer.getMaterial(blockstate.getBlock());
        RenderMaterial rendermaterial = new RenderMaterial(Atlases.SIGN_SHEET, ((GravestoneBlock) sign.getBlockState().getBlock()).getTexture());
        IVertexBuilder ivertexbuilder = rendermaterial.buffer(irendertypebuffer$impl, this.model::renderType);
        this.model.sign.render(p_230430_1_, ivertexbuilder, 15728880, OverlayTexture.NO_OVERLAY);

        p_230430_1_.popPose();
        float f2 = 0.010416667F;
        p_230430_1_.translate(0.0D, (double)0.33333334F, (double)0.046666667F);
        p_230430_1_.scale(0.010416667F, -0.010416667F, 0.010416667F);
        int i = this.sign.getColor().getTextColor();
        int j = this.signField.getCursorPos();
        int k = this.signField.getSelectionPos();
        int l = this.line * 10 - this.messages.length * 5;
        Matrix4f matrix4f = p_230430_1_.last().pose();

        for(int i1 = 0; i1 < this.messages.length; ++i1) {
            String s = this.messages[i1];
            if (s != null) {
                if (this.font.isBidirectional()) {
                    s = this.font.bidirectionalShaping(s);
                }

                float f3 = (float)(-this.minecraft.font.width(s) / 2);
                this.minecraft.font.drawInBatch(s, f3, (float)(i1 * 10 - this.messages.length * 5), i, false, matrix4f, irendertypebuffer$impl, false, 0, 15728880, false);
                if (i1 == this.line && j >= 0 && flag1) {
                    int j1 = this.minecraft.font.width(s.substring(0, Math.max(Math.min(j, s.length()), 0)));
                    int k1 = j1 - this.minecraft.font.width(s) / 2;
                    if (j >= s.length()) {
                        this.minecraft.font.drawInBatch("_", (float)k1, (float)l, i, false, matrix4f, irendertypebuffer$impl, false, 0, 15728880, false);
                    }
                }
            }
        }

        irendertypebuffer$impl.endBatch();

        for(int i3 = 0; i3 < this.messages.length; ++i3) {
            String s1 = this.messages[i3];
            if (s1 != null && i3 == this.line && j >= 0) {
                int j3 = this.minecraft.font.width(s1.substring(0, Math.max(Math.min(j, s1.length()), 0)));
                int k3 = j3 - this.minecraft.font.width(s1) / 2;
                if (flag1 && j < s1.length()) {
                    fill(p_230430_1_, k3, l - 1, k3 + 1, l + 9, -16777216 | i);
                }

                if (k != j) {
                    int l3 = Math.min(j, k);
                    int l1 = Math.max(j, k);
                    int i2 = this.minecraft.font.width(s1.substring(0, l3)) - this.minecraft.font.width(s1) / 2;
                    int j2 = this.minecraft.font.width(s1.substring(0, l1)) - this.minecraft.font.width(s1) / 2;
                    int k2 = Math.min(i2, j2);
                    int l2 = Math.max(i2, j2);
                    Tessellator tessellator = Tessellator.getInstance();
                    BufferBuilder bufferbuilder = tessellator.getBuilder();
                    RenderSystem.disableTexture();
                    RenderSystem.enableColorLogicOp();
                    RenderSystem.logicOp(GlStateManager.LogicOp.OR_REVERSE);
                    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
                    bufferbuilder.vertex(matrix4f, (float)k2, (float)(l + 9), 0.0F).color(0, 0, 255, 255).endVertex();
                    bufferbuilder.vertex(matrix4f, (float)l2, (float)(l + 9), 0.0F).color(0, 0, 255, 255).endVertex();
                    bufferbuilder.vertex(matrix4f, (float)l2, (float)l, 0.0F).color(0, 0, 255, 255).endVertex();
                    bufferbuilder.vertex(matrix4f, (float)k2, (float)l, 0.0F).color(0, 0, 255, 255).endVertex();
                    bufferbuilder.end();
                    WorldVertexBufferUploader.end(bufferbuilder);
                    RenderSystem.disableColorLogicOp();
                    RenderSystem.enableTexture();
                }
            }
        }

        p_230430_1_.popPose();
        RenderHelper.setupFor3DItems();
        super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
    }

}
