package com.finallion.graveyard.entites;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import com.finallion.graveyard.entites.ReaperEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class ReaperEntity extends MonsterEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private final AnimationBuilder DEATH_ANIMATION = new AnimationBuilder().addAnimation("death", false);
    private final AnimationBuilder IDLE_ANIMATION = new AnimationBuilder().addAnimation("idle", true);
    private final AnimationBuilder WALK_ANIMATION = new AnimationBuilder().addAnimation("walk", true);
    private final AnimationBuilder SPAWN_ANIMATION = new AnimationBuilder().addAnimation("spawn", false);
    private final AnimationBuilder ATTACK_ANIMATION = new AnimationBuilder().addAnimation("attack", true);
    protected final byte ANIMATION_IDLE = 0;
    protected final byte ANIMATION_WALK = 1;
    protected final byte ANIMATION_SPAWN = 2;
    protected final byte ANIMATION_DEATH = 3;
    protected final byte ANIMATION_ATTACK = 4;
    protected static final DataParameter<Byte> ANIMATION = EntityDataManager.defineId(ReaperEntity.class, DataSerializers.BYTE);
    protected static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.defineId(ReaperEntity.class, DataSerializers.BYTE);
    private BlockPos boundOrigin;
    MobEntity owner;
    @Nullable
    private BlockPos bounds;

    public ReaperEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new ReaperEntity.MoveHelperController(this);
    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
        this.entityData.define(ANIMATION, ANIMATION_IDLE);
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        if (p_70037_1_.contains("BoundX")) {
            this.boundOrigin = new BlockPos(p_70037_1_.getInt("BoundX"), p_70037_1_.getInt("BoundY"), p_70037_1_.getInt("BoundZ"));
        }
    }

    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        if (this.boundOrigin != null) {
            p_213281_1_.putInt("BoundX", this.boundOrigin.getX());
            p_213281_1_.putInt("BoundY", this.boundOrigin.getY());
            p_213281_1_.putInt("BoundZ", this.boundOrigin.getZ());
        }

    }


    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(4, new ReaperEntity.ChargeAttackGoal());
        this.goalSelector.addGoal(8, new ReaperEntity.MoveRandomGoal());
        this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, AbstractRaiderEntity.class)).setAlertOthers());
        this.targetSelector.addGoal(2, new ReaperEntity.CopyOwnerTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }


    public void move(MoverType p_213315_1_, Vector3d p_213315_2_) {
        super.move(p_213315_1_, p_213315_2_);
        this.checkInsideBlocks();
    }

    public void tick() {
        this.noPhysics = true;
        super.tick();
        this.noPhysics = false;
        this.setNoGravity(true);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        setAnimation(ANIMATION_SPAWN);
        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
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

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.ATTACK_DAMAGE, 4.0D).add(Attributes.MAX_HEALTH, 20.0D);
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
        this.playSound(SoundEvents.VEX_AMBIENT, 1.0F, -10.0F);
    }


    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        this.playSound(SoundEvents.VEX_HURT, 1.0F, -10.0F);
        return super.getHurtSound(p_184601_1_);
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        this.playSound(SoundEvents.VEX_DEATH, 1.0F, -10.0F);
        return super.getDeathSound();
    }

    class CopyOwnerTargetGoal extends TargetGoal {
        private final EntityPredicate copyOwnerTargeting = (new EntityPredicate()).allowUnseeable().ignoreInvisibilityTesting();

        public CopyOwnerTargetGoal(CreatureEntity p_i47231_2_) {
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

    class MoveHelperController extends MovementController {
        public MoveHelperController(ReaperEntity p_i47230_2_) {
            super(p_i47230_2_);
        }

        public void tick() {
            if (this.operation == MovementController.Action.MOVE_TO) {
                Vector3d vector3d = new Vector3d(this.wantedX - ReaperEntity.this.getX(), this.wantedY - ReaperEntity.this.getY(), this.wantedZ - ReaperEntity.this.getZ());
                double d0 = vector3d.length();
                if (d0 < ReaperEntity.this.getBoundingBox().getSize()) {
                    this.operation = MovementController.Action.WAIT;
                    ReaperEntity.this.setDeltaMovement(ReaperEntity.this.getDeltaMovement().scale(0.5D));
                } else {
                    ReaperEntity.this.setDeltaMovement(ReaperEntity.this.getDeltaMovement().add(vector3d.scale(this.speedModifier * 0.05D / d0)));
                    if (ReaperEntity.this.getTarget() == null) {
                        Vector3d vector3d1 = ReaperEntity.this.getDeltaMovement();
                        ReaperEntity.this.yRot = -((float)MathHelper.atan2(vector3d1.x, vector3d1.z)) * (180F / (float)Math.PI);
                        ReaperEntity.this.yBodyRot = ReaperEntity.this.yRot;
                    } else {
                        double d2 = ReaperEntity.this.getTarget().getX() - ReaperEntity.this.getX();
                        double d1 = ReaperEntity.this.getTarget().getZ() - ReaperEntity.this.getZ();
                        ReaperEntity.this.yRot = -((float)MathHelper.atan2(d2, d1)) * (180F / (float)Math.PI);
                        ReaperEntity.this.yBodyRot = ReaperEntity.this.yRot;
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
            Vector3d vector3d = livingentity.getEyePosition(1.0F);
            ReaperEntity.this.moveControl.setWantedPosition(vector3d.x, vector3d.y, vector3d.z, 1.0D);
            ReaperEntity.this.setIsCharging(true);
            ReaperEntity.this.playSound(SoundEvents.VEX_CHARGE, 1.0F, 1.0F);
        }

        public void stop() {
            ReaperEntity.this.setIsCharging(false);
        }

        public void tick() {
            LivingEntity livingentity = ReaperEntity.this.getTarget();
            if (ReaperEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
                ReaperEntity.this.doHurtTarget(livingentity);
                ReaperEntity.this.setIsCharging(false);
            } else {
                double d0 = ReaperEntity.this.distanceToSqr(livingentity);
                if (d0 < 9.0D) {
                    Vector3d vector3d = livingentity.getEyePosition(1.0F);
                    ReaperEntity.this.moveControl.setWantedPosition(vector3d.x, vector3d.y, vector3d.z, 1.0D);
                }
            }

        }
    }



}
