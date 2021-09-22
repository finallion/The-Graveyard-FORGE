package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
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

        createSpawnEgg(TGEntities.SKELETON_CREEPER, 7960171, 15263976);

        items_list.forEach((e) -> event.getRegistry().register(e));
    }

    public static Item registerItem(Item item, String name) {
        item.setRegistryName(new ResourceLocation(TheGraveyard.MOD_ID, name));
        items_list.add(item);

        return item;
    }

    public static Item createSpawnEgg(EntityType<?> entity, int color1, int color2) {
        return registerItem(new SpawnEggItem(entity, color1, color2, new Item.Properties().tab(TheGraveyard.GROUP)), entity.getRegistryName().getPath() + "_spawn_egg");
    }


}
