package com.finallion.graveyard.entites;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;

import javax.annotation.Nullable;

public class AcolyteEntity extends AbstractIllagerEntity {

    public AcolyteEntity(EntityType<? extends AbstractIllagerEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(2, new AbstractIllagerEntity.RaidOpenDoorGoal(this));
        this.goalSelector.addGoal(3, new AbstractRaiderEntity.FindTargetGoal(this, 10.0F));
        this.goalSelector.addGoal(3, new AttackGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, AbstractRaiderEntity.class)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));

    }

    @Override
    public void applyRaidBuffs(int p_213660_1_, boolean p_213660_2_) {

    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.VINDICATOR_CELEBRATE;
    }

    protected void customServerAiStep() {
        if (!this.isNoAi() && GroundPathHelper.hasGroundPathNavigation(this)) {
            boolean flag = ((ServerWorld)this.level).isRaided(this.blockPosition());
            ((GroundPathNavigator)this.getNavigation()).setCanOpenDoors(flag);
        }

        super.customServerAiStep();
    }


    @Override
    public boolean canBeLeader() {
        return false;
    }


    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.3499999940395355D).add(Attributes.FOLLOW_RANGE, 12.0D).add(Attributes.ATTACK_DAMAGE, 5.0D).add(Attributes.MAX_HEALTH, 24.0D);
    }


    @OnlyIn(Dist.CLIENT)
    public AbstractIllagerEntity.ArmPose getArmPose() {
        if (this.isAggressive()) {
            return AbstractIllagerEntity.ArmPose.ATTACKING;
        } else {
            return this.isCelebrating() ? AbstractIllagerEntity.ArmPose.CELEBRATING : AbstractIllagerEntity.ArmPose.CROSSED;
        }
    }


    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        ILivingEntityData ilivingentitydata = super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
        ((GroundPathNavigator)this.getNavigation()).setCanOpenDoors(true);
        this.populateDefaultEquipmentSlots(p_213386_2_);
        this.populateDefaultEquipmentEnchantments(p_213386_2_);
        return ilivingentitydata;
    }


    protected void populateDefaultEquipmentSlots(DifficultyInstance p_180481_1_) {
        if (this.getCurrentRaid() == null) {
            this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Registry.ITEM.get(new ResourceLocation(TheGraveyard.MOD_ID, "bone_dagger"))));
        }

    }

    public boolean isAlliedTo(Entity p_184191_1_) {
        if (super.isAlliedTo(p_184191_1_)) {
            return true;
        } else if (p_184191_1_ instanceof LivingEntity && ((LivingEntity)p_184191_1_).getMobType() == CreatureAttribute.ILLAGER) {
            return this.getTeam() == null && p_184191_1_.getTeam() == null;
        } else {
            return false;
        }
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
            if (this.mob.getVehicle() instanceof RavagerEntity) {
                float f = this.mob.getVehicle().getBbWidth() - 0.1F;
                return (double)(f * 2.0F * f * 2.0F + p_179512_1_.getBbWidth());
            } else {
                return super.getAttackReachSqr(p_179512_1_);
            }
        }
    }

}
