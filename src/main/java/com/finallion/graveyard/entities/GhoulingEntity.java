package com.finallion.graveyard.entities;

import com.finallion.graveyard.entities.ai.goals.*;
import com.finallion.graveyard.init.TGAdvancements;
import com.finallion.graveyard.init.TGBlocks;
import com.finallion.graveyard.init.TGParticles;
import com.finallion.graveyard.init.TGSounds;
import com.finallion.graveyard.item.BoneStaffItem;
import com.finallion.graveyard.util.MathUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GhoulingEntity extends GraveyardMinionEntity implements IAnimatable, MenuProvider {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    private static final EntityDataAccessor<Integer> ATTACK_ANIM_TIMER;
    private static final EntityDataAccessor<Integer> ANIMATION;
    private static final EntityDataAccessor<Integer> SPAWN_TIMER;
    private static final EntityDataAccessor<Integer> TELEPORT_TIMER;
    private static final EntityDataAccessor<ItemStack> STAFF;
    private static final EntityDataAccessor<Boolean> COFFIN;
    private static final EntityDataAccessor<Byte> VARIANT;
    //private static final TrackedData<Boolean> CAN_COLLECT;

    private final AnimationBuilder SPAWN_ANIMATION = new AnimationBuilder().addAnimation("spawn", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
    private final AnimationBuilder IDLE_ANIMATION = new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP);
    private final AnimationBuilder ATTACK_ANIMATION = new AnimationBuilder().addAnimation("attack", ILoopType.EDefaultLoopTypes.LOOP);
    private final AnimationBuilder WALK_ANIMATION = new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP);
    private final AnimationBuilder DEATH_ANIMATION = new AnimationBuilder().addAnimation("death", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
    protected static final int ANIMATION_SPAWN = 0;
    protected static final int ANIMATION_IDLE = 1;
    protected static final int ANIMATION_MELEE = 2;
    protected static final int ANIMATION_WALK = 3;
    protected static final int ANIMATION_DEATH = 4;

    public final int ATTACK_ANIMATION_DURATION = 14;
    protected SimpleContainer inventory;
    //private Goal collectGoal;
    private static final List<Item> GHOULING_HOLDABLE = new ArrayList<>();

    public GhoulingEntity(EntityType<? extends GhoulingEntity> entityType, Level world) {
        super(entityType, world);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new GhoulingEscapeDangerGoal(1.5D));
        this.goalSelector.addGoal(2, new SitGoal(this));
        //this.goalSelector.addGoal();(3, new WanderAroundGoal(this, 1.0F));
        this.goalSelector.addGoal(5, new GhoulingMeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new TrackOwnerAttackerGoal(this));
        this.targetSelector.addGoal(2, new AttackWithOwnerGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[0]));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
                .add(Attributes.ARMOR_TOUGHNESS, 2.0D)
                .add(Attributes.ARMOR, 5.0D)
                .add(Attributes.ATTACK_DAMAGE, 6.5D)
                .add(Attributes.MOVEMENT_SPEED, 0.31D)
                .add(Attributes.FOLLOW_RANGE, 35.0D);
    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ANIMATION, ANIMATION_IDLE);
        this.entityData.define(STAFF, ItemStack.EMPTY);
        this.entityData.define(ATTACK_ANIM_TIMER, 0);
        this.entityData.define(COFFIN, false);
        this.entityData.define(SPAWN_TIMER, 0);
        this.entityData.define(TELEPORT_TIMER, 0);
        this.entityData.define(VARIANT, (byte)0);
        //this.dataTracker.startTracking(CAN_COLLECT, false);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (getAnimationState() == ANIMATION_SPAWN && getSpawnTimer() >= 0) {
            event.getController().setAnimation(SPAWN_ANIMATION);
            return PlayState.CONTINUE;
        }

        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState predicate2(AnimationEvent<E> event) {
        /* DEATH */
        if (this.isDeadOrDying() || this.getHealth() < 0.01) {
            event.getController().setAnimation(DEATH_ANIMATION);
            return PlayState.CONTINUE;
        }

        /* ATTACK */
        // takes one tick to get to this method (from mobtick)
        if (getAnimationState() == ANIMATION_MELEE && getAttackAnimTimer() == (ATTACK_ANIMATION_DURATION - 1) && isAggressive() && !(this.isDeadOrDying() || this.getHealth() < 0.01)) {
            setAttackAnimTimer(ATTACK_ANIMATION_DURATION - 2);
            event.getController().setAnimation(ATTACK_ANIMATION);
            return PlayState.CONTINUE;
        }

        /* WALK */
        if ((getAnimationState() == ANIMATION_WALK || event.isMoving()) && getAttackAnimTimer() <= 0) {
            if (!event.isMoving()) {
                event.getController().setAnimation(IDLE_ANIMATION);
            } else {
                event.getController().setAnimation(WALK_ANIMATION);
            }
            return PlayState.CONTINUE;
        }

        /* IDLE */
        if (getAnimationState() == ANIMATION_IDLE && getAttackAnimTimer() <= 0 && getSpawnTimer() <= 0) {
            event.getController().setAnimation(IDLE_ANIMATION);
            return PlayState.CONTINUE;
        }

        /* STOPPERS */
        // stops idle animation from looping
        if (getAnimationState() == ANIMATION_IDLE && getAttackAnimTimer() > 0) {
            setAnimationState(ANIMATION_MELEE);
            return PlayState.STOP;
        }

        // stops attack animation from looping
        if (getAttackAnimTimer() <= 0 && !(this.isDeadOrDying() || this.getHealth() < 0.01) && getSpawnTimer() <= 0) {
            setAnimationState(ANIMATION_IDLE);
            return PlayState.STOP;
        }


        return PlayState.CONTINUE;
    }

    protected void customServerAiStep() {
        // ATTACK TIMER
        if (this.getAttackAnimTimer() == ATTACK_ANIMATION_DURATION) {
            setAnimationState(ANIMATION_MELEE);
        }

        if (this.getAttackAnimTimer() > 0) {
            int animTimer = this.getAttackAnimTimer() - 1;
            this.setAttackAnimTimer(animTimer);
        }

        if (this.getSpawnTimer() > 0) {
            this.setSpawnTimer(getSpawnTimer() - 1);
        }

        if (this.getTeleportTimer() > 0) {
            this.setTeleportTimer(getTeleportTimer() - 1);
        }

        if (this.getHealth() < this.getMaxHealth()) {
            this.heal(0.01F);
        }

        super.customServerAiStep();
    }

    // tick movement
    @Override
    public void baseTick() {
        if (getSpawnTimer() == 50) {
            level.playSound(null, this.blockPosition(), TGSounds.GHOULING_SPAWN.get(), SoundSource.HOSTILE, 5.0F, 1.5F);
            level.playSound(null, this.blockPosition(), TGSounds.GHOUL_ROAR.get(), SoundSource.HOSTILE, 1.0F, -2.0F);
        }

        if (isInSittingPose() && random.nextInt(5) == 0) {
            MathUtil.createParticleCircle(level, this.getX(), this.getY() + 0.6D, this.getZ(), 0.0D, 0.0D, 0.0D, 1.5F, TGParticles.GRAVEYARD_SOUL_PARTICLE.get(), level.random, 0.5F);
        }

        if (getTeleportTimer() > 0) {
            if (getTeleportTimer() == 10) {
                playSound(SoundEvents.SOUL_ESCAPE, 2.0F, -10.0F);
            }
            MathUtil.createParticleCircle(level, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D, 1.5F, TGParticles.GRAVEYARD_SOUL_PARTICLE.get(), level.random, 0.5F);
            MathUtil.createParticleCircle(level, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D, 1.5F, ParticleTypes.SOUL_FIRE_FLAME, level.random, 0.5F);
        }

        super.baseTick();
    }

    @Override
    public void tick() {
        if (getSpawnTimer() > 0 && level != null) {
            //MinecraftClient.getInstance().particleManager.addBlockBreakParticles(this.getBlockPos().down(), world.getBlockState(this.getBlockPos().down()));
            Random randomsource = this.getRandom();
            BlockState blockstate = this.getBlockStateOn();
            if (blockstate.getRenderShape() != RenderShape.INVISIBLE) {
                for(int i = 0; i < 30; ++i) {
                    double d0 = this.getX() + (double) Mth.randomBetween(randomsource, -0.7F, 0.7F);
                    double d1 = this.getY();
                    double d2 = this.getZ() + (double)Mth.randomBetween(randomsource, -0.7F, 0.7F);
                    this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockstate), d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }
            }
        }
        super.tick();
    }
    public int getAnimationState() {
        return this.entityData.get(ANIMATION);
    }

    public void setAnimationState(int state) {
        this.entityData.set(ANIMATION, state);
    }

    public int getAttackAnimTimer() {
        return (Integer) this.entityData.get(ATTACK_ANIM_TIMER);
    }

    public void setAttackAnimTimer(int time) {
        this.entityData.set(ATTACK_ANIM_TIMER, time);
    }

    public int getTeleportTimer() {
        return (Integer) this.entityData.get(TELEPORT_TIMER);
    }

    public void setTeleportTimer(int time) {
        this.entityData.set(TELEPORT_TIMER, time);
    }

    public void onSummoned() {
        this.setAnimationState(ANIMATION_SPAWN);
        setSpawnTimer(50);
    }

    public boolean removeWhenFarAway(double p_27598_) {
        return false;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
        animationData.addAnimationController(new AnimationController(this, "controller2", 0, this::predicate2));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }


    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!this.level.isClientSide() && isOwner(player)) {
            if (this.hasCoffin() && player.isCrouching()) {
                player.openMenu(this);
                return InteractionResult.SUCCESS;
            }

            if (!itemStack.isEmpty()) {
                if (!this.hasCoffin() && GHOULING_HOLDABLE.contains(itemStack.getItem())) {
                    this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(itemStack.getItem()));
                    if (inventory == null) {
                        this.playSound(SoundEvents.CHEST_CLOSE, 1.0F, -5.0F);
                        TGAdvancements.EQUIP_COFFIN.trigger((ServerPlayer) player);
                        inventory = new SimpleContainer(54);
                        this.setHasCoffin(true);
                    }
                    if (!player.isCreative()) {
                        itemStack.shrink(1);
                    }
                    return InteractionResult.SUCCESS;
                }
            }

            if (itemStack.getItem() instanceof BoneStaffItem && !player.isCrouching()) {
                InteractionResult actionResult = super.mobInteract(player, hand);
                if (!actionResult.consumesAction()) {
                    this.playSound(TGSounds.GHOULING_GROAN.get(), 1.0F, -2.0F);
                    if (isInSittingPose()) {
                        player.displayClientMessage(new TranslatableComponent("entity.graveyard.ghouling.nowait"), true);
                    } else {
                        player.displayClientMessage(new TranslatableComponent("entity.graveyard.ghouling.wait"), true);
                    }

                    this.setSitting(!this.isSitting());
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget((LivingEntity) null);

                    return InteractionResult.SUCCESS;
                }

                return actionResult;
            }
        }
        return super.mobInteract(player, hand);
    }

    @Override
    protected void tickDeath() {
        ++this.deathTime;

        if (this.deathTime == 44 && !this.level.isClientSide()) {
            this.level.broadcastEntityEvent(this, (byte)60);
            this.remove(RemovalReason.KILLED);
        }

    }

    public int getSpawnTimer() {
        return (Integer) this.entityData.get(SPAWN_TIMER);
    }

    public void setSpawnTimer(int ticks) {
        this.entityData.set(SPAWN_TIMER, ticks);
    }

    public byte getVariant() {
        return entityData.get(VARIANT);
    }

    public void setVariant(byte variant) {
        entityData.set(VARIANT, variant);
    }

    public ItemStack getStaff() {
        return entityData.get(STAFF);
    }

    public void setStaff(ItemStack staff) {
        entityData.set(STAFF, staff);
    }

    /* INVENTORY */
    public boolean hasCoffin() {
        return (Boolean) this.entityData.get(COFFIN);
    }

    public void setHasCoffin(boolean hasCoffin) {
        this.entityData.set(COFFIN, hasCoffin);
    }

    protected void dropEquipment() {
        super.dropEquipment();
        if (this.hasCoffin()) {
            if (!this.level.isClientSide()) {
                this.spawnAtLocation(this.getOffhandItem().getItem());
                if (this.inventory != null) {
                    for (int i = 0; i < this.inventory.getContainerSize(); i++) {
                        ItemStack stack = this.inventory.getItem(i);
                        if (!stack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(stack)) {
                            this.spawnAtLocation(stack);
                        }
                    }
                }
            }
            this.setHasCoffin(false);
        }
    }


    @Override
    public void die(DamageSource source) {
        if (!level.isClientSide()) {
            BoneStaffItem.ownerGhoulingMapping.remove(this.uuid, getOwnerUuid());
        }
        super.die(source);
    }

    protected SoundEvent getDeathSound() {
        return TGSounds.GHOULING_DEATH.get();
    }

    @Override
    public float getVoicePitch() {
        return -2.0F;
    }


    @Override
    public void playAmbientSound() {
        this.playSound(TGSounds.GHOULING_AMBIENT.get(), 1.0F, -1.0F);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(TGSounds.GHOULING_STEP.get(), 0.3F, 0.0F);
    }

    @Override
    protected void playHurtSound(DamageSource source) {
        this.playSound(TGSounds.GHOULING_HURT.get(), 1.0F, -1.0F);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("CoffinGhouling", this.hasCoffin());
        nbt.putByte("ghoulVariant", getVariant());
        //nbt.put("Ghouling", this.writeNbt(new NbtCompound()));
        if (getStaff() != null) {
            nbt.put("Staff", getStaff().save(new CompoundTag()));
        }
        if (inventory != null) {
            final ListTag inv = new ListTag();
            for (int i = 0; i < this.inventory.getContainerSize(); i++) {
                inv.add(inventory.getItem(i).save(new CompoundTag()));
            }
            nbt.put("Inventory", inv);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setHasCoffin(nbt.getBoolean("CoffinGhouling"));
        this.setVariant(nbt.getByte("ghoulVariant"));
        if (nbt.contains("Staff")) {
            setStaff(ItemStack.of(nbt.getCompound("Staff")));
        }
        if (nbt.contains("Inventory")) {
            final ListTag inv = nbt.getList("Inventory", 10);
            inventory = new SimpleContainer(inv.size());
            for (int i = 0; i < inv.size(); i++) {
                inventory.setItem(i, ItemStack.of(inv.getCompound(i)));
            }
        }
    }



    @Override
    public Component getDisplayName() {
        return new TextComponent("Ghouling");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
        if (inventory == null) {
            return null;
        }
        return ChestMenu.sixRows(p_39954_, p_39955_, inventory);
    }



    static {
        GHOULING_HOLDABLE.add(TGBlocks.SARCOPHAGUS.get().asItem());
        GHOULING_HOLDABLE.add(TGBlocks.OAK_COFFIN.get().asItem());
        GHOULING_HOLDABLE.add(TGBlocks.DARK_OAK_COFFIN.get().asItem());
        GHOULING_HOLDABLE.add(TGBlocks.SPRUCE_COFFIN.get().asItem());
        GHOULING_HOLDABLE.add(TGBlocks.BIRCH_COFFIN.get().asItem());
        GHOULING_HOLDABLE.add(TGBlocks.JUNGLE_COFFIN.get().asItem());
        GHOULING_HOLDABLE.add(TGBlocks.ACACIA_COFFIN.get().asItem());
        GHOULING_HOLDABLE.add(TGBlocks.WARPED_COFFIN.get().asItem());
        GHOULING_HOLDABLE.add(TGBlocks.CRIMSON_COFFIN.get().asItem());

        ATTACK_ANIM_TIMER = SynchedEntityData.defineId(GhoulingEntity.class, EntityDataSerializers.INT);
        ANIMATION = SynchedEntityData.defineId(GhoulingEntity.class, EntityDataSerializers.INT);
        SPAWN_TIMER = SynchedEntityData.defineId(GhoulingEntity.class, EntityDataSerializers.INT);
        TELEPORT_TIMER = SynchedEntityData.defineId(GhoulingEntity.class, EntityDataSerializers.INT);
        COFFIN = SynchedEntityData.defineId(GhoulingEntity.class, EntityDataSerializers.BOOLEAN);
        VARIANT = SynchedEntityData.defineId(GhoulingEntity.class, EntityDataSerializers.BYTE);
        STAFF = SynchedEntityData.defineId(GhoulingEntity.class, EntityDataSerializers.ITEM_STACK);
        //CAN_COLLECT = DataTracker.registerData(GhoulingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }


    class GhoulingEscapeDangerGoal extends PanicGoal {
        public GhoulingEscapeDangerGoal(double p_203124_) {
            super(GhoulingEntity.this, p_203124_);
        }

        protected boolean m_202729_() {
            return this.mob.m_203117_() || this.mob.isOnFire();
        }
    }

}
