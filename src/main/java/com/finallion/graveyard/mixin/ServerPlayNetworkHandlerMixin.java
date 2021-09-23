package com.finallion.graveyard.mixin;

import com.finallion.graveyard.blockentities.GravestoneBlockEntity;
import com.finallion.graveyard.blocks.GravestoneBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.ServerPlayNetHandler;
import net.minecraft.network.play.client.CUpdateSignPacket;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;


@Mixin(ServerPlayNetHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Shadow
    private static final Logger LOGGER = LogManager.getLogger();

    @Shadow
    public ServerPlayerEntity player;

    @Inject(method = "processUpdateSign", at = @At(value = "HEAD"), cancellable = true)
    private void signUpdate(CUpdateSignPacket packet, List<String> signText, CallbackInfo info) {
        this.player.resetLastActionTime();
        ServerWorld serverworld = this.player.getLevel();
        BlockPos blockpos = packet.getPos();
        if (serverworld.hasChunkAt(blockpos)) {
            BlockState blockstate = serverworld.getBlockState(blockpos);
            TileEntity tileentity = serverworld.getBlockEntity(blockpos);
            if (!(tileentity instanceof GravestoneBlockEntity)) {
                return;
            }

            GravestoneBlockEntity signtileentity = (GravestoneBlockEntity)tileentity;
            if (!signtileentity.isEditable() || signtileentity.getPlayerWhoMayEdit() != this.player) {
                LOGGER.warn("Player {} just tried to change non-editable sign", (Object)this.player.getName().getString());
                return;
            }

            for(int i = 0; i < signText.size(); ++i) {
                signtileentity.setMessage(i, new StringTextComponent(signText.get(i)));
            }

            signtileentity.setChanged();
            serverworld.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
            info.cancel();
        }
    }


}
