package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TGSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TheGraveyard.MOD_ID);

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

    public static final RegistryObject<SoundEvent> NAMELESS_HANGED_BREATH = build("entity.nameless_hanged.breath");
    public static final RegistryObject<SoundEvent> NAMELESS_HANGED_AMBIENT = build("entity.nameless_hanged.ambient");
    public static final RegistryObject<SoundEvent> NAMELESS_HANGED_INTERACT = build("entity.nameless_hanged.interact");

    public static final RegistryObject<SoundEvent> CORRUPTED_ILLAGER_AMBIENT = build("entity.corrupted_illager.ambient");
    public static final RegistryObject<SoundEvent> CORRUPTED_ILLAGER_HURT = build("entity.corrupted_illager.hurt");
    public static final RegistryObject<SoundEvent> CORRUPTED_ILLAGER_DEATH = build("entity.corrupted_illager.death");
    public static final RegistryObject<SoundEvent> CORRUPTED_ILLAGER_STEP = build("entity.corrupted_illager.step");

    public static final RegistryObject<SoundEvent> ACOLYTE_AMBIENT = build("entity.acolyte.ambient");
    public static final RegistryObject<SoundEvent> ACOLYTE_HURT = build("entity.acolyte.hurt");
    public static final RegistryObject<SoundEvent> ACOLYTE_DEATH = build("entity.acolyte.death");

    public static final RegistryObject<SoundEvent> REAPER_AMBIENT = build("entity.reaper.ambient");
    public static final RegistryObject<SoundEvent> REAPER_HURT = build("entity.reaper.hurt");
    public static final RegistryObject<SoundEvent> REAPER_DEATH = build("entity.reaper.death");
    public static final RegistryObject<SoundEvent> REAPER_CHARGE = build("entity.reaper.charge");

    public static final RegistryObject<SoundEvent> GHOUL_ROAR = build("entity.ghoul.roar");
    public static final RegistryObject<SoundEvent> GHOUL_AMBIENT = build("entity.ghoul.ambient");
    public static final RegistryObject<SoundEvent> GHOUL_HURT = build("entity.ghoul.hurt");
    public static final RegistryObject<SoundEvent> GHOUL_DEATH = build("entity.ghoul.death");
    public static final RegistryObject<SoundEvent> GHOUL_STEP = build("entity.ghoul.step");

    public static final RegistryObject<SoundEvent> GHOULING_GROAN = build("entity.ghouling.groan");
    public static final RegistryObject<SoundEvent> GHOULING_AMBIENT = build("entity.ghouling.ambient");
    public static final RegistryObject<SoundEvent> GHOULING_HURT = build("entity.ghouling.hurt");
    public static final RegistryObject<SoundEvent> GHOULING_DEATH = build("entity.ghouling.death");
    public static final RegistryObject<SoundEvent> GHOULING_ATTACK = build("entity.ghouling.attack");
    public static final RegistryObject<SoundEvent> GHOULING_STEP = build("entity.ghouling.step");
    public static final RegistryObject<SoundEvent> GHOULING_SPAWN = build("entity.ghouling.spawn");

    public static final RegistryObject<SoundEvent> REVENANT_AMBIENT = build("entity.revenant.ambient");
    public static final RegistryObject<SoundEvent> REVENANT_HURT = build("entity.revenant.hurt");
    public static final RegistryObject<SoundEvent> REVENANT_DEATH = build("entity.revenant.death");
    public static final RegistryObject<SoundEvent> REVENANT_STEP = build("entity.revenant.step");

    public static final RegistryObject<SoundEvent> NIGHTMARE_AMBIENT = build("entity.nightmare.ambient");
    public static final RegistryObject<SoundEvent> NIGHTMARE_HURT = build("entity.nightmare.hurt");
    public static final RegistryObject<SoundEvent> NIGHTMARE_DEATH = build("entity.nightmare.death");

    public static final RegistryObject<SoundEvent> WRAITH_AMBIENT = build("entity.wraith.ambient");
    public static final RegistryObject<SoundEvent> WRAITH_HURT = build("entity.wraith.hurt");

    public static final RegistryObject<SoundEvent> ALTAR_AMBIENT = build("block.altar.ambient");

    public static final RegistryObject<SoundEvent> COFFIN_OPEN = build("block.coffin.open");
    public static final RegistryObject<SoundEvent> COFFIN_CLOSE = build("block.coffin.close");

    public static final RegistryObject<SoundEvent> URN_OPEN = build("block.urn.open");
    public static final RegistryObject<SoundEvent> URN_CLOSE = build("block.urn.close");

    public static final RegistryObject<SoundEvent> OSSUARY_OPEN = build("block.ossuary.open");

    public static final RegistryObject<SoundEvent> SARCOPHAGUS_USE = build("block.sarcophagus.use");

    public static final RegistryObject<SoundEvent> BONE_PLACED = build("block.bone.placed");
    public static final RegistryObject<SoundEvent> BONE_AMBIENT = build("block.bone.ambient");


    public static final RegistryObject<SoundEvent> VIAL_SPLASH = build("item.vial.splash");

    public static final RegistryObject<SoundEvent> LICH_THEME_01 = build("entity.lich.theme_01");


    private static RegistryObject<SoundEvent> build(String id) {
        return SOUNDS.register(id, () -> new SoundEvent(new ResourceLocation(TheGraveyard.MOD_ID, id)));
    }


    public static void init() {
    }

}
