package com.finallion.graveyard.entites;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

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
    protected static final DataParameter<Byte> VARIANT = EntityDataManager.defineId(BaseGhoulEntity.class, DataSerializers.BYTE);
    private AnimationFactory factory = new AnimationFactory(this);
    private static boolean isInRange = false;
    private static final double ATTACK_RANGE = 3.5D;
    private TargetPredicate targetPredicate = TargetPredicate.createAttackable().setBaseMaxDistance(25.0D).ignoreVisibility();

    public BaseGhoulEntity(EntityType<? extends BaseGhoulEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();

        // selects one of four skins for the ghoul
        byte variant = (byte) random.nextInt(8);
        this.dataTracker.startTracking(VARIANT, variant);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new GhoulMeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));;
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(BaseGhoulEntity.class));
    }


    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.195D).add(Attributes.FOLLOW_RANGE, 20.0D).add(Attributes.ATTACK_DAMAGE, 4.0D).add(Attributes.ARMOR, 3.0D).add(Attributes.KNOCKBACK_RESISTANCE, 0.5D);
    }



    public static boolean canSpawn(EntityType<? extends HostileEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return isSpawnDark(world, pos, random);
    }

    public void writeCustomDataToNbt(NbtCompound tag) {
        super.writeCustomDataToNbt(tag);
        tag.putByte("ghoulVariant", getVariant());
    }

    public void readCustomDataFromNbt(NbtCompound tag) {
        super.readCustomDataFromNbt(tag);
        setVariant(tag.getByte("ghoulVariant"));
    }

    private void isInAttackDistance() {
        if (this.getTarget() != null) {
            if (this.getTarget().squaredDistanceTo(this) > ATTACK_RANGE) {
                isInRange = false;
                setState(ANIMATION_RAGE);
            }
        }
    }


    private boolean isInRageDistance() {
        // TODO: clean up!
        Box box = new Box(new BlockPos(this.getX(), this.getY(), this.getZ())).expand(15.0, 5.0, 15.0);
        PlayerEntity player = this.world.getClosestPlayer(targetPredicate, this);
        LivingEntity villager = this.world.getClosestEntity(MerchantEntity.class, targetPredicate, this, this.getX(), this.getY(), this.getZ(), box);
        LivingEntity ironGolem = this.world.getClosestEntity(IronGolemEntity.class, targetPredicate, this, this.getX(), this.getY(), this.getZ(), box);

        // getTarget is useless because it returns sometimes null, even when the mob is tracking
        if (player != null) {
            return !(this.distanceTo(player) < 4.5D);
        } else if (villager != null) {
            return !(this.distanceTo(villager) < 4.5D);
        } else if (ironGolem != null) {
            return !(this.distanceTo(ironGolem) < 4.5D);
        }

        return true;
    }

    private void stopAttackAnimation() {
        if (!this.hasAngerTime()) {
            setState(ANIMATION_RAGE);
        }
    }

    @Override
    public void tickMovement() {
        // hinders attack animation from playing when there is no target
        isInAttackDistance();
        // stops attack animation when anger time is 0 and sets rage animation to play
        stopAttackAnimation();

        super.tickMovement();
    }

    private <E extends IAnimatable> PlayState predicate2(AnimationEvent<E> event) {
        if (getAnimationState() == ANIMATION_ATTACK && !(this.isDead() || this.getHealth() < 0.01) && isInRange) {
            event.getController().setAnimation(ATTACK_ANIMATION);
            return PlayState.CONTINUE;
        }

        if (getAnimationState() == ANIMATION_RAGE  && !(this.isDead() || this.getHealth() < 0.01) && isInRageDistance() && getAnimationState() != ANIMATION_WALK) {
            //this.playSound(SoundEvents.ENTITY_ENDERMAN_SCREAM, 1.0F, -5.0F);
            event.getController().setAnimation(RAGE_ANIMATION);
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        float limbSwingAmount = event.getLimbSwingAmount();
        boolean isMoving = !(limbSwingAmount > -0.05F && limbSwingAmount < 0.05F);

        if (isDead()) {
            event.getController().setAnimation(DEATH_ANIMATION);
            return PlayState.CONTINUE;
        }



        if (event.isMoving() || isMoving) {
            if (isWet()) {
                event.getController().setAnimation(WALK_ANIMATION);
                //setState(ANIMATION_WALK);
            } else if (isAttacking()) {
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
        return dataTracker.get(VARIANT);
    }

    public void setVariant(byte variant) {
        dataTracker.set(VARIANT, variant);
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
        this.playSound(SoundEvents.ENTITY_HUSK_AMBIENT, 1.0F, -5.0F);
    }

    @Override
    protected void playHurtSound(DamageSource source) {
        this.playSound(SoundEvents.ENTITY_HUSK_HURT, 1.0F, -5.0F);
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        this.playSound(SoundEvents.ENTITY_HUSK_DEATH, 1.0F, -5.0F);
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
                double squaredDistance = this.mob.squaredDistanceTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());

                // if target is in range, allow attack animation to actually happen
                isInRange = squaredDistance < ATTACK_RANGE;
            }
            super.tick();
        }

        @Override
        protected void attack(LivingEntity target, double squaredDistance) {
            double d = ATTACK_RANGE;

            if (squaredDistance <= d && attackTimer <= 0) {
                ghoul.setState(ANIMATION_ATTACK);
                attackTimer = ATTACK_DURATION;
                //this.ghoul.playSound(SoundEvents.ENTITY_HUSK_STEP, 1.5F, -5.0F);
                this.mob.tryAttack(target);
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
