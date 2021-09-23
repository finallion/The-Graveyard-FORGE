package com.finallion.graveyard.mixin;

import com.finallion.graveyard.blockentities.GravestoneBlockEntity;
import com.finallion.graveyard.client.gui.GravestoneScreen;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.SignEditorOpenS2CPacket;
import net.minecraft.network.play.server.SOpenSignMenuPacket;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Shadow
    private ClientWorld level;

    @Shadow
    private Minecraft minecraft;

    @Inject(method = "handleOpenSignEditor", at = @At(value = "HEAD"), cancellable = true)
    public void openSignEditor(SOpenSignMenuPacket packet, CallbackInfo info) {
        PacketThreadUtil.ensureRunningOnSameThread(packet, this, this.minecraft);
        TileEntity tileentity = this.level.getBlockEntity(packet.getPos());
        if (!(tileentity instanceof GravestoneBlockEntity)) {
            tileentity = new GravestoneBlockEntity();
            tileentity.setLevelAndPosition(this.level, packet.getPos());
            this.minecraft.player.openTextEdit((SignTileEntity)tileentity);
            info.cancel();
        }
    }

    @Inject(method = "onBlockEntityUpdate", at = @At(value = "HEAD"), cancellable = true)
    public void onEntityUpdate(BlockEntityUpdateS2CPacket packet, CallbackInfo info) {
        NetworkThreadUtils.forceMainThread(packet, (ClientPlayPacketListener) this, this.client);
        BlockPos blockPos = packet.getPos();
        BlockEntity blockEntity = this.world.getBlockEntity(blockPos);
        if (blockEntity instanceof GravestoneBlockEntity) {
            blockEntity.fromTag(this.client.world.getBlockState(blockPos), packet.getNbt());
            info.cancel();
        }
    }
}
