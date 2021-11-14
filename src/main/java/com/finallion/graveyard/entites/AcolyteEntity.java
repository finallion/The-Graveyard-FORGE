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
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

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

    protected void mobTick() {
        if (!this.isAiDisabled() && NavigationConditions.hasMobNavigation(this)) {
            boolean bl = ((ServerWorld)this.world).hasRaidAt(this.getBlockPos());
            ((MobNavigation)this.getNavigation()).setCanPathThroughDoors(bl);
        }

        super.mobTick();
    }

    @Override
    public boolean canLead() {
        return false;
    }


    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.3499999940395355D).add(Attributes.FOLLOW_RANGE, 12.0D).add(Attributes.ATTACK_DAMAGE, 5.0D).add(Attributes.MAX_HEALTH, 24.0D);
    }


    public IllagerEntity.State getState() {
        if (this.isAttacking()) {
            return IllagerEntity.State.ATTACKING;
        } else {
            return this.isCelebrating() ? IllagerEntity.State.CELEBRATING : IllagerEntity.State.CROSSED;
        }
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        EntityData entityData2 = super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
        ((MobNavigation)this.getNavigation()).setCanPathThroughDoors(true);
        this.initEquipment(difficulty);
        this.updateEnchantments(difficulty);
        return entityData2;
    }

    protected void initEquipment(LocalDifficulty difficulty) {
        if (this.getRaid() == null) {
            this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Registry.ITEM.get(new Identifier(TheGraveyard.MOD_ID, "bone_dagger"))));
        }

    }

    public boolean isTeammate(Entity other) {
        if (super.isTeammate(other)) {
            return true;
        } else if (other instanceof LivingEntity && ((LivingEntity)other).getGroup() == EntityGroup.ILLAGER) {
            return this.getScoreboardTeam() == null && other.getScoreboardTeam() == null;
        } else {
            return false;
        }
    }

    @Override
    public void playAmbientSound() {
        this.playSound(SoundEvents.ENTITY_VINDICATOR_AMBIENT, 1.0F, 1.0F);
    }

    @Override
    protected void playHurtSound(DamageSource source) {
        this.playSound(SoundEvents.ENTITY_VINDICATOR_HURT, 1.0F, 1.0F);
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        this.playSound(SoundEvents.ENTITY_VINDICATOR_DEATH, 1.0F, 1.0F);
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
