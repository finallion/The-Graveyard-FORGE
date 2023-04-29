package com.finallion.graveyard.entities;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;


public class CorruptedVindicator extends CorruptedIllager {

    public CorruptedVindicator(EntityType<? extends CorruptedIllager> entityType, Level world) {
        super(entityType, world, "corrupted_vindicator");
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.3499999940395355D)
                .add(Attributes.FOLLOW_RANGE, 12.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.MAX_HEALTH, 24.0D);
    }

    @Override
    public boolean isModelDamaged() {
        return true;
    }

    @Override
    public State getState() {
        if (this.isAggressive()) {
            return State.UNDEAD_ATTACKING;
        } else {
            return State.UNDEAD;
        }
    }

    @Override
    public void playAmbientSound() {
        this.playSound(TGSounds.CORRUPTED_ILLAGER_AMBIENT.get(), 0.8F, 0.0F);
    }

    @Override
    protected void playHurtSound(DamageSource source) {
        this.playSound(TGSounds.CORRUPTED_ILLAGER_HURT.get(), 0.8F, 0.0F);
    }


    protected SoundEvent getDeathSound() {
        return TGSounds.CORRUPTED_ILLAGER_DEATH.get();
    }

    @Override
    public float getVoicePitch() {
        return 0.0F;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(TGSounds.CORRUPTED_ILLAGER_STEP.get(), 0.8F, 0.0F);
    }
}
