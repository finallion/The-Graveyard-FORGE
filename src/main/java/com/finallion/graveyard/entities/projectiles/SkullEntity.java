package com.finallion.graveyard.entities.projectiles;

import com.finallion.graveyard.init.TGEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class SkullEntity extends AbstractHurtingProjectile {
    private static final EntityDataAccessor<Boolean> CHARGED;

    public SkullEntity(EntityType<? extends SkullEntity> entityType, Level world) {
        super(entityType, world);
    }

    public SkullEntity(Level world, LivingEntity owner, double directionX, double directionY, double directionZ) {
        super(TGEntities.SKULL.get(), owner, directionX, directionY, directionZ, world);
    }

    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    protected float getInertia() {
        return this.isDangerous() ? 0.73F : super.getInertia();
    }

    @Override
    public float getLightLevelDependentMagicValue() {
        return 0.0F;
    }

    public boolean isOnFire() {
        return false;
    }

    public float getBlockExplosionResistance(Explosion p_37619_, BlockGetter p_37620_, BlockPos p_37621_, BlockState p_37622_, FluidState p_37623_, float p_37624_) {
        return this.isDangerous() && p_37622_.canEntityDestroy(p_37620_, p_37621_, this) ? Math.min(0.8F, p_37624_) : p_37624_;
    }

    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (!this.level.isClientSide) {
            Entity entity = entityHitResult.getEntity();
            Entity entity2 = this.getOwner();
            boolean bl;
            if (entity2 instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity)entity2;
                bl = entity.hurt(DamageSource.indirectMagic(this, livingEntity), 10.0F);
                if (bl) {
                    if (entity.isAlive()) {
                        this.doEnchantDamageEffects(livingEntity, entity);
                    }
                }
            }


        }
    }

    public void tick() {
        super.tick();
        Vec3 vec3d = this.getDeltaMovement();
        this.getLevel().addParticle(ParticleTypes.SOUL_FIRE_FLAME, this.getX() + vec3d.x * 0.4D, this.getY() + vec3d.y + 0.5D, this.getZ() + vec3d.z * 0.4D, 0.0D, 0.0D, 0.0D);
    }


    protected void onHit(HitResult p_37628_) {
        super.onHit(p_37628_);
        if (!this.level.isClientSide) {
            Explosion.BlockInteraction explosion$blockinteraction = Explosion.BlockInteraction.NONE;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 2.0F, false, explosion$blockinteraction);
            this.discard();
        }

    }

    public boolean isPickable() {
        return false;
    }

    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    protected void defineSynchedData() {
        this.entityData.define(CHARGED, false);
    }

    public boolean isDangerous() {
        return this.entityData.get(CHARGED);
    }

    public void setDangerous(boolean p_37630_) {
        this.entityData.set(CHARGED, p_37630_);
    }

    protected boolean shouldBurn() {
        return false;
    }

    static {
        CHARGED = SynchedEntityData.defineId(SkullEntity.class, EntityDataSerializers.BOOLEAN);
    }
}

