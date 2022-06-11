package com.finallion.graveyard.entities;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;

public abstract class CorruptedIllager extends HordeGraveyardEntity {

    public CorruptedIllager(EntityType<? extends CorruptedIllager> entityType, Level world, String name) {
        super(entityType, world, name);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
       // this.goalSelector.addGoal(2, new PatrolApproachGoal(this, 10.0F));
        this.goalSelector.addGoal(3, new AttackGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[]{Raider.class})).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }

    @Override
    public MobType getMobType() {
        return MobType.ILLAGER;
    }

    public boolean canAttack(LivingEntity p_186270_) {
        return p_186270_ instanceof AbstractVillager && p_186270_.isBaby() ? false : super.canAttack(p_186270_);
    }

    protected void customServerAiStep() {
        if (!this.isNoAi() && GoalUtils.hasGroundPathNavigation(this)) {
            boolean flag = ((ServerLevel)this.level).isRaided(this.blockPosition());
            ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(flag);
        }

        super.customServerAiStep();
    }



    public boolean canBeLeader() {
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.3499999940395355D)
                .add(Attributes.FOLLOW_RANGE, 12.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.MAX_HEALTH, 24.0D);
    }


    public State getState() {
        if (this.isAggressive()) {
            return State.ATTACKING;
        } else {
            return State.CROSSED;
        }
    }


    @javax.annotation.Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34297_, DifficultyInstance p_34298_, MobSpawnType p_34299_, @javax.annotation.Nullable SpawnGroupData p_34300_, @javax.annotation.Nullable CompoundTag p_34301_) {
        RandomSource randomSource = p_34297_.getRandom();
        p_34300_ = super.finalizeSpawn(p_34297_, p_34298_, p_34299_, p_34300_, p_34301_);
        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
        this.populateDefaultEquipmentSlots(randomSource, p_34298_);
        this.populateDefaultEquipmentEnchantments(randomSource, p_34298_);

        return p_34300_;
    }

    public boolean isAlliedTo(Entity p_33314_) {
        if (super.isAlliedTo(p_33314_)) {
            return true;
        } else if (p_33314_ instanceof LivingEntity && ((LivingEntity)p_33314_).getMobType() == MobType.ILLAGER) {
            return this.getTeam() == null && p_33314_.getTeam() == null;
        } else {
            return false;
        }
    }


    public boolean isModelDamaged() {
        return false;
    }

    class AttackGoal extends MeleeAttackGoal {
        public AttackGoal(CorruptedIllager illager) {
            super(illager, 1.0D, false);
        }

        @Override
        protected double getAttackReachSqr(LivingEntity p_25556_) {
            if (this.mob.getVehicle() instanceof Ravager) {
                float f = this.mob.getVehicle().getBbWidth() - 0.1F;
                return (double)(f * 2.0F * f * 2.0F + p_25556_.getBbWidth());
            } else {
                return super.getAttackReachSqr(p_25556_);
            }
        }
    }

    public enum State {
        CROSSED,
        ATTACKING,
        SPELLCASTING,
        BOW_AND_ARROW,
        CROSSBOW_HOLD,
        CROSSBOW_CHARGE,
        CELEBRATING,
        UNDEAD,
        UNDEAD_ATTACKING,
        NEUTRAL;

        private State() {
        }
    }

}

