package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.*;
import com.finallion.graveyard.entities.projectiles.SkullEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, TheGraveyard.MOD_ID);

    public static final RegistryObject<EntityType<SkeletonCreeper>> SKELETON_CREEPER = ENTITIES.register("skeleton_creeper", () -> EntityType.Builder.of(SkeletonCreeper::new, MobCategory.MONSTER).sized(0.6F, 1.7F).build(TheGraveyard.MOD_ID + ":skeleton_creeper"));
    public static final RegistryObject<EntityType<AcolyteEntity>> ACOLYTE = ENTITIES.register("acolyte", () -> EntityType.Builder.of(AcolyteEntity::new, MobCategory.MONSTER).sized(0.6F, 1.9F).build(TheGraveyard.MOD_ID + ":acolyte"));

    public static final RegistryObject<EntityType<ReaperEntity>> REAPER = ENTITIES.register("reaper", () -> EntityType.Builder.of(ReaperEntity::new, MobCategory.MONSTER).sized(0.5F, 1.4F).build(TheGraveyard.MOD_ID + ":reaper"));
    public static final RegistryObject<EntityType<GhoulEntity>> GHOUL = ENTITIES.register("ghoul", () -> EntityType.Builder.of(GhoulEntity::new, MobCategory.MONSTER).sized(0.8F, 2.1F).build(TheGraveyard.MOD_ID + ":ghoul")); // if hitbox is set to 0.9 the ghoul wont move
    public static final RegistryObject<EntityType<NightmareEntity>> NIGHTMARE = ENTITIES.register("nightmare", () -> EntityType.Builder.of(NightmareEntity::new, MobCategory.MONSTER).sized(0.6F, 2.6F).build(TheGraveyard.MOD_ID + ":nightmare"));
    public static final RegistryObject<EntityType<RevenantEntity>> REVENANT = ENTITIES.register("revenant", () -> EntityType.Builder.of(RevenantEntity::new, MobCategory.MONSTER).sized(0.6F, 1.9F).build(TheGraveyard.MOD_ID + ":revenant"));
    public static final RegistryObject<EntityType<FallingCorpse>> FALLING_CORPSE = ENTITIES.register("falling_corpse", () -> EntityType.Builder.of(FallingCorpse::new, MobCategory.MONSTER).sized(0.4F, 0.5F).build(TheGraveyard.MOD_ID + ":falling_corpse"));
    public static final RegistryObject<EntityType<LichEntity>> LICH = ENTITIES.register("lich", () -> EntityType.Builder.of(LichEntity::new, MobCategory.MONSTER).sized(0.9F, 4.0F).build(TheGraveyard.MOD_ID + ":lich"));

    public static final RegistryObject<EntityType<WraithEntity>> WRAITH = ENTITIES.register("wraith", () -> EntityType.Builder.of(WraithEntity::new, MobCategory.MONSTER).sized(0.6F, 1.8F).build(TheGraveyard.MOD_ID + ":wraith"));
    public static final RegistryObject<EntityType<CorruptedPillager>> CORRUPTED_PILLAGER = ENTITIES.register("corrupted_pillager", () -> EntityType.Builder.of(CorruptedPillager::new, MobCategory.MONSTER).sized(0.6F, 1.9F).build(TheGraveyard.MOD_ID + ":corrupted_pillager"));
    public static final RegistryObject<EntityType<CorruptedVindicator>> CORRUPTED_VINDICATOR = ENTITIES.register("corrupted_vindicator", () -> EntityType.Builder.of(CorruptedVindicator::new, MobCategory.MONSTER).sized(0.6F, 1.9F).build(TheGraveyard.MOD_ID + ":corrupted_vindicator"));

    public static final RegistryObject<EntityType<GhoulingEntity>> GHOULING = ENTITIES.register("ghouling", () -> EntityType.Builder.of(GhoulingEntity::new, MobCategory.CREATURE).sized(0.7F, 2.0F).build(TheGraveyard.MOD_ID + ":ghouling"));
    public static final RegistryObject<EntityType<SkullEntity>> SKULL = ENTITIES.register("skull", () -> EntityType.Builder
            .<SkullEntity>of(SkullEntity::new, MobCategory.MISC)
            .setTrackingRange(4)
            .updateInterval(10)
            .sized(0.25F, 0.25F)
            .build(TheGraveyard.MOD_ID + ":skull"));

    public static final RegistryObject<EntityType<NamelessHangedEntity>> NAMELESS_HANGED = ENTITIES.register("nameless_hanged", () -> EntityType.Builder
            .of(NamelessHangedEntity::new, MobCategory.CREATURE)
            .sized(0.8F, 2.5F)
            .build(TheGraveyard.MOD_ID + ":nameless_hanged"));


    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(TGEntities.SKELETON_CREEPER.get(), SkeletonCreeper.createAttributes().build());
        event.put(TGEntities.ACOLYTE.get(), AcolyteEntity.createAttributes().build());
        event.put(TGEntities.GHOUL.get(), GhoulEntity.createAttributes().build());
        event.put(TGEntities.REAPER.get(), ReaperEntity.createAttributes().build());
        event.put(TGEntities.NIGHTMARE.get(), NightmareEntity.createAttributes().build());
        event.put(TGEntities.REVENANT.get(), RevenantEntity.createAttributes().build());

        event.put(TGEntities.WRAITH.get(), WraithEntity.createAttributes().build());
        event.put(TGEntities.CORRUPTED_PILLAGER.get(), CorruptedPillager.createAttributes().build());
        event.put(TGEntities.CORRUPTED_VINDICATOR.get(), CorruptedVindicator.createAttributes().build());
        event.put(TGEntities.FALLING_CORPSE.get(), FallingCorpse.createAttributes().build());
        event.put(TGEntities.GHOULING.get(), GhoulingEntity.createAttributes().build());
        event.put(TGEntities.LICH.get(), LichEntity.createAttributes().build());
        event.put(TGEntities.NAMELESS_HANGED.get(), NamelessHangedEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        SpawnPlacements.register(TGEntities.SKELETON_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(TGEntities.GHOUL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(TGEntities.ACOLYTE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules);
        SpawnPlacements.register(TGEntities.REAPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(TGEntities.NIGHTMARE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(TGEntities.REVENANT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(TGEntities.WRAITH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(TGEntities.CORRUPTED_PILLAGER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(TGEntities.CORRUPTED_VINDICATOR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(TGEntities.LICH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(TGEntities.FALLING_CORPSE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);

    }

}
