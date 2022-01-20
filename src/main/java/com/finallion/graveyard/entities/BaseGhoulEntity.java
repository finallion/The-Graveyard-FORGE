package com.finallion.graveyard.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class BaseGhoulEntity extends AnimatedGraveyardEntity implements IAnimatable {
    private final AnimationBuilder DEATH_ANIMATION = new AnimationBuilder().addAnimation("death", false);
    private final AnimationBuilder IDLE_ANIMATION = new AnimationBuilder().addAnimation("idle", true);
    private final AnimationBuilder WALK_ANIMATION = new AnimationBuilder().addAnimation("walk", true);
    private final AnimationBuilder RAGE_ANIMATION = new AnimationBuilder().addAnimation("rage", false);
    private final AnimationBuilder RUNNING_ANIMATION = new AnimationBuilder().addAnimation("running", true);
    private final AnimationBuilder ATTACK_ANIMATION = new AnimationBuilder().addAnimation("attack", true);
    protected static final byte ANIMATION_IDLE = 0;
    protected static final byte ANIMATION_WALK = 1;
    protected static final byte ANIMATION_RAGE = 2;
    protected static final byte ANIMATION_DEATH = 3;
    protected static final byte ANIMATION_RUNNING = 4;
    protected static final byte ANIMATION_ATTACK = 5;
    protected static final EntityDataAccessor<Byte> VARIANT = SynchedEntityData.defineId(BaseGhoulEntity.class, EntityDataSerializers.BYTE);
    private AnimationFactory factory = new AnimationFactory(this);
    private static boolean canAttack = false;
    private static boolean canRage = false;
    private static int timeSinceLastAttack = 0;
    private static final double ATTACK_RANGE = 4.5D;


    public BaseGhoulEntity(EntityType<? extends BaseGhoulEntity> entityType, Level world) {
        super(entityType, world);
    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        byte variant = (byte) random.nextInt(8);
        this.entityData.define(VARIANT, variant);

    }


    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));;
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(BaseGhoulEntity.class));
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.155D)
                .add(Attributes.FOLLOW_RANGE, 30.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.ARMOR, 3.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putByte("ghoulVariant", getVariant());
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setVariant(tag.getByte("ghoulVariant"));
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    public double getMeleeAttackRangeSqr(LivingEntity p_147273_) {
        return ATTACK_RANGE;
    }

    private void stopAttackAnimation() {
        if (!isCreepy()) {
            setState(ANIMATION_RAGE);
        }
    }

    @Override
    public void aiStep() {
        // stops attack animation when anger time is 0 and sets rage animation to play
        stopAttackAnimation();

        timeSinceLastAttack--;

        if (!this.level.isClientSide()) {
            if (this.getTarget() != null) {
                canAttack = this.getTarget().distanceToSqr(this) <= ATTACK_RANGE;
                canRage = this.getTarget().distanceToSqr(this) > ATTACK_RANGE * 6;
            }
        }


        super.aiStep();
    }

    private <E extends IAnimatable> PlayState predicate2(AnimationEvent<E> event) {
        if (isAggressive() && canAttack && !(this.isDeadOrDying() || this.getHealth() < 0.01)) {
            timeSinceLastAttack = 25;
            canAttack = false;
            event.getController().setAnimation(ATTACK_ANIMATION);
            return PlayState.CONTINUE;
        }

        if (getAnimationState() == ANIMATION_RAGE  && !(this.isDeadOrDying() || this.getHealth() < 0.01) && canRage) {
            event.getController().setAnimation(RAGE_ANIMATION);
            return PlayState.CONTINUE;
        }

        if (timeSinceLastAttack < 0) {
            return PlayState.STOP;
        }

        return PlayState.CONTINUE;
    }



    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        float limbSwingAmount = event.getLimbSwingAmount();
        boolean isMoving = !(limbSwingAmount > -0.05F && limbSwingAmount < 0.05F);

        if (isDeadOrDying()) {
            event.getController().setAnimation(DEATH_ANIMATION);
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            if (isInWater()) {
                event.getController().setAnimation(WALK_ANIMATION);
            } else if (isAggressive() && timeSinceLastAttack < 0) {
                event.getController().setAnimation(RUNNING_ANIMATION);
            } else if (timeSinceLastAttack < 0) {
                event.getController().setAnimation(WALK_ANIMATION);
            }
        } else if (timeSinceLastAttack < 0) {
            event.getController().setAnimation(IDLE_ANIMATION);
        }
        return PlayState.CONTINUE;

    }


    public byte getVariant() {
        return entityData.get(VARIANT);
    }

    public void setVariant(byte variant) {
        entityData.set(VARIANT, variant);
    }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 3, this::predicate));
        data.addAnimationController(new AnimationController(this, "controller2", 3, this::predicate2));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public boolean canBeAffected(MobEffectInstance p_34192_) {
        return p_34192_.getEffect() == MobEffects.WITHER ? false : super.canBeAffected(p_34192_);
    }

    @Override
    public void playAmbientSound() {
        this.playSound(SoundEvents.HUSK_AMBIENT, 1.0F, -5.0F);
    }


    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        this.playSound(SoundEvents.HUSK_HURT, 1.0F, -5.0F);
        return super.getHurtSound(p_184601_1_);
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        this.playSound(SoundEvents.HUSK_DEATH, 1.0F, -5.0F);
        return super.getDeathSound();
    }

}
