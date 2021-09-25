package com.finallion.graveyard.mixin;

import com.finallion.graveyard.blockentities.GravestoneBlockEntity;
import com.finallion.graveyard.client.gui.GravestoneScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.handshake.ClientHandshakeNetHandler;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.IServerPlayNetHandler;
import net.minecraft.network.play.client.CUpdateSignPacket;
import net.minecraft.network.play.server.SOpenSignMenuPacket;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.*;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Shadow
    private Minecraft minecraft;

    @Shadow
    private ClientWorld level;

    @Inject(method = "handleOpenSignEditor", at = @At(value = "HEAD"), cancellable = true)
    public void openSignEditor(SOpenSignMenuPacket packet, CallbackInfo info) {
        PacketThreadUtil.ensureRunningOnSameThread(packet, (ClientPlayNetHandler) (Object) this, this.minecraft);
        TileEntity tileentity = this.level.getBlockEntity(packet.getPos());
        if (tileentity instanceof GravestoneBlockEntity) {
            GravestoneBlockEntity gravestone = (GravestoneBlockEntity) tileentity;
            minecraft.setScreen(new GravestoneScreen(gravestone));
            info.cancel();
        }
    }


    @Inject(method = "handleBlockEntityData", at = @At(value = "HEAD"), cancellable = true)
    public void onEntityUpdate(SUpdateTileEntityPacket p_147273_1_, CallbackInfo info) {
        PacketThreadUtil.ensureRunningOnSameThread(p_147273_1_, (ClientPlayNetHandler) (Object) this, this.minecraft);
        BlockPos blockpos = p_147273_1_.getPos();
        TileEntity tileentity = this.minecraft.level.getBlockEntity(blockpos);
        if (tileentity instanceof GravestoneBlockEntity) {
            tileentity.load(this.minecraft.level.getBlockState(blockpos), p_147273_1_.getTag());
            info.cancel();
        }
    }



}
