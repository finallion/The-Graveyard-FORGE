package com.finallion.graveyard.entities;

import com.finallion.graveyard.blocks.BrazierBlock;
import com.finallion.graveyard.init.TGAdvancements;
import com.finallion.graveyard.init.TGSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
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

import java.util.EnumSet;
import java.util.UUID;

public class WraithEntity extends HostileGraveyardEntity implements IAnimatable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private static final UUID SPEED_MODIFIER_ATTACKING_UUID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final AttributeModifier ATTACKING_SPEED_BOOST = new AttributeModifier(SPEED_MODIFIER_ATTACKING_UUID, "Attacking speed boost", (double)0.2F, AttributeModifier.Operation.ADDITION);
    private final AnimationBuilder DEATH_ANIMATION = new AnimationBuilder().addAnimation("death", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
    private final AnimationBuilder IDLE_ANIMATION = new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP);
    private final AnimationBuilder SPAWN_ANIMATION = new AnimationBuilder().addAnimation("spawn", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
    private final AnimationBuilder MOVE_ANIMATION = new AnimationBuilder().addAnimation("moving", ILoopType.EDefaultLoopTypes.LOOP);
    private final AnimationBuilder ATTACK_ANIMATION = new AnimationBuilder().addAnimation("attacking", ILoopType.EDefaultLoopTypes.LOOP);
    protected final byte ANIMATION_SPAWN = 0;
    protected final byte ANIMATION_IDLE = 1;
    protected final byte ANIMATION_DEATH = 2;
    protected final byte ANIMATION_ATTACK = 3;
    protected static final EntityDataAccessor<Byte> ANIMATION = SynchedEntityData.defineId(WraithEntity.class, EntityDataSerializers.BYTE);
    private boolean spawned = false;
    private int spawnTimer;

    @Nullable
    private BlockPos homePosition;
    private int timeSinceExtinguish;


    public WraithEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world, "wraith");
        this.moveControl = new FlyingMoveControl(this, 0, true);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.COCOA, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.FENCE, -1.0F);
    }


    public void move(MoverType p_33997_, Vec3 p_33998_) {
        super.move(p_33997_, p_33998_);
        this.checkInsideBlocks();
    }

    @Override
    public void makeStuckInBlock(BlockState p_20006_, Vec3 p_20007_) {
        if (!p_20006_.is(Blocks.COBWEB)) {
            super.makeStuckInBlock(p_20006_, p_20007_);
        }
    }


    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new WraithMeleeGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new ExtinguishGoal());
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));

    }

    protected PathNavigation createNavigation(net.minecraft.world.level.Level p_27815_) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_27815_) {
            public boolean isStableDestination(BlockPos p_27947_) {
                return !this.level.getBlockState(p_27947_.below()).isAir();
            }

            public void tick() {
                super.tick();
            }
        };
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(false);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else if (source instanceof IndirectEntityDamageSource) {
            Entity entity = source.getDirectEntity();
            if (entity instanceof Arrow) {
               return false;
            }
            return super.hurt(source, amount);
        } else {
            return super.hurt(source, amount);
        }
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ANIMATION, ANIMATION_IDLE);
        spawnTimer = 20;
        setAnimation(ANIMATION_SPAWN);
    }

    public void tick() {
        super.tick();
        this.setNoGravity(true);

        if (getHomePosition() == null) {
            homePosition = WraithEntity.this.getOnPos();
        }

        if (this.random.nextFloat() < 0.025F) {
            addParticles();

        }
    }


    private BlockPos getHomePosition() {
        return homePosition;
    }


    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        if (this.homePosition != null) {
            nbt.putInt("BoundX", this.homePosition.getX());
            nbt.putInt("BoundY", this.homePosition.getY());
            nbt.putInt("BoundZ", this.homePosition.getZ());
        }

        nbt.putInt("timeSinceExtinguish", this.timeSinceExtinguish);
    }

    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("BoundX")) {
            this.homePosition = new BlockPos(
                    nbt.getInt("BoundX"),
                    nbt.getInt("BoundY"),
                    nbt.getInt("BoundZ"));
        }
        this.timeSinceExtinguish = nbt.getInt("timeSinceExtinguish");
    }

    @Override
    public void aiStep() {
        spawnTimer--;
        if (level.isClientSide() && spawnTimer >= 0 && spawned) {
            addParticles();
        }

        if (level.isClientSide() && spawnTimer >= 0) {
            level.playSound(null, this.getOnPos(), SoundEvents.SOUL_SAND_BREAK, SoundSource.BLOCKS,2.5F, -10.0F);
        }

        AttributeInstance attributeinstance = this.getAttribute(Attributes.FLYING_SPEED);
        if (!isAggressive()) {
            attributeinstance.removeModifier(ATTACKING_SPEED_BOOST);
        } else {
            if (!attributeinstance.hasModifier(ATTACKING_SPEED_BOOST)) {
                attributeinstance.addTransientModifier(ATTACKING_SPEED_BOOST);
            }
        }



        if (!this.isAlive()) {
            addDeathParticles();
        }
        super.aiStep();
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 42 && !this.level.isClientSide()) {
            this.level.broadcastEntityEvent(this, (byte)60);
            this.remove(RemovalReason.KILLED);
        }
    }


    private void addParticles() {
        level.addParticle(ParticleTypes.SOUL, this.getX() + (random.nextDouble() - random.nextDouble()), this.getY(), this.getZ() + (random.nextDouble() - random.nextDouble()), 0, 0.05F, 0);
    }

    private void addDeathParticles() {
        for (int i = 0; i < 10; i++) {
            level.addParticle(ParticleTypes.SMOKE, this.getX() + (random.nextDouble() - random.nextDouble()), this.getY() + 1.0D, this.getZ() + (random.nextDouble() - random.nextDouble()), 0, 0.05F, 0);
        }
    }

    int getTimeSinceExtinguish() {
        return this.timeSinceExtinguish;
    }


    void addExtinguishCounter() {
        ++this.timeSinceExtinguish;
    }

    public boolean hasHomePosition() {
        return this.homePosition != null;
    }


    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (damageSource == DamageSource.CACTUS ||
                damageSource == DamageSource.DROWN ||
                damageSource == DamageSource.SWEET_BERRY_BUSH ||
                damageSource == DamageSource.HOT_FLOOR ||
                damageSource == DamageSource.FLY_INTO_WALL ||
                damageSource == DamageSource.FALLING_BLOCK ||
                damageSource == DamageSource.FALL ||
                damageSource == DamageSource.ANVIL)
            return true;

        return super.isInvulnerableTo(damageSource);
    }


    @SuppressWarnings("rawtypes")
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        float limbSwingAmount = event.getLimbSwingAmount();
        boolean isMoving = !(limbSwingAmount > -0.05F && limbSwingAmount < 0.05F);

        if (this.isDeadOrDying()) {
            controller.setAnimation(DEATH_ANIMATION);
            return PlayState.CONTINUE;
        }

        if (spawnTimer < 0) {
            if (isAggressive()) {
                controller.setAnimation(ATTACK_ANIMATION);
            } else if (isMoving) {
                controller.setAnimation(MOVE_ANIMATION);
            } else {
                controller.setAnimation(IDLE_ANIMATION);
            }
            return PlayState.CONTINUE;

        }
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState predicate2(AnimationEvent<E> event) {
        if (getAnimation() == 0) {
            event.getController().setAnimation(SPAWN_ANIMATION);
            spawned = true;

            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 6.5D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.FLYING_SPEED, 0.35);
    }


    public byte getAnimation() {
        return entityData.get(ANIMATION);
    }

    public void setAnimation(byte animation) {
        entityData.set(ANIMATION, animation);
    }


    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 2, this::predicate));
        data.addAnimationController(new AnimationController(this, "controller2", 0, this::predicate2));
    }


    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    boolean closerThan(BlockPos pos, int distance) {
        if (pos == null) {
            return false;
        }
        return pos.closerThan(this.getOnPos(), (double) distance);
    }

    @Override
    public void playAmbientSound() {
        this.playSound(TGSounds.WRAITH_AMBIENT.get(), 1.5F, -10.0F);
    }

    @Override
    protected void playHurtSound(DamageSource source) {
        this.playSound(TGSounds.WRAITH_HURT.get(), 1.0F, -10.0F);
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        playDeathSound();
    }

    private void playDeathSound() {
        for (int i = 0; i < 10; i++) {
            this.playSound(TGSounds.WRAITH_AMBIENT.get(), 2.5F, -10.0F);
        }
    }


    @Override
    public boolean canCollideWith(Entity other) {
        if (other instanceof WraithEntity) {
            return false;
        }
        return super.canCollideWith(other);
    }

    @Override
    protected void playBlockFallSound() {
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
    }


    public boolean causeFallDamage(float p_148750_, float p_148751_, DamageSource p_148752_) {
        return false;
    }

    protected void checkFallDamage(double p_27754_, boolean p_27755_, BlockState p_27756_, BlockPos p_27757_) {
    }



    class WraithMeleeGoal extends MeleeAttackGoal {
        WraithMeleeGoal(PathfinderMob mob, double speed, boolean pauseWhenMobIdle) {
            super(mob, speed, pauseWhenMobIdle);
        }

        public boolean canUse() {
            return super.canUse();
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse();
        }
    }


    private class ExtinguishGoal extends Goal {

        ExtinguishGoal() {
            super();
        }

        public boolean canUse() {
            if (WraithEntity.this.getTimeSinceExtinguish() >= 10) {
                return false;
            } else if (WraithEntity.this.random.nextFloat() < 0.3F) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public boolean canContinueToUse() {
            return this.canUse();
        }

        public void tick() {
            if (WraithEntity.this.random.nextInt(this.adjustedTickDelay(10)) == 0) {
                for (int i = 1; i <= 2; ++i) {
                    BlockPos blockPos = WraithEntity.this.getOnPos().below(i);
                    BlockState blockState = WraithEntity.this.level.getBlockState(blockPos);
                    Block block = blockState.getBlock();
                    boolean bl = false;
                    boolean torchAndLantern = false;
                    if (blockState.is(BlockTags.CANDLES)) {
                        if (block instanceof CandleBlock) {
                            if (blockState.getValue(BlockStateProperties.LIT)) {
                                bl = true;
                            }
                        } else if (block instanceof BrazierBlock) {
                            if (blockState.getValue(BlockStateProperties.LIT)) {
                                bl = true;
                            }
                        }
                        if (bl) {
                            //WraithEntity.this.world.syncWorldEvent(1502, blockPos, 0);
                            WraithEntity.this.level.playSound((Player) null, blockPos, SoundEvents.CANDLE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
                            WraithEntity.this.level.setBlock(blockPos, (BlockState) blockState.setValue(BlockStateProperties.LIT, false), 3);
                            WraithEntity.this.addExtinguishCounter();
                            triggerAdvancement(WraithEntity.this, bl);
                            return;
                        }
                    }
                    if (random.nextInt(10) == 0) {
                        if (blockState.is(Blocks.TORCH)) {
                            WraithEntity.this.level.setBlock(blockPos, Blocks.SOUL_TORCH.defaultBlockState(), 3);
                            torchAndLantern = true;
                        } else if (blockState.is(Blocks.LANTERN)) {
                            WraithEntity.this.level.setBlock(blockPos, Blocks.SOUL_LANTERN.defaultBlockState()
                                    .setValue(BlockStateProperties.HANGING, blockState.getValue(BlockStateProperties.HANGING))
                                    .setValue(BlockStateProperties.WATERLOGGED, blockState.getValue(BlockStateProperties.WATERLOGGED)), 3);
                            torchAndLantern = true;
                        } else if (blockState.is(Blocks.WALL_TORCH)) {
                            WraithEntity.this.level.setBlock(blockPos, Blocks.SOUL_WALL_TORCH.defaultBlockState()
                                    .setValue(HorizontalDirectionalBlock.FACING, blockState.getValue(HorizontalDirectionalBlock.FACING)), 3);
                            torchAndLantern = true;
                        }

                        if (torchAndLantern) {
                            WraithEntity.this.level.playSound((Player) null, blockPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
                            WraithEntity.this.addExtinguishCounter();
                            return;
                        }
                    }
                }

            }
        }
    }

    private static void triggerAdvancement(WraithEntity wraith, boolean bool) {
        if (bool) {
            Player player = wraith.level.getNearestPlayer(wraith, 10.0D);
            if (player instanceof ServerPlayer) {
                TGAdvancements.DIM_LIGHT.trigger((ServerPlayer) player);
            }
        }
    }


    class WraithWanderAroundGoal extends Goal {
        private static final int MAX_DISTANCE = 15;

        WraithWanderAroundGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            return WraithEntity.this.navigation.isDone() && WraithEntity.this.random.nextInt(10) == 0;
        }


        public boolean canContinueToUse() {
            return WraithEntity.this.navigation.isInProgress();
        }


        public void start() {
            Vec3 vec3 = this.findPos();
            if (vec3 != null) {
                WraithEntity.this.navigation.moveTo(WraithEntity.this.navigation.createPath(new BlockPos(vec3), 1), 1.0D);
            }


        }

        @Nullable
        private Vec3 findPos() {
            Vec3 vec3d2;
            if (!WraithEntity.this.closerThan(WraithEntity.this.homePosition, MAX_DISTANCE) && WraithEntity.this.hasHomePosition()) {
                Vec3 vec3dx = Vec3.atCenterOf(WraithEntity.this.homePosition);
                vec3d2 = vec3dx.subtract(WraithEntity.this.position()).normalize();
            } else {
                vec3d2 = WraithEntity.this.getViewVector(0.0F);
            }


            Vec3 vec3d3 = HoverRandomPos.getPos(WraithEntity.this, 8, 2, vec3d2.x, vec3d2.z, 1.5707964F, 2, 1);
            return vec3d3 != null ? vec3d3 : AirAndWaterRandomPos.getPos(WraithEntity.this, 8, 2, -2, vec3d2.x, vec3d2.z, 1.5707963705062866D);
        }

    }


}
