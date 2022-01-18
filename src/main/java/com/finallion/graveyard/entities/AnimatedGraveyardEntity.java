package com.finallion.graveyard.entities;

import com.finallion.graveyard.entities.horde.GraveyardHordeEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;


import javax.annotation.Nullable;
import java.util.UUID;

public class AnimatedGraveyardEntity extends GraveyardHordeEntity implements NeutralMob {
    private static final UUID SPEED_MODIFIER_ATTACKING_UUID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final AttributeModifier SPEED_MODIFIER_ATTACKING = new AttributeModifier(SPEED_MODIFIER_ATTACKING_UUID, "Attacking speed boost", (double)0.15F, AttributeModifier.Operation.ADDITION);
    private static final EntityDataAccessor<Boolean> DATA_CREEPY = SynchedEntityData.defineId(AnimatedGraveyardEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_STARED_AT = SynchedEntityData.defineId(AnimatedGraveyardEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Byte> ANIMATION_MOVE_STATE = SynchedEntityData.defineId(AnimatedGraveyardEntity.class, EntityDataSerializers.BYTE);
    private int lastStareSound = Integer.MIN_VALUE;
    private int targetChangeTime;
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private int remainingPersistentAngerTime;
    private UUID persistentAngerTarget;


    protected AnimatedGraveyardEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ANIMATION_MOVE_STATE, (byte) 0);
        this.entityData.define(DATA_CREEPY, false);
        this.entityData.define(DATA_STARED_AT, false);
    }

    public int getAnimationState() {
        return this.entityData.get(ANIMATION_MOVE_STATE);
    }

    public void setState(byte time) {
        this.entityData.set(ANIMATION_MOVE_STATE, time);
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    public void setRemainingPersistentAngerTime(int p_230260_1_) {
        this.remainingPersistentAngerTime = p_230260_1_;
    }

    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    public void setPersistentAngerTarget(@Nullable UUID p_230259_1_) {
        this.persistentAngerTarget = p_230259_1_;
    }

    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }


    public void addAdditionalSaveData(CompoundTag p_32520_) {
        super.addAdditionalSaveData(p_32520_);
        this.addPersistentAngerSaveData(p_32520_);
    }

    public void readAdditionalSaveData(CompoundTag p_32511_) {
        super.readAdditionalSaveData(p_32511_);
        if(!level.isClientSide) {
            this.readPersistentAngerSaveData(this.level, p_32511_);
        }
    }


    public void aiStep() {
        if (!this.level.isClientSide) {
            this.updatePersistentAnger((ServerLevel) this.level, true);
        }

        super.aiStep();
    }

    public boolean isCreepy() {
        return this.entityData.get(DATA_CREEPY);
    }

    @Override
    public boolean isAngry() {
        return this.remainingPersistentAngerTime > 0;
    }


    public void setTarget(@Nullable LivingEntity p_32537_) {
        AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
        if (p_32537_ == null) {
            this.targetChangeTime = 0;
            this.entityData.set(DATA_CREEPY, false);
            this.entityData.set(DATA_STARED_AT, false);
            attributeinstance.removeModifier(SPEED_MODIFIER_ATTACKING);
        } else {
            this.targetChangeTime = this.tickCount;
            this.entityData.set(DATA_CREEPY, true);
            if (!attributeinstance.hasModifier(SPEED_MODIFIER_ATTACKING)) {
                attributeinstance.addTransientModifier(SPEED_MODIFIER_ATTACKING);
            }
        }

        super.setTarget(p_32537_); //Forge: Moved down to allow event handlers to write data manager values.
    }


    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }



}
