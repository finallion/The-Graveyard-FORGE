package com.finallion.graveyard.mixin;

import com.finallion.graveyard.utils.SpriteIdentifierRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(Atlases.class)
public class AtlasesMixin {
    @Inject(method = "collectAllMaterials", at = @At("RETURN"))
    private static void injectTGSigns(Consumer<RenderMaterial> consumer, CallbackInfo info) {
        for(RenderMaterial identifier: SpriteIdentifierRegistry.INSTANCE.getSprites()) {
            consumer.accept(identifier);
        }
    }
}


