package com.finallion.graveyard.entities;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;

public class AcolyteEntity extends AbstractIllager {

    public AcolyteEntity(EntityType<? extends AbstractIllager> entityType, Level world) {
        super(entityType, world);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new AbstractIllager.RaiderOpenDoorGoal(this));
        this.goalSelector.addGoal(3, new Raider.HoldGroundAttackGoal(this, 10.0F));
        this.goalSelector.addGoal(3, new AttackGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }


    @Override
    public void applyRaidBuffs(int p_213660_1_, boolean p_213660_2_) {

    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.VINDICATOR_CELEBRATE;
    }

    protected void customServerAiStep() {
        if (!this.isNoAi() && GoalUtils.hasGroundPathNavigation(this)) {
            boolean flag = ((ServerLevel)this.level).isRaided(this.blockPosition());
            ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(flag);
        }


        super.customServerAiStep();
    }

    @Override
    public void aiStep() {
        if (this.isAlive()) {
            boolean flag = this.isSunSensitive() && this.isSunBurnTick() && GraveyardConfig.COMMON.acolyteCanBurnInSunlight.get();
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

    @Override
    public boolean canBeLeader() {
        return false;
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.3499999940395355D).add(Attributes.FOLLOW_RANGE, 12.0D).add(Attributes.ATTACK_DAMAGE, 5.0D).add(Attributes.MAX_HEALTH, 24.0D);
    }


    public AbstractIllager.IllagerArmPose getArmPose() {
        if (this.isAggressive()) {
            return AbstractIllager.IllagerArmPose.ATTACKING;
        } else {
            return this.isCelebrating() ? AbstractIllager.IllagerArmPose.CELEBRATING : AbstractIllager.IllagerArmPose.CROSSED;
        }
    }


    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34088_, DifficultyInstance p_34089_, MobSpawnType p_34090_, @Nullable SpawnGroupData p_34091_, @Nullable CompoundTag p_34092_) {
        SpawnGroupData spawngroupdata = super.finalizeSpawn(p_34088_, p_34089_, p_34090_, p_34091_, p_34092_);
        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
        this.populateDefaultEquipmentSlots(p_34089_);
        this.populateDefaultEquipmentEnchantments(p_34089_);
        return spawngroupdata;
    }


    protected void populateDefaultEquipmentSlots(DifficultyInstance p_34084_) {
        if (this.getCurrentRaid() == null) {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Registry.ITEM.get(new ResourceLocation(TheGraveyard.MOD_ID, "bone_dagger"))));
        }

    }

    protected boolean isSunSensitive() {
        return true;
    }

    @Override
    protected boolean isSunBurnTick() {
        return super.isSunBurnTick();
    }

    public boolean isAlliedTo(Entity p_184191_1_) {
        if (super.isAlliedTo(p_184191_1_)) {
            return true;
        } else if (p_184191_1_ instanceof LivingEntity && ((LivingEntity)p_184191_1_).getMobType() == MobType.ILLAGER) {
            return this.getTeam() == null && p_184191_1_.getTeam() == null;
        } else {
            return false;
        }
    }

    public boolean canBeAffected(MobEffectInstance effect) {
        if (effect.getEffect() == MobEffects.WITHER) {
            if (GraveyardConfig.COMMON.acolyteCanBeWithered.get()) {
                return true;
            } else {
                return false;
            }
        }

        return super.canBeAffected(effect);
    }

    @Override
    public void playAmbientSound() {
        this.playSound(SoundEvents.VINDICATOR_AMBIENT, 1.0F, 1.0F);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.VINDICATOR_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.VINDICATOR_DEATH;
    }



    class AttackGoal extends MeleeAttackGoal {
        public AttackGoal(AcolyteEntity p_i50577_2_) {
            super(p_i50577_2_, 1.0D, false);
        }

        protected double getAttackReachSqr(LivingEntity p_179512_1_) {
            if (this.mob.getVehicle() instanceof Ravager) {
                float f = this.mob.getVehicle().getBbWidth() - 0.1F;
                return (double)(f * 2.0F * f * 2.0F + p_179512_1_.getBbWidth());
            } else {
                return super.getAttackReachSqr(p_179512_1_);
            }
        }
    }

}
