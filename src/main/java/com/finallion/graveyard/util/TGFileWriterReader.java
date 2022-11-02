package com.finallion.graveyard.util;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.item.BoneStaffItem;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.LevelResource;

import java.io.*;
import java.util.Map;
import java.util.UUID;

public class TGFileWriterReader {

    public TGFileWriterReader() {}

    public static class Load {

        public void onWorldLoad(MinecraftServer server) {
            /* GHOULING UUID FILE READER */
            String line;
            File file = new File(server.getWorldPath(LevelResource.ROOT).toString() + "/graveyardGhoulingUUIDmapping.txt");
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(server.getWorldPath(LevelResource.ROOT).toString() + "/graveyardGhoulingUUIDmapping.txt"))) {
                    TheGraveyard.LOGGER.info("Readubg Graveyard Ghouling UUIDs in graveyardGhoulingUUIDMapping.txt.");
                    while ((line = reader.readLine()) != null) {
                        String[] keyValuePair = line.split(":", 2);

                        if (keyValuePair.length > 1) {
                            String key = keyValuePair[0];
                            String value = keyValuePair[1];
                            BoneStaffItem.ownerGhoulingMapping.putIfAbsent(UUID.fromString(key), UUID.fromString(value));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static class Unload {

        public void onWorldUnload(MinecraftServer server) {
            try {
                FileWriter fileWriter = new FileWriter(server.getWorldPath(LevelResource.ROOT).toString() + "/graveyardGhoulingUUIDmapping.txt");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                TheGraveyard.LOGGER.info("Saving Graveyard Ghouling UUIDs in graveyardGhoulingUUIDMapping.txt.");

                for (Map.Entry<UUID, UUID> entry : BoneStaffItem.ownerGhoulingMapping.entrySet()) {
                    bufferedWriter.write(entry.getKey() + ":" + entry.getValue());
                    bufferedWriter.newLine();
                }

                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
