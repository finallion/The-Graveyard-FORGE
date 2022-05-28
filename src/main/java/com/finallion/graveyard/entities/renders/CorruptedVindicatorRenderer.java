package com.finallion.graveyard.entities.renders;

import com.finallion.graveyard.client.TheGraveyardClient;
import com.finallion.graveyard.entities.CorruptedPillager;
import com.finallion.graveyard.entities.CorruptedVindicator;
import com.finallion.graveyard.entities.models.CorruptedIllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class CorruptedVindicatorRenderer extends CorruptedIllagerRenderer<CorruptedVindicator> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("graveyard:textures/entity/corrupted_vindicator.png");

    public CorruptedVindicatorRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new CorruptedIllagerModel<>(ctx.bakeLayer(TheGraveyardClient.CORRUPTED_ILLAGER_MODEL_LAYER)), 0.5F);
        //this.addFeature(new VindicatorEyes(this));
        this.model.getHat().visible = false;
    }

    @Override
    public ResourceLocation getTextureLocation(CorruptedVindicator p_114482_) {
        return TEXTURE;
    }
}

