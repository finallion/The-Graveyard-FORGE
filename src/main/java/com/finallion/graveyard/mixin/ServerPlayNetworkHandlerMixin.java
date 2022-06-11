package com.finallion.graveyard.mixin;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blockentities.GravestoneBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundSignUpdatePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.FilteredText;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.network.TextFilter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;


@Mixin(ServerGamePacketListenerImpl.class)
public class ServerPlayNetworkHandlerMixin {

    @Shadow
    public ServerPlayer player;

    @Final
    @Shadow
    public Connection connection;


    @Shadow @Final private MinecraftServer server;

    @Inject(method = "updateSignText", at = @At(value = "HEAD"), cancellable = true)
    private void signUpdate(ServerboundSignUpdatePacket packet, List<FilteredText<String>> signText, CallbackInfo info) {
        this.player.resetLastActionTime();
        ServerLevel serverlevel = this.player.getLevel();
        BlockPos blockpos = packet.getPos();
        if (serverlevel.hasChunkAt(blockpos)) {
            BlockState blockstate = serverlevel.getBlockState(blockpos);
            BlockEntity blockentity = serverlevel.getBlockEntity(blockpos);

            if (!(blockentity instanceof GravestoneBlockEntity)) {
                return;
            }

            GravestoneBlockEntity signblockentity = (GravestoneBlockEntity)blockentity;
            if (!signblockentity.isEditable() || !this.player.getUUID().equals(signblockentity.getPlayerWhoMayEdit())) {
                TheGraveyard.LOGGER.warn("Player {} just tried to change non-editable sign", (Object)this.player.getName().getString());
                return;
            }

            for(int i = 0; i < signText.size(); ++i) {
                FilteredText<Component> textfilter$filteredtext = signText.get(i).map(Component::literal);
                if (this.player.isTextFilteringEnabled()) {
                    signblockentity.setMessage(i, textfilter$filteredtext.filteredOrElse(CommonComponents.EMPTY));
                } else {
                    signblockentity.setMessage(i, textfilter$filteredtext.raw(), textfilter$filteredtext.filteredOrElse(CommonComponents.EMPTY));
                }
            }
            signblockentity.setChanged();
            serverlevel.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
            info.cancel();


        }
    }
}
