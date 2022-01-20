package com.finallion.graveyard.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;
import java.util.UUID;
import java.util.function.Predicate;

public class NightmareEntity extends Monster implements IAnimatable, NeutralMob {
    private AnimationFactory factory = new AnimationFactory(this);
    private final AnimationBuilder DEATH_ANIMATION = new AnimationBuilder().addAnimation("death", false);
    private final AnimationBuilder IDLE_ANIMATION = new AnimationBuilder().addAnimation("idle", true);
    private final AnimationBuilder WALK_ANIMATION = new AnimationBuilder().addAnimation("walk", true);
    private final AnimationBuilder ATTACK_ANIMATION = new AnimationBuilder().addAnimation("attack", true);
    protected static final byte ANIMATION_IDLE = 0;
    protected static final byte ANIMATION_WALK = 1;
    protected static final byte ANIMATION_DEATH = 3;
    protected static final byte ANIMATION_ATTACK = 5;
    protected static final EntityDataAccessor<Byte> ANIMATION = SynchedEntityData.defineId(NightmareEntity.class, EntityDataSerializers.BYTE);
    private static boolean isInRange = false;
    private static final double ATTACK_RANGE = 5.5D;

    private static final EntityDataAccessor<Boolean> DATA_CREEPY = SynchedEntityData.defineId(NightmareEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_STARED_AT = SynchedEntityData.defineId(NightmareEntity.class, EntityDataSerializers.BOOLEAN);
    private int targetChangeTime;
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private int remainingPersistentAngerTime;
    @Nullable
    private UUID persistentAngerTarget;

    public NightmareEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
        this.maxUpStep = 1.0F;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_CREEPY, false);
        this.entityData.define(DATA_STARED_AT, false);
        this.entityData.define(ANIMATION, ANIMATION_IDLE);
    }

    public int getAnimationState() {
        return this.entityData.get(ANIMATION);
    }

    public void setState(byte time) {
        this.entityData.set(ANIMATION, time);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new ChasePlayerGoal(this));
        this.goalSelector.addGoal(2, new NightmareMeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new TeleportTowardsPlayerGoal(this, this::isAngryAt));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
    }


    public void aiStep() {
        // hinders attack animation from playing when there is no target
        isInAttackDistance();
        // stops attack animation when anger time is 0 and sets idle animation to play
        stopAttackAnimation();


        this.jumping = false;
        if (!this.level.isClientSide) {
            if (this.getTarget() != null) {
                if (this.getTarget().distanceToSqr(this) >= 1024.0D) {
                    stopBeingAngry();
                }
            }
            this.updatePersistentAnger((ServerLevel)this.level, true);
        }

        super.aiStep();
    }

    public void addAdditionalSaveData(CompoundTag p_32520_) {
        super.addAdditionalSaveData(p_32520_);
        this.addPersistentAngerSaveData(p_32520_);
    }

    public void readAdditionalSaveData(CompoundTag p_32511_) {
        super.readAdditionalSaveData(p_32511_);
        this.readPersistentAngerSaveData(this.level, p_32511_);
    }


    private void isInAttackDistance() {
        if (this.getTarget() != null) {
            if (this.getTarget().distanceToSqr(this) > ATTACK_RANGE) {
                isInRange = false;
                setState(ANIMATION_IDLE);
            }
        }
    }

    private void stopAttackAnimation() {
        if (!this.isCreepy()) {
            setState(ANIMATION_IDLE);
        }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        float limbSwingAmount = event.getLimbSwingAmount();
        boolean isMoving = !(limbSwingAmount > -0.05F && limbSwingAmount < 0.05F);

        if (isDeadOrDying()) {
            event.getController().setAnimation(DEATH_ANIMATION);
            return PlayState.CONTINUE;
        }

        if (getAnimationState() == ANIMATION_ATTACK && !(this.isDeadOrDying() || this.getHealth() < 0.01)) {
            event.getController().setAnimation(ATTACK_ANIMATION);
            return PlayState.CONTINUE;
        }

        if (event.isMoving() || isMoving) {
            event.getController().setAnimation(WALK_ANIMATION);
        } else {
            event.getController().setAnimation(IDLE_ANIMATION);
        }
        return PlayState.CONTINUE;

    }


    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 50.0D).add(Attributes.ATTACK_DAMAGE, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.19D).add(Attributes.FOLLOW_RANGE, 64.0D);
    }

    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 28 && !this.level.isClientSide()) {
            this.level.broadcastEntityEvent(this, (byte)60);
            this.remove(Entity.RemovalReason.KILLED);
        }
    }

    public boolean hurt(DamageSource p_32494_, float p_32495_) {
        if (this.isInvulnerableTo(p_32494_)) {
            return false;
        } else if (p_32494_ instanceof IndirectEntityDamageSource) {
            return false;
        } else {
            return super.hurt(p_32494_, p_32495_);
        }
    }


    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 2, this::predicate));
    }


    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }


    @Override
    public void playAmbientSound() {
        this.playSound(SoundEvents.ENDERMAN_AMBIENT, 1.0F, -10.0F);
    }

    @Override
    protected void playHurtSound(DamageSource source) {
        this.playSound(SoundEvents.ENDERMAN_HURT, 1.0F, -10.0F);
    }

    @Override
    public void die(DamageSource p_21014_) {
        super.die(p_21014_);
        this.playSound(SoundEvents.ENDERMAN_DEATH, 1.0F, -10.0F);
    }

    boolean isLookingAtMe(Player p_32535_) {
        ItemStack itemstack = p_32535_.getInventory().armor.get(3);
        if (itemstack.is(Blocks.CARVED_PUMPKIN.asItem())) {
            return false;
        } else {
            Vec3 vec3 = p_32535_.getViewVector(1.0F).normalize();
            Vec3 vec31 = new Vec3(this.getX() - p_32535_.getX(), this.getEyeY() - p_32535_.getEyeY(), this.getZ() - p_32535_.getZ());
            double d0 = vec31.length();
            vec31 = vec31.normalize();
            double d1 = vec3.dot(vec31);

            if (d1 > 1.0D - 0.025D / d0) {
                return p_32535_.hasLineOfSight(this);
            } else {
                return false;
            }

        }
    }


    protected float getStandingEyeHeight(Pose p_32517_, EntityDimensions p_32518_) {
        return 2.55F;
    }

    boolean teleportTowards(Entity entity) {
        Vec3 vec3 = new Vec3(this.getX() - entity.getX(), this.getY(0.5D) - entity.getEyeY(), this.getZ() - entity.getZ());
        vec3 = vec3.normalize();
        double d0 = 16.0D;
        double d1 = this.getX() + (this.random.nextDouble() - 0.5D) * 2.0D - vec3.x * 16.0D;
        double d2 = this.getY() + (double)(this.random.nextInt(4) - 2) - vec3.y * 16.0D;
        double d3 = this.getZ() + (this.random.nextDouble() - 0.5D) * 2.0D - vec3.z * 16.0D;
        return this.teleport(d1, d2, d3);
    }

    private boolean teleport(double p_32544_, double p_32545_, double p_32546_) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(p_32544_, p_32545_, p_32546_);

        while(blockpos$mutableblockpos.getY() > this.level.getMinBuildHeight() && !this.level.getBlockState(blockpos$mutableblockpos).getMaterial().blocksMotion()) {
            blockpos$mutableblockpos.move(Direction.DOWN);
        }

        BlockState blockstate = this.level.getBlockState(blockpos$mutableblockpos);
        boolean flag = blockstate.getMaterial().blocksMotion();
        boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
        if (flag && !flag1) {
            net.minecraftforge.event.entity.EntityTeleportEvent.EnderEntity event = net.minecraftforge.event.ForgeEventFactory.onEnderTeleport(this, p_32544_, p_32545_, p_32546_);
            if (event.isCanceled()) return false;
            boolean flag2 = this.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), false);
            if (flag2 && !this.isSilent()) {
                this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, -10.0F);
            }

            return flag2;
        } else {
            return false;
        }
    }

    public boolean isCreepy() {
        return this.entityData.get(DATA_CREEPY);
    }

    public boolean hasBeenStaredAt() {
        return this.entityData.get(DATA_STARED_AT);
    }

    public void setBeingStaredAt() {
        this.entityData.set(DATA_STARED_AT, true);
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    public void setRemainingPersistentAngerTime(int p_32515_) {
        this.remainingPersistentAngerTime = p_32515_;
    }

    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    public void setPersistentAngerTarget(@javax.annotation.Nullable UUID p_32509_) {
        this.persistentAngerTarget = p_32509_;
    }

    @javax.annotation.Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    public void setTarget(@javax.annotation.Nullable LivingEntity p_32537_) {
        if (p_32537_ == null) {
            this.targetChangeTime = 0;
            this.entityData.set(DATA_CREEPY, false);
            this.entityData.set(DATA_STARED_AT, false);
        } else {
            this.targetChangeTime = this.tickCount;
            this.entityData.set(DATA_CREEPY, true);
        }

        super.setTarget(p_32537_);
    }


    static class NightmareMeleeAttackGoal extends MeleeAttackGoal {
        private final NightmareEntity nightmare;
        private static final int ATTACK_DURATION = 20;
        private int attackTimer = 0;

        public NightmareMeleeAttackGoal(NightmareEntity nightmareEntity, double speed, boolean pauseWhenIdle) {
            super(nightmareEntity, speed, pauseWhenIdle);
            this.nightmare = nightmareEntity;
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
            double d = ATTACK_RANGE;

            if (squaredDistance <= d && attackTimer <= 0) {
                nightmare.setState(ANIMATION_ATTACK);
                attackTimer = ATTACK_DURATION;
                this.mob.doHurtTarget(target);
                attackTimer--;
            }


            if (attackTimer > 0) {
                attackTimer--;

                if (attackTimer == 0) {
                    nightmare.setState(ANIMATION_WALK);
                }
            }

        }

        @Override
        public void stop() {
            super.stop();
            attackTimer = 0;
        }
    }

    static class ChasePlayerGoal extends Goal {
        private final NightmareEntity nightmare;
        @javax.annotation.Nullable
        private LivingEntity target;

        public ChasePlayerGoal(NightmareEntity p_32550_) {
            this.nightmare = p_32550_;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        public boolean canUse() {
            this.target = this.nightmare.getTarget();
            if (!(this.target instanceof Player)) {
                return false;
            } else {
                double d0 = this.target.distanceToSqr(this.nightmare);
                return d0 > 256.0D ? false : this.nightmare.isLookingAtMe((Player)this.target);
            }
        }

        public void start() {
            this.nightmare.getNavigation().stop();
        }

        public void tick() {
            this.nightmare.getLookControl().setLookAt(this.target.getX(), this.target.getEyeY(), this.target.getZ());
        }
    }

    static class TeleportTowardsPlayerGoal extends NearestAttackableTargetGoal<Player> {
        private final NightmareEntity nightmare;
        @javax.annotation.Nullable
        private Player pendingTarget;
        private int aggroTime;
        private int teleportTime;
        private final TargetingConditions startAggroTargetConditions;
        private final TargetingConditions continueAggroTargetConditions = TargetingConditions.forCombat().ignoreLineOfSight();

        public TeleportTowardsPlayerGoal(NightmareEntity p_32573_, @javax.annotation.Nullable Predicate<LivingEntity> p_32574_) {
            super(p_32573_, Player.class, 10, false, false, p_32574_);
            this.nightmare = p_32573_;
            this.startAggroTargetConditions = TargetingConditions.forCombat().range(this.getFollowDistance()).selector((p_32578_) -> {
                return p_32573_.isLookingAtMe((Player)p_32578_);
            });
        }

        public boolean canUse() {
            this.pendingTarget = this.nightmare.level.getNearestPlayer(this.startAggroTargetConditions, this.nightmare);
            return this.pendingTarget != null;
        }

        public void start() {
            this.aggroTime = this.adjustedTickDelay(5);
            this.teleportTime = 0;
            this.nightmare.setBeingStaredAt();
        }

        public void stop() {
            this.pendingTarget = null;
            super.stop();
        }

        public boolean canContinueToUse() {
            if (this.pendingTarget != null) {
                if (!this.nightmare.isLookingAtMe(this.pendingTarget)) {
                    return false;
                } else {
                    this.nightmare.lookAt(this.pendingTarget, 10.0F, 10.0F);
                    return true;
                }
            } else {
                return this.target != null && this.continueAggroTargetConditions.test(this.nightmare, this.target) ? true : super.canContinueToUse();
            }
        }

        public void tick() {
            if (this.nightmare.getTarget() == null) {
                super.setTarget((LivingEntity)null);
            }

            if (this.pendingTarget != null) {
                if (--this.aggroTime <= 0) {
                    this.target = this.pendingTarget;
                    this.pendingTarget = null;
                    super.start();
                }
            } else {
                if (this.target != null && !this.nightmare.isPassenger()) {
                    if (this.nightmare.isLookingAtMe((Player)this.target)) {
                        if (this.target.distanceToSqr(this.nightmare) <= 384.0D && target != null) {
                            if (this.target.distanceToSqr(this.nightmare) > 24.0D) {
                                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100));
                                this.nightmare.teleportTowards(target);
                            }
                        }

                        this.teleportTime = 0;
                    } else if (this.target.distanceToSqr(this.nightmare) > 256.0D && this.teleportTime++ >= this.adjustedTickDelay(80) && this.nightmare.teleportTowards(this.target)) {
                        this.teleportTime = 0;
                    }
                }

                super.tick();
            }

        }
    }


}
