package com.finallion.graveyard.entities;

import com.finallion.graveyard.config.GraveyardConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class HostileGraveyardEntity extends Monster {
    private String name;
    private static final EntityDataAccessor<Boolean> CAN_BURN_IN_SUNLIGHT;

    public HostileGraveyardEntity(EntityType<? extends Monster> entityType, Level world, String name) {
        super(entityType, world);
        this.name = name;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(CAN_BURN_IN_SUNLIGHT, true);
        super.defineSynchedData();
    }

    @Override
    protected boolean isSunBurnTick() {
        return super.isSunBurnTick();
    }

    protected boolean isSunSensitive() {
        return true;
    }

    private boolean canBurnInSunlight() {
        return entityData.get(CAN_BURN_IN_SUNLIGHT);
    }

    public void setCanBurnInSunlight(boolean bool) {
        entityData.set(CAN_BURN_IN_SUNLIGHT, bool);
    }

    // TODO: do mob config and access like structure config
    private boolean getSunBurnConfigBoolean(String name) {
        switch (name) {
            case ("acolyte") -> {return GraveyardConfig.COMMON.acolyteCanBurnInSunlight.get();}
            case ("ghoul") -> {return GraveyardConfig.COMMON.ghoulCanBurnInSunlight.get();}
            case ("reaper") -> {return GraveyardConfig.COMMON.reaperCanBurnInSunlight.get();}
            case ("revenant") -> {return GraveyardConfig.COMMON.revenantCanBurnInSunlight.get();}
            case ("skeleton_creeper") -> {return GraveyardConfig.COMMON.skeletonCreeperCanBurnInSunlight.get();}
            case ("wraith") -> {return GraveyardConfig.COMMON.wraithCanBurnInSunlight.get();}
            case ("nightmare") -> {return GraveyardConfig.COMMON.nightmareCanBurnInSunlight.get();}
            case ("corrupted_pillager") -> {return GraveyardConfig.COMMON.corruptedPillagerCanBurnInSunlight.get();}
            case ("corrupted_vindicator") -> {return GraveyardConfig.COMMON.corruptedVindicatorCanBurnInSunlight.get();}
            default -> {return false;}
        }
    }

    private boolean getWitherConfigBoolean(String name) {
        switch (name) {
            case ("acolyte") -> {return GraveyardConfig.COMMON.acolyteCanBeWithered.get();}
            case ("ghoul") -> {return GraveyardConfig.COMMON.ghoulCanBeWithered.get();}
            case ("reaper") -> {return GraveyardConfig.COMMON.reaperCanBeWithered.get();}
            case ("revenant") -> {return GraveyardConfig.COMMON.revenantCanBeWithered.get();}
            case ("skeleton_creeper") -> {return GraveyardConfig.COMMON.skeletonCreeperCanBeWithered.get();}
            case ("wraith") -> {return GraveyardConfig.COMMON.wraithCanBeWithered.get();}
            case ("nightmare") -> {return GraveyardConfig.COMMON.nightmareCanBeWithered.get();}
            case ("corrupted_pillager") -> {return GraveyardConfig.COMMON.corruptedPillagerCanBeWithered.get();}
            case ("corrupted_vindicator") -> {return GraveyardConfig.COMMON.corruptedVindicatorCanBeWithered.get();}
            default -> {return false;}
        }
    }


    public boolean canBeAffected(MobEffectInstance effect) {
        if (effect.getEffect() == MobEffects.WITHER) {
            if (getWitherConfigBoolean(name)) {
                return true;
            } else {
                return false;
            }
        }

        return super.canBeAffected(effect);
    }

    // on game stop
    public void addAdditionalSaveData(CompoundTag nbt) {
        nbt.putBoolean("canBurn", canBurnInSunlight());
        super.addAdditionalSaveData(nbt);
    }

    // on game load
    public void readAdditionalSaveData(CompoundTag nbt) {
        if (!nbt.contains("canBurn")) {
            this.setCanBurnInSunlight(canBurnInSunlight());
        } else {
            this.setCanBurnInSunlight(nbt.getBoolean("canBurn"));
        }

        super.readAdditionalSaveData(nbt);
    }



    @Override
    public void aiStep() {
        if (this.isAlive()) {
            boolean flag = this.isSunSensitive() && this.isSunBurnTick() && getSunBurnConfigBoolean(name) && canBurnInSunlight();
            if (flag) {
                ItemStack itemstack = this.getItemBySlot(EquipmentSlot.HEAD);
                if (!itemstack.isEmpty()) {
                    if (itemstack.isDamageableItem()) {
                        itemstack.setDamageValue(itemstack.getDamageValue() + this.random.nextInt(2));
                        if (itemstack.getDamageValue() >= itemstack.getMaxDamage()) {
                            this.broadcastBreakEvent(EquipmentSlot.HEAD);
                            this.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                        }
                    }

                    flag = false;
                }

                if (flag) {
                    this.setSecondsOnFire(8);
                }
            }
        }

        super.aiStep();
    }


    static {
        CAN_BURN_IN_SUNLIGHT = SynchedEntityData.defineId(HostileGraveyardEntity.class, EntityDataSerializers.BOOLEAN);
    }




}
