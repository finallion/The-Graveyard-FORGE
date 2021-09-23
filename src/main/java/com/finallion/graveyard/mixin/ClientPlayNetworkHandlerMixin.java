package com.finallion.graveyard.mixin;

import com.finallion.graveyard.blockentities.GravestoneBlockEntity;
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
    private Minecraft client;

    @Shadow
    private ClientWorld world;

    @Inject(method = "handleSignEditorOpen", at = @At(value = "HEAD"), cancellable = true)
    public void openSignEditor(SOpenSignMenuPacket packet, CallbackInfo info) {
        PacketThreadUtil.ensureRunningOnSameThread(packet, (ClientPlayNetHandler) (Object) this, this.client);
        TileEntity tileentity = this.world.getBlockEntity(packet.getPos());
        if (!(tileentity instanceof GravestoneBlockEntity)) {
            tileentity = new GravestoneBlockEntity();
            this.client.player.openTextEdit((SignTileEntity)tileentity);
            info.cancel();
        }
    }

    @Inject(method = "handleUpdateTileEntity", at = @At(value = "HEAD"), cancellable = true)
    public void onEntityUpdate(SUpdateTileEntityPacket p_147273_1_, CallbackInfo info) {
        PacketThreadUtil.ensureRunningOnSameThread(p_147273_1_, (ClientPlayNetHandler) (Object) this, this.client);
        BlockPos blockpos = p_147273_1_.getPos();
        TileEntity tileentity = this.client.level.getBlockEntity(blockpos);

        if (tileentity instanceof GravestoneBlockEntity) {
            tileentity.load(this.client.level.getBlockState(blockpos), p_147273_1_.getTag());
            info.cancel();
        }
    }

}
