package com.finallion.graveyard.entites;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.world.NoteBlockEvent;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;

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
    private static boolean isInRange = false;
    private static final double ATTACK_RANGE = 4.5D;
    //private TargetPredicate targetPredicate = TargetPredicate.createAttackable().setBaseMaxDistance(25.0D).ignoreVisibility();
    private TargetingConditions targetPredicate = TargetingConditions.DEFAULT.range(25.0D).ignoreLineOfSight();

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
        this.goalSelector.addGoal(2, new GhoulMeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));;
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(BaseGhoulEntity.class));
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.195D).add(Attributes.FOLLOW_RANGE, 20.0D).add(Attributes.ATTACK_DAMAGE, 4.0D).add(Attributes.ARMOR, 3.0D).add(Attributes.KNOCKBACK_RESISTANCE, 0.5D);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putByte("ghoulVariant", getVariant());
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setVariant(tag.getByte("ghoulVariant"));
    }

    private void isInAttackDistance() {
        if (this.getTarget() != null) {
            if (this.getTarget().distanceToSqr(this) > ATTACK_RANGE) {
                isInRange = false;
                setState(ANIMATION_RAGE);
            }
        }
    }


    private boolean isInRageDistance() {
        // TODO: clean up!
        AABB box = new AABB(new BlockPos(this.getX(), this.getY(), this.getZ())).inflate(15.0, 5.0, 15.0);
        Player player = this.level.getNearestPlayer(targetPredicate, this);
        LivingEntity villager = this.level.getNearestEntity(AbstractVillager.class, targetPredicate, this, this.getX(), this.getY(), this.getZ(), box);
        LivingEntity ironGolem = this.level.getNearestEntity(IronGolem.class, targetPredicate, this, this.getX(), this.getY(), this.getZ(), box);

        // getTarget is useless because it returns sometimes null, even when the mob is tracking
        if (player != null) {
            return !(this.distanceTo(player) < ATTACK_RANGE);
        } else if (villager != null) {
            return !(this.distanceTo(villager) < ATTACK_RANGE);
        } else if (ironGolem != null) {
            return !(this.distanceTo(ironGolem) < ATTACK_RANGE);
        }

        return true;
    }

    private void stopAttackAnimation() {
        if (!isCreepy()) {
            setState(ANIMATION_RAGE);
        }
    }

    @Override
    public void aiStep() {
        // hinders attack animation from playing when there is no target
        isInAttackDistance();
        // stops attack animation when anger time is 0 and sets rage animation to play
        stopAttackAnimation();

        super.aiStep();
    }

    private <E extends IAnimatable> PlayState predicate2(AnimationEvent<E> event) {
        if (this.entityData.get(ANIMATION_MOVE_STATE) == ANIMATION_ATTACK && !(this.isDeadOrDying() || this.getHealth() < 0.01) && isInRange) {
            event.getController().setAnimation(ATTACK_ANIMATION);
            return PlayState.CONTINUE;
        }

        if (this.entityData.get(ANIMATION_MOVE_STATE) == ANIMATION_RAGE  && !(this.isDeadOrDying() || this.getHealth() < 0.01) && isInRageDistance() && this.entityData.get(ANIMATION_MOVE_STATE) != ANIMATION_WALK) {
            event.getController().setAnimation(RAGE_ANIMATION);
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }



    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        float limbSwingAmount = event.getLimbSwingAmount();
        boolean isMoving = !(limbSwingAmount > -0.05F && limbSwingAmount < 0.05F);

        if (isDeadOrDying()) {
            event.getController().setAnimation(DEATH_ANIMATION);
            return PlayState.CONTINUE;
        }



        if (event.isMoving() || isMoving) {
            if (isInWater()) {
                event.getController().setAnimation(WALK_ANIMATION);
                //setState(ANIMATION_WALK);
            } else if (isAggressive()) {
                event.getController().setAnimation(RUNNING_ANIMATION);
            } else {
                //setState(ANIMATION_WALK);
                event.getController().setAnimation(WALK_ANIMATION);
            }
        } else {
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
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
        data.addAnimationController(new AnimationController(this, "controller2", 0, this::predicate2));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    // TODO: custom sounds
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



    static class GhoulMeleeAttackGoal extends MeleeAttackGoal {
        private final BaseGhoulEntity ghoul;
        private static final int ATTACK_DURATION = 7;
        private int attackTimer = 0;

        public GhoulMeleeAttackGoal(BaseGhoulEntity ghoulEntity, double speed, boolean pauseWhenIdle) {
            super(ghoulEntity, speed, pauseWhenIdle);
            this.ghoul = ghoulEntity;
        }

        @Override
        public void tick() {
            LivingEntity livingEntity = this.mob.getTarget();
            isInRange = false;
            if (livingEntity != null) {
                double squaredDistance = this.mob.distanceToSqr(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());

                // if target is in range, allow attack animation to actually happen
                isInRange = squaredDistance < ATTACK_RANGE;
            }
            super.tick();
        }


        @Override
        protected void checkAndPerformAttack(LivingEntity target, double squaredDistance) {
            if (squaredDistance <= ATTACK_RANGE && attackTimer <= 0) {
                ghoul.setState(ANIMATION_ATTACK);
                attackTimer = ATTACK_DURATION;
                this.mob.doHurtTarget(target);
                attackTimer--;
            }


            if (attackTimer > 0) {
                attackTimer--;

                if (attackTimer == 0) {
                    ghoul.setState(ANIMATION_RUNNING);
                }
            }

        }

        @Override
        public void stop() {
            super.stop();
            attackTimer = 0;
        }
    }

}
