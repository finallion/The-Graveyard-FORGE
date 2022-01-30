package com.finallion.graveyard.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class RevenantEntity extends AnimatedGraveyardEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private final AnimationBuilder DEATH_ANIMATION = new AnimationBuilder().addAnimation("death", false);
    private final AnimationBuilder IDLE_ANIMATION = new AnimationBuilder().addAnimation("idle", true);
    private final AnimationBuilder WALK_ANIMATION = new AnimationBuilder().addAnimation("walk", true);
    private final AnimationBuilder ATTACK_ANIMATION = new AnimationBuilder().addAnimation("attack", true);
    private final AnimationBuilder RUNNING_ANIMATION = new AnimationBuilder().addAnimation("running", true);
    private static final double ATTACK_RANGE = 3.5D;
    private static boolean canAttack = false;
    private static int timeSinceLastAttack = 0;

    public RevenantEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }


    @Override
    public double getMeleeAttackRangeSqr(LivingEntity p_147273_) {
        return ATTACK_RANGE;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.5D)
                .add(Attributes.MOVEMENT_SPEED, 0.155D)
                .add(Attributes.FOLLOW_RANGE, 35.0D);
    }


    @Override
    public void aiStep() {

        timeSinceLastAttack--;

        if (!this.level.isClientSide()) {
            if (this.getTarget() != null) {
                canAttack = this.getTarget().distanceToSqr(this) <= ATTACK_RANGE;
            } else {
                canAttack = false;
            }
        }

        if (this.isAlive()) {
            boolean flag = this.isSunSensitive() && this.isSunBurnTick();
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

    protected boolean isSunSensitive() {
        return true;
    }

    @Override
    protected boolean isSunBurnTick() {
        return super.isSunBurnTick();
    }


    public boolean canBeAffected(MobEffectInstance p_34192_) {
        return p_34192_.getEffect() == MobEffects.WITHER ? false : super.canBeAffected(p_34192_);
    }

    private <E extends IAnimatable> PlayState predicate2(AnimationEvent<E> event) {
        if (isAggressive() && canAttack && !(this.dead || this.getHealth() < 0.01 || this.isDeadOrDying())) {
            timeSinceLastAttack = 6;
            event.getController().setAnimation(ATTACK_ANIMATION);
            return PlayState.CONTINUE;
        }

        // hinders the animation to stop abruptly
        if (timeSinceLastAttack < 0 || !canAttack) {
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

        if (event.isMoving() || isMoving) {
            if (isInWater()) {
                event.getController().setAnimation(WALK_ANIMATION);
            } else if (isAggressive() && !(this.dead || this.getHealth() < 0.01 || this.isDeadOrDying())) {
                event.getController().setAnimation(RUNNING_ANIMATION);
            } else {
                event.getController().setAnimation(WALK_ANIMATION);
            }
        } else {
            event.getController().setAnimation(IDLE_ANIMATION);
        }
        return PlayState.CONTINUE;

    }

    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 30 && !this.level.isClientSide()) {
            this.level.broadcastEntityEvent(this, (byte) 60);
            this.remove(Entity.RemovalReason.KILLED);
        }
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
        data.addAnimationController(new AnimationController(this, "controller2", 0, this::predicate2));
    }


    @Override
    public void playAmbientSound() {
        this.playSound(SoundEvents.WITHER_SKELETON_AMBIENT, 1.0F, 1.0F);
    }

    @Override
    protected void playHurtSound(DamageSource source) {
        this.playSound(SoundEvents.WITHER_SKELETON_HURT, 1.0F, 1.0F);
    }

    @Override
    public void die(DamageSource p_21014_) {
        super.die(p_21014_);
        this.playSound(SoundEvents.WITHER_SKELETON_DEATH, 1.0F, 1.0F);
    }

    @Override
    protected void playStepSound(BlockPos p_20135_, BlockState p_20136_) {
        this.playSound(SoundEvents.WITHER_SKELETON_STEP, 0.15F, 1.0F);
    }


}
