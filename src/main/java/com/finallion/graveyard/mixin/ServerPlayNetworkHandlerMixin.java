package com.finallion.graveyard.mixin;

import com.finallion.graveyard.blockentities.GravestoneBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ServerboundSignUpdatePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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

    @Final
    @Shadow
    static final Logger LOGGER = LogManager.getLogger();

    @Shadow
    public ServerPlayer player;

    @Final
    @Shadow
    public Connection connection;

    /*
     private void updateSignText(ServerboundSignUpdatePacket p_9923_, List<TextFilter.FilteredText> p_9924_) {
      this.player.resetLastActionTime();
      ServerLevel serverlevel = this.player.getLevel();
      BlockPos blockpos = p_9923_.getPos();
      if (serverlevel.hasChunkAt(blockpos)) {
         BlockState blockstate = serverlevel.getBlockState(blockpos);
         BlockEntity blockentity = serverlevel.getBlockEntity(blockpos);
         if (!(blockentity instanceof SignBlockEntity)) {
            return;
         }

         SignBlockEntity signblockentity = (SignBlockEntity)blockentity;
         if (!signblockentity.isEditable() || !this.player.getUUID().equals(signblockentity.getPlayerWhoMayEdit())) {
            LOGGER.warn("Player {} just tried to change non-editable sign", (Object)this.player.getName().getString());
            return;
         }

         for(int i = 0; i < p_9924_.size(); ++i) {
            TextFilter.FilteredText textfilter$filteredtext = p_9924_.get(i);
            if (this.player.isTextFilteringEnabled()) {
               signblockentity.setMessage(i, new TextComponent(textfilter$filteredtext.getFiltered()));
            } else {
               signblockentity.setMessage(i, new TextComponent(textfilter$filteredtext.getRaw()), new TextComponent(textfilter$filteredtext.getFiltered()));
            }
         }

         signblockentity.setChanged();
         serverlevel.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
      }

   }
     */

    @Shadow @Final private MinecraftServer server;

    @Inject(method = "updateSignText", at = @At(value = "HEAD"), cancellable = true)
    private void signUpdate(ServerboundSignUpdatePacket packet, List<TextFilter.FilteredText> signText, CallbackInfo info) {
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
                LOGGER.warn("Player {} just tried to change non-editable sign", (Object)this.player.getName().getString());
                return;
            }

            for(int i = 0; i < signText.size(); ++i) {
                TextFilter.FilteredText textfilter$filteredtext = signText.get(i);
                if (this.player.isTextFilteringEnabled()) {
                    signblockentity.setMessage(i, new TextComponent(textfilter$filteredtext.getFiltered()));
                } else {
                    signblockentity.setMessage(i, new TextComponent(textfilter$filteredtext.getRaw()), new TextComponent(textfilter$filteredtext.getFiltered()));
                }
            }
            signblockentity.setChanged();
            serverlevel.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
            info.cancel();


        }
    }
}
