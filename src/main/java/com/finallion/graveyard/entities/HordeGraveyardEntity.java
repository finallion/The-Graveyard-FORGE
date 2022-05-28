package com.finallion.graveyard.entities;

import com.finallion.graveyard.init.TGAdvancements;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.PatrollingMonster;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class HordeGraveyardEntity extends HostileGraveyardEntity {
    @javax.annotation.Nullable
    private BlockPos patrolTarget;
    private boolean patrolLeader;
    private boolean patrolling;

    protected HordeGraveyardEntity(EntityType<? extends Monster> entityType, Level world, String name) {
        super(entityType, world, name);
    }

    protected void registerGoals() {
        super.registerGoals();
        //this.goalSelector.add(2, new PatrolApproachGoal(this, 10.0F));
        this.goalSelector.addGoal(4, new LongDistancePatrolGoal<>(this, 1.1D, 1.0D));
    }

    public void addAdditionalSaveData(CompoundTag p_33063_) {
        super.addAdditionalSaveData(p_33063_);
        if (this.patrolTarget != null) {
            p_33063_.put("PatrolTarget", NbtUtils.writeBlockPos(this.patrolTarget));
        }

        p_33063_.putBoolean("PatrolLeader", this.patrolLeader);
        p_33063_.putBoolean("Patrolling", this.patrolling);
    }

    public void readAdditionalSaveData(CompoundTag p_33055_) {
        super.readAdditionalSaveData(p_33055_);
        if (p_33055_.contains("PatrolTarget")) {
            this.patrolTarget = NbtUtils.readBlockPos(p_33055_.getCompound("PatrolTarget"));
        }

        this.patrolLeader = p_33055_.getBoolean("PatrolLeader");
        this.patrolling = p_33055_.getBoolean("Patrolling");
    }


    public double getMyRidingOffset() {
        return -0.45D;
    }

    public boolean canBeLeader() {
        return true;
    }


    @javax.annotation.Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_33049_, DifficultyInstance p_33050_, MobSpawnType p_33051_, @javax.annotation.Nullable SpawnGroupData p_33052_, @javax.annotation.Nullable CompoundTag p_33053_) {
        if (p_33051_ != MobSpawnType.PATROL && p_33051_ != MobSpawnType.EVENT && p_33051_ != MobSpawnType.STRUCTURE && this.random.nextFloat() < 0.06F && this.canBeLeader()) {
            this.patrolLeader = true;
        }

        if (p_33051_ == MobSpawnType.PATROL) {
            this.patrolling = true;
        }

        return super.finalizeSpawn(p_33049_, p_33050_, p_33051_, p_33052_, p_33053_);
    }


    public boolean removeWhenFarAway(double p_33073_) {
        return !this.patrolling || p_33073_ > 16384.0D;
    }


    public void setPatrolTarget(BlockPos p_33071_) {
        this.patrolTarget = p_33071_;
        this.patrolling = true;
    }

    public BlockPos getPatrolTarget() {
        return this.patrolTarget;
    }

    public boolean hasPatrolTarget() {
        return this.patrolTarget != null;
    }

    public void setPatrolLeader(boolean p_33076_) {
        this.patrolLeader = p_33076_;
        this.patrolling = true;
    }

    public boolean isPatrolLeader() {
        return this.patrolLeader;
    }

    public boolean canJoinPatrol() {
        return true;
    }


    public void findPatrolTarget() {
        this.patrolTarget = this.blockPosition().offset(-500 + this.random.nextInt(1000), 0, -500 + this.random.nextInt(1000));
        this.patrolling = true;
    }

    protected boolean isPatrolling() {
        return this.patrolling;
    }

    protected void setPatrolling(boolean p_33078_) {
        this.patrolling = p_33078_;
    }

    // getKillCredit
    @Override
    public void createWitherRose(@Nullable LivingEntity adversary) {
        if (adversary instanceof ServerPlayer player) {
            if (this.patrolLeader) {
                TGAdvancements.KILL_HORDE.trigger(player);
            }
        }
        super.setLastHurtByMob(adversary);
    }


    public static class LongDistancePatrolGoal<T extends HordeGraveyardEntity> extends Goal {
        private final T mob;
        private final double speedModifier;
        private final double leaderSpeedModifier;
        private long cooldownUntil;

        public LongDistancePatrolGoal(T p_33084_, double p_33085_, double p_33086_) {
            this.mob = p_33084_;
            this.speedModifier = p_33085_;
            this.leaderSpeedModifier = p_33086_;
            this.cooldownUntil = -1L;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            boolean flag = this.mob.level.getGameTime() < this.cooldownUntil;
            return this.mob.isPatrolling() && this.mob.getTarget() == null && !this.mob.isVehicle() && this.mob.hasPatrolTarget() && !flag;
        }

        public void start() {
        }

        public void stop() {
        }

        public void tick() {
            boolean flag = this.mob.isPatrolLeader();
            PathNavigation pathnavigation = this.mob.getNavigation();
            if (pathnavigation.isDone()) {
                List<HordeGraveyardEntity> list = this.findPatrolCompanions();
                if (this.mob.isPatrolling() && list.isEmpty()) {
                    this.mob.setPatrolling(false);
                } else if (flag && this.mob.getPatrolTarget().m_203195_(this.mob.position(), 10.0D)) {
                    this.mob.findPatrolTarget();
                } else {
                    Vec3 vec3 = Vec3.atBottomCenterOf(this.mob.getPatrolTarget());
                    Vec3 vec31 = this.mob.position();
                    Vec3 vec32 = vec31.subtract(vec3);
                    vec3 = vec32.yRot(90.0F).scale(0.4D).add(vec3);
                    Vec3 vec33 = vec3.subtract(vec31).normalize().scale(10.0D).add(vec31);
                    BlockPos blockpos = new BlockPos(vec33);
                    blockpos = this.mob.level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, blockpos);
                    if (!pathnavigation.moveTo((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), flag ? this.leaderSpeedModifier : this.speedModifier)) {
                        this.moveRandomly();
                        this.cooldownUntil = this.mob.level.getGameTime() + 200L;
                    } else if (flag) {
                        for(HordeGraveyardEntity patrollingmonster : list) {
                            patrollingmonster.setPatrolTarget(blockpos);
                        }
                    }
                }
            }

        }


        private List<HordeGraveyardEntity> findPatrolCompanions() {
            return this.mob.level.getEntitiesOfClass(HordeGraveyardEntity.class, this.mob.getBoundingBox().inflate(16.0D), (p_33089_) -> {
                return p_33089_.canJoinPatrol() && !p_33089_.is(this.mob);
            });
        }

        private boolean moveRandomly() {
            Random random = this.mob.getRandom();
            BlockPos blockpos = this.mob.level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, this.mob.blockPosition().offset(-8 + random.nextInt(16), 0, -8 + random.nextInt(16)));
            return this.mob.getNavigation().moveTo((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), this.speedModifier);
        }
    }



}
