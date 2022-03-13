package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.*;
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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, TheGraveyard.MOD_ID);

    public static final RegistryObject<EntityType<SkeletonCreeper>> SKELETON_CREEPER = ENTITIES.register("skeleton_creeper", () -> EntityType.Builder.of(SkeletonCreeper::new, MobCategory.MONSTER).sized(0.6F, 1.7F).build(TheGraveyard.MOD_ID + ":skeleton_creeper"));
    public static final RegistryObject<EntityType<AcolyteEntity>> ACOLYTE = ENTITIES.register("acolyte", () -> EntityType.Builder.of(AcolyteEntity::new, MobCategory.MONSTER).sized(0.6F, 1.9F).build(TheGraveyard.MOD_ID + ":acolyte"));
    public static final RegistryObject<EntityType<ReaperEntity>> REAPER = ENTITIES.register("reaper", () -> EntityType.Builder.of(ReaperEntity::new, MobCategory.MONSTER).sized(0.5F, 1.4F).build(TheGraveyard.MOD_ID + ":reaper"));
    public static final RegistryObject<EntityType<BaseGhoulEntity>> GHOUL = ENTITIES.register("ghoul", () -> EntityType.Builder.of(BaseGhoulEntity::new, MobCategory.MONSTER).sized(0.8F, 2.1F).build(TheGraveyard.MOD_ID + ":ghoul")); // if hitbox is set to 0.9 the ghoul wont move
    public static final RegistryObject<EntityType<NightmareEntity>> NIGHTMARE = ENTITIES.register("nightmare", () -> EntityType.Builder.of(NightmareEntity::new, MobCategory.MONSTER).sized(0.6F, 2.6F).build(TheGraveyard.MOD_ID + ":nightmare"));
    public static final RegistryObject<EntityType<RevenantEntity>> REVENANT = ENTITIES.register("revenant", () -> EntityType.Builder.of(RevenantEntity::new, MobCategory.MONSTER).sized(0.6F, 1.9F).build(TheGraveyard.MOD_ID + ":revenant"));

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(TGEntities.SKELETON_CREEPER.get(), SkeletonCreeper.createAttributes().build());
        event.put(TGEntities.ACOLYTE.get(), AcolyteEntity.createAttributes().build());
        event.put(TGEntities.GHOUL.get(), BaseGhoulEntity.createAttributes().build());
        event.put(TGEntities.REAPER.get(), ReaperEntity.createAttributes().build());
        event.put(TGEntities.NIGHTMARE.get(), NightmareEntity.createAttributes().build());
        event.put(TGEntities.REVENANT.get(), RevenantEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        SpawnPlacements.register(TGEntities.SKELETON_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(TGEntities.GHOUL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(TGEntities.ACOLYTE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules);
        SpawnPlacements.register(TGEntities.REAPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(TGEntities.NIGHTMARE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(TGEntities.REVENANT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
    }

}
