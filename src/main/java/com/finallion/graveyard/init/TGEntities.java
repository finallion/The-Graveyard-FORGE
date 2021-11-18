package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entites.AcolyteEntity;
import com.finallion.graveyard.entites.BaseGhoulEntity;
import com.finallion.graveyard.entites.ReaperEntity;
import com.finallion.graveyard.entites.SkeletonCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGEntities {

    public static Set<EntityType<?>> entities = new HashSet<>();

    public static final EntityType<SkeletonCreeper> SKELETON_CREEPER = createEntity("skeleton_creeper", EntityType.Builder.of(SkeletonCreeper::new, EntityClassification.MONSTER).sized(0.6F, 1.7F).build(TheGraveyard.MOD_ID + ":skeleton_creeper"));
    public static final EntityType<AcolyteEntity> ACOLYTE = createEntity("acolyte", EntityType.Builder.of(AcolyteEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.9F).build(TheGraveyard.MOD_ID + ":acolyte"));
    public static final EntityType<ReaperEntity> REAPER = createEntity("reaper", EntityType.Builder.of(ReaperEntity::new, EntityClassification.MONSTER).sized(0.5F, 1.4F).build(TheGraveyard.MOD_ID + ":reaper"));
    public static final EntityType<BaseGhoulEntity> GHOUL = createEntity("ghoul", EntityType.Builder.of(BaseGhoulEntity::new, EntityClassification.MONSTER).sized(1.0F, 2.15F).build(TheGraveyard.MOD_ID + ":ghoul"));

    public static <E extends Entity, ET extends EntityType<E>> ET createEntity(String id, ET entityType) {
        entityType.setRegistryName(new ResourceLocation(TheGraveyard.MOD_ID, id));
        entities.add(entityType);
        return entityType;
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        entities.forEach(entityType -> event.getRegistry().register(entityType));
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(SKELETON_CREEPER, SkeletonCreeper.createAttributes().build());
        event.put(ACOLYTE, AcolyteEntity.createAttributes().build());
        event.put(GHOUL, BaseGhoulEntity.createAttributes().build());
        event.put(REAPER, ReaperEntity.createAttributes().build());
    }




    /*
    public static final EntityType<BaseGhoulEntity> GHOUL = EntityType.Builder.of(BaseGhoulEntity::new)
            .spawnGroup(SpawnGroup.MONSTER)
            .entityFactory(BaseGhoulEntity::new)
            .dimensions(EntityDimensions.changing(1.0F, 2.15F))
            .spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BaseGhoulEntity::canSpawn)
            .build();
*/








}
