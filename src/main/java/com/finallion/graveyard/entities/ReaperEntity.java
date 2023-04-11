package com.finallion.graveyard.entities;


import com.finallion.graveyard.init.TGSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class ReaperEntity extends HostileGraveyardEntity implements IAnimatable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private final AnimationBuilder DEATH_ANIMATION = new AnimationBuilder().addAnimation("death", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
    private final AnimationBuilder IDLE_ANIMATION = new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP);
    private final AnimationBuilder WALK_ANIMATION = new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP);
    private final AnimationBuilder SPAWN_ANIMATION = new AnimationBuilder().addAnimation("spawn", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
    private final AnimationBuilder ATTACK_ANIMATION = new AnimationBuilder().addAnimation("attack", ILoopType.EDefaultLoopTypes.LOOP);
    protected final byte ANIMATION_IDLE = 0;
    protected final byte ANIMATION_WALK = 1;
    protected final byte ANIMATION_SPAWN = 2;
    protected final byte ANIMATION_DEATH = 3;
    protected final byte ANIMATION_ATTACK = 4;
    protected static final EntityDataAccessor<Byte> ANIMATION = SynchedEntityData.defineId(ReaperEntity.class, EntityDataSerializers.BYTE);
    protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(ReaperEntity.class, EntityDataSerializers.BYTE);
    private BlockPos boundOrigin;
    Mob owner;
    @Nullable
    private BlockPos bounds;

    public ReaperEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world, "reaper");
        this.moveControl = new MoveHelperController(this);
    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
        this.entityData.define(ANIMATION, ANIMATION_IDLE);
    }

    public void readAdditionalSaveData(CompoundTag p_34008_) {
        super.readAdditionalSaveData(p_34008_);
        if (p_34008_.contains("BoundX")) {
            this.boundOrigin = new BlockPos(p_34008_.getInt("BoundX"), p_34008_.getInt("BoundY"), p_34008_.getInt("BoundZ"));
        }

    }

    public void addAdditionalSaveData(CompoundTag p_34015_) {
        super.addAdditionalSaveData(p_34015_);
        if (this.boundOrigin != null) {
            p_34015_.putInt("BoundX", this.boundOrigin.getX());
            p_34015_.putInt("BoundY", this.boundOrigin.getY());
            p_34015_.putInt("BoundZ", this.boundOrigin.getZ());
        }
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new ChargeAttackGoal());
        this.goalSelector.addGoal(8, new MoveRandomGoal());
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
        this.targetSelector.addGoal(2, new CopyOwnerTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public void move(MoverType p_33997_, Vec3 p_33998_) {
        super.move(p_33997_, p_33998_);
        this.checkInsideBlocks();
    }

    public void tick() {
        this.noPhysics = true;
        super.tick();
        this.noPhysics = false;
        this.setNoGravity(true);
    }

    @SuppressWarnings("rawtypes")
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        float limbSwingAmount = event.getLimbSwingAmount();
        boolean isMoving = !(limbSwingAmount > -0.05F && limbSwingAmount < 0.05F);
        boolean isDying = this.isDeadOrDying();
        boolean isAttacking = this.isAggressive();

        if (isDying) {
            controller.setAnimation(DEATH_ANIMATION);
            return PlayState.CONTINUE;
        }

        if (isAttacking || isCharging()) {
            controller.setAnimation(ATTACK_ANIMATION);
            return PlayState.CONTINUE;
        }

        byte currentAnimation = getAnimation();
        switch (currentAnimation) {
            case ANIMATION_ATTACK: controller.setAnimation(ATTACK_ANIMATION); break;
            default: controller.setAnimation(isMoving ? WALK_ANIMATION : IDLE_ANIMATION); break;
        }

        return PlayState.CONTINUE;
    }

    @Nullable
    public BlockPos getBoundOrigin() {
        return this.boundOrigin;
    }

    public void setBoundOrigin(@Nullable BlockPos p_190651_1_) {
        this.boundOrigin = p_190651_1_;
    }


    private boolean getVexFlag(int p_190656_1_) {
        int i = this.entityData.get(DATA_FLAGS_ID);
        return (i & p_190656_1_) != 0;
    }

    private void setVexFlag(int p_190660_1_, boolean p_190660_2_) {
        int i = this.entityData.get(DATA_FLAGS_ID);
        if (p_190660_2_) {
            i = i | p_190660_1_;
        } else {
            i = i & ~p_190660_1_;
        }

        this.entityData.set(DATA_FLAGS_ID, (byte)(i & 255));
    }
    public boolean isCharging() {
        return this.getVexFlag(1);
    }

    public void setIsCharging(boolean p_190648_1_) {
        this.setVexFlag(1, p_190648_1_);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.ATTACK_DAMAGE, 4.0D).add(Attributes.MAX_HEALTH, 20.0D);
    }

    public byte getAnimation() {
        return entityData.get(ANIMATION);
    }

    public void setAnimation(byte animation) {
        entityData.set(ANIMATION, animation);
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
        this.playSound(TGSounds.REAPER_AMBIENT.get(), 1.0F, -10.0F);
    }

    @Override
    protected void playHurtSound(DamageSource source) {
        this.playSound(TGSounds.REAPER_HURT.get(), 1.0F, -10.0F);
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        this.playSound(TGSounds.REAPER_DEATH.get(), 1.0F, -10.0F);
    }


    class CopyOwnerTargetGoal extends TargetGoal {
        private final TargetingConditions copyOwnerTargeting = TargetingConditions.forNonCombat().ignoreLineOfSight().ignoreInvisibilityTesting();

        public CopyOwnerTargetGoal(PathfinderMob p_i47231_2_) {
            super(p_i47231_2_, false);
        }

        public boolean canUse() {
            return ReaperEntity.this.owner != null && ReaperEntity.this.owner.getTarget() != null && this.canAttack(ReaperEntity.this.owner.getTarget(), this.copyOwnerTargeting);
        }

        public void start() {
            ReaperEntity.this.setTarget(ReaperEntity.this.owner.getTarget());
            super.start();
        }
    }


    class MoveHelperController extends MoveControl {
        public MoveHelperController(ReaperEntity p_34062_) {
            super(p_34062_);
        }

        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                Vec3 vec3 = new Vec3(this.wantedX - ReaperEntity.this.getX(), this.wantedY - ReaperEntity.this.getY(), this.wantedZ - ReaperEntity.this.getZ());
                double d0 = vec3.length();
                if (d0 < ReaperEntity.this.getBoundingBox().getSize()) {
                    this.operation = MoveControl.Operation.WAIT;
                    ReaperEntity.this.setDeltaMovement(ReaperEntity.this.getDeltaMovement().scale(0.5D));
                } else {
                    ReaperEntity.this.setDeltaMovement(ReaperEntity.this.getDeltaMovement().add(vec3.scale(this.speedModifier * 0.05D / d0)));
                    if (ReaperEntity.this.getTarget() == null) {
                        Vec3 vec31 = ReaperEntity.this.getDeltaMovement();
                        ReaperEntity.this.setYRot(-((float) Mth.atan2(vec31.x, vec31.z)) * (180F / (float)Math.PI));
                        ReaperEntity.this.yBodyRot = ReaperEntity.this.getYRot();
                    } else {
                        double d2 = ReaperEntity.this.getTarget().getX() - ReaperEntity.this.getX();
                        double d1 = ReaperEntity.this.getTarget().getZ() - ReaperEntity.this.getZ();
                        ReaperEntity.this.setYRot(-((float)Mth.atan2(d2, d1)) * (180F / (float)Math.PI));
                        ReaperEntity.this.yBodyRot = ReaperEntity.this.getYRot();
                    }
                }

            }
        }
    }


    class MoveRandomGoal extends Goal {
        public MoveRandomGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            return !ReaperEntity.this.getMoveControl().hasWanted() && ReaperEntity.this.random.nextInt(7) == 0;
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void tick() {
            BlockPos blockpos = ReaperEntity.this.getBoundOrigin();
            if (blockpos == null) {
                blockpos = ReaperEntity.this.blockPosition();
            }

            for(int i = 0; i < 3; ++i) {
                BlockPos blockpos1 = blockpos.offset(ReaperEntity.this.random.nextInt(15) - 7, ReaperEntity.this.random.nextInt(11) - 5, ReaperEntity.this.random.nextInt(15) - 7);
                if (ReaperEntity.this.level.isEmptyBlock(blockpos1)) {
                    ReaperEntity.this.moveControl.setWantedPosition((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 0.25D);
                    if (ReaperEntity.this.getTarget() == null) {
                        ReaperEntity.this.getLookControl().setLookAt((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                    }
                    break;
                }
            }

        }
    }

    class ChargeAttackGoal extends Goal {
        public ChargeAttackGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            if (ReaperEntity.this.getTarget() != null && !ReaperEntity.this.getMoveControl().hasWanted() && ReaperEntity.this.random.nextInt(7) == 0) {
                return ReaperEntity.this.distanceToSqr(ReaperEntity.this.getTarget()) > 4.0D;
            } else {
                return false;
            }
        }

        public boolean canContinueToUse() {
            return ReaperEntity.this.getMoveControl().hasWanted() && ReaperEntity.this.isCharging() && ReaperEntity.this.getTarget() != null && ReaperEntity.this.getTarget().isAlive();
        }


        public void start() {
            LivingEntity livingentity = ReaperEntity.this.getTarget();
            if (livingentity != null) {
                Vec3 vec3 = livingentity.getEyePosition();
                ReaperEntity.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 1.0D);
            }

            ReaperEntity.this.setIsCharging(true);
            ReaperEntity.this.playSound(SoundEvents.VEX_CHARGE, 1.0F, -10.0F);
        }

        public void stop() {
            ReaperEntity.this.setIsCharging(false);
        }

        public void tick() {
            LivingEntity livingentity = ReaperEntity.this.getTarget();
            if (livingentity != null) {
                if (ReaperEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
                    ReaperEntity.this.doHurtTarget(livingentity);
                    ReaperEntity.this.setIsCharging(false);
                } else {
                    double d0 = ReaperEntity.this.distanceToSqr(livingentity);
                    if (d0 < 9.0D) {
                        Vec3 vec3 = livingentity.getEyePosition();
                        ReaperEntity.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 1.0D);
                    }
                }

            }
        }
    }



}
