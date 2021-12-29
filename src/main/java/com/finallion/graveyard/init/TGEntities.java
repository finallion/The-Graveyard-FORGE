package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entites.AcolyteEntity;
import com.finallion.graveyard.entites.BaseGhoulEntity;
import com.finallion.graveyard.entites.ReaperEntity;
import com.finallion.graveyard.entites.SkeletonCreeper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGEntities {
    public static Set<EntityType<?>> entities = new HashSet<>();

    public static final EntityType<SkeletonCreeper> SKELETON_CREEPER = createEntity("skeleton_creeper", EntityType.Builder.of(SkeletonCreeper::new, MobCategory.MONSTER).sized(0.6F, 1.7F).build(TheGraveyard.MOD_ID + ":skeleton_creeper"));
    public static final EntityType<AcolyteEntity> ACOLYTE = createEntity("acolyte", EntityType.Builder.of(AcolyteEntity::new, MobCategory.MONSTER).sized(0.6F, 1.9F).build(TheGraveyard.MOD_ID + ":acolyte"));
    public static final EntityType<ReaperEntity> REAPER = createEntity("reaper", EntityType.Builder.of(ReaperEntity::new, MobCategory.MONSTER).sized(0.5F, 1.4F).build(TheGraveyard.MOD_ID + ":reaper"));
    public static final EntityType<BaseGhoulEntity> GHOUL = createEntity("ghoul", EntityType.Builder.of(BaseGhoulEntity::new, MobCategory.MONSTER).sized(0.8F, 2.1F).build(TheGraveyard.MOD_ID + ":ghoul")); // if hitbox is set to 0.9 the ghoul wont move

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


    static {
        SpawnPlacements.register(SKELETON_CREEPER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(GHOUL, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(ACOLYTE, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules);
        SpawnPlacements.register(REAPER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
    }








}
