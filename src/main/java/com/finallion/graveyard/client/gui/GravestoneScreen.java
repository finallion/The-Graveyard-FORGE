package com.finallion.graveyard.client.gui;

import com.finallion.graveyard.blockentities.GravestoneBlockEntity;
import com.finallion.graveyard.blockentities.render.GravestoneBlockEntityRenderer;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.font.TextFieldHelper;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundSignUpdatePacket;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.stream.IntStream;

@OnlyIn(Dist.CLIENT)
public class GravestoneScreen extends Screen {
    private final GravestoneBlockEntity sign;
    private SignRenderer.SignModel model;
    private int frame;
    private int line;
    private WoodType woodType;
    private TextFieldHelper signField;
    private final String[] messages;
    private SignText text;

    public GravestoneScreen(GravestoneBlockEntity p_277842_, boolean p_277969_) {
        this(p_277842_, p_277969_, Component.translatable("gravestone.edit"));
    }

    public GravestoneScreen(GravestoneBlockEntity p_277792_, boolean p_278039_, Component p_277393_) {
        super(p_277393_);
        this.sign = p_277792_;
        this.text = p_277792_.getText();
        this.woodType = SignBlock.getWoodType(p_277792_.getBlockState().getBlock());
        this.messages = IntStream.range(0, 4).mapToObj((p_277214_) -> this.text.getMessage(p_277214_, p_278039_)).map(Component::getString).toArray(String[]::new);
    }


    protected void init() {
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (p_251194_) -> {
            this.onDone();
        }).bounds(this.width / 2 - 100, this.height / 4 + 144, 200, 20).build());
        this.signField = new TextFieldHelper(() -> this.messages[this.line], this::setMessage, TextFieldHelper.createClipboardGetter(this.minecraft), TextFieldHelper.createClipboardSetter(this.minecraft), (p_280850_) -> this.minecraft.font.width(p_280850_) <= this.sign.getMaxTextLineWidth());
        this.woodType = WoodType.OAK;
        this.model = GravestoneBlockEntityRenderer.createSignModel(this.minecraft.getEntityModels(), this.woodType);
    }

    private void setMessage(String p_277913_) {
        this.messages[this.line] = p_277913_;
        this.text = this.text.setMessage(this.line, Component.literal(p_277913_));
        this.sign.setText(this.text);
    }

    public void removed() {
        ClientPacketListener clientpacketlistener = this.minecraft.getConnection();
        if (clientpacketlistener != null) {
            clientpacketlistener.send(new ServerboundSignUpdatePacket(this.sign.getBlockPos(), true, this.messages[0], this.messages[1], this.messages[2], this.messages[3]));
        }
    }

    public void tick() {
        ++this.frame;
        if (!this.sign.getType().isValid(this.sign.getBlockState())) {
            this.onDone();
        }

    }

    private void onDone() {
        this.sign.setChanged();
        this.minecraft.setScreen((Screen)null);
    }

    public boolean charTyped(char p_99262_, int p_99263_) {
        this.signField.charTyped(p_99262_);
        return true;
    }

    public void onClose() {
        this.onDone();
    }

    public boolean keyPressed(int p_99267_, int p_99268_, int p_99269_) {
        if (p_99267_ == 265) {
            this.line = this.line - 1 & 3;
            this.signField.setCursorToEnd();
            return true;
        } else if (p_99267_ != 264 && p_99267_ != 257 && p_99267_ != 335) {
            return this.signField.keyPressed(p_99267_) ? true : super.keyPressed(p_99267_, p_99268_, p_99269_);
        } else {
            this.line = this.line + 1 & 3;
            this.signField.setCursorToEnd();
            return true;
        }
    }

    public void render(GuiGraphics p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        Lighting.setupForFlatItems();
        this.renderBackground(p_230430_1_);
        p_230430_1_.drawCenteredString(this.font, this.title, this.width / 2, 40, 16777215);

        p_230430_1_.pose().pushPose();
        p_230430_1_.pose().translate((double)(this.width / 2), 0.0D, 50.0D);
        float f = 93.75F;
        p_230430_1_.pose().scale(93.75F, -93.75F, 93.75F);
        p_230430_1_.pose().translate(0.0D, -1.3125D, 0.0D);

        boolean flag1 = this.frame / 6 % 2 == 0;
        float f1 = 0.6666667F;
        p_230430_1_.pose().pushPose();
        p_230430_1_.pose().scale(0.6666667F, -0.6666667F, -0.6666667F);
        MultiBufferSource.BufferSource irendertypebuffer$impl = this.minecraft.renderBuffers().bufferSource();
        VertexConsumer ivertexbuilder = GravestoneBlockEntityRenderer.getConsumer(irendertypebuffer$impl, sign.getBlockState().getBlock());
        this.model.stick.visible = false;
        this.model.root.render(p_230430_1_.pose(), ivertexbuilder, 15728880, OverlayTexture.NO_OVERLAY);

        p_230430_1_.pose().popPose();
        float f2 = 0.010416667F;
        p_230430_1_.pose().translate(0.0D, (double)0.33333334F, (double)0.046666667F);
        p_230430_1_.pose().scale(0.010416667F, -0.010416667F, 0.010416667F);


        int i = this.text.getColor().getTextColor();
        boolean flag = this.frame / 6 % 2 == 0;
        int j = this.signField.getCursorPos();
        int k = this.signField.getSelectionPos();
        int l = 4 * this.sign.getTextLineHeight() / 2;
        int i1 = this.line * this.sign.getTextLineHeight() - l;

        for(int j1 = 0; j1 < this.messages.length; ++j1) {
            String s = this.messages[j1];
            if (s != null) {
                if (this.font.isBidirectional()) {
                    s = this.font.bidirectionalShaping(s);
                }

                int k1 = -this.font.width(s) / 2;
                p_230430_1_.drawString(this.font, s, k1, j1 * this.sign.getTextLineHeight() - l, i, false);
                if (j1 == this.line && j >= 0 && flag) {
                    int l1 = this.font.width(s.substring(0, Math.max(Math.min(j, s.length()), 0)));
                    int i2 = l1 - this.font.width(s) / 2;
                    if (j >= s.length()) {
                        p_230430_1_.drawString(this.font, "_", i2, i1, i, false);
                    }
                }
            }
        }

        for(int k3 = 0; k3 < this.messages.length; ++k3) {
            String s1 = this.messages[k3];
            if (s1 != null && k3 == this.line && j >= 0) {
                int l3 = this.font.width(s1.substring(0, Math.max(Math.min(j, s1.length()), 0)));
                int i4 = l3 - this.font.width(s1) / 2;
                if (flag && j < s1.length()) {
                    p_230430_1_.fill(i4, i1 - 1, i4 + 1, i1 + this.sign.getTextLineHeight(), -16777216 | i);
                }

                if (k != j) {
                    int j4 = Math.min(j, k);
                    int j2 = Math.max(j, k);
                    int k2 = this.font.width(s1.substring(0, j4)) - this.font.width(s1) / 2;
                    int l2 = this.font.width(s1.substring(0, j2)) - this.font.width(s1) / 2;
                    int i3 = Math.min(k2, l2);
                    int j3 = Math.max(k2, l2);
                    p_230430_1_.fill(RenderType.guiTextHighlight(), i3, i1, j3, i1 + this.sign.getTextLineHeight(), -16776961);
                }
            }
        }
        p_230430_1_.pose().popPose();
        Lighting.setupFor3DItems();
        super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
    }

}
