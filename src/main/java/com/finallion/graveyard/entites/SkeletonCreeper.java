package com.finallion.graveyard.entites;

import com.finallion.graveyard.init.TGBlocks;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;


import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class SkeletonCreeper extends CreeperEntity {
    private static final EntityPredicate CLOSE_PLAYER_PREDICATE;
    private PlayerEntity closestPlayer;
    private final double explosionRadius = 3.5D;

    public SkeletonCreeper(EntityType<? extends CreeperEntity> entityType, World world) {
        super(entityType, world);
    }


    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.29D);
    }



    public boolean canStart() {
        this.closestPlayer = this.level.getNearestPlayer(SkeletonCreeper.CLOSE_PLAYER_PREDICATE, this);
        return this.closestPlayer != null;
    }

    protected void dropCustomDeathLoot(DamageSource p_213333_1_, int p_213333_2_, boolean p_213333_3_) {
        Entity entity = p_213333_1_.getEntity();
        if (entity instanceof CreeperEntity) {
            CreeperEntity creeperEntity = (CreeperEntity)entity;
            // changed from onHeadsDropped so every skeleton creeper will drop its skeleton instead of only one per charged creeper
            if (creeperEntity.isIgnited() && random.nextBoolean()) {
                this.spawnAtLocation(TGBlocks.CREEPER_SKELETON.asItem());
            }
        } else {
            super.dropCustomDeathLoot(p_213333_1_, p_213333_2_, p_213333_3_);
        }

    }

    public void explode() {
        if (!this.level.isClientSide) {
            Explosion.Mode explosion$mode = Explosion.Mode.NONE;
            float f = this.isPowered() ? 2.0F : 1.0F;

            if (canStart()) {
                this.playSound(SoundEvents.END_PORTAL_SPAWN, 1.0F, 10.0F);
                this.closestPlayer.addEffect(new EffectInstance(Effects.BLINDNESS, 125));
            }

            this.dead = true;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * f, explosion$mode);
            this.remove();
            this.spawnLingeringCloud();
        }

    }

    private void spawnLingeringCloud() {
        Collection<EffectInstance> collection = this.getActiveEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(this.level, this.getX(), this.getY(), this.getZ());
            areaeffectcloudentity.setRadius(2.5F);
            areaeffectcloudentity.setRadiusOnUse(-0.5F);
            areaeffectcloudentity.setWaitTime(10);
            areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
            areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float)areaeffectcloudentity.getDuration());

            for(EffectInstance effectinstance : collection) {
                areaeffectcloudentity.addEffect(new EffectInstance(effectinstance));
            }

            this.level.addFreshEntity(areaeffectcloudentity);
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
        CLOSE_PLAYER_PREDICATE = (new EntityPredicate()).range(8);
    }

}
