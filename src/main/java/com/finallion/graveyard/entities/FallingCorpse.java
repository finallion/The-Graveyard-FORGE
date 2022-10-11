package com.finallion.graveyard.entities;

import com.finallion.graveyard.util.MathUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class FallingCorpse extends Monster implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private final AnimationBuilder FALLING_ANIMATION = new AnimationBuilder().addAnimation("falling", true);
    private final AnimationBuilder LANDING_ANIMATION = new AnimationBuilder().addAnimation("landing", false).addAnimation("despawn", false);
    private static final EntityDataAccessor<Boolean> IS_FALLING;
    private static final EntityDataAccessor<Boolean> HAS_COLLIDED;
    private final float DAMAGE = 10.0F;
    private int landingCounter = 40;
    private int levitationCounter = 15;
    private float rotation;

    public FallingCorpse(EntityType<? extends FallingCorpse> entityType, Level world) {
        super(entityType, world);
        setRotation(getRandom().nextInt(361));
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (isFalling()) {
            event.getController().setAnimation(FALLING_ANIMATION);
            return PlayState.CONTINUE;
        } else {
            event.getController().setAnimation(LANDING_ANIMATION);
            return PlayState.CONTINUE;
        }

    }
    // I don't want the red overlay on death, so bypass landing effects and kill the mob after some ticks (in mobTick())
    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        this.level.playSound(null, this.blockPosition(), SoundEvents.HOSTILE_BIG_FALL, SoundSource.HOSTILE, 2.0F, 1.0F);
        return false;
    }


    public boolean hurt(DamageSource source, float amount) {
        if (source == DamageSource.OUT_OF_WORLD) {
            return true;
        }
        return false;
    }



    @Override
    public void resetFallDistance() {
        setIsFalling(false);
        super.resetFallDistance();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_FALLING, true);
        this.entityData.define(HAS_COLLIDED, false);
    }


    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 5.0D);
    }

    @Override
    public void aiStep() {
        if (this.getBlockStateOn().isAir()) {
            for (int i = 0; i < 20; i++) {
                BlockPos pos = this.blockPosition().offset(0, -i, 0);
                BlockState state = this.level.getBlockState(pos);
                if (!state.isAir() && state.isSolidRender(level, pos)) {
                    //this.world.addParticle(ParticleTypes.SCULK_CHARGE_POP, pos.getX() + random.nextDouble() + random.nextDouble() - random.nextDouble(), pos.getY() + 1.3D, pos.getZ() + random.nextDouble() + random.nextDouble() - random.nextDouble(), 0.0D, 0.0D, 0.0D);
                    MathUtil.createParticleDisk(this.level, pos.getX() + random.nextDouble(), pos.getY() + 1.3D, pos.getZ() + + random.nextDouble(), 0.0D, 0.0D, 0.0D,1, DustParticleOptions.REDSTONE, this.getRandom());
                    break;
                }
            }
        }

        super.aiStep();
    }

    @Override
    protected void customServerAiStep() {
        if (levitationCounter > 0) {
            levitationCounter--;
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.04D, 0.0D));
        } else {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
        }

        // kill after despawn animation has played to avoid red death overlay
        if (!isFalling() && landingCounter > 0) {
            landingCounter--;
        }

        if (landingCounter <= 0) {
            this.discard();
        }

        if (!level.getBlockState(this.blockPosition().below()).isAir()) {
            setIsFalling(false);
        }

        if (this.getBlockStateOn().isAir()) {
            setIsFalling(true);
        }

        super.customServerAiStep();
    }

    @Override
    public void playerTouch(Player p_20081_) {
        if (!hasCollided() && isFalling()) {
            p_20081_.hurt(DamageSource.GENERIC, DAMAGE);
            setHasCollided(true);
        }
        super.playerTouch(p_20081_);
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public boolean isFalling() {
        return this.entityData.get(IS_FALLING);
    }

    public void setIsFalling(boolean isFalling) {
        this.entityData.set(IS_FALLING, isFalling);
    }

    public boolean hasCollided() {
        return this.entityData.get(HAS_COLLIDED);
    }

    public void setHasCollided(boolean hasCollided) {
        this.entityData.set(HAS_COLLIDED, hasCollided);
    }

    static {
        IS_FALLING = SynchedEntityData.defineId(FallingCorpse.class, EntityDataSerializers.BOOLEAN);
        HAS_COLLIDED = SynchedEntityData.defineId(FallingCorpse.class, EntityDataSerializers.BOOLEAN);
    }

}




