package com.finallion.graveyard.entities;

import com.finallion.graveyard.init.TGBlocks;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

import java.util.Collection;
import java.util.Random;

public class SkeletonCreeper extends Creeper {
    private static final TargetingConditions CLOSE_PLAYER_PREDICATE;
    private Player closestPlayer;
    private final double explosionRadius = 3.5D;

    public SkeletonCreeper(EntityType<? extends Creeper> entityType, Level world) {
        super(entityType, world);
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.29D);
    }

    public boolean canStart() {
        this.closestPlayer = this.level.getNearestPlayer(SkeletonCreeper.CLOSE_PLAYER_PREDICATE, this);
        return this.closestPlayer != null;
    }


    protected void dropCustomDeathLoot(DamageSource p_32292_, int p_32293_, boolean p_32294_) {
        super.dropCustomDeathLoot(p_32292_, p_32293_, p_32294_);
        Entity entity = p_32292_.getEntity();
        if (entity != this && entity instanceof Creeper) {
            Creeper creeper = (Creeper)entity;
            if (creeper.isIgnited() && random.nextBoolean()) {
                this.spawnAtLocation(TGBlocks.CREEPER_SKELETON.asItem());
            }
        } else {
            super.dropCustomDeathLoot(p_32292_, p_32293_, p_32294_);
        }

    }


    public void explode() {
        if (!this.level.isClientSide) {
            Explosion.BlockInteraction explosion$mode = Explosion.BlockInteraction.NONE;
            float f = this.isPowered() ? 2.0F : 1.0F;

            if (canStart()) {
                this.playSound(SoundEvents.END_PORTAL_SPAWN, 1.0F, 10.0F);
                this.closestPlayer.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 125));
            }

            this.dead = true;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * f, explosion$mode);
            this.discard();
            this.spawnLingeringCloud();
        }

    }

    private void spawnLingeringCloud() {
        Collection<MobEffectInstance> collection = this.getActiveEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level, this.getX(), this.getY(), this.getZ());
            areaeffectcloud.setRadius(2.5F);
            areaeffectcloud.setRadiusOnUse(-0.5F);
            areaeffectcloud.setWaitTime(10);
            areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
            areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());

            for(MobEffectInstance mobeffectinstance : collection) {
                areaeffectcloud.addEffect(new MobEffectInstance(mobeffectinstance));
            }

            this.level.addFreshEntity(areaeffectcloud);
        }

    }




    @Override
    public void aiStep() {
        if (this.level.isClientSide) {
            if (random.nextInt(7) == 0) {
                this.level.addParticle(ParticleTypes.ASH, this.getRandomX(2.0D), this.getRandomY() + new Random().nextInt(1), this.getRandomZ(2.0D), 2.0D, 7.0D, 2.0D);
            }
        }
        super.aiStep();

    }

    static {
        CLOSE_PLAYER_PREDICATE = TargetingConditions.DEFAULT.range(8);
    }

}
