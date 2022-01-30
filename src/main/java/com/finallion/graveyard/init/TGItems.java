package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.item.DaggerItem;
import com.finallion.graveyard.item.SarcophagusItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;


@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGItems {
    public static final List<Item> items_list = new ArrayList<Item>();

    @SubscribeEvent
    public static void initItems(RegistryEvent.Register<Item> event) {

        // all items that are no block items
        createSpawnEgg(TGEntities.SKELETON_CREEPER, 7960171, 15263976, "skeleton_creeper");
        createSpawnEgg(TGEntities.ACOLYTE, 2688830, 5898240, "acolyte");
        createSpawnEgg(TGEntities.GHOUL, 6239802, 16487198, "ghoul");
        createSpawnEgg(TGEntities.REAPER, 1381653, 7456477, "reaper");
        createSpawnEgg(TGEntities.REVENANT, 12965589, 9765908, "revenant");
        createSpawnEgg(TGEntities.NIGHTMARE, 592137, 4718849, "nightmare");

        registerItem(new DaggerItem(Tiers.STONE, 4, -1.0F, new Item.Properties().tab(TheGraveyard.GROUP)), "bone_dagger");
        registerItem(new SarcophagusItem(new Item.Properties().tab(TheGraveyard.GROUP)), "sarcophagus");

        items_list.forEach((e) -> event.getRegistry().register(e));
    }

    public static Item registerItem(Item item, String name) {
        item.setRegistryName(new ResourceLocation(TheGraveyard.MOD_ID, name));
        items_list.add(item);

        return item;
    }

    // raw use
    public static Item createSpawnEgg(EntityType entity, int color1, int color2, String name) {
        Item spawnEgg = new SpawnEggItem(entity, color1, color2, new Item.Properties().tab(TheGraveyard.GROUP));
        return registerItem(spawnEgg, name + "_spawn_egg");
    }


}
