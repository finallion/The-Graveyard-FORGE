package com.finallion.graveyard.mixin;

import com.finallion.graveyard.blockentities.GravestoneBlockEntity;
import com.finallion.graveyard.client.gui.GravestoneScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.CommandBlockEditScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.BlockPos;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundOpenSignEditorPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.CommandBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPlayNetworkHandlerMixin {

    @Shadow
    private ClientLevel level;

    @Final
    @Shadow
    private Connection connection;

    @Final
    @Shadow
    private Minecraft minecraft;

    @Inject(method = "handleOpenSignEditor", at = @At(value = "HEAD"), cancellable = true)
    public void handleOpenSignEditor(ClientboundOpenSignEditorPacket packet, CallbackInfo info) {
        PacketUtils.ensureRunningOnSameThread(packet, (ClientGamePacketListener) this, this.minecraft);
        BlockPos blockpos = packet.getPos();
        BlockEntity blockentity = this.level.getBlockEntity(blockpos);
        if (blockentity instanceof GravestoneBlockEntity) {
            BlockState blockstate = this.level.getBlockState(blockpos);
            blockentity = new GravestoneBlockEntity(blockpos, blockstate);
            blockentity.setLevel(this.level);
            Minecraft.getInstance().setScreen(new GravestoneScreen((GravestoneBlockEntity) blockentity,  false));
            info.cancel();
        }
    }


    @Inject(method = "handleBlockEntityData", at = @At(value = "HEAD"), cancellable = true)
    public void handleBlockEntityData(ClientboundBlockEntityDataPacket p_104976_, CallbackInfo info) {
      PacketUtils.ensureRunningOnSameThread(p_104976_, (ClientGamePacketListener) this, this.minecraft);
      BlockPos blockpos = p_104976_.getPos();
      BlockEntity blockEntity = this.level.getBlockEntity(blockpos);
        if (blockEntity instanceof GravestoneBlockEntity) {
            blockEntity.load(p_104976_.getTag());
            info.cancel();
        }
   }



}
