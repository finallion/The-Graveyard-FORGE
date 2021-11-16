package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;


/*
public class TGItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheGraveyard.MOD_ID);

    public static final RegistryObject<Item> SKELETON_CREEPER_SPAWN_EGG = ITEMS.register("skeleton_creeper_spawn_egg",
            () -> new SpawnEggItem(TGEntities.SKELETON_CREEPER.get(), 7960171, 15263976, new Item.Properties().tab(TheGraveyard.GROUP)));

    public static final RegistryObject<Item> ACOLYTE_SPAWN_EGG = ITEMS.register("acolyte_spawn_egg",
            () -> new SpawnEggItem(TGEntities.ACOLYTE.get(), 2688830, 5898240, new Item.Properties().tab(TheGraveyard.GROUP)));

    public static final RegistryObject<Item> GHOUL_SPAWN_EGG = ITEMS.register("ghoul_spawn_egg",
            () -> new SpawnEggItem(TGEntities.GHOUL.get(), 6239802, 16487198, new Item.Properties().tab(TheGraveyard.GROUP)));

    public static final RegistryObject<Item> REAPER_SPAWN_EGG = ITEMS.register("reaper_spawn_egg",
            () -> new SpawnEggItem(TGEntities.REAPER.get(), 1381653, 5898240, new Item.Properties().tab(TheGraveyard.GROUP)));


}

 */


@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGItems {
    public static final List<Item> items_list = new ArrayList<Item>();

    @SubscribeEvent
    public static void initItems(RegistryEvent.Register<Item> event) {

        createSpawnEgg(TGEntities.SKELETON_CREEPER.get(), 7960171, 15263976, "skeleton_creeper");
        createSpawnEgg(TGEntities.ACOLYTE.get(), 2688830, 5898240, "acolyte");
        createSpawnEgg(TGEntities.GHOUL.get(), 6239802, 16487198, "ghoul");
        createSpawnEgg(TGEntities.REAPER.get(), 1381653, 5898240, "reaper");

        items_list.forEach((e) -> event.getRegistry().register(e));
    }

    public static Item registerItem(Item item, String name) {
        item.setRegistryName(new ResourceLocation(TheGraveyard.MOD_ID, name));
        items_list.add(item);

        return item;
    }

    public static Item createSpawnEgg(EntityType<?> entity, int color1, int color2, String name) {
        return registerItem(new SpawnEggItem(entity, color1, color2, new Item.Properties().tab(TheGraveyard.GROUP)), name + "_spawn_egg");
    }


}
