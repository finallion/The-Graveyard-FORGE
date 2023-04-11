package com.finallion.graveyard.entities;

import com.finallion.graveyard.init.TGSounds;
import com.finallion.graveyard.trades.NamelessHangedTradeOffers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.InteractGoal;
import net.minecraft.world.entity.ai.goal.LookAtTradingPlayerGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;


public class NamelessHangedEntity extends AbstractVillager implements IAnimatable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private final AnimationBuilder IDLE_ANIMATION = new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP);

    public NamelessHangedEntity(EntityType<? extends NamelessHangedEntity> entityType, Level world) {
        super(entityType, world);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new LookAtTradingPlayerGoal(this));
        this.goalSelector.addGoal(9, new InteractGoal(this, Player.class, 3.0F, 1.0F));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createLivingAttributes()
                .add(Attributes.FOLLOW_RANGE, 0.0D)
                .add(Attributes.MAX_HEALTH, 45.0D);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(IDLE_ANIMATION);
        return PlayState.CONTINUE;
    }


    @Override
    public boolean canBeSeenAsEnemy() {
        return false;
    }

    public boolean isPushable() {
        return false;
    }

    @Override
    public void push(Entity p_21294_) {
    }

    @Override
    public boolean hurt(DamageSource source, float p_21017_) {
        return source == DamageSource.OUT_OF_WORLD || source == DamageSource.CRAMMING;
    }

    @Override
    public void thunderHit(ServerLevel p_19927_, LightningBolt p_19928_) {
    }

    @Override
    public boolean ignoreExplosion() {
        return true;
    }

    @Override
    public boolean isAffectedByPotions() {
        return false;
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel p_150046_, AgeableMob p_150047_) {
        return null;
    }

    public boolean showProgressBar() {
        return false;
    }

    public void aiStep() {
        if (this.level.isClientSide) {
            this.level.addParticle(ParticleTypes.ASH, this.getX(0.5D), this.getY() + 1.75D, this.getZ(0.5D), 0, 0, 0);
        }

        super.aiStep();
    }

    @Override
    public void tick() {
        if (level.isDay() && this.offers != null) {
            this.offers = null;
        }

        super.tick();
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (level.isDay() && !this.level.isClientSide) {
            player.displayClientMessage(Component.translatable("entity.graveyard.nameless_hanged.wait"), true);
            level.playSound(null, player.blockPosition(), TGSounds.NAMELESS_HANGED_INTERACT.get(), SoundSource.HOSTILE, 0.6F, 1.0F);
            //player.playSound(TGSounds.NAMELESS_HANGED_INTERACT, 1.0F, 1.0F);
        }

        if (this.isAlive() && !this.isTrading() && level.isNight()) {
            if (!this.getOffers().isEmpty()) {
                if (!this.level.isClientSide) {
                    this.setTradingPlayer(player);
                    this.openTradingScreen(player, this.getDisplayName(), 1);
                    level.playSound(null, player.blockPosition(), TGSounds.NAMELESS_HANGED_INTERACT.get(), SoundSource.HOSTILE, 0.6F, 1.0F);
                    //player.playSound(TGSounds.NAMELESS_HANGED_INTERACT, 1.0F, 1.0F);
                }
            }
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(player, hand);
        }
    }

    protected void updateTrades() {
        VillagerTrades.ItemListing[] factorys = NamelessHangedTradeOffers.NAMELESS_HANGED_TRADES.get(1);
        if (factorys != null) {
            MerchantOffers tradeOfferList = this.getOffers();
            int offers = random.nextInt(3) + 7;
            this.addOffersFromItemListings(tradeOfferList, factorys, offers);
        }
    }

    protected void rewardTradeXp(MerchantOffer offer) {
        if (offer.shouldRewardExp()) {
            int i = 3 + this.random.nextInt(4);
            this.level.addFreshEntity(new ExperienceOrb(this.level, this.getX(), this.getY() + 0.5D, this.getZ(), i));
        }

    }

    public boolean removeWhenFarAway(double p_35886_) {
        return false;
    }

    @Override
    public void playAmbientSound() {
        this.playSound(TGSounds.NAMELESS_HANGED_AMBIENT.get(), 0.3F, 1.0F);

        if (this.random.nextBoolean()) {
            this.playSound(TGSounds.NAMELESS_HANGED_BREATH.get(), 0.5F, 1.0F);
        }
    }

    @Override
    protected float getStandingEyeHeight(Pose p_35297_, EntityDimensions p_35298_) {
        return 2.0F;
    }


    public SoundEvent getNotifyTradeSound() {
        return null;
    }

    protected SoundEvent getTradeUpdatedSound(boolean p_35890_) {
        return null;
    }


}
