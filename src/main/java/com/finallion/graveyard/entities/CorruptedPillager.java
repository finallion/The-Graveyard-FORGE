package com.finallion.graveyard.entities;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;


public class CorruptedPillager extends CorruptedIllager {

    public CorruptedPillager(EntityType<? extends CorruptedIllager> entityType, Level world) {
        super(entityType, world, "corrupted_pillager");
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.3499999940395355D)
                .add(Attributes.FOLLOW_RANGE, 12.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.MAX_HEALTH, 24.0D);
    }

    @Override
    public State getState() {
        if (this.isAggressive()) {
            return State.ATTACKING;
        } else {
            return State.UNDEAD;
        }
    }

    @Override
    public void playAmbientSound() {
        this.playSound(SoundEvents.ZOMBIE_VILLAGER_AMBIENT, 0.8F, 0.0F);
    }

    @Override
    protected void playHurtSound(DamageSource source) {
        this.playSound(SoundEvents.ZOMBIE_VILLAGER_HURT, 0.8F, 0.0F);
    }


    @Override
    public void die(DamageSource source) {
        super.die(source);
        this.playSound(SoundEvents.ZOMBIE_VILLAGER_DEATH, 0.8F, 0.0F);
    }

}