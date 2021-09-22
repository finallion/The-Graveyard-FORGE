package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entites.SkeletonCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGEntities {

    public static List<EntityType<?>> entities = new ArrayList<EntityType<?>>();
    public static final EntityType<SkeletonCreeper> SKELETON_CREEPER = registerEntity(EntityType.Builder.of(SkeletonCreeper::new, EntityClassification.MONSTER).sized(0.6F, 1.7F), "skeleton_creeper");

    public static <T extends Entity> EntityType<T> registerEntity(EntityType.Builder<?> builder, String name) {
        EntityType<T> entity = (EntityType<T>) builder.build(name).setRegistryName(new ResourceLocation(TheGraveyard.MOD_ID, name));
        entities.add(entity);

        return entity;
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        entities.forEach((e) -> event.getRegistry().register(e));
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(SKELETON_CREEPER, SkeletonCreeper.createAttributes().build());
    }


}
