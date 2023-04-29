package com.finallion.graveyard.entities;


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
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class AngerableGraveyardEntity extends HordeGraveyardEntity implements NeutralMob {
    private static final UUID SPEED_MODIFIER_ATTACKING_UUID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final AttributeModifier SPEED_MODIFIER_ATTACKING = new AttributeModifier(SPEED_MODIFIER_ATTACKING_UUID, "Attacking speed boost", (double)0.15F, AttributeModifier.Operation.ADDITION);
    private static final EntityDataAccessor<Boolean> ANGRY = SynchedEntityData.defineId(AngerableGraveyardEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> PROVOKED = SynchedEntityData.defineId(AngerableGraveyardEntity.class, EntityDataSerializers.BOOLEAN);
    private static final UniformInt ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private UUID target;
    private int ageWhenTargetSet;
    private int angerTime;

    public AngerableGraveyardEntity(EntityType<? extends Monster> entityType, Level world, String name) {
        super(entityType, world, name);
    }

    public void aiStep() {
        if (!this.level.isClientSide()) {
            this.updatePersistentAnger((ServerLevel) this.level, true);
        }
        super.aiStep();
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ANGRY, false);
        this.entityData.define(PROVOKED, false);
    }

    public void addAdditionalSaveData(CompoundTag p_32520_) {
        super.addAdditionalSaveData(p_32520_);
        this.addPersistentAngerSaveData(p_32520_);
    }

    public void readAdditionalSaveData(CompoundTag p_32511_) {
        super.readAdditionalSaveData(p_32511_);
        this.readPersistentAngerSaveData(this.level, p_32511_);
    }


    public boolean hasAngerTime() {
        return this.entityData.get(ANGRY);
    }

    @Nullable
    public UUID getPersistentAngerTarget() {
        return this.target;
    }

    public void setPersistentAngerTarget(@javax.annotation.Nullable UUID p_32509_) {
        this.target = p_32509_;
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(ANGER_TIME.sample(this.random));
    }


    @Override
    public int getRemainingPersistentAngerTime() {
        return this.angerTime;
    }

    @Override
    public void setRemainingPersistentAngerTime(int p_21673_) {
        this.angerTime = p_21673_;
    }


    public void setTarget(@Nullable LivingEntity target) {
        AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
        if (target == null) {
            this.ageWhenTargetSet = 0;
            this.entityData.set(ANGRY, false);
            this.entityData.set(PROVOKED, false);
            attributeinstance.removeModifier(SPEED_MODIFIER_ATTACKING);
        } else {
            this.ageWhenTargetSet = this.tickCount;
            this.entityData.set(ANGRY, true);
            if (!attributeinstance.hasModifier(SPEED_MODIFIER_ATTACKING)) {
                attributeinstance.addTransientModifier(SPEED_MODIFIER_ATTACKING);
            }
        }
        super.setTarget(target);
    }




}
