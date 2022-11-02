package com.finallion.graveyard.events;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.horde.GraveyardHordeSpawner;
import com.finallion.graveyard.util.TGFileWriterReader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID)
public class TGServerEvents {
    private static Map<ResourceLocation, GraveyardHordeSpawner> spawners = new HashMap<>();

    @SubscribeEvent
    public static void onServerStart(ServerStartingEvent event) {
        MinecraftServer server = event.getServer();
        spawners.put(Level.OVERWORLD.location(), new GraveyardHordeSpawner(server));
    }

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {
        MinecraftServer server = event.getWorld().getServer();

        if (server != null) {
            new TGFileWriterReader.Load().onWorldLoad(server);
        }
    }

    @SubscribeEvent
    public static void onWorldUnload(WorldEvent.Unload event) {
        MinecraftServer server = event.getWorld().getServer();

        if (server != null) {
            new TGFileWriterReader.Unload().onWorldUnload(server);
        }
    }


    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event) {
        spawners.clear();
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.phase != TickEvent.Phase.START)
            return;

        if (event.side != LogicalSide.SERVER)
            return;

        GraveyardHordeSpawner spawner = spawners.get(event.world.dimension().location());
        if (spawner != null) {
            spawner.tick(event.world);
        }
    }

    //@SubscribeEvent
    //public void registerCommands(RegisterCommandsEvent event) {
    //    event.getDispatcher().register(TGCommands.register(event.getDispatcher()));
    //}
}