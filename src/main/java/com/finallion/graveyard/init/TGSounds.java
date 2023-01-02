package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TGSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TheGraveyard.MOD_ID);

    public static final RegistryObject<SoundEvent> GHOUL_ROAR = build("entity.ghoul.roar");
    public static final RegistryObject<SoundEvent> ALTAR_AMBIENT = build("block.altar.ambient");
    public static final RegistryObject<SoundEvent> BONE_PLACED = build("block.bone.placed");
    public static final RegistryObject<SoundEvent> BONE_AMBIENT = build("block.bone.ambient");
    public static final RegistryObject<SoundEvent> VIAL_SPLASH = build("item.vial.splash");

    public static final RegistryObject<SoundEvent> LICH_SPAWN = build("entity.lich.spawn");
    public static final RegistryObject<SoundEvent> LICH_MELEE = build("entity.lich.melee");
    public static final RegistryObject<SoundEvent> LICH_PHASE_02 = build("entity.lich.phase_two");
    public static final RegistryObject<SoundEvent> LICH_PHASE_03 = build("entity.lich.phase_three");
    public static final RegistryObject<SoundEvent> LICH_CORPSE_SPELL = build("entity.lich.corpse_spell");
    public static final RegistryObject<SoundEvent> LICH_CAST_SKULL = build("entity.lich.shoot_skull");
    public static final RegistryObject<SoundEvent> LICH_CAST_LEVITATION = build("entity.lich.levitation");
    public static final RegistryObject<SoundEvent> LICH_CAST_TELEPORT = build("entity.lich.heal");
    public static final RegistryObject<SoundEvent> LICH_DEATH = build("entity.lich.death");
    public static final RegistryObject<SoundEvent> LICH_HUNT = build("entity.lich.hunt");
    public static final RegistryObject<SoundEvent> LICH_SCARE = build("entity.lich.scare");
    public static final RegistryObject<SoundEvent> LICH_PHASE_03_ATTACK = build("entity.lich.phase_three_attack");
    public static final RegistryObject<SoundEvent> LICH_IDLE = build("entity.lich.idle");
    public static final RegistryObject<SoundEvent> LICH_HURT = build("entity.lich.hurt");

    private static RegistryObject<SoundEvent> build(String id) {
        return SOUNDS.register(id, () -> SoundEvent.m_262824_(new ResourceLocation(TheGraveyard.MOD_ID, id)));
    }


    public static void init() {
    }

}
