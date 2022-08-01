package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.horde.GraveyardHordeSpawner;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID)
public class TGSpawner {
    private static Map<ResourceLocation, GraveyardHordeSpawner> spawners = new HashMap<>();

    @SubscribeEvent
    public static void onWorldLoad(ServerStartingEvent event) {
        MinecraftServer server = event.getServer();
        spawners.put(Level.OVERWORLD.location(), new GraveyardHordeSpawner(server));
    }

    @SubscribeEvent
    public static void onServerStart(ServerStoppedEvent event) {
        spawners.clear();
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.START)
            return;

        if (event.side != LogicalSide.SERVER)
            return;

        GraveyardHordeSpawner spawner = spawners.get(event.level.dimension().location());
        if (spawner != null) {
            spawner.tick(event.level);
        }
    }
}