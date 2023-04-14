package com.finallion.graveyard.blockentities.animation;


import net.minecraft.util.math.MathHelper;

public class SarcophagusLidAnimator {
    private boolean shouldBeOpen;
    private float openness;
    private float oOpenness;

    public void tickLid() {
        this.oOpenness = this.openness;
        float f = 0.1F;
        if (!this.shouldBeOpen && this.openness > 0.0F) {
            this.openness = Math.max(this.openness - 0.1F, 0.0F);
        } else if (this.shouldBeOpen && this.openness < 1.0F) {
            this.openness = Math.min(this.openness + 0.1F, 1.0F);
        }

    }

    public float getOpenness(float p_155376_) {
        return MathHelper.lerp(p_155376_, this.oOpenness, this.openness);
    }

    public void shouldBeOpen(boolean p_155378_) {
        this.shouldBeOpen = p_155378_;
    }
}
